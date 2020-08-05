package com.zrzhen.huozhiwang.dao;

import com.zrzhen.huozhiwang.entity.ShoppingCartItem;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author: 慧燕
 * @date: 2020/7/30 20:00
 * @copyright yanlongyun2020
 */
public interface ShoppingCartItemMapper {

    Integer selectCountByUserId(Long userId);

    int saveGoods(ShoppingCartItem shoppingCartItem);

    List<ShoppingCartItem> selectCartItemByUserId(Long userId);

    ShoppingCartItem selectItemByGoodsIdAndUserId(@RequestParam("goodsId") Long goodsId,@RequestParam("userId") Long userId);

    int updateShoppingCartGoodsCount(@RequestParam("goodsCount") int goodsCount, @RequestParam("cartItemId") Long cartItemId);

    int deleteShopCartItem(Long userId);
}
