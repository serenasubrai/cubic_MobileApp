package com.tfl.appium.lib;

import java.util.Hashtable;

import org.testng.ITestContext;

import com.cubic.appiumaction.AppiumActions;
import com.tfl.appium.page.HomePage;
import com.tfl.appium.page.LoginPage;
import com.tfl.appium.page.LoginPage;
import com.tfl.appium.page.topupCardPage;

import io.appium.java_client.AppiumDriver;

public class OysterLogOut{

	/**
	 * This method is used to login to the app and also verify whether user logged in successfully.
	 * @param data
	 * @throws Throwable
	 */
	public void verifyAppLogin(ITestContext context,Hashtable<String, String> data,AppiumActions action) throws Throwable{
		HomePage.homePageLocators(context);
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
		//action.click(LoginPage.iKnowItsOKForMe, "iKnowItsOKForMe");	
		//action.click(LoginPage.signInBtn, "signInBtn");
		action.click(LoginPage.emailLabel, "emailLabel");
		//action.sendKeys(LoginPage.userName,"ravi.mutya@gmail.com", "UserName");
		action.sendKeys(LoginPage.userName,data.get("AppUserName"), "UserName");
		AppiumDriver driver = action.getAppiumDriver();
		driver.hideKeyboard();
		action.click(LoginPage.passwordLabel, "passwordLabel");
		//action.sendKeys(LoginPage.password,"Qazwsx@1234", "Password");
		action.sendKeys(LoginPage.password,data.get("AppPassword"), "Password");
		driver.hideKeyboard();
		action.click(LoginPage.loginBtn, "loginBtn");
		while (!(action.isVisibleWithOutReport(HomePage.hamBurger, "hamBurger", true))) {
			if (action.isVisibleWithOutReport(LoginPage.errorMessage, "errorMessage", true)) {
				action.click(LoginPage.errorOKBtn, "errorOKBtn");
				action.click(LoginPage.loginBtn, "loginBtn");
			}
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
		//action.click(LoginPage.iKnowItsOKForMe, "iKnowItsOKForMe");	
		action.click(LoginPage.signInBtn, "signInBtn");
		action.click(LoginPage.emailLabel, "emailLabel");
		//action.sendKeys(LoginPage.userName,"ravi.mutya@gmail.com", "UserName");
		//action.sendKeys(LoginPage.userName,"switch", "UserName");
		//System.out.println("test data from excel is "+data.get("switchUserName"));
		action.sendKeys(LoginPage.userName,data.get("switchUserName"), "UserName");
		AppiumDriver driver = action.getAppiumDriver();
		driver.hideKeyboard();
		action.click(LoginPage.passwordLabel, "passwordLabel");
		//action.sendKeys(LoginPage.password,"Qazwsx@1234", "Password");
		action.sendKeys(LoginPage.password,data.get("switchPassword"), "Password");
		driver.hideKeyboard();
		action.click(LoginPage.loginBtn, "loginBtn");
		action.click(LoginPage.testModeOKBtn, "testModeOKBtn");
		action.click(LoginPage.switchNetworkBtn, "switchNetworkBtn");
		action.click(LoginPage.cubicTest002, "cubicTest002");
		
		//action.waitForInVisibilityOfElement(LoginPage.loginBtn, "signInBtn");


	}
	
	/**
	 * This method is used to logout of the App.
	 * @param context
	 * @param action
	 * @throws Throwable
	 */
	public void appLogOut(ITestContext context,AppiumActions action) throws Throwable{
		HomePage.homePageLocators(context);
		if (action.waitForElementPresent(HomePage.hamBurger, "hamBurger")) {
			action.click(HomePage.hamBurger, "hamBurger");
			action.click(HomePage.SignOut, "SignOut");
			LoginPage.loginPageLocators(context);
			if (action.waitForElementPresent(LoginPage.signInBtn, "signInBtn")) {
			action.successReportAppium("Verify User Logged out from the Mobile App", "User successfully logged out from the mobile App");	
			}
			else{
				action.successReportAppium("Verify User Logged out from the Mobile App", "Logout Failed");	
			}
			
		}
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
