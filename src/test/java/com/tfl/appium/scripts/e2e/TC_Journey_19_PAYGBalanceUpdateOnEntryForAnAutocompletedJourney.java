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
import com.tfl.appium.lib.OysterBalanceUpdate;
import com.tfl.appium.lib.OysterCreateTransaction;
import com.tfl.appium.lib.OysterJourneyHistory;
import com.tfl.appium.lib.OysterLogOut;
import com.tfl.appium.lib.OysterLogin;
import com.tfl.appium.lib.OysterPayAsYouGo;
import com.tfl.appium.lib.OysterPaymentCardDetails;

public class TC_Journey_19_PAYGBalanceUpdateOnEntryForAnAutocompletedJourney extends AppiumEngine{

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
	public void verifyOysterPAYGEntryForAnAutoCompletedJourney(ITestContext context,Hashtable<String, String> data)
			throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);

		String TransactionFileEntry = data.get("TransactionFileEntry");
		String TransactionData1 =FileUtil.readFile(TransactionFileEntry);
		Object EntryTransactionObject = JsonUtil.getJsonObject(TransactionData1, "$.Entry");
		String entryStationName1 = JsonUtil.getJsonObjectElement(EntryTransactionObject, ".StationName");
		
		String TransactionFileAutoCompletedJourney = data.get("TransactionFileAutoCompletedJourney");
		String TransactionData2 =FileUtil.readFile(TransactionFileAutoCompletedJourney);
		Object ExitTransactionObject2 = JsonUtil.getJsonObject(TransactionData2, "$.Exit");
		String exitStationName1 = JsonUtil.getJsonObjectElement(ExitTransactionObject2, ".StationName");
		
		Object PAYGTransactionObject = JsonUtil.getJsonObject(TransactionData2, "$.PAYG");
		String paygStationName = JsonUtil.getJsonObjectElement(PAYGTransactionObject, ".StationName");

		
		Object EntryTransactionObject3 = JsonUtil.getJsonObject(TransactionData2, "$.Entry");
		String entryStationName2 = JsonUtil.getJsonObjectElement(EntryTransactionObject3, ".StationName");
		
		String TransactionFileAutoCompletedJourneyExit = data.get("TransactionFileAutoCompletedJourneyExit");
		String TransactionData3 =FileUtil.readFile(TransactionFileAutoCompletedJourneyExit);
		Object exitTransactionObject2 = JsonUtil.getJsonObject(TransactionData3, "$.Exit");
		String exitStationName2 = JsonUtil.getJsonObjectElement(exitTransactionObject2, ".StationName");
		int curBal;

		try {
				login.switchNetwork(context,data, action);
				login.verifyAppLogin(context,data, action);
				txnFilePath = txn.generateEntryTransaction(data,TransactionFileEntry,entryStationName1,EntryTransactionType,action);
				txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
				topupAmout = pyag.topup(context,data,action,data.get("topup"));
				paymentCard.payWithExistingCard(context,data,action);
				requestSequenceNumber = txn.verifyWebAgencyCodeAndGetRequestSequenceNumber(data, action);
				txn.setAutoLoadTransaction(true);
				txnFilePath = txn.generatePAYG(data,TransactionFileAutoCompletedJourney,PAYGTransactionType,requestSequenceNumber,action,topupAmout);
				txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
				txnFilePath = txn.generateAutoCompletedTransaction(data,TransactionFileAutoCompletedJourney,ExitTransactionType,action);
				txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
				txnFilePath = txn.generateExitTransaction(data,TransactionFileAutoCompletedJourneyExit,ExitTransactionType,action);
				txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
				txn.setAutoLoadTransaction(false);
				txn.verifyTrasactionExportedToCS(data,action);
				txnDetailsMap = txn.verifyTrasactionExportedToOYBO(data,action);
				txn.verifyFileSentToMobile(data, action,txnDetailsMap.get("FileReceivedId"));
				curBal=balaneUpdate.verifyBalanceUpdatedtoMobile(context,data,action,txnDetailsMap.get("FinalBalance"));
				journeyHistory.openJourneyHistoryScreen(context,action);
				journeyHistory.verifyJourneyHistory(context,entryStationName2,exitStationName2,action);
				journeyHistory.verifyTopUpScreen(context, data, paygStationName, action);
				journeyHistory.verifyJourneyHistory(context,entryStationName1,exitStationName1,action);
				//journeyHistory.verifyTopUpScreen(context, data, paygStationName, action);
				journeyHistory.navigateBackToMainScreen(context, action);			
				logout.appLogOut(context,action);	
				if(curBal<=10) {
					System.out.println("We are topping up :::: "+ data.get("PrestigeCardNumber")+" since the balance is low :::: " + curBal);
					requestSequenceNumber = txn.verifyWebAgencyCodeAndGetRequestSequenceNumber(data, action);
					txnFilePath = txn.generatePAYG(data,TransactionFileAutoCompletedJourney,PAYGTransactionType,requestSequenceNumber,action,"4000");
					txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
					txnFilePath = txn.generateEntryTransaction(data,TransactionFileEntry,entryStationName1,EntryTransactionType,action);
					txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
					txnFilePath = txn.generateExitTransaction(data,TransactionFileAutoCompletedJourneyExit,ExitTransactionType,action);
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
