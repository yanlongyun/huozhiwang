package com.zrzhen.huozhiwang.service;

import com.zrzhen.huozhiwang.entity.MallUser;
import com.zrzhen.huozhiwang.util.Result;

import javax.servlet.http.HttpSession;

/**
 * @author: 慧燕
 * @date: 2020/7/30 16:02
 * @copyright yanlongyun2020
 */
public interface UserService {

    /**
    *登陆方法
    * @param: [loginName, passwordMD5, httpSession]
    * @createDate: 2020/7/30 16:04
    * @return: java.lang.String
    */
    //String login(String loginName, String passwordMD5, HttpSession httpSession);
    /**
    *注册方法
    * @param: [loginName, password]
    * @createDate: 2020/7/30 16:16
    * @return: java.lang.String
    */
    String register(String loginName, String password);


    String login(String loginName, String password,HttpSession session);

    /**
    *
    * @param: [mallUser]
    * @createDate: 2020/7/30 23:00
    * @return: java.lang.String
    */
    String updateInfo(MallUser mallUser,HttpSession httpSession);

}
