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
import com.tfl.appium.lib.OysterCreateTransaction;
import com.tfl.appium.lib.OysterJourneyHistory;
import com.tfl.appium.lib.OysterLogOut;
import com.tfl.appium.lib.OysterLogin;
import com.tfl.appium.lib.OysterPayAsYouGo;
import com.tfl.appium.lib.OysterPaymentCardDetails;
import com.tfl.appium.lib.OysterProduct;

public class TC_Journey_08_PAYGBalanceUpdateOnBus extends AppiumEngine{

	public static String txnFilePath = "";
	public static String topupAmout = "";
	public HashMap<String, String> txnDetailsMap = new HashMap<String, String>();
	public static boolean testrailResultStatus = false;
	public static String testrailResultComment = "";
	public static String PAYGTransactionType = "PAYG";
	public static String BusTransactionType = "BUS";
	public static String selectedDate = null;
	public static String requestSequenceNumber = null;
	
	
	OysterLogin login = new OysterLogin();
	OysterProduct product = new OysterProduct();
	OysterLogOut logout = new OysterLogOut();
	OysterCreateTransaction txn = new OysterCreateTransaction();
	OysterPaymentCardDetails paymentCard = new OysterPaymentCardDetails();
	OysterAddTravelCard addTravelCard = new OysterAddTravelCard();
	OysterJourneyHistory journeyHistory = new OysterJourneyHistory();

	@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class)
	public void verifyOysterBalanceUpdateOnBus(ITestContext context,Hashtable<String, String> data)
			throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);
	
		String TransactionFile = data.get("TransactionFile");
		String TransactionData =FileUtil.readFile(TransactionFile);
		Object EntryTransactionObject = JsonUtil.getJsonObject(TransactionData, "$.BUS");
		String entryStationName = JsonUtil.getJsonObjectElement(EntryTransactionObject, ".StationName");
		String exitStationName = "";
		try {
				login.switchNetwork(context,data, action);
				login.verifyAppLogin(context,data, action);
				addTravelCard.addWeeklyTravelCard(context,data,action);
				selectedDate = addTravelCard.selectZoneOneToTwo(context,data.get("StartDate"),data.get("ZoneRanges").split("-")[0],data.get("ZoneRanges").split("-")[1],action);
				paymentCard.payWithExistingCard(context,data,action);
				requestSequenceNumber = txn.verifyWebAgencyCodeAndGetRequestSequenceNumber(data, action);
				txnFilePath = txn.generatePAYG(data,TransactionFile,PAYGTransactionType,requestSequenceNumber,action,topupAmout);
				txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
				txnFilePath = txn.generateBusTap(data,TransactionFile,BusTransactionType,action);
				txn.copyFileToDGC(txnFilePath, AppConstants.BUS_DGC_PATH,action);
				txn.verifyTrasactionExportedToCS(data,action);
				txnDetailsMap = txn.verifyTrasactionExportedToOYBO(data,action);
				txn.verifyFileSentToMobile(data, action,txnDetailsMap.get("FileReceivedId"));
				product.verifyTicketUpdatedtoMobile(context,data,action,txnDetailsMap.get("FinalBalance"),selectedDate,data.get("ZoneRanges"));
				journeyHistory.verifyTramJourneyHistory(context, entryStationName, action);
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
