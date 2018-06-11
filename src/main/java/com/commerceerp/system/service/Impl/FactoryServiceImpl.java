package com.commerceerp.system.service.Impl;

import com.commerceerp.system.entity.GoodInfo;
import com.commerceerp.system.entity.GoodStorehouseInfo;
import com.commerceerp.system.entity.StorehouseInfo;
import com.commerceerp.system.mapper.GoodMapper;
import com.commerceerp.system.mapper.GoodStorehouseMapper;
import com.commerceerp.system.mapper.StorehouseMapper;
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

    @Autowired
    private GoodStorehouseMapper goodStorehouseMapper;

    @Autowired
    private StorehouseMapper storehouseMapper;

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


    /**
     * 获取仓库清单列表
     */
    @Override
    public List<Map> getAllGoodStorehouseInfo(String gsStorehouseId, String goodSystemChname, String goodSystemSku, String goodSellType, Integer gsCreateTimePX, Integer gsUpdTimePX, Integer gsGoodSkuPX, Integer gsAllNumberPX) {

        List<Map> gsList = goodStorehouseMapper.getAllGoodStorehouseInfo(gsStorehouseId,  goodSystemChname,  goodSystemSku,  goodSellType,  gsCreateTimePX,  gsUpdTimePX,  gsGoodSkuPX,  gsAllNumberPX);

        return gsList;
    }

    /**
     * 更新商品的仓库清单
     */
    @Override
    public Integer updGoodStorehouseInfo(GoodStorehouseInfo goodStorehouseInfo) {

        goodStorehouseInfo.setGsUpdTime(DateUtil.getYMDHMSDate());
        goodStorehouseInfo.setGsId(StringUtils.getUUID());

        Integer success = goodStorehouseMapper.updGoodStorehouseInfo(goodStorehouseInfo);

        return success;
    }

    /**
     * 获取仓库信息列表
     */
    @Override
    public List<StorehouseInfo> getAllStorehouseInfo() {

        List<StorehouseInfo> storehouseinfos = storehouseMapper.getAllStorehouseInfo();

        return storehouseinfos;
    }

    /**
     * 新增仓库信息
     * @param storehouseInfo
     * @return
     */
    @Override
    public Integer saveNewStorehouseInfo(StorehouseInfo storehouseInfo) {

        storehouseInfo.setStorehouseId(StringUtils.getUUID());
        storehouseInfo.setStorehouseIsdefault("0");

        Integer success = storehouseMapper.saveNewStorehouseInfo(storehouseInfo);

        return success;
    }
}
