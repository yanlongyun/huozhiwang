package com.zrzhen.huozhiwang.dao;

import com.zrzhen.huozhiwang.entity.GoodsInfo;

import java.util.List;

/**
 * @author: 慧燕
 * @date: 2020/7/31 15:01
 * @copyright yanlongyun2020
 */
public interface GoodsMapper {
    List<GoodsInfo> selectByPrimaryKeys(List<Long> id);
    GoodsInfo selectGoodInfoById(Long id);

}
