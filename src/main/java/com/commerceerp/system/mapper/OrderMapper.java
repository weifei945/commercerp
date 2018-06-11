package com.commerceerp.system.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OrderMapper {


    List<Map> getAllOrderInfo(@Param("orderChannel")String orderChannel, @Param("storeName")String storeName, @Param("addressCountry")String addressCountry,
                              @Param("orderStatus")String orderStatus, @Param("orderDownTimePX")Integer orderDownTimePX, @Param("orderSkuPX")Integer orderSkuPX,
                              @Param("orderMoneyPX")Integer orderMoneyPX);

}
