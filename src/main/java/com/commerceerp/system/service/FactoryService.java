package com.commerceerp.system.service;

import com.commerceerp.system.entity.GoodInfo;
import com.commerceerp.system.entity.GoodStorehouseInfo;
import com.commerceerp.system.entity.StorehouseInfo;

import java.util.List;
import java.util.Map;

public interface FactoryService {

    List<Map> getAllSystemGood(String goodSku,String goodSystemChname,String goodClass,String goodSellType,Integer goodCreateTimePX,Integer goodUpdTimePX,
                               Integer goodPurchasePricePX);

    Integer saveNewSystemGood(GoodInfo goodInfo);

    List<Map> getAllGoodStorehouseInfo(String gsStorehouseId,String goodSystemChname,String goodSystemSku,
                                       String goodSellType,Integer gsCreateTimePX,Integer gsUpdTimePX,
                                       Integer gsGoodSkuPX,Integer gsAllNumberPX);

    Integer updGoodStorehouseInfo(GoodStorehouseInfo goodStorehouseInfo);

    List<StorehouseInfo> getAllStorehouseInfo();

    Integer saveNewStorehouseInfo(StorehouseInfo storehouseInfo);

}
