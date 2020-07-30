package com.zrzhen.huozhiwang.util;

import org.springframework.util.StringUtils;

import javax.xml.bind.annotation.XmlType;

public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";
    private static final String DEFAULT_FAIL_MESSAGE = "FAIL";
    private static final int RESULT_CODE_SUCCESS = 200;
    private static final int RESULT_CODE_FAIL = 500;

    /*成功：用于没有返回值，只是给予提示信息的请求*/
    public static Result genSuccessResult() {
        Result result = new Result();
        result.setResultCode(RESULT_CODE_SUCCESS);
        result.setMessage(DEFAULT_SUCCESS_MESSAGE);
        return result;
    }

    /*成功：用于手动修改message的*/
    public static Result genSuccessResult(String message) {
        Result result = new Result();
        result.setResultCode(RESULT_CODE_SUCCESS);
        result.setMessage(message);
        return result;
    }

    /*成功：用于手动输入data值，只是给予提示信息的请求*/
    public static Result genSuccessResult(Object data) {
        Result result = new Result();
        result.setResultCode(RESULT_CODE_SUCCESS);
        result.setMessage(DEFAULT_SUCCESS_MESSAGE);
        result.setData(data);
        return result;
    }

    /*失败：返回失败结果*/
    public static Result genFailResult(String message) {
        Result result = new Result();
        result.setResultCode(RESULT_CODE_FAIL);
        if (StringUtils.isEmpty(message)) {
            result.setMessage(DEFAULT_FAIL_MESSAGE);
        } else {
            result.setMessage(message);
        }
        return result;
    }

    /*错误结果：太多种类，故没有默认配置，手动输入*/
    public static Result genErrorResult(int code, String message) {
        Result result = new Result();
        result.setResultCode(code);
        result.setMessage(message);
        return result;
    }

}
