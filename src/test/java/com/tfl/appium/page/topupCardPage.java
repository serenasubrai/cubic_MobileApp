package com.tfl.appium.page;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.testng.ITestContext;

public class topupCardPage {

	public static By newBalance;
	public static By plusBtn;
	public static By minusBtn;	
	public static By nextBtn;
	public static By changePaymentCard;
	public static By addPaymentCardPlusBtn;
	public static By cardNumber;
	public static By cardNumberLabel;
	public static By cardHolderName;
	public static By expiryDate;
	public static By addressLine1Label;
	public static By addressLine2Label;
	public static By townLabel;
	public static By countryLabel;
	public static By postCodeLabel;
	public static By addressLine1;
	public static By addressLine2;
	public static By town;
	public static By country;
	public static By postCode;
	public static By addCard;
	public static By endingDigits;
	public static By backArrow;
	public static By cancelBtn;
	public static By topupCard;
	public static By topupCardNextBtn;
	public static By paymentCardNextBtn;
	public static By addPaymentCardNextBtn;
	public static By billingAddressNextBtn;
	public static By expiryDateVal;
	public static By last4Digits;
	public static By GBCountry;
	public static By checkBox_TermsAndConditions;
	public static By cvvTextBox;
	public static By paynowBtn;
	public static By errorText;
	public static By errorMessage;
	public static By errorOKBtn;
	public static By continueBtn_cardToppedUp;
	public static By paygAmount;
	public static By zonesDisplayed;
	public static By startDateDisplayed;
	public static By expiryDateDisplayed;
	public static By cardNumberVal;
	public static By cardNumberval;
	public static By getDisplayedStartDate;
	public static By cardNumber2;
	public static By takingPayment;
	public static By closeBtn;
	
	


