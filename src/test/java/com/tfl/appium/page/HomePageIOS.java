package com.tfl.appium.page;

import org.openqa.selenium.By;

import com.cubic.appiumaction.AppiumActions;
import com.cubic.appiumaction.AppiumEngine;

public class HomePageIOS {

	public static By hamBurger;
	public static By SignOut;
	public static By topUp;
	public static By closeBtn;
	public static By imageView;
	public static By myCards;

	/* Page Objects Of Home Page */
	static {
		hamBurger=By.xpath("//android.widget.TextView[@text='h']");
		myCards=By.xpath("//*[@value='My Cards']");
		SignOut=By.xpath("//android.widget.TextView[@text='Sign out']");
		topUp=By.xpath("(//*[contains(@label,'Top up')])[16]");
		closeBtn=By.xpath("//android.widget.TextView[@text='Close']");
		imageView=By.xpath("(//XCUIElementTypeOther[contains(@label,'Last touch')])[10]");
	}
	

}