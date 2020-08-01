package com.zrzhen.huozhiwang.dao;

import com.zrzhen.huozhiwang.entity.GoodsInfo;
import com.zrzhen.huozhiwang.util.PageUtil;

import java.util.List;
import java.util.Map;

/**
 * @author: 慧燕
 * @date: 2020/7/31 15:01
 * @copyright yanlongyun2020
 */
public interface GoodsMapper {
    List<GoodsInfo> selectByPrimaryKeys(List<Long> id);
    GoodsInfo selectGoodInfoById(Long id);
   // List<GoodsInfo> selectByCategoryId(List<Long> id);
    int getTotalPage(PageUtil param);

    List<GoodsInfo> getPageResult(PageUtil param);
}
