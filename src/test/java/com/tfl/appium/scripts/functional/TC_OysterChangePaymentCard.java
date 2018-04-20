package com.tfl.appium.scripts.functional;

import java.util.Hashtable;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cubic.appiumaction.AppiumActions;
import com.cubic.appiumaction.AppiumEngine;
import com.cubic.datadriven.TestDataUtil;
import com.tfl.appium.lib.OysterPaymentCardDetails;
import com.tfl.appium.lib.OysterLogin;
import com.tfl.appium.lib.OysterPayAsYouGo;

public class TC_OysterChangePaymentCard extends AppiumEngine{
	OysterLogin login = new OysterLogin();
	OysterPayAsYouGo pyag = new OysterPayAsYouGo();
	OysterPaymentCardDetails changeCard = new OysterPaymentCardDetails();


	@DataProvider
	public Object[][] getTestDataForTFLMobile() {
		return TestDataUtil.getData("getTestDataForTFLMobile", com.cubic.constants.AppConstants.TESTDATA_FOLDER_PATH, "TFL_CardChange");
	}
	@Test(dataProvider = "getTestDataForTFLMobile")
	public void verifyChangePaymentCard(ITestContext context,Hashtable<String, String> data)
			throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);

		try {
			if (data.get("RunMode").equals("Y")) {
				System.out.println("in script");
				login.switchNetwork(context,data, action);
				login.verifyAppLogin(context,data, action);
				pyag.topup(context,data,action,data.get("topup"));
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
