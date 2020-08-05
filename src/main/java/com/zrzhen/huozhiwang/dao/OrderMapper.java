package com.zrzhen.huozhiwang.dao;

import com.zrzhen.huozhiwang.entity.Order;
import com.zrzhen.huozhiwang.entity.OrderItem;
import com.zrzhen.huozhiwang.util.PageUtil;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author: 慧燕
 * @date: 2020/8/4 11:54
 * @copyright yanlongyun2020
 */
public interface OrderMapper {

    int insertOrder(Order order);

    Order findOrderByOrderNo(String orderNo);

    int updatePayTypeAndOrderStatus(String orderNo,int payType,int orderStatus,int payStatus);

    List<Order> selectMyOrder(Long userId,int start,int limit);

    Integer selectCountByUserId(Long userId);

    Integer deleteOrder(String orderNo);

}
