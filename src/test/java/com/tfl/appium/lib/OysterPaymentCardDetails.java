package com.tfl.appium.lib;

import java.sql.ResultSet;
import java.util.Hashtable;

import org.openqa.selenium.WebElement;
import org.testng.ITestContext;

import com.cubic.appiumaction.AppiumActions;
import com.cubic.database.DataBaseUtil;
import com.tfl.appium.page.HomePage;
import com.tfl.appium.page.LoginPage;
import com.tfl.appium.page.addTravelCardPage;
import com.tfl.appium.page.journeyHistoryPage;
import com.tfl.appium.page.topupCardPage;

import io.appium.java_client.AppiumDriver;

public class OysterPaymentCardDetails{
	public String cardNumberVal = "";
	
	OysterCreateTransaction txn = new OysterCreateTransaction();
	/**
	 *  This method is used to add a new payment card. 
	 * @param data
	 * @throws Throwable
	 */

	public void addNewPaymentCard(ITestContext context,Hashtable<String, String> data,AppiumActions action) throws Throwable{
		topupCardPage.topupCardPageLocators(context);
		action.click(topupCardPage.changePaymentCard, "changePaymentCard");
		while (!action.isVisibleWithOutReport(topupCardPage.addPaymentCardPlusBtn, "addPaymentCardPlusBtn",true)) {
			action.swipeBottomToTop();
		}
		action.click(topupCardPage.addPaymentCardPlusBtn, "addPaymentCardPlusBtn");
		@SuppressWarnings("rawtypes")
		AppiumDriver driver = action.getAppiumDriver();
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")) {
			//action.click(topupCardPage.cardNumberLabel, "cardNumberLabel");
			//action.click(topupCardPage.cardNumberLabel, "cardNumberLabel");
			action.sendKeys(topupCardPage.cardNumber2,data.get("CardNumber"), "cardNumber");
			//driver.hideKeyboard();
			//action.click(topupCardPage.paymentCardNextBtn, "nextBtn");
			cardNumberVal = action.getText(topupCardPage.cardNumberVal, "cardNumberVal");
			//action.click(topupCardPage.cardNumberLabel, "cardNumber");
			//action.sendKeys(topupCardPage.cardNumber,data.get("CardNumber"), "cardNumber");
			//driver.hideKeyboard();
			//action.click(topupCardPage.paymentCardNextBtn, "nextBtn");
			//cardNumberVal = action.getText(topupCardPage.cardNumberval, "cardNumberval");
			action.click(topupCardPage.cardHolderName, "cardHolderName");
			action.sendKeys(topupCardPage.cardHolderName,data.get("CardHolderName"), "cardHolderName");
			driver.hideKeyboard();
			action.click(topupCardPage.expiryDate, "expiryDate");
			AppiumDriver driver2 = action.getAppiumDriver();
			WebElement el = driver2.findElement(topupCardPage.expiryDate);
			String text = data.get("ExpiryDate");
			//Character ch ;
			for(Character ch : text.toCharArray()){
				el.sendKeys(ch+"");
				Thread.sleep(10);
			}
			//action.sendKeys(topupCardPage.expiryDate,data.get("ExpiryDate"), "expiryDate");	
			driver.hideKeyboard();

		}else{
			action.click(topupCardPage.cardNumberLabel, "cardNumber");
			action.sendKeys(topupCardPage.cardNumber,data.get("CardNumber"), "cardNumber");
			cardNumberVal = action.getText(topupCardPage.cardNumber, "cardNumberVal");
			action.click(topupCardPage.cardHolderName, "cardHolderName");
			action.sendKeys(topupCardPage.cardHolderName,data.get("CardHolderName"), "cardHolderName");
			action.click(topupCardPage.expiryDate, "expiryDate");
			action.sendKeys(topupCardPage.expiryDateVal,data.get("ExpiryDate"), "expiryDate");	
		}
		action.click(topupCardPage.addPaymentCardNextBtn, "nextBtn");
	}

	/**
	 *  This method is used to make a payment with the existing card when performing a TopUp and returns true or false based on the Successful payment. 
	 * @param context
	 * @param data
	 * @param action
	 * @return
	 * @throws Throwable
	 */
	public Boolean payWithExistingCard(ITestContext context,Hashtable<String, String> data,AppiumActions action) throws Throwable{
		boolean flag = false;
		try {
			topupCardPage.topupCardPageLocators(context);
			action.sendKeys(topupCardPage.cvvTextBox, data.get("PaymentCardCVVNumber"),"cvvTextBox checkBox");
			action.click(topupCardPage.paynowBtn, "paynowBtn");
			/*while(action.isVisibleWithOutReport(topupCardPage.takingPayment, "takingPayment",true)){
				continue;
			}*/
			Thread.sleep(8000);
			if ((action.isVisibleWithOutReport(topupCardPage.continueBtn_cardToppedUp, "continueBtn_cardToppedUp",true))){
				verifyPayment(context, data, action);
			}
			else{
				flag = verifyPaymentError(context, data, action);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		return flag;

	}
	
	/**
	 * This method is used to  make a payment with new payment card when performing a TopUp. 
	 * @param context
	 * @param data
	 * @param action
	 * @throws Throwable
	 */
	public void payWithNewCard(ITestContext context,Hashtable<String, String> data,AppiumActions action) throws Throwable{
		try {
			topupCardPage.topupCardPageLocators(context);
			HomePage.homePageLocators(context);
			String Cardlast4Digits = cardNumberVal.substring(cardNumberVal.length() -4);
			action.WaitforTextPresent(topupCardPage.verifyLast4Digits(context,Cardlast4Digits), "Cardlast4Digits","Ending "+Cardlast4Digits);
			topupCardPage.topupCardPageLocators(context);
			action.sendKeys(topupCardPage.cvvTextBox, data.get("PaymentCardCVVNumber"),"cvvTextBox checkBox");
			action.click(topupCardPage.paynowBtn, "paynowBtn");
			/*while(action.isVisibleWithOutReport(topupCardPage.takingPayment, "takingPayment",true)){
				continue;
			}*/
			Thread.sleep(8000);
			if ((action.isVisibleWithOutReport(topupCardPage.continueBtn_cardToppedUp, "continueBtn_cardToppedUp",true))){
				verifyPayment(context, data, action);
			}
			else{
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

	}

	/**
	 * This method is used to verify whether the TopUp is Successful or not. 
	 * @param context
	 * @param data
	 * @param action
	 * @throws Throwable
	 */
	public void verifyPayment(ITestContext context,Hashtable<String, String> data,AppiumActions action) throws Throwable{
		try {
			HomePage.homePageLocators(context);
			topupCardPage.topupCardPageLocators(context);
			action.waitForElementPresent(topupCardPage.continueBtn_cardToppedUp, "continueBtn_cardToppedUp");
			action.click(topupCardPage.continueBtn_cardToppedUp, "continueBtn_cardToppedUp");
			action.WaitforTextPresent(HomePage.myCards, "myCards", "My cards");
			//action.successReportAppium("Verify the oyster card is topped up on the mobile app ", "Top up has been performed from the mobile app");
			/*if (action.isVisibleWithOutReport(HomePage.oysterCardBalance, "oysterCardBalance",true)) {
				action.click(HomePage.closeBtn, "closeBtn");
			}*/
			action.successReportAppium("Verify the oyster card is topped up on the mobile app ", "Top up has been performed from the mobile app");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * This method is used to verify whether the payment is successful or we have a error when performing a TopUp and returns true or false based on the end result.
	 * @param context
	 * @param data
	 * @param action
	 * @return
	 * @throws Throwable
	 */
	public Boolean verifyPaymentError(ITestContext context,Hashtable<String, String> data,AppiumActions action) throws Throwable{
		boolean paymentErrorFlag = false;
		try {
				addTravelCardPage.travelCardPageLocators(context);
				if (action.isVisibleWithOutReport(addTravelCardPage.unableToPurchase, "unableToPurchase",true)) {
					String errorMessage = action.getText(addTravelCardPage.unableToPurchaseErrorMessage, "unableToPurchaseErrorMessage");
					System.out.println(errorMessage);
					if(!context.getCurrentXmlTest().getXmlClasses().toString().contains("TC_Error_03_Error70PPVAMOUNTWOULDEXCEEDMAXALLOWEDPPVMobileAppDoesNotAllowPAYGAmountToGoOver£90")){
						action.failureReport("Verify Travel card is selected", "Unable to select travel card because of errror message '"+errorMessage+"'");
						action.click(topupCardPage.errorOKBtn, "errorOKBtn");
						throw new RuntimeException(errorMessage);
					}else {
						action.click(topupCardPage.errorOKBtn, "errorOKBtn");
						paymentErrorFlag = true;
					}
					
				}/*else if(action.isVisibleWithOutReport(addTravelCardPage.topUpExceedsMaxBalance, "topUpExceedsMaxBalance",true)){
					if (action.getText(addTravelCardPage.topUpExceedsMaxBalance, "topUpExceedsMaxBalance").equalsIgnoreCase("Topup exceeds maximum balance")) {
						action.click(addTravelCardPage.topUpExceedsOKBtn, "topUpExceedsOKBtn");
						throw new RuntimeException("top up failed please see the screenshot");
					}
					System.out.println("*********** Creating journey Scenarios ************");
					txn.createJourneyScenarios(data, action);
					topupCardPage.topupCardPageLocators(context);
					action.sendKeys(topupCardPage.cvvTextBox, data.get("PaymentCardCVVNumber"),"cvvTextBox checkBox");
					action.click(topupCardPage.paynowBtn, "paynowBtn");
					if ((action.isVisibleWithOutReport(topupCardPage.continueBtn_cardToppedUp, "continueBtn_cardToppedUp",true))){
						verifyPayment(context, data, action);
					}
					else if(action.isVisibleWithOutReport(topupCardPage.errorOKBtn, "errorText",true)){
						action.failureReport("Verify the top up done from the application", "top up failed please see the screenshot ");
						throw new RuntimeException("top up failed please see the screenshot ");
					}
				}*/
				else if (action.isVisibleWithOutReport(topupCardPage.errorOKBtn, "errorText",true)) {
					action.click(topupCardPage.errorOKBtn, "errorOKBtn");
					topupCardPage.topupCardPageLocators(context);
					action.sendKeys(topupCardPage.cvvTextBox, data.get("PaymentCardCVVNumber"),"cvvTextBox checkBox");
					action.click(topupCardPage.paynowBtn, "paynowBtn");
					if ((action.isVisibleWithOutReport(topupCardPage.continueBtn_cardToppedUp, "continueBtn_cardToppedUp",true))){
						verifyPayment(context, data, action);
					}
					else if(action.isVisibleWithOutReport(topupCardPage.errorOKBtn, "errorText",true)){
						action.click(topupCardPage.errorOKBtn, "errorOKBtn");
						if(context.getCurrentXmlTest().getXmlClasses().toString().contains("TC_Error_03_Error70PPVAMOUNTWOULDEXCEEDMAXALLOWEDPPVMobileAppDoesNotAllowPAYGAmountToGoOver£90")){
							action.successReportAppium("Verify the oyster card is topped up on the mobile app ", "Top up has failed when performed from the mobile app, please see the attachment");
						}
						else {
							action.failureReport("Verify the top up done from the application", "top up failed please see the screenshot ");
						}
						paymentErrorFlag = true;
					}

				}
				else if((action.isVisibleWithOutReport(topupCardPage.continueBtn_cardToppedUp, "continueBtn_cardToppedUp",true))){
					action.waitForElementPresent(topupCardPage.continueBtn_cardToppedUp, "continueBtn_cardToppedUp");
					action.click(topupCardPage.continueBtn_cardToppedUp, "continueBtn_cardToppedUp");
					action.WaitforTextPresent(HomePage.myCards, "myCards", "My cards");
					//action.successReportAppium("Verify the oyster card is topped up on the mobile app ", "Top up has been performed from the mobile app");
					/*if (action.isVisibleWithOutReport(topupCardPage.closeBtn, "closeBtn",true)) {
						action.click(HomePage.closeBtn, "closeBtn");
					}*/
					action.successReportAppium("Verify the oyster card is topped up on the mobile app ", "Top up has been performed from the mobile app");
				}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return paymentErrorFlag;


	}
	
	/**
	 * This method is used to verify whether error message is shown or not when tried to perform TopUp on a Hotlisted card.
	 * @param context
	 * @param data
	 * @param action
	 * @throws Throwable
	 */
	public void verifyErrorCodeHotlistedCard(ITestContext context,Hashtable<String, String> data,AppiumActions action) throws Throwable{
		try {
			topupCardPage.topupCardPageLocators(context);
			/*action.waitForElementPresent(topupCardPage.checkBox_TermsAndConditions, "TermsAndConditions checkBox");
			action.click(topupCardPage.checkBox_TermsAndConditions, "TermsAndConditions checkBox");*/
			action.sendKeys(topupCardPage.cvvTextBox, data.get("PaymentCardCVVNumber"),"cvvTextBox checkBox");
			action.click(topupCardPage.paynowBtn, "paynowBtn");
			addTravelCardPage.travelCardPageLocators(context); //verify what this does
			action.waitForElementPresent(addTravelCardPage.cardHotlisted, "cardHotlisted");
			if (action.isVisible(addTravelCardPage.cardHotlisted, "cardHotlisted")) {
				String errorMessage = action.getText(addTravelCardPage.cardHotlistedErrorMessage, "cardHotlistedErrorMessage");
				action.successReportAppium("Verify the Hotlisted card Error message shown on the App", "Successfully displayed the Error message as'"+errorMessage+"'");
			}
			else{
				action.click(topupCardPage.continueBtn_cardToppedUp, "continueBtn_cardToppedUp");
				action.WaitforTextPresent(HomePage.myCards, "myCards", "My Cards");
				action.failureReport("Verify the Hotlisted Error message shown on the App ", "Error Message not displayed");
			}
			addTravelCardPage.travelCardPageLocators(context);
			action.waitForElementPresent(addTravelCardPage.cardHotlistedErrorMessage, "cardHotlisted");
			action.click(addTravelCardPage.OKBtn, "OKBtn");

		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

	}

	/**
	 * This method is used to verify the error code shown in database when tried to perform TopUp on a Hotlisted card.
	 * @param context
	 * @param data
	 * @param action
	 * @throws Throwable
	 */
	public void verifyErrorCodeForHotListedCard(ITestContext context,Hashtable<String, String> data,AppiumActions action) throws Throwable{
		try {
			String cardNumber = data.get("PrestigeCardNumber");

			String requestFailure = OysterCreateTransaction.getDBColumnValue("Oracle","select * from cdsprod1.FUL_REQUESTFAILURES  where prestigeid = '"+cardNumber+"' and failurecode = '190' order by createddate desc", "FAILURECODE");

			if (requestFailure.equalsIgnoreCase("190")) {
				action.successReport("Verify error code", "Error code verified successfully and displayed as " + requestFailure);			
			}
			else{
				action.failureReport("Verify error code", "Error code is NOT verified");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}


	}

	/**
	 * This method is used to verify whether error message is shown or not when tried to perform TopUp on a card and the balance is more than £90.
	 * @param context
	 * @param data
	 * @param action
	 * @throws Throwable
	 */
	public void verifyErrorCodeMaximumBalanceExceedsMessage(ITestContext context,Hashtable<String, String> data,AppiumActions action) throws Throwable{
		try {
			topupCardPage.topupCardPageLocators(context);
			/*action.waitForElementPresent(topupCardPage.checkBox_TermsAndConditions, "TermsAndConditions checkBox");
			action.click(topupCardPage.checkBox_TermsAndConditions, "TermsAndConditions checkBox");*/
			action.sendKeys(topupCardPage.cvvTextBox, data.get("PaymentCardCVVNumber"),"cvvTextBox checkBox");
			action.click(topupCardPage.paynowBtn, "paynowBtn");
			addTravelCardPage.travelCardPageLocators(context);
			action.waitForElementPresent(addTravelCardPage.topUpExceedsMaxBalance, "topUpExceedsMaxBalance");
			String messageDisplayed = action.getText(addTravelCardPage.topUpExceedsMaxBalance, "topUpExceedsMaxBalance");
			if (action.isVisibleWithOutReport(addTravelCardPage.topUpExceedsMaxBalance, "topUpExceedsMaxBalance",true)) {
				String errorMessage = action.getText(addTravelCardPage.topUpExceedsMaxBalanceErrorMessage, "topUpExceedsMaxBalanceErrorMessage");
				if(messageDisplayed.equalsIgnoreCase(errorMessage)) {
					action.successReportAppium("Verify the Maximum Balance Error message shown on the App", "Successfully displayed the Error message as'"+errorMessage+"'");
				}
			}
			else{
				action.click(topupCardPage.continueBtn_cardToppedUp, "continueBtn_cardToppedUp");
				action.WaitforTextPresent(HomePage.myCards, "myCards", "My cards");
				action.failureReport("Verify the Maximum Balance Error message shown on the App ", "Error Message not displayed");
			}
			addTravelCardPage.travelCardPageLocators(context);
			action.waitForElementPresent(addTravelCardPage.topUpExceedsMaxBalance, "topUpExceedsMaxBalance");
			action.click(addTravelCardPage.OKBtn, "OKBtn");

		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

	}

	/**
	 * This method is used to verify the error code shown in database when tried to perform TopUp on a card and the balance is more than £90.
	 * @param context
	 * @param data
	 * @param action
	 * @throws Throwable
	 */
	public void verifyErrorCodeMaximumRequestsExceedsMessage(ITestContext context,Hashtable<String, String> data,AppiumActions action) throws Throwable{
		try {
			topupCardPage.topupCardPageLocators(context);
			/*action.waitForElementPresent(topupCardPage.checkBox_TermsAndConditions, "TermsAndConditions checkBox");
			action.click(topupCardPage.checkBox_TermsAndConditions, "TermsAndConditions checkBox");*/
			action.sendKeys(topupCardPage.cvvTextBox, data.get("PaymentCardCVVNumber"),"cvvTextBox checkBox");
			action.click(topupCardPage.paynowBtn, "paynowBtn");
			addTravelCardPage.travelCardPageLocators(context);
			if (action.isVisibleWithOutReport(addTravelCardPage.unableToPurchase, "unableToPurchase",true)) {
				String errorMessage = action.getText(addTravelCardPage.unableToPurchaseErrorMessage, "unableToPurchaseErrorMessage");
				action.successReportAppium("Verify the Maximum Travel Cards Error message shown on the App", "Successfully displayed the Error message as'"+errorMessage+"'");
			}
			else{
				action.click(topupCardPage.continueBtn_cardToppedUp, "continueBtn_cardToppedUp");
				action.WaitforTextPresent(HomePage.myCards, "myCards", "My Cards");
				action.failureReport("Verify the Maximum Travel Cards Error message shown on the App ", "Error Message not displayed");
			}
			addTravelCardPage.travelCardPageLocators(context);
			action.click(addTravelCardPage.OKBtn, "OKBtn");

		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}

	}

	/**
	 * This method is used to verify the error code shown in database when tried to perform TopUp for more than allowed no.of.times.
	 * @param context
	 * @param data
	 * @param action
	 */
	public void getDBErrorCodeForMaximumRequestsExceedsMessage(ITestContext context,Hashtable<String, String> data,AppiumActions action){
		try {
			String cardNumber = data.get("PrestigeCardNumber");
			;
			String requestFailure = OysterCreateTransaction.getDBColumnValue("Oracle","select * from cdsprod1.FUL_REQUESTFAILURES where prestigeid = "+cardNumber+" and REQUESTTYPE like '%ALC%' order by DAYKEY desc", "FAILURECODE");

			if (requestFailure.equalsIgnoreCase("520")) {
				action.successReport("Verify error code", "Error code verified successfully and displayed as " + requestFailure);			
			}
			else{
				action.failureReport("Verify error code", "Error code is NOT verified");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/**
	 * This method is used to verify the error code shown in database when tried to perform TopUp on a card and the balance is more than £90.
	 * @param context
	 * @param data
	 * @param action
	 */
	public void getDBErrorCodeForMaximumBalanceExceedsMessage(ITestContext context,Hashtable<String, String> data,AppiumActions action){
		try {
			String cardNumber = data.get("PrestigeCardNumber");
			;
			String requestFailure = OysterCreateTransaction.getDBColumnValue("Oracle","select * from cdsprod1.FUL_REQUESTFAILURES where prestigeid = "+cardNumber+" and REQUESTTYPE like '%PPV%' order by DAYKEY desc", "FAILURECODE");

			if (requestFailure.equalsIgnoreCase("70")) {
				action.successReport("Verify error code", "Error code verified successfully and displayed as " + requestFailure);			
			}
			else{
				action.failureReport("Verify error code", "Error code is NOT verified");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/**
	 * This method is used to verify the error code shown in database when tried to buy Tickets for more than allowed no.of.times.
	 * @param context
	 * @param data
	 * @param action
	 */
	public void getDBErrorCodeForMaximumTicketExceedsMessage(ITestContext context,Hashtable<String, String> data,AppiumActions action){
		try {
			String cardNumber = data.get("PrestigeCardNumber");
			String requestFailure = OysterCreateTransaction.getDBColumnValue("Oracle","select * from cdsprod1.FUL_REQUESTFAILURES where prestigeid = "+cardNumber+" and REQUESTTYPE like '%PPT%' order by DAYKEY desc", "FAILURECODE");

			if (requestFailure.equalsIgnoreCase("60")) {
				action.successReport("Verify error code", "Error code verified successfully and displayed as " + requestFailure);			
			}
			else{
				action.failureReport("Verify error code", "Error code is NOT verified");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	/**
	 * This method is used to verify the error message shown when tried to buy Travel cards for more than allowed.
	 * @param context
	 * @param data
	 * @param action
	 * @return
	 * @throws Throwable
	 */
	public boolean verifyErrorCodeMaximumTravelCardExceedsMessage(ITestContext context,Hashtable<String, String> data,AppiumActions action) throws Throwable{
		boolean continueFlag = false; 
		try {
			topupCardPage.topupCardPageLocators(context);
			/*action.waitForElementPresent(topupCardPage.checkBox_TermsAndConditions, "TermsAndConditions checkBox");
			action.click(topupCardPage.checkBox_TermsAndConditions, "TermsAndConditions checkBox");*/
			action.sendKeys(topupCardPage.cvvTextBox, data.get("PaymentCardCVVNumber"),"cvvTextBox checkBox");
			action.click(topupCardPage.paynowBtn, "paynowBtn");
			addTravelCardPage.travelCardPageLocators(context);
			if (action.isVisibleWithOutReport(addTravelCardPage.unableToPurchase, "unableToPurchase",true)) {
				String errorMessage = action.getText(addTravelCardPage.unableToPurchaseErrorMessage, "unableToPurchaseErrorMessage");
				if(errorMessage.equalsIgnoreCase("You already have the maximum 3 valid Travelcards on your Oyster card.")) {
					action.successReportAppium("Verify the Maximum Travel Cards Error message shown on the App", "Successfully displayed the Error message as'"+errorMessage+"'");
				}	
				action.click(addTravelCardPage.OKBtn, "OKBtn");
				action.click(addTravelCardPage.buyTravlCardBackBtn, "buyTravlCardBackBtn");
				action.click(addTravelCardPage.buyTravlCardBackBtn, "buyTravlCardBackBtn");
				action.click(addTravelCardPage.cancel, "cancel");
			}
			else{
				action.click(topupCardPage.continueBtn_cardToppedUp, "continueBtn_cardToppedUp");
				if (action.isVisibleWithOutReport(topupCardPage.closeBtn, "closeBtn",true)) {
					action.click(HomePage.closeBtn, "closeBtn");
					continueFlag = true;
				}
				/*action.WaitforTextPresent(HomePage.myCards, "myCards", "My Cards");
				action.failureReport("Verify the Maximum Travel Cards Error message shown on the App ", "Error Message not displayed");*/
			}
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		return continueFlag;

	}

	
	/**
	 * This method is used to add a new billing address.
	 * @param context
	 * @param data
	 * @param action
	 * @throws Throwable
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public void addBliingAddress(ITestContext context,Hashtable<String, String> data,AppiumActions action) throws Throwable{		
		AppiumDriver driver = action.getAppiumDriver();
		topupCardPage.topupCardPageLocators(context);
		String Cardlast4Digits = cardNumberVal.substring(cardNumberVal.length() -4);
		action.click(topupCardPage.addressLine1Label, "addressLine1");
		action.sendKeys(topupCardPage.addressLine1,data.get("AddressLine1"), "addressLine1");
		driver.hideKeyboard();
		action.click(topupCardPage.addressLine2Label, "addressLine2");
		action.sendKeys(topupCardPage.addressLine2,data.get("AddressLine2"), "addressLine2");
		driver.hideKeyboard();
		action.click(topupCardPage.townLabel, "town");
		action.sendKeys(topupCardPage.town,data.get("Town"), "town");
		driver.hideKeyboard();
		action.click(topupCardPage.countryLabel, "country");
		action.sendKeys(topupCardPage.country,data.get("Country"), "country");
		driver.hideKeyboard();
		/*action.click(topupCardPage.postCodeLabel, "postCode");
		driver.hideKeyboard();*/
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")) {
			driver.findElement(topupCardPage.postCode).sendKeys(data.get("PostCode")+"\n");
			driver.findElement(topupCardPage.GBCountry).sendKeys("\n");
			//action.click(topupCardPage.addCard, "addCard");	
			//action.click(topupCardPage.addCard, "addCard");
		}
		else{
			action.sendKeys(topupCardPage.postCode,data.get("PostCode"), "post code");
			driver.findElement(topupCardPage.GBCountry).sendKeys("\n");
			//driver.findElement(topupCardPage.GBCountry).sendKeys("\n");
			action.click(topupCardPage.addPaymentCardNextBtn, "checkCardDetNextBtn");
		}
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")) {
			action.click(topupCardPage.addCard, "addCard");	
		}
	}

}
