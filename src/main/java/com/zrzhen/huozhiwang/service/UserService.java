package com.zrzhen.huozhiwang.service;


import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UserService {

    public String register(JSONObject params);
    public String checkRegister(JSONObject params);
    public String login(JSONObject params);
    public String checkLogin(JSONObject params);
}
