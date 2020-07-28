package com.zrzhen.huozhiwang.service;


import com.zrzhen.huozhiwang.controller.vo.IndexCategoryVO;

import java.util.List;

public interface CategoryService {
    /*返回分类数据
    *
    * 首页调用
    *
    * */
    List<IndexCategoryVO> getCategoriesForIndex();
}
