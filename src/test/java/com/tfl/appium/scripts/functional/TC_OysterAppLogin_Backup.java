package com.tfl.appium.scripts.functional;
/*package com.tfl.appium.script;

import java.util.Hashtable;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cubic.appiumaction.AppiumActions;
import com.cubic.appiumaction.AppiumEngine;
import com.cubic.commonLib.DataProviderSource;
import com.cubic.constants.AppConstants;
import com.cubic.datadriven.TestDataUtil;
import com.tfl.appium.lib.OysterCreateTransactionBackup1;

public class TC_OysterAppLogin_Backup extends AppiumEngine{
	com.tfl.appium.lib.OysterLogin oyster = new com.tfl.appium.lib.OysterLogin();
	OysterCreateTransactionBackup1 txn = new OysterCreateTransactionBackup1();

	@DataProvider
	public Object[][] getTestDataOfJPMobile() {
		return TestDataUtil.getData("getTestDataForJPMobile", com.cubic.constants.AppConstants.TESTDATA_FOLDER_PATH, "JPMobile");
	}

	@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class)
	public void verifyOysterLogin(ITestContext context,Hashtable<String, String> data)
			throws Throwable {
		System.out.println("data is "+data);
		String testCaseName = data.get("TestCase_Description");
		System.out.println("testCaseName is "+testCaseName);
		AppiumActions action = setupAutomationTest(context, testCaseName);

		try {
			System.out.println("in script");

			oyster.switchNetwork(context,data, action);
				oyster.verifyAppLogin(context,data, action);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			System.out.println("In Finally Block");
			teardownAutomationTest(context, testCaseName);
		}
	}

	@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class)
	public void generateEntryTransaction(ITestContext context, Hashtable<String, String> data) throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);
		try {
			System.out.println("in script");
			//txn.generateEntryTransaction(data,action);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			System.out.println("In Finally Block");
			teardownAutomationTest(context, testCaseName);
		}
	}
}

*/