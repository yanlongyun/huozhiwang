package com.zrzhen.huozhiwang.module.user.service;


import com.alibaba.fastjson.JSONObject;

public interface UserService {

    public String insert(JSONObject params);
    public String checkRegister(JSONObject params);
}
