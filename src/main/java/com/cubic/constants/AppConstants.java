package com.cubic.constants;

import java.util.Hashtable;
import com.cubic.genericutils.PropertiesUtil;

public interface AppConstants {
	
	Hashtable<String, String> CONFIG_PROPERTIES = PropertiesUtil.getPropertysAsHashtable(System.getProperty("user.dir")+"/resources/Config.properties");

	String APP_BASEURL = CONFIG_PROPERTIES.get("baseUrl");
	String TESTDATA_FOLDER_PATH = CONFIG_PROPERTIES.get("testdata_folder_path");
	String DATA_PROVIDER = "dataProvider";
	String DGC_PATH = CONFIG_PROPERTIES.get("DGCDriverPath");
	String BUS_DGC_PATH = CONFIG_PROPERTIES.get("BUSDGCDriverPath");
	String TestRailRunId = CONFIG_PROPERTIES.get("TestRailRunId");
	String DGCDomainName = CONFIG_PROPERTIES.get("DGCDomain");
	String DGCAccessUserName = CONFIG_PROPERTIES.get("DGCAccessUserName");
	String DGCAccessPassword = CONFIG_PROPERTIES.get("DGCAccessPassword");
	String TestEnvAPIEndpoint = CONFIG_PROPERTIES.get("TestEnvAPIEndpoint");
	String platformName = CONFIG_PROPERTIES.get("platformName");
	String platformNameiOS = CONFIG_PROPERTIES.get("platformNameiOS");
	String app = CONFIG_PROPERTIES.get("app");
	String browsername = CONFIG_PROPERTIES.get("browsername");
	String executionenv = CONFIG_PROPERTIES.get("executionenv");
	String platformVersion = CONFIG_PROPERTIES.get("platformVersion");
	String appiumUrl = CONFIG_PROPERTIES.get("appiumUrl");
	String deviceName = CONFIG_PROPERTIES.get("deviceName");
	String udid = CONFIG_PROPERTIES.get("udid");
	String bundleId = CONFIG_PROPERTIES.get("bundleId");
	String appPackage = CONFIG_PROPERTIES.get("appPackage");
	String appActivity = CONFIG_PROPERTIES.get("appActivity");
	String appWaitActivity = CONFIG_PROPERTIES.get("appWaitActivity");
	String autoGrantPermissions = CONFIG_PROPERTIES.get("autoGrantPermissions");
	String locale = CONFIG_PROPERTIES.get("locale");
	String noReset = CONFIG_PROPERTIES.get("noReset");
	String fullReset = CONFIG_PROPERTIES.get("fullReset");
	String deviceOrientation = CONFIG_PROPERTIES.get("deviceOrientation");
	String automationName = CONFIG_PROPERTIES.get("automationName");
	String appiumVersion = CONFIG_PROPERTIES.get("appiumVersion");
	
} 