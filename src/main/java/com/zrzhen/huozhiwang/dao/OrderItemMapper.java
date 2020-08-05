package com.zrzhen.huozhiwang.dao;

import com.zrzhen.huozhiwang.entity.OrderItem;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author: 慧燕
 * @date: 2020/8/4 17:33
 * @copyright yanlongyun2020
 */
public interface OrderItemMapper {
    int insertOrderItem(List<OrderItem> orderItems);

    List<OrderItem> selectOrderItemsByOrderId(Long orderId);

    Integer deleteOrderItem(Long orderId);
}
