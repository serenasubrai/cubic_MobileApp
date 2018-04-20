/*package com.tfl.appium.lib;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;

import com.cubic.appiumaction.AppiumActions;
import com.cubic.constants.AppConstants;
import com.cubic.database.DataBaseUtil;
import com.cubic.genericutils.FileUtil;
import com.cubic.genericutils.JsonUtil;

public class OysterCreateTransactionBackup1 {
	//FileHeader
	public static long creationDate;
	public static long creationTime;

	//Record Header
	public static long RecordType;
	public static long RecordNumber;
	public static long RecordLength;

	//TH
	public static long TransactionDate;
	public static long PrestigeStationNumber;
	public static long TicketHall;
	public static long DeviceLocation;
	public static long DeviceType;
	public static long Generation;
	public static long PlinthNumber;
	public static long SpecialResetCount ;
	public static long TransactionNumber;
	public static long OperatorIdentity;
	public static long TransactionTime;
	public static long TransactionType;

	//ETH
	public static long TransactionSubType ;
	public static long LinkingSequenceNo  ;
	public static long LinkingStatus  ;

	//RTD Transaction Header
	public static long txnType;
	public static long txnTypeV6;
	public static long TestTrainingFlag;
	public static long CurrencyType;
	public static long fulFlag;
	public static long padding;
	public static long disablePPToverLoading;
	public static long disableSVOverriding;
	public static long ntmCardUpdated;
	public static long overallTxnlength;
	public static long currentTxnLength;
	public static long txnDate;
	public static long txnTime;
	public static long NTMno;
	public static long NTMNoCheckDigits;
	public static long CSN ;
	public static long NTMTxnSeqNo;
	public static long RTDID ;
	public static long RTDTxnSeqNo;
	public static long plinthNumber;
	public static long NLC;
	public static long deviceType;
	public static long SubSystemID;
	public static long TxnTimeSecs;
	public static long TxnPerfTime;
	public static long PhysicalCardType;
	public static long LinkingSeqNo;
	public static long FULCurrentASN;
	public static long FULASNStatuses ;
	public static long PreviousTranSummaries;

	//Transaction Body
	public static long resultCode;
	public static long stationNLC;
	public static long fullEntryAdjustmentDeducted;
	public static long entryAdjustmentDeducted;
	public static long SVBalance;
	public static long autoContinutionFlag;
	public static long autoCompletionFlag;
	public static long osiEntryFlag;
	public static long svInvlovedFlag;
	public static long ppt3UsedFlag;
	public static long ppt2UsedFlag;
	public static long ppt1UsedFlag;
	public static long tokenExit;
	public static long sparebit14;
	public static long previousValidationErrorFlag;
	public static long unStartedPPTExitFlag;
	public static long unCompletedPPTEntryFlag;
	public static long exitChargeAppliedFlag;
	public static long passbackAuthorised ;
	public static long dailyCapping ;
	public static long TransferDiscountApp;
	public static long PPTValidityPageCRC;
	public static long passengerType;
	public static long discountType;
	public static long zoneRangeCode;
	public static long cappingSceme;
	public static long pptProductCode;
	public static long oepUsedFlag;
	public static long differentStationRegion;
	public static long sameStationReentryFlag;
	public static long relevantRoutingNodeFlag;
	public static long zonalIndicator;
	public static long spareBit35;
	public static long tktDiscountApplied;
	public static long oysterFareType;
	public static long crossDayJourneyFlag;
	public static long travelSetting;

	//PAYG Transaction Body
	public static long RequestSeqNo;
	public static long svAdded;
	public static long paymentMethod;
	public static long svBalance;

	//EXIT Transaction Body
	public static long fullExitAdjustmentDeducted  = -300;
	public static long exitAdjustmentDeducted  = -300;
	public static long PPT1ValidityPageCRC  = 0;
	public static long PPT2ValidityPageCRC  = 0;
	public static long PPT3ValidityPageCRC  = 0;
	public static long timeOfFirstEntry  = 900;
	public static long spareByte  = 0;
	public static long zonalValidity  = 0;
	public static long fullFare  = 240;
	public static long discountedFare  = 240;
	public static long pptPassengerType  = 0;
	public static long twoBitFlag  = 0;
	public static long sameStationExitFlag  =  0;

	//TransactionTrailer
	public static long MAC;

	//Record Header for File Trailer
	public static long fileTrailerRecordType;
	public static long fileTrailerRecordNumber;
	public static long fileTrailerRecordLength;

	//File Trailer
	public static long completionDate;
	public static long completionTime;
	public static long numberofRecords;

	//General File Initilizations
	public static DataOutputStream os;
	public static File file;
	public static String reverseString="";
	//public static Writer wr2;
	public static String finalBinaryData = "";
	public static long finalDecimalvalue=0;
	public static FileOutputStream output= null;

	//Database Connection Initilizations
	private static Connection connection = null;
	private static ResultSet resultSet = null;
	public static ResultSetMetaData rsmd = null;
	public DataBaseUtil util;
	public static int fileCounter = 0;
	public static  String transactionFilePath = null;



	public  void hexaToInt(String hex) throws IOException{
		long a = Long.parseLong(hex, 16);
		createbinary(a,"binary",1,0);
	}

	public  String appendZero(int zeroVal, int lenDifference){
		String appendZeroVal = "";
		while(!(appendZeroVal.length()==lenDifference)){
			appendZeroVal=0+appendZeroVal;
			//return appendZeroVal;
		}
		return appendZeroVal;


	}

	public  String longToString(long number, long groupSize) {
		StringBuilder result = new StringBuilder();

		for(long i = 31; i >= 0 ; i--) {
			long mask = 1 << i;
			result.append((number & mask) != 0 ? "1" : "0");

			if (i % groupSize == 0)
				result.append(" ");
		}
		result.replace(result.length() - 1, result.length(), "");

		return result.toString();
	}

	public void CreateBCD(long decValue, int bytes, int bitPosition) throws NumberFormatException, IOException{
		String decStrValue =  decValue+"";
		//System.out.println("Length of DecString is "+decStrValue.length());
		int byteLen = bytes*2;
		//System.out.println("Length of Bytes "+byteLen);
		if (byteLen==decStrValue.length()) {
			String fullBCDStr = decStrValue;
			//System.out.println("fullBCDStr is "+fullBCDStr);
			List<String> myList = new ArrayList<String>();
			while(fullBCDStr.length()!=0){
				myList.add(fullBCDStr.substring(0,2));
				fullBCDStr = fullBCDStr.substring(2);
			}
			for (String a : myList) {
				hexaToInt(a);

			}
		}
		else{
			int lenDifference = byteLen-decStrValue.length();
			//System.out.println("Zeros are "+longToString(0, lenDifference));
			String appendedStr = appendZero(0, lenDifference);
			String fullBCDStr = appendedStr+decStrValue;
			//System.out.println("fullBCDStr is "+fullBCDStr);
			List<String> myList = new ArrayList<String>();
			while(fullBCDStr.length()!=0){
				myList.add(fullBCDStr.substring(0,2));
				fullBCDStr = fullBCDStr.substring(2);
			}
			for (String a : myList) {
				hexaToInt(a);

			}
		}

	}

	public  void createbinary(long decValue,String dataType, int bytes, int bitPosition) throws IOException {
		// TODO Auto-generated method stub

		if (bitPosition>0&&dataType.equalsIgnoreCase("BitField")) {
			//String bindarydata22 = Long.toBinaryString(decValue).toString();
			String bindaryStringdata =longToString(decValue,8).toString();
			//System.out.println("binary data is "+bindaryStringdata);
			String initialbBinaryData = bindaryStringdata.substring(bindaryStringdata.length()-bitPosition);
			finalBinaryData = finalBinaryData+initialbBinaryData;
			//	System.out.println("Length of the string is "+finalBinaryData);
			if (finalBinaryData.length()==8) {
				os = new DataOutputStream(new FileOutputStream(System.getProperty("user.dir")+"\\outputBinary.dat"));
				finalDecimalvalue = Long.parseLong(finalBinaryData,2);
				//long decimalLongVal = Long.parseLong(finalBinaryData,2);
				os.writeLong(finalDecimalvalue);
				createByte(bytes);
				finalBinaryData="";
				//os.close();
			}
		}
		else if(dataType.equalsIgnoreCase("BCD")){
			CreateBCD(decValue, bytes, bitPosition);
		}
		else if(!(Long.valueOf(decValue)==null)&&dataType.equalsIgnoreCase("Binary")){
			os = new DataOutputStream(new FileOutputStream(System.getProperty("user.dir")+"\\outputBinary.dat"));
			finalDecimalvalue = decValue;
			os.writeLong(finalDecimalvalue);
			os.close();
			createByte(bytes);

		}


	}

	public  void createByte(int bytes) throws IOException {
		// TODO Auto-generated method stub
		//File file = new File("D:\\binout.dat");
		//output = new FileOutputStream(System.getProperty("user.dir")+"\\tra_"+getCurrentdayKey()+"_000"+recordHeaderCount+".dat", true);
		byte[] bFile = readBytesFromFile(System.getProperty("user.dir")+"\\outputBinary.dat");
		byte[] bFile3 = new byte[bytes];
		int j = bFile.length;
		//System.out.println("ByteLength is "+bFile.length);
		int l=0;
		for(int k=0; k<bytes;k++){
			bFile3[k] = bFile[j-1];
			j=j-1;
		}
		try {
			if (transactionMode.equalsIgnoreCase("PAYG")) {
				output = new FileOutputStream(System.getProperty("user.dir")+"\\tra_"+getCurrentdayKey()+"_0001.dat", true);
				output.write(bFile3);
			}
			else{
				output = new FileOutputStream(System.getProperty("user.dir")+"\\tra_"+getCurrentdayKey()+"_0002.dat", true);
				output.write(bFile3);
			}
			output.write(bFile3);

		} finally {

		}

	}

	public static byte[] readBytesFromFile(String filePath) {

		FileInputStream fileInputStream = null;
		byte[] bytesArray = null;

		try {

			File file = new File(filePath);
			bytesArray = new byte[(int) file.length()];
			//System.out.println("bytes array is "+bytesArray.length);
			//read file into bytes[]
			fileInputStream = new FileInputStream(file);
			fileInputStream.read(bytesArray);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		return bytesArray;

	}

	public int getCurrentdayKey(){
		int dayKey = 0;
		try {
			util = DataBaseUtil.getDatabaseConnection("ORACLE");
			resultSet = util.retrieveData("select trunc(sysdate)  - to_date('01011980','ddmmyyyy') from dual");
			while(resultSet.next())
			{
				dayKey = resultSet.getInt(1);
				break;
			}
			util.closeConnections();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dayKey;

	}

	public Long getCurrentUTCTime(){
		Calendar now = Calendar.getInstance();
		String time = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE);
		String[] timesecs = time.split(":");
		long hours = Long.parseLong(timesecs[0])*60;
		long mins = Long.parseLong(timesecs[1]);
		long total = hours+mins;
		//System.out.println("total time in UTC is "+total);
		return total;

		//System.out.println(now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE));
	}

	public String getDBColumnValue(String DatabaseQuery, String ColumnName){
		String ColumnValue = null;
		try {
			util = DataBaseUtil.getDatabaseConnection("ORACLE");
			//resultSet = util.retrieveData("select * from RDD_stations where stationname like '%Elephant & Castle%'");
			resultSet = util.retrieveData(DatabaseQuery);
			//System.out.println("Value of column is " + rs.getInt(1));
			while(resultSet.next())
			{
				ColumnValue= resultSet.getString(ColumnName);
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ColumnValue;

	}

	public void generateFileHeader(Hashtable<String, String> data){

		try {
			String txnMode = data.get("TransactionType");

			creationDate = getCurrentdayKey();

			if (txnMode.equalsIgnoreCase("Exit")) {
				creationTime = getCurrentUTCTime();
			}
			else{
				if (creationTime==0) {
					creationTime = getCurrentUTCTime();
				}
			}
			createbinary(creationDate,"Binary",2,0);
			createbinary(creationTime,"Binary",2,0);

			//action.successReport("Verify the File Header is Created", "File Header is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			//action.failureReport("Verify the File Header is Created", "File Header is NOT Created");
		}


	}

	public void generateRecordHeader(Hashtable<String, String> data) throws Throwable {
		try {
			String EntryTransactionFile = data.get("TransactionHeader");
			String EntryTransactionData =FileUtil.readFile(EntryTransactionFile);
			String txnMode = data.get("TransactionType");

			RecordType = Long.parseLong(JsonUtil.getJsonElement(EntryTransactionData, "$.RecordType"));
			RecordNumber = Long.parseLong(JsonUtil.getJsonElement(EntryTransactionData, "$.RecordNumber"));
			//RecordNumber = recordHeaderCount;
			RecordLength = Long.parseLong(JsonUtil.getJsonElement(EntryTransactionData, "$."+txnMode+".RecordLength"));

			createbinary(RecordType,"Binary",4,0);
			createbinary(RecordNumber,"Binary",4,0);
			createbinary(RecordLength,"Binary",4,0);
			//action.successReport("Verify the Record Header is Created", "Record Header is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			//action.failureReport("Verify the Record Header is Created", "Record Header is NOT Created");
		}

	}

	public void generateTH(Hashtable<String, String> data) throws Throwable {
		try {
			String TransactionHeaderFile = data.get("TransactionHeader");
			String TransactionData =FileUtil.readFile(TransactionHeaderFile);
			String StationName = data.get("StationName");

			TransactionDate = creationDate;
			PrestigeStationNumber = Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$."+StationName+".PrestigeStationNumber"));
			TicketHall = Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.TicketHall"));
			DeviceLocation = Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.DeviceLocation"));
			DeviceType = Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$."+StationName+".DeviceType"));
			Generation = Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.Generation"));
			PlinthNumber = Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$."+StationName+".PlinthNumber"));
			SpecialResetCount = Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.SpecialResetCount"));
			TransactionNumber = Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.TransactionNumber"));
			OperatorIdentity = Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.OperatorIdentity"));
			TransactionTime = creationTime;
			TransactionType= Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.TransactionType"));

			createbinary(TransactionDate,"Binary",2,0);
			createbinary(PrestigeStationNumber,"Binary",2,0);
			createbinary(TicketHall,"Binary",1,0);
			createbinary(DeviceLocation,"Binary",1,0);
			createbinary(DeviceType,"BitField",1,4);
			createbinary(Generation,"BitField",1,4);
			createbinary(PlinthNumber,"Binary",1,0);
			createbinary(SpecialResetCount,"Binary",1,0);
			createbinary(TransactionNumber,"Binary",2,0);
			createbinary(OperatorIdentity,"BCD",3,0);
			createbinary(TransactionTime,"Binary",2,0);
			createbinary(TransactionType,"Binary",1,0);
			
			//action.successReport("Verify the Transaction Header is Created", "Transaction Header is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			//action.failureReport("Verify the Transaction Header is Created", "Transaction Header is NOT Created");
		}

	}
	public void generateETH(Hashtable<String, String> data) throws Throwable{
		try {
			String TransactionHeaderFile = data.get("TransactionHeader");
			String TransactionData =FileUtil.readFile(TransactionHeaderFile);
			TransactionSubType= Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.TransactionSubType"));
			LinkingSequenceNo= Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.LinkingSequenceNo"));
			LinkingStatus=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.LinkingStatus"));

			createbinary(TransactionSubType,"Binary",1,0);
			createbinary(LinkingSequenceNo,"Binary",1,0);
			createbinary(LinkingStatus,"Binary",1,0);
			//action.successReport("Verify the ETH Header is Created", "ETH Header is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			//action.failureReport("Verify the ETH Header is Created", "ETH Header is NOT Created");
		}
	}
	public void generateRTDTransactionHeader(Hashtable<String, String> data) throws Throwable{
		try {
			String TransactionHeaderFile = data.get("TransactionHeader");
			String TransactionData =FileUtil.readFile(TransactionHeaderFile);
			String txnMode = data.get("TransactionType");
			String cardNumber = data.get("PrestigeCardNumber");
			String stationName = data.get("StationName");

			txnType= Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.txnType"));
			txnTypeV6= Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$."+txnMode+".txnTypeV6"));
			TestTrainingFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.TestTrainingFlag"));
			CurrencyType=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.CurrencyType"));
			fulFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.fulFlag"));
			padding=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.padding"));
			disablePPToverLoading=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.disablePPToverLoading"));
			disableSVOverriding=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.disableSVOverriding"));
			ntmCardUpdated=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.ntmCardUpdated"));
			overallTxnlength=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$."+txnMode+".overallTxnlength"));
			currentTxnLength=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$."+txnMode+".currentTxnLength"));
			txnDate=creationDate;
			txnTime=creationTime;
			NTMno=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$."+cardNumber+".NTMno"));
			NTMNoCheckDigits=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$."+cardNumber+".NTMNoCheckDigits"));
			CSN=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$."+cardNumber+".CSN"));
			NTMTxnSeqNo= Long.parseLong(getDBColumnValue("SELECT * FROM DD_CardTransaction WHERE prestigeid like '%"+cardNumber+"' ORDER BY createddate DESC", "CARDSEQNO"))+1;
			RTDID=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$."+stationName+".RTDID"));
			String RTDIDString = JsonUtil.getJsonElement(TransactionData, "$."+stationName+".RTDID");
			String RTDSeqNO = getDBColumnValue("select MAX(RTDSEQUENCENO) from tdms_rtdtransactionheader WHERE RTDID = "+RTDIDString+" and DAYKEY = "+getCurrentdayKey(),"MAX(RTDSEQUENCENO)");
			if (RTDSeqNO==null) {
				RTDTxnSeqNo=1;
			}else{
				RTDTxnSeqNo=Long.parseLong(RTDSeqNO);
			}
			plinthNumber=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$."+stationName+".PlinthNumber"));
			NLC=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$."+stationName+".NLC"));
			deviceType=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$."+stationName+".DeviceType"));
			SubSystemID=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$."+stationName+".SubSystemID"));
			TxnTimeSecs=FileUtil.generateRandomNumber(50,30);
			TxnPerfTime=FileUtil.generateRandomNumber(900,300);
			PhysicalCardType=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.PhysicalCardType"));
			LinkingSeqNo=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.LinkingSeqNo"));
			FULCurrentASN=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.FULCurrentASN"));
			FULASNStatuses=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.FULASNStatuses"));
			PreviousTranSummaries=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.PreviousTranSummaries"));

			createbinary(txnType,"Binary",1,0);
			createbinary(txnTypeV6,"Binary",1,0);
			createbinary(TestTrainingFlag,"BitField",1,1);
			createbinary(CurrencyType,"BitField",1,1);
			createbinary(fulFlag,"BitField",1,1);
			createbinary(padding,"BitField",1,2);
			createbinary(disablePPToverLoading,"BitField",1,1);
			createbinary(disableSVOverriding,"BitField",1,1);
			createbinary(ntmCardUpdated,"BitField",1,1);
			createbinary(overallTxnlength,"Binary",1,0);
			createbinary(currentTxnLength,"Binary",1,0);
			createbinary(txnDate,"Binary",2,0);
			createbinary(txnTime,"Binary",2,0);
			createbinary(NTMno,"Binary",4,0);
			createbinary(NTMNoCheckDigits,"Binary",1,0);
			createbinary(CSN,"Binary",7,0);
			createbinary(NTMTxnSeqNo,"Binary",2,0);
			createbinary(RTDID,"Binary",3,0);
			createbinary(RTDTxnSeqNo,"Binary",2,0);
			createbinary(plinthNumber,"Binary",1,0);
			createbinary(NLC,"Binary",2,0);
			createbinary(deviceType,"Binary",1,0);
			createbinary(SubSystemID,"Binary",1,0);
			createbinary(TxnTimeSecs,"Binary",1,0);
			createbinary(TxnPerfTime,"Binary",2,0);
			createbinary(PhysicalCardType,"Binary",1,0);
			createbinary(LinkingSeqNo,"Binary",1,0);
			createbinary(FULCurrentASN,"Binary",1,0);
			createbinary(FULASNStatuses,"Binary",1,0);
			createbinary(PreviousTranSummaries,"Binary",1,0);


			//action.successReport("Verify the RTD Transaction Header is Created", "RTD Transaction Header is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			//action.failureReport("Verify the RTD TransactionHeader is Created", "RTD Transaction Header is NOT Created");
		}
	}

	public void generateEntryTransactionBody(Hashtable<String, String> data) throws Throwable{
		try {
			String EntryTransactionFile = data.get("EntryTransaction");
			String TransactionEntryData =FileUtil.readFile(EntryTransactionFile);
			String TransactionHeaderFile = data.get("TransactionHeader");
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);
			String cardNumber = data.get("PrestigeCardNumber");
			String stationName = data.get("StationName");

			resultCode=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.resultCode"));
			stationNLC=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".NLC"));
			fullEntryAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".fullEntryAdjustmentDeducted"));
			entryAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".entryAdjustmentDeducted"));
			SVBalance=Long.parseLong(getDBColumnValue("SELECT * FROM DD_CardTransaction WHERE prestigeid like '%"+cardNumber+"' ORDER BY createddate DESC", "PAYGBALANCE"))-fullEntryAdjustmentDeducted;
			autoContinutionFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.autoContinutionFlag"));
			autoCompletionFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.autoCompletionFlag"));
			osiEntryFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.osiEntryFlag"));
			svInvlovedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.svInvlovedFlag"));
			ppt3UsedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.ppt3UsedFlag"));
			ppt2UsedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.ppt2UsedFlag"));
			ppt1UsedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.ppt1UsedFlag"));
			tokenExit=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.tokenExit"));
			sparebit14=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.sparebit14"));
			previousValidationErrorFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.previousValidationErrorFlag"));
			unStartedPPTExitFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.unStartedPPTExitFlag"));
			unCompletedPPTEntryFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.unCompletedPPTEntryFlag"));
			exitChargeAppliedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.exitChargeAppliedFlag"));
			passbackAuthorised=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.passbackAuthorised"));
			dailyCapping=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.dailyCapping"));
			TransferDiscountApp=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.TransferDiscountApp"));
			PPTValidityPageCRC=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.PPTValidityPageCRC"));
			passengerType=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.passengerType"));
			discountType=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.discountType"));
			zoneRangeCode=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.zoneRangeCode"));
			cappingSceme=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.cappingSceme"));
			pptProductCode=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.pptProductCode"));
			oepUsedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.oepUsedFlag"));
			differentStationRegion=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.differentStationRegion"));
			sameStationReentryFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.sameStationReentryFlag"));
			relevantRoutingNodeFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.relevantRoutingNodeFlag"));
			zonalIndicator=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.zonalIndicator"));
			spareBit35=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.spareBit35"));
			tktDiscountApplied=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.tktDiscountApplied"));
			oysterFareType=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.oysterFareType"));
			crossDayJourneyFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.crossDayJourneyFlag"));
			travelSetting=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.travelSetting"));

			createbinary(resultCode,"Binary",1,0);
			createbinary(stationNLC,"Binary",2,0);
			createbinary(fullEntryAdjustmentDeducted,"Binary",2,0);
			createbinary(entryAdjustmentDeducted,"Binary",2,0);
			createbinary(SVBalance,"Binary",2,0);
			createbinary(autoContinutionFlag,"BitField",1,1);
			createbinary(autoCompletionFlag,"BitField",1,1);
			createbinary(osiEntryFlag,"BitField",1,1);
			createbinary(svInvlovedFlag,"BitField",1,1);
			createbinary(ppt3UsedFlag,"BitField",1,1);
			createbinary(ppt2UsedFlag,"BitField",1,1);
			createbinary(ppt1UsedFlag,"BitField",1,1);
			createbinary(tokenExit,"BitField",1,1);
			createbinary(sparebit14,"BitField",1,1);
			createbinary(previousValidationErrorFlag,"BitField",1,1);
			createbinary(unStartedPPTExitFlag,"BitField",1,1);
			createbinary(unCompletedPPTEntryFlag,"BitField",1,1);
			createbinary(exitChargeAppliedFlag,"BitField",1,1);
			createbinary(passbackAuthorised,"BitField",1,1);
			createbinary(dailyCapping,"BitField",1,1);
			createbinary(TransferDiscountApp,"BitField",1,1);
			createbinary(PPTValidityPageCRC,"Binary",2,0);
			createbinary(passengerType,"BitField",1,3);
			createbinary(discountType,"BitField",1,5);
			createbinary(zoneRangeCode,"BitField",1,4);
			createbinary(cappingSceme,"BitField",1,4);
			createbinary(pptProductCode,"Binary",1,0);
			createbinary(oepUsedFlag,"BitField",1,1);
			createbinary(differentStationRegion,"BitField",1,1);
			createbinary(sameStationReentryFlag,"BitField",1,1);
			createbinary(relevantRoutingNodeFlag,"BitField",1,1);
			createbinary(zonalIndicator,"BitField",1,4);
			createbinary(spareBit35,"BitField",1,1);
			createbinary(tktDiscountApplied,"BitField",1,1);
			createbinary(oysterFareType,"BitField",1,2);
			createbinary(crossDayJourneyFlag,"BitField",1,1);
			createbinary(travelSetting,"BitField",1,3);


			//action.successReport("Verify the Entry Transaction Body is Created", "Entry Transaction Body is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			//action.failureReport("Verify the Entry Transaction Body is Created", "Entry Transaction Body is NOT Created");
		}
	}


	public void generateExitTransactionBody(Hashtable<String, String> data) throws Throwable{
		try {
			String ExitTransactionFile = data.get("ExitTransaction");
			String TransactionExitData =FileUtil.readFile(ExitTransactionFile);
			String TransactionHeaderFile = data.get("TransactionHeader");
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);
			String cardNumber = data.get("PrestigeCardNumber");
			String stationName = data.get("StationName");

			resultCode=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.resultCode"));
			stationNLC=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".NLC"));
			fullExitAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.fullExitAdjustmentDeducted"));
			exitAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.exitAdjustmentDeducted"));
			SVBalance=Long.parseLong(getDBColumnValue("SELECT * FROM DD_CardTransaction WHERE prestigeid like '%"+cardNumber+"' ORDER BY createddate DESC", "PAYGBALANCE"))-fullEntryAdjustmentDeducted;
			autoContinutionFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.autoContinutionFlag"));
			autoCompletionFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.autoCompletionFlag"));
			osiEntryFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.osiEntryFlag"));
			svInvlovedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.svInvlovedFlag"));
			ppt3UsedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.ppt3UsedFlag"));
			ppt2UsedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.ppt2UsedFlag"));
			ppt1UsedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.ppt1UsedFlag"));
			tokenExit=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.tokenExit"));
			sparebit14=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.sparebit14"));
			previousValidationErrorFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.previousValidationErrorFlag"));
			unStartedPPTExitFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.unStartedPPTExitFlag"));
			unCompletedPPTEntryFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.unCompletedPPTEntryFlag"));
			exitChargeAppliedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.exitChargeAppliedFlag"));
			passbackAuthorised=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.passbackAuthorised"));
			dailyCapping=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.dailyCapping"));
			TransferDiscountApp=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.TransferDiscountApp"));
			PPT1ValidityPageCRC=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.PPT1ValidityPageCRC"));
			PPT2ValidityPageCRC=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.PPT2ValidityPageCRC"));
			PPT3ValidityPageCRC=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.PPT3ValidityPageCRC"));
			passengerType=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.passengerType"));
			discountType=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.discountType"));
			zoneRangeCode=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.zoneRangeCode"));
			cappingSceme=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.cappingSceme"));
			pptProductCode=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.pptProductCode"));
			timeOfFirstEntry=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.timeOfFirstEntry"));
			spareByte=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.spareByte"));
			zonalValidity=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.zonalValidity"));
			fullFare=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.fullFare"));
			discountedFare=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.discountedFare"));
			pptPassengerType=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.pptPassengerType"));
			twoBitFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.twoBitFlag"));
			sameStationExitFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.sameStationExitFlag"));
			relevantRoutingNodeFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.relevantRoutingNodeFlag"));
			zonalIndicator=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.zonalIndicator"));
			spareBit35=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.spareBit35"));
			tktDiscountApplied=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.tktDiscountApplied"));
			oysterFareType=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.oysterFareType"));
			crossDayJourneyFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.crossDayJourneyFlag"));
			travelSetting=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.travelSetting"));

			createbinary(resultCode,"Binary",1,0);
			createbinary(stationNLC,"Binary",2,0);
			createbinary(fullExitAdjustmentDeducted,"Binary",2,0);
			createbinary(exitAdjustmentDeducted,"Binary",2,0);
			createbinary(SVBalance,"Binary",2,0);
			createbinary(autoContinutionFlag,"BitField",1,1);
			createbinary(autoCompletionFlag,"BitField",1,1);
			createbinary(osiEntryFlag,"BitField",1,1);
			createbinary(svInvlovedFlag,"BitField",1,1);
			createbinary(ppt3UsedFlag,"BitField",1,1);
			createbinary(ppt2UsedFlag,"BitField",1,1);
			createbinary(ppt1UsedFlag,"BitField",1,1);
			createbinary(tokenExit,"BitField",1,1);
			createbinary(sparebit14,"BitField",1,1);
			createbinary(previousValidationErrorFlag,"BitField",1,1);
			createbinary(unStartedPPTExitFlag,"BitField",1,1);
			createbinary(unCompletedPPTEntryFlag,"BitField",1,1);
			createbinary(exitChargeAppliedFlag,"BitField",1,1);
			createbinary(passbackAuthorised,"BitField",1,1);
			createbinary(dailyCapping,"BitField",1,1);
			createbinary(TransferDiscountApp,"BitField",1,1);
			createbinary(PPT1ValidityPageCRC,"Binary",2,0);
			createbinary(PPT2ValidityPageCRC,"Binary",2,0);
			createbinary(PPT3ValidityPageCRC,"Binary",2,0);
			createbinary(passengerType,"BitField",1,3);
			createbinary(discountType,"BitField",1,5);
			createbinary(zoneRangeCode,"BitField",1,4);
			createbinary(cappingSceme,"BitField",1,4);
			createbinary(pptProductCode,"Binary",1,0);
			createbinary(timeOfFirstEntry,"Binary",2,0);
			createbinary(spareByte,"Binary",1,0);
			createbinary(zonalValidity,"Binary",2,0);
			createbinary(fullFare,"Binary",2,0);
			createbinary(discountedFare,"Binary",2,0);
			createbinary(pptPassengerType,"Binary",1,0);
			createbinary(twoBitFlag,"BitField",1,2);
			createbinary(sameStationExitFlag,"BitField",1,1);
			createbinary(relevantRoutingNodeFlag,"BitField",1,1);
			createbinary(zonalIndicator,"BitField",1,4);
			createbinary(spareBit35,"BitField",1,1);
			createbinary(tktDiscountApplied,"BitField",1,1);
			createbinary(oysterFareType,"BitField",1,2);
			createbinary(crossDayJourneyFlag,"BitField",1,1);
			createbinary(travelSetting,"BitField",1,3);


			//action.successReport("Verify the Exit Transaction Body is Created", "Exit Transaction Body is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			//action.failureReport("Verify the Exit Transaction Body is Created", "Exit Transaction Body is NOT Created");
		}
	}

	public void generateTransactionTrailer(){
		try {
			MAC = FileUtil.generateRandomNumber(225819999,225811111);
			createbinary(MAC,"Binary",4,0);
			//action.successReport("Verify the Transaction Trailer is Created", "Transaction Trailer is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			//action.failureReport("Verify the Transaction Trailer is Created", "Transaction Trailer is NOT Created");
		}


	}
	public void generateFileTrailer(Hashtable<String, String> data) throws Throwable{
		try {
			String TransactionHeaderFile = data.get("TransactionHeader");
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);

			fileTrailerRecordType=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.fileTrailerRecordType"));
			fileTrailerRecordNumber=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.fileTrailerRecordNumber"));
			fileTrailerRecordLength=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.fileTrailerRecordLength"));
			completionDate=creationDate;
			completionTime=getCurrentUTCTime();
			numberofRecords=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.numberofRecords"));

			//RH
			createbinary(fileTrailerRecordType,"Binary",4,0);
			createbinary(fileTrailerRecordNumber,"Binary",4,0);
			createbinary(fileTrailerRecordLength,"Binary",4,0);

			//FT
			createbinary(completionDate,"Binary",2,0);
			createbinary(completionTime,"Binary",2,0);
			createbinary(numberofRecords,"Binary",4,0);
			//action.successReport("Verify the File Trailer is Created", "File Trailer is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
		//action.failureReport("Verify the File Trailer is Created", "File Trailer is NOT Created");
		}

	}

	public void generatePAYGTransactionBody(Hashtable<String, String> data) throws Throwable{
		try {
			String PAYGTransaction = data.get("PAYGTransaction");
			String PAYGTransactionData =FileUtil.readFile(PAYGTransaction);
			String cardNumber = data.get("PrestigeCardNumber");

			System.out.println("Request Seq no is "+JsonUtil.getJsonElement(PAYGTransactionData, "$.RequestSeqNo"));

			RequestSeqNo = Long.parseLong(JsonUtil.getJsonElement(PAYGTransactionData, "$.RequestSeqNo"));
			svAdded = Long.parseLong(JsonUtil.getJsonElement(PAYGTransactionData, "$.svAdded"));
			paymentMethod = Long.parseLong(JsonUtil.getJsonElement(PAYGTransactionData, "$.paymentMethod"));
			svBalance= Long.parseLong(getDBColumnValue("SELECT * FROM DD_CardTransaction WHERE prestigeid like '%"+cardNumber+"' ORDER BY createddate DESC", "PAYGBALANCE"))+svAdded;

			createbinary(RequestSeqNo,"Binary",4,0);
			createbinary(svAdded,"Binary",3,0);
			createbinary(paymentMethod,"Binary",2,0);
			createbinary(svBalance,"Binary",3,0);
			//action.successReport("Verify the PAYG Transaction Body is Created", "PAYG Transaction Body is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			//action.failureReport("Verify the PAYG Transaction Body is Created", "PAYG Transaction Body is NOT Created");
		}

	}

	public String generatePAYG(Hashtable<String, String> data) throws Throwable{

		try {
			fileCounter=fileCounter+1;
			String FileName = "tra_"+getCurrentdayKey()+"_000"+fileCounter+".dat";
			FileUtil.deleteSpecificFile(FileName);
			transactionFilePath = System.getProperty("user.dir")+"\\"+FileName;
			output = new FileOutputStream(transactionFilePath, true);

			generateFileHeader(data);
			generateRecordHeader(data);
			generateTH(data);
			generateETH(data);
			generateRTDTransactionHeader(data);
			generatePAYGTransactionBody(data);
			generateTransactionTrailer();
			generateFileTrailer(data);
			output.flush();
			output.close();
			//AppiumActions actions = new AppiumActions();
			//action.successReport("Verify PAYG TAP File Creation", "PAYG Transaction TAP is created successfully");

		} catch (Exception e) {
			e.printStackTrace();
			//action.failureReport("Verify PAYG TAP File Creation", "PAYG Transaction TAP is NOT created");
		}
		return transactionFilePath;

	}
	public String generateEntryTransaction(Hashtable<String, String> data) throws Throwable{
		try {
			fileCounter=fileCounter+1;
			String FileName = "tra_"+getCurrentdayKey()+"_000"+fileCounter+".dat";
			FileUtil.deleteSpecificFile(FileName);
			transactionFilePath = System.getProperty("user.dir")+"\\"+FileName;
			output = new FileOutputStream(transactionFilePath, true);

			generateFileHeader(data);
			generateRecordHeader(data);
			generateTH(data);
			generateETH(data);
			generateRTDTransactionHeader(data);
			generateEntryTransactionBody(data);
			generateTransactionTrailer();
			generateFileTrailer(data);

			output.flush();
			output.close();
			//action.successReport("Verify Entry Transaction TAP File Creation", "Entry Transaction TAP is created successfully");

		} catch (Exception e) {
			e.printStackTrace();
			//action.failureReport("Verify Entry Transaction TAP File Creation", "Entry Transaction TAP  is NOT created");
		}
		return transactionFilePath;

	}

	public String generateExitTransaction(Hashtable<String, String> data) throws Throwable{
		try {
			fileCounter=fileCounter+1;
			String FileName = "tra_"+getCurrentdayKey()+"_000"+fileCounter+".dat";
			FileUtil.deleteSpecificFile(FileName);
			transactionFilePath = System.getProperty("user.dir")+"\\"+FileName;
			output = new FileOutputStream(transactionFilePath, true);

			generateFileHeader(data);
			generateRecordHeader(data);
			generateTH(data);
			generateETH(data);
			generateRTDTransactionHeader(data);
			generateExitTransactionBody(data);
			generateTransactionTrailer();
			generateFileTrailer(data);
			output.flush();
			output.close();
			//action.successReport("Verify Exit Transaction TAP File Creation", "Exit Transaction TAP is created successfully");

		} catch (Exception e) {
			e.printStackTrace();
			//action.failureReport("Verify Exit Transaction TAP File Creation", "Exit Transaction TAP is NOT created");
		}
		return transactionFilePath;

	}

	public void copyFileToDGC(String sourcePath, String destinationPath){
		File source = new File(sourcePath);
		File dest = new File(destinationPath);
		try {
			FileUtils.copyFileToDirectory(source, dest);
			action.successReport("Verify Transaction File Copied to DGC", "Transaction file has been copied to DGC successfully");
		} catch (IOException e) {
			e.printStackTrace();
			action.failureReport("Verify File Copied to DGC", "Transaction file is NOT copied to DGC");
		}
	}


	public OysterCreateTransaction(){
		try {
			file = new File(System.getProperty("user.dir")+"\\outputBinary.dat");
			//output = new FileOutputStream(System.getProperty("user.dir")+"\\tra_"+getCurrentdayKey()+"_0001.dat", true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}


}
*/