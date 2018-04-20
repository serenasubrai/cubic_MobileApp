package com.tfl.appium.lib;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.ITestContext;

import com.cubic.appiumaction.AppiumActions;
import com.cubic.genericutils.FileUtil;
import com.cubic.genericutils.JsonUtil;
import com.tfl.appium.page.HomePage;
import com.tfl.appium.page.addTravelCardPage;
import com.tfl.appium.page.topupCardPage;

public class OysterAddTravelCard{
	  
	OysterPayAsYouGo swipe = new OysterPayAsYouGo();
	/**
	 * 
	 * @param data
	 * @throws Throwable
	 */

/**
 * This method is to Add a annual travel card when supplied with desired parameters.
 * @param context
 * @param action
 * @throws Throwable
 */
	public void addAnualTravelCard(ITestContext context,AppiumActions action) throws Throwable{
		HomePage.homePageLocators(context);
		if(action.isVisibleWithOutReport(HomePage.myCards, "myCards",true)){
			String expText= action.getText(HomePage.myCards, "myCards");
			action.assertTextStringMatching(expText, "My Cards");
		}
		//action.WaitforTextPresent(HomePage.myCards, "myCards", "My Cards");
		action.click(HomePage.imageView, "imageView");
		addTravelCardPage.travelCardPageLocators(context);
		action.click(addTravelCardPage.buyBtn, "buyBtn");
		action.click(addTravelCardPage.travelcardType, "travelcardType");
		action.click(addTravelCardPage.annualLabel, "annualLabel");
	}

/**
 * 	This method is for selecting From Zone and To Zone while adding annual,monthly or weekly travel cards when supplied with desired parameters.
 * @param context
 * @param action
 * @throws Throwable
 */
	public void selectZone(ITestContext context,AppiumActions action) throws Throwable{
		for(int i=1; i<=10; i++){
			action.click(addTravelCardPage.fromZone, "fromZone");
			Thread.sleep(1000);
			action.click(addTravelCardPage.chooseZoneValue(context,FileUtil.generateRandomNumber(11, 1)+""), "fromZoneValue");
			action.click(addTravelCardPage.toZone, "toZone");
			Thread.sleep(1000);
			action.click(addTravelCardPage.chooseZoneValue(context,FileUtil.generateRandomNumber(11, 1)+""), "toZoneValue");
			action.click(addTravelCardPage.startDate, "startDate");
			Thread.sleep(1000);
			action.click(addTravelCardPage.confirm, "confirm");
			action.click(addTravelCardPage.next, "next");
			Thread.sleep(3000);
			if (!(action.isVisibleWithOutReport(addTravelCardPage.noProductMsg, "No Product Found",true))) {
				break;
			}
			else{
				action.click(addTravelCardPage.OKBtn, "OKBtn");
			}
		}

	}

/**
 * This method is for selecting Zone1 to Zon2e while adding annual,monthly or weekly travel cards when supplied with desired parameters.
 * This method also returns the Start Date of a travel card.	
 * @param context
 * @param startDate
 * @param fromZone
 * @param toZone
 * @param action
 * @return Start Date of a travel card
 * @throws Throwable
 */
	public String selectZoneOneToTwo(ITestContext context,String startDate,String fromZone, String toZone,AppiumActions action) throws Throwable{
		String selectedStartDate = null;
		int waitConuter=0;
		
		try {
			action.click(addTravelCardPage.fromZone, "fromZone");
			Thread.sleep(1000);
			action.click(addTravelCardPage.chooseZoneValue(context, fromZone), "fromZoneValue");
			action.click(addTravelCardPage.toZone, "toZone");
			Thread.sleep(1000);
			action.click(addTravelCardPage.chooseZoneValue(context, toZone), "toZoneValue");
			action.click(addTravelCardPage.startDate, "startDate");
			Thread.sleep(1000);
			if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")&&!(startDate.equalsIgnoreCase(""))&&startDate.equalsIgnoreCase("futureDate")){
				action.click(addTravelCardPage.futureDateValue, "futureDateValue");
			}
			if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")&&!(startDate.equalsIgnoreCase(""))&&startDate.equalsIgnoreCase("futureDate")) {
				action.click(addTravelCardPage.selectFutureDate(context), "futureDateValue");
			}
			/*else if(!(startDate.equalsIgnoreCase(""))&&startDate.equalsIgnoreCase("futureDate")){
				action.click(addTravelCardPage.futureDateValue, "futureDateValue");
			}*/
			//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]
			
