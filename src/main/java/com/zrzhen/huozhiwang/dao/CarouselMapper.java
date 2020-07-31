package com.zrzhen.huozhiwang.dao;

import com.zrzhen.huozhiwang.entity.MallCarousel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarouselMapper {

    List<MallCarousel> findCarouselByNum(@Param("number") int number);
}
