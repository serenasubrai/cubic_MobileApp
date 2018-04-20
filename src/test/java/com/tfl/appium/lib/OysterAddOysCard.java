package com.tfl.appium.lib;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.ITestContext;

import com.cubic.appiumaction.AppiumActions;
import com.cubic.genericutils.FileUtil;
import com.cubic.genericutils.JsonUtil;
import com.tfl.appium.page.AddOysCard;
import com.tfl.appium.page.HomePage;
import com.tfl.appium.page.LoginPage;
import com.tfl.appium.page.addTravelCardPage;
import com.tfl.appium.page.journeyHistoryPage;
import com.tfl.appium.page.topupCardPage;

import io.appium.java_client.AppiumDriver;
import oracle.jdbc.util.Login;

public class OysterAddOysCard{
	OysterLogin login = new OysterLogin();
	OysterCreateTransaction txn = new OysterCreateTransaction();
	String verifyProduct;

	/**
	 * 
	 * @param data
	 * @throws Throwable
	 */


	public void verifyCardUnregInCS(Hashtable<String, String> data,AppiumActions action) throws InterruptedException {

		String cardNumber = data.get("PrestigeCardNumber");

		String unregInCS = OysterCreateTransaction.getDBColumnValue("Oracle","SELECT * FROM CMS_NTMCARDS WHERE PRESTIGEID = "+cardNumber+"","REGISTERED");

		try {
			if (unregInCS.equalsIgnoreCase("0")) {
				action.successReport("Verify if oyster card is unregistered", "The following oyster card is unregistered:" + cardNumber);
			}
			else {
				action.failureReport("Verify if oyster card is registered", "The following oyster card is registered:" + cardNumber);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
	}


	public String verifyProductinCS(Hashtable<String, String> data,AppiumActions action) {

		String cardNumber = data.get("PrestigeCardNumber");

		verifyProduct = OysterCreateTransaction.getDBColumnValue("oracle", "SELECT A.PRESTIGEID, A.SLOTNUMBER, A.EXPIRYDATE, B.TIMEVALIDITY||' - '||C.DESCRIPTION Ticket FROM CMS_PPTS A, RDD_TICKETPRODUCTS B, RDD_ZONALVALIDITY C WHERE A.TICKETPRODUCTKEY = B.TICKETPRODUCTKEY AND B.ZONEKEY = C.ZONEKEY AND A.EXPIRYDATE >= sysdate AND prestigeid =  "+cardNumber+" ORDER BY A.EXPIRYDATE DESC", "TICKET");


		try {
			if (verifyProduct.isEmpty()) {
				System.out.println("failed");
				action.failureReport("Verify ticket name", "No valid ticket");
			}
			else {
				System.out.println(verifyProduct);
				action.successReport("Verify ticket name", "Valid ticket name is:" +verifyProduct);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		return verifyProduct;
	}

	public void storeProducts(Hashtable<String, String> data,AppiumActions action) throws Throwable {

		String text = action.getText (HomePage.travelcardName,"");
		//boolean test = text.contains(verifyProduct);

		if (text.contains(verifyProduct)){
			action.successReportAppium("Product displayed correctly", verifyProduct);
			System.out.println("product verified");
		}

		else {
			action.failureReport(verifyProduct, "Product displayed incorrectly");
			System.out.println("product not verified");
		}

	}



	public void verifyCardRegInCSWithMatchingPW(Hashtable<String, String> data,AppiumActions action) throws InterruptedException {

		String cardNumber = data.get("PrestigeCardNumber");

		String unregInCS = OysterCreateTransaction.getDBColumnValue("Oracle","SELECT * FROM CMS_NTMCARDS WHERE PRESTIGEID = "+cardNumber+"","REGISTERED");

		try {
			if (unregInCS.equalsIgnoreCase("1")) {
				action.successReport("Verify if oyster card is registered", "The following oyster card is registered:" + cardNumber);
			}
			else {
				action.failureReport("Verify if oyster card is unregistered", "The following oyster card is unregistered:" + cardNumber);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
	}



	public void addOysterCard (ITestContext context, Hashtable<String, String> data,AppiumActions action) throws InterruptedException {
		AppiumDriver driver = action.getAppiumDriver();

		//action.waitForInVisibilityOfElement(HomePage.plusBtn,"plusBtn");
		action.click(HomePage.plusBtn, "plusBtn");
		//action.click(AddOysCard.oysCardNumber, "oysCardNumber");
		//action.waitForInVisibilityOfElement(AddOysCard.oysCardNumber,"addOysCardNum");
		AddOysCard.addOysCardScreen(context);
		action.sendKeys(AddOysCard.oysCardNumber, data.get("addOysCardNum"), "addOysCardNum"); 
		action.click(AddOysCard.nxtBtn, "nxtBtn");
		//enter security questions
		action.click(AddOysCard.securityQuestion, "securityQuestion");
		action.click(AddOysCard.securityList,"securityList");
		action.click(AddOysCard.securityAnswer,"securityAnswer");
		action.sendKeys(AddOysCard.securityAnswer, data.get("securityAnswer"),"secAns");
		driver.findElement(AddOysCard.securityAnswer).sendKeys("\n");
		//driver.hideKeyboard();

		//action.click(AddOysCard.security_nxtBtn, "security_nxtBtn");
		//action.click(AddOysCard.security_nxtBtn, "security_nxtBtn");
		//AppiumDriver.hideKeyboard(); **NEED TO HIDE KEYBOARD**
		Thread.sleep(3000);
		action.click(AddOysCard.travellingByBtn, "travellingByBtn");
		action.click(AddOysCard.tubeOption, "tubeOption");
		action.click(AddOysCard.stationBtn, "stationBtn");

	}

	public void addRegOysterCard (ITestContext context, Hashtable<String, String> data,AppiumActions action) throws InterruptedException {
		AppiumDriver driver = action.getAppiumDriver();

		//action.waitForInVisibilityOfElement(HomePage.plusBtn,"plusBtn");
		action.click(HomePage.plusBtn, "plusBtn");
		//action.click(AddOysCard.oysCardNumber, "oysCardNumber");
		//action.waitForInVisibilityOfElement(AddOysCard.oysCardNumber,"addOysCardNum");
		AddOysCard.addOysCardScreen(context);
		action.sendKeys(AddOysCard.oysCardNumber, data.get("addOysCardNum"), "addOysCardNum"); 
		action.click(AddOysCard.nxtBtn, "nxtBtn");
		//enter security questions
		action.click(AddOysCard.securityQuestion, "securityQuestion");
		action.click(AddOysCard.securityList,"securityList");
		action.click(AddOysCard.securityAnswer,"securityAnswer");
		action.sendKeys(AddOysCard.securityAnswer, data.get("securityAnswer"),"secAns");
		driver.findElement(AddOysCard.securityAnswer).sendKeys("\n");
		//driver.hideKeyboard();

		//action.click(AddOysCard.security_nxtBtn, "security_nxtBtn");
		//action.click(AddOysCard.security_nxtBtn, "security_nxtBtn");
		//AppiumDriver.hideKeyboard(); **NEED TO HIDE KEYBOARD**
		Thread.sleep(3000);

	}

	public void addHotlistedCard (ITestContext context, Hashtable<String, String> data,AppiumActions action) throws Throwable {

		//String hotlistederror = action.getText(AddOysCard.hotlistedErrorText,"hotlistedErrorText");					
		//action.waitForInVisibilityOfElement(HomePage.plusBtn,"plusBtn");
		action.click(HomePage.plusBtn, "plusBtn");
		//action.click(AddOysCard.oysCardNumber, "oysCardNumber");
		//action.waitForInVisibilityOfElement(AddOysCard.oysCardNumber,"addOysCardNum");
		AddOysCard.addOysCardScreen(context);
		action.sendKeys(AddOysCard.oysCardNumber, data.get("addOysCardNum"), "addOysCardNum"); 
		action.click(AddOysCard.nxtBtn, "nxtBtn");
		Thread.sleep(4000);
		action.isVisibleWithOutReport(AddOysCard.hotlistedErrorText, "hotlistedErrorText", true); 
		System.out.println("Cannot be added "); 
		action.successReportAppium("Verify card cannot be added", "Card cannot be added "); 

	}


	//method to verify journey history in CS

	public void journeyHistoryCheck (Hashtable<String, String> data,AppiumActions action) throws Throwable {

		String cardNumber = data.get("PrestigeCardNumber");

		String ConnectionString = "SELECT STATIONNLC,STATIONNAME, B.TXNCREATEDDATE FROM RDD_STATIONS A, DD_CARDTRANSACTION B WHERE A.STATIONNLC = B.TXNNLC and B.PRESTIGEID = "+cardNumber+" AND A.CEASEDDATE is null AND B.TXNCREATEDDATE is not null AND B.HOSTDEVICEID <>1 order by B.TXNCREATEDDATE desc";

		String stationName = txn.getDBColumnValue("oracle", ConnectionString, "STATIONNAME");

		System.out.println("stationName is **** "+stationName);

		action.sendKeys(AddOysCard.searchBtn, stationName,"searchBtn");
		action.click(AddOysCard.stationName, "stationName");
		action.click(AddOysCard.nxtBtn,"nxtBtn");
		Thread.sleep(4000);


		//action.WaitforTextPresent(HomePage.myCards, "myCards", "My Cards"); 
		int counter = 0;

		while(!(action.isVisibleWithOutReport(HomePage.myCards, "myCards", true))) {
			counter++;
			Thread.sleep(1000);
			if (counter==10) {
				break;
			}
			if (action.isVisibleWithOutReport(LoginPage.errorOKBtn, "errorOKBtn", true)) {
				//action.failureReport("Verify the ", "System encountered Network Error Please see screenshot");
				action.click(LoginPage.errorOKBtn, "errorOKBtn");
				action.click(AddOysCard.nxtBtn,"nxtBtn");
				//throw new RuntimeException("System encountered Network Error Please see screenshot");
			}
		}



	}


	public void verifyOysterCardAddedSuccessfully(ITestContext context,Hashtable<String, String> data,AppiumActions action) throws Throwable {


		AddOysCard.addOysCardScreen(context);
		String oysterCardVisibleNumberOnApp = data.get("addOysCardNum");

		action.swipeFromMidScreenToBottom();
		Thread.sleep(4000);
		action.WaitforTextPresent(HomePage.myCards, "myCards", "My Cards");
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

		if (action.isVisibleWithOutReport(AddOysCard.cardAddedNotification, "cardAddedNotification",true)) {
			String cardAddedNotification = action.getText(AddOysCard.cardAddedText, "cardAddedText");
			System.out.println("cardAddedNotification is "+cardAddedNotification);
			action.successReportAppium("Verify oyster card added successfully", cardAddedNotification);
		}
		else {
			System.out.println("cardAddedNotification is not displayed");
			action.failureReport("Verify oyster card added successfully ", "cardAddedNotification is not displayed ");


		}






	}
}






