			action.click(addTravelCardPage.confirm, "confirm");
			selectedStartDate = action.getText(addTravelCardPage.selectedStartDate, "selectedStartDate");
			action.click(addTravelCardPage.next, "next");
			while (action.isVisibleWithOutReport(addTravelCardPage.pleaseWait, "pleaseWait",true)) {
				waitConuter++;
				if (waitConuter==20){
					action.failureReport("Unable to proceed futher..", "Unable to proceed futher..");
					throw new RuntimeException();
				}
				Thread.sleep(1000);
				continue;
			}
			
			if (action.isVisibleWithOutReport(addTravelCardPage.continueBtnForCheckPurchase, "continueBtnForCheckPurchase",true)) {
				action.click(addTravelCardPage.continueBtnForCheckPurchase, "continueBtnForCheckPurchase");
			}
			else if (action.isVisibleWithOutReport(addTravelCardPage.OKBtn, "OKBtn",true)) {
				action.click(addTravelCardPage.OKBtn, "OKBtn");
				action.failureReport("Verify Travel card is selected", "Unable to select travel card");
				//System.exit(1);
			}
		} catch (Exception e) {
			throw e;
			// TODO: handle exception
		}
		return selectedStartDate;
	}
	
/**
 * This method is to Add a monthly travel card when supplied with desired parameters.	
 * @param context
 * @param action
 * @throws Throwable
 */
	public void addMonthlyTravelCard(ITestContext context,AppiumActions action) throws Throwable{
		HomePage.homePageLocators(context);
		if(action.isVisibleWithOutReport(HomePage.myCards, "myCards",true)){
			String expText= action.getText(HomePage.myCards, "myCards");
			action.assertTextStringMatching(expText, "My Cards");
		}
		//action.WaitforTextPresent(HomePage.myCards, "myCards", "My Cards");
		action.click(HomePage.imageView, "imageView");
		addTravelCardPage.travelCardPageLocators(context);
		action.click(addTravelCardPage.buyBtn, "buyBtn");
		action.click(addTravelCardPage.travelcardType, "travelcardType");
		action.click(addTravelCardPage.monthLabel, "monthLabel");
	}
	
/**
 * This method is to Add a weekly travel card when supplied with desired parameters.	
 * @param context
 * @param data
 * @param action
 * @throws Throwable
 */
	public void addWeeklyTravelCard(ITestContext context,Hashtable<String, String> data,AppiumActions action) throws Throwable{


		String TransactionHeaderFile = data.get("TransactionHeader");
		String TransactionData =FileUtil.readFile(TransactionHeaderFile);
		String cardNumber = data.get("PrestigeCardNumber");
		String oysterCardVisibleNumberOnApp = "000"+cardNumber+JsonUtil.getJsonElement(TransactionData, "$."+cardNumber+".NTMNoCheckDigits");

		HomePage.homePageLocators(context);
		if(action.isVisibleWithOutReport(HomePage.myCards, "myCards",true)){
			String expText= action.getText(HomePage.myCards, "myCards");
			action.assertTextStringMatching(expText, "My Cards");
		}
		//action.WaitforTextPresent(HomePage.myCards, "myCards", "My Cards");
		String expText= action.getText(HomePage.myCards, "myCards");
		action.assertTextStringMatching(expText, "My Cards");
		
		/*while(!(action.getText(HomePage.oysterCardNumber, "oysterCardNumber").equalsIgnoreCase(oysterCardVisibleNumberOnApp))){
			action.swipeRightToLeft();
		}*/
		for (int i = 0; i < 6; i++) {
			if (!(action.getText(HomePage.oysterCardNumber, "oysterCardNumber").equalsIgnoreCase(oysterCardVisibleNumberOnApp))) {
				action.swipeRightToLeft();
			}
			else{
				break;
			}
		}
		for (int i = 0; i < 6; i++) {
			if (!(action.getText(HomePage.oysterCardNumber, "oysterCardNumber").equalsIgnoreCase(oysterCardVisibleNumberOnApp))) {
				action.SwipeLeftToRight();
			}
			else{
				break;
			}
		}

		int counterValue = 0;
		action.WaitforTextPresent(HomePage.myCards, "myCards", "My Cards");
		action.click(HomePage.imageView, "imageView");
		addTravelCardPage.travelCardPageLocators(context);
		action.click(addTravelCardPage.buyBtn, "buyBtn");
		while (!(action.isVisibleWithOutReport(addTravelCardPage.travelcardType, "travelcardType",true))) {
			counterValue++;
			action.click(addTravelCardPage.travelcards, "travelcards");	
			if (counterValue==3) {
				break;
			}
		}
		counterValue=0;
		while (!(action.isVisibleWithOutReport(addTravelCardPage.weekLabel, "weekLabel",true))) {
			counterValue++;
			action.click(addTravelCardPage.travelcardType, "travelcardType");	
			if (counterValue==3) {
				break;
			}
		}
		action.click(addTravelCardPage.weekLabel, "weekLabel");
	}

