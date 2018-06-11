package com.commerceerp.system.mapper;

import com.commerceerp.system.entity.GoodStorehouseInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodStorehouseMapper {

    List<Map> getAllGoodStorehouseInfo(@Param("gsStorehouseId")String gsStorehouseId,@Param("goodSystemChname")String goodSystemChname,@Param("goodSystemSku")String goodSystemSku,
                                       @Param("goodSellType")String goodSellType,@Param("gsCreateTimePX")Integer gsCreateTimePX,@Param("gsUpdTimePX")Integer gsUpdTimePX,
                                       @Param("gsGoodSkuPX")Integer gsGoodSkuPX,@Param("gsAllNumberPX")Integer gsAllNumberPX);

    Integer updGoodStorehouseInfo(GoodStorehouseInfo goodStorehouseInfo);

}
