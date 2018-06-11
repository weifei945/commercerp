package com.commerceerp.system.service.Impl;

import com.commerceerp.system.mapper.OrderMapper;
import com.commerceerp.system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderMapper orderMapper;


    /**
     * 获取所有订单信息列表
     */
    @Override
    public List<Map> getAllOrderInfo(String orderChannel, String storeName, String addressCountry, String orderStatus, Integer orderDownTimePX, Integer orderSkuPX, Integer orderMoneyPX) {

        List<Map> orderInfoList = orderMapper.getAllOrderInfo(orderChannel,storeName,addressCountry,orderStatus,orderDownTimePX,orderSkuPX,orderMoneyPX);

        return orderInfoList;
    }
}
