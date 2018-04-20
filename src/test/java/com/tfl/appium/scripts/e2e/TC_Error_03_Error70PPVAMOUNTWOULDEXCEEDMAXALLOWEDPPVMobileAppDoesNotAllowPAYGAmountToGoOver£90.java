package com.tfl.appium.scripts.e2e;

import java.util.HashMap;
import java.util.Hashtable;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.cubic.appiumaction.AppiumActions;
import com.cubic.appiumaction.AppiumEngine;
import com.cubic.commonLib.DataProviderSource;
import com.cubic.constants.AppConstants;
import com.tfl.appium.lib.OysterBalanceUpdate;
import com.tfl.appium.lib.OysterCreateTransaction;
import com.tfl.appium.lib.OysterJourneyHistory;
import com.tfl.appium.lib.OysterLogOut;
import com.tfl.appium.lib.OysterLogin;
import com.tfl.appium.lib.OysterPayAsYouGo;
import com.tfl.appium.lib.OysterPaymentCardDetails;
import com.tfl.appium.page.HomePage;
import com.tfl.appium.page.journeyHistoryPage;

	public class TC_Error_03_Error70PPVAMOUNTWOULDEXCEEDMAXALLOWEDPPVMobileAppDoesNotAllowPAYGAmountToGoOver£90 extends AppiumEngine{
		
	public static String txnFilePath = "";
	public static String topupAmout = "";
	public HashMap<String, String> txnDetailsMap = new HashMap<String, String>();
	public static boolean testrailResultStatus = false;
	public static String testrailResultComment = "";
	public static String PAYGTransactionType = "PAYG";
	public static String EntryTransactionType = "Entry";
	public static String ExitTransactionType = "Exit";
	public static String requestSequenceNumber = null;
	
	
	
	OysterLogin login = new OysterLogin();
	OysterPayAsYouGo pyag = new OysterPayAsYouGo();
	OysterLogOut logout = new OysterLogOut();
	OysterCreateTransaction txn = new OysterCreateTransaction();
	OysterPaymentCardDetails paymentCard = new OysterPaymentCardDetails();
	OysterBalanceUpdate balaneUpdate = new OysterBalanceUpdate();
	OysterJourneyHistory journeyHistory = new OysterJourneyHistory();
	
	@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class)
	public void verifyOysterPAYGErrorCodeMaximumBalanceExceeds(ITestContext context,Hashtable<String, String> data)
		throws Throwable {
			String testCaseName = data.get("TestCase_Description");
			AppiumActions action = setupAutomationTest(context, testCaseName);
	
	int whileCounter = 0;
	try {
		journeyHistoryPage.journeyHistoryPageLocators(context);
		HomePage.homePageLocators(context); 
		login.switchNetwork(context,data, action);
		login.verifyAppLogin(context,data, action);
		while (txn.returnBalance(context,data,action)<90){
			whileCounter++;
			if (whileCounter==4) {
				break;
			}
			pyag.topup(context,data,action,data.get("topup"));
		if(paymentCard.payWithExistingCard(context,data,action)== true) {
			break;
		}else {
			continue;
		}
	}
	paymentCard.verifyErrorCodeMaximumBalanceExceedsMessage(context,data,action);
	paymentCard.getDBErrorCodeForMaximumBalanceExceedsMessage(context, data, action);
	action.click(journeyHistoryPage.backArrow, "backArrow");
	action.click(journeyHistoryPage.cancel, "cancel");
	//action.click(HomePage.closeBtn, "closeBtn");
	logout.appLogOut(context,action);
	
	} catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException(e);
	
	} finally {
		System.out.println("In Finally Block");
			teardownAutomationTest(context, testCaseName);
		}
	}
	
}
