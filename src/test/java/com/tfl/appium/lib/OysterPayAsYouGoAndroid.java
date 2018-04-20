package com.tfl.appium.lib;

import com.cubic.appiumaction.AppiumActions;
import com.tfl.appium.page.HomePageAndroid;
import com.tfl.appium.page.topupCardPageAndroid;

public class OysterPayAsYouGoAndroid{

	/**
	 * This method is used to perform TopUp on the Android device. 
	 * @param data
	 * @throws Throwable
	 */

	public void topup(AppiumActions action) throws Throwable{
		
		action.WaitforTextPresent(HomePageAndroid.myCards, "myCards", "My Cards");
		String expText= action.getText(HomePageAndroid.myCards, "myCards");
		action.assertTextStringMatching(expText, "My Cards");
		action.click(HomePageAndroid.imageView, "imageView");
		action.click(HomePageAndroid.topUp, "topUp");
		action.click(topupCardPageAndroid.plusBtn, "plusBtn");
		String expectedNewBalance = action.getText(topupCardPageAndroid.newBalance, "ExpectedNewBalance");
		action.click(topupCardPageAndroid.topupCardNextBtn, "topupCardNextBtn");
		String ActualNewBalance = action.getText(topupCardPageAndroid.newBalance, "ActualNewBalance");
		action.assertTrue(expectedNewBalance.equalsIgnoreCase(ActualNewBalance), "Both are Verified Same");
	}
	

}
