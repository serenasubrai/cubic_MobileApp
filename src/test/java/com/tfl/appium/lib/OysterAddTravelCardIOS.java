package com.tfl.appium.lib;

import javax.swing.Action;

import org.openqa.selenium.By;

import com.cubic.appiumaction.AppiumActions;
import com.cubic.genericutils.FileUtil;
import com.tfl.appium.page.HomePageIOS;
import com.tfl.appium.page.addTravelCardPageIOS;

import io.appium.java_client.AppiumDriver;

public class OysterAddTravelCardIOS{

	/**
	 * 
	 * @param data
	 * @throws Throwable
	 */

	/**
	 * This method is to Add a annual travel card when supplied with desired parameters on iOS device.
	 * @param action
	 * @throws Throwable
	 */
	public void addAnualTravelCard(AppiumActions action) throws Throwable{
		action.click(HomePageIOS.imageView, "imageView");
		action.click(addTravelCardPageIOS.buyBtn, "buyBtn");
		action.click(addTravelCardPageIOS.travelcardType, "travelcardType");
		action.click(addTravelCardPageIOS.annualLabel, "annualLabel");
//		action.click(addTravelCardPageIOS.fromZone, "fromZone");
//		action.click(addTravelCardPageIOS.fromZoneValue, "fromZoneValue");
//		action.click(addTravelCardPageIOS.toZone, "toZone");
//		action.click(addTravelCardPageIOS.toZoneValue, "toZoneValue");
//		action.click(addTravelCardPageIOS.startDate, "startDate");
//		action.click(addTravelCardPageIOS.dateValue, "dateValue");
//		action.click(addTravelCardPageIOS.confirm, "confirm");
//		action.click(addTravelCardPageIOS.next, "next");
	}
	
	/**
	 * This method is to select zones to add Annual,Weekly or Monthly travel cards when supplied with desired parameters on iOS device.
	 * @param action
	 * @throws Throwable
	 */
	public void selectZone(AppiumActions action) throws Throwable{
		for(int i=1; i<=10; i++){
			action.click(addTravelCardPageIOS.fromZone, "fromZone");
			action.click(addTravelCardPageIOS.chooseZoneValue(FileUtil.generateRandomNumber(11, 1)), "fromZoneValue");
			action.click(addTravelCardPageIOS.toZone, "toZone");
			action.click(addTravelCardPageIOS.chooseZoneValue(FileUtil.generateRandomNumber(11, 1)), "toZoneValue");
			action.click(addTravelCardPageIOS.startDate, "startDate");
			action.click(addTravelCardPageIOS.confirm, "confirm");
			action.click(addTravelCardPageIOS.next, "next");
			Thread.sleep(2000);
			if (!(action.isVisibleWithOutReport(addTravelCardPageIOS.noProductMsg, "No Product Found",true))) {
				break;
			}
			else{
				action.click(addTravelCardPageIOS.OKBtn, "OKBtn");
			}
		}
			
	}
	
	/**
	 * This method is to Add a monthly travel card when supplied with desired parameters on iOS device.
	 * @param action
	 * @throws Throwable
	 */
	public void addMonthlyTravelCard(AppiumActions action) throws Throwable{
		action.click(HomePageIOS.imageView, "imageView");
		action.click(addTravelCardPageIOS.buyBtn, "buyBtn");
		action.click(addTravelCardPageIOS.travelcardType, "travelcardType");
		action.click(addTravelCardPageIOS.monthLabel, "monthLabel");
		action.click(addTravelCardPageIOS.fromZone, "fromZone");
		action.click(addTravelCardPageIOS.fromZoneValue, "fromZoneValue");
		action.click(addTravelCardPageIOS.toZone, "toZone");
		action.click(addTravelCardPageIOS.toZoneValue, "toZoneValue");
		action.click(addTravelCardPageIOS.startDate, "startDate");
		action.click(addTravelCardPageIOS.dateValue, "dateValue");
		action.click(addTravelCardPageIOS.confirm, "confirm");
		action.click(addTravelCardPageIOS.next, "next");
	}
	
	/**
	 * This method is to Add a weekly travel card when supplied with desired parameters on iOS device.
	 * @param action
	 * @throws Throwable
	 */
	public void addWeeklyTravelCard(AppiumActions action) throws Throwable{
		action.click(HomePageIOS.imageView, "imageView");
		action.click(addTravelCardPageIOS.buyBtn, "buyBtn");
		action.click(addTravelCardPageIOS.travelcardType, "travelcardType");
		action.click(addTravelCardPageIOS.weekLabel, "weekLabel");
		action.click(addTravelCardPageIOS.fromZone, "fromZone");
		action.click(addTravelCardPageIOS.fromZoneValue, "fromZoneValue");
		action.click(addTravelCardPageIOS.toZone, "toZone");
		action.click(addTravelCardPageIOS.toZoneValue, "toZoneValue");
		action.click(addTravelCardPageIOS.startDate, "startDate");
		action.click(addTravelCardPageIOS.dateValue, "dateValue");
		action.click(addTravelCardPageIOS.confirm, "confirm");
		action.click(addTravelCardPageIOS.next, "next");
	}


}
