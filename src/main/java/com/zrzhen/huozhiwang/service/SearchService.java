package com.zrzhen.huozhiwang.service;

import com.zrzhen.huozhiwang.controller.vo.SearchPageCategoryVO;
import com.zrzhen.huozhiwang.controller.vo.SearchPageShowVO;
import com.zrzhen.huozhiwang.util.PageUtil;

import java.util.Map;

/**
 * @author: 慧燕
 * @date: 2020/8/1 12:47
 * @copyright yanlongyun2020
 */
public interface SearchService {
    /*查询页面分类数据*/
    SearchPageCategoryVO getPageCategory(Map<String,Object> param);
    /*查询页面内容*/
    SearchPageShowVO getPageShow(PageUtil param);
}
