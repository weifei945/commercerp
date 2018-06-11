package com.commerceerp.system.mapper;

import com.commerceerp.system.entity.StorehouseInfo;

import java.util.List;

public interface StorehouseMapper {

    List<StorehouseInfo> getAllStorehouseInfo();

    Integer saveNewStorehouseInfo(StorehouseInfo storehouseInfo);

}
