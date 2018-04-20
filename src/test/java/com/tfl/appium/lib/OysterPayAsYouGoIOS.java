package com.tfl.appium.lib;

import com.cubic.appiumaction.AppiumActions;
import com.tfl.appium.page.HomePageIOS;
import com.tfl.appium.page.topupCardPageIOS;

public class OysterPayAsYouGoIOS{

	/**
	 *  This method is used to perform TopUp on the iOS device. 
	 * @param data
	 * @throws Throwable
	 */

	public void topup(AppiumActions action) throws Throwable{
		
		action.WaitforTextPresent(HomePageIOS.myCards, "myCards", "My Cards");
		String expText= action.getText(HomePageIOS.myCards, "myCards");
		action.assertTextStringMatching(expText, "My Cards");
		action.click(HomePageIOS.imageView, "imageView");
		action.click(HomePageIOS.topUp, "topUp");
		action.click(topupCardPageIOS.plusBtn, "plusBtn");
		String expectedNewBalance = action.getText(topupCardPageIOS.newBalance, "ExpectedNewBalance");
		action.click(topupCardPageIOS.nextBtn, "nextBtn");
		String ActualNewBalance = action.getText(topupCardPageIOS.newBalance, "ActualNewBalance");
		action.assertTrue(expectedNewBalance.equalsIgnoreCase(ActualNewBalance), "Both are Verified Same");
	}
	

}
