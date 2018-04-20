package com.tfl.appium.page;

import org.openqa.selenium.By;
import org.testng.ITestContext;

//import com.tfl.appium.scripts.e2e.txt_answer;
//import com.tfl.appium.scripts.e2e.txt_number;

public class AddOysCard {

	public static By oysCardNumber;
	public static By nxtBtn;
	public static By securityQuestion;
	public static By securityAnswer;
	public static By securityList;
	public static By securityQuestionTitle;
	public static By journeyHistoryTitle;
	public static By travellingByBtn;
	public static By stationBtn;
	public static By tubeOption;
	public static By searchBtn;
	public static By security_nxtBtn;
	public static By stationName;
	public static By cardAddedNotification;
	public static By cardAddedText;
	public static By hotlistedErrorText;



	//	
	//	txt_answer
	//	listItem  /// index	2

	/* Page Objects Of Home Page */

	public static void addOysCardScreen(ITestContext context){
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")) {
			//oysCardNumber = By.id("txt_number");
			oysCardNumber =By.xpath("//android.widget.TextView[@text='12 digit card number']");
			nxtBtn = By.id("btn_next");
			securityQuestion = By.xpath("//android.view.ViewGroup[@content-desc=\"btn_secQuestion\"]/android.widget.TextView[2]");
			securityList = By.xpath("//android.widget.TextView[@text='A memorable place']");
			//securityList = By.id("listItem");
			securityAnswer = By.id("txt_answer");
			securityQuestionTitle = By.xpath("//android.widget.TextView[@text='Security question']");
			journeyHistoryTitle = By.xpath("//android.widget.TextView[@text='Journey history check']");
			travellingByBtn = By.id("btn_transport");
			stationBtn = By.id("btn_stations");
			tubeOption = By.xpath("//android.widget.TextView[@text='Tube, DLR, Overground or National Rail']");
			searchBtn = By.id("txt_search");
			security_nxtBtn = By.xpath("//android.view.ViewGroup[@content-desc='btn_next']/android.widget.TextView");
			stationName = By.xpath("//android.view.ViewGroup[@content-desc=\"listItem\"][1]/android.widget.TextView");
			cardAddedNotification = By.xpath("//android.view.ViewGroup[@content-desc='note_addOC']");
			cardAddedText = By.xpath("//android.widget.TextView[@text='Oyster card added']");
			hotlistedErrorText = By.xpath("//android.widget.TextView[@text='This Oyster card has been stopped']");
			

			//tubeOption = By.xpath("//android.widget.TextView[@text='Tube, DLR, Overground or National Rail']\\");
		}
		//		else if(context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")){
		//			hamBurger=By.xpath("//XCUIElementTypeOther[@name=\"btn_burgerMenu\"]");
		//			myCards=By.xpath("//XCUIElementTypeStaticText[@name=\"title\"]");
		//			oysterCardNumber=By.xpath("//XCUIElementTypeOther[@name=\"carddeck\"]");
		//			oysterCardBalance=By.xpath("//android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.TextView[2]");
		//			SignOut=By.xpath("(//XCUIElementTypeOther[contains(@label,'Sign out')])[last()]");
		//			topUp=By.xpath("//XCUIElementTypeOther[@name=\"btn_topUp\"]");
		//			closeBtn=By.xpath("//XCUIElementTypeOther[@name=\"carddeck\"]");
		//			imageView=By.xpath("(//XCUIElementTypeOther[contains(@label,'Last touch')])[last()]");
		//			viewFullHistory = By.xpath("//XCUIElementTypeOther[@name=\"btn_journeyHistory\"]");
		//			imageViewOptions = By.xpath("//XCUIElementTypeOther[@name=\"btn_flyOutMenu\"]");
		//			viewJourneyHistoryOption = By.xpath("//XCUIElementTypeOther[@name=\"btn_journeyHistory\"]");

	}
}
