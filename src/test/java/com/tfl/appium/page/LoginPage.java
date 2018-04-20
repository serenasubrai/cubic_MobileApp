package com.tfl.appium.page;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

import com.cubic.appiumaction.AppiumActions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage {

	public static By logOutAndClearData;
	public static By signInBtn;
	public static By userName;
	public static By password;
	public static By passwordLabel;
	public static By loginBtn;
	public static By emailLabel;
	public static By iKnowItsOKForMe;
	public static By testModeOKBtn;
	public static By switchNetworkBtn;
	public static By switchNetworkBtn2;
	public static By cubicTest002;
	public static By errorMessage;
	public static By errorOKBtn;
	public static By acceptBtn;
	public static By termsAndConditions;
	public static String switchNetworkBtniOS;
	public static By allowBtn;
	

	/* Page Objects Of Home Page */

	public static void loginPageLocators(ITestContext context){
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")) {
			logOutAndClearData=By.xpath("//android.widget.TextView[@text='Log out and clear data']");
			iKnowItsOKForMe=By.xpath("//android.widget.TextView[@text='I know and it is ok for me']");
			signInBtn=By.xpath("//android.widget.TextView[@text='Sign in']");
			//acceptBtn=By.xpath("//android.widget.TextView[contains(@text,'Accept')]");
			acceptBtn=By.xpath("//android.widget.TextView[@text='Accept']");
			
			//acceptBtn= @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\" your_text \")");	
			termsAndConditions = By.xpath("//android.widget.TextView[contains(@text,'By clicking')]");
			emailLabel = By.xpath("//android.widget.TextView[@text='Email']");
			userName=By.xpath("//android.widget.TextView[@text='Email']/preceding-sibling::android.widget.EditText");
			passwordLabel=By.xpath("//android.widget.TextView[@text='Password']");
			//loginBtn = By.xpath("//android.widget.TextView[@text='Sign in']");
			//loginBtn = By.id("signin");
			loginBtn = By.id("bnt_signIn");
			//loginBtn = By.xpath("//android.view.ViewGroup[@contect-desc='bnt_signIn']/android.widget.TextView[@text='Sign in']");
			password=By.xpath("//android.widget.TextView[@text='Password']/preceding-sibling::android.widget.EditText");
			//password=By.xpath("//android.widget.EditText[@NAF='true']");
			testModeOKBtn=By.xpath("//android.widget.TextView[@text='OK']");
			switchNetworkBtn=By.className("android.widget.ImageView");
			switchNetworkBtn2=By.className("android.widget.ImageView");
			cubicTest002=By.xpath("//android.widget.TextView[@text='Cubic UK 002']");
			errorMessage = By.xpath("//android.widget.TextView[contains(@text,'Please')]/following-sibling::android.widget.TextView");
			errorOKBtn= By.xpath("//android.widget.TextView[@text='OK']");
			
			
		}
		else if(context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")){
			signInBtn=By.xpath("(//XCUIElementTypeOther[contains(@label,'Sign in')])[last()]");
			//emailLabel = By.xpath("//XCUIElementTypeTextField[@value='Email']");
			emailLabel = By.xpath("//XCUIElementTypeTextField[@name=\"txt_username\"]");
			acceptBtn=By.xpath("(//XCUIElementTypeOther[contains(@label,'Accept')])[last()]");
			//acceptBtn=By.xpath("//XCUIElementTypeOther[@name=\"bnt_signIn\"]");
			userName=By.xpath("//XCUIElementTypeTextField[@name=\"txt_username\"]");
			//userName=By.xpath("//XCUIElementTypeStaticText[@value='Email']/following-sibling::*/XCUIElementTypeTextField");
			passwordLabel=By.xpath("//XCUIElementTypeSecureTextField[@value='Password']");
			loginBtn = By.xpath("//XCUIElementTypeOther[@name=\"bnt_signIn\"]");
			password=By.xpath("//XCUIElementTypeSecureTextField[@name=\"txt_password\"]");
			//password=By.xpath("//XCUIElementTypeStaticText[@value='Password']/following-sibling::*/XCUIElementTypeSecureTextField");
			switchNetworkBtn=By.xpath("//XCUIElementTypeImage[@name='assets/App/Images/tflRoundelSplashscreen@2x.png']");
			testModeOKBtn=By.xpath("//XCUIElementTypeOther[@name=\"btn_dialog0\"]");
			//switchNetworkBtn=By.xpath("//XCUIElementTypeOther[@name=\"btn_serverSelect\"]");
			cubicTest002=By.xpath("(//XCUIElementTypeOther[contains(@label,'Cubic UK 002')])[last()]");
			switchNetworkBtn2=By.xpath("//XCUIElementTypeOther[@name=\"btn_serverSelect\"]");
			errorOKBtn= By.xpath("//XCUIElementTypeOther[@name='OK']");
			allowBtn= By.id("Allow");
			
		}
	}


}