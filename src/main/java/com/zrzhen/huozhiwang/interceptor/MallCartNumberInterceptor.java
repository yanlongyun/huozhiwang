package com.zrzhen.huozhiwang.interceptor;

import com.zrzhen.huozhiwang.common.Constants;
import com.zrzhen.huozhiwang.controller.vo.MallUserVO;
import com.zrzhen.huozhiwang.dao.ShoppingCartItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Console;


/**
 * @author: 慧燕
 * @date: 2020/7/30 19:48
 * @copyright yanlongyun2020
 */
@Component
public class MallCartNumberInterceptor implements HandlerInterceptor {
    @Autowired
    ShoppingCartItemMapper shoppingCartItemMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //购物车中的数量会更改，但是在这些接口中并没有对session中的数据做修改，这里统一处理一下
        if (null != request.getSession() && null != request.getSession().getAttribute(Constants.MALL_USER_SESSION_KEY)) {
            //如果当前为登陆状态，就查询数据库并设置购物车中的数量值
            MallUserVO mallUserVO = (MallUserVO) request.getSession().getAttribute(Constants.MALL_USER_SESSION_KEY);
            //设置购物车中的数量
            Integer count = shoppingCartItemMapper.selectCountByUserId(mallUserVO.getUserId());
            if (count!=null) {
                mallUserVO.setShopCartItemCount(count);
                request.getSession().setAttribute(Constants.MALL_USER_SESSION_KEY, mallUserVO);
            }else {
                mallUserVO.setShopCartItemCount(0);
                request.getSession().setAttribute(Constants.MALL_USER_SESSION_KEY, mallUserVO);
            }
        }
        return true;
    }
}
