package com.tfl.appium.scripts.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class appiumSampleScript {
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", "091609f2b5933503");
		capabilities.setCapability("platformVersion", "6.0.1");
		capabilities.setCapability("app", "");
		capabilities.setCapability("browserName", "");
		capabilities.setCapability("deviceOrientation", "portrait");
		capabilities.setCapability("appiumVersion", "1.6.4");
		capabilities.setCapability("appPackage", "com.oyster");
		capabilities.setCapability("appActivity", "com.oyster.MainActivity");
		
		AppiumDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		System.out.println("**** Execution Started ****");
		//signin link
		driver.findElement(By.xpath("//android.widget.TextView[@text='Sign in']")).click();
		//Email label
		driver.findElement(By.xpath("//android.widget.EditText[@text='Email']")).click();
		//UserName text box
		driver.findElement(By.xpath("//android.widget.TextView[@text='Email']/preceding-sibling::android.widget.EditText")).sendKeys("switch");
		//password label
		driver.findElement(By.xpath("//android.widget.EditText[@NAF='true']")).click();
		//password text box
		driver.findElement(By.xpath("//android.widget.TextView[@text='Password']/preceding-sibling::android.widget.EditText")).sendKeys("Hiaant!");
		//sign in button
		driver.findElement(By.id("signin")).click();
		//switch ok button
		driver.findElement(By.xpath("//android.widget.TextView[@text='OK']")).click();
		//switch network 
		driver.findElement(By.className("android.widget.ImageView")).click();
		// test network 
		driver.findElement(By.xpath("//android.widget.TextView[@text='Cubic UK 002']")).click();
		// Email label
		driver.findElement(By.xpath("//android.widget.EditText[@text='Email']")).click();
		//UserName text box
		driver.findElement(By.xpath("//android.widget.TextView[@text='Email']/preceding-sibling::android.widget.EditText")).sendKeys("cubicuser32@saf.com");
		//password label
		driver.findElement(By.xpath("//android.widget.EditText[@NAF='true']")).click();
		//password text box
		driver.findElement(By.xpath("//android.widget.TextView[@text='Password']/preceding-sibling::android.widget.EditText")).sendKeys("Cub1cuser");
		//login button
		driver.findElement(By.id("signin")).click();
		Thread.sleep(4000);
		
		System.out.println("**** Execution Ended ****");

		
		/**
		 * Test Actions here...
		 */

		driver.quit();
	}
}