/**
 * This method is to Add a travel card based on the travel card type supplied in parameter.	
 * @param context
 * @param data
 * @param travlCardType
 * @param action
 * @throws Throwable
 */
	@SuppressWarnings("static-access")
	public void addTravelCard(ITestContext context,Hashtable<String, String> data,String travlCardType,AppiumActions action) throws Throwable{

		String TransactionHeaderFile = data.get("TransactionHeader");
		String TransactionData =FileUtil.readFile(TransactionHeaderFile);
		String cardNumber = data.get("PrestigeCardNumber");
		String oysterCardVisibleNumberOnApp = "000"+cardNumber+JsonUtil.getJsonElement(TransactionData, "$."+cardNumber+".NTMNoCheckDigits");

		HomePage.homePageLocators(context);
		if(action.isVisibleWithOutReport(HomePage.myCards, "myCards",true)){
			String expText= action.getText(HomePage.myCards, "myCards");
			action.assertTextStringMatching(expText, "My Cards");
		}
		//action.WaitforTextPresent(HomePage.myCards, "myCards", "My Cards");
		String expText= action.getText(HomePage.myCards, "myCards");
		action.assertTextStringMatching(expText, "My Cards");
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")) {
			if(!context.getCurrentXmlTest().getXmlClasses().toString().contains("TC_FULPAYGTKT_12")){
				swipe.SwipeToNextCardiOS(context, data, action, oysterCardVisibleNumberOnApp);
			}	
			if((action.isVisibleWithOutReport(HomePage.oysterCardBalance, "oysterCardBalance",true))) {
				action.click(HomePage.imageView, "imageView");
				action.swipeFromMidScreenToBottom();
			}
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
			if((action.isVisibleWithOutReport(HomePage.viewFullHistory, "viewFullHistory",true) && context.getCurrentXmlTest().getXmlClasses().toString().contains("TC_FULPAYGTKT_06"))) {
				action.click(HomePage.imageView, "imageView");
			}
		}
		HomePage.homePageLocators(context);
		action.click(HomePage.imageView, "imageView");
		addTravelCardPage.travelCardPageLocators(context);
		action.click(addTravelCardPage.buyBtn, "buyBtn");
		
		while (!(action.isVisibleWithOutReport(addTravelCardPage.travelcardType, "travelcardType",true))) {
			action.click(addTravelCardPage.travelcards, "travelcards");
			if(action.isVisibleWithOutReport(addTravelCardPage.travelcardType,"travelcardType",true)) {
				break;
			}
		}
		while (!(action.isVisibleWithOutReport(addTravelCardPage.weekLabel, "weekLabel",true))) {
			action.click(addTravelCardPage.travelcardType, "travelcardType");	
			if(action.isVisibleWithOutReport(addTravelCardPage.weekLabel,"weekLabel",true)) {
				break;
			}
		}
		
		if(travlCardType.equalsIgnoreCase("weekly")) {
			action.click(addTravelCardPage.weekLabel, "weekLabel");
		}else if(travlCardType.equalsIgnoreCase("monthly")) {
			action.click(addTravelCardPage.monthLabel, "monthLabel");
		}
		else if(travlCardType.equalsIgnoreCase("yearly")) {
			action.click(addTravelCardPage.annualLabel, "annualLabel");
		}
		
	}
}
