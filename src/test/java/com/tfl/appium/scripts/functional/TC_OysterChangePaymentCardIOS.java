package com.tfl.appium.scripts.functional;

import java.util.Hashtable;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cubic.appiumaction.AppiumActions;
import com.cubic.appiumaction.AppiumEngine;
import com.cubic.datadriven.TestDataUtil;
import com.tfl.appium.lib.OysterChangeCardDetailsIOS;
import com.tfl.appium.lib.OysterLoginIOS;
import com.tfl.appium.lib.OysterPayAsYouGoIOS;

public class TC_OysterChangePaymentCardIOS extends AppiumEngine{
	OysterLoginIOS login = new OysterLoginIOS();
	OysterPayAsYouGoIOS pyag = new OysterPayAsYouGoIOS();
	OysterChangeCardDetailsIOS changeCard = new OysterChangeCardDetailsIOS();


	@DataProvider
	public Object[][] getTestDataOfJPMobile() {
		return TestDataUtil.getData("getTestDataForJPMobile", com.cubic.constants.AppConstants.TESTDATA_FOLDER_PATH, "JPMobile");
	}

	@Test(dataProvider = "getTestDataOfJPMobile")
	public void verifyOysterTopUP(ITestContext context,Hashtable<String, String> data)
			throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);

		try {
			if (data.get("RunMode").equals("Y")) {
				System.out.println("in script");
				login.verifyAppLogin(data, action);
				pyag.topup(action);
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
