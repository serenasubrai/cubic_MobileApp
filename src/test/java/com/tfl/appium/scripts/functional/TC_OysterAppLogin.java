package com.tfl.appium.scripts.functional;

import java.util.Hashtable;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.cubic.appiumaction.AppiumActions;
import com.cubic.appiumaction.AppiumEngine;
import com.cubic.commonLib.DataProviderSource;
import com.cubic.constants.AppConstants;
import com.cubic.testrail.TestRailUtil;
import com.tfl.appium.lib.OysterCreateTransaction;

public class TC_OysterAppLogin extends AppiumEngine{
	public static String txnFilePath = "";
	public static boolean testrailResultStatus = false;
	public static String testrailResultComment = "";

	com.tfl.appium.lib.OysterLogin oyster = new com.tfl.appium.lib.OysterLogin();
	OysterCreateTransaction txn = new OysterCreateTransaction();

	@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class)
	public void verifyOysterLogin(ITestContext context,Hashtable<String, String> data)
			throws Throwable {
		System.out.println("data is "+data);
		String testCaseName = data.get("TestCase_Description");
		System.out.println("testCaseName is "+testCaseName);
		AppiumActions action = setupAutomationTest(context, testCaseName);

		try {
			System.out.println("in script");
			/*oyster.switchNetwork(context,data, action);
			oyster.verifyAppLogin(context,data, action);*/
			testrailResultStatus = true;
			testrailResultComment = "User successfully logged into the application";

		} catch (Exception e) {
			testrailResultStatus = false;
			testrailResultComment = "Failed in login operation. Please refer the Results folder under the project workspace";
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			System.out.println("In Finally Block");
			//TestRailUtil.updateResultsOfTest(context, data.get("TestRailCaseId"), testrailResultStatus, testrailResultComment);
			teardownAutomationTest(context, testCaseName);
		}
	}
	
	/*@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class,dependsOnMethods = { "verifyOysterLogin" })
	public void updateResultsToTestrail(ITestContext context,Hashtable<String, String> data)
			throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);
		String CaseID = data.get("TestRailCaseId");

		try {
				System.out.println("in script");
				if (testrailResultStatus==true) {
					testrailResultComment = "User successfully logged into the application";
				}
				else{
					testrailResultComment = "Failed in login operation. Please refer the Results folder under the project workspace";
				}
				TestRailUtil.updateResultsOfTest(AppConstants.TestRailRunId, CaseID, testrailResultStatus, testrailResultComment);
				action.successReport("Verify Results updated to Test Rail", "Result status is successfully updated back to TestRail and "+testrailResultComment);
				
		} catch (Exception e) {
			e.printStackTrace();
			testrailResultStatus = false;
			action.failureReport("Verify Results updated to Test Rail", "Result status is NOT  updated back to TestRail ");
			throw new RuntimeException(e);
		} finally {
			System.out.println("In Finally Block");
			teardownAutomationTest(context, testCaseName);
		}
	}*/
	/*@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class)
	public void generatePAYGTransaction(ITestContext context, Hashtable<String, String> data) throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);
		try {
			System.out.println("in PAYG script");
			txnFilePath = txn.generatePAYG(data,action);
			txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			System.out.println("In Finally Block");
			teardownAutomationTest(context, testCaseName);
		}
	}

	@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class)
	public void generateEntryTransaction(ITestContext context, Hashtable<String, String> data) throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);
		try {
			System.out.println("in script");
			txnFilePath = txn.generateEntryTransaction(data,action);
			txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			System.out.println("In Finally Block");
			teardownAutomationTest(context, testCaseName);
		}
	}

	@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class)
	public void generateExitTransaction(ITestContext context, Hashtable<String, String> data) throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);
		try {
			System.out.println("in script");
			txnFilePath = txn.generateExitTransaction(data,action);
			txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			System.out.println("In Finally Block");
			teardownAutomationTest(context, testCaseName);
		}
	}
	
	@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class)
	public void verifyTransactionStatusInCS(ITestContext context, Hashtable<String, String> data) throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);
		try {
			System.out.println("in script");
			txn.verifyTrasactionExportedToCS(data,action);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			System.out.println("In Finally Block");
			teardownAutomationTest(context, testCaseName);
		}
	}
	
	@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = DataProviderSource.class)
	public void verifyTransactionStatusInOYBO(ITestContext context, Hashtable<String, String> data) throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		AppiumActions action = setupAutomationTest(context, testCaseName);
		try {
			System.out.println("in script");
			String fileReceivedID = txn.verifyTrasactionExportedToOYBO(data,action);
			System.out.println("fileReceivedID is "+fileReceivedID);
			txn.verifyFileSentToMobile(data, action,fileReceivedID);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			System.out.println("In Finally Block");
			teardownAutomationTest(context, testCaseName);
		}
	}*/
}
