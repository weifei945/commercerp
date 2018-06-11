package com.commerceerp.system.mapper;

import com.commerceerp.system.entity.GoodInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodMapper {

    List<Map> getAllSystemGood(@Param("goodSku")String goodSku,@Param("goodSystemChname")String goodSystemChname,@Param("goodClass")String goodClass,
                               @Param("goodSellType")String goodSellType,@Param("goodCreateTimePX")Integer goodCreateTimePX,@Param("goodUpdTimePX")Integer goodUpdTimePX,
                               @Param("goodPurchasePricePX")Integer goodPurchasePricePX);

    Integer saveNewSystemGood(GoodInfo goodInfo);

}
