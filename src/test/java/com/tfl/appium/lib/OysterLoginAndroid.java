package com.tfl.appium.lib;

import java.util.Hashtable;

import com.cubic.appiumaction.AppiumActions;
import com.tfl.appium.page.HomePageAndroid;
import com.tfl.appium.page.LoginPageAndroid;
import com.tfl.appium.page.topupCardPageAndroid;

public class OysterLoginAndroid{

	/**
	 * This method is used to login to the app in Android device.
	 * @param data
	 * @throws Throwable
	 */
	public void verifyAppLogin(Hashtable<String, String> data,AppiumActions action) throws Throwable{
		//action.isElementPresent(LoginPageAndroid.logOutAndClearData, "logOutAndClearData", true);
		//action.waitForElementPresent(LoginPageAndroid.iKnowItsOKForMe, "iKnowItsOKForMe", 60);
		//action.isVisibleWithOutReport(LoginPageAndroid.logOutAndClearData, "logOutAndClearData");
		//action.click(LoginPageAndroid.logOutAndClearData, "logOutAndClearData");
		action.click(LoginPageAndroid.iKnowItsOKForMe, "iKnowItsOKForMe");
		action.click(LoginPageAndroid.signInBtn, "signInBtn");
		action.click(LoginPageAndroid.emailLabel, "emailLabel");
		action.sendKeys(LoginPageAndroid.userName,"ravi.mutya@gmail.com", "UserName");
		action.click(LoginPageAndroid.passwordLabel, "passwordLabel");
		action.sendKeys(LoginPageAndroid.password,"Qazwsx@1234", "Password");
		action.click(LoginPageAndroid.loginBtn, "signInBtn");
		//action.waitForInVisibilityOfElement(LoginPageAndroid.loginBtn, "signInBtn");



	}
	
	/**
	 * This method is used to logout to the app in Android device.
	 * @param action
	 * @throws Throwable
	 */
	public void logout(AppiumActions action) throws Throwable{
		action.click(HomePageAndroid.hamBurger, "hamBurger");
		action.click(HomePageAndroid.SignOut, "SignOut");
	}

	/**
	 * This method is used to navigate to home page in Android device.
	 * @param action
	 * @throws Throwable
	 */
	public void navigateToHomePage(AppiumActions action) throws Throwable{
		action.click(topupCardPageAndroid.cancelBtn, "cancelBtn");
		action.click(HomePageAndroid.closeBtn, "closeBtn");
	}

}
