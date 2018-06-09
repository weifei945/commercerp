package com.commerceerp.system.controller;

import com.commerceerp.system.service.EbayService;
import com.commerceerp.system.util.ApiResult;
import com.ebay.sdk.ApiContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Api(description = "ebay接口", value = "ebay接口")
@Scope("prototype")
@RestController
@EnableAutoConfiguration
public class EBayController {

    @Autowired
    private EbayService ebayService;

    @ApiOperation(value = "ebay获取token")
    @ApiImplicitParams({

    })
    @RequestMapping(value = "/getEbayUserToken", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> getEbayUserToken() throws IOException {

        ApiResult<String> result= null;

        try {

            ebayService.obtainUserToken();
        }
        catch(Exception e) {
            System.out.println("Fail to get sessionID.");
            e.printStackTrace();
        }

        return null;
    }

    @ApiOperation(value = "ebay获取订单情况")
    @ApiImplicitParams({

    })
    @RequestMapping(value = "/getEbayUserOrders", method = RequestMethod.POST)
    @ResponseBody
    public ApiResult<String> getEbayUserOrders() throws IOException {

        ApiResult<String> result= null;

        try {

            ebayService.GetOrders();
        }
        catch(Exception e) {
            System.out.println("Fail to get orders.");
            e.printStackTrace();
        }

        return null;
    }

}
