package com.zrzhen.huozhiwang.dao;

import com.zrzhen.huozhiwang.entity.IndexConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface IndexConfigMapper {
//    @Param是MyBatis所提供的(org.apache.ibatis.annotations.Param)，作为Dao层的注解，作用是用于传递参数，从而可以与SQL中的的字段名相对应，一般在2=<参数数<=5时使用最佳。
    List<IndexConfig> findIndexConfigsByTypeAndNum(@Param("configType") int configType, @Param("number") int number);
}
