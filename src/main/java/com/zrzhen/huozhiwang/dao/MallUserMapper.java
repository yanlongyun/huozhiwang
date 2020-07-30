package com.zrzhen.huozhiwang.dao;

import com.zrzhen.huozhiwang.entity.MallUser;

/**
 * @author: 慧燕
 * @date: 2020/7/30 16:19
 * @copyright yanlongyun2020
 */
public interface MallUserMapper {
    /*根据用户名查询用户信息，用于注册前查看是否有重复信息*/
    MallUser selectByLoginName(String loginName);

    /*插入用户信息，用户注册*/
    int insertSelective(MallUser mallUser);

    /*根据用户名密码查询用户，用于登录操作*/
    MallUser selectByLoginNameAndPassword(String loginName,String passwordMd5);

    /*修改用户信息*/
    int updateByPrimaryKeySelective(MallUser mallUser);

    /*根据主键查询用户，返回用户信息*/
    MallUser selectByPrimaryKey(Long userId);
}
