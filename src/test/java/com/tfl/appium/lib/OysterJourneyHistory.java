package com.tfl.appium.lib;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.testng.ITestContext;
import com.cubic.appiumaction.AppiumActions;
import com.cubic.genericutils.FileUtil;
import com.cubic.genericutils.JsonUtil;
import com.tfl.appium.page.HomePage;
import com.tfl.appium.page.journeyHistoryPage;



public class OysterJourneyHistory{
	public static int journeyHistoryCounter = 2;
	public static int iOSJourneyHistoryCounter = 0;
	OysterLogin login = new OysterLogin();
	

	/**
	 * 
	 * @param data
	 * @throws Throwable
	 */


	/**
	 * This method is used to open the journey history screen on the App.
	 * @param context
	 * @param action
	 * @throws Throwable
	 */
	public void openJourneyHistoryScreen(ITestContext context,AppiumActions action) throws Throwable{
		try {
			HomePage.homePageLocators(context);
			journeyHistoryPage.journeyHistoryPageLocators(context);
			if(action.isVisibleWithOutReport(HomePage.myCards, "myCards",true)){
				String expText= action.getText(HomePage.myCards, "myCards");
				action.assertTextStringMatching(expText, "My Cards");
			}
			//action.WaitforTextPresent(HomePage.myCards, "myCards", "My Cards");
			action.waitForElementPresent(HomePage.imageView, "imageView");
			action.click(HomePage.imageView, "imageView");
			//action.click(HomePage.viewJourneyHistoryBtn, "viewJourneyHistoryBtn");

			//action.click(HomePage.viewFullHistory, "viewFullHistory");
			Thread.sleep(2000);
			action.waitForElementPresent(HomePage.imageViewOptions, "imageViewOptions");
			action.click(HomePage.imageViewOptions, "imageViewOptions");
			action.waitForElementPresent(HomePage.viewJourneyHistoryOption, "viewJourneyHistoryOption");
			if (!(action.isVisibleWithOutReport(HomePage.viewJourneyHistoryOption, "viewJourneyHistoryOption", true))) {
				action.click(HomePage.imageViewOptions, "imageViewOptions");	
			}
			action.click(HomePage.viewJourneyHistoryOption, "viewJourneyHistoryOption");
			action.waitForElementPresent(journeyHistoryPage.backArrow, "backArrow");
			Thread.sleep(2000);
			journeyHistoryPage.journeyHistoryPageLocators(context);	
			action.click(journeyHistoryPage.backArrow, "backArrow");
			Thread.sleep(2000);
			HomePage.homePageLocators(context);
			action.waitForElementPresent(HomePage.imageViewOptions, "imageViewOptions");
			action.click(HomePage.imageViewOptions, "imageViewOptions");
			action.waitForElementPresent(HomePage.viewJourneyHistoryOption, "viewJourneyHistoryOption");
			action.click(HomePage.viewJourneyHistoryOption, "viewJourneyHistoryOption");


		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * This method is used to verify the journey history on the App.
	 * @param context
	 * @param entryStationName
	 * @param exitStationName
	 * @param action
	 * @throws Throwable
	 */
	public void verifyJourneyHistory(ITestContext context,String entryStationName, String exitStationName,AppiumActions action) throws Throwable{
		try {
			journeyHistoryPage.journeyHistoryPageLocators(context);	
			Thread.sleep(2000);
			if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")) {
				iOSJourneyHistoryCounter = iOSJourneyHistoryCounter+1;
				String journeyDetailName = action.getText(journeyHistoryPage.getJourneyDetailName(context, iOSJourneyHistoryCounter), "getJourneyDetailName").trim();
				String[] journeyDetName = journeyDetailName.split("£");
				journeyDetailName = journeyDetName[0].substring(0, 10).trim(); 
				System.out.println(journeyDetailName);
				if(journeyDetailName.equalsIgnoreCase("Topped up") || journeyDetailName.contains("Season") || journeyDetailName.equalsIgnoreCase("Tram Journey")){
					iOSJourneyHistoryCounter = iOSJourneyHistoryCounter+1;
				}
				action.click(journeyHistoryPage.seclectJourneyHistory(context, iOSJourneyHistoryCounter), "Selecting JourneyHistory");
			}
			else {
				journeyHistoryCounter = journeyHistoryCounter+1; 
				String journeyDetailName = action.getText(journeyHistoryPage.getJourneyDetailName(context, journeyHistoryCounter), "getJourneyDetailName");
				if(journeyDetailName.equalsIgnoreCase("Topped up") || journeyDetailName.equalsIgnoreCase("Season ticket collected") || journeyDetailName.equalsIgnoreCase("Tram Journey")){
					journeyHistoryCounter = journeyHistoryCounter+1;
				}
				action.click(journeyHistoryPage.seclectJourneyHistory(context, journeyHistoryCounter), "Selecting JourneyHistory");
				//action.click(journeyHistoryPage.seclectJourneyHistory(context, journeyHistoryCounter), "Selecting JourneyHistory");
			}
			String actualentryStationName = action.getText(journeyHistoryPage.entryStationName, "entryStationName").trim().toLowerCase();
			String actualentryStation = actualentryStationName.split(" ")[0];
			String actualexitStationName = action.getText(journeyHistoryPage.exitStationName, "exitStationName").trim().toLowerCase();
			String actualExitStation = actualexitStationName.split(" ")[0];

			if (entryStationName.trim().equalsIgnoreCase("")) {
				entryStationName = "Missing touch";
			}
			else if(exitStationName.trim().equalsIgnoreCase("")){
				exitStationName = "Missing touch";
			}

			if (entryStationName.trim().toLowerCase().contains(actualentryStation)&&exitStationName.trim().toLowerCase().contains(actualExitStation)) {
				action.successReportAppium("Verify the Journey History is updated with Entry and Exit Stations", "Journey History is updated on App and Entry and Exit Stations are displayed as '"+actualentryStationName+" --> "+actualexitStationName+"'");
			}
			else if(actualentryStation.toLowerCase().contains(entryStationName.toLowerCase())){
				action.successReportAppium("Verify the Journey History is updated with Entry and Exit Stations", "Journey History is updated on App and Entry and Exit Stations are displayed as '"+actualentryStationName+" --> "+actualexitStationName+"'");
			}
			else if(entryStationName.toLowerCase().contains(actualentryStation)&&exitStationName.toLowerCase().contains(actualExitStation)){
				action.successReportAppium("Verify the Journey History is updated with Entry and Exit Stations", "Journey History is updated on App and Entry and Exit Stations are displayed as '"+actualentryStationName+" --> "+actualexitStationName+"'");
			}
			else{
				action.failureReport("Verify the Journey History is updated with Entry and Exit Stations", "Journey History Details are not Updated to App");
			}
			action.click(journeyHistoryPage.backArrow, "backArrow");
		} catch (Exception e) {
			// TODO: handle exception
		}


	}
	
	/**
	 * This method is used to clear the variables created for verifying the journey history on the App.
	 */
	public void clearJourneyHistoryStaticVariables(){
		try {
			iOSJourneyHistoryCounter=0;
			journeyHistoryCounter=2;
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	/**
	 * This method is used to verify the TopUp on the App.
	 * @param context
	 * @param data
	 * @param paygStationName
	 * @param action
	 * @throws Throwable
	 */
	public void verifyTopUpScreen(ITestContext context,Hashtable<String, String> data,String paygStationName,AppiumActions action) throws Throwable{
		String expectedTopUpBalance = null;
		String actualBalanceToppedUp = null;
		String actualentryStation = null;
		try {
			if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")) {
				if (!(action.isVisibleWithOutReport(journeyHistoryPage.toppedUpAmountMainScreen, "toppedUpAmount",true))) {
					action.click(journeyHistoryPage.backArrow, "backArrow");
					action.click(HomePage.viewFullHistory, "viewFullHistory");
				}
				String journeyDetailName = action.getText(journeyHistoryPage.toppedUpAmountMainScreen, "toppedUpAmount");
				String[] parts = journeyDetailName.split("£");
				journeyDetailName = parts[0].substring(0,10).trim(); 
				
				if(journeyDetailName.equalsIgnoreCase("Topped up")){
					action.click(journeyHistoryPage.toppedUpAmountMainScreen, "toppedUpAmountMainScreen");
				}
				Thread.sleep(2000);
				expectedTopUpBalance = "+ £"+data.get("topup")+".00";
				actualBalanceToppedUp = action.getText(journeyHistoryPage.toppedUpAmount, "toppedUpAmount");
				String expToppedUpStnName = action.getText(journeyHistoryPage.toppedUpStnName, "toppedUpStnName");
				actualentryStation = expToppedUpStnName.split(" ")[0];
				if (actualentryStation.trim().toLowerCase().contains(paygStationName.trim().toLowerCase())) {
					action.successReportAppium("Verified the toppedup station successfully ", "Balance topped up at a station is displayed as '"+actualentryStation);
				}
				else if (paygStationName.trim().toLowerCase().contains(actualentryStation.trim().toLowerCase())) {
					action.successReportAppium("Verified the toppedup station successfully ", "Balance topped up at a station is displayed as '"+actualentryStation);
				}
				else{
					action.failureReport("Verified the toppedup station successfully ", "Balance topped up at station is not correct");
				}	
			}
			else {
				String journeyDetailName = action.getText(journeyHistoryPage.toppedUpAmountMainScreen, "toppedUpAmount");
				if(journeyDetailName.equalsIgnoreCase("Topped up")){
					action.click(journeyHistoryPage.toppedUpAmountMainScreen, "toppedUpAmountMainScreen");
				}
				Thread.sleep(2000);
				expectedTopUpBalance = "+ £"+data.get("topup")+".00";
				actualBalanceToppedUp = action.getText(journeyHistoryPage.toppedUpAmount, "toppedUpAmount");
			}	
			if (expectedTopUpBalance.equalsIgnoreCase(actualBalanceToppedUp)){
				action.successReportAppium("Verify the Balance toppedup successfully ", "Balance topped up successfully and amount displayed as '"+actualBalanceToppedUp);
			}
			else{
				action.failureReport("Verify the Balance toppedup successfully ", "Balance NOT topped up ");
			}
			action.click(journeyHistoryPage.backArrow, "backArrow");
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * This method is used to verify the Ticket collected station on the App.
	 * @param context
	 * @param data
	 * @param action
	 */
	@SuppressWarnings("unused")
	public void verifyTicketCollectedStationScreen(ITestContext context,Hashtable<String, String> data,AppiumActions action){
		try {
			
			String journeyDetailName = action.getText(journeyHistoryPage.ticketCollectedScreen, "ticketCollectedScreen");
			if(journeyDetailName.contains("Season ticket collected")){
				//journeyHistoryCounter = journeyHistoryCounter+1;
				/*journeyHistoryPage.journeyHistoryPageLocators(context);*/	
				action.click(journeyHistoryPage.ticketCollectedScreen, "ticketCollectedScreen");
			}
			Thread.sleep(2000);
			if (journeyDetailName!=null) {
				action.successReportAppium("Verify the Ticket collected station successfully ", "Ticket collected  successfully ");
			}
			else{
				action.failureReport("Verify the Ticket collected station successfully ", "Ticket not collected");
			}
			action.click(journeyHistoryPage.backArrow, "backArrow");
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	/**
	 * This method is used to navigate to the main screen on the App.
	 * @param context
	 * @param action
	 */
	public void navigateBackToMainScreen(ITestContext context,AppiumActions action){
		try {
			Thread.sleep(1000);
			action.click(journeyHistoryPage.backArrow, "backArrow");
			HomePage.homePageLocators(context);
			//action.click(HomePage.closeBtn, "closeBtn");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	/**
	 * This method is used to verify the Tram journey history on the App.
	 * @param context
	 * @param stationName
	 * @param action
	 * @throws Throwable
	 */
	public void verifyTramJourneyHistory(ITestContext context,String stationName,AppiumActions action) throws Throwable{
		try {
			Thread.sleep(2000);
			journeyHistoryPage.journeyHistoryPageLocators(context);	
			if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")) {
				iOSJourneyHistoryCounter = iOSJourneyHistoryCounter+1;
				String journeyDetailName = action.getText(journeyHistoryPage.getJourneyDetailName(context, iOSJourneyHistoryCounter), "getJourneyDetailName").trim();
				String[] journeyDetName = journeyDetailName.split("£");
				journeyDetailName = journeyDetName[0].substring(0, 10).trim(); 
				System.out.println(journeyDetailName);
				if(journeyDetailName.equalsIgnoreCase("Topped up") || journeyDetailName.equalsIgnoreCase("Season ticket collected") || journeyDetailName.equalsIgnoreCase("Tram Journey")){
					iOSJourneyHistoryCounter = iOSJourneyHistoryCounter+1;
				}
				action.click(journeyHistoryPage.seclectJourneyHistory(context, iOSJourneyHistoryCounter), "Selecting JourneyHistory");
			}
			else {
				journeyHistoryCounter = journeyHistoryCounter+1;
				String journeyDetailName = action.getText(journeyHistoryPage.getJourneyDetailName(context, journeyHistoryCounter), "getJourneyDetailName");
				if(journeyDetailName.equalsIgnoreCase("Topped up") || journeyDetailName.equalsIgnoreCase("Season ticket collected") || journeyDetailName.equalsIgnoreCase("Tram Journey")){
					journeyHistoryCounter = journeyHistoryCounter+1;
				}
				action.click(journeyHistoryPage.seclectJourneyHistory(context, journeyHistoryCounter), "Selecting JourneyHistory");
			}
			/*action.click(journeyHistoryPage.seclectJourneyHistory(context, journeyHistoryCounter), "journeyHistoryCounter");
			String journeyDetailName = action.getText(journeyHistoryPage.tramJourneyMainScreen, "tramJourneyMainScreen");
			if(journeyDetailName.equalsIgnoreCase(" Tram Journey")){
				//journeyHistoryCounter = journeyHistoryCounter+1;
				journeyHistoryPage.journeyHistoryPageLocators(context);	
				action.click(journeyHistoryPage.tramJourneyMainScreen, "tramJourneyMainScreen");
			}*/
			//action.click(journeyHistoryPage.tramJourneyMainScreen, "tramJourneyMainScreen");
			
			journeyHistoryPage.journeyHistoryPageLocators(context);			
			String actualentryStationName = action.getText(journeyHistoryPage.tramEntryStationName, "tramEntryStationName").trim().toLowerCase();
			String [] actualentryStation = actualentryStationName.split(" ");
			for(String entryStn: actualentryStation){
				if (stationName.trim().toLowerCase().contains(entryStn)) {
					action.successReportAppium("Verify the Journey History is updated with Entry and Exit Stations", "Journey History is updated on App and Entry and Exit Stations are displayed as '"+actualentryStationName);

				}else if(entryStn.trim().contains(stationName.trim())) {
					action.successReportAppium("Verify the Journey History is updated with Entry and Exit Stations", "Journey History is updated on App and Entry and Exit Stations are displayed as '"+actualentryStationName);
				}
				else{
					action.failureReport("Verify the Journey History is updated with Entry and Exit Stations", "Journey History Details are not Updated to App");
				}
			}

			action.click(journeyHistoryPage.backArrow, "backArrow");
			/*HomePage.homePageLocators(context);
			action.click(HomePage.closeBtn, "closeBtn");*/

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * This method is used to verify the Bus journey history on the App.
	 * @param context
	 * @param stationName
	 * @param action
	 * @throws Throwable
	 */
	public void verifyBusJourneyHistory(ITestContext context,String stationName,AppiumActions action) throws Throwable{
		try {
			Thread.sleep(2000);
			journeyHistoryPage.journeyHistoryPageLocators(context);	
			/*HomePage.homePageLocators(context);
			action.WaitforTextPresent(HomePage.myCards, "myCards", "My Cards");
			action.click(HomePage.imageView, "imageView");
			action.click(HomePage.viewJourneyHistoryBtn, "viewJourneyHistoryBtn");*/

			journeyHistoryCounter = journeyHistoryCounter+1;
			journeyHistoryPage.journeyHistoryPageLocators(context);	
			Thread.sleep(2000);
			String journeyDetailName = action.getText(journeyHistoryPage.getJourneyDetailName(context, journeyHistoryCounter), "getJourneyDetailName");
			if(journeyDetailName.equalsIgnoreCase("Topped up") || journeyDetailName.equalsIgnoreCase("Season ticket collected")){
				journeyHistoryCounter = journeyHistoryCounter+1;
			}
			action.click(journeyHistoryPage.seclectJourneyHistory(context, journeyHistoryCounter), "Selecting JourneyHistory");

			/*String journeyDetailName = action.getText(journeyHistoryPage.tramJourneyMainScreen, "tramJourneyMainScreen");
			if(journeyDetailName.equalsIgnoreCase(" Tram Journey")){
				//journeyHistoryCounter = journeyHistoryCounter+1;
				journeyHistoryPage.journeyHistoryPageLocators(context);	
				action.click(journeyHistoryPage.tramJourneyMainScreen, "tramJourneyMainScreen");
			}*/
			//action.click(journeyHistoryPage.tramJourneyMainScreen, "tramJourneyMainScreen");
			journeyHistoryPage.journeyHistoryPageLocators(context);			
			String actualentryStationName = action.getText(journeyHistoryPage.tramEntryStationName, "tramEntryStationName").trim().toLowerCase();
			String [] actualentryStation = actualentryStationName.split(" ");
			/*if (entryStationName.trim().equalsIgnoreCase("")) {
				entryStationName = "Missing touch";

			}
			else if(exitStationName.trim().equalsIgnoreCase("")){
				exitStationName = "Missing touch";
			}*/
			for(String entryStn: actualentryStation){
				if (stationName.trim().toLowerCase().contains(entryStn)) {
					action.successReportAppium("Verify the Journey History is updated with Entry and Exit Stations", "Journey History is updated on App and Entry and Exit Stations are displayed as '"+actualentryStationName);

				}else if(entryStn.trim().contains(stationName.trim())) {
					action.successReportAppium("Verify the Journey History is updated with Entry and Exit Stations", "Journey History is updated on App and Entry and Exit Stations are displayed as '"+actualentryStationName);
				}
				else{
					action.failureReport("Verify the Journey History is updated with Entry and Exit Stations", "Journey History Details are not Updated to App");
				}
			}

			action.click(journeyHistoryPage.backArrow, "backArrow");
			/*HomePage.homePageLocators(context);
			action.click(HomePage.closeBtn, "closeBtn");*/

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	/**
	 * This method is used to verify the Tram journies on the App.
	 * @param context
	 * @param entryTramStationName
	 * @param exitTramStationName
	 * @param action
	 * @throws Throwable
	 */
	public void verifyTramJournies(ITestContext context,String entryTramStationName, String exitTramStationName, AppiumActions action) throws Throwable{
		try {
		Thread.sleep(2000);
		journeyHistoryPage.journeyHistoryPageLocators(context); 
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")) {
		iOSJourneyHistoryCounter = iOSJourneyHistoryCounter+1;
		String journeyDetailName = action.getText(journeyHistoryPage.getJourneyDetailName(context, iOSJourneyHistoryCounter), "getJourneyDetailName").trim();
		String[] journeyDetName = journeyDetailName.split("£");
		journeyDetailName = journeyDetName[0].substring(0, 12).trim(); 
		System.out.println(journeyDetailName);
		if(journeyDetailName.equalsIgnoreCase("Topped up") || journeyDetailName.equalsIgnoreCase("Season ticket collected")){
		iOSJourneyHistoryCounter = iOSJourneyHistoryCounter+1;
		}
		action.click(journeyHistoryPage.seclectJourneyHistory(context, iOSJourneyHistoryCounter), "Selecting JourneyHistory");
		}
		else {
		journeyHistoryCounter = journeyHistoryCounter+1;
		String journeyDetailName = action.getText(journeyHistoryPage.getJourneyDetailName(context, journeyHistoryCounter), "getJourneyDetailName");
		if(journeyDetailName.equalsIgnoreCase("Topped up") || journeyDetailName.equalsIgnoreCase("Season ticket collected")){
		journeyHistoryCounter = journeyHistoryCounter+1;
		}
		action.click(journeyHistoryPage.seclectJourneyHistory(context, journeyHistoryCounter), "Selecting JourneyHistory");
		}
		String actualentryStationName = action.getText(journeyHistoryPage.tramEntryStationName, "tramEntryStationName").trim().toLowerCase();
		String actualexitStationName = action.getText(journeyHistoryPage.tramExitStationName, "tramExitStationName").trim().toLowerCase();
		String actualentryStation = actualentryStationName.split(" ")[0];
		String actualExitStation = actualexitStationName.split(" ")[0];
		if (entryTramStationName.trim().toLowerCase().contains(actualentryStation)&&exitTramStationName.trim().toLowerCase().contains(actualExitStation)) {
		//action.click(journeyHistoryPage.journeyDetails, "journeyDetails");
		//action.WaitforTextPresent(journeyHistoryPage.verifyStationName, "verifyStationName ",actualentryStationName);
		action.successReportAppium("Verify the Journey History is updated with Entry and Exit Stations", "Journey History is updated on App and Entry and Exit Stations are displayed as '"+actualentryStationName+" --> "+actualexitStationName+"'");

		}
		else if (entryTramStationName.trim().toLowerCase().contains(actualExitStation)&&exitTramStationName.trim().toLowerCase().contains(actualentryStation)) {
			//action.click(journeyHistoryPage.journeyDetails, "journeyDetails");
			//action.WaitforTextPresent(journeyHistoryPage.verifyStationName, "verifyStationName ",actualentryStationName);
			//action.click(journeyHistoryPage.backArrow, "backArrow");
			action.successReportAppium("Verify the Journey History is updated with Entry and Exit Stations", "Journey History is updated on App and Entry and Exit Stations are displayed as '"+actualentryStationName+" --> "+actualexitStationName+"'");
			}
		else if(actualentryStation.contains(entryTramStationName)){
		//action.click(journeyHistoryPage.journeyDetails, "journeyDetails");
		//action.WaitforTextPresent(journeyHistoryPage.verifyStationName, "verifyStationName ",actualentryStationName);
		//action.click(journeyHistoryPage.backArrow, "backArrow");
		action.successReportAppium("Verify the Journey History is updated with Entry and Exit Stations", "Journey History is updated on App and Entry and Exit Stations are displayed as '"+actualentryStationName+" --> "+actualexitStationName+"'");
		}
		else if(entryTramStationName.toLowerCase().contains(actualentryStation)&&exitTramStationName.toLowerCase().contains(actualExitStation)){
		//action.click(journeyHistoryPage.journeyDetails, "journeyDetails");
		//action.WaitforTextPresent(journeyHistoryPage.verifyStationName, "verifyStationName ",actualentryStationName);
		//action.click(journeyHistoryPage.backArrow, "backArrow");
		action.successReportAppium("Verify the Journey History is updated with Entry and Exit Stations", "Journey History is updated on App and Entry and Exit Stations are displayed as '"+actualentryStationName+" --> "+actualexitStationName+"'");
		}
		else{
		action.failureReport("Verify the Journey History is updated with Entry and Exit Stations", "Journey History Details are not Updated to App");
		}

		action.click(journeyHistoryPage.backArrow, "backArrow");
		} catch (Exception e) {
		// TODO: handle exception
		}

		}

	/**
	 * This method is used to verify the IVAL journey history on the App.
	 * @param context
	 * @param entryStationName
	 * @param exitStationName
	 * @param action
	 * @throws Throwable
	 */
	public void verifyIvalJourneyHistory(ITestContext context,String entryStationName,String exitStationName,AppiumActions action) throws Throwable{
		try {
			//journeyHistoryCounter = journeyHistoryCounter+1;
			journeyHistoryPage.journeyHistoryPageLocators(context);	
			Thread.sleep(2000);

			if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")) {
				iOSJourneyHistoryCounter = iOSJourneyHistoryCounter+1;
				String journeyDetailName = action.getText(journeyHistoryPage.getJourneyDetailName(context, iOSJourneyHistoryCounter), "getJourneyDetailName").trim();
				String[] journeyDetName = journeyDetailName.split("£");
				journeyDetailName = journeyDetName[0].substring(0, 10).trim(); 
				System.out.println(journeyDetailName);
				if(journeyDetailName.equalsIgnoreCase("Topped up") || journeyDetailName.equalsIgnoreCase("Season ticket collected") || journeyDetailName.equalsIgnoreCase("Tram Journey")){
					iOSJourneyHistoryCounter = iOSJourneyHistoryCounter+1;
				}
				action.click(journeyHistoryPage.seclectJourneyHistory(context, iOSJourneyHistoryCounter), "Selecting JourneyHistory");
			}
			else {
				journeyHistoryCounter = journeyHistoryCounter+1;
				String journeyDetailName = action.getText(journeyHistoryPage.getJourneyDetailName(context, journeyHistoryCounter), "getJourneyDetailName");
				if(journeyDetailName.equalsIgnoreCase("Topped up") || journeyDetailName.equalsIgnoreCase("Season ticket collected") || journeyDetailName.equalsIgnoreCase("Tram Journey")){
					journeyHistoryCounter = journeyHistoryCounter+1;
				}
				action.click(journeyHistoryPage.seclectJourneyHistory(context, journeyHistoryCounter), "Selecting JourneyHistory");
			}

			String actualentryStationName = action.getText(journeyHistoryPage.entryStationName, "entryStationName").trim().toLowerCase();
			String actualexitStationName = action.getText(journeyHistoryPage.exitStationName, "exitStationName").trim().toLowerCase();
			String actualentryStation = actualentryStationName.split(" ")[0];
			String actualExitStation = actualexitStationName.split(" ")[0];

			if (entryStationName.trim().equalsIgnoreCase("")) {
				entryStationName = "Unknown";

			}
			else if(exitStationName.trim().equalsIgnoreCase("")){
				exitStationName = "Unknown";
			}
			if (entryStationName.trim().toLowerCase().contains(actualentryStation)&&exitStationName.trim().toLowerCase().contains(actualExitStation)) {
				//action.click(journeyHistoryPage.journeyDetails, "journeyDetails");
				//action.WaitforTextPresent(journeyHistoryPage.verifyStationName, "verifyStationName ",actualentryStationName);
				action.successReportAppium("Verify the Journey History is updated with Entry and Exit Stations", "Journey History is updated on App and Entry and Exit Stations are displayed as '"+actualentryStationName+" --> "+actualexitStationName+"'");

			}
			//else if(actualentryStation.contains(entryStationName)&&actualExitStation.contains(exitStationName)){
			else if(actualentryStation.contains(entryStationName)){
				//action.click(journeyHistoryPage.journeyDetails, "journeyDetails");
				//action.WaitforTextPresent(journeyHistoryPage.verifyStationName, "verifyStationName ",actualentryStationName);
				action.successReportAppium("Verify the Journey History is updated with Entry and Exit Stations", "Journey History is updated on App and Entry and Exit Stations are displayed as '"+actualentryStationName+" --> "+actualexitStationName+"'");
				//action.click(journeyHistoryPage.backArrow, "backArrow");
			}
			else if(entryStationName.toLowerCase().contains(actualentryStation)&&exitStationName.toLowerCase().contains(actualExitStation)){
				//action.click(journeyHistoryPage.journeyDetails, "journeyDetails");
				//action.WaitforTextPresent(journeyHistoryPage.verifyStationName, "verifyStationName ",actualentryStationName);
				action.successReportAppium("Verify the Journey History is updated with Entry and Exit Stations", "Journey History is updated on App and Entry and Exit Stations are displayed as '"+actualentryStationName+" --> "+actualexitStationName+"'");
				//action.click(journeyHistoryPage.backArrow, "backArrow");
			}
			else{
				action.failureReport("Verify the Journey History is updated with Entry and Exit Stations", "Journey History Details are not Updated to App");
			}

			action.click(journeyHistoryPage.backArrow, "backArrow");

		} catch (Exception e) {
			// TODO: handle exception
		}

	}


	//** Backup **//
	/*public void verifyJourneyHistory(ITestContext context,String entryStationName, String exitStationName,AppiumActions action) throws Throwable{
		try {
			journeyHistoryCounter = journeyHistoryCounter+1;

			HomePage.homePageLocators(context);
			action.WaitforTextPresent(HomePage.myCards, "myCards", "My Cards");
			action.click(HomePage.imageView, "imageView");
			//action.click(HomePage.viewJourneyHistoryBtn, "viewJourneyHistoryBtn");

			action.click(HomePage.viewFullHistory, "viewFullHistory");

			journeyHistoryPage.journeyHistoryPageLocators(context);	

			action.click(journeyHistoryPage.seclectJourneyHistory(context, journeyHistoryCounter), "Selecting JourneyHistory");
			//action.WaitforTextPresent(journeyHistoryPage.pageHeader, "Page header", "Journey History");
			String actualentryStationName = action.getText(journeyHistoryPage.entryStationName, "entryStationName");
			String actualexitStationName = action.getText(journeyHistoryPage.exitStationName, "exitStationName");

			String actualentryStationName = action.getText(journeyHistoryPage.entryStationName, "entryStationName").trim().toLowerCase();
			String actualexitStationName = action.getText(journeyHistoryPage.exitStationName, "exitStationName").trim().toLowerCase();
			String actualentryStation = actualentryStationName.split(" ")[0];
			String actualExitStation = actualexitStationName.split(" ")[0];
			//action.click(journeyHistoryPage.journeyDetails, "journeyDetails");
			//action.WaitforTextPresent(journeyHistoryPage.verifyStationName, "verifyStationName ",actualentryStationName);
			action.successReportAppium("Verify the Journey History is updated with Entry and Exit Stations", "Journey History is updated on App and Entry and Exit Stations are displayed as '"+actualentryStationName+" --> "+actualexitStationName+"'");
			action.click(journeyHistoryPage.backArrow, "backArrow");
			if (entryStationName.trim().equalsIgnoreCase("")) {
				entryStationName = "Missing touch";

			}
			else if(exitStationName.trim().equalsIgnoreCase("")){
				exitStationName = "Missing touch";
			}


			if (entryStationName.trim().toLowerCase().contains(actualentryStation)&&exitStationName.trim().toLowerCase().contains(actualExitStation)) {
				//action.click(journeyHistoryPage.journeyDetails, "journeyDetails");
				//action.WaitforTextPresent(journeyHistoryPage.verifyStationName, "verifyStationName ",actualentryStationName);
				action.successReportAppium("Verify the Journey History is updated with Entry and Exit Stations", "Journey History is updated on App and Entry and Exit Stations are displayed as '"+actualentryStationName+" --> "+actualexitStationName+"'");

			}
			//else if(actualentryStation.contains(entryStationName)&&actualExitStation.contains(exitStationName)){
			else if(actualentryStation.contains(entryStationName)){
				//action.click(journeyHistoryPage.journeyDetails, "journeyDetails");
				//action.WaitforTextPresent(journeyHistoryPage.verifyStationName, "verifyStationName ",actualentryStationName);
				action.successReportAppium("Verify the Journey History is updated with Entry and Exit Stations", "Journey History is updated on App and Entry and Exit Stations are displayed as '"+actualentryStationName+" --> "+actualexitStationName+"'");
				//action.click(journeyHistoryPage.backArrow, "backArrow");
			}
			else if(entryStationName.toLowerCase().contains(actualentryStation)&&exitStationName.toLowerCase().contains(actualExitStation)){
				//action.click(journeyHistoryPage.journeyDetails, "journeyDetails");
				//action.WaitforTextPresent(journeyHistoryPage.verifyStationName, "verifyStationName ",actualentryStationName);
				action.successReportAppium("Verify the Journey History is updated with Entry and Exit Stations", "Journey History is updated on App and Entry and Exit Stations are displayed as '"+actualentryStationName+" --> "+actualexitStationName+"'");
				//action.click(journeyHistoryPage.backArrow, "backArrow");
			}
			else{
				action.failureReport("Verify the Journey History is updated with Entry and Exit Stations", "Journey History Details are not Updated to App");
			}
			action.click(journeyHistoryPage.backArrow, "backArrow");
			HomePage.homePageLocators(context);
			action.click(HomePage.closeBtn, "closeBtn");

		} catch (Exception e) {
			// TODO: handle exception
		}

	}*/
	
}
