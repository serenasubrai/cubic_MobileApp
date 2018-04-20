package com.tfl.appium.page;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.testng.ITestContext;

public class addTravelCardPage {

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
	public static By toZoneValuee;
	public static By startDate;
	public static By dateValue;
	public static By futureDateValue;
	public static By confirm;
	public static By next;
	public static By dynamicLocator;
	public static By zoneValue;
	//public static By dateValue;
	public static By noProductMsg;
	public static By OKBtn;
	public static By continueBtnForCheckPurchase;
	public static By unableToPurchase;
	public static By unableToPurchaseErrorMessage;
	public static By topUpExceedsMaxBalance;
	public static By topUpExceedsMaxBalanceErrorMessage;
	public static By selectedStartDate;
	public static By travelcards;
	public static By topUpExceedsOKBtn;
	public static By pleaseWait;
	public static By buyTravlCardBackBtn;
	public static By cancel;
	public static By cardHotlisted;
	public static By cardHotlistedErrorMessage;
	

	public static By createDynamicXpath(String locator){
		return dynamicLocator = By.xpath("//android.widget.TextView[@text='"+locator+"']");
	}
	static {

	}
	public static By chooseZoneValue(ITestContext context,String i){
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")) {
			//zoneValue = By.xpath("//android.support.v7.widget.RecyclerView/android.view.ViewGroup["+i+"]");
			zoneValue = By.xpath("//android.widget.TextView[@text='"+i+"']");
		}
		else if(context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")){
		 zoneValue = By.xpath("(//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther["+i+"])[last()]");
		}
		return zoneValue;
	}
	
	/*public static By selectFutureDate(ITestContext context,String month, String date){
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")) {
			zoneValue = By.xpath("//android.widget.ScrollView//android.widget.TextView[@text='"+month+"']/following-sibling::android.view.ViewGroup/android.widget.TextView[@text='"+date+"']");
		}
		else if(context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")){
		 
		}
		return zoneValue;
	}*/
	
	public static By selectFutureDate(ITestContext context){
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")) {
			LocalDate date = LocalDate.now();
			date = date.plusDays(1);
			String futureDate = date.toString();
			String parts[] = futureDate.split("-");
			futureDate = parts[2];
			int futureDay = Integer.parseInt(futureDate);
			if(futureDay<=9) {
				futureDate = (futureDate.substring(1,2));
			}else {
				futureDate = parts[2];
			}
			futureDateValue = By.xpath("//XCUIElementTypeOther[@name='day_"+futureDate+"']");
		}
		
		return futureDateValue;
	}
	
	public static void travelCardPageLocators(ITestContext context){

		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")) {
			plusBtn=By.xpath("//android.widget.TextView[@text='+']");
			buyBtn=By.xpath("//android.widget.TextView[@text='Buy']");
			//travelcardType=By.xpath("//android.widget.TextView[@text='Travelcard type']");
			travelcards=By.xpath("//android.widget.TextView[@text='Travelcards']");
			travelcardType=By.id("btn_travelcardType");
			monthLabel=By.xpath("//android.widget.TextView[@text='Month']");
			weekLabel=By.xpath("//android.widget.TextView[@text='7 Day']");
			annualLabel=By.xpath("//android.widget.TextView[@text='Annual']");
			fromZone=By.xpath("//android.widget.TextView[@text='From zone']");
			fromZoneValue=By.xpath("//android.widget.TextView[@text='1']");
			toZone=By.xpath("//android.widget.TextView[@text='To zone']");
			toZoneValue=By.xpath("//android.widget.TextView[@text='2']");
			toZoneValuee=By.xpath("//android.widget.TextView[@text='3']");
			startDate=By.xpath("//android.widget.TextView[@text='Start date']");
			dateValue=By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[3]/android.widget.TextView");
			futureDateValue = By.xpath("//android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.TextView");
			confirm=By.xpath("//android.widget.TextView[@text='Confirm']");
			selectedStartDate=By.xpath("//android.widget.TextView[@text='Start date']/following-sibling::android.widget.TextView");			
			next=By.xpath("//android.widget.TextView[@text='Next']");
			noProductMsg = By.xpath("//android.widget.TextView[@text='No product found.']");
			OKBtn = By.xpath("//android.widget.TextView[@text='OK']");
			continueBtnForCheckPurchase = By.xpath("//android.widget.TextView[@text='Continue']");		
			unableToPurchase = By.xpath("//android.widget.TextView[@text='Unable to complete your purchase']");
			unableToPurchaseErrorMessage = By.xpath("//android.widget.TextView[@text='Unable to complete your purchase']/following-sibling::android.widget.TextView");
			buyTravlCardBackBtn = By.xpath("//android.view.ViewGroup[@content-desc='btn_back']");
			cancel = By.xpath("//android.view.ViewGroup[@content-desc='btnCancel']"); 
			//topUpExceedsMaxBalance = By.xpath("//android.widget.TextView[@text='Topup exceeds maximum balance']");
			topUpExceedsMaxBalance = By.xpath("//android.widget.TextView[@content-desc='lbl_title']");
			
			topUpExceedsMaxBalanceErrorMessage = By.xpath("//android.widget.TextView[@text='Topup exceeds maximum balance']/following-sibling::android.widget.TextView");
			
			topUpExceedsOKBtn = By.xpath("//android.widget.TextView[@content-desc='lbl_textBox0']");
			pleaseWait=By.xpath("//android.widget.TextView[@text='Please wait...']");
			cardHotlisted = By.xpath("//android.widget.TextView[@text='This Oyster card has been stopped']");
			cardHotlistedErrorMessage = By.xpath("//android.widget.TextView[@text='This Oyster card has been stopped']/following-sibling::android.widget.TextView");
			
		}
		else if(context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")){
			plusBtn=By.xpath("//android.widget.TextView[@text='+']");
			buyBtn=By.id("btn_buyTravelcard");
			travelcards=By.id("btn_travelcards");
			travelcardType = By.id("btn_travelcardType");
			selectedStartDate=By.id("btn_startDate");
			weekLabel=By.xpath("(//XCUIElementTypeOther[@name=\'listItem'])[1]");
			monthLabel=By.xpath("(//XCUIElementTypeOther[@name=\'listItem'])[2]");
			annualLabel=By.xpath("(//XCUIElementTypeOther[@name=\'listItem'])[5]");	
			fromZone=By.id("btn_fromZone");
			fromZoneValue=By.xpath("(//XCUIElementTypeOther[@name=\'listItem'])[2]");
			toZone=By.id("btn_toZone");
			toZoneValue=By.xpath("(//XCUIElementTypeOther[@name=\'listItem'])[6]");
			toZoneValuee=By.xpath("(//XCUIElementTypeOther[@name=\'listItem'])[7]");
			startDate=By.id("btn_startDate");
			confirm=By.id("btn_confirm");
			next=By.xpath("(//XCUIElementTypeOther[contains(@label,'Next')])[last()]");
			continueBtnForCheckPurchase = By.xpath("(//XCUIElementTypeOther[contains(@label,'Continue')])[last()]");
			pleaseWait=By.xpath("//XCUIElementTypeStaticText[@value='Please wait...']");
			buyTravlCardBackBtn = By.id("btn_back");
			cancel = By.id("btnCancel"); 
			noProductMsg = By.xpath("//XCUIElementTypeStaticText[@value='No product found.']");
			OKBtn = By.xpath("(//XCUIElementTypeOther[contains(@label,'OK')])[last()]");
			//unableToPurchase = By.xpath("//XCUIElementTypeOther[@name='Unable to complete your purchase']");
			unableToPurchase = By.id("lbl_title");
			unableToPurchaseErrorMessage = By.id("lbl_content");
			//unableToPurchaseErrorMessage = By.xpath("//XCUIElementTypeStaticText[@value='Unable to complete your purchase']/following-sibling::XCUIElementTypeOther");
			topUpExceedsMaxBalance = By.id("lbl_title");
			topUpExceedsMaxBalanceErrorMessage = By.id("Topup exceeds maximum balance");
			cardHotlisted = By.id("This Oyster card has been stopped");
			cardHotlistedErrorMessage = By.id("lbl_content");
			
		}
	}

}