package com.zrzhen.huozhiwang.common;

public enum SysResultCode implements ResultCode {


    /*设置不同情况下的返回值和信息*/
    SUCCESS(1, "success"),

    FAIL(0, "fail"),


    BEANUTILS_ERROR(10000, "BeanUtils.copyProperties error");
    private int code;

    private String msg;


    SysResultCode(int code, String msg) {
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
