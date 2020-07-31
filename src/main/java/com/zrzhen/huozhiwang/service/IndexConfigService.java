package com.zrzhen.huozhiwang.service;

import com.zrzhen.huozhiwang.controller.vo.IndexConfigGoodsVO;

import java.util.List;

public interface IndexConfigService {
    /**
     * 返回固定数量的首页配置商品对象(首页调用)
     *
     * @param number
     * @return
     */
    List<IndexConfigGoodsVO> getConfigGoodsesForIndex(int configType, int number);
}
