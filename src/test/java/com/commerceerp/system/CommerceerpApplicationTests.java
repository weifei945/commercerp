package com.commerceerp.system;

import com.commerceerp.system.service.EbayService;
import com.ebay.sdk.ApiContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommerceerpApplicationTests {

	@Autowired
	private EbayService ebayService;

	@Test
	public void contextLoads() {

		/*try {
			// Instantiate  ApiContext and initialize with token and Trading API URL
			ApiContext apiContext = ebayService.getApiContext();
			String SessionID = ebayService.obtainSessionID(apiContext);
			ebayService.obtainUserToken(apiContext,SessionID);
		}  //try
		catch(Exception e) {
			System.out.println("Fail to get sessionID.");
			e.printStackTrace();
		}*/
	}

}
