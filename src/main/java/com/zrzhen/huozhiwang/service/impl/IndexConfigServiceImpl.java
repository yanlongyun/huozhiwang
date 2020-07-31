package com.zrzhen.huozhiwang.service.impl;

import com.zrzhen.huozhiwang.controller.vo.IndexConfigGoodsVO;
import com.zrzhen.huozhiwang.dao.GoodsMapper;
import com.zrzhen.huozhiwang.dao.IndexConfigMapper;
import com.zrzhen.huozhiwang.entity.GoodsInfo;
import com.zrzhen.huozhiwang.entity.IndexConfig;
import com.zrzhen.huozhiwang.service.IndexConfigService;
import com.zrzhen.huozhiwang.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import sun.management.snmp.jvmmib.EnumJvmClassesVerboseLevel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndexConfigServiceImpl implements IndexConfigService {
    @Autowired
    IndexConfigMapper indexConfigMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Override
    public List<IndexConfigGoodsVO> getConfigGoodsesForIndex(int configType, int number) {
        List<IndexConfigGoodsVO> indexConfigGoodsVOS = new ArrayList<>(number);
        /*先查询出所有配置信息*/
        List<IndexConfig> configGoods = indexConfigMapper.findIndexConfigsByTypeAndNum(configType,number);
        if(!CollectionUtils.isEmpty(configGoods)){
            List<Long> ids = configGoods.stream().map(IndexConfig::getGoodsId).collect(Collectors.toList());
            /*根据id查出商品信息*/
            List<GoodsInfo> goodsInfos = goodsMapper.selectByPrimaryKeys(ids);
            indexConfigGoodsVOS = BeanUtil.copyList(goodsInfos,IndexConfigGoodsVO.class);
            for (IndexConfigGoodsVO indexConfigGoodsVO : indexConfigGoodsVOS) {
                String goodsName = indexConfigGoodsVO.getGoodsName();
                String goodsIntro = indexConfigGoodsVO.getGoodsIntro();
                // 字符串过长导致文字超出的问题
                if (goodsName.length() > 30) {
                    goodsName = goodsName.substring(0, 30) + "...";
                    indexConfigGoodsVO.setGoodsName(goodsName);
                }
                if (goodsIntro.length() > 22) {
                    goodsIntro = goodsIntro.substring(0, 22) + "...";
                    indexConfigGoodsVO.setGoodsIntro(goodsIntro);
                }
            }
        }
        return indexConfigGoodsVOS;

    }
}
