package com.tfl.appium.page;

import org.openqa.selenium.By;

public class topupCardPageIOS {

	public static By newBalance;
	public static By plusBtn;
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
	public static By expiryDateVal;
	public static By topupCardNextBtn;
	public static By paymentCardNextBtn;
	public static By addPaymentCardNextBtn;
	public static By billingAddressNextBtn;
	public static By last4Digits;
	
	
	/* Page Objects Of Home Page */
	static {
		plusBtn=By.xpath("(//*[contains(@label,'+')])[last()]");
		newBalance=By.xpath("//XCUIElementTypeStaticText[@value='New balance']/following-sibling::XCUIElementTypeStaticText");
		topupCardNextBtn=By.xpath("//XCUIElementTypeStaticText[@value='New balance']/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther/XCUIElementTypeOther");
		changePaymentCard=By.xpath("(//XCUIElementTypeOther[contains(@label,'Change')])[last()]");
		addPaymentCardPlusBtn=By.xpath("(//XCUIElementTypeOther[contains(@label,'Add a payment')])[last()]");
		cardNumberLabel=By.xpath("(//XCUIElementTypeOther[contains(@label,'Card number')])[last()]");
		cardNumber=By.xpath("//XCUIElementTypeStaticText[@value='Card number']/following-sibling::XCUIElementTypeOther");
		paymentCardNextBtn=By.xpath("//XCUIElementTypeStaticText[@value='Card number']/../../following-sibling::XCUIElementTypeOther[2]/XCUIElementTypeOther");
		addPaymentCardNextBtn = By.xpath("//XCUIElementTypeStaticText[@value='Expiry date']/../../following-sibling::*/XCUIElementTypeOther");
		billingAddressNextBtn=By.xpath("//XCUIElementTypeStaticText[@value='Country']/../../following-sibling::*/XCUIElementTypeOther");
		cardHolderName=By.xpath("//XCUIElementTypeStaticText[@value='Card number']/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField");
		expiryDate=By.xpath("//XCUIElementTypeTextField[@value='Expiry date']");
		expiryDateVal = By.xpath("//XCUIElementTypeTextField[@value='MM/YY']");
		addressLine1Label=By.xpath("//XCUIElementTypeStaticText[@value='Address line 1']/following-sibling::XCUIElementTypeTextField");
		addressLine1=By.xpath("//XCUIElementTypeStaticText[@value='Address line 1']/following-sibling::XCUIElementTypeTextField");
		addressLine2Label=By.xpath("//XCUIElementTypeStaticText[@value='Address line 2']/following-sibling::XCUIElementTypeTextField");
		addressLine2=By.xpath("//XCUIElementTypeStaticText[@value='Address line 2']/following-sibling::XCUIElementTypeTextField");
		townLabel=By.xpath("//XCUIElementTypeStaticText[@value='Town/City']/following-sibling::XCUIElementTypeTextField");
		countryLabel=By.xpath("//XCUIElementTypeStaticText[@value='County']/following-sibling::XCUIElementTypeTextField");
		postCodeLabel=By.xpath("//XCUIElementTypeStaticText[@value='Postcode']/following-sibling::XCUIElementTypeTextField");
		town=By.xpath("//XCUIElementTypeStaticText[@value='Town/City']/following-sibling::XCUIElementTypeTextField");
		country=By.xpath("//XCUIElementTypeStaticText[@value='County']/following-sibling::XCUIElementTypeTextField");
		postCode=By.xpath("//XCUIElementTypeStaticText[@value='Postcode']/following-sibling::XCUIElementTypeTextField");
		addCard=By.xpath("//XCUIElementTypeStaticText[@value='Save my payment card']/../../following-sibling::*/*[contains(@label,'Add card')]");
		endingDigits=By.xpath("//android.widget.TextView[@text='Ending 5100']");
		backArrow=By.xpath("android.widget.TextView[@text='a']");
		cancelBtn=By.xpath("android.widget.TextView[@text='Cancel']");
		topupCard=By.xpath("//XCUIElementTypeStaticText[@value='Top up Card']");
		
		
	}
	
	public static By verifyLast4Digits(String data){
		return last4Digits= By.xpath("//XCUIElementTypeStaticText[@value='Ending "+data+"']");
		
	}


}