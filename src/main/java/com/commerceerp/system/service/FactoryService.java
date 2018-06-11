package com.commerceerp.system.service;

import com.commerceerp.system.entity.GoodInfo;

import java.util.List;
import java.util.Map;

public interface FactoryService {

    List<Map> getAllSystemGood(String goodSku,String goodSystemChname,String goodClass,String goodSellType,Integer goodCreateTimePX,Integer goodUpdTimePX,
                               Integer goodPurchasePricePX);

    Integer saveNewSystemGood(GoodInfo goodInfo);

}
