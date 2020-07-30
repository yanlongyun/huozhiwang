package com.zrzhen.huozhiwang.config;

import com.zrzhen.huozhiwang.common.Constants;
import com.zrzhen.huozhiwang.interceptor.MallCartNumberInterceptor;
import com.zrzhen.huozhiwang.interceptor.MallLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: 慧燕
 * @date: 2020/7/30 20:25
 * @copyright yanlongyun2020
 */
@Configuration
public class MallWebMvcConfigurer implements WebMvcConfigurer {
    @Autowired
    private MallLoginInterceptor mallLoginInterceptor;
    @Autowired
    private MallCartNumberInterceptor mallCartNumberInterceptor;

    public void addInterceptors(InterceptorRegistry registry){
        /*添加一个拦截器，对购物车中的数量进行统一处理*/
        registry.addInterceptor(mallCartNumberInterceptor)
                .excludePathPatterns("/login")
                .excludePathPatterns("/admin/**")
                .excludePathPatterns("/register")
                .excludePathPatterns("/logout");
        registry.addInterceptor(mallLoginInterceptor)
                .excludePathPatterns("/login")
                .excludePathPatterns("/register")
                .excludePathPatterns("/logout")
                .excludePathPatterns("/admin/**")
                .addPathPatterns("/goods/detail/**")
                .addPathPatterns("/shop-cart")
                .addPathPatterns("/shop-cart/**")
                .addPathPatterns("/saveOrder")
                .addPathPatterns("/orders")
                .addPathPatterns("/orders/**")
                .addPathPatterns("/personal")
                .addPathPatterns("/personal/updateInfo")
                .addPathPatterns("/selectPayType")
                .addPathPatterns("/payPage");
    }



    /*配置路径*/
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:" + Constants.FILE_UPLOAD_DIC);
        registry.addResourceHandler("/goods-img/**").addResourceLocations("file:" + Constants.FILE_UPLOAD_DIC);
    }
}
