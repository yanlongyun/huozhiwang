package com.zrzhen.huozhiwang.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zrzhen.huozhiwang.dao.UserMapper;
import com.zrzhen.huozhiwang.entity.User;
import com.zrzhen.huozhiwang.service.UserService;
import com.zrzhen.huozhiwang.util.SessionMapUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;

    @Override
    public String register(JSONObject params) {
        User user = new User();
        String phone = params.getString("phone");
        String password = params.getString("password");
        user.setPhone(phone);
        user.setPassword(password);
        int i = userMapper.insert(user);
        if (i > 0) {
            return "注册成功";
        }
        return "注册失败";
    }

    @Override
    public String checkRegister(JSONObject params) {
        String phone = params.getString("phone");
        if (userMapper.oneByPhone(phone) > 0) {
            return "该手机号已注册,请重新输入";
        } else {
            return "true";
        }
    }

    @Override
    public String login(JSONObject params) {
        /*获取，如果没有的话就生成一个*/
        String sessionid = SessionMapUtil.getSession(request, response);
        /*获取sessionid的值*/
        Long value = SessionMapUtil.getValue(sessionid);
        if (value != null) {
            return "已经登录";
        }
        String phone = params.getString("phone");
        String password = params.getString("password");
        User user = userMapper.selectByPhoneAndPwd(phone, password);
        if (user == null) {
            //  检查手机号看用户是否存在，不存在提醒用户注册
            if (userMapper.oneByPhone(phone) > 0) {
                return "密码错误，请重新输入";
            } else {
                return "该手机号未注册，请注册后登录";
            }
        } else {
            SessionMapUtil.putValue(sessionid, user.getId());
            return "登录成功";
            /*生成sessionid*/
        }
    }

    @Override
    public String checkLogin(JSONObject params) {
        /*获取，如果没有的话就生成一个*/
        String sessionid = SessionMapUtil.getSession(request, response);
        /*获取sessionid的值*/
        Long value = SessionMapUtil.getValue(sessionid);
        if (value == null) {
            return "没有登录";
        }
        return "已经登录";
    }
}

