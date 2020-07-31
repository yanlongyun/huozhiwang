package com.zrzhen.huozhiwang.service;

import com.zrzhen.huozhiwang.entity.GoodsInfo;

import java.util.List;

/**
 * @author: 慧燕
 * @date: 2020/7/31 16:04
 * @copyright yanlongyun2020
 */
public interface GoodsService {

    GoodsInfo getGoodDetail(Long goodsId);

    List<GoodsInfo> getGoodsDetail(Long goodCategoryId);
}
