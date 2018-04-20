package com.tfl.appium.page;

import org.openqa.selenium.By;
import org.testng.ITestContext;

public class journeyHistoryPage {

	public static By pageHeader;
	public static By backArrow;
	public static By entryStationName;
	public static By exitStationName;
	public static By journeyDetails;
	public static By verifyStationName;
	public static By tramEntryStationName;
	public static By selectJourneyHistoryNumber;
	public static By getJourneyName;
	public static By toppedUpAmount;
	public static By toppedUpAmountMainScreen;
	public static By ticketCollectedScreen;
	public static By tramJourneyMainScreen;
	public static By tramExitStationName;
	public static By toppedUpStnName;
	public static By backArrowJnrHis;
	public static By cancel;

	public static By seclectJourneyHistory(ITestContext context, int journeyHistoryNumber){
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")) {
			//selectJourneyHistoryNumber = By.xpath("//android.support.v7.widget.RecyclerView/android.view.ViewGroup["+journeyHistoryNumber+"]/android.view.ViewGroup/android.view.ViewGroup");
			selectJourneyHistoryNumber = By.xpath("//android.widget.ScrollView/android.view.View/android.view.View["+journeyHistoryNumber+"]/android.view.View/android.view.View");
			//selectJourneyHistoryNumber = By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup["+journeyHistoryNumber+"]/android.view.ViewGroup/android.view.ViewGroup");
		}
		else if(context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")){
			selectJourneyHistoryNumber = By.xpath("(//XCUIElementTypeOther[@name=\"listItem\"])"+"["+journeyHistoryNumber+"]");
		}
		return selectJourneyHistoryNumber;
	}
	
	public static By getJourneyDetailName(ITestContext context, int journeyHistoryNumber){
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")) {
			//getJourneyName = By.xpath("//android.support.v7.widget.RecyclerView/android.view.ViewGroup["+journeyHistoryNumber+"]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView");
			//getJourneyName = By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup["+journeyHistoryNumber+"]/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView");
			getJourneyName = By.xpath("//android.widget.ScrollView/android.view.View/android.view.View["+journeyHistoryNumber+"]/android.view.View/android.view.View/android.widget.TextView");
		}
		else if(context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")){
			getJourneyName = By.xpath("(//XCUIElementTypeOther[@name=\"listItem\"])"+"["+journeyHistoryNumber+"]");
		}
		return getJourneyName;
	}

	public static void journeyHistoryPageLocators(ITestContext context){
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")) {
			pageHeader = By.xpath("//android.widget.TextView[@text='Journey History']");
			backArrow=By.xpath("//android.widget.TextView[@text='a']");
			/*entryStationName = By.xpath("//android.support.v7.widget.RecyclerView/android.view.ViewGroup[2]//android.view.ViewGroup/android.widget.TextView[1]");
			exitStationName = By.xpath("//android.support.v7.widget.RecyclerView/android.view.ViewGroup[2]//android.view.ViewGroup/android.widget.TextView[4]");*/
			entryStationName = By.xpath("//android.widget.TextView[@text='b']/preceding-sibling::android.widget.TextView");
			tramEntryStationName = By.xpath("(//android.widget.TextView[@content-desc=\"lbl_tapText\"])[1]");
			tramExitStationName = By.xpath("(//android.widget.TextView[@content-desc=\"lbl_tapText\"])[2]");
			/*tramEntryStationName = By.xpath("//android.widget.TextView[@text='Entry']/following-sibling::android.widget.ImageView/following-sibling::android.widget.TextView");*/
			/*tramExitStationName = By.xpath("//android.widget.TextView[@text='Exit']/following-sibling::android.widget.ImageView/following-sibling::android.widget.TextView");*/
			
			exitStationName = By.xpath("//android.widget.TextView[@text='b']/following-sibling::android.widget.TextView");
			journeyDetails = By.xpath("//android.support.v7.widget.RecyclerView/android.view.ViewGroup[2]//android.view.ViewGroup");
			verifyStationName = By.xpath("//android.widget.TextView[@text='b']/preceding-sibling::android.widget.TextView");
			toppedUpAmount = By.xpath("//android.widget.TextView[@text='Topped up']/following-sibling::android.widget.TextView");
			toppedUpAmountMainScreen = By.xpath("//android.widget.TextView[@text='Topped up']");
			tramJourneyMainScreen = By.xpath("//android.widget.TextView[@text=' Tram Journey']");
			ticketCollectedScreen =  By.xpath("//android.widget.TextView[@text='Season ticket collected']");
			cancel = By.id("btnCancel");
		}
		else if(context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")){
			entryStationName = By.xpath("(//XCUIElementTypeStaticText[@name=\"lbl_locOrAtt\"])[1]");
			exitStationName = By.xpath("(//XCUIElementTypeStaticText[@name=\"lbl_locOrAtt\"])[2]");
			backArrow =  By.xpath("//XCUIElementTypeStaticText[@name='title']/../preceding-sibling::XCUIElementTypeOther");
			//tramEntryStationName = By.xpath("//XCUIElementTypeStaticText[@name=\"Tram Journey\"]/preceding-sibling::XCUIElementTypeStaticText");
			tramEntryStationName = By.xpath("(//XCUIElementTypeStaticText[@name='lbl_tapText'])[1]");
			tramExitStationName = By.xpath("(//XCUIElementTypeStaticText[@name='lbl_tapText'])[2]");
			/*backArrow = By.xpath("//XCUIElementTypeOther[@name='a']/");
			exitStationName = By.xpath("//XCUIElementTypeStaticText[@name='b']/following-sibling::XCUIElementTypeStaticText");
			entryStationName = By.xpath("//XCUIElementTypeStaticText[@name=\"lbl_locOrAtt\"][1]");
			exitStationName = By.xpath("//XCUIElementTypeStaticText[@name=\"lbl_locOrAtt\"][2]");
			entryStationName = By.xpath("//XCUIElementTypeStaticText[@name='b']/preceding-sibling::XCUIElementTypeOther/XCUIElementTypeStaticText");
			exitStationName = By.xpath("//XCUIElementTypeImage[@name='tube']/following-sibling::XCUIElementTypeStaticText");
			exitStationName = By.xpath("//XCUIElementTypeStaticText[@name='Topped up' and contains(@label,'Topped up')]/following-sibling::XCUIElementTypeStaticText/following-sibling::XCUIElementTypeStaticText");
			backArrowJnrHis = backArrow=By.xpath("//XCUIElementTypeOther[@name=\" Journey History\"]/preceding-sibling::XCUIElementTypeOther");*/
			//backArrow = By.xpath("(//XCUIElementTypeOther[contains(@label,' a')])[last()]");
			//backArrowJnrHis = By.xpath("(//XCUIElementTypeStaticText[@name='title']/../preceding-sibling::XCUIElementTypeOther)");
			//(//XCUIElementTypeStaticText[@name="title"]/../preceding-sibling::XCUIElementTypeOther/XCUIElementTypeOther)[2]
			toppedUpAmountMainScreen = By.xpath("//XCUIElementTypeOther[@name='listItem' and contains(@label,'Topped up')]");
			toppedUpAmount = By.xpath("//XCUIElementTypeStaticText[@name='Topped up' and contains(@label,'Topped up')]/following-sibling::XCUIElementTypeStaticText");
			toppedUpStnName = By.xpath("//XCUIElementTypeStaticText[@name=\"lbl_tapText\"]");
			cancel = By.id("Cancel");
			ticketCollectedScreen =  By.xpath("//XCUIElementTypeOther[@name='listItem' and contains(@label,'Season ticket collected z')]");
		}
	}
	
	

}