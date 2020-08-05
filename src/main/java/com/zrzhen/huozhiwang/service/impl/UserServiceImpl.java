package com.zrzhen.huozhiwang.service.impl;

import com.zrzhen.huozhiwang.common.Constants;
import com.zrzhen.huozhiwang.common.ServiceResultEnum;
import com.zrzhen.huozhiwang.controller.vo.MallUserVO;
import com.zrzhen.huozhiwang.dao.MallUserMapper;
import com.zrzhen.huozhiwang.entity.MallUser;
import com.zrzhen.huozhiwang.service.UserService;
import com.zrzhen.huozhiwang.util.BeanUtil;
import com.zrzhen.huozhiwang.util.CharacterUtils;
import com.zrzhen.huozhiwang.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @author: 慧燕
 * @date: 2020/7/30 16:05
 * @copyright yanlongyun2020
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    MallUserMapper mallUserDaoMapper;

    @Override
    public String register(String loginName, String password) {
        /*先判断是否有该用户，如果有，则返回已注册*/
        if (mallUserDaoMapper.selectByLoginName(loginName) != null) {
            return ServiceResultEnum.SAME_LOGIN_NAME_EXIST.getResult();
        }
        MallUser user = new MallUser();
        user.setLoginName(loginName);
        user.setNickName(loginName);
        user.setPasswordMd5(MD5Util.MD5Encode(password, "UTF-8"));
        if (mallUserDaoMapper.insertSelective(user) > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.DB_ERROR.getResult();
    }

    @Override
    public String login(String loginName, String password, HttpSession httpSession) {
        /*1.查询用户是否存在，不存在返回对应信息*/
        String passwordMd5 = MD5Util.MD5Encode(password, "UTF-8");
        MallUser user = mallUserDaoMapper.selectByLoginNameAndPassword(loginName, passwordMd5);
        if (user != null && httpSession != null) {
            /*锁定标识字段，如果为0则未锁定，1锁定*/
            if (user.getLockedFlag() == 1) {
                /*锁定的禁止登陆*/
                return ServiceResultEnum.LOGIN_USER_LOCKED.getResult();
            }
            //昵称太长 影响页面展示，所以只显示一部分
            if (user.getNickName() != null && user.getNickName().length() > 7) {
                String tempNickName = user.getNickName().substring(0, 7) + "..";
                user.setNickName(tempNickName);
            }
            /*把用户信息封装为MallUserVo对象*/
            MallUserVO mallUserVo = new MallUserVO();
            BeanUtil.copyProperties(user, mallUserVo);
            /*把该对象给session,这里因为可以使用拦截器设置购物车数量，所以不在这里设置。*/
            httpSession.setAttribute(Constants.MALL_USER_SESSION_KEY, mallUserVo);
            /*返回成功信息*/
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.LOGIN_ERROR.getResult();
    }

    @Override
    public String updateInfo(MallUser mallUser, HttpSession httpSession) {
        /*通过userID来查询user是否存在*/
        MallUser user = mallUserDaoMapper.selectByPrimaryKey(mallUser.getUserId());
        if (user != null) {
            if (mallUserDaoMapper.updateByPrimaryKeySelective(mallUser) > 0) {
                /*更新session中的内容*/
                MallUser userAfter = mallUserDaoMapper.selectByPrimaryKey(mallUser.getUserId());
                MallUserVO mallUserVO = new MallUserVO();
                BeanUtil.copyProperties(userAfter, mallUserVO);
                //昵称太长 影响页面展示，所以只显示一部分
                if (mallUserVO.getNickName() != null && mallUserVO.getNickName().length() > 7) {
                    String tempNickName = user.getNickName().substring(0, 7) + "..";
                    mallUserVO.setNickName(tempNickName);
                }
                httpSession.setAttribute(Constants.MALL_USER_SESSION_KEY, mallUserVO);
                return ServiceResultEnum.SUCCESS.getResult();
            }
            return ServiceResultEnum.UPDATE_USER_FAIL.getResult();
        }
        return ServiceResultEnum.USER_NO_EXIST.getResult();
    }
}
