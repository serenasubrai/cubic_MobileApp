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

public class TC_Journey_23_PAYGBalanceUpdateOnDifferentStationReEntryFollowedByFinalExit extends AppiumEngine{

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
	public void verifyOysterPAYGBalanceUpdateOnDifferentStationReEntryByFinalExit(ITestContext context,Hashtable<String, String> data)
			throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);

		String TransactionFile1 = data.get("TransactionFile1");
		String TransactionData =FileUtil.readFile(TransactionFile1);
		Object EntryTransactionObject = JsonUtil.getJsonObject(TransactionData, "$.Entry");
		String entryStationName1 = JsonUtil.getJsonObjectElement(EntryTransactionObject, ".StationName");
		
		Object ExitTransactionObject2 = JsonUtil.getJsonObject(TransactionData, "$.Exit");
		String exitStationName1 = JsonUtil.getJsonObjectElement(ExitTransactionObject2, ".StationName");
		
		String TransactionFile2 = data.get("TransactionFile2");
		String TransactionData2 =FileUtil.readFile(TransactionFile2);
		Object EntryTransactionObject3 = JsonUtil.getJsonObject(TransactionData2, "$.Entry");
		
		String entryStationName2 = JsonUtil.getJsonObjectElement(EntryTransactionObject3, ".StationName");
		Object ExitTransactionObject4 = JsonUtil.getJsonObject(TransactionData2, "$.Exit");
		String exitStationName2 = JsonUtil.getJsonObjectElement(ExitTransactionObject4, ".StationName");
		
		Object PAYGTransactionObject = JsonUtil.getJsonObject(TransactionData2, "$.PAYG");
		String paygStationName = JsonUtil.getJsonObjectElement(PAYGTransactionObject, ".StationName");
		int curBal;
		try {
				txnFilePath = txn.generateEntryTransaction(data,TransactionFile1,entryStationName1,EntryTransactionType,action);
				txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
				txnFilePath = txn.generateExitTransaction(data,TransactionFile1,ExitTransactionType,action);
				txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
				login.switchNetwork(context,data, action);
				login.verifyAppLogin(context,data, action);
				topupAmout = pyag.topup(context,data,action,data.get("topup"));
				paymentCard.payWithExistingCard(context,data,action);
				requestSequenceNumber = txn.verifyWebAgencyCodeAndGetRequestSequenceNumber(data, action);
				txnFilePath = txn.generatePAYG(data,TransactionFile2,PAYGTransactionType,requestSequenceNumber,action,topupAmout);
				txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
				txnFilePath = txn.generateEntryTransaction(data,TransactionFile2,entryStationName2,EntryTransactionType,action);
				txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
				txnFilePath = txn.generateExitTransaction(data,TransactionFile2,ExitTransactionType,action);
				txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
				txn.verifyTrasactionExportedToCS(data,action);
				txnDetailsMap = txn.verifyTrasactionExportedToOYBO(data,action);
				txn.verifyFileSentToMobile(data, action,txnDetailsMap.get("FileReceivedId"));
				curBal = balaneUpdate.verifyBalanceUpdatedtoMobile(context,data,action,txnDetailsMap.get("FinalBalance"));
				journeyHistory.openJourneyHistoryScreen(context,action);
				journeyHistory.verifyJourneyHistory(context,entryStationName2,exitStationName2,action);
				journeyHistory.verifyTopUpScreen(context, data, paygStationName, action);
				journeyHistory.verifyJourneyHistory(context,entryStationName1,exitStationName1,action);
				journeyHistory.navigateBackToMainScreen(context, action);	
				logout.appLogOut(context,action);
				if(curBal<=10) {
					System.out.println("We are topping up :::: "+ data.get("PrestigeCardNumber")+" since the balance is low :::: " + curBal);
					requestSequenceNumber = txn.verifyWebAgencyCodeAndGetRequestSequenceNumber(data, action);
					txnFilePath = txn.generatePAYG(data,TransactionFile2,PAYGTransactionType,requestSequenceNumber,action,"4000");
					txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
					txnFilePath = txn.generateEntryTransaction(data,TransactionFile2,entryStationName2,EntryTransactionType,action);
					txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
					txnFilePath = txn.generateExitTransaction(data,TransactionFile2,ExitTransactionType,action);
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
