package com.zrzhen.huozhiwang.common;

public enum ServiceResultEnum {
    ERROR("error"),
    SUCCESS("success"),
    FAIL("FAIL"),
    LOGIN_NAME_NULL("请输入登录名"),
    LOGIN_PASSWORD_NULL("请输入密码"),
    LOGIN_VERIFY_CODE_NULL("请输入验证码"),
    LOGIN_VERIFY_CODE_ERROR("验证码错误"),
    SAME_LOGIN_NAME_EXIST("用户名已存在"),
    DB_ERROR("database error"),
    LOGIN_ERROR("登录失败！"),
    USER_NO_EXIST("用户不存在"),
    LOGIN_USER_LOCKED("用户已被禁止登录！"),
    UPDATE_USER_FAIL("修改用户信息失败"),
    UPDATE_USER_SUCCESS("修改用户信息成功")
    ;

    private String result;
    ServiceResultEnum(String result){
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