	public static By verifyZonesDisplayed(ITestContext context,String zones){
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")) {
			zonesDisplayed = By.xpath("//android.widget.TextView[contains(@text,'"+zones+"')]");
		}
		else if(context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")){
			zonesDisplayed = By.id("lbl_travelcard");
		}
		return zonesDisplayed;
	}
	public static By verifyStartDateDisplayed(ITestContext context,String zones, String date){
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")) {
			//startDateDisplayed = By.xpath("//android.widget.TextView[contains(@text,'"+zones+"')]/../following-sibling::android.view.ViewGroup/android.widget.TextView[starts-with(@text,'Start:') and contains(@text,'"+date+"')]");
			//startDateDisplayed = By.xpath("//android.widget.TextView[@id='lbl_travelcard']/../following-sibling::android.view.ViewGroup/android.widget.TextView[starts-with(@text,'Start:') and contains(@text,'"+date+"')]");
			startDateDisplayed = By.xpath("//android.widget.TextView[@id='lbl_travelcard']/../following-sibling::android.view.ViewGroup/android.widget.TextView");
		}
		else if(context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")){
			startDateDisplayed = By.xpath("//XCUIElementTypeOther[contains(@name,'Start')]");
		}
		return startDateDisplayed;
	}
	public static By verifyStartDateDisplayed(ITestContext context){
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")) {
			//startDateDisplayed = By.xpath("//android.widget.TextView[contains(@text,'"+zones+"')]/../following-sibling::android.view.ViewGroup/android.widget.TextView[starts-with(@text,'Start:') and contains(@text,'"+date+"')]");
			//startDateDisplayed = By.xpath("//android.widget.TextView[@id='lbl_travelcard']/../following-sibling::android.view.ViewGroup/android.widget.TextView[starts-with(@text,'Start:') and contains(@text,'"+date+"')]");
			startDateDisplayed = By.xpath("//android.widget.TextView[@id='lbl_travelcard']/../following-sibling::android.view.ViewGroup/android.widget.TextView");
		}
		else if(context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")){
			startDateDisplayed = By.xpath("//XCUIElementTypeOther[contains(@name,'Start')]");
		}
		return startDateDisplayed;
	}
	public static By verifyExpiryDateDisplayed(ITestContext context,String zones){
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")) {
			expiryDateDisplayed = By.xpath("//android.widget.TextView[contains(@text,'"+zones+"')]/../following-sibling::android.widget.TextView[starts-with(@text,'Expires:')]");
		}
		else if(context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")){
			expiryDateDisplayed = By.xpath("//XCUIElementTypeOther[contains(@name,'Expires')]");
		}
		return expiryDateDisplayed;
	}

	public static void topupCardPageLocators(ITestContext context){
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")) {
			plusBtn=By.xpath("//android.widget.TextView[@text='+']");
			minusBtn=By.xpath("//android.widget.TextView[@text='-']");
			newBalance=By.xpath("//android.widget.TextView[@text='New balance']/following-sibling::android.widget.TextView");
			topupCardNextBtn=By.xpath("//android.widget.TextView[@text='Next']");
			paymentCardNextBtn=By.xpath("//android.widget.TextView[@text='Next']");
			addPaymentCardNextBtn=By.id("btn_next");
			//addPaymentCardNextBtn=By.xpath("//android.widget.TextView[@text='Next']");
			billingAddressNextBtn=By.xpath("//android.widget.TextView[@text='Next']");
			changePaymentCard=By.xpath("//android.widget.TextView[@text='Change/add payment card']");
			addPaymentCardPlusBtn=By.xpath("//android.widget.TextView[@text='i']");
			cardNumber2=By.id("txt_cardnumber");
			cardNumberLabel=By.xpath("(//android.widget.TextView[@text='Card number'])[last()]");
			cardNumberVal = By.xpath("//android.widget.TextView[@text='Card number']/preceding-sibling::android.widget.EditText");
			cardNumberval=By.xpath("//android.widget.TextView[@text='Card number']/preceding-sibling::android.widget.EditText");
			cardHolderName=By.id("txt_cardholder");
			//cardHolderName=By.xpath("//android.widget.TextView[@text='Card number']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText");
			expiryDate=By.id("txt_expdate");
			//expiryDate=By.xpath("(//android.widget.TextView[@text='Expiry date'])[last()]");
			expiryDateVal = By.xpath("//android.widget.TextView[@text='Expiry date']/following-sibling::android.widget.EditText");
			addressLine1Label=By.xpath("//android.widget.TextView[@text='Address line 1']");
			addressLine1=By.xpath("//android.widget.TextView[@text='Address line 1']/preceding-sibling::android.widget.EditText");
			addressLine2Label=By.xpath("//android.widget.TextView[@text='Address line 2']");
			addressLine2=By.xpath("//android.widget.TextView[@text='Address line 2']/preceding-sibling::android.widget.EditText");
			townLabel=By.xpath("//android.widget.TextView[@text='Town/City']");
			countryLabel=By.xpath("//android.widget.TextView[@text='County']");
			postCodeLabel=By.xpath("//android.widget.TextView[@text='Postcode']");
			town=By.xpath("//android.widget.TextView[@text='Town/City']/preceding-sibling::android.widget.EditText");
			country=By.xpath("//android.widget.TextView[@text='County']/preceding-sibling::android.widget.EditText");
			postCode=By.xpath("//android.widget.TextView[@text='Postcode']/preceding-sibling::android.widget.EditText");
			GBCountry=By.xpath("//android.widget.EditText[@text='GB']");
			addCard=By.id("btn_next");
			//addCard=By.xpath("//android.widget.TextView[@text='Add card']");
			endingDigits=By.xpath("//android.widget.TextView[@text='Ending 5100']");
			backArrow=By.xpath("//android.widget.TextView[@text='a']");
			cancelBtn=By.xpath("//android.widget.TextView[@text='Cancel']");
			topupCard=By.xpath("//android.widget.TextView[@text='Top up Card']");
			checkBox_TermsAndConditions= By.xpath("//android.widget.TextView[@text='Terms & Conditions']/../../android.view.ViewGroup/android.widget.ImageView");
			//checkBox_TermsAndConditions= By.xpath("//android.widget.TextView[@contains(@text,'I accept')]/preceding-sibling::android.view.ViewGroup/android.widget.ImageView");
			cvvTextBox= By.xpath("//android.widget.EditText[@text='CVV']");
			paynowBtn= By.xpath("//android.widget.TextView[@text='Pay now']");
			takingPayment = By.xpath("//android.widget.TextView[@content-desc='lbl_text1']");
			errorText=By.xpath("//android.widget.TextView[@text='Error']");
			errorMessage = By.xpath("//android.widget.TextView[@text='Error']/following-sibling::android.widget.TextView");
			errorOKBtn= By.xpath("//android.widget.TextView[@text='OK']");
			continueBtn_cardToppedUp = By.xpath("//android.widget.TextView[@text='Continue']");
			closeBtn =By.xpath("//android.widget.TextView[@text='Close']");
			paygAmount = By.xpath("//android.widget.TextView[@text='-']/../following-sibling::android.widget.TextView");
			getDisplayedStartDate= By.xpath("//android.widget.TextView[@content-desc='lbl_travelcard']/../following-sibling::android.view.ViewGroup/android.widget.TextView");
			//getDisplayedStartDate= By.xpath("//android.widget.TextView[@content-desc='lbl_travelcard']");
			//getDisplayedStartDate= By.id("lbl_travelcard");
			
		}
		else if(context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")){
			plusBtn=By.xpath("(//*[contains(@label,'+')])[last()]");
			minusBtn=By.xpath("//XCUIElementTypeOther[@name=\"btn_decrease\"]");
			newBalance=By.xpath("//XCUIElementTypeStaticText[@value='New balance']/following-sibling::XCUIElementTypeStaticText");
			paygAmount = By.xpath("//XCUIElementTypeOther[@name=\"btn_decrease\"]/following-sibling::XCUIElementTypeStaticText");
			topupCardNextBtn=By.xpath("//XCUIElementTypeStaticText[@value='New balance']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther/XCUIElementTypeOther");
			changePaymentCard=By.xpath("(//XCUIElementTypeOther[contains(@label,'Change')])[last()]");
			addPaymentCardPlusBtn=By.xpath("(//XCUIElementTypeOther[contains(@label,'Add a payment')])[last()]");
			cardNumberLabel=By.xpath("(//XCUIElementTypeOther[contains(@label,'Card number')])[last()]");
			cardNumber=By.id("txt_cardnumber");
			//cardNumber=By.xpath("//XCUIElementTypeStaticText[@value='Card number']/following-sibling::XCUIElementTypeOther/XCUIElementTypeTextField");
			paymentCardNextBtn=By.xpath("//XCUIElementTypeStaticText[@value='Card number']/../../following-sibling::XCUIElementTypeOther[2]/XCUIElementTypeOther");
			addPaymentCardNextBtn=By.id("btn_next");
			//addPaymentCardNextBtn = By.xpath("//XCUIElementTypeOther[@name=\"btn_next\"]");
			billingAddressNextBtn=By.xpath("//XCUIElementTypeOther[@name=\"btn_next\\\"]");
			cardHolderName=By.id("txt_cardholder");
			//cardHolderName=By.xpath("//XCUIElementTypeTextField[@name=\"txt_cardholder\"]");
			expiryDate=By.id("txt_expdate");
			//expiryDate=By.xpath("//XCUIElementTypeTextField[@value='Expiry date']");
			expiryDateVal = By.xpath("//XCUIElementTypeTextField[@value='MM/YY']");
			addressLine1Label=By.id("txt_addr1");
			addressLine1=By.id("txt_addr1");
			//addressLine1Label=By.xpath("//XCUIElementTypeTextField[@name=\"txt_addr1\"]");
			//addressLine1=By.xpath("//XCUIElementTypeTextField[@name=\"txt_addr1\"]");
			addressLine2Label=By.id("txt_addr2");
			addressLine2=By.id("txt_addr2");
			/*addressLine2Label=By.xpath("//XCUIElementTypeTextField[@name=\"txt_addr2\"]");
			addressLine2=By.xpath("//XCUIElementTypeTextField[@name=\"txt_addr2\"]");*/
			townLabel=By.id("txt_city");
			town=By.id("txt_city");
			countryLabel=By.id("txt_county");
			country=By.id("txt_county");
			postCodeLabel=By.id("txt_postcode");
			postCode=By.id("txt_postcode");
			GBCountry=By.id("txt_country");
			//townLabel=By.xpath("//XCUIElementTypeTextField[@name=\"txt_city\"]");
			//countryLabel=By.xpath("//XCUIElementTypeTextField[@name=\"txt_county\"]");
			//postCodeLabel=By.xpath("//XCUIElementTypeTextField[@name=\"txt_postcode\"]");
			/*town=By.xpath("//XCUIElementTypeTextField[@name=\"txt_city\"]");
			country=By.xpath("//XCUIElementTypeTextField[@name=\"txt_county\"]");
			postCode=By.xpath("//XCUIElementTypeTextField[@name=\"txt_postcode\"]");
			GBCountry=By.id("txt_country");*/
			//GBCountry=By.xpath("//XCUIElementTypeTextField[@name=\"txt_country\"]");
			addCard=By.xpath("//XCUIElementTypeStaticText[@value='Save my payment card']/../../following-sibling::*/*[contains(@label,'Add card')]");
			endingDigits=By.xpath("//android.widget.TextView[@text='Ending 5100']");
			topupCard=By.xpath("//XCUIElementTypeStaticText[@value='Top up Card']");
			cvvTextBox= By.xpath("//XCUIElementTypeTextField[@name=\"txt_cvc\"]");
			paynowBtn= By.xpath("//XCUIElementTypeOther[@name=\"btn_payNow\"]");
			continueBtn_cardToppedUp = By.xpath("//XCUIElementTypeOther[@name=\"btn_continue\"]");
			errorOKBtn= By.xpath("(//XCUIElementTypeOther[contains(@label,'OK')])[last()]");
			errorMessage = By.xpath("(//XCUIElementTypeOther[contains(@label,'OK')])[last()]/../preceding-sibling::XCUIElementTypeOther/XCUIElementTypeStaticText");
			cancelBtn= By.xpath("(//XCUIElementTypeOther[contains(@label,'Cancel')])[last()]");
			backArrow=By.xpath("//XCUIElementTypeOther[@name=\" Top up\"]/preceding-sibling::XCUIElementTypeOther");
			closeBtn=By.xpath("//XCUIElementTypeOther[@name=\"carddeck\"]");
			getDisplayedStartDate= By.xpath("//XCUIElementTypeOther[contains(@name,'Start')]");
		}
	}
	public static By verifyLast4Digits(ITestContext context,String data){
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")) {
			last4Digits= By.xpath("//android.widget.TextView[@text='Ending "+data+"']");
			
		}
		else if(context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")){
			last4Digits= By.xpath("//XCUIElementTypeStaticText[@value='Ending "+data+"']");
		}
		return last4Digits;

	}

}
