package com.commerceerp.system.service.Impl;
import com.commerceerp.system.service.EbayService;


import com.commerceerp.system.util.DateUtil;
import com.ebay.sdk.ApiAccount;
import com.ebay.sdk.ApiCall;
import com.ebay.sdk.ApiContext;
import com.ebay.sdk.ApiCredential;
import com.ebay.sdk.call.FetchTokenCall;
import com.ebay.sdk.call.GetOrdersCall;
import com.ebay.sdk.call.GetSessionIDCall;
import com.ebay.services.client.ClientConfig;
import com.ebay.services.client.FindingServiceClientFactory;
import com.ebay.services.finding.FindingServicePortType;
import com.ebay.soap.eBLBaseComponents.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EbayServiceImpl implements EbayService{

    @Value("${ebay.appID}")
    private String ebayAppID;

    @Value("${ebay.devID}")
    private String ebayDevID;

    @Value("${ebay.certID}")
    private String ebayCertID;

    @Value("${ebay.ruName}")
    private String ebayRuName;


    /**
     * 获取用户token
     * @throws Exception
     */
    public  String  obtainUserToken() throws Exception {

        /*
         * 生成Context
         */
        ApiContext localContext = new ApiContext();
        ApiAccount apiAccount = new ApiAccount();
        apiAccount.setApplication(ebayAppID);
        apiAccount.setCertificate(ebayCertID);
        apiAccount.setDeveloper(ebayDevID);
        localContext.getApiCredential().setApiAccount(apiAccount);
        localContext.setApiServerUrl("https://api.sandbox.ebay.com/wsapi");

        /*
         *获取SessionID和生成登录URL
         */
        GetSessionIDCall apiCall = new GetSessionIDCall(localContext);
        apiCall.setRuName(ebayRuName);
        String sessionID = apiCall.getSessionID();
        //Handle the result returned
        System.out.println("sessionID : " + sessionID);
        //沙箱环境的连接，正式的时候可能要换连接
        String url = "https://signin.sandbox.ebay.com/ws/eBayISAPI.dll?SignIn&runame="+ebayRuName+"&SessID=" + sessionID;
        System.out.println("url: "+url);
        openBrowser(url);

        /*
         *生成token
         */
        FetchTokenCall fetchTokenCall = new FetchTokenCall(localContext);
        fetchTokenCall.setSessionID(sessionID);
        String token = fetchTokenCall.fetchToken();
        System.out.println("token :"+token);
        return token;
    }



    /**
     * 获取用户订单
     */
    public String GetOrders() throws Exception{

        ClientConfig config = new ClientConfig();

        config.setApplicationId(ebayAppID);

        GetOrdersRequestType getOrdersRequestType = new GetOrdersRequestType();
        getOrdersRequestType.setSortingOrder(SortOrderCodeType.DESCENDING);
        getOrdersRequestType.setCreateTimeFrom(DateUtil.getCalendarToStringTime("2018-01-01 00:00:00"));
        getOrdersRequestType.setCreateTimeTo(DateUtil.getCalendarToStringTime("2018-06-08 00:00:00"));

        ApiCall apiCall = new ApiCall(getApiContext());
        GetOrdersResponseType responseType = (GetOrdersResponseType) apiCall.execute(getOrdersRequestType);

       System.out.println(responseType);

        System.out.println("是否有更多订单---"+responseType.isHasMoreOrders());

        System.out.println("订单数组---"+responseType.getPageNumber()+"====="+responseType.getPaginationResult());

        //System.out.println("订单详情---"+responseType.getOrderArray().getOrder(0));

        System.out.println("订单数量---"+responseType.getOrderArray().getOrderLength());

        return null;

    }

    /**
     * 打开登录页面
     * @param url
     * @return
     */
    public  boolean openBrowser(String url) {
        if (url == null) return false;
        String[] unixBrowser = new String[] { "google-chrome", "firefox" };
        boolean success = false;
        if (System.getProperty("os.name").toLowerCase().startsWith("win")) {
            try {
                Runtime.getRuntime().exec(
                        new String[] { "rundll32.exe", "url.dll,FileProtocolHandler", url });
                success = true;
            } catch (Exception e) {
            }
        } else {
            for (int i = 0; i < unixBrowser.length; ++i)
                try {
                    Runtime.getRuntime().exec(new String[] { unixBrowser[0], url });
                    success = true;
                    break;
                } catch (Exception e) {
                }
        }
        return success;
    }


    /**
     * 生成APIContext
     * @return
     * @throws IOException
     */
    // Initializes ApiContext with token and eBay API server URL
    public  ApiContext getApiContext() throws Exception {
        ApiContext apiContext = new ApiContext();
        ApiAccount apiAccount = new ApiAccount();
        apiAccount.setApplication(ebayAppID);
        apiAccount.setCertificate(ebayCertID);
        apiAccount.setDeveloper(ebayDevID);
        apiContext.getApiCredential().setApiAccount(apiAccount);
        ApiCredential cred = apiContext.getApiCredential();
        cred.seteBayToken(obtainUserToken());
        apiContext.setApiServerUrl("https://api.sandbox.ebay.com/wsapi");
        apiContext.setEpsServerUrl("https://api.sandbox.ebay.com/ws/api.dll");    // 这个要设置，不然会出错
        return apiContext;
    }
}
