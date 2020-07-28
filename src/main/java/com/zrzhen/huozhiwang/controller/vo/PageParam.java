package com.zrzhen.huozhiwang.controller.vo;

public class PageParam<T> {
    private static final long serialVersionUID = 1L;

    private Integer pageNo;//当前页
    private Integer pageSize;//一页包含多少条记录

    private T t;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}