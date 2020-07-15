package com.zrzhen.huozhiwang.result;


import com.zrzhen.huozhiwang.util.JsonUtil;

/**
 * @author chenanlian
 */
public class ResponseResult<T> {

    private int code;

    private String msg;


    private T data;

    public ResponseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功结果，data 为null
     *
     * @return
     */
    public static ResponseResult buildSuccess() {
        return new ResponseResult(SysResultCode.SUCCESS.getCode(), SysResultCode.SUCCESS.getMsg());
    }

    /**
     * 成功结果
     *
     * @param data data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> buildSuccess(T data) {
        return new ResponseResult(SysResultCode.SUCCESS.getCode(), SysResultCode.SUCCESS.getMsg(), data);
    }


    /**
     * 根据系统结果枚举生成结果，data为null
     *
     * @return
     */
    public static ResponseResult build(ResultCode resultCode) {

        return new ResponseResult(resultCode.getCode(), resultCode.getMsg());
    }


    public static <T> ResponseResult<T> build(ResultCode resultCode, T data) {
        return new ResponseResult(resultCode.getCode(), resultCode.getMsg(), data);
    }


    public static <T> ResponseResult<T> build(int code, String msg, T data) {
        return new ResponseResult(code, msg, data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JsonUtil.entity2Json(this);
    }
}
