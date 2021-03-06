package com.tfl.appium.scripts.functional;

import java.util.Hashtable;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cubic.appiumaction.AppiumActions;
import com.cubic.appiumaction.AppiumEngine;
import com.cubic.datadriven.TestDataUtil;

public class TC_OysterAddMonthlyTravelCardAndroid extends AppiumEngine{
	com.tfl.appium.lib.OysterLoginAndroid login = new com.tfl.appium.lib.OysterLoginAndroid();
	com.tfl.appium.lib.OysterAddTravelCardAndroid AddTravelCard = new com.tfl.appium.lib.OysterAddTravelCardAndroid();
	com.tfl.appium.lib.OysterChangeCardDetailsAndroid changeCard = new com.tfl.appium.lib.OysterChangeCardDetailsAndroid();
	
	@DataProvider
	public Object[][] getTestDataOfJPMobile() {
		return TestDataUtil.getData("getTestDataForJPMobile", com.cubic.constants.AppConstants.TESTDATA_FOLDER_PATH, "JPMobile");
	}
	
	@Test(dataProvider = "getTestDataOfJPMobile")
	public void verifyOysterAddMpnthlyTravelCard(ITestContext context,Hashtable<String, String> data)
			throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);

		try {
			if (data.get("RunMode").equals("Y")) {
				System.out.println("in script");
				login.verifyAppLogin(data, action);
				AddTravelCard.addMonthlyTravelCard(action);
				changeCard.changeORAddPaymentCard(data, action);
				changeCard.addBliingAddress(data, action);
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
