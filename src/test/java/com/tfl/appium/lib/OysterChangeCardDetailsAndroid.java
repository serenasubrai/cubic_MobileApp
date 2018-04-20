package com.tfl.appium.lib;

import java.util.Hashtable;

import com.cubic.appiumaction.AppiumActions;
import com.tfl.appium.page.topupCardPageAndroid;

public class OysterChangeCardDetailsAndroid{

	/**
	 * 
	 * @param data
	 * @throws Throwable
	 */

	
/**
 * This method is to change or to add a payment card when supplied with desired parameters on android device.
 * @param data
 * @param action
 * @throws Throwable
 */
	public void changeORAddPaymentCard(Hashtable<String, String> data,AppiumActions action) throws Throwable{
		action.click(topupCardPageAndroid.changePaymentCard, "changePaymentCard");
		action.click(topupCardPageAndroid.addPaymentCardPlusBtn, "addPaymentCardPlusBtn");
		action.click(topupCardPageAndroid.cardNumberLabel, "cardNumber");
		action.sendKeys(topupCardPageAndroid.cardNumber,"5105105105105100", "cardNumber");
		action.click(topupCardPageAndroid.nextBtn, "nextBtn");
		action.click(topupCardPageAndroid.cardHolderName, "cardHolderName");
		action.sendKeys(topupCardPageAndroid.cardHolderName,"Prashanth Sannayala", "cardHolderName");
		action.click(topupCardPageAndroid.expiryDate, "expiryDate");
		action.sendKeys(topupCardPageAndroid.expiryDateVal,"1020", "expiryDate");
		action.click(topupCardPageAndroid.nextBtn, "nextBtn");
	}

/**
 * This method is to add billing address when supplied with desired parameters on android device.	
 * @param data
 * @param action
 * @throws Throwable
 */
	public void addBliingAddress(Hashtable<String, String> data,AppiumActions action) throws Throwable{
		action.click(topupCardPageAndroid.addressLine1Label, "addressLine1");
		action.sendKeys(topupCardPageAndroid.addressLine1,"#302459 Block P", "addressLine1");
		action.click(topupCardPageAndroid.addressLine2Label, "addressLine2");
		action.sendKeys(topupCardPageAndroid.addressLine2,"Avalon Fremont", "addressLine2");
		action.click(topupCardPageAndroid.townLabel, "town");
		action.sendKeys(topupCardPageAndroid.town,"Fremont", "town");
		action.click(topupCardPageAndroid.countryLabel, "country");
		action.sendKeys(topupCardPageAndroid.country,"USA", "country");
		action.click(topupCardPageAndroid.postCodeLabel, "postCode");
		action.sendKeys(topupCardPageAndroid.postCode,"543953", "caexpipostCoderyDaterdNumber");
		action.click(topupCardPageAndroid.nextBtn, "nextBtn");
		action.click(topupCardPageAndroid.addCard, "addCard");
		action.click(topupCardPageAndroid.addCard, "addCard");
		action.waitForElementPresent(topupCardPageAndroid.topupCard, "topupCard");
		
	}

}
