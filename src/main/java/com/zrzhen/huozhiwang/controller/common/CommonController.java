package com.zrzhen.huozhiwang.controller.common;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.zrzhen.huozhiwang.common.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Properties;

@Controller
public class CommonController {

    /*生成商城验证码图片*/
    @GetMapping("/common/mall/kaptcha")
    public void mallKaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*第一步：配置*/
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.put("kaptcha.border","no");
        properties.put("kaptcha.textproducer.font.color","27,174,174");
        properties.put("kaptcha.noise.impl","com.google.code.kaptcha.impl.NoNoise");
//        properties.put("kaptcha.noise.color","20,33,42");
        properties.put("kaptcha.text.producer.font.size","10");
        properties.put("kaptcha.image.height","40");
        properties.put("kaptcha.image.width","110");
        properties.put("kaptcha.session.key", Constants.MALL_VERIFY_CODE_KEY);
        properties.put("kaptcha.textproducer.char.space","3");
        properties.put("kaptcha.textproducer.char.length","4");
        Config config = new Config(properties);
        kaptcha.setConfig(config);
        /*创建字节数组用于储存图片*/
        byte[] captchaOutputStream = null;
        /*创建二进制流*/
        ByteArrayOutputStream imgOutPutStream = new ByteArrayOutputStream();
        try{
            //生产验证码字符串
            String verifyCode = kaptcha.createText();
            //将验证码字符串保存在session中，以便校验
            request.getSession().setAttribute(Constants.MALL_VERIFY_CODE_KEY, verifyCode);
            //使用验证码字符串生成图片
            BufferedImage bufferedImage = kaptcha.createImage(verifyCode);
            //将图片写入流中
            ImageIO.write(bufferedImage,"jpg",imgOutPutStream);
        }catch (IllegalArgumentException | IOException e){
            response.sendError(response.SC_NOT_FOUND);
            return;
        }
//        将图片写入到浏览器中
        captchaOutputStream = imgOutPutStream.toByteArray();
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader("Expires",0);
        response.setContentType("image/jpeg");
        /**/
        ServletOutputStream servletOutputStream = response.getOutputStream();
        servletOutputStream.write(captchaOutputStream);
        servletOutputStream.flush();
        servletOutputStream.close();
    }
}
