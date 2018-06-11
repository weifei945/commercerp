package com.commerceerp.system.service.Impl;

import com.commerceerp.system.entity.GoodInfo;
import com.commerceerp.system.mapper.GoodMapper;
import com.commerceerp.system.service.FactoryService;
import com.commerceerp.system.util.DateUtil;
import com.commerceerp.system.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FactoryServiceImpl implements FactoryService {

    @Autowired
    private GoodMapper goodMapper;

    /**
     * 获取所有商品列表
     */
    @Override
    public List<Map> getAllSystemGood(String goodSku, String goodSystemChname, String goodClass, String goodSellType, Integer goodCreateTimePX, Integer goodUpdTimePX, Integer goodPurchasePricePX) {

        List<Map> goodsList = goodMapper.getAllSystemGood(goodSku,goodSystemChname,goodClass,goodSellType,goodCreateTimePX,goodUpdTimePX,goodPurchasePricePX);

        return goodsList;
    }

    /**
     *添加系统商品信息
     */
    @Override
    public Integer saveNewSystemGood(GoodInfo goodInfo) {

        goodInfo.setGoodCreateTime(DateUtil.getYMDHMSDate());
        goodInfo.setGoodId(StringUtils.getUUID());

        Integer success = goodMapper.saveNewSystemGood(goodInfo);

        return success;
    }
}
