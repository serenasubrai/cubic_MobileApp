package com.tfl.appium.page;

import org.openqa.selenium.By;
import org.testng.ITestContext;

public class addTravelCardPageIOS {

	public static By plusBtn;
	public static By buyBtn;
	public static By travelcardType;
	public static By monthLabel;
	public static By weekLabel;
	public static By annualLabel;
	public static By fromZone;
	public static By fromZoneValue;
	public static By toZone;
	public static By toZoneValue;
	public static By startDate;
	public static By dateValue;
	public static By confirm;
	public static By next;
	public static By dynamicLocator;
	public static By zoneValue;
	public static By noProductMsg;
	public static By OKBtn;
	

	public static By createDynamicXpath(String locator){
		return dynamicLocator = By.xpath("//android.widget.TextView[@text='"+locator+"']");
	}
	static {
		plusBtn=By.xpath("//android.widget.TextView[@text='+']");
		buyBtn=By.xpath("//XCUIElementTypeStaticText[@value='None']/following-sibling::XCUIElementTypeOther");
		//buyBtn=By.id("Buy");
		travelcardType=By.xpath("(//XCUIElementTypeOther[contains(@label,'Travelcard type')])[last()]");
		monthLabel=By.xpath("//android.widget.TextView[@text='Month']");
		weekLabel=By.xpath("//android.widget.TextView[@text='Week']");
		annualLabel=By.xpath("(//XCUIElementTypeOther[contains(@label,'Annual')])[last()]");
		fromZone=By.xpath("(//XCUIElementTypeOther[contains(@label,'From zone')])[last()]");
		fromZoneValue=By.xpath("(//XCUIElementTypeOther[contains(@label,'Watford Junction')])[last()]");
		toZone=By.xpath("(//XCUIElementTypeOther[contains(@label,'To zone')])[last()]");
		toZoneValue=By.xpath("(//XCUIElementTypeOther[contains(@label,'9')])[last()]");
		startDate=By.xpath("(//XCUIElementTypeOther[contains(@label,'Start date')])[last()]");
		dateValue=By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.TextView");
		confirm=By.xpath("(//XCUIElementTypeOther[contains(@label,'Confirm')])[last()]");
		next=By.xpath("(//XCUIElementTypeOther[contains(@label,'Next')])[last()]");
		noProductMsg = By.xpath("//XCUIElementTypeStaticText[@value='No product found.']");
		OKBtn = By.xpath("(//XCUIElementTypeOther[contains(@label,'OK')])[last()]");
	}
	public static By chooseZoneValue(int i){
		return zoneValue = By.xpath("(//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther["+i+"])[last()]");
	}


}