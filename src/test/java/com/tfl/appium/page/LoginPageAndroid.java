package com.tfl.appium.page;

import org.openqa.selenium.By;
import org.testng.ITestContext;

public class LoginPageAndroid {

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
		signInBtn=By.xpath("//android.widget.TextView[@text='Sign in']");
		emailLabel = By.xpath("//android.widget.EditText[@text='Email']");
		userName=By.xpath("//android.widget.TextView[@text='Email']/preceding-sibling::android.widget.EditText");
		passwordLabel=By.xpath("//android.widget.EditText[@text='Password']");
		loginBtn = By.id("signin");
		password=By.xpath("//android.widget.TextView[@text='Password']/preceding-sibling::android.widget.EditText");
	}

}