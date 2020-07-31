package com.zrzhen.huozhiwang.controller.vo;

import java.util.List;

/**
 * @author: 慧燕
 * @date: 2020/7/31 16:58
 * @copyright yanlongyun2020
 */
public class SearchPageCategoryVO {
    /**
    *三级分类信息
    * @param:
    * @createDate: 2020/7/31 20:08
    * @return:
    */
    private GoodsDetailVO goodsDetailVO;
    /**
    *三级分类查出上一级信息。
    * @param:
    * @createDate: 2020/7/31 20:09
    * @return:
    */
    private List<SecondLevelCategoryVO> secondLevelCategoryVOS;
}
