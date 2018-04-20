package com.tfl.appium.scripts.e2e;

import java.util.HashMap;
import java.util.Hashtable;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.cubic.appiumaction.AppiumActions;
import com.cubic.appiumaction.AppiumEngine;
import com.cubic.commonLib.DataProviderSource;
import com.cubic.constants.AppConstants;
import com.cubic.genericutils.FileUtil;
import com.cubic.genericutils.JsonUtil;
import com.cubic.testrail.TestRailUtil;
import com.tfl.appium.lib.OysterBalanceUpdate;
import com.tfl.appium.lib.OysterCreateTransaction;
import com.tfl.appium.lib.OysterJourneyHistory;
import com.tfl.appium.lib.OysterLogOut;
import com.tfl.appium.lib.OysterLogin;
import com.tfl.appium.lib.OysterPayAsYouGo;
import com.tfl.appium.lib.OysterPaymentCardDetails;

public class TC_Journey_34_PAYGBalanceUpdateOnEntryGateDuringUncompletedJourney extends AppiumEngine{

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

	@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class)
	public void verifyOysterPAYGBalanceUpdateOnEntryGateDuringUncomletedJourney(ITestContext context,Hashtable<String, String> data)
			throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);

		String TransactionFile = data.get("TransactionFile");
		String TransactionData =FileUtil.readFile(TransactionFile);
		Object EntryTransactionObject = JsonUtil.getJsonObject(TransactionData, "$.Entry");
		String entryStationName = JsonUtil.getJsonObjectElement(EntryTransactionObject, ".StationName");
		Object PAYGTransactionObject = JsonUtil.getJsonObject(TransactionData, "$.PAYG");
		String paygStationName = JsonUtil.getJsonObjectElement(PAYGTransactionObject, ".StationName");
		String exitStationName ="";
		int curBal;
		try {
				login.switchNetwork(context,data, action);
				login.verifyAppLogin(context,data, action);
				topupAmout = pyag.topup(context,data,action,data.get("topup"));
				finalBalance = pyag.getFinalBalance(action);
				System.out.println("fianal balance is "+finalBalance);
				paymentCard.payWithExistingCard(context,data,action);
				requestSequenceNumber = txn.verifyWebAgencyCodeAndGetRequestSequenceNumber(data, action);
				txnFilePath = txn.generatePAYG(data,TransactionFile,PAYGTransactionType,requestSequenceNumber,action,topupAmout);
				txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
				txnFilePath = txn.generateEntryTransaction(data,TransactionFile,entryStationName,EntryTransactionType,action);
				txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
				txn.verifyTrasactionExportedToCS(data,action);
				txnDetailsMap = txn.verifyTrasactionExportedToOYBO(data,action);
				txn.verifyFileSentToMobile(data, action,txnDetailsMap.get("FileReceivedId"));
				Thread.sleep(30000);
				curBal=balaneUpdate.verifyBalanceUpdatedtoMobile(context,data,action,finalBalance);
				journeyHistory.openJourneyHistoryScreen(context,action);
				journeyHistory.verifyJourneyHistory(context,entryStationName,exitStationName,action);
				journeyHistory.verifyTopUpScreen(context, data, paygStationName, action);
				journeyHistory.navigateBackToMainScreen(context, action);		
				logout.appLogOut(context,action);
				if(curBal<=10) {
					System.out.println("We are topping up :::: "+ data.get("PrestigeCardNumber")+" since the balance is low :::: " + curBal);
					requestSequenceNumber = txn.verifyWebAgencyCodeAndGetRequestSequenceNumber(data, action);
					txnFilePath = txn.generatePAYG(data,TransactionFile,PAYGTransactionType,requestSequenceNumber,action,"4000");
					txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
					txnFilePath = txn.generateEntryTransaction(data,TransactionFile,entryStationName,EntryTransactionType,action);
					txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
					txn.verifyTrasactionExportedToCS(data,action);
					txnDetailsMap = txn.verifyTrasactionExportedToOYBO(data,action);
					txn.verifyFileSentToMobile(data, action,txnDetailsMap.get("FileReceivedId"));
				}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);

		} finally {
			System.out.println("In Finally Block");
			txn.clearTransactionGeneratorStaticVariables();
			journeyHistory.clearJourneyHistoryStaticVariables();
			teardownAutomationTest(context, testCaseName);
		}
	}



}
