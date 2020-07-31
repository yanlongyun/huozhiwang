package com.zrzhen.huozhiwang.service.impl;

import com.zrzhen.huozhiwang.common.ServiceResultEnum;
import com.zrzhen.huozhiwang.controller.vo.IndexCarouselVO;
import com.zrzhen.huozhiwang.dao.CarouselMapper;
import com.zrzhen.huozhiwang.entity.MallCarousel;
import com.zrzhen.huozhiwang.service.CarouselService;
import com.zrzhen.huozhiwang.util.BeanUtil;
import com.zrzhen.huozhiwang.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {
    @Autowired
    CarouselMapper carouselMapper;
    @Override
    public List<IndexCarouselVO> getCarouselsForIndex(int number) {
        List<IndexCarouselVO> list = new ArrayList<>(number);
        List<MallCarousel> list1 = carouselMapper.findCarouselByNum(number);
        if(CollectionUtils.isEmpty(list1)){
            list = BeanUtil.copyList(list1,IndexCarouselVO.class);
        }
        return list;
    }
}
