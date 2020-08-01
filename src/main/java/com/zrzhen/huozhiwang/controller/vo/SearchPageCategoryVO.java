package com.zrzhen.huozhiwang.controller.vo;

import com.zrzhen.huozhiwang.entity.GoodsCategory;

import java.util.List;

/**
 * @author: 慧燕
 * @date: 2020/7/31 16:58
 * @copyright yanlongyun2020
 */
public class SearchPageCategoryVO {
    private String firstLevelCategoryName;
    private String secondLevelCategoryName;
    private String thirdLevelCategoryName;
    private List<GoodsCategory> thirdLevelCategoryList;
    private String currentCategoryName;

    public String getCurrentCategoryName() {
        return currentCategoryName;
    }

    public void setCurrentCategoryName(String currentCategoryName) {
        this.currentCategoryName = currentCategoryName;
    }

    public String getFirstLevelCategoryName() {
        return firstLevelCategoryName;
    }

    public void setFirstLevelCategoryName(String firstLevelCategoryName) {
        this.firstLevelCategoryName = firstLevelCategoryName;
    }

    public String getSecondLevelCategoryName() {
        return secondLevelCategoryName;
    }

    public void setSecondLevelCategoryName(String secondLevelCategoryName) {
        this.secondLevelCategoryName = secondLevelCategoryName;
    }

    public String getThirdLevelCategoryName() {
        return thirdLevelCategoryName;
    }

    public void setThirdLevelCategoryName(String thirdLevelCategoryName) {
        this.thirdLevelCategoryName = thirdLevelCategoryName;
    }

    public List<GoodsCategory> getThirdLevelCategoryList() {
        return thirdLevelCategoryList;
    }

    public void setThirdLevelCategoryList(List<GoodsCategory> thirdLevelCategoryList) {
        this.thirdLevelCategoryList = thirdLevelCategoryList;
    }
}

