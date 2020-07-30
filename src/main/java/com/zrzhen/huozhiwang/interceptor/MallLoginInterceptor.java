package com.zrzhen.huozhiwang.interceptor;

import com.zrzhen.huozhiwang.common.Constants;
import com.zrzhen.huozhiwang.controller.vo.MallUserVO;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: 慧燕
 * @date: 2020/7/30 19:47
 * @copyright yanlongyun2020
 */
@Component
public class MallLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*检查是否已经登录*/
        if(null == request.getSession().getAttribute(Constants.MALL_USER_SESSION_KEY)){
            response.sendRedirect(request.getContextPath()+"/login");
            return false;
        }else {
            return true;
        }
    }
}
