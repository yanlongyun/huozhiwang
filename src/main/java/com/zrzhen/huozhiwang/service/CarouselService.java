package com.zrzhen.huozhiwang.service;

import com.zrzhen.huozhiwang.controller.vo.IndexCarouselVO;

import java.util.List;

public interface CarouselService {
    List<IndexCarouselVO> getCarouselsForIndex(int number);
}
