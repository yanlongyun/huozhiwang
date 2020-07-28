package com.zrzhen.huozhiwang.controller.mall;

import com.alibaba.fastjson.JSONObject;
import com.zrzhen.huozhiwang.service.impl.UserServiceImpl;
import com.zrzhen.huozhiwang.common.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    /*注册功能*/
    @RequestMapping("/register")
    @ResponseBody
    public ResponseResult<String> register(@RequestBody JSONObject params) {
        String insert = userService.register(params);
        return ResponseResult.buildSuccess(insert);
    }
    @RequestMapping("/checkRegister")
    @ResponseBody
    public ResponseResult<String> checkRegister(@RequestBody JSONObject params) {
        String checkRegister = userService.checkRegister(params);
        return ResponseResult.buildSuccess(checkRegister);
    }

    /*登录功能*/
    /**
     * 检查是否登录
     *
     * @return
     */
    @RequestMapping("checkLogin")
    @ResponseBody
    public String checkLogin(@RequestBody JSONObject params) {
        return userService.checkLogin(params);
    }

    @RequestMapping("/login")
    @ResponseBody
    public ResponseResult<String> login(@RequestBody JSONObject params) {
        String insert = userService.login(params);
        return ResponseResult.buildSuccess(insert);
    }


}