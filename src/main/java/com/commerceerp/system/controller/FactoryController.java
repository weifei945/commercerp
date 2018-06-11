package com.commerceerp.system.controller;

import com.commerceerp.system.entity.GoodInfo;
import com.commerceerp.system.entity.GoodStorehouseInfo;
import com.commerceerp.system.entity.StoreInfo;
import com.commerceerp.system.entity.StorehouseInfo;
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


    @ApiOperation(value = "获取仓库清单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "gsStorehouseId", value = "仓库id", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "goodSystemChname", value = "商品名称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "goodSystemSku", value = "商品sku", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "goodSellType", value = "销售方式", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "gsCreateTimePX", value = "创建时间排序（1:正序,2:反序）", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "gsUpdTimePX", value = "更新时间排序（1:正序,l2：反序）", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "gsGoodSkuPX", value = "商品sku（1:正序,l2：反序）", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "gsAllNumberPX", value = "商品库存量（1:正序,l2：反序）", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "current", value = "当前页", required = true, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "pageSize", value = "记录数", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "/getAllGoodStorehouseInfo", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult getAllGoodStorehouseInfo(@RequestParam(value = "gsStorehouseId", required = false) String gsStorehouseId,
                                       @RequestParam(value = "goodSystemChname", required = false) String goodSystemChname,
                                       @RequestParam(value = "goodSystemSku", required = false) String goodSystemSku,
                                       @RequestParam(value = "goodSellType", required = false) String goodSellType,
                                       @RequestParam(value = "gsCreateTimePX", required = false) Integer gsCreateTimePX,
                                       @RequestParam(value = "gsUpdTimePX", required = false) Integer gsUpdTimePX,
                                       @RequestParam(value = "gsGoodSkuPX", required = false) Integer gsGoodSkuPX,
                                       @RequestParam(value = "gsAllNumberPX", required = false) Integer gsAllNumberPX,
                                       @RequestParam(value = "current") Integer current,
                                       @RequestParam(value = "pageSize") Integer pageSize) throws IOException {


        List<Map> gsList = factoryService.getAllGoodStorehouseInfo(gsStorehouseId,  goodSystemChname,  goodSystemSku,  goodSellType,  gsCreateTimePX,  gsUpdTimePX,  gsGoodSkuPX,  gsAllNumberPX);

        PageHelper.startPage(Integer.valueOf(current), Integer.valueOf(pageSize));

        //获取分页信息
        PageInfo<Map> pageInfo = new PageInfo<>(gsList);
        RdPage rdPage = RdPage.getPageInfo(pageInfo, String.valueOf(current), String.valueOf(pageSize));

        return new JsonResult(rdPage);
    }


    @ApiOperation(value = "更新商品的仓库清单")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "gsAllNumber", value = "商品库存量", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "gsGoodPrice", value = "商品单价", required = false, dataType = "Double"),
            @ApiImplicitParam(paramType = "query", name = "gsRemark", value = "备注", required = false, dataType = "String")
    })
    @RequestMapping(value = "/updGoodStorehouseInfo", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult updGoodStorehouseInfo(@RequestParam(value = "gsAllNumber", required = false) Integer gsAllNumber,
                                        @RequestParam(value = "gsGoodPrice", required = false) Double gsGoodPrice,
                                        @RequestParam(value = "gsRemark", required = false) String gsRemark) throws IOException {

        GoodStorehouseInfo gs = new GoodStorehouseInfo();
        gs.setGsAllNumber(gsAllNumber);
        gs.setGsGoodPrice(gsGoodPrice);
        gs.setGsRemark(gsRemark);

        Integer success = factoryService.updGoodStorehouseInfo(gs);

        if(success == 1){
            return new JsonResult(JsonResult.SUCCESS);
        }else{
            return new JsonResult(JsonResult.ERROR);
        }
    }


    @ApiOperation(value = "获取仓库信息列表")
    @ApiImplicitParams({

    })
    @RequestMapping(value = "/getAllStorehouseInfo", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult getAllStorehouseInfo() throws IOException {

        List<StorehouseInfo> storehouseInfos = factoryService.getAllStorehouseInfo();

            return new JsonResult(storehouseInfos);

    }



    @ApiOperation(value = "新增仓库信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "storehouseName", value = "仓库名称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "storehouseResponsibleMan", value = "负责人", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "storehousePhone", value = "联系方式", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "storehouseQq", value = "qq", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "storehouseEmail", value = "email", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "storehousePostcode", value = "邮编", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "storehouseProvince", value = "省", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "storehouseCity", value = "市", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "storehouseArea", value = "区", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "storehouseDetailAddress", value = "详细地址", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "storehouseRemark", value = "备注", required = true, dataType = "String")
    })
    @RequestMapping(value = "/saveNewStorehouseInfo", method = RequestMethod.POST)
    @ResponseBody
    public JsonResult saveNewStorehouseInfo(@RequestParam(value = "storehouseName", required = false) String storehouseName,
                                        @RequestParam(value = "storehouseResponsibleMan", required = false) String storehouseResponsibleMan,
                                        @RequestParam(value = "storehousePhone", required = false) String storehousePhone,
                                        @RequestParam(value = "storehouseQq", required = false) String storehouseQq,
                                        @RequestParam(value = "storehouseEmail", required = false) String storehouseEmail,
                                        @RequestParam(value = "storehousePostcode", required = false) String storehousePostcode,
                                        @RequestParam(value = "storehouseProvince", required = false) String storehouseProvince,
                                        @RequestParam(value = "storehouseCity", required = false) String storehouseCity,
                                        @RequestParam(value = "storehouseArea", required = false) String storehouseArea,
                                        @RequestParam(value = "storehouseDetailAddress", required = false) String storehouseDetailAddress,
                                        @RequestParam(value = "storehouseRemark", required = true) String storehouseRemark) throws IOException {


        StorehouseInfo storehouseInfo = new StorehouseInfo();
        storehouseInfo.setStorehouseArea(storehouseArea);
        storehouseInfo.setStorehouseCity(storehouseCity);
        storehouseInfo.setStorehouseArea(storehouseArea);
        storehouseInfo.setStorehouseDetailAddress(storehouseDetailAddress);
        storehouseInfo.setStorehouseEmail(storehouseEmail);
        storehouseInfo.setStorehousePhone(storehousePhone);
        storehouseInfo.setStorehouseProvince(storehouseProvince);
        storehouseInfo.setStorehouseResponsibleMan(storehouseResponsibleMan);
        storehouseInfo.setStorehouseRemark(storehouseRemark);
        storehouseInfo.setStorehouseName(storehouseName);
        storehouseInfo.setStorehouseQq(storehouseQq);
        storehouseInfo.setStorehousePostcode(storehousePostcode);

        Integer success = factoryService.saveNewStorehouseInfo(storehouseInfo);

        if(success == 1){
            return new JsonResult(JsonResult.SUCCESS);
        }else{
            return new JsonResult(JsonResult.ERROR);
        }
    }

}
