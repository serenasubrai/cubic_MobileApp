package com.tfl.appium.lib;

import java.util.Hashtable;

import com.cubic.appiumaction.AppiumActions;
import com.tfl.appium.page.topupCardPageIOS;

import io.appium.java_client.AppiumDriver;

public class OysterChangeCardDetailsIOS{
	public String cardNumber = "510012345678910187686";

	/**
	 * 
	 * @param data
	 * @throws Throwable
	 */

	
	/**
	 * This method is to change or to add a payment card when supplied with desired parameters on iOS device.
	 * @param data
	 * @param action
	 * @throws Throwable
	 */
	public void changeORAddPaymentCard(Hashtable<String, String> data,AppiumActions action) throws Throwable{
		action.click(topupCardPageIOS.changePaymentCard, "changePaymentCard");
		action.click(topupCardPageIOS.addPaymentCardPlusBtn, "addPaymentCardPlusBtn");
		action.click(topupCardPageIOS.cardNumberLabel, "cardNumber");
		action.sendKeys(topupCardPageIOS.cardNumber,cardNumber, "cardNumber");
		AppiumDriver driver = action.getAppiumDriver();
		driver.findElement(topupCardPageIOS.cardNumber).sendKeys(org.openqa.selenium.Keys.ENTER);
		action.click(topupCardPageIOS.paymentCardNextBtn, "nextBtn");
		cardNumber=action.getText(topupCardPageIOS.cardNumber,cardNumber);
		System.out.println("Card Number is "+cardNumber);
		action.click(topupCardPageIOS.cardHolderName, "cardHolderName");
		action.sendKeys(topupCardPageIOS.cardHolderName,"Prashanth Sannayala", "cardHolderName");
		action.click(topupCardPageIOS.expiryDate, "expiryDate");
		action.sendKeys(topupCardPageIOS.expiryDateVal,"1020", "expiryDate");
		action.click(topupCardPageIOS.addPaymentCardNextBtn, "nextBtn");
	}
	
	/**
	 * This method is to add billing address when supplied with desired parameters on iOS device.	
	 * @param data
	 * @param action
	 * @throws Throwable
	 */
	public void addBliingAddress(Hashtable<String, String> data,AppiumActions action) throws Throwable{
		String Cardlast4Digits = cardNumber.substring(cardNumber.length() -4);
		action.click(topupCardPageIOS.addressLine1Label, "addressLine1");
		action.sendKeys(topupCardPageIOS.addressLine1,"#302459 Block P", "addressLine1");
		action.click(topupCardPageIOS.addressLine2Label, "addressLine2");
		action.sendKeys(topupCardPageIOS.addressLine2,"Avalon Fremont", "addressLine2");
		action.click(topupCardPageIOS.townLabel, "town");
		action.sendKeys(topupCardPageIOS.town,"Fremont", "town");
		action.click(topupCardPageIOS.countryLabel, "country");
		action.sendKeys(topupCardPageIOS.country,"USA", "country");
		action.click(topupCardPageIOS.postCodeLabel, "postCode");
		action.sendKeys(topupCardPageIOS.postCode,"543953", "post code");
		action.click(topupCardPageIOS.billingAddressNextBtn, "billingAddressNextBtn");
		action.click(topupCardPageIOS.addCard, "addCard");
		action.WaitforTextPresent(topupCardPageIOS.verifyLast4Digits(Cardlast4Digits), "Cardlast4Digits","Ending "+Cardlast4Digits);
		
	}

}
