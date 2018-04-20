package com.cubic.commonLib;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.cubic.constants.AppConstants;
import com.cubic.datadriven.TestDataUtil;

public class DataProviderSource {

	@DataProvider(name = AppConstants.DATA_PROVIDER)
	public static Object[][] dataProvider(Method method) throws Throwable {

		String testDatafilePath = AppConstants.TESTDATA_FOLDER_PATH+"/"+method.getDeclaringClass().getSimpleName()+".json";
		String parentElement = method.getName();
		return TestDataUtil.getTestDataFromJson(testDatafilePath, parentElement);
	}	
}
