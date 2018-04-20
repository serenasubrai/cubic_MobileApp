package com.tfl.appium.scripts.functional;

import java.util.Hashtable;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cubic.appiumaction.AppiumActions;
import com.cubic.appiumaction.AppiumEngine;
import com.cubic.datadriven.TestDataUtil;
import com.cubic.constants.*;

public class TC_OysterAddAnualTravelCard extends AppiumEngine{
	com.tfl.appium.lib.OysterLogin login = new com.tfl.appium.lib.OysterLogin();
	com.tfl.appium.lib.OysterAddTravelCard AddTravelCard = new com.tfl.appium.lib.OysterAddTravelCard();
	com.tfl.appium.lib.OysterPaymentCardDetails changeCard = new com.tfl.appium.lib.OysterPaymentCardDetails();
	
	@DataProvider
	public Object[][] getTestDataForTFLMobile() {
		return TestDataUtil.getData("getTestDataForTFLMobile", AppConstants.TESTDATA_FOLDER_PATH, "TFL_AddTravelCard");
	}
	
	@Test(dataProvider = "getTestDataForTFLMobile")
	public void verifyOysterAddAnualTravelCard(ITestContext context,Hashtable<String, String> data)
			throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);

		try {
			if (data.get("RunMode").equals("Y")) {
				System.out.println("in script");
				login.switchNetwork(context,data, action);
				login.verifyAppLogin(context,data, action);
				AddTravelCard.addAnualTravelCard(context,action);
				//AddTravelCard.selectZone(context,action);
				changeCard.addNewPaymentCard(context,data, action);
				changeCard.addBliingAddress(context,data, action);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			System.out.println("In Finally Block");
			teardownAutomationTest(context, testCaseName);
		}  
	}
}
