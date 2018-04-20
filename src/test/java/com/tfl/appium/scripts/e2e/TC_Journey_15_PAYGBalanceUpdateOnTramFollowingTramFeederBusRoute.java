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

public class TC_Journey_15_PAYGBalanceUpdateOnTramFollowingTramFeederBusRoute extends AppiumEngine{

	public static String txnFilePath = "";
	public static String topupAmout = "";
	public HashMap<String, String> txnDetailsMap = new HashMap<String, String>();
	public static boolean testrailResultStatus = false;
	public static String testrailResultComment = "";
	public static String TKTTransactionType = "TKT";
	public static String TramTransactionType = "TRAM";
	public static String EntryTransactionType = "Entry";
	public static String PAYGTransactionType = "PAYG";
	public static String ExitTransactionType = "Exit";
	public static String selectedDate = null;
	public static String requestSequenceNumber = null;
	public static String BusTransactionType = "BUS";
	
	
	OysterLogin login = new OysterLogin();
	OysterProduct product = new OysterProduct();
	OysterLogOut logout = new OysterLogOut();
	OysterPayAsYouGo pyag = new OysterPayAsYouGo();
	OysterCreateTransaction txn = new OysterCreateTransaction();
	OysterPaymentCardDetails paymentCard = new OysterPaymentCardDetails();
	OysterAddTravelCard addTravelCard = new OysterAddTravelCard();
	OysterJourneyHistory journeyHistory = new OysterJourneyHistory();
	OysterBalanceUpdate balaneUpdate = new OysterBalanceUpdate();

	@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class)
	public void verifyOysterBalanceUpdateOnTram(ITestContext context,Hashtable<String, String> data)
			throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);
	
		String TramTransactionFile = data.get("TransactionFile1");
		String TramTransactionData =FileUtil.readFile(TramTransactionFile);
		Object tramTransactionObject = JsonUtil.getJsonObject(TramTransactionData, "$.TRAM");
		String tramStationName = JsonUtil.getJsonObjectElement(tramTransactionObject, ".StationName");
		Object PAYGTransactionObject = JsonUtil.getJsonObject(TramTransactionData, "$.PAYG");
		String paygStationName = JsonUtil.getJsonObjectElement(PAYGTransactionObject, ".StationName");
		
		String BusTransactionFile = data.get("TransactionFile2");
		String BusTransactionData =FileUtil.readFile(BusTransactionFile);
		Object busTransactionObject = JsonUtil.getJsonObject(BusTransactionData, "$.BUS");
		String busStationName = JsonUtil.getJsonObjectElement(busTransactionObject, ".StationName");
		String exitStationName = "";
		int curBal;		
		
		try {
				login.switchNetwork(context,data, action);
				login.verifyAppLogin(context,data, action);
				topupAmout = pyag.topup(context,data,action,data.get("topup"));
				paymentCard.payWithExistingCard(context,data,action);
				requestSequenceNumber = txn.verifyWebAgencyCodeAndGetRequestSequenceNumber(data, action);
				txnFilePath = txn.generatePAYG(data,TramTransactionFile,PAYGTransactionType,requestSequenceNumber,action,topupAmout);
				txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
				txnFilePath = txn.generateTramTap(data,TramTransactionFile,TramTransactionType,action);
				txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
				txnFilePath = txn.generateBusTap(data, BusTransactionFile, BusTransactionType, action);
				txn.copyFileToDGC(txnFilePath, AppConstants.BUS_DGC_PATH,action);
				txn.verifyTrasactionExportedToCS(data,action);
				txnDetailsMap = txn.verifyTrasactionExportedToOYBO(data,action);
				txn.verifyFileSentToMobile(data, action,txnDetailsMap.get("FileReceivedId"));
				curBal=balaneUpdate.verifyBalanceUpdatedtoMobile(context,data,action,txnDetailsMap.get("FinalBalance"));
				journeyHistory.openJourneyHistoryScreen(context,action);
				journeyHistory.verifyTramJourneyHistory(context, tramStationName, action);
				journeyHistory.verifyBusJourneyHistory(context, busStationName, action);
				journeyHistory.verifyTopUpScreen(context, data, paygStationName, action);
				journeyHistory.navigateBackToMainScreen(context, action);		
				logout.appLogOut(context,action);
				if(curBal<=10){
					System.out.println("We are topping up :::: "+ data.get("PrestigeCardNumber")+" since the balance is low :::: " + curBal);
					requestSequenceNumber = txn.verifyWebAgencyCodeAndGetRequestSequenceNumber(data, action);
					txnFilePath = txn.generatePAYG(data,TramTransactionFile,PAYGTransactionType,requestSequenceNumber,action,"4000");
					txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
					txnFilePath = txn.generateTramTap(data,TramTransactionFile,TramTransactionType,action);
					txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
					txnFilePath = txn.generateBusTap(data, BusTransactionFile, BusTransactionType, action);
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
