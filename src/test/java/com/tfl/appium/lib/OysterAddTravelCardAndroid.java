package com.tfl.appium.lib;

import com.cubic.appiumaction.AppiumActions;
import com.tfl.appium.page.HomePageAndroid;
import com.tfl.appium.page.addTravelCardPageAndroid;

public class OysterAddTravelCardAndroid{

/**
 * This method is to Add a Annual travel card when supplied with desired parameters for Android device.	
 * @param action
 * @throws Throwable
 */
	 
	public void addAnualTravelCard(AppiumActions action) throws Throwable{
		action.click(HomePageAndroid.imageView, "imageView");
		action.click(addTravelCardPageAndroid.buyBtn, "buyBtn");
		action.click(addTravelCardPageAndroid.travelcardType, "travelcardType");
		action.click(addTravelCardPageAndroid.annualLabel, "annualLabel");
		action.click(addTravelCardPageAndroid.fromZone, "fromZone");
		action.click(addTravelCardPageAndroid.fromZoneValue, "fromZoneValue");
		action.click(addTravelCardPageAndroid.toZone, "toZone");
		action.click(addTravelCardPageAndroid.toZoneValue, "toZoneValue");
		action.click(addTravelCardPageAndroid.startDate, "startDate");
		action.click(addTravelCardPageAndroid.dateValue, "dateValue");
		action.click(addTravelCardPageAndroid.confirm, "confirm");
		action.click(addTravelCardPageAndroid.next, "next");
	}
	
/**
 * This method is to Add a Monthly travel card when supplied with desired parameters for Android device.	
 * @param action
 * @throws Throwable
 */
	public void addMonthlyTravelCard(AppiumActions action) throws Throwable{
		action.click(HomePageAndroid.imageView, "imageView");
		action.click(addTravelCardPageAndroid.buyBtn, "buyBtn");
		action.click(addTravelCardPageAndroid.travelcardType, "travelcardType");
		action.click(addTravelCardPageAndroid.monthLabel, "monthLabel");
		action.click(addTravelCardPageAndroid.fromZone, "fromZone");
		action.click(addTravelCardPageAndroid.fromZoneValue, "fromZoneValue");
		action.click(addTravelCardPageAndroid.toZone, "toZone");
		action.click(addTravelCardPageAndroid.toZoneValue, "toZoneValue");
		action.click(addTravelCardPageAndroid.startDate, "startDate");
		action.click(addTravelCardPageAndroid.dateValue, "dateValue");
		action.click(addTravelCardPageAndroid.confirm, "confirm");
		action.click(addTravelCardPageAndroid.next, "next");
	}
	
/**
 * This method is to Add a Weekly travel card when supplied with desired parameters for Android device.	
 * @param action
 * @throws Throwable
 */
	public void addWeeklyTravelCard(AppiumActions action) throws Throwable{
		action.click(HomePageAndroid.imageView, "imageView");
		action.click(addTravelCardPageAndroid.buyBtn, "buyBtn");
		action.click(addTravelCardPageAndroid.travelcardType, "travelcardType");
		action.click(addTravelCardPageAndroid.weekLabel, "weekLabel");
		action.click(addTravelCardPageAndroid.fromZone, "fromZone");
		action.click(addTravelCardPageAndroid.fromZoneValue, "fromZoneValue");
		action.click(addTravelCardPageAndroid.toZone, "toZone");
		action.click(addTravelCardPageAndroid.toZoneValue, "toZoneValue");
		action.click(addTravelCardPageAndroid.startDate, "startDate");
		action.click(addTravelCardPageAndroid.dateValue, "dateValue");
		action.click(addTravelCardPageAndroid.confirm, "confirm");
		action.click(addTravelCardPageAndroid.next, "next");
	}


}
