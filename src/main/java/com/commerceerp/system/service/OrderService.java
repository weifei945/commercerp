package com.commerceerp.system.service;

import com.commerceerp.system.mapper.OrderMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface OrderService {

    List<Map> getAllOrderInfo(@Param("orderChannel") String orderChannel,@Param("storeName") String storeName,@Param("addressCountry") String addressCountry,
                              @Param("orderStatus")String orderStatus, @Param("orderDownTimePX")Integer orderDownTimePX,
                              @Param("orderSkuPX")Integer orderSkuPX, @Param("orderMoneyPX")Integer orderMoneyPX);

}
