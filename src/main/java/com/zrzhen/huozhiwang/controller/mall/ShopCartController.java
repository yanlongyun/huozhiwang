package com.zrzhen.huozhiwang.controller.mall;

import com.zrzhen.huozhiwang.common.Constants;
import com.zrzhen.huozhiwang.common.ServiceResultEnum;
import com.zrzhen.huozhiwang.controller.vo.MallUserVO;
import com.zrzhen.huozhiwang.controller.vo.ShopCartShowVO;
import com.zrzhen.huozhiwang.entity.GoodsInfo;
import com.zrzhen.huozhiwang.entity.MallUser;
import com.zrzhen.huozhiwang.entity.ShoppingCartItem;
import com.zrzhen.huozhiwang.service.ShopCartService;
import com.zrzhen.huozhiwang.util.Result;
import com.zrzhen.huozhiwang.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: 慧燕
 * @date: 2020/8/2 15:01
 * @copyright yanlongyun2020
 */
@Controller
public class ShopCartController {
    @Autowired
    ShopCartService shopCartService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;
    /*跳转页面*/
    @GetMapping("/shop-cart")
    public String cartPage(HttpSession httpSession){
            MallUserVO mallUserVO = (MallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
            Long userId = mallUserVO.getUserId();
            List<ShopCartShowVO> shopCartShowVO = shopCartService.getCart(userId);
            request.setAttribute("myShoppingCartItems",shopCartShowVO);
            /*计算商品金额*/
            int priceTotal = 0;
            int itemsTotal = 0;
            for(ShopCartShowVO item:shopCartShowVO){
                priceTotal += item.getGoodsCount()*item.getSellingPrice();
                itemsTotal += item.getGoodsCount();
            }
            request.setAttribute("priceTotal",priceTotal);
            request.setAttribute("itemsTotal",itemsTotal);
            return "/mall/cart";
        }



    /*
    * 1.将商品加入到购物车
    * 2.将商品加入到购物车并跳转到结算页面
    * */
    @PostMapping("/shop-cart")
    @ResponseBody
    public Result saveToCart(@RequestBody ShoppingCartItem cartItem, HttpSession httpSession){
        /*判断goodsId是否有，如果有就执行方法*/
        if(cartItem.getGoodsId()!=null){
            MallUserVO mallUserVO = (MallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
            cartItem.setUserId(mallUserVO.getUserId());
            cartItem.setIsDeleted((byte) Constants.SELL_STATUS_UP);
            String result = shopCartService.saveToCart(cartItem);
            if(result.equals(ServiceResultEnum.SUCCESS.getResult())) {
                return ResultGenerator.genSuccessResult(ServiceResultEnum.SUCCESS.getResult());
            }else {
                return ResultGenerator.genFailResult(ServiceResultEnum.FAIL.getResult());
            }
        }
        return ResultGenerator.genFailResult(ServiceResultEnum.FAIL.getResult());
    }
    @PutMapping("/shop-cart")
    @ResponseBody
    public Result updateItem(@RequestBody ShoppingCartItem cartItem){
        String result = shopCartService.updateCart(cartItem.getCartItemId(),cartItem.getGoodsCount());
        if(result.equals(ServiceResultEnum.SUCCESS.getResult())) {
            return ResultGenerator.genSuccessResult(ServiceResultEnum.SUCCESS.getResult());
        }else {
            return ResultGenerator.genFailResult(ServiceResultEnum.FAIL.getResult());
        }
    }

}

