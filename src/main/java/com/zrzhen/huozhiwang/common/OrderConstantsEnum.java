package com.zrzhen.huozhiwang.common;

/**
 * @author: 慧燕
 * @date: 2020/8/3 21:11
 * @copyright yanlongyun2020
 */
public enum OrderConstantsEnum {
    ORDER_GEN_KEY("redis中的保存orderID的key"),
    ORDER_DEFAULT_KEY("默认的key"),
    ORDER_ITEM_GEN("订单项的key");

    private String result;
    OrderConstantsEnum(String result){
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
