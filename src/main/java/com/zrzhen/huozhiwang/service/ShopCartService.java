package com.zrzhen.huozhiwang.service;

import com.zrzhen.huozhiwang.controller.vo.ShopCartShowVO;
import com.zrzhen.huozhiwang.entity.ShoppingCartItem;

import java.util.List;

/**
 * @author: 慧燕
 * @date: 2020/8/2 15:31
 * @copyright yanlongyun2020
 */
public interface ShopCartService {
    /**/
    List<ShopCartShowVO> getCart(Long userId);

    String saveToCart(ShoppingCartItem shoppingCartItem);

    String updateCart(Long cartItemId,int goodsCount);
}
