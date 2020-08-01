package com.zrzhen.huozhiwang.service.impl;

import com.zrzhen.huozhiwang.common.CategoryLevelEnum;
import com.zrzhen.huozhiwang.common.Constants;
import com.zrzhen.huozhiwang.controller.vo.*;
import com.zrzhen.huozhiwang.dao.GoodsCategoryMapper;
import com.zrzhen.huozhiwang.dao.GoodsMapper;
import com.zrzhen.huozhiwang.dao.IndexConfigMapper;
import com.zrzhen.huozhiwang.entity.GoodsCategory;
import com.zrzhen.huozhiwang.entity.GoodsInfo;
import com.zrzhen.huozhiwang.entity.IndexConfig;
import com.zrzhen.huozhiwang.service.SearchService;
import com.zrzhen.huozhiwang.util.BeanUtil;
import com.zrzhen.huozhiwang.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: 慧燕
 * @date: 2020/8/1 12:50
 * @copyright yanlongyun2020
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    GoodsCategoryMapper goodsCategoryMapper;
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public SearchPageCategoryVO getPageCategory(Map<String, Object> param) {
        GoodsCategory goodsCategory = (GoodsCategory) param.get("goodsCategory");
        GoodsCategory secondCategory = goodsCategoryMapper.selectCategoryById(goodsCategory.getParentId());
        /*获取二级分类的名字*/
        String secondLevelCategoryName = secondCategory.getCategoryName();
        /*获取三级分类的名字*/
        String thirdLevelCategoryName = goodsCategory.getCategoryName();
        /*创建一个list，保存ID*/
        List<Long> list= new ArrayList<>();
        list.add(Long.valueOf(param.get("goodsCategoryId")+""));
        List<GoodsCategory> goodsCategories = goodsCategoryMapper.selectByLevelAndParentIdsAndNumber(list, CategoryLevelEnum.LEVEL_THREE.getLevel(), Constants.SEARCH_CATEGORY_NUMBER);
        /*封装数据，返回给前端*/
        SearchPageCategoryVO searchPageCategoryVO= new SearchPageCategoryVO();
        searchPageCategoryVO.setThirdLevelCategoryList(goodsCategories);
        searchPageCategoryVO.setSecondLevelCategoryName(secondLevelCategoryName);
        searchPageCategoryVO.setCurrentCategoryName(goodsCategory.getCategoryName());
        searchPageCategoryVO.setThirdLevelCategoryName(thirdLevelCategoryName);
        return searchPageCategoryVO;
    }

    @Override
    public SearchPageShowVO getPageShow(PageUtil param) {
        /*根据封装好的信息进行查询*/
        List<GoodsInfo> goodsList=goodsMapper.getPageResult(param);
        int count = goodsMapper.getTotalPage(param);
        List<SearchGoodsVO> searchGoodsVOS = new ArrayList<>();
        if (!CollectionUtils.isEmpty(goodsList)) {
            searchGoodsVOS = BeanUtil.copyList(goodsList, SearchGoodsVO.class);
            for (SearchGoodsVO searchGoodsVO : searchGoodsVOS) {
                String goodsName = searchGoodsVO.getGoodsName();
                String goodsIntro = searchGoodsVO.getGoodsIntro();
                // 字符串过长导致文字超出的问题
                if (goodsName.length() > 28) {
                    goodsName = goodsName.substring(0, 28) + "...";
                    searchGoodsVO.setGoodsName(goodsName);
                }
                if (goodsIntro.length() > 30) {
                    goodsIntro = goodsIntro.substring(0, 30) + "...";
                    searchGoodsVO.setGoodsIntro(goodsIntro);
                }
            }
        }
        SearchPageShowVO pageResult = new SearchPageShowVO(searchGoodsVOS, count, param.getLimit(), param.getPage());
        return pageResult;
    }
}
