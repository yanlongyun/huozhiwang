package com.zrzhen.huozhiwang.service.impl;

import com.zrzhen.huozhiwang.common.*;
import com.zrzhen.huozhiwang.controller.vo.*;
import com.zrzhen.huozhiwang.dao.GoodsMapper;
import com.zrzhen.huozhiwang.dao.OrderItemMapper;
import com.zrzhen.huozhiwang.dao.OrderMapper;
import com.zrzhen.huozhiwang.dao.ShoppingCartItemMapper;
import com.zrzhen.huozhiwang.entity.*;
import com.zrzhen.huozhiwang.service.OrderService;
import com.zrzhen.huozhiwang.util.BeanUtil;
import com.zrzhen.huozhiwang.util.NumberUtil;
import com.zrzhen.huozhiwang.util.PageUtil;
import com.zrzhen.huozhiwang.util.ResultGenerator;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author: 慧燕
 * @date: 2020/8/3 20:56
 * @copyright yanlongyun2020
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    ShoppingCartItemMapper shoppingCartItemMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    HttpSession httpSession;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderItemMapper orderItemMapper;


    @Override
    public String createOrder(Long userId, List<ShopCartShowVO> shopCartShowVO) {
        /*
         * 1.要判断购买的数量是否超过库存，如果超过库存则不需购买，没有超过，则库存要减，是否包含已下线的商品，包含的话返回错误。
         * 方法：根据goodsids查出所有商品的库存，for循环比较购买的库存。
         * 2.order的设置：先生成订单号，然后设置userID、sellingprice、总价
         * */
        List<Long> ids = shopCartShowVO.stream().map(ShopCartShowVO::getGoodsId).collect(Collectors.toList());
        List<GoodsInfo> goodsInfos = goodsMapper.selectByPrimaryKeys(ids);
        /*查询是否包含已下线的商品*/
        List<GoodsInfo> goodsInfos1 = goodsInfos.stream().filter(goodsInfo -> goodsInfo.getGoodsSellStatus() != Constants.SELL_STATUS_UP).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(goodsInfos1)) {
            MallException.fail(goodsInfos1.get(0).getGoodsName() + "已下架，无法生成订单");

        }
        /*创建map，来保存goodsID*/
        Map<Long, GoodsInfo> goodsInfoMap = goodsInfos.stream().collect(Collectors.toMap(GoodsInfo::getGoodsId, Function.identity(), (entity1, entity2) -> entity1));
        List<GoodsInfo> newGoodsInfo = new ArrayList<GoodsInfo>();
        /*根据map查询出stock*/
        for (ShopCartShowVO shopCartShowVO1 : shopCartShowVO) {
            if (goodsInfoMap.containsKey(shopCartShowVO1.getGoodsId())) {
                if (goodsInfoMap.get(shopCartShowVO1.getGoodsId()).getStockNum() < shopCartShowVO1.getGoodsCount()) {
                    MallException.fail(goodsInfoMap.get(shopCartShowVO1.getGoodsId()).getGoodsName() + "库存不足，无法生成订单");
                }
                /*如果够的话，就删除购物车的数据，更新库存*/
                int stockNum = goodsInfoMap.get(shopCartShowVO1.getGoodsId()).getStockNum() - shopCartShowVO1.getGoodsCount();
                /*把新的库存信息加入到数据库中*/
                int update = goodsMapper.updateCount(shopCartShowVO1.getGoodsId(), stockNum);
                if (update > 0) {
                    newGoodsInfo.add(goodsInfoMap.get(shopCartShowVO1.getGoodsId()));
                }
            }
        }
        /*获取总金额*/
        int totalPrice = 0;
        for (ShopCartShowVO shopCartShowVO1 : shopCartShowVO) {
            totalPrice += shopCartShowVO1.getSellingPrice() * shopCartShowVO1.getGoodsCount();
        }
        /*生成订单号*/
        Order order = new Order();
        String orderNo = NumberUtil.genOrderNo();
        order.setOrderNo(orderNo);
        /*订单状态有几种订单状态:0.待支付 1.已支付 2.配货完成 3:出库成功 4.交易成功 -1.手动关闭 -2.超时关闭 -3.商家关闭*/
        order.setUserId(userId);
        order.setTotalPrice(totalPrice);
        order.setOrderStatus((byte) 1);
        MallUserVO mallUserVO = (MallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        order.setUserAddress(mallUserVO.getAddress());
        order.setIsDeleted((byte) Constants.SELL_STATUS_UP);
        order.setOrderStatus((byte) OrderStatusEnum.ORDER_PRE_PAY.getOrderStatus());
        order.setPayType((byte) PayTypeEnum.NOT_PAY.getPayType());
        /*把order插入到数据库中*/
        int insert = orderMapper.insertOrder(order);
        /*清空购物车*/
        int delete = shoppingCartItemMapper.deleteShopCartItem(userId);
        if (insert < 1) {
            MallException.fail(ServiceResultEnum.DB_ERROR.getResult());
        }
        /*包装orderItem*/
        List<OrderItem> orderItems = new ArrayList<OrderItem>();
        for (ShopCartShowVO shopCartShowVO1 : shopCartShowVO) {
            OrderItem orderitem = new OrderItem();
            orderitem.setOrderId(order.getOrderId());
            orderitem.setGoodsCount(shopCartShowVO1.getGoodsCount());
            orderitem.setGoodsId(shopCartShowVO1.getGoodsId());
            orderitem.setGoodsCoverImg(shopCartShowVO1.getGoodsCoverImg());
            orderitem.setGoodsName(shopCartShowVO1.getGoodsName());
            orderitem.setSellingPrice(shopCartShowVO1.getSellingPrice());
            orderItems.add(orderitem);
        }
        /*数据库中插入orderItem*/
        int insertOrderItem = orderItemMapper.insertOrderItem(orderItems);
        if (insertOrderItem < 0) {
            MallException.fail(ServiceResultEnum.DB_ERROR.getResult());
        }
        return orderNo;

    }


    @Override
    public OrderVO getOrderDetailVOS(String orderNo) {
        /*通过orderNo查找orderID，然后查找list*/
        Order order = orderMapper.findOrderByOrderNo(orderNo);
        if (order != null) {
            Long orderId = order.getOrderId();
            OrderVO orderVO = new OrderVO();
            BeanUtil.copyProperties(order, orderVO);
            List<OrderItem> orderItemList = orderItemMapper.selectOrderItemsByOrderId(orderId);
            if (orderItemList != null) {
                List<OrderItemVO> orderItemVOS = BeanUtil.copyList(orderItemList, OrderItemVO.class);
                orderVO.setOrderStatus(order.getOrderStatus());
                orderVO.setOrderStatusString(OrderStatusEnum.getMallOrderStatusEnumByStatus(orderVO.getOrderStatus()).getName());
                orderVO.setPayTypeString(PayTypeEnum.getPayTypeEnumByType(orderVO.getPayType()).getName());
                orderVO.setOrderItemVOS(orderItemVOS);
                return orderVO;
            }
            return null;
        }
        return null;
    }

    @Override
    public Order getPayPage(String orderNo) {
        Order order = orderMapper.findOrderByOrderNo(orderNo);
        return order;
    }

    @Override
    public String setPayTypeAndOrderStatus(String orderNo, String payType,int orderStatus,int payStatus) {
        int payType1 = Integer.parseInt(payType);
        int update = orderMapper.updatePayTypeAndOrderStatus(orderNo, payType1,orderStatus,payStatus);
        if (update > 0) {
            return ServiceResultEnum.SUCCESS.getResult();
        } else {
            return ServiceResultEnum.FAIL.getResult();
        }
    }

    @Override
    public OrderPageResultVO getOrderPageResult(Long userId, int currPage) {
        /*找出orderNo，通过userID和isDeleted*/
        int limit = Constants.ORDER_SEARCH_PAGE_LIMIT;
        int start = (currPage-1)*limit;
        List<Order> orders = orderMapper.selectMyOrder(userId,start,limit);
        OrderPageResultVO orderPageResultVO = new OrderPageResultVO();
        List<OrderVO> list = new ArrayList<OrderVO>();
        if (!CollectionUtils.isEmpty(orders)) {
            for (Order order : orders) {
                OrderVO orderVO = this.getOrderDetailVOS(order.getOrderNo());
                list.add(orderVO);
            }
            orderPageResultVO.setList(list);
            int pageSize = Constants.ORDER_SEARCH_PAGE_LIMIT;
            /*查询totalPage*/
             Integer count = orderMapper.selectCountByUserId(userId);
            /*先向上转型为double，然后强转为int*/
            int totalPage =  (int) Math.ceil((double) count / pageSize);
            orderPageResultVO.setTotalPage(totalPage);
            orderPageResultVO.setCurrPage(currPage);
            return orderPageResultVO;
        } else {
            return new OrderPageResultVO();
        }
    }

    /**
    *删除订单操作
    * @param: [orderNo]
    * @createDate: 2020/8/5 21:52
    * @return: java.lang.String
    */
    @Transactional
    @Override
    public String cancelOrder(String orderNo) throws Exception {
        /*先获取order*/
        Order order = orderMapper.findOrderByOrderNo(orderNo);
        int deleteOrder = orderMapper.deleteOrder(orderNo);
        //int i = 1/0;
        int deleteOrderItem = orderItemMapper.deleteOrderItem(order.getOrderId());
        if(deleteOrder >0 && deleteOrderItem>0){
           return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.FAIL.getResult();
    }
}
