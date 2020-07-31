package com.zrzhen.huozhiwang.controller.mall;

import com.zrzhen.huozhiwang.controller.vo.GoodsDetailVO;
import com.zrzhen.huozhiwang.entity.GoodsInfo;
import com.zrzhen.huozhiwang.service.GoodsService;
import com.zrzhen.huozhiwang.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author: 慧燕
 * @date: 2020/7/31 15:56
 * @copyright yanlongyun2020
 */
@Controller
public class GoodsController {
    @Autowired
    GoodsService goodsService;

   /**
   *点击商品图片，跳转到商品详情页，
   * @param: [goodsId, request]
   * @createDate: 2020/7/31 16:00
   * @return: java.lang.String
   */
    @GetMapping("goods/detail/{goodsId}")
    public String goodDetail(@PathVariable("goodsId") Long goodsId, HttpServletRequest request){
//        判断ID大小
        if(goodsId<1){
            return "error/error_5xx";
        }
//        判断是否存在该商品
        GoodsInfo goodsInfo = goodsService.getGoodDetail(goodsId);
        GoodsDetailVO goodsDetailVO = new GoodsDetailVO();
        BeanUtil.copyProperties(goodsInfo,goodsDetailVO);
        request.setAttribute("goodsDetail",goodsDetailVO);
        return "mall/detail";
    }
    /**
    *查找商品分为两种方法：1.搜索框中search?keyword=%E6%89%8B%E6%9C%BA
     * 2.从分类中搜索http://127.0.0.1:28089/search?keyword=&goodsCategoryId=45&orderBy=new
    * @param: [goodsCategoryId, request]
    * @createDate: 2020/7/31 22:54
    * @return: java.lang.String
    */
   /* @GetMapping("/search")
    public String searchPage(@RequestParam Map<String,Object> params, HttpServletRequest request){
        *//*可以看到链接可能传递过来的信息较多
        * 1.只有goodsCategoryId的情况
        * 2.只有keyword
        * 3.有三个：因为点击了orderBy按钮，有三个参数
        * 4.点击了分页按钮多了page：search?keyword=手机&page=2&goodsCategoryId=&orderBy=new
        * 所以：使用一个map来接受，返回的时候返回多个信息
        * *//*

    }*/
}
