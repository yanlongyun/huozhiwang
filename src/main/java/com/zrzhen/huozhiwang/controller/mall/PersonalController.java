package com.zrzhen.huozhiwang.controller.mall;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class PersonalController {
    /*开发登录和注册功能*/


    @GetMapping({"/login","login.html"})
    public String login(){
        return "/mall/login";
    }

    @GetMapping({"/register","register.html"})
    public String register(){
        return "/mall/register";
    }



}
