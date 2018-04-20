package com.tfl.appium.page;

import org.openqa.selenium.By;

public class topupCardPageAndroid {

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
	
	
	/* Page Objects Of Home Page */
	static {
		plusBtn=By.xpath("//android.widget.TextView[@text='+']");
		newBalance=By.xpath("//android.widget.TextView[@text='New balance']/following-sibling::android.widget.TextView");
		topupCardNextBtn=By.xpath("//android.widget.TextView[@text='Next']");
		paymentCardNextBtn=By.xpath("//android.widget.TextView[@text='Next']");
		addPaymentCardNextBtn=By.xpath("//android.widget.TextView[@text='Next']");
		billingAddressNextBtn=By.xpath("//android.widget.TextView[@text='Next']");
		changePaymentCard=By.xpath("//android.widget.TextView[@text='Change/add payment card']");
		addPaymentCardPlusBtn=By.xpath("//android.widget.TextView[@text='i']");
		cardNumberLabel=By.xpath("//android.widget.EditText[@text='Card number']");
		cardNumber=By.xpath("//android.widget.TextView[@text='Card number']/following-sibling::android.widget.EditText[@NAF='true']");
		cardHolderName=By.xpath("//android.widget.TextView[@text='Card number']/parent::android.view.ViewGroup/parent::android.view.ViewGroup/following-sibling::android.view.ViewGroup/android.view.ViewGroup/android.widget.EditText");
		expiryDate=By.xpath("//android.widget.EditText[@text='Expiry date']");
		expiryDateVal = By.xpath("//android.widget.TextView[@text='Expiry date']/following-sibling::android.widget.EditText");
		addressLine1Label=By.xpath("//android.widget.EditText[@text='Address line 1']");
		addressLine1=By.xpath("//android.widget.TextView[@text='Address line 1']/following-sibling::android.widget.EditText");
		addressLine2Label=By.xpath("//android.widget.EditText[@text='Address line 2']");
		addressLine2=By.xpath("//android.widget.TextView[@text='Address line 2']/following-sibling::android.widget.EditText");
		townLabel=By.xpath("//android.widget.EditText[@text='Town/City']");
		countryLabel=By.xpath("//android.widget.EditText[@text='County']");
		postCodeLabel=By.xpath("//android.widget.EditText[@text='Postcode']");
		town=By.xpath("//android.widget.TextView[@text='Town/City']/following-sibling::android.widget.EditText");
		country=By.xpath("//android.widget.TextView[@text='County']/following-sibling::android.widget.EditText");
		postCode=By.xpath("//android.widget.TextView[@text='Postcode']/following-sibling::android.widget.EditText");
		addCard=By.xpath("//android.view.ViewGroup/android.widget.TextView[@text='Add card']");
		endingDigits=By.xpath("//android.widget.TextView[@text='Ending 5100']");
		backArrow=By.xpath("android.widget.TextView[@text='a']");
		cancelBtn=By.xpath("android.widget.TextView[@text='Cancel']");
		topupCard=By.xpath("android.widget.TextView[@text='Top up Card']");
		
		
		
		
		
		
		
	}


}