package com.zrzhen.huozhiwang.controller.vo;

import java.util.List;

/**
 * @author: 慧燕
 * @date: 2020/8/1 13:57
 * @copyright yanlongyun2020
 */
public class SearchPageShowVO {
    /*查询到的页面数据*/
    /*当前页码*/
    private int currPage;
    /*总页数*/
    private int totalPage;
    /*每页数据*/
    private int limit;
    /*页面数据*/
    private List<SearchGoodsVO> list;

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<SearchGoodsVO> getList() {
        return list;
    }

    public void setList(List<SearchGoodsVO> list) {
        this.list = list;
    }

    public SearchPageShowVO(List<SearchGoodsVO> searchGoodsVOS,int currPage,int totalPage, int limit){
        this.list = searchGoodsVOS;
        this.currPage = currPage;
        this.totalPage=totalPage;
        this.limit=limit;

    }
    public SearchPageShowVO(){};
}
