package com.zrzhen.huozhiwang.util;

//import com.huiyan.bohui.result.Constant;

import com.zrzhen.huozhiwang.util.Constant;

public class CheckUtil {

    public static Long userid() {
        String id = Constant.syscode + String.valueOf(System.currentTimeMillis());
        return Long.valueOf(id);
    }

    public static void main(String[] args) {

        System.out.println(userid());

        System.out.println(System.currentTimeMillis());
    }
}
