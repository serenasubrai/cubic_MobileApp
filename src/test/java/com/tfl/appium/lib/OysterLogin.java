package com.tfl.appium.lib;

import java.util.Hashtable;

import org.testng.ITestContext;

import com.cubic.appiumaction.AppiumActions;
import com.tfl.appium.page.HomePage;
import com.tfl.appium.page.LoginPage;
import com.tfl.appium.page.topupCardPage;

import io.appium.java_client.AppiumDriver;

public class OysterLogin{

	/**
	 * This method is used to login to the app and also verify whether user logged in successfully.
	 * @param data
	 * @throws Throwable
	 */
	public void verifyAppLogin(ITestContext context,Hashtable<String, String> data,AppiumActions action) throws Throwable{
		HomePage.homePageLocators(context);
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")) {
			/*if (action.isVisibleWithOutReport(HomePage.myCards, "myCards",true)) {
				action.click(HomePage.hamBurger, "hamBurger");
				action.click(HomePage.SignOut, "SignOut");	
			}*/
		}
		LoginPage.loginPageLocators(context);
		/*if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")) {
			if (action.isVisibleWithOutReport(LoginPage.logOutAndClearData, "logOutAndClearData")) {
				action.click(LoginPage.logOutAndClearData, "logOutAndClearData");	
			}
		}*/
		//action.click(LoginPage.iKnowItsOKForMe, "iKnowItsOKForMe");	
		//action.click(LoginPage.signInBtn, "signInBtn");
		action.click(LoginPage.emailLabel, "emailLabel");
		//action.sendKeys(LoginPage.userName,"ravi.mutya@gmail.com", "UserName");
		action.sendKeys(LoginPage.userName,data.get("AppUserName"), "UserName");
		//AppiumDriver driver = action.getAppiumDriver();
		//driver.hideKeyboard();
		action.click(LoginPage.passwordLabel, "passwordLabel");
		//action.sendKeys(LoginPage.password,"Qazwsx@1234", "Password");
		action.sendKeys(LoginPage.password,data.get("AppPassword"), "Password");
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")) {
			AppiumDriver driver = action.getAppiumDriver();
			driver.hideKeyboard();
		}
		action.click(LoginPage.loginBtn, "loginBtn");
		int counter = 0;
		
		while (!(action.isVisibleWithOutReport(HomePage.myCards, "myCards", true))){
			counter = counter+1;
			if (counter==10) {
				action.failureReport("Unable to signin to the TFL Mobile app", "Unable to signin to the TFL Mobile app");
				throw new RuntimeException("Unable to signin to the TFL Mobile app");
				//break;
			}
			if (action.isVisibleWithOutReport(LoginPage.errorOKBtn, "errorOKBtn", true)) {
				action.click(LoginPage.errorOKBtn, "errorOKBtn");
				action.click(LoginPage.loginBtn, "loginBtn");
			}else if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS") && (action.isVisibleWithOutReport(LoginPage.allowBtn, "allowBtn", true))){
				AppiumDriver appiumDriver = action.getAppiumDriver();
				appiumDriver.switchTo().alert().accept();
			}
		}
		
	}

	/**
	 * This method is used to swipe the screen from Right to Left to reach the desired page.
	 * @param action
	 */
	public void swipes(AppiumActions action) {
		try {
			action.swipeRightToLeft();
			action.swipeRightToLeft();
			action.swipeRightToLeft();
			action.swipeRightToLeft();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	/**
	 * This method is used to switch to the desired network.
	 * @param context
	 * @param data
	 * @param action
	 * @throws Throwable
	 */
	public void switchNetwork(ITestContext context,Hashtable<String, String> data,AppiumActions action) throws Throwable{
		HomePage.homePageLocators(context);
		//System.out.println(context.getCurrentXmlTest().getParameter("platformName"));
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")) {
			if (action.isVisibleWithOutReport(HomePage.myCards, "myCards",true)) {
				action.click(HomePage.hamBurger, "hamBurger");
				action.click(HomePage.SignOut, "SignOut");	
			}
		}
		LoginPage.loginPageLocators(context);
		/*if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")) {
			if (action.isVisibleWithOutReport(LoginPage.logOutAndClearData, "logOutAndClearData")) {
				action.click(LoginPage.logOutAndClearData, "logOutAndClearData");	
			}
		}*/
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")) {
			if(action.isVisibleWithOutReport(LoginPage.switchNetworkBtn, "switchNetworkBtn",true)) {
				swipes(action);
			}
		}
		else {
			if(action.isVisibleWithOutReport(LoginPage.switchNetworkBtn, "switchNetworkBtn",true)) {
				swipes(action);
			}
		}
		action.click(LoginPage.signInBtn, "signInBtn");

		if (action.isVisibleWithOutReport(LoginPage.acceptBtn, "acceptBtn", true)) {
			action.click(LoginPage.acceptBtn, "acceptBtn");
		}
		

		action.click(LoginPage.emailLabel, "emailLabel"); 


		action.sendKeys(LoginPage.userName,data.get("switchUserName"), "UserName");
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")) {
			AppiumDriver driver = action.getAppiumDriver();
			driver.hideKeyboard();
		}

		action.click(LoginPage.passwordLabel, "passwordLabel");
		//action.sendKeys(LoginPage.password,"Qazwsx@1234", "Password");
		action.sendKeys(LoginPage.password,data.get("switchPassword"), "Password");
		if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")) {
			AppiumDriver driver = action.getAppiumDriver();
			driver.hideKeyboard();
		}
		//driver.hideKeyboard();
		action.click(LoginPage.loginBtn, "loginBtn");
		action.click(LoginPage.testModeOKBtn, "testModeOKBtn");
		action.click(LoginPage.switchNetworkBtn2, "switchNetworkBtn");
		action.click(LoginPage.cubicTest002, "cubicTest002");

		//action.waitForInVisibilityOfElement(LoginPage.loginBtn, "signInBtn");


	}
	
	/**
	 * This method is used to logout of the App.
	 * @param action
	 * @throws Throwable
	 */
	public void logout(AppiumActions action) throws Throwable{
		action.click(HomePage.hamBurger, "hamBurger");
		action.click(HomePage.SignOut, "SignOut");
	}

	/**
	 * This method is used to navigate to home page.
	 * @param action
	 * @throws Throwable
	 */
	public void navigateToHomePage(AppiumActions action) throws Throwable{
		action.click(topupCardPage.cancelBtn, "cancelBtn");
		action.click(HomePage.closeBtn, "closeBtn");
	}

}
