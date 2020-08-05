package com.zrzhen.huozhiwang.controller.vo;

import java.util.List;

/**
 * @author: 慧燕
 * @date: 2020/8/5 17:01
 * @copyright yanlongyun2020
 */
public class OrderPageResultVO {
    private int currPage;
    private int totalPage;
    private List<OrderVO> list;
    private List<OrderItemVO> orderItemVOS;

    @Override
    public String toString() {
        return "OrderPageResultVO{" +
                "currPage=" + currPage +
                ", totalPage=" + totalPage +
                ", list=" + list +
                ", orderItemVOS=" + orderItemVOS +
                '}';
    }

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

    public List<OrderVO> getList() {
        return list;
    }

    public void setList(List<OrderVO> list) {
        this.list = list;
    }

    public List<OrderItemVO> getOrderItemVOS() {
        return orderItemVOS;
    }

    public void setOrderItemVOS(List<OrderItemVO> orderItemVOS) {
        this.orderItemVOS = orderItemVOS;
    }
}
