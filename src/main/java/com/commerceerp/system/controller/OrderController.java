package com.commerceerp.system.controller;

import com.commerceerp.system.service.OrderService;
import com.commerceerp.system.util.ApiResult;
import com.commerceerp.system.util.JsonResult;
import com.commerceerp.system.util.RdPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(description = "订单接口", value = "订单接口")
@Scope("prototype")
@RestController
@EnableAutoConfiguration
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "获取订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "orderChannel", value = "平台渠道", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "storeName", value = "店铺账号", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "addressCountry", value = "国家区域", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "orderStatus", value = "订单状态（1:待审核,2:待处理,3:待发货,4:发货成功,5:发货失败）", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "orderDownTimePX", value = "下单时间排序（1:正序,2:反序）", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "orderMoneyPX", value = "订单金额排序（1:正序,l2：反序）", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "orderSkuPX", value = "产品sku（1:正序,l2：反序）", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "current", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "记录数", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/getAllOrderInfo", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult getAllOrderInfo(@RequestParam(value = "orderChannel", required = false) String orderChannel,
                                      @RequestParam(value = "storeName", required = false) String storeName,
                                      @RequestParam(value = "addressCountry", required = false) String addressCountry,
                                      @RequestParam(value = "orderStatus", required = false) String orderStatus,
                                      @RequestParam(value = "orderDownTimePX", required = false) Integer orderDownTimePX,
                                      @RequestParam(value = "orderMoneyPX", required = false) Integer orderMoneyPX,
                                      @RequestParam(value = "orderSkuPX", required = false) Integer orderSkuPX,
                                      @RequestParam(value = "current") Integer current,
                                      @RequestParam(value = "pageSize") Integer pageSize) throws IOException {


        List<Map> orderInfoList = orderService.getAllOrderInfo(orderChannel,storeName,addressCountry,orderStatus,orderDownTimePX,orderSkuPX,orderMoneyPX);

        PageHelper.startPage(Integer.valueOf(current), Integer.valueOf(pageSize));

        //获取分页信息
        PageInfo<Map> pageInfo = new PageInfo<>(orderInfoList);
        RdPage rdPage = RdPage.getPageInfo(pageInfo, String.valueOf(current), String.valueOf(pageSize));

        return new JsonResult(rdPage);
    }

}
