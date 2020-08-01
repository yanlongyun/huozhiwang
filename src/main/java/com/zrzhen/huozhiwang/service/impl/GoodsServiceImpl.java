package com.zrzhen.huozhiwang.service.impl;

import com.zrzhen.huozhiwang.dao.GoodsMapper;
import com.zrzhen.huozhiwang.entity.GoodsInfo;
import com.zrzhen.huozhiwang.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 慧燕
 * @date: 2020/7/31 16:03
 * @copyright yanlongyun2020
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public GoodsInfo getGoodDetail(Long goodsId) {
        return goodsMapper.selectGoodInfoById(goodsId);
    }

    @Override
    public List<GoodsInfo> getGoodsDetail(Long goodCategoryId) {
       // return goodsMapper.selectGoodsInfoByCategoryId();
        return null;
    }
}
