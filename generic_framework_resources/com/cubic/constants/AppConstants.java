package com.cubic.constants;

import java.util.Hashtable;
import com.cubic.genericutils.PropertiesUtil;

public interface AppConstants {
	
	Hashtable<String, String> CONFIG_PROPERTIES = PropertiesUtil.getPropertysAsHashtable(System.getProperty("user.dir")+"/resources/Config.properties");

	String APP_BASEURL = CONFIG_PROPERTIES.get("baseUrl");
	String TESTDATA_FOLDER_PATH = CONFIG_PROPERTIES.get("testdata_folder_path");
	String DATA_PROVIDER = "dataProvider";
} 