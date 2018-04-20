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
import com.tfl.appium.lib.OysterAddTravelCard;
import com.tfl.appium.lib.OysterBalanceUpdate;
import com.tfl.appium.lib.OysterCreateTransaction;
import com.tfl.appium.lib.OysterJourneyHistory;
import com.tfl.appium.lib.OysterLogOut;
import com.tfl.appium.lib.OysterLogin;
import com.tfl.appium.lib.OysterPayAsYouGo;
import com.tfl.appium.lib.OysterPaymentCardDetails;
import com.tfl.appium.lib.OysterProduct;

public class TC_Journey_03_TKTUpdateOnIntermediateValidationDuringcompletedJourney extends AppiumEngine{

	public static String txnFilePath = "";
	public static String topupAmout = "";
	public HashMap<String, String> txnDetailsMap = new HashMap<String, String>();
	public static boolean testrailResultStatus = false;
	public static String testrailResultComment = "";
	public static String PAYGTransactionType = "PAYG";
	public static String EntryTransactionType = "Entry";
	public static String ExitTransactionType = "Exit";
	public static String TKTTransactionType = "TKT";
	public static String IVALTransactionType = "IVAL";
	public static String requestSequenceNumber = null;
	public static String selectedDate = null;


	OysterLogin login = new OysterLogin();
	OysterPayAsYouGo pyag = new OysterPayAsYouGo();
	OysterLogOut logout = new OysterLogOut();
	OysterCreateTransaction txn = new OysterCreateTransaction();
	OysterPaymentCardDetails paymentCard = new OysterPaymentCardDetails();
	OysterBalanceUpdate balaneUpdate = new OysterBalanceUpdate();
	OysterJourneyHistory journeyHistory = new OysterJourneyHistory();
	OysterAddTravelCard addTravelCard = new OysterAddTravelCard();
	OysterProduct product = new OysterProduct();

	@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class)
	public void verifyOysterTKTUpdateOnIntermediateValidationDuringCompletedJourney(ITestContext context,Hashtable<String, String> data)
			throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);

		String TransactionFile1 = data.get("TransactionFile");
		String TransactionData1 =FileUtil.readFile(TransactionFile1);
		Object EntryTransactionObject = JsonUtil.getJsonObject(TransactionData1, "$.Entry");
		Object IvalTransactionObject = JsonUtil.getJsonObject(TransactionData1, "$.IVAL");
		Object ExitTransactionObject = JsonUtil.getJsonObject(TransactionData1, "$.Exit");
		
		String entryStationName = JsonUtil.getJsonObjectElement(EntryTransactionObject, ".StationName");
		String iValStationName = JsonUtil.getJsonObjectElement(IvalTransactionObject, ".StationName");
		String exitStationName = JsonUtil.getJsonObjectElement(ExitTransactionObject, ".StationName");




		try {
				login.switchNetwork(context,data, action);
				login.verifyAppLogin(context,data, action);
				addTravelCard.addTravelCard(context, data, "weekly", action);
				selectedDate = addTravelCard.selectZoneOneToTwo(context,data.get("StartDate"),data.get("ZoneRanges").split("-")[0],data.get("ZoneRanges").split("-")[1],action);
				
				paymentCard.payWithExistingCard(context,data,action);
				requestSequenceNumber = txn.verifyWebAgencyCodeAndGetRequestSequenceNumber(data, action);
				//selectedDate = "14/11/2017";
				txnFilePath = txn.generateEntryTransaction(data,TransactionFile1,entryStationName,EntryTransactionType,action);
				txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
				
				txnFilePath = txn.generateTKTTransaction(context,data,TransactionFile1,TKTTransactionType,requestSequenceNumber,selectedDate,data.get("ZoneRanges"),action);
				txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
				
				txn.setEntryStationName(entryStationName);
				txnFilePath = txn.generateIvalTransaction(data,TransactionFile1,entryStationName,IVALTransactionType,action);
				txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
				
				txnFilePath = txn.generateExitTransaction(data,TransactionFile1,ExitTransactionType,action);
				txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);


				txn.verifyTrasactionExportedToCS(data,action);
				txnDetailsMap = txn.verifyTrasactionExportedToOYBO(data,action);
				txn.verifyFileSentToMobile(data, action,txnDetailsMap.get("FileReceivedId"));
				product.verifyTicketUpdatedtoMobile(context,data,action,txnDetailsMap.get("FinalBalance"),selectedDate,data.get("ZoneRanges"));
				journeyHistory.openJourneyHistoryScreen(context,action);
				journeyHistory.verifyJourneyHistory(context,entryStationName,exitStationName,action);
				journeyHistory.verifyTicketCollectedStationScreen(context, data, action);
				journeyHistory.navigateBackToMainScreen(context, action);				
				logout.appLogOut(context,action);
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
