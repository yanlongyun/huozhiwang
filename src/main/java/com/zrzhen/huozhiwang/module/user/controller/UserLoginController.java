package com.zrzhen.huozhiwang.module.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.zrzhen.huozhiwang.module.user.dao.UserMapper;
import com.zrzhen.huozhiwang.module.user.po.User;
import com.zrzhen.huozhiwang.module.user.service.impl.UserServiceImpl;
import com.zrzhen.huozhiwang.result.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserLoginController {
    @Autowired
    UserServiceImpl userService;


    @RequestMapping("/register")
    @ResponseBody
    public ResponseResult<String> register(@RequestBody JSONObject params) {
        String insert = userService.insert(params);
        return ResponseResult.buildSuccess(insert);
    }

    @RequestMapping("/checkRegister")
    @ResponseBody
    public String checkRegister(@RequestBody JSONObject params) {
        return userService.checkRegister(params);
    }

}