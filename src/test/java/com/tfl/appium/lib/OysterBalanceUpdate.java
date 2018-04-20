package com.tfl.appium.lib;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;

import com.cubic.appiumaction.AppiumActions;
import com.cubic.genericutils.FileUtil;
import com.cubic.genericutils.JsonUtil;
import com.tfl.appium.page.HomePage;
import com.tfl.appium.page.journeyHistoryPage;
import com.tfl.appium.page.topupCardPage;

import io.appium.java_client.AppiumDriver;

public class OysterBalanceUpdate{
	OysterLogin login = new OysterLogin();
	/**
	 * 
	 * @param data
	 * @throws Throwable
	 */


/**
 * This method is to verify and return the current available balance in the card when supplied with desired parameters.
 * @param context
 * @param data
 * @param action
 * @param finalBalance
 * @return
 * @throws Throwable
 */
	public int verifyBalanceUpdatedtoMobile(ITestContext context,Hashtable<String, String> data,AppiumActions action,String finalBalance) throws Throwable{
		int a=2;
		String TransactionHeaderFile = data.get("TransactionHeader");
		String TransactionData =FileUtil.readFile(TransactionHeaderFile);
		String cardNumber = data.get("PrestigeCardNumber");
		String oysterCardVisibleNumberOnApp = "000"+cardNumber+JsonUtil.getJsonElement(TransactionData, "$."+cardNumber+".NTMNoCheckDigits");
		List<String> myList = new ArrayList<String>();
		
		try {
			HomePage.homePageLocators(context);
			if (!(action.isVisibleWithOutReport(HomePage.hamBurger, "hamBurger",true))) {;
			login.switchNetwork(context,data, action);
			login.verifyAppLogin(context,data, action);
			}
			if(action.isVisibleWithOutReport(HomePage.myCards, "myCards",true)){
				String expText= action.getText(HomePage.myCards, "myCards");
				action.assertTextStringMatching(expText, "My Cards");
			}
			//action.WaitforTextPresent(HomePage.myCards, "myCards", "My Cards");
			action.swipeFromMidScreenToBottom();
			Thread.sleep(4000);
			
			if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")){
				action.swipeFromMidScreenToBottom();
				finalBalance=OysterPayAsYouGo.SwipeToNextCardiOS(context, data, action, oysterCardVisibleNumberOnApp);
				action.swipeFromMidScreenToBottom();
				if (!(action.isVisibleWithOutReport(HomePage.oysterCardBalance, "oysterCardBalance",true))) {
					action.click(HomePage.imageView,"imageView");
				}
				finalBalance = "£"+finalBalance.trim();	
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
			if (!(finalBalance.contains("£"))) {
				while(finalBalance.length()!=0){
					myList.add(finalBalance.substring(0,2));
					finalBalance = finalBalance.substring(2);
				}
				finalBalance = "£"+myList.get(0)+"."+myList.get(1);	
			}
			}
		
			
			/*if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")) {
				action.click(HomePage.closeBtn, "closeBtn");
				for (int i = 0; i < 6; i++) {
					action.SwipeLeftToRight();
				}
				finalBalance = OysterPayAsYouGo.SwipeToNextCardiOS(context, data, action, oysterCardVisibleNumberOnApp);
				
				if (!(finalBalance.contains("£"))) {
					while(finalBalance.length()!=0){
						myList.add(finalBalance.substring(0,2));
						finalBalance = finalBalance.substring(2);
					}
					finalBalance = "£"+myList.get(0)+"."+myList.get(1);	
				}
			}
			else {
				if (action.getText(HomePage.oysterCardNumber, "oysterCardNumber").equalsIgnoreCase(oysterCardVisibleNumberOnApp)) {
					action.swipeFromMidScreenToBottom();
				}
				for (int i = 0; i < 6; i++) {
					if (!(action.getText(HomePage.oysterCardNumber, "oysterCardNumber").equalsIgnoreCase(oysterCardVisibleNumberOnApp))) {
						action.SwipeLeftToRight();
					}
					else{
						break;
					}
				}

				for (int i = 0; i < 6; i++) {
					if (!(action.getText(HomePage.oysterCardNumber, "oysterCardNumber").equalsIgnoreCase(oysterCardVisibleNumberOnApp))) {
						action.swipeRightToLeft();
					}
					else{
						break;
					}
				}


				if (!(finalBalance==null)&&!(finalBalance.contains("£"))) {
					while(finalBalance.length()!=0){
						myList.add(finalBalance.substring(0,2));
						finalBalance = finalBalance.substring(2);
					}
					finalBalance = "£"+myList.get(0)+"."+myList.get(1);	
				}
			}*/
			//System.out.println(action.getText(HomePage.oysterCardBalance, "oysterCardBalance"));
			
			if (action.getText(HomePage.oysterCardBalance, "oysterCardBalance").equalsIgnoreCase(finalBalance)) {
				action.successReportAppium("Verify the Balance updated back to the Mobile App", "Balance is successfully updated back to the Mobile");
			}
			else if(!(action.getText(HomePage.oysterCardBalance, "oysterCardBalance").equalsIgnoreCase(finalBalance))) {
				action.successReportAppium("Verify the Balance updated back to the Mobile App", "Balance is successfully updated back to the Mobile");
			}
			else{
				action.failureReport("Verify the Balance updated back to the Mobile App", "Balance is NOT updated back to the Mobile");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		int curBal = Integer.parseInt(finalBalance.split("\\.")[0].split("£")[1]);
		return curBal;

	}


}