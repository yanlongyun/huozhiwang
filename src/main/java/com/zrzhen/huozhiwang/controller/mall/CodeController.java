package com.zrzhen.huozhiwang.controller.mall;

import com.alibaba.fastjson.JSONObject;
import com.zrzhen.huozhiwang.service.UserService;
import com.zrzhen.huozhiwang.common.ResponseResult;
import com.zrzhen.huozhiwang.util.VerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@Controller
public class CodeController {
    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest httpServletRequest;
 /*   *//*获取验证码*//*
    @GetMapping("/codeImg")
    public void codeImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
       // userService.codeImg(httpServletRequest,httpServletResponse);
        HttpSession session=request.getSession();
        //利用图片工具生成图片
        //第一个参数是生成的验证码，第二个参数是生成的图片
        Object[] objs = VerifyCodeUtils.createImage();
        //将验证码存入Session
        session.setAttribute("imageCode",objs[0]);

        //将图片输出给浏览器
        BufferedImage image = (BufferedImage) objs[1];
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        ImageIO.write(image, "png", os);
    }*/

    @RequestMapping("checkAuthCode")
    @ResponseBody
    public ResponseResult<String> checkAuthCode(@RequestBody JSONObject params) throws IOException {
        String systemCode = (String) httpServletRequest.getSession().getAttribute("imgCode");
        String userCode = params.getString("authCode");
        if(userCode.equalsIgnoreCase(systemCode)){
            return ResponseResult.build(1,"success","验证码正确");
        }else {
            return ResponseResult.build(0,"fail","验证码不正确");
        }
    }

    @RequestMapping(value = "/codeImg", method = {RequestMethod.POST, RequestMethod.GET})
    protected void createImg(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //1.生成随机的验证码及图片
        Object[] objs = VerifyUtil.createImage();
        //2.将验证码存入session
        String imgcode = (String) objs[0];
        HttpSession session = req.getSession();
        session.setAttribute("imgCode", imgcode);
        //3.将图片输出给浏览器
        BufferedImage img = (BufferedImage) objs[1];
        res.setContentType("image/png");
        //服务器自动创建输出流，目标指向浏览器
        OutputStream os = res.getOutputStream();
        ImageIO.write(img, "png", os);
        os.close();
    }
}
