package com.tfl.appium.page;

import org.openqa.selenium.By;

public class HomePageAndroid {

	public static By hamBurger;
	public static By SignOut;
	public static By topUp;
	public static By closeBtn;
	public static By imageView;
	public static By myCards;

	/* Page Objects Of Home Page */
	static {
		hamBurger=By.xpath("//android.widget.TextView[@text='h']");
		myCards=By.xpath("//android.widget.TextView[@text='My Cards']");
		SignOut=By.xpath("//android.widget.TextView[@text='Sign out']");
		topUp=By.xpath("//android.widget.TextView[@text='Top up']");
		closeBtn=By.xpath("//android.widget.TextView[@text='Close']");
		imageView=By.xpath("//android.support.v4.view.ViewPager/android.view.ViewGroup/android.widget.ImageView");
	}

	
}