package com.tfl.appium.page;

import org.openqa.selenium.By;
import org.testng.ITestContext;

public class HomePage {

	public static By hamBurger;
	public static By SignOut;
	public static By topUp;
	public static By closeBtn;
	public static By imageView;
	public static By myCards;
	public static By oysterCardNumber;
	public static By oysterCardBalance;
	public static By viewJourneyHistoryBtn;
	public static By zonesDisplayed;
	public static By expiryDate;
	public static By dateDisplayed;
	public static By viewFullHistory;
	public static By imageViewOptions;
	public static By viewJourneyHistoryOption;
	public static By plusBtn;
	public static By travelcardName;
	

	/* Page Objects Of Home Page */

	public static void homePageLocators(ITestContext context){
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")) {
			hamBurger=By.xpath("//android.widget.TextView[@text='h']");
			myCards=By.xpath("//android.widget.TextView[@text='My cards']");
			SignOut=By.xpath("//android.widget.TextView[@text='Sign out']");
			topUp=By.xpath("//android.widget.TextView[@text='Top up']");
			closeBtn=By.xpath("//android.widget.TextView[@text='Close']");
			//imageView=By.xpath("//android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.ImageView");
			imageView=By.id("touch_oysterCard");
			//oysterCardNumber=By.xpath("//android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.TextView");
			oysterCardNumber=By.id("lbl_cardNumber");
			//oysterCardBalance=By.xpath("//android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.TextView[2]");
			oysterCardBalance=By.id("lbl_balance");
			viewJourneyHistoryBtn = By.xpath("//android.widget.TextView[@text='Last activity']/../../following-sibling::android.view.ViewGroup/android.view.ViewGroup");
			viewFullHistory = By.xpath("//android.widget.TextView[@text='Full history']");
			//imageViewOptions = By.xpath("//android.support.v4.view.ViewPager/../following-sibling::android.view.ViewGroup//android.view.View");
			//imageViewOptions = By.id("btn_flyOutMenu");
			imageViewOptions = By.xpath("//android.view.View[@content-desc='btn_flyOutMenu']");
			viewJourneyHistoryOption = By.id("btn_journeyHistory");
			//viewJourneyHistoryOption = By.xpath("//android.widget.TextView[@text='Journey history']/following-sibling::android.view.ViewGroup/android.widget.ImageView");
			//viewFullHistory = By.xpath("//android.widget.TextView[@text='Last activity']/../../following-sibling::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='Full history']");
			zonesDisplayed = By.xpath("//android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.TextView[contains(@text,'Zones')]");
			expiryDate = By.xpath("//android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.TextView[5]");
			dateDisplayed = By.xpath("//android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.TextView[contains(@text,'Zones')]/following-sibling::android.widget.TextView");
			plusBtn = By.id("btn_plus");
			travelcardName = By.xpath("//android.view.ViewGroup[@content-desc=\"lbl_products\"]/android.widget.TextView[2]");
			
					//android.view.ViewGroup[@content-desc="lbl_products"]/android.widget.TextView[1]
					//android.view.ViewGroup[@content-desc="lbl_products"]/android.widget.TextView[2]
		}
		else if(context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")){
			hamBurger=By.xpath("//XCUIElementTypeOther[@name=\"btn_burgerMenu\"]");
			myCards=By.xpath("//XCUIElementTypeStaticText[@name='title']");
			oysterCardNumber=By.xpath("//XCUIElementTypeOther[@name=\"carddeck\"]");
			//oysterCardBalance=By.xpath("//android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.TextView[2]");
			SignOut=By.xpath("(//XCUIElementTypeOther[contains(@label,'Sign out')])[last()]");
			topUp=By.xpath("//XCUIElementTypeOther[@name=\"btn_topUp\"]");
			closeBtn=By.xpath("//XCUIElementTypeOther[@name=\"carddeck\"]");
			imageView=By.xpath("(//XCUIElementTypeOther[contains(@label,'Last touch')])[last()]");
			viewFullHistory = By.xpath("//XCUIElementTypeOther[@name=\"btn_journeyHistory\"]");
			imageViewOptions = By.xpath("//XCUIElementTypeOther[@name=\"btn_flyOutMenu\"]");
			viewJourneyHistoryOption = By.xpath("//XCUIElementTypeOther[@name=\"btn_journeyHistory\"]");
			oysterCardBalance=By.id("lbl_balance");
			
		}
	}
}