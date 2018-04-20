package com.tfl.appium.scripts.functional;
/*package com.tfl.appium.script;

import java.util.HashMap;
import java.util.Hashtable;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.cubic.appiumaction.AppiumActions;
import com.cubic.appiumaction.AppiumEngine;
import com.cubic.commonLib.DataProviderSource;
import com.cubic.constants.AppConstants;
import com.cubic.testrail.TestRailUtil;
import com.tfl.appium.lib.OysterCreateTransaction;
import com.tfl.appium.lib.OysterLogOut;
import com.tfl.appium.lib.OysterLogin;
import com.tfl.appium.lib.OysterPayAsYouGo;

public class TC_OysterPayAsYouGo2 extends AppiumEngine{
	OysterLogin login = new OysterLogin();
	OysterPayAsYouGo pyag = new OysterPayAsYouGo();
	OysterLogOut logout = new OysterLogOut();
	OysterCreateTransaction txn = new OysterCreateTransaction();
	public static String txnFilePath = "";
	public static String topupAmout = "";
	public HashMap<String, String> txnDetailsMap = new HashMap<String, String>();
	public static boolean testrailResultStatus = false;
	public static String testrailResultComment = "";
	
	@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class)
	public void verifyOysterTopUP(ITestContext context,Hashtable<String, String> data)
			throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);

		try {
			if (data.get("RunMode").equals("Y")) {
				System.out.println("in script");
				login.switchNetwork(context,data, action);
				login.verifyAppLogin(context,data, action);
				topupAmout = pyag.topup(context,data,action);
				txnFilePath = txn.generatePAYG(data,action,topupAmout);
				testrailResultStatus = true;
			}
		} catch (Exception e) {
			testrailResultStatus = false;
			e.printStackTrace();
			throw new RuntimeException(e);

		} finally {
			System.out.println("In Finally Block");
			teardownAutomationTest(context, testCaseName);
		}
	}
	
	@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class,dependsOnMethods = { "verifyOysterTopUP" })
	public void generatePAYGTransaction(ITestContext context, Hashtable<String, String> data) throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);
		setupAutomationTest(context, testCaseName);
		
		try {
			System.out.println("in PAYG script");
			txnFilePath = txn.generatePAYG(data,action,topupAmout);
			txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
			testrailResultStatus = true;
		} catch (Exception e) {
			e.printStackTrace();
			testrailResultStatus = false;
			throw new RuntimeException(e);
		} finally {
			System.out.println("In Finally Block");
			teardownAutomationTest(context, testCaseName);
		}
	}

	@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class,dependsOnMethods = { "generatePAYGTransaction" })
	public void generateEntryTransaction(ITestContext context, Hashtable<String, String> data) throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);
		try {
			System.out.println("in script");
			txnFilePath = txn.generateEntryTransaction(data,action);
			txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
			testrailResultStatus = true;
		} catch (Exception e) {
			e.printStackTrace();
			testrailResultStatus = false;
			throw new RuntimeException(e);
		} finally {
			System.out.println("In Finally Block");
			teardownAutomationTest(context, testCaseName);
		}
	}

	@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class,dependsOnMethods = { "generateEntryTransaction" })
	public void generateExitTransaction(ITestContext context, Hashtable<String, String> data) throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);
		try {
			System.out.println("in script");
			txnFilePath = txn.generateExitTransaction(data,action);
			txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
			testrailResultStatus = true;
		} catch (Exception e) {
			e.printStackTrace();
			testrailResultStatus = false;
			throw new RuntimeException(e);
		} finally {
			System.out.println("In Finally Block");
			teardownAutomationTest(context, testCaseName);
		}
	}
	
	@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class,dependsOnMethods = { "generateExitTransaction" })
	public void verifyTransactionStatusInCS(ITestContext context, Hashtable<String, String> data) throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);
		try {
			System.out.println("in script");
			txn.verifyTrasactionExportedToCS(data,action);
			testrailResultStatus = true;
		} catch (Exception e) {
			e.printStackTrace();
			testrailResultStatus = false;
			throw new RuntimeException(e);
		} finally {
			System.out.println("In Finally Block");
			teardownAutomationTest(context, testCaseName);
			
		}
	}
	
	@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class,dependsOnMethods = { "verifyTransactionStatusInCS" })
	public void verifyTransactionStatusInOYBO(ITestContext context, Hashtable<String, String> data) throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);
		try {
			System.out.println("in script");
			txnDetailsMap = txn.verifyTrasactionExportedToOYBO(data,action);
			txn.verifyFileSentToMobile(data, action,txnDetailsMap.get("FileReceivedId"));
			testrailResultStatus = true;
		} catch (Exception e) {
			e.printStackTrace();
			testrailResultStatus = false;
			throw new RuntimeException(e);
		} finally {
			System.out.println("In Finally Block");
			teardownAutomationTest(context, testCaseName);
		}
	}
	
	@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class,dependsOnMethods = { "verifyTransactionStatusInOYBO" })
	public void verifyBalanceUpdatedOnApp(ITestContext context,Hashtable<String, String> data)
			throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);

		try {
				System.out.println("in script");
			    pyag.verifyBalanceUpdatedtoMobile(context,data,action,txnDetailsMap.get("FinalBalance"));
			    testrailResultStatus = true;
		} catch (Exception e) {
			e.printStackTrace();
			testrailResultStatus = false;
			throw new RuntimeException(e);
		} finally {
			System.out.println("In Finally Block");
			teardownAutomationTest(context, testCaseName);
		}
	}
	
	@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class,dependsOnMethods = { "verifyTransactionStatusInOYBO" })
	public void verifyJourneyHistoryUpdatedOnApp(ITestContext context,Hashtable<String, String> data)
			throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);

		try {
				System.out.println("in script");
			    pyag.verifyJourneyHistory(context,data,action);
			    testrailResultStatus = true;
		} catch (Exception e) {
			e.printStackTrace();
			testrailResultStatus = false;
			throw new RuntimeException(e);
		} finally {
			System.out.println("In Finally Block");
			teardownAutomationTest(context, testCaseName);
		}
	}
	
	@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class,dependsOnMethods = { "verifyBalanceUpdatedOnApp" })
	public void verifyAppLogOut(ITestContext context,Hashtable<String, String> data)
			throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);

		try {
				System.out.println("in script");
				logout.appLogOut(context,action);
				testrailResultStatus = true;
		} catch (Exception e) {
			e.printStackTrace();
			testrailResultStatus = false;
			throw new RuntimeException(e);
		} finally {
			System.out.println("In Finally Block");
			teardownAutomationTest(context, testCaseName);
		}
	}
	
	@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class,dependsOnMethods = { "verifyAppLogOut" })
	public void updateResultsToTestrail(ITestContext context,Hashtable<String, String> data)
			throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);
		String CaseID = data.get("TestRailCaseId");

		try {
				System.out.println("in script");
				if (testrailResultStatus==true) {
					testrailResultComment = "Successfully Created End to End PAYG Transaction";
				}
				else{
					testrailResultComment = "Failed in creating End to End PAYG Transaction. Please refer the Results folder under the project workspace";
				}
				//TestRailUtil.updateResultsOfTest(AppConstants.TestRailRunId, CaseID, testrailResultStatus, testrailResultComment);
		} catch (Exception e) {
			e.printStackTrace();
			testrailResultStatus = false;
			throw new RuntimeException(e);
		} finally {
			System.out.println("In Finally Block");
			teardownAutomationTest(context, testCaseName);
		}
	}


}
*/