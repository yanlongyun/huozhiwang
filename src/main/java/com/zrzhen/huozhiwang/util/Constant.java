package com.zrzhen.huozhiwang.util;

import org.springframework.beans.factory.annotation.Value;

public class Constant {
    /**
     * @author chenanlian
     */
    public static String domain;

    public static Integer syscode;

    public static String db;



    @Value("${Constant.domain}")
    public void setDomain(String domain) {

        Constant.domain = domain;
    }
}
