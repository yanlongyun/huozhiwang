package com.zrzhen.huozhiwang.module.user.constant;

import com.zrzhen.huozhiwang.result.ResultCode;

/**
 * 用户模块业务错误码枚举
 */
public enum UserResultEnum implements ResultCode {

    REGISTER_FAIL(20001, "注册失败"),
    ;

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
