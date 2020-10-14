package com.zrzhen.huozhiwang.interceptor;

import com.zrzhen.huozhiwang.common.Constants;
import com.zrzhen.huozhiwang.controller.vo.IndexCategoryVO;
import com.zrzhen.huozhiwang.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: 慧燕
 * @date: 2020/8/14 0:45
 * @copyright yanlongyun2020
 */
@Component
public class MallGoodsSearchInterceptor implements HandlerInterceptor {
    @Autowired
    CategoryService categoryService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<IndexCategoryVO> categories =categoryService.getCategoriesForIndex();
        request.setAttribute("categories",categories);
        return true;
    }
}
