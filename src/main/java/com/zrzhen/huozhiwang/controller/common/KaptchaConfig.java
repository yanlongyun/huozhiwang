package com.zrzhen.huozhiwang.controller.common;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import com.google.code.kaptcha.util.Config;

import java.util.Properties;

@Controller
public class KaptchaConfig {
    /*验证码配置*/
    @Bean
    public DefaultKaptcha getDefaultKaptcha(){
        com.google.code.kaptcha.impl.DefaultKaptcha defaultKaptcha = new com.google.code.kaptcha.impl.DefaultKaptcha();
        Properties properties = new Properties();
//        配置验证码的边界，默认yes
        properties.put("kaptcha.border", "no");
//        验证码文字颜色，默认black
        properties.put("kaptcha.textproducer.font.color", "black");
//        图片宽度，默认200
        properties.put("kaptcha.image.width", "150");
//        图片高度，默认50
        properties.put("kaptcha.image.height", "40");
//        字体大小，默认40
        properties.put("kaptcha.textproducer.font.size", "30");
//        默认其他
        properties.put("kaptcha.session.key", "verifyCode");
//        文字间隔
        properties.put("kaptcha.textproducer.char.space", "5");

        Config config = new Config(properties);

        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }

}
