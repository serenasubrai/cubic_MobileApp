package com.tfl.appium.lib;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.ITestContext;

import com.cubic.appiumaction.AppiumActions;
import com.cubic.genericutils.DateUtil;
import com.cubic.genericutils.FileUtil;
import com.cubic.genericutils.JsonUtil;
import com.tfl.appium.page.HomePage;
import com.tfl.appium.page.journeyHistoryPage;
import com.tfl.appium.page.topupCardPage;

public class OysterProduct{
	OysterLogin login = new OysterLogin();
	OysterPayAsYouGo swipe = new OysterPayAsYouGo();
	
	/**
	 * This method is used to verify the zones applied for the Travel cards and returns true or false based on the end result.
	 * @param data
	 * @throws Throwable
	 */
	@SuppressWarnings("static-access")
	public boolean  verifyZonesApplied(ITestContext context,Hashtable<String, String> data,AppiumActions action, String zoneRanges) throws Throwable{
		boolean result = false;
		String actualZoneRange="";
		String TransactionHeaderFile = data.get("TransactionHeader");
		String TransactionData =FileUtil.readFile(TransactionHeaderFile);
		String cardNumber = data.get("PrestigeCardNumber");
		String oysterCardVisibleNumberOnApp = "000"+cardNumber+JsonUtil.getJsonElement(TransactionData, "$."+cardNumber+".NTMNoCheckDigits");
		List<String> myList = new ArrayList<String>();
		String latestFinalBalance = "";		
		try {
			HomePage.homePageLocators(context);
			if (!(action.isVisibleWithOutReport(HomePage.hamBurger, "hamBurger",true))) {
				login.switchNetwork(context,data, action);
				login.verifyAppLogin(context,data, action);
			}
			if(action.isVisibleWithOutReport(HomePage.myCards, "myCards",true)){
				String expText= action.getText(HomePage.myCards, "myCards");
				action.assertTextStringMatching(expText, "My cards");
			}
			//action.WaitforTextPresent(HomePage.myCards, "myCards", "My Cards");
			if (action.getText(HomePage.oysterCardNumber, "oysterCardNumber").equalsIgnoreCase(oysterCardVisibleNumberOnApp)) {
				action.swipeFromMidScreenToBottom();
			}
			
			if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")) {
				swipe.SwipeToNextCardiOS(context, data, action, oysterCardVisibleNumberOnApp);
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
				
			}
			/*for (int i = 0; i < 6; i++) {
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
			}*/

			String ExpectedZoneRange = "7 Day Travelcard Zones "+zoneRanges.split("-")[0]+" to "+zoneRanges.split("-")[1]+"";
			action.click(HomePage.imageView, "imageView");
			topupCardPage.topupCardPageLocators(context);
			if (action.isVisibleWithOutReport(topupCardPage.verifyZonesDisplayed(context, ExpectedZoneRange),"zonesDisplayed", true)) {
				 actualZoneRange = action.getText(topupCardPage.verifyZonesDisplayed(context, ExpectedZoneRange),"zonesDisplayed");	
			}	
			
			if (actualZoneRange.equalsIgnoreCase("")) {
				result = false;
			}
			else if (actualZoneRange.trim().contains(ExpectedZoneRange.trim())) {
				action.successReportAppium("Verify the Ticket is updated with Zones to the Mobile App", "Ticket is updated successfully and zones are displayed as '"+actualZoneRange);
				result = true;
			}
			else if(ExpectedZoneRange.trim().contains(actualZoneRange.trim())){
				action.successReportAppium("Verify the Ticket is updated with Zones to the Mobile App", "Ticket is updated successfully and zones are displayed as '"+actualZoneRange);
				result = true;
			}
			else{
				//action.failureReport("Verify the Ticket is updated with Zones to the Mobile App", "Ticket is NOT updated back to the Mobile");
				result = false;
			}
			HomePage.homePageLocators(context);
			action.click(HomePage.closeBtn, "closeBtn");

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;

	}


