package com.tfl.appium.lib;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.ITestContext;

import com.cubic.appiumaction.AppiumActions;
import com.cubic.genericutils.FileUtil;
import com.cubic.genericutils.JsonUtil;
import com.tfl.appium.page.HomePage;
import com.tfl.appium.page.journeyHistoryPage;
import com.tfl.appium.page.topupCardPage;

public class OysterPayAsYouGo{
	OysterLogin login = new OysterLogin();
	OysterCreateTransaction txn = new OysterCreateTransaction();
	public static String finalBalance= null;
	/**
	 * This method is used to swipe to the next card until the desired card is shown in the iOS device app.
	 * @param data
	 * @throws Throwable
	 */
	public static String SwipeToNextCardiOS(ITestContext context,Hashtable<String, String> data,AppiumActions action, String oysterCardNumber) {
		String balanceOnCard = null;
		try {
			String cardDetailsScreen = action.getText(HomePage.oysterCardNumber, "oysterCardNumber");
			String expectedCard = oysterCardNumber;
			String [] strArr = cardDetailsScreen.split("£");
			System.out.println("array size is "+strArr.length);
			String CardIndexValue = null;
			for (int i = 0; i < strArr.length; i++) {
				if (strArr[i].contains(expectedCard)) {
					//System.out.println("true "+strArr[i]);
					//System.out.println("index is "+i);
					CardIndexValue = i+"";
					//System.out.println("balance is "+strArr[i+1].substring(0, 5));
					balanceOnCard = strArr[i+1].substring(0, 5);
					break;
				}
			}

			switch(CardIndexValue) {
			case "0" :
				action.SwipeLeftToRight();
				action.SwipeLeftToRight();
				action.SwipeLeftToRight();
				action.SwipeLeftToRight();
				action.SwipeLeftToRight();
				action.SwipeLeftToRight();
				action.SwipeLeftToRight();
				action.SwipeLeftToRight();
				break;
			case "1" :
				action.swipeRightToLeft();
				break;
			case "2" :
				action.swipeRightToLeft();
				action.swipeRightToLeft();
				break;
			case "3" :
				action.swipeRightToLeft();
				action.swipeRightToLeft();
				action.swipeRightToLeft();
				break;
			case "4" :
				action.swipeRightToLeft();
				action.swipeRightToLeft();
				action.swipeRightToLeft();
				action.swipeRightToLeft();
				break;
			case "5" :
				action.swipeRightToLeft();
				action.swipeRightToLeft();
				action.swipeRightToLeft();
				action.swipeRightToLeft();
				action.swipeRightToLeft();
				break;

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return balanceOnCard;
	}
	
	/**
	 * This method is to perform a TopUp and it returns the TopUp amount.
	 * @param context
	 * @param data
	 * @param action
	 * @param topUP
	 * @return
	 * @throws Throwable
	 */
	public String topup(ITestContext context,Hashtable<String, String> data,AppiumActions action, String topUP) throws Throwable{
		String topupAmount = "";
		//topUP = "35";
		String TransactionHeaderFile = data.get("TransactionHeader");
		String TransactionData =FileUtil.readFile(TransactionHeaderFile);
		String cardNumber = data.get("PrestigeCardNumber");
		String oysterCardVisibleNumberOnApp = "000"+cardNumber+JsonUtil.getJsonElement(TransactionData, "$."+cardNumber+".NTMNoCheckDigits");
		try {
			HomePage.homePageLocators(context);
			if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")) {
				finalBalance =SwipeToNextCardiOS(context, data, action, oysterCardVisibleNumberOnApp);
			}

			else {
				Thread.sleep(2000);
				for (int i = 0; i < 8; i++) {
					if (!(action.getText(HomePage.oysterCardNumber, "oysterCardNumber").equalsIgnoreCase(oysterCardVisibleNumberOnApp))) {
						action.swipeRightToLeft();
					}
					else{
						break;
					}
				}
				
				if (!(action.getText(HomePage.oysterCardNumber, "oysterCardNumber").equalsIgnoreCase(oysterCardVisibleNumberOnApp))) {
					throw new RuntimeException("Unable to find the card");
				}
				finalBalance =action.getText(HomePage.oysterCardBalance, "oysterCardBalance");
			}
			//action.swipeFromMidScreenToBottom();
			if(!context.getCurrentXmlTest().getXmlClasses().toString().contains("TC_Error_03_Error70PPVAMOUNTWOULDEXCEEDMAXALLOWEDPPVMobileAppDoesNotAllowPAYGAmountToGoOver£90")){
				txn.verifyBalanceLimit(context, data, action, finalBalance);
			}
			if(action.isVisibleWithOutReport(HomePage.myCards, "myCards",true)){
				String expText= action.getText(HomePage.myCards, "myCards");
				action.assertTextStringMatching(expText, "My Cards");
			}
			/*action.WaitforTextPresent(HomePage.myCards, "myCards", "My Cards");
			action.WaitforTextPresent(HomePage.myCards, "myCards", "My Cards");*/
			//action.click(HomePage.imageViewOptions, "imageViewOptions");
			if (!(action.isVisibleWithOutReport(HomePage.topUp, "topUp", true))) {
				action.click(HomePage.imageViewOptions, "imageViewOptions");	
			}
			action.waitForElementPresent(HomePage.topUp, "topUp");
			action.click(HomePage.topUp, "topUp");
			
			/*action.waitForElementPresent(HomePage.imageView, "imageView");
			action.waitForElementPresent(HomePage.imageViewOptions, "imageViewOptions");
			action.click(HomePage.viewJourneyHistoryOption, "viewJourneyHistoryOption");
			while(!(action.isVisibleWithOutReport(HomePage.topUp, "topUp", true))) {
				action.click(HomePage.imageView, "imageView");
			}*/
			
			topupCardPage.topupCardPageLocators(context);
			switch(topUP) {
			case "10" :
				action.click(topupCardPage.minusBtn, "minusBtn");
				action.click(topupCardPage.minusBtn, "minusBtn");
				break; 

			case "15" :
				action.click(topupCardPage.minusBtn, "minusBtn");
				break; 
			case "20" :
				break; 
			case "25" :
				action.click(topupCardPage.plusBtn, "plusBtn");
				break; 
			case "30" :
				action.click(topupCardPage.plusBtn, "plusBtn");
				action.click(topupCardPage.plusBtn, "plusBtn");
				break; 
			case "35" :
				action.click(topupCardPage.plusBtn, "plusBtn");
				action.click(topupCardPage.plusBtn, "plusBtn");
				action.click(topupCardPage.plusBtn, "plusBtn");

				break; 
			case "40" :
				action.click(topupCardPage.plusBtn, "plusBtn");
				action.click(topupCardPage.plusBtn, "plusBtn");
				action.click(topupCardPage.plusBtn, "plusBtn");
				action.click(topupCardPage.plusBtn, "plusBtn");

				break; 
			case "45" :
				action.click(topupCardPage.plusBtn, "plusBtn");
				action.click(topupCardPage.plusBtn, "plusBtn");
				action.click(topupCardPage.plusBtn, "plusBtn");
				action.click(topupCardPage.plusBtn, "plusBtn");
				action.click(topupCardPage.plusBtn, "plusBtn");

				break; 
			case "50" :
				action.click(topupCardPage.plusBtn, "plusBtn");
				action.click(topupCardPage.plusBtn, "plusBtn");
				action.click(topupCardPage.plusBtn, "plusBtn");
				action.click(topupCardPage.plusBtn, "plusBtn");
				action.click(topupCardPage.plusBtn, "plusBtn");
				action.click(topupCardPage.plusBtn, "plusBtn");
				break; 
			default : 
				action.click(topupCardPage.minusBtn, "minusBtn");
				action.click(topupCardPage.minusBtn, "minusBtn");
			}
			//action.click(topupCardPage.minusBtn, "minusBtn");
			//action.click(topupCardPage.minusBtn, "minusBtn");
			String pagyAmount = action.getText(topupCardPage.paygAmount, "paygAmount");
			String[] ActualBalance = pagyAmount.split("£"); 
			topupAmount =  ActualBalance[1].replace(".","");
			System.out.println("topupAmount is *** "+topupAmount);
			String expectedNewBalance = action.getText(topupCardPage.newBalance, "ExpectedNewBalance");
			if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android") && (!(action.isVisibleWithOutReport(topupCardPage.topupCardNextBtn, "topupCardNextBtn", true)))) {
				action.scrollDownToElement("android:id/content","Next");
			}
			action.click(topupCardPage.topupCardNextBtn, "topupCardNextBtn");
			action.waitForElementPresent(topupCardPage.newBalance, "ActualNewBalance");
			String ActualNewBalance = action.getText(topupCardPage.newBalance, "ActualNewBalance");
			action.assertTrue(expectedNewBalance.equalsIgnoreCase(ActualNewBalance), "Both are Verified Same");


		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

		return topupAmount;

	}

	/**
	 * This method is used to get the balance available and returns the final balance.
	 * @param action
	 * @return
	 */
	public String getFinalBalance(AppiumActions action){
		String fianlBalance="";
		try {
			fianlBalance = action.getText(topupCardPage.newBalance, "fianlBalance");	
		} catch (Exception e) {
			// TODO: handle exception
		}
		return fianlBalance;
	}

	/**
	 * This method is to verify the balance limit available before TopUp and if it's greater than 60 then deducting the balance by creating journey scenarios.
	 * @param context
	 * @param data
	 * @param action
	 * @throws Throwable
	 */
	public void verifyBalanceLimit(ITestContext context,Hashtable<String, String> data,AppiumActions action) throws Throwable{
		try {
			String TransactionHeaderFile = data.get("TransactionHeader");
			String TransactionData =FileUtil.readFile(TransactionHeaderFile);
			String cardNumber = data.get("PrestigeCardNumber");
			String finalBalance = null;
			int ActualfinalBalance = 0;

			String oysterCardVisibleNumberOnApp = "000"+cardNumber+JsonUtil.getJsonElement(TransactionData, "$."+cardNumber+".NTMNoCheckDigits");
			action.swipeFromMidScreenToBottom();
			List<String> myList = new ArrayList<String>();
			if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")){
				finalBalance = OysterPayAsYouGo.SwipeToNextCardiOS(context, data, action, oysterCardVisibleNumberOnApp);
			}
			else {
				for (int i = 0; i < 6; i++){
					if (!(action.getText(HomePage.oysterCardNumber, "oysterCardNumber").equalsIgnoreCase(oysterCardVisibleNumberOnApp))) {
						action.swipeRightToLeft();
					}
					else{
						break;
					}
				}
				finalBalance = action.getText(HomePage.oysterCardBalance, "oysterCardBalance");
			}
			ActualfinalBalance= Integer.parseInt(finalBalance.split(".")[0].split("£")[1]);
			if (ActualfinalBalance>=60) {
				action.successReport("Verify the existing balance limit in the card", "Balance limit is more than 60 hence executing the balance deduction program");
				for (int i = 0; i < 2; i++) {
					txn.createJourneyScenarios(data, action);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}


}
