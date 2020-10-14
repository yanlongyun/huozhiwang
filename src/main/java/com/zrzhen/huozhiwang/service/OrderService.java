package com.zrzhen.huozhiwang.service;

import com.zrzhen.huozhiwang.controller.vo.OrderPageResultVO;
import com.zrzhen.huozhiwang.controller.vo.OrderVO;
import com.zrzhen.huozhiwang.controller.vo.ShopCartShowVO;
import com.zrzhen.huozhiwang.entity.Order;
import com.zrzhen.huozhiwang.entity.OrderItem;

import java.util.List;
import java.util.Map;

/**
 * @author: 慧燕
 * @date: 2020/8/3 20:54
 * @copyright yanlongyun2020
 */
public interface OrderService {

    String createOrder(Long userId, List<ShopCartShowVO> shopCartShowVO);

    OrderVO getOrderDetailVOS(String orderNo);

    Order getPayPage(String orderNo);

    String setPayTypeAndOrderStatus(String orderNo,String payType,int orderStatus,int payStatus);

    OrderPageResultVO getOrderPageResult(Long userId, int currPage);

    String cancelOrder(String orderNo) throws Exception;
}