	/**
	 * This method is used to verify whether the Ticket is updated to the app and returns true or false based on the end result.
	 * @param context
	 * @param data
	 * @param action
	 * @param finalBalance
	 * @param selectedStartDate
	 * @param zoneRanges
	 * @return
	 * @throws Throwable
	 */
	@SuppressWarnings("static-access")
	public boolean  verifyTicketUpdatedtoMobile(ITestContext context,Hashtable<String, String> data,AppiumActions action, String finalBalance,String selectedStartDate, String zoneRanges) throws Throwable{
		int l=1;
		boolean result = false;
		String TransactionHeaderFile = data.get("TransactionHeader");
		String TransactionData =FileUtil.readFile(TransactionHeaderFile);
		String cardNumber = data.get("PrestigeCardNumber");
		String oysterCardVisibleNumberOnApp = "000"+cardNumber+JsonUtil.getJsonElement(TransactionData, "$."+cardNumber+".NTMNoCheckDigits");
		List<String> myList = new ArrayList<String>();
		String latestFinalBalance = "";
		try {
			HomePage.homePageLocators(context);
			topupCardPage.topupCardPageLocators(context);
			if (!(action.isVisibleWithOutReport(HomePage.hamBurger, "hamBurger",true))) {
				login.switchNetwork(context,data, action);
				login.verifyAppLogin(context,data, action);
			}
			//action.WaitforTextPresent(HomePage.myCards, "myCards", "My Cards");
			action.isVisibleWithOutReport(HomePage.myCards, "myCards", true);
			action.swipeFromMidScreenToBottom();
			if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")) {
				if((action.isVisibleWithOutReport(HomePage.oysterCardBalance, "oysterCardBalance",true))) {
					action.click(HomePage.imageView, "imageView");
					action.waitForElementPresent(HomePage.oysterCardBalance, "oysterCardBalance");
					Thread.sleep(2000);
					action.swipeFromMidScreenToBottom();
				}
			}	
			if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")) {
				if(!(action.isVisibleWithOutReport(HomePage.oysterCardBalance, "oysterCardBalance",true))) {
					action.swipeFromMidScreenToBottom();
					finalBalance = swipe.SwipeToNextCardiOS(context, data, action, oysterCardVisibleNumberOnApp);
					action.click(HomePage.imageView, "imageView");
				}else {
					action.click(HomePage.imageView, "imageView");
					action.swipeFromMidScreenToBottom();
					finalBalance = swipe.SwipeToNextCardiOS(context, data, action, oysterCardVisibleNumberOnApp);
				}
				finalBalance = "£"+finalBalance.trim();	
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
				finalBalance = action.getText(HomePage.oysterCardBalance, "oysterCardBalance");
				if (!(finalBalance.contains("£"))) {
					while(finalBalance.length()!=0){
						myList.add(finalBalance.substring(0,2));
						finalBalance = finalBalance.substring(2);
					}
					finalBalance = "£"+myList.get(0)+"."+myList.get(1);	
				}
				action.click(HomePage.imageView, "imageView");
			}
			
			/*while(!(finalBalance==null)&&finalBalance.length()!=0){
				myList.add(finalBalance.substring(0,2));
				finalBalance = finalBalance.substring(2);
			}*/

			/*if (myList.size()!=0) {
				latestFinalBalance = "£"+myList.get(0)+"."+myList.get(1);
			}*/
			String ExpectedZoneRange = "Zones "+zoneRanges.split("-")[0]+" to "+zoneRanges.split("-")[1]+"";
			if(!(action.isVisibleWithOutReport(HomePage.oysterCardBalance, "oysterCardBalance",true))&&context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")) {
				action.click(HomePage.imageView, "imageView");
			}
			
