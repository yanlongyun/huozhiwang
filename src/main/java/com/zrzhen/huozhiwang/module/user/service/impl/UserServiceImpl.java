package com.zrzhen.huozhiwang.module.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.zrzhen.huozhiwang.module.user.dao.UserMapper;
import com.zrzhen.huozhiwang.module.user.po.User;
import com.zrzhen.huozhiwang.module.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public String insert(JSONObject params) {
        User user = new User();
        String phone = params.getString("phone");
        String password = params.getString("password");
        user.setPhone(phone);
        user.setPassword(password);
        int i = userMapper.insert(user);
        if(i>0){
            return "注册成功";
        }
        return "注册失败";
    }

    @Override
    public String checkRegister(JSONObject params) {
        String phone = params.getString("phone");
        if(userMapper.oneByPhone(phone)>0){
            return "该手机号已注册,请重新输入";
        }else {
            return "true";
        }
    }


}

