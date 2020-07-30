package com.zrzhen.huozhiwang.dao;

/**
 * @author: 慧燕
 * @date: 2020/7/30 20:00
 * @copyright yanlongyun2020
 */
public interface ShoppingCartItemMapper {

    int selectCountByUserId(Long userId);
}
