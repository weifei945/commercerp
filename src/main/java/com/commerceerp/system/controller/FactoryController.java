package com.commerceerp.system.controller;

import com.commerceerp.system.entity.GoodInfo;
import com.commerceerp.system.service.FactoryService;
import com.commerceerp.system.service.OrderService;
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
import java.util.List;
import java.util.Map;

@Api(description = "仓库接口", value = "仓库接口")
@Scope("prototype")
@RestController
@EnableAutoConfiguration
public class FactoryController {

    @Autowired
    private FactoryService factoryService;

    @ApiOperation(value = "获取商品信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "goodSku", value = "商品", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "goodSystemChname", value = "商品名称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "goodClass", value = "产品类型", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "goodSellType", value = "销售方式", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "goodCreateTimePX", value = "创建时间排序（1:正序,2:反序）", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "goodUpdTimePX", value = "更新时间排序（1:正序,l2：反序）", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "goodPurchasePricePX", value = "商品参考价（1:正序,l2：反序）", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "current", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "记录数", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/getAllSystemGood", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult getAllSystemGood(@RequestParam(value = "goodSku", required = false) String goodSku,
                                      @RequestParam(value = "goodSystemChname", required = false) String goodSystemChname,
                                      @RequestParam(value = "goodClass", required = false) String goodClass,
                                      @RequestParam(value = "goodSellType", required = false) String goodSellType,
                                      @RequestParam(value = "goodCreateTimePX", required = false) Integer goodCreateTimePX,
                                      @RequestParam(value = "goodUpdTimePX", required = false) Integer goodUpdTimePX,
                                      @RequestParam(value = "goodPurchasePricePX", required = false) Integer goodPurchasePricePX,
                                      @RequestParam(value = "current") Integer current,
                                      @RequestParam(value = "pageSize") Integer pageSize) throws IOException {


        List<Map> goodsInfoList = factoryService.getAllSystemGood(goodSku,goodSystemChname,goodClass,goodSellType,goodCreateTimePX,goodUpdTimePX,goodPurchasePricePX);

        PageHelper.startPage(Integer.valueOf(current), Integer.valueOf(pageSize));

        //获取分页信息
        PageInfo<Map> pageInfo = new PageInfo<>(goodsInfoList);
        RdPage rdPage = RdPage.getPageInfo(pageInfo, String.valueOf(current), String.valueOf(pageSize));

        return new JsonResult(rdPage);
    }

    @ApiOperation(value = "新增系统商品信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "goodSystemEnname", value = "英文名称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "goodSystemChname", value = "中文名称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "goodPicPath", value = "图片地址", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "goodStorehouseId", value = "仓库id", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "goodSellType", value = "销售方式", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "goodClass", value = "产品分类", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "goodPurchasePrice", value = "商品参考价", required = false, dataType = "Double"),
            @ApiImplicitParam(paramType = "query", name = "goodState", value = "产品状态", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "goodWeight", value = "产品重量", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "goodSize", value = "产品尺寸", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "goodPlatformSku", value = "平台商品sku", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "goodSystemSku", value = "系统商品sku", required = true, dataType = "String")
    })
    @RequestMapping(value = "/saveNewSystemGood", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult saveNewSystemGood(@RequestParam(value = "goodSystemEnname", required = false) String goodSystemEnname,
                                       @RequestParam(value = "goodSystemChname", required = false) String goodSystemChname,
                                       @RequestParam(value = "goodPicPath", required = false) String goodPicPath,
                                       @RequestParam(value = "goodStorehouseId", required = false) String goodStorehouseId,
                                       @RequestParam(value = "goodSellType", required = false) String goodSellType,
                                       @RequestParam(value = "goodClass", required = false) String goodClass,
                                       @RequestParam(value = "goodPurchasePrice", required = false) Double goodPurchasePrice,
                                       @RequestParam(value = "goodState", required = false) String goodState,
                                       @RequestParam(value = "goodWeight", required = false) String goodWeight,
                                       @RequestParam(value = "goodSize", required = false) String goodSize,
                                       @RequestParam(value = "goodPlatformSku", required = true) String goodPlatformSku,
                                       @RequestParam(value = "goodSystemSku", required = true) String goodSystemSku) throws IOException {


        GoodInfo goodInfo = new GoodInfo();
        goodInfo.setGoodSystemEnname(goodSystemEnname);
        goodInfo.setGoodSystemChname(goodSystemChname);
        goodInfo.setGoodPicPath(goodPicPath);
        goodInfo.setGoodStorehouseId(goodStorehouseId);
        goodInfo.setGoodSellType(goodSellType);
        goodInfo.setGoodClass(goodClass);
        goodInfo.setGoodPurchasePrice(goodPurchasePrice);
        goodInfo.setGoodState(goodState);
        goodInfo.setGoodWeight(goodWeight);
        goodInfo.setGoodSize(goodSize);
        goodInfo.setGoodPlatformSku(goodPlatformSku);
        goodInfo.setGoodSystemSku(goodSystemSku);

        Integer success = factoryService.saveNewSystemGood(goodInfo);

        if(success == 1){
            return new JsonResult(JsonResult.SUCCESS);
        }else{
            return new JsonResult(JsonResult.ERROR);
        }
    }

}
