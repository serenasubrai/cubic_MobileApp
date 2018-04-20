/*package com.tfl.appium.scripts.e2e;

import java.util.HashMap;
import java.util.Hashtable;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.cubic.accelerators.RESTActions;
import com.cubic.accelerators.RESTEngine;
import com.cubic.appiumaction.AppiumActions;
import com.cubic.commonLib.DataProviderSource;
import com.cubic.constants.AppConstants;
import com.tfl.appium.lib.OysterBalanceUpdate;
import com.tfl.appium.lib.OysterCreateTransaction;
import com.tfl.appium.lib.OysterJourneyHistory;
import com.tfl.appium.lib.OysterLogOut;
import com.tfl.appium.lib.OysterLogin;
import com.tfl.appium.lib.OysterPayAsYouGo;
import com.tfl.appium.lib.OysterPaymentCardDetails;
import com.tfl.appium.lib.createAutoLoadTransactionRecords;
import com.tfl.appium.page.journeyHistoryPage;

public class TC_Error_02_Error520MAXIMUMNUMBEROFCARDREQUESTSEXCEEDEDOk extends RESTEngine{

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
	OysterPayAsYouGo pyag = new OysterPayAsYouGo();
	OysterLogOut logout = new OysterLogOut();
	OysterCreateTransaction txn = new OysterCreateTransaction();
	OysterPaymentCardDetails paymentCard = new OysterPaymentCardDetails();
	OysterBalanceUpdate balaneUpdate = new OysterBalanceUpdate();
	OysterJourneyHistory journeyHistory = new OysterJourneyHistory();
	createAutoLoadTransactionRecords autoLoadRecords = new createAutoLoadTransactionRecords();

	@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class)
	public void verifyOysterPAYGMaximumNumberOfRequestsExceeds(ITestContext context,Hashtable<String, String> data)
			throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		RESTActions action = setupAutomationTest(context, testCaseName);
		

		try {
			AppiumActions AppiumAction = null ;
			autoLoadRecords.createAutoLoadRecords(context, data, action);
			if(context.getCurrentXmlTest().getSuite().getAllParameters().get("platformName").toString().equalsIgnoreCase("iOS")){
				AppiumAction =  new AppiumActions(customReports, testCaseName, AppConstants.platformNameiOS, "", AppConstants.browsername, AppConstants.executionenv, AppConstants.platformVersion, AppConstants.appiumUrl, AppConstants.deviceName, AppConstants.udid, AppConstants.bundleId, AppConstants.appPackage, AppConstants.appActivity, AppConstants.appWaitActivity,AppConstants.autoGrantPermissions,AppConstants.locale,AppConstants.noReset,AppConstants.fullReset,AppConstants.deviceOrientation,AppConstants.automationName,AppConstants.appiumVersion);
			}else if(context.getCurrentXmlTest().getSuite().getAllParameters().get("platformName").toString().equalsIgnoreCase("Android")) {
				AppiumAction =  new AppiumActions(customReports, testCaseName, AppConstants.platformName, AppConstants.app, AppConstants.browsername, AppConstants.executionenv, "", AppConstants.appiumUrl, AppConstants.deviceName, "", "", AppConstants.appPackage, AppConstants.appActivity, AppConstants.appWaitActivity,AppConstants.autoGrantPermissions,AppConstants.locale,AppConstants.noReset,AppConstants.fullReset,AppConstants.deviceOrientation,"","");
			}
			
			//AppiumActions AppiumAction =  new AppiumActions(customReports, testCaseName, AppConstants.platformName, AppConstants.app, AppConstants.browsername, AppConstants.executionenv, AppConstants.platformVersion, AppConstants.appiumUrl, AppConstants.deviceName, AppConstants.udid, AppConstants.bundleId, AppConstants.appPackage, AppConstants.appActivity, AppConstants.appWaitActivity);
			
			login.switchNetwork(context,data, AppiumAction);
			login.verifyAppLogin(context,data, AppiumAction);
			topupAmout = pyag.topup(context,data,AppiumAction,data.get("topup"));
			paymentCard.verifyErrorCodeMaximumRequestsExceedsMessage(context,data,AppiumAction);
			journeyHistoryPage.journeyHistoryPageLocators(context);
			AppiumAction.click(journeyHistoryPage.backArrow, "backArrow");
			AppiumAction.click(journeyHistoryPage.cancel, "cancel");
			//AppiumAction.click(HomePage.closeBtn, "closeBtn");
			logout.appLogOut(context,AppiumAction);
			
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