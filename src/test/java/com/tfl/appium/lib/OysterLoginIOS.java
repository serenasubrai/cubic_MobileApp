package com.tfl.appium.lib;

import java.util.Hashtable;

import com.cubic.appiumaction.AppiumActions;
import com.tfl.appium.page.HomePageIOS;
import com.tfl.appium.page.LoginPageIOS;
import com.tfl.appium.page.topupCardPageIOS;

public class OysterLoginIOS{

	/**
	 * This method is used to login to the app in iOS device.
	 * @param data
	 * @throws Throwable
	 */
	public void verifyAppLogin(Hashtable<String, String> data,AppiumActions action) throws Throwable{
		//action.isElementPresent(LoginPageIOS.logOutAndClearData, "logOutAndClearData", true);
		action.click(LoginPageIOS.signInBtn, "signInBtn");
		action.click(LoginPageIOS.emailLabel, "emailLabel");
		action.sendKeys(LoginPageIOS.userName,"ravi.mutya@gmail.com", "UserName");
		action.click(LoginPageIOS.passwordLabel, "passwordLabel");
		action.sendKeys(LoginPageIOS.password,"Qazwsx@1234", "Password");
		action.click(LoginPageIOS.loginBtn, "signInBtn");
		action.waitForInVisibilityOfElement(LoginPageIOS.loginBtn, "signInBtn");



	}
	
	/**
	 * This method is used to logout to the app in iOS device.
	 * @param action
	 * @throws Throwable
	 */
	public void logout(AppiumActions action) throws Throwable{
		action.click(HomePageIOS.hamBurger, "hamBurger");
		action.click(HomePageIOS.SignOut, "SignOut");
	}

	/**
	 * This method is used to navigate to home page in iOS device.
	 * @param action
	 * @throws Throwable
	 */
	public void navigateToHomePage(AppiumActions action) throws Throwable{
		action.click(topupCardPageIOS.cancelBtn, "cancelBtn");
		action.click(HomePageIOS.closeBtn, "closeBtn");
	}

}
