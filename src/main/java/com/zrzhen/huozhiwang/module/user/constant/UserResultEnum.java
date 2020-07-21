package com.zrzhen.huozhiwang.module.user.constant;

import com.zrzhen.huozhiwang.result.ResultCode;

/**
 * 用户模块业务错误码枚举
 */
public enum UserResultEnum implements ResultCode {

    REGISTER_FAIL(20001, "注册失败"),
    REGISTER_SUCCESS(0, "激活成功，请记住您的登录密码，现在可以去登录了！"),
    USER_NO_EXIST(1, "账户不存在，请检查链接是否有误，"),
    TOKEN_NO_RIGHT(2, "token有误，请检查链接是否正确"),
    TOKEN_EXPIRED(3, "验证已过期，请重新注册"),
    TOKEN_SUCCEED(4, "验证已成功，无需重复验证"),
    EXCEPTION(5, "验证异常，请重试"),;


    private int code;

    private String msg;

    UserResultEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }


    @Override
    public String getMsg() {
        return msg;
    }
}
