package com.zrzhen.huozhiwang.controller.mall;

import com.zrzhen.huozhiwang.common.*;
import com.zrzhen.huozhiwang.controller.vo.MallUserVO;
import com.zrzhen.huozhiwang.controller.vo.OrderPageResultVO;
import com.zrzhen.huozhiwang.controller.vo.OrderVO;
import com.zrzhen.huozhiwang.controller.vo.ShopCartShowVO;
import com.zrzhen.huozhiwang.entity.Order;
import com.zrzhen.huozhiwang.service.OrderService;
import com.zrzhen.huozhiwang.service.ShopCartService;
import com.zrzhen.huozhiwang.util.Result;
import com.zrzhen.huozhiwang.util.ResultGenerator;
import com.zrzhen.huozhiwang.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author: 慧燕
 * @date: 2020/8/3 20:48
 * @copyright yanlongyun2020
 */
@Controller
public class OrderController {
    @Autowired
    ShopCartService shopCartService;
    @Autowired
    OrderService orderService;
    @Autowired
    HttpSession httpSession;
    @Autowired
    HttpServletRequest request;
    @Autowired
    HttpServletResponse response;

    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession httpSession) {
        /*把该用户的购物车里面的信息写入order表*/
        MallUserVO mallUserVO = (MallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        Long userId = mallUserVO.getUserId();
        if (userId != null) {
            /*根据userId来获取shopcart里的内容*/
            List<ShopCartShowVO> shopCartShowVO = shopCartService.getCart(userId);
            /*获取orderId*/
            String orderNo = orderService.createOrder(userId, shopCartShowVO);
            return "redirect:/orders/" + orderNo;
        } else {
            return "error/error_5xx";
        }
    }

    @GetMapping("/orders/{orderNo}")
    public String getOrderPage(@PathVariable("orderNo") String orderNo, HttpServletRequest request, HttpServletResponse response) {
        /*根据orderNo查询orderitem，封装为orderVO对象*/
        if (orderNo != null) {
            OrderVO orderDetailVOS = orderService.getOrderDetailVOS(orderNo);
            /*封装orderNo，orderStatus，order orderItemVOS*/
            request.setAttribute("orderDetailVO", orderDetailVOS);
        } else {
            request.setAttribute("orderDetailVO", new OrderVO());
        }
        return "mall/order-detail";
    }

    @GetMapping("/selectPayType")
    public String selectPayType(@RequestParam("orderNo") String orderNo, HttpServletRequest request) {
        /*跳转到支付页面*/
        /*查出totalPrice*/
        Order order = orderService.getPayPage(orderNo);
        if (order != null) {
            request.setAttribute("totalPrice", order.getTotalPrice());
            request.setAttribute("orderNo", orderNo);
            return "mall/pay-select";
        }
        return "error/error_5xx";
    }

    @GetMapping("/payPage")
    public String payPage(@RequestParam("orderNo") String orderNo, @RequestParam("payType") String payType) {
        /*查询订单金额*/
        String payTypeName = "";
        if (!StringUtil.isEmpty(orderNo) && !StringUtil.isEmpty(payType)) {
            int totalPrice = orderService.getPayPage(orderNo).getTotalPrice();
            request.setAttribute("orderNo", orderNo);
            request.setAttribute("totalPrice", totalPrice);
            payTypeName = PayTypeEnum.getPayTypeEnumByType(Integer.parseInt(payType)).getName();
        }
        String[] payTypeList = {"支付宝", "微信支付", "无", "ERROR"};
        if (payTypeName.equals(payTypeList[0])) {
            return "error/error_5xx";
        }
        if (payTypeName.equals(payTypeList[1])) {
            return "/mall/wxpay";
        }
        if (payTypeName.equals(payTypeList[2])) {
            return "error/error_5xx";
        }
        if (payTypeName.equals(payTypeList[4])) {
            return "error/error_5xx";
        }
        return "error/error_5xx";
    }


    /**
     * 展示订单列表
     *
     * @param: []
     * @createDate: 2020/8/5 16:54
     * @return: java.lang.String
     */
    @GetMapping("/orders")
    public String myOrders(Integer page) {
        MallUserVO mallUserVO = (MallUserVO) httpSession.getAttribute(Constants.MALL_USER_SESSION_KEY);
        Long userId = mallUserVO.getUserId();
        int currPage = 1;
        if (page != null) {
            currPage = page;
        }
        if (userId != null) {
            OrderPageResultVO orderPageResultVO = orderService.getOrderPageResult(userId, currPage);
            request.setAttribute("orderPageResult", orderPageResultVO);
        }
        return "mall/my-orders";
    }

    @GetMapping("/paySuccess")
    @ResponseBody
    public Result paySuccess(@RequestParam("payType") String payType, @RequestParam("orderNo") String orderNo) {
        /*更新type*/
        int orderStatus = OrderStatusEnum.OREDER_PAID.getOrderStatus();
        int payStatus = PayStatusEnum.PAY_SUCCESS.getPayStatus();
        String result = orderService.setPayTypeAndOrderStatus(orderNo, payType, orderStatus, payStatus);
        if (result.equals(ServiceResultEnum.SUCCESS.getResult())) {
            return ResultGenerator.genSuccessResult(ServiceResultEnum.SUCCESS.getResult());
        }
        return ResultGenerator.genFailResult(ServiceResultEnum.FAIL.getResult());
    }

    /**
     * 删除订单
     *
     * @param: [orderNO]
     * @createDate: 2020/8/5 22:21
     * @return: com.zrzhen.huozhiwang.util.Result
     */
    @PutMapping("/orders/{orderNo}/cancel")
    @ResponseBody
    public Result cancelOrder(@PathVariable("orderNo") String orderNo) throws Exception {
        String result = orderService.cancelOrder(orderNo);
        if (result == ServiceResultEnum.SUCCESS.getResult()) {
            return ResultGenerator.genSuccessResult();
        }
        else {
        return ResultGenerator.genFailResult(ServiceResultEnum.FAIL.getResult());
        }
    }
}
