package com.tfl.appium.page;

import org.openqa.selenium.By;
import org.testng.ITestContext;

public class LoginPageIOS {

	public static By logOutAndClearData;
	public static By signInBtn;
	public static By userName;
	public static By password;
	public static By passwordLabel;
	public static By loginBtn;
	public static By emailLabel;
	public static By iKnowItsOKForMe;

	/* Page Objects Of Home Page */
	static {
		logOutAndClearData=By.xpath("//android.widget.TextView[@text='Log out and clear data']");
		iKnowItsOKForMe=By.xpath("//android.widget.TextView[@text='I know and it is ok for me']");
		signInBtn=By.xpath("//XCUIElementTypeImage/parent::XCUIElementTypeOther/following-sibling::XCUIElementTypeOther/XCUIElementTypeOther");
		emailLabel = By.xpath("//XCUIElementTypeTextField[@value='Email']");
		userName=By.xpath("//XCUIElementTypeStaticText[@value='Email']/following-sibling::*/XCUIElementTypeTextField");
		passwordLabel=By.xpath("//XCUIElementTypeSecureTextField[@value='Password']");
		loginBtn = By.id("signin");
		password=By.xpath("//XCUIElementTypeStaticText[@value='Password']/following-sibling::*/XCUIElementTypeSecureTextField");

	}


}