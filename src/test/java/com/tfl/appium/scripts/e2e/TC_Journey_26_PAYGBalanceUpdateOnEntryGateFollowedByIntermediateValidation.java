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

public class TC_Journey_26_PAYGBalanceUpdateOnEntryGateFollowedByIntermediateValidation extends AppiumEngine{

	public static String txnFilePath = "";
	public static String topupAmout = "";
	public HashMap<String, String> txnDetailsMap = new HashMap<String, String>();
	public static boolean testrailResultStatus = false;
	public static String testrailResultComment = "";
	public static String PAYGTransactionType = "PAYG";
	public static String EntryTransactionType = "Entry";
	public static String ExitTransactionType = "Exit";
	public static String IVALTransactionType = "IVAL";
	public static String requestSequenceNumber = null;
	
	
	OysterLogin login = new OysterLogin();
	OysterPayAsYouGo pyag = new OysterPayAsYouGo();
	OysterLogOut logout = new OysterLogOut();
	OysterCreateTransaction txn = new OysterCreateTransaction();
	OysterPaymentCardDetails paymentCard = new OysterPaymentCardDetails();
	OysterBalanceUpdate balaneUpdate = new OysterBalanceUpdate();
	OysterJourneyHistory journeyHistory = new OysterJourneyHistory();

	@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class)
	public void verifyOysterPAYGBalanceUpdateOnEntryGateFollowedByIntermediateValidation(ITestContext context,Hashtable<String, String> data)
			throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);

		String TransactionFile1 = data.get("TransactionFile1");
		String TransactionData1 =FileUtil.readFile(TransactionFile1);
		Object EntryTransactionObject = JsonUtil.getJsonObject(TransactionData1, "$.Entry");
		Object IvalTransactionObject = JsonUtil.getJsonObject(TransactionData1, "$.IVAL");
		String entryStationName1 = JsonUtil.getJsonObjectElement(EntryTransactionObject, ".StationName");
		String exitStationName1 = "";
		String entryStationName2 = JsonUtil.getJsonObjectElement(IvalTransactionObject, ".StationName");
		Object PAYGTransactionObject = JsonUtil.getJsonObject(TransactionData1, "$.PAYG");
		String paygStationName = JsonUtil.getJsonObjectElement(PAYGTransactionObject, ".StationName");
		int curBal;
		
		try {
				login.switchNetwork(context,data, action);
				login.verifyAppLogin(context,data, action);
				
				topupAmout = pyag.topup(context,data,action,data.get("topup"));
				paymentCard.payWithExistingCard(context,data,action);
				requestSequenceNumber = txn.verifyWebAgencyCodeAndGetRequestSequenceNumber(data, action);
				
				txnFilePath = txn.generatePAYG(data,TransactionFile1,PAYGTransactionType,requestSequenceNumber,action,topupAmout);
				txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
				
				txnFilePath = txn.generateEntryTransaction(data,TransactionFile1,entryStationName1,EntryTransactionType,action);
				txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
				
				txn.setEntryStationName(entryStationName1);
				txnFilePath = txn.generateIvalTransaction(data,TransactionFile1,entryStationName2,IVALTransactionType,action);
				txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
				
				txn.verifyTrasactionExportedToCS(data,action);
				txnDetailsMap = txn.verifyTrasactionExportedToOYBO(data,action);
				txn.verifyFileSentToMobile(data, action,txnDetailsMap.get("FileReceivedId"));
				curBal =balaneUpdate.verifyBalanceUpdatedtoMobile(context,data,action,txnDetailsMap.get("FinalBalance"));
				journeyHistory.openJourneyHistoryScreen(context,action);
				journeyHistory.verifyJourneyHistory(context,entryStationName1,exitStationName1,action);
				journeyHistory.verifyTopUpScreen(context, data, paygStationName, action);
				journeyHistory.navigateBackToMainScreen(context, action);				
				logout.appLogOut(context,action);
				
				if(curBal<=10){
					System.out.println("We are topping up :::: "+ data.get("PrestigeCardNumber")+" since the balance is low :::: " + curBal);
					requestSequenceNumber = txn.verifyWebAgencyCodeAndGetRequestSequenceNumber(data, action);
					txnFilePath = txn.generatePAYG(data,TransactionFile1,PAYGTransactionType,requestSequenceNumber,action,"4000");
					txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
					txnFilePath = txn.generateEntryTransaction(data,TransactionFile1,entryStationName1,EntryTransactionType,action);
					txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
					txn.setEntryStationName(entryStationName1);
					txnFilePath = txn.generateIvalTransaction(data,TransactionFile1,entryStationName2,IVALTransactionType,action);
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
