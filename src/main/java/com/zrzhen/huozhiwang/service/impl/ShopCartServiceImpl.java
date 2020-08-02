package com.zrzhen.huozhiwang.service.impl;

import com.zrzhen.huozhiwang.common.ServiceResultEnum;
import com.zrzhen.huozhiwang.controller.vo.ShopCartShowVO;
import com.zrzhen.huozhiwang.dao.GoodsMapper;
import com.zrzhen.huozhiwang.dao.ShoppingCartItemMapper;
import com.zrzhen.huozhiwang.entity.GoodsInfo;
import com.zrzhen.huozhiwang.entity.ShoppingCartItem;
import com.zrzhen.huozhiwang.service.ShopCartService;
import com.zrzhen.huozhiwang.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: 慧燕
 * @date: 2020/8/2 15:33
 * @copyright yanlongyun2020
 */
@Service
public class ShopCartServiceImpl implements ShopCartService {
    @Autowired
    ShoppingCartItemMapper shoppingCartItemMapper;
    @Autowired
    GoodsMapper goodsMapper;

    @Override

    public String saveToCart(ShoppingCartItem shoppingCartItem) {
        /*先根据goodsID和userID查出购物车中是否存在该商品*/
        ShoppingCartItem item= shoppingCartItemMapper.selectItemByGoodsIdAndUserId(shoppingCartItem.getGoodsId(),shoppingCartItem.getUserId());
        /*如果已保存的商品中包含此商品，商品的数量加1*/
        if (item != null){
            int goodsCount = item.getGoodsCount()+shoppingCartItem.getGoodsCount();
            int count = shoppingCartItemMapper.updateShoppingCartGoodsCount(goodsCount,item.getCartItemId());
            if(count>0){
                return ServiceResultEnum.SUCCESS.getResult();
            }
            return ServiceResultEnum.FAIL.getResult();
        } else {
            int insert = shoppingCartItemMapper.saveGoods(shoppingCartItem);
            if (insert > 0) {
                return ServiceResultEnum.SUCCESS.getResult();
            }
            return ServiceResultEnum.FAIL.getResult();
        }
    }

    @Override
    public String updateCart(Long cartItemId, int goodsCount) {
        int update = shoppingCartItemMapper.updateShoppingCartGoodsCount(goodsCount,cartItemId);
        if(update>0){
            return ServiceResultEnum.SUCCESS.getResult();
        }
        return ServiceResultEnum.FAIL.getResult();
    }

    @Override
    public List<ShopCartShowVO> getCart(Long userId) {
            List<ShoppingCartItem> shoppingCartItem1 = shoppingCartItemMapper.selectCartItemByUserId(userId);
            List<ShopCartShowVO> shopCartShowVO = null;
            shopCartShowVO = BeanUtil.copyList(shoppingCartItem1, ShopCartShowVO.class);
            for (ShopCartShowVO cartItem : shopCartShowVO) {
                String goodsName = goodsMapper.selectGoodInfoById(cartItem.getGoodsId()).getGoodsName();
                // 字符串过长导致文字超出的问题
                if (goodsName.length() > 28) {
                    goodsName = goodsName.substring(0, 28) + "...";
                }
                cartItem.setGoodsName(goodsName);
                cartItem.setSellingPrice(goodsMapper.selectGoodInfoById(cartItem.getGoodsId()).getSellingPrice());
                cartItem.setGoodsCoverImg(goodsMapper.selectGoodInfoById(cartItem.getGoodsId()).getGoodsCoverImg());
            }
            return shopCartShowVO;
    }
}
