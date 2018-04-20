package com.tfl.appium.lib;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.cubic.accelerators.RESTActions;
import com.cubic.appiumaction.AppiumActions;
import com.cubic.constants.AppConstants;
import com.cubic.genericutils.FileUtil;
import com.cubic.genericutils.JsonUtil;
import com.cubic.genericutils.XmlUtil;
import com.cubic.logutils.Log4jUtil;
import com.tfl.appium.page.HomePage;
import com.tfl.appium.page.LoginPage;
import com.tfl.appium.page.LoginPage;
import com.tfl.appium.page.topupCardPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;

public class createAutoLoadTransactionRecords{

	/**
	 *This method is to Create Auto Load Transaction Records and returns auto load response as a string when supplied with desired parameters.	
	 * @param data
	 * @throws Throwable
	 */
	public String createAutoLoadRecords(ITestContext context,Hashtable<String, String> data,RESTActions action) throws Throwable{
		String autoLoadXMLResponse = null;
		try {		
		String TransactionHeaderFile = data.get("TransactionHeader");
		String TransactionData =FileUtil.readFile(TransactionHeaderFile);
		String cardNumber = data.get("PrestigeCardNumber");
		String oysterCardNumber = cardNumber+JsonUtil.getJsonElement(TransactionData, "$."+cardNumber+".NTMNoCheckDigits");
	
        String testDataXMLTemplatePath = FileUtil.readFile(System.getProperty("user.dir") + "/" + data.get("RequestXMLPath"));
        
        String uri = AppConstants.TestEnvAPIEndpoint; 
        
        Date date = new Date();
        String RequestExpiryDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
    	
        testDataXMLTemplatePath = testDataXMLTemplatePath.replaceAll("@REQID@", data.get("RequestID"));
        testDataXMLTemplatePath = testDataXMLTemplatePath.replaceAll("@OysterID@", oysterCardNumber);
        testDataXMLTemplatePath = testDataXMLTemplatePath.replaceAll("@AutoLoadState@", data.get("AutoLoadState"));
        testDataXMLTemplatePath = testDataXMLTemplatePath.replaceAll("@PickupMode@", data.get("PickupMode"));
        testDataXMLTemplatePath = testDataXMLTemplatePath.replaceAll("@RequestExpiryDate@", RequestExpiryDate);
        testDataXMLTemplatePath = testDataXMLTemplatePath.replaceAll("@UserID@", data.get("UserID"));
        testDataXMLTemplatePath = testDataXMLTemplatePath.replaceAll("@Password@", data.get("Password"));
        for (int i = 0; i < 16; i++) {
        	autoLoadXMLResponse = action.postXMLResponseAsString(uri, testDataXMLTemplatePath,null , null);	
        	
        	/*String data2 = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\r\n" + 
    				"<RequestFailure>\r\n" + 
    				"    <RequestID>12345</RequestID>\r\n" + 
    				"    <OysterID>9092342072</OysterID>\r\n" + 
    				"    <ErrorCode>520</ErrorCode>\r\n" + 
    				"    <ErrorDescription>MAXIMUM NUMBER OF CARD REQUESTS EXCEEDED</ErrorDescription>\r\n" + 
    				"</RequestFailure>"; */
        	//String xpathresponse = XmlUtil.getXmlElement(data2.toString(),"//RequestID" ) ;
        	//String xpathresponse = getXmlElement(data2,"//RequestID" ) ;
        	//System.out.println("xpathresponse "+xpathresponse);
        	//boolean isUserValid = action.assertXmlElement(autoLoadXMLResponse, "/RequestFailure/OysterID", oysterCardNumber, "User Name"); 
		}        
        
        action.successReportForXmlWebService("Verify the response for Get AutoLoad request ", "Successfully got the response from the server ",autoLoadXMLResponse);
      
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			action.failureReportForXmlWebService("Verify the response for Get AutoLoad request ", "Failure in getting the response from the server ",autoLoadXMLResponse);
		}
		  return autoLoadXMLResponse;
	}
	/**
	 * This method returns a string with actual value when supplied with desired parameters  
	 * @param xmlData
	 * @param xmlPathExpression
	 * @return
	 */
	public static String getXmlElement(String xmlData, String xmlPathExpression){

		String actualVal = null;
		try{
			Document xmlDocument = getXMLDocumentObject(xmlData);
			XPathFactory xpathFactory = XPathFactory.newInstance();
			XPath xpath = xpathFactory.newXPath();
			actualVal = xpath.compile(xmlPathExpression).evaluate(xmlDocument);
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return actualVal;
	}	
	
	/**
	 * Converts the inputXml(i.e. String xml) into org.w3c.dom.Document and returns the org.w3c.dom.Document object.
	 * @param inputXml 
	 * @return org.w3c.dom.Document 
	 */
	//public synchronized static Document getXMLDocumentObject(String inputXml) {
	public static Document getXMLDocumentObject(String inputXml) {
		Document document = null;

		try {
			if (inputXml == null) {
				throw new Exception("inputXml should not be null");
			}

			InputSource inputSource = new InputSource();
			inputSource.setCharacterStream(new StringReader(inputXml));

			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilderFactory.setNamespaceAware(true);

			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			document = documentBuilder.parse(inputSource);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return document;
	}
}