			String actualZoneRange = action.getText(topupCardPage.verifyZonesDisplayed(context, ExpectedZoneRange),"zonesDisplayed");
			if (actualZoneRange.trim().contains(ExpectedZoneRange.trim())) {
				action.successReportAppium("Verify the Ticket is updated with Zones to the Mobile App", "Ticket is updated successfully and zones are displayed as '"+actualZoneRange+"' and the balance is displayed as '"+finalBalance+"'");
				result = true;
			}
			else if(ExpectedZoneRange.trim().contains(actualZoneRange.trim())){
				action.successReportAppium("Verify the Ticket is updated with Zones to the Mobile App", "Ticket is updated successfully and zones are displayed as '"+actualZoneRange+"' and the balance is displayed as '"+finalBalance+"'");
				result = true;
			}
			else{
				action.failureReport("Verify the Ticket is updated with Zones to the Mobile App", "Ticket is NOT updated back to the Mobile");
				result = false;
			}
			if (!(selectedStartDate.equalsIgnoreCase(""))) {
				String startdate = DateUtil.getProperDateFormat(selectedStartDate);
				//String actualTicketStartDate = action.getText(topupCardPage.verifyStartDateDisplayed(context, ExpectedZoneRange, startdate), "Start date");
				//String actualTicketStartDate = action.getText(topupCardPage.verifyStartDateDisplayed(context), "Start date");
				String actualTicketStartDate = action.getText(topupCardPage.getDisplayedStartDate, "Start date");
				
				if (actualTicketStartDate.trim().contains(startdate.trim())) {
					action.successReportAppium("Verify the Start Date is displayed is updated to the Mobile App", "Start date is updated successfully and displayed as '"+actualTicketStartDate+"' and the balance is displayed as '"+finalBalance+"'");
					result = true;
				}
				else if(startdate.trim().contains(actualTicketStartDate.trim())){
					//action.successReportAppium("Verify the Start Date is displayed is updated to the Mobile App", "Start date is updated successfully and displayed as '"+actualTicketStartDate+"' and the balance is displayed as '"+latestFinalBalance+"'");
					action.successReportAppium("Verify the Start Date is displayed is updated to the Mobile App", "Start date is updated successfully and displayed as '"+actualTicketStartDate+"' and the balance is displayed as '"+finalBalance+"'");
					result = true;
				}
				else if (action.isVisible(topupCardPage.verifyExpiryDateDisplayed(context, ExpectedZoneRange), "Expiry Date")) {
					String actualExpiryDate = action.getText(topupCardPage.verifyExpiryDateDisplayed(context, ExpectedZoneRange), "Expiry Date");
					action.successReportAppium("Verify the Expiry Date is displayed is updated to the Mobile App", "Expiry date is updated successfully and displayed as '"+actualExpiryDate+"' and the balance is displayed as '"+finalBalance+"'");
					result = true;
				}
				else{
					action.failureReport("Verify the Start Date is displayed is updated to the Mobile App", "Start Date is NOT updated back to the Mobile");
					result = false;
				}

			}
			else{
				if (action.isVisible(topupCardPage.verifyExpiryDateDisplayed(context, ExpectedZoneRange), "Expiry Date")) {
					String actualExpiryDate = action.getText(topupCardPage.verifyExpiryDateDisplayed(context, ExpectedZoneRange), "Expiry Date");
					action.successReportAppium("Verify the Expiry Date is displayed is updated to the Mobile App", "Expiry date is updated successfully and displayed as '"+actualExpiryDate+"' and the balance is displayed as '"+finalBalance+"'");
					result = true;
				} 
				else{
					action.failureReport("Verify the Expiry Date is displayed is updated to the Mobile App", "Expiry Date is NOT updated back to the Mobile");
					result = false;
				}

			}
			HomePage.homePageLocators(context);
			action.click(HomePage.closeBtn, "closeBtn");

		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;

	}

	/**
	 * This method is used to verify whether multiple tickets are updated to the app when bought multiple tickets.
	 * @param context
	 * @param action
	 * @param data
	 * @param startDate1
	 * @param startDate2
	 * @param zoneRanges1
	 * @param zoneRanges2
	 * @param finalBalance
	 * @throws Throwable
	 */
	public void verifymultipleTicketsUpdatedtoMobile(ITestContext context,AppiumActions action,Hashtable<String, String> data,String startDate1, String startDate2, String zoneRanges1, String zoneRanges2,String finalBalance) throws Throwable{
		String TransactionHeaderFile = data.get("TransactionHeader");
		String TransactionData =FileUtil.readFile(TransactionHeaderFile);
		String cardNumber = data.get("PrestigeCardNumber");
		String oysterCardVisibleNumberOnApp = "00"+cardNumber+JsonUtil.getJsonElement(TransactionData, "$."+cardNumber+".NTMNoCheckDigits");
		List<String> myList = new ArrayList<String>();
		String latestFinalBalance = "";
		try {
			int a= 3;
			HomePage.homePageLocators(context);
			if (!(action.isVisibleWithOutReport(HomePage.hamBurger, "hamBurger",true))) {
				login.switchNetwork(context,data, action);
				login.verifyAppLogin(context,data, action);
			}
			action.WaitforTextPresent(HomePage.myCards, "myCards", "My Cards");
			/*while(!(action.getText(HomePage.oysterCardNumber, "oysterCardNumber").equalsIgnoreCase(oysterCardVisibleNumberOnApp))){
				action.swipeRightToLeft();
			}*/
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
			if (action.getText(HomePage.oysterCardNumber, "oysterCardNumber").equalsIgnoreCase(oysterCardVisibleNumberOnApp)) {
				action.swipeFromMidScreenToBottom();
			}

			while(!(finalBalance==null)&&finalBalance.length()!=0){
				myList.add(finalBalance.substring(0,2));
				finalBalance = finalBalance.substring(2);
			}

			//action.successReportAppium("Verify the Balance updated back to the Mobile App", "Balance is successfully updated back to the Mobile");
			if (myList.size()!=0) {
				latestFinalBalance = "£"+myList.get(0)+"."+myList.get(1);
			}

			String ExpectedZoneRange1 = "Zones "+zoneRanges1.split("-")[0]+" to "+zoneRanges1.split("-")[1]+"";
			action.click(HomePage.imageView, "imageView");
			topupCardPage.topupCardPageLocators(context);
			String actualZoneRange = action.getText(topupCardPage.verifyZonesDisplayed(context, ExpectedZoneRange1),"zonesDisplayed");
			if (actualZoneRange.trim().contains(ExpectedZoneRange1.trim())) {
				action.successReportAppium("Verify the Ticket is updated with Zones to the Mobile App", "Ticket is updated successfully and zones are displayed as '"+actualZoneRange+"' and the balance is displayed as '"+latestFinalBalance+"'");
			}
			else if(ExpectedZoneRange1.trim().contains(actualZoneRange.trim())){
				action.successReportAppium("Verify the Ticket is updated with Zones to the Mobile App", "Ticket is updated successfully and zones are displayed as '"+actualZoneRange+"' and the balance is displayed as '"+latestFinalBalance+"'");
			}
			else{
				action.failureReport("Verify the Ticket is updated with Zones to the Mobile App", "Ticket is NOT updated back to the Mobile");
			}
			if (!(startDate1.equalsIgnoreCase(""))) {
				String modifystartDate1 = DateUtil.changeDateFormat(startDate1, "dd-MMM-yyyy", "dd/MM/yyyy");
				String actualTicketStartDate = action.getText(topupCardPage.verifyStartDateDisplayed(context, ExpectedZoneRange1, modifystartDate1), "Start date");
				if (actualTicketStartDate.trim().contains(modifystartDate1.trim())) {
					action.successReportAppium("Verify the Start Date is displayed is updated to the Mobile App", "Start date is updated successfully and displayed as '"+actualTicketStartDate+"' and the balance is displayed as '"+latestFinalBalance+"'");
				}
				else if(modifystartDate1.trim().contains(actualTicketStartDate.trim())){
					action.successReportAppium("Verify the Start Date is displayed is updated to the Mobile App", "Start date is updated successfully and displayed as '"+actualTicketStartDate+"' and the balance is displayed as '"+latestFinalBalance+"'");
				}
				else{
					action.failureReport("Verify the Start Date is displayed is updated to the Mobile App", "Start Date is NOT updated back to the Mobile");
				}

			}
			else{
				if (action.isVisible(topupCardPage.verifyExpiryDateDisplayed(context, ExpectedZoneRange1), "Expiry Date")) {
					String actualExpiryDate = action.getText(topupCardPage.verifyExpiryDateDisplayed(context, ExpectedZoneRange1), "Expiry Date");
					action.successReportAppium("Verify the Expiry Date is displayed is updated to the Mobile App", "Expiry date is updated successfully and displayed as '"+actualExpiryDate+"' and the balance is displayed as '"+latestFinalBalance+"'");
				} 
				else{
					action.failureReport("Verify the Expiry Date is displayed is updated to the Mobile App", "Expiry Date is NOT updated back to the Mobile");
				}
				HomePage.homePageLocators(context);

			}

			String ExpectedZoneRange2 = "Zones "+zoneRanges2.split("-")[0]+" to "+zoneRanges2.split("-")[1]+"";
			action.click(HomePage.imageView, "imageView");
			topupCardPage.topupCardPageLocators(context);
			String actualZoneRange2 = action.getText(topupCardPage.verifyZonesDisplayed(context, ExpectedZoneRange2),"zonesDisplayed");
			if (actualZoneRange2.trim().contains(ExpectedZoneRange2.trim())) {
				action.successReportAppium("Verify the Ticket is updated with Zones to the Mobile App", "Ticket is updated successfully and zones are displayed as '"+actualZoneRange2+"' and the balance is displayed as '"+latestFinalBalance+"'");
			}
			else if(ExpectedZoneRange2.trim().contains(actualZoneRange2.trim())){
				action.successReportAppium("Verify the Ticket is updated with Zones to the Mobile App", "Ticket is updated successfully and zones are displayed as '"+actualZoneRange2+"' and the balance is displayed as '"+latestFinalBalance+"'");
			}
			else{
				action.failureReport("Verify the Ticket is updated with Zones to the Mobile App", "Ticket is NOT updated back to the Mobile");
			}
			if (!(startDate2.equalsIgnoreCase(""))) {
				String modifystartDate2 = DateUtil.changeDateFormat(startDate2, "dd-MMM-yyyy", "dd/MM/yyyy");
				String actualTicketStartDate2 = action.getText(topupCardPage.verifyStartDateDisplayed(context, ExpectedZoneRange2, modifystartDate2), "Start date");
				if (actualTicketStartDate2.trim().contains(modifystartDate2.trim())) {
					action.successReportAppium("Verify the Start Date is displayed is updated to the Mobile App", "Start date is updated successfully and displayed as '"+actualTicketStartDate2+"' and the balance is displayed as '"+latestFinalBalance+"'");
				}
				else if(modifystartDate2.trim().contains(actualTicketStartDate2.trim())){
					action.successReportAppium("Verify the Start Date is displayed is updated to the Mobile App", "Start date is updated successfully and displayed as '"+actualTicketStartDate2+"' and the balance is displayed as '"+latestFinalBalance+"'");
				}
				else{
					action.failureReport("Verify the Start Date is displayed is updated to the Mobile App", "Start Date is NOT updated back to the Mobile");
				}

			}
			else{
				if (action.isVisible(topupCardPage.verifyExpiryDateDisplayed(context, ExpectedZoneRange2), "Expiry Date")) {
					String actualExpiryDate2 = action.getText(topupCardPage.verifyExpiryDateDisplayed(context, ExpectedZoneRange2), "Expiry Date");
					action.successReportAppium("Verify the Expiry Date is displayed is updated to the Mobile App", "Expiry date is updated successfully and displayed as '"+actualExpiryDate2+"' and the balance is displayed as '"+latestFinalBalance+"'");
				} 
				else{
					action.failureReport("Verify the Expiry Date is displayed is updated to the Mobile App", "Expiry Date is NOT updated back to the Mobile");
				}
			}
			HomePage.homePageLocators(context);
			action.click(HomePage.closeBtn, "closeBtn");

		} catch (Exception e) {
			// TODO: handle exception
		}

	}




}
