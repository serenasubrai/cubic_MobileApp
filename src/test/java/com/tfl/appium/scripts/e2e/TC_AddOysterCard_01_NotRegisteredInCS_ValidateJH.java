package com.tfl.appium.scripts.e2e;

import java.util.HashMap;
import java.util.Hashtable;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.cubic.appiumaction.AppiumActions;
import com.cubic.appiumaction.AppiumEngine;
import com.cubic.commonLib.DataProviderSource;
import com.cubic.constants.AppConstants;
import com.tfl.appium.lib.OysterAddOysCard;
import com.tfl.appium.lib.OysterLogOut;
import com.tfl.appium.lib.OysterLogin;


public class TC_AddOysterCard_01_NotRegisteredInCS_ValidateJH extends AppiumEngine{

	public static String txnFilePath = "";
	public static String topupAmout = "";
	public HashMap<String, String> txnDetailsMap = new HashMap<String, String>();
	public static boolean testrailResultStatus = false;
	public static String testrailResultComment = "";
	public static String PAYGTransactionType = "PAYG";
	public static String EntryTransactionType = "Entry";
	public static String ExitTransactionType = "Exit";
	public static String requestSequenceNumber = null;
	public static String finalBalance= null;


	OysterLogin login = new OysterLogin();
	OysterLogOut logout = new OysterLogOut();
	OysterAddOysCard addOys = new OysterAddOysCard();

	@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class)
	public void addOysterNotRegInCSValidateJH(ITestContext context,Hashtable<String, String> data)
			throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);

		try {

			addOys.verifyCardUnregInCS(data, action);
			login.switchNetwork(context,data, action);
			login.verifyAppLogin(context,data, action);
			addOys.addOysterCard(context,data, action);
			addOys.journeyHistoryCheck(data, action);
			addOys.verifyOysterCardAddedSuccessfully (context,data, action);
			/*logout.appLogOut(context,action);*/
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);

		} finally {
			System.out.println("In Finally Block");
			teardownAutomationTest(context, testCaseName);
		}
	}



}
