package com.zrzhen.huozhiwang.controller.mall;

import com.alibaba.fastjson.JSONObject;
import com.zrzhen.huozhiwang.common.Constants;
import com.zrzhen.huozhiwang.common.ServiceResultEnum;
import com.zrzhen.huozhiwang.entity.MallUser;
import com.zrzhen.huozhiwang.service.UserService;
import com.zrzhen.huozhiwang.util.Result;
import com.zrzhen.huozhiwang.util.ResultGenerator;
import com.zrzhen.huozhiwang.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class PersonalController {
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;
    @Autowired
    UserService userService;

    /*跳转到登录面，这个是通过链接，get请求*/
    @GetMapping({"/login","login.html"})
    public String loginPage(){
        return "/mall/login";
    }
    
    @GetMapping({"/register","register.html"})
    public String registerPage(){
        return "/mall/register";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute(Constants.MALL_USER_SESSION_KEY);
        return "mall/login";
    }
    @GetMapping("/personal")
    public String personalPage(HttpServletRequest request,
                               HttpSession httpSession) {
        request.setAttribute("path", "personal");
        return "mall/personal";
    }


    /**
    *注册方法
    * @param: [loginName, password, verifyCode, httpSession]
    * @createDate: 2020/7/30 16:01
    * @return: com.zrzhen.huozhiwang.util.Result
    */
    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestParam String loginName,
                           @RequestParam String password,
                           @RequestParam String verifyCode,
                           HttpSession httpSession){
        if (StringUtils.isEmpty(loginName)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_NAME_NULL.getResult());
        }
        if (StringUtils.isEmpty(password)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_PASSWORD_NULL.getResult());
        }
        if (StringUtils.isEmpty(verifyCode)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_VERIFY_CODE_NULL.getResult());
        }
        /*检验验证码*/
        String kaptchaCode =httpSession.getAttribute(Constants.MALL_VERIFY_CODE_KEY)+"";
        if(StringUtils.isEmpty(kaptchaCode) || !verifyCode.equalsIgnoreCase(kaptchaCode)){
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_VERIFY_CODE_ERROR.getResult());
        }
        String result = userService.register(loginName,password);
        if(ServiceResultEnum.SUCCESS.getResult().equals(result)){
            return ResultGenerator.genSuccessResult(ServiceResultEnum.SUCCESS.getResult());
        }
        return ResultGenerator.genFailResult(result);
    }

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestParam String loginName,
                           @RequestParam String password,
                           @RequestParam String verifyCode,
                           HttpSession httpSession){
        if (StringUtils.isEmpty(loginName)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_NAME_NULL.getResult());
        }
        if (StringUtils.isEmpty(password)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_PASSWORD_NULL.getResult());
        }
        if (StringUtils.isEmpty(verifyCode)) {
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_VERIFY_CODE_NULL.getResult());
        }
        /*检验验证码*/
        String kaptchaCode =httpSession.getAttribute(Constants.MALL_VERIFY_CODE_KEY)+"";
        if(StringUtils.isEmpty(kaptchaCode) || !verifyCode.equalsIgnoreCase(kaptchaCode)){
            return ResultGenerator.genFailResult(ServiceResultEnum.LOGIN_VERIFY_CODE_ERROR.getResult());
        }

        String result = userService.login(loginName,password,httpSession);
        if(ServiceResultEnum.SUCCESS.getResult().equals(result)){
            return ResultGenerator.genSuccessResult(ServiceResultEnum.SUCCESS.getResult());
        }
        return ResultGenerator.genFailResult(result);
    }

    @PostMapping("/personal/updateInfo")
    @ResponseBody
    public Result updataInfo(@RequestBody MallUser mallUser,HttpSession httpSession){
        //userService.updateInfo(mallUser);
        String result = userService.updateInfo(mallUser,httpSession);
        if(result.equalsIgnoreCase("success")){
            return ResultGenerator.genSuccessResult(ServiceResultEnum.UPDATE_USER_SUCCESS.getResult());
        }
        return ResultGenerator.genFailResult(result);
    }
}
