package com.commerceerp.system.service;

import com.ebay.sdk.ApiAccount;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.call.FetchTokenCall;
import com.ebay.sdk.call.GetSessionIDCall;
import com.ebay.sdk.helper.ConsoleUtil;

import java.io.IOException;

public interface EbayService {



    /**
     * 获取用户token
     */
    String obtainUserToken() throws Exception ;


    /**
     * 获取订单信息
     */
    String GetOrders() throws Exception;

    /**
     * 打开登录页面
     */
     boolean openBrowser(String url) ;


    /**
     * 生成Context
     */
     ApiContext getApiContext() throws Exception;


}
