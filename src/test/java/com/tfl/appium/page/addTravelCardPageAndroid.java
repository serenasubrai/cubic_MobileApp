package com.tfl.appium.page;

import org.openqa.selenium.By;
import org.testng.ITestContext;

public class addTravelCardPageAndroid {

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


	public static By createDynamicXpath(String locator){
		return dynamicLocator = By.xpath("//android.widget.TextView[@text='"+locator+"']");
	}

	public static void travelCardPageLocators(ITestContext context){
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")) {
			plusBtn=By.xpath("//android.widget.TextView[@text='+']");
			buyBtn=By.xpath("//android.widget.TextView[@text='Buy']");
			travelcardType=By.xpath("//android.widget.TextView[@text='Travelcard type / Duration']");
			monthLabel=By.xpath("//android.widget.TextView[@text='Month']");
			weekLabel=By.xpath("//android.widget.TextView[@text='7 Day']");
			annualLabel=By.xpath("//android.widget.TextView[@text='Annual']");
			fromZone=By.xpath("//android.widget.TextView[@text='From zone']");
			fromZoneValue=By.xpath("//android.widget.TextView[@text='5']");
			toZone=By.xpath("//android.widget.TextView[@text='To zone']");
			toZoneValue=By.xpath("//android.widget.TextView[@text='Watford Junction']");
			startDate=By.xpath("//android.widget.TextView[@text='Start date']");
			dateValue=By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.TextView");
			confirm=By.xpath("//android.widget.TextView[@text='Confirm']");
			next=By.xpath("//android.widget.TextView[@text='Next']");
		}
		else if(context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")){

		}
	}

}