package com.tfl.appium.lib;

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
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;
import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import com.cubic.appiumaction.AppiumActions;
import com.cubic.constants.AppConstants;
import com.cubic.database.DataBaseUtil;
import com.cubic.genericutils.FileUtil;
import com.cubic.genericutils.JsonUtil;
import com.tfl.appium.page.HomePage;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;

public class OysterCreateTransaction {
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

	//Bus TH
	public static long BusTransactionType;
	public static long BusReaderTransactionType;
	public static long BusTransactionNumber;
	public static long BusTransactionLength;
	//public static long TransactionDate = 13798;
	//public static long TransactionTime = 834;
	public static long ReaderID;
	public static long ControllerID ;
	public static long BusReaderPosition;
	public static long ControllerTrayID;
	public static long ReaderContainerID;
	public static long GarageNumber;
	public static String FleetNumber;
	public static String RegistrationNumber;
	public static long IncidentNumber;

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

	//RTD Bus Transaction Header
	public static long busRHD0Bit7 = 0;
	public static long txnTypeBus;
	public static long txnTypeV5;
	//public static long busTrainingFlag = 0;
	//public static long busCurrencyType = 0;
	//public static long busFULFlag= 1;
	public static long busRHD4Bit4 = 0;	
	//public static long busDisablePPTOvr = 0;
	//public static long busDisableSVOvr =0;
	//public static long busNTMCardUpdated = 1;
	//public static long busOverallTxnlength = 97;
	//public static long busCurrentTxnLength = 33;
	//public static long TransactionDate =0;
	//public static long TransactionTime=1;
	//public static long NTMno = 90923421;
	//public static long busNTMCheckDigits = 91;
	//public static long BusCSN  = 36067029512892676L;
	//public static long busNTMSequenceNo = 657;
	//public static long BusRTDID = 1470351;
	//public static long busRTDSequenceNo = 21;
	//public static long busTransactionTimeSecs  = 91;
	//public static long busTransactionTimePerf  = 530;
	public static long busReaderTimeOffLine  = 0;
	//public static long busPhysicalCardType  =  3;
	//public static long busFULCurrentASN  = 88;
	//public static long busFULASNStatuses  = 20;
	public static String busRouteID;
	public static long busBoardingFareStageNo;
	public static long busStopIDStatus;
	public static String busStopID;
	public static String balance;
	public static long busDirectionOfTravel;
	public static long busTripType;
	public static long busTripNumber;
	public static long busRHD35;

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

	//EXIT Transaction Body
	public static long fullExitAdjustmentDeducted;
	public static long exitAdjustmentDeducted;
	public static long PPT1ValidityPageCRC;
	public static long PPT2ValidityPageCRC;
	public static long PPT3ValidityPageCRC;
	public static long timeOfFirstEntry;
	public static long spareByte;
	public static long zonalValidity;
	public static long fullFare;
	public static long discountedFare;
	public static long pptPassengerType;
	public static long twoBitFlag;
	public static long sameStationExitFlag;

	//TKT Transaction Body
	public static long TKTRequestSeqNo;
	public static long TKTPPTSlotNo;
	public static long TKTPPTValidityPageCRC;
	public static long TKTPassengerType;
	public static long TKTDiscountApplied;
	public static long TKTPaymentMethod;
	public static long TKTProductCode;
	public static long TKTProductID;
	public static long TKTRTVCalendarMonths;
	public static long TKTRTVDays;
	public static long TKTRTVPricingStructure;
	public static long TKTChargeBasis ;
	public static long TKTRTVSelector;
	public static long TKTRTVReportingSegmentation;
	public static long TKTPassengerTypeAge;
	public static long TKTTicketClass;
	public static long TKTZonalValidity;
	public static long TKTRoute;
	public static long TKTDestination;
	public static long TKTTravelcardZones;
	public static long TKTMediumType;
	public static long TKTTimeRestriction;
	public static long TKTDiscount;
	public static long TKTStartDate;
	public static long TKTExtraDays;
	public static long TKTspareBit;

	//TRAM Transaction Body
	public static String routeID;

	//Void Tram Transaction Body
	public static long TramStation;
	public static long EntryAdjustmentRestored;
	public static long ExitChargeAppliedFlag;
	public static long EntryAdjustmentBeforeDiscount;

	//Void Entry Body
	public static long FullEntryAdjustment;
	public static long stationOfFirstEntry;




	//BUS Transaction Body
	public static long WAYCONClass = 768;//570;//669;
	public static long AlightingFareStage = 26;//570;//669;
	//public static String routeIDString = "TRAM";

	//IVAL Transaction Body
	public static long stationOfFirstEntryNLC;
	public static long timeOfFirstEntryIVAL;
	public static long R65_3_2BitFlag;
	public static long CrossDayJourneyFlag;
	public static long RelevantRoutingNodeFlag;
	public static long ZonalIndicator;

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
	public static String finalBitBinaryData = "";

	public static long finalDecimalvalue=0;
	public static FileOutputStream output= null;
	public static String finalBinaryStr="";

	//Database Connection Initilizations
	private static Connection connection = null;
	public  static ResultSet  resultSet = null;
	public static ResultSetMetaData rsmd = null;
	public static DataBaseUtil util;
	public static int fileCounter = 0;
	public static  String transactionFilePath = null;
	//	public static String entryStationName = "";
	public static String entryStationName = null;
	public static String finalBalance= null;
	public HashMap<String, String> hmap = new HashMap<String, String>();
	public static int count = 0;
	public static String ivalTxnType = null;
	public static long entryTime = 0;
	public static long firstEntryTime = 0;
	public static boolean preserveEntryTime = false;
	public static boolean autoLoadTxn = false;
	public  void hexaToInt(String hex) throws IOException{
		long a = Long.parseLong(hex, 16);
		createbinary(a,"binary",1,0);
	}

/**
 * This method is used to append zeros and return the value in form of a string when supplied with desired parameters.	
 * @param zeroVal
 * @param lenDifference
 * @return
 */
	public  String appendZero(int zeroVal, int lenDifference){
		String appendZeroVal = "";
		while(!(appendZeroVal.length()==lenDifference)){
			appendZeroVal=0+appendZeroVal;
			//return appendZeroVal;
		}
		return appendZeroVal;


	}
/**
 * This method is used to convert a long datatype and return the value in form of a string when supplied with desired parameters.	
 * @param number
 * @param groupSize
 * @return
 */
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

/**
 * This method is used to create a binary value in order to create a transaction when supplied with desired parameters.	
 * @param decValue
 * @param bytes
 * @param bitPosition
 * @throws NumberFormatException
 * @throws IOException
 */
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
	/*public void createBitbinary(long decValue,String dataType, int bitPosition) throws IOException{

		System.out.println("Long Binary is "+Long.toBinaryString(decValue));
		String bindaryStringdata =longToString(decValue,8).toString();
		String[] binaryArray = bindaryStringdata.split(" ");

		for(String binaryStr:binaryArray){
			finalBinaryStr=finalBinaryStr+binaryStr;
		}
		System.out.println("finalBinaryStr is "+finalBinaryStr);
		//System.out.println("binary data is "+bindaryStringdata);
		String initialbBinaryData = finalBinaryStr.substring(finalBinaryStr.length()-bitPosition);
		//finalBitBinaryData = initialbBinaryData+finalBitBinaryData;
		finalBitBinaryData = initialbBinaryData+finalBitBinaryData;
		if(finalBitBinaryData.length()>=8){
			String initialbBinaryData2 = finalBitBinaryData.substring(finalBitBinaryData.length()-8);
			System.out.println("initialbBinaryData2 is "+initialbBinaryData2);
			if (initialbBinaryData2.length()==8) {
				System.out.println("reached 1 bytes ");
				os = new DataOutputStream(new FileOutputStream(System.getProperty("user.dir")+"\\outputBinary.dat"));
				finalDecimalvalue = Long.parseLong(finalBitBinaryData,2);
				os.writeLong(finalDecimalvalue);
				createByte(1);
				//finalBinaryData="";
			}
			finalBitBinaryData = finalBitBinaryData.substring(0, finalBitBinaryData.length() - 8);
			if (finalBitBinaryData.length()>=8) {
				count = count +1;
				System.out.println("reached byte "+count);
				os = new DataOutputStream(new FileOutputStream(System.getProperty("user.dir")+"\\outputBinary.dat"));
				finalDecimalvalue = Long.parseLong(finalBitBinaryData,2);
				os.writeLong(finalDecimalvalue);
				createByte(1);
				//finalBinaryData="";
				finalBitBinaryData = finalBitBinaryData.substring(0, finalBitBinaryData.length() - 8);
			}
			System.out.println("finalBinaryData after is "+finalBitBinaryData);
			//finalBitBinaryData="";
		}
		else{

		}
		finalBinaryStr="";
	}*/

	/**
	 * This method is used to create a bit binary values in order to create a transaction when supplied with desired parameters.	
	 * @param decValue
	 * @param dataType
	 * @param bitPosition
	 * @throws IOException
	 */
	
	public void createBitbinary(long decValue,String dataType, int bitPosition) throws IOException{

		System.out.println("Long Binary is "+Long.toBinaryString(decValue));
		String bindaryStringdata =longToString(decValue,8).toString();
		String[] binaryArray = bindaryStringdata.split(" ");

		for(String binaryStr:binaryArray){
			finalBinaryStr=finalBinaryStr+binaryStr;
		}
		System.out.println("finalBinaryStr is "+finalBinaryStr);
		//System.out.println("binary data is "+bindaryStringdata);
		String initialbBinaryData = finalBinaryStr.substring(finalBinaryStr.length()-bitPosition);
		//finalBitBinaryData = finalBitBinaryData+initialbBinaryData;
		finalBitBinaryData = initialbBinaryData+finalBitBinaryData;
		System.out.println("finalBitBinaryData is "+finalBitBinaryData);
		if(finalBitBinaryData.length()>=8){
			String initialbBinaryData2 = finalBitBinaryData.substring(finalBitBinaryData.length()-8);
			System.out.println("initialbBinaryData2 is "+initialbBinaryData2);
			if (initialbBinaryData2.length()==8) {
				count = count +1;
				System.out.println("reached byte "+count);
				os = new DataOutputStream(new FileOutputStream(System.getProperty("user.dir")+"\\outputBinary.dat"));
				finalDecimalvalue = Long.parseLong(initialbBinaryData2,2);
				os.writeLong(finalDecimalvalue);
				createByte(1);
				//finalBinaryData="";
			}
			finalBitBinaryData = finalBitBinaryData.substring(0, finalBitBinaryData.length() - 8);
			if (finalBitBinaryData.length()>=8) {
				String initialbBinaryData3 = finalBitBinaryData.substring(finalBitBinaryData.length()-8);
				if (initialbBinaryData3.length()==8) {
					count = count +1;
					System.out.println("reached byte "+count);
					os = new DataOutputStream(new FileOutputStream(System.getProperty("user.dir")+"\\outputBinary.dat"));
					finalDecimalvalue = Long.parseLong(initialbBinaryData3,2);
					os.writeLong(finalDecimalvalue);
					createByte(1);
					//finalBinaryData="";
					finalBitBinaryData = finalBitBinaryData.substring(0, finalBitBinaryData.length() - 8);
				}

			}
			System.out.println("finalBinaryData after is "+finalBitBinaryData);
			//finalBitBinaryData="";
		}
		else{

		}
		finalBinaryStr="";
	}
	
	/**
	 * This method is used to create a binary value in order to create a transaction when supplied with desired parameters.	
	 * @param decValue
	 * @param dataType
	 * @param bytes
	 * @param bitPosition
	 * @throws IOException
	 */
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

	/**
	 * This method is used to create a byte value in order to create a transaction when supplied with bytes as parameter.	
	 * @param bytes
	 * @throws IOException
	 */
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
			/*if (transactionMode.equalsIgnoreCase("PAYG")) {
				output = new FileOutputStream(System.getProperty("user.dir")+"\\tra_"+getCurrentdayKey()+"_0001.dat", true);
				output.write(bFile3);
			}
			else{
				output = new FileOutputStream(System.getProperty("user.dir")+"\\tra_"+getCurrentdayKey()+"_0002.dat", true);
				output.write(bFile3);
			}*/
			output.write(bFile3);

		} finally {

		}

	}

	/**
	 * This method is used to read bytes from the file present in the supplied file path and returns the data as byte array.	
	 * @param filePath
	 * @return
	 */
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
	
	/**
	 * This method is used to create AsciiBinary values when supplied with desired parameters. 
	 * @param asciData
	 * @param bytes
	 * @throws IOException
	 */
	public void createAsciiBinary(String asciData, int bytes) throws IOException{
		int decimalASCIcode = 0;
		char[] charArray = asciData.toCharArray();
		for (char chr: charArray){
			decimalASCIcode = getDecimalASCICode(chr);
			if (decimalASCIcode!=0) {
				createbinary(decimalASCIcode, "Binary", 1, 0);
			}
		}

	}
	
	/**
	 * This method is used to get decimalAscii values and return the decimalAscii value when supplied with asciiValue parameter. 
	 * @param asciValue
	 * @return
	 */
	public int getDecimalASCICode(char asciValue){
		int decimalASCIValue=0;
		try {
			decimalASCIValue = (int) asciValue;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return decimalASCIValue;

	}
	
	/**
	 * This method is used to get the current day key value from the database and return the value as a int.
	 * @return
	 */
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
			util.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dayKey;

	}

	/**
	 * This method is used to get the day key value from the database when a date is passed as a  parameter and return the value as a int.
	 * @param date
	 * @return
	 */
	public int getDayKey(String date){
		
		int dayKey = 0;
		try {
			util = DataBaseUtil.getDatabaseConnection("ORACLE");
			resultSet = util.retrieveData("select to_date('"+date+"', 'dd/mm/yyyy') - to_date('01011980','ddmmyyyy') from dual");
			while(resultSet.next())
			{
				dayKey = resultSet.getInt(1);
				break;
			}
			util.closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return dayKey;

	}


	/**
	 * This method is used to get the current UTC time and return the value as a Long.
	 * @return
	 */
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

	/**
	 * This method is used to get the value present in the column from the database and return the value as a string when supplied with desired parameters.
	 * @param Database
	 * @param DatabaseQuery
	 * @param ColumnName
	 * @return
	 */
	public static String getDBColumnValue(String Database, String DatabaseQuery, String ColumnName){
		String ColumnValue = null;
		try {
			util = DataBaseUtil.getDatabaseConnection(Database);
			//resultSet = util.retrieveData("select * from RDD_stations where stationname like '%Elephant & Castle%'");
			/*resultSet = util.retrieveData(DatabaseQuery);
			//System.out.println("Value of column is " + rs.getInt(1));
			while(resultSet.next())
			{
				ColumnValue= resultSet.getString(ColumnName);
				break;

			}*/
			do{
				resultSet = util.retrieveData(DatabaseQuery);
			}while(!(resultSet.next()));
			ColumnValue= resultSet.getString(ColumnName);
			util.closeConnection();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ColumnValue;

	}

	/**
	 * This method is used to get the value present in the column from the database and return the value as a string when supplied with desired parameters.
	 * @param Database
	 * @param DatabaseQuery
	 * @param ColumnName
	 * @param timeOutValue
	 * @return
	 */
	public static String getDBColumnValue(String Database, String DatabaseQuery, String ColumnName, int timeOutValue){
		String ColumnValue = null;
		try {
			util = DataBaseUtil.getDatabaseConnection(Database);
			//resultSet = util.retrieveData("select * from RDD_stations where stationname like '%Elephant & Castle%'");
			//resultSet = util.retrieveData(DatabaseQuery);
			//System.out.println("Value of column is " + rs.getInt(1));
			resultSet = util.retrieveData(DatabaseQuery);
			for(int i=1 ; i <timeOutValue; i++){

				if (resultSet.next()==false) {
					resultSet = util.retrieveData(DatabaseQuery);
					System.out.println("***** Loading Data to DataBase ******");
					Thread.sleep(1000);
					continue;
				}
				else{

					ColumnValue= resultSet.getString(ColumnName);
					if (ColumnValue!=null) {
						System.out.println("ColumnValue is ***** "+ColumnValue);
						break;
					}
					else{
						continue;
					}

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			util.closeConnection();
		}
		return ColumnValue;

	}

	/**
	 * This method is used to generate the file header when supplied with desired parameters.
	 * @param data
	 * @param action
	 * @param transactionType
	 */
	public void generateFileHeader(Hashtable<String, String> data,AppiumActions action, String transactionType){

		try {

			creationDate = getCurrentdayKey();

			if (transactionType.equalsIgnoreCase("Exit")) {
				if (autoLoadTxn==false) {

					creationTime = getCurrentUTCTime();
				}

			}
			else{
				if (creationTime==0) {
					//creationTime = getCurrentUTCTime()-30;
					//entryTime = creationTime;
					if (autoLoadTxn==false) {

						creationTime = getCurrentUTCTime();
					}


					entryTime = creationTime;

					if (preserveEntryTime==true) {
						firstEntryTime = creationTime;
					}
				}
			}
			createbinary(creationDate,"Binary",2,0);
			createbinary(creationTime,"Binary",2,0);

			action.successReport("Verify the File Header is Created", "File Header is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			action.failureReport("Verify the File Header is Created", "File Header is NOT Created");
			throw new RuntimeException(e);
		}


	}
	/**
	 * This method is used to set the status for preserved entry time.
	 * @param status
	 */
	public void preserveEntryTime(boolean status){
		preserveEntryTime = status;
	}

	/**
	 * This method is used to set the status for a auto load transaction.
	 * @param status
	 */
	public void setAutoLoadTransaction(boolean status){
		autoLoadTxn = status;
	}

	/**
	 * This method is used to generate a record header when supplied with desired parameters.
	 * @param data
	 * @param action
	 * @param transactionType
	 * @throws Throwable
	 */
	public void generateRecordHeader(Hashtable<String, String> data,AppiumActions action, String transactionType) throws Throwable {
		try {
			String TransactionHeaderFile = data.get("TransactionHeader");
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);

			RecordType = Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+transactionType+".RecordType"));
			RecordNumber = Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.RecordNumber"));
			//RecordNumber = recordHeaderCount;
			RecordLength = Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+transactionType+".RecordLength"));

			createbinary(RecordType,"Binary",4,0);
			createbinary(RecordNumber,"Binary",4,0);
			createbinary(RecordLength,"Binary",4,0);
			action.successReport("Verify the Record Header is Created", "Record Header is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			action.failureReport("Verify the Record Header is Created", "Record Header is NOT Created");
			throw new RuntimeException(e);
		}

	}

	/**
	 * This method is used to generate a record header for a auto completed journey when supplied with desired parameters.
	 * @param data
	 * @param action
	 * @param transactionType
	 * @throws Throwable
	 */
	public void generateRecordHeaderForAutoCompletedJourney(Hashtable<String, String> data,AppiumActions action, String transactionType) throws Throwable {
		try {
			String TransactionHeaderFile = data.get("TransactionHeader");
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);

			RecordType = Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+transactionType+".RecordType"));
			RecordNumber = Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.RecordNumber"));
			//RecordNumber = recordHeaderCount;
			RecordLength = 160;

			createbinary(RecordType,"Binary",4,0);
			createbinary(RecordNumber,"Binary",4,0);
			createbinary(RecordLength,"Binary",4,0);
			action.successReport("Verify the Record Header is Created", "Record Header is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			action.failureReport("Verify the Record Header is Created", "Record Header is NOT Created");
			throw new RuntimeException(e);
		}

	}
	
	/**
	 * This method is used to generate a record header for a Ticket when supplied with desired parameters.
	 * @param data
	 * @param action
	 * @param transactionType
	 * @throws Throwable
	 */
	public void generateTKTRecordHeader(Hashtable<String, String> data,AppiumActions action, String transactionType) throws Throwable {
		try {
			String TransactionHeaderFile = data.get("TransactionHeader");
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);

			RecordType = Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+transactionType+".RecordType"));
			RecordNumber = Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.RecordNumber"));
			//RecordNumber = recordHeaderCount;
			RecordLength = 154;

			createbinary(RecordType,"Binary",4,0);
			createbinary(RecordNumber,"Binary",4,0);
			createbinary(RecordLength,"Binary",4,0);
			action.successReport("Verify the Record Header is Created", "Record Header is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			action.failureReport("Verify the Record Header is Created", "Record Header is NOT Created");
			throw new RuntimeException(e);
		}

	}
	
	/**
	 * This method is used to generate a transaction header when supplied with desired parameters.
	 * @param data
	 * @param action
	 * @param TransactionFile
	 * @param transactionType
	 * @throws Throwable
	 */
	public void generateTH(Hashtable<String, String> data,AppiumActions action,String TransactionFile, String transactionType) throws Throwable {
		try {
			String TransactionHeaderFile = data.get("TransactionHeader");
			String TransactionData =FileUtil.readFile(TransactionHeaderFile);

			//String TransactionFile = data.get("TransactionFile");
			String TransactionDataType =FileUtil.readFile(TransactionFile);

			Object TransactionObject = JsonUtil.getJsonObject(TransactionDataType, "$."+transactionType+"");

			String StationName =JsonUtil.getJsonObjectElement(TransactionObject, ".StationName");

			//String StationName =JsonUtil.getJsonElement(TransactionDataType, "$."+transactionType+".StationName");


			TransactionDate = creationDate;
			PrestigeStationNumber = Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$."+StationName+".PrestigeStationNumber"));
			TicketHall = Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.TicketHall"));
			DeviceLocation = Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.DeviceLocation"));
			/*DeviceType = Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$."+StationName+".DeviceType"));
			Generation = Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.Generation"));*/
			DeviceType = 4;
			Generation = 12;
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
			/*createBitbinary(Generation, "Bits", 4);
			createBitbinary(DeviceType, "Bits", 4);*/
			createbinary(PlinthNumber,"Binary",1,0);
			createbinary(SpecialResetCount,"Binary",1,0);
			createbinary(TransactionNumber,"Binary",2,0);
			createbinary(OperatorIdentity,"BCD",3,0);
			createbinary(TransactionTime,"Binary",2,0);
			createbinary(TransactionType,"Binary",1,0);

			action.successReport("Verify the Transaction Header is Created", "Transaction Header is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			action.failureReport("Verify the Transaction Header is Created", "Transaction Header is NOT Created");
			throw new RuntimeException(e);
		}

	}

	/**
	 * This method is used to generate a copy of a transaction header when supplied with desired parameters.
	 * @param data
	 * @param action
	 * @param TransactionFile
	 * @param transactionType
	 * @param transactionNo
	 * @throws Throwable
	 */
	public void generateTHCopy(Hashtable<String, String> data,AppiumActions action,String TransactionFile, String transactionType,int transactionNo) throws Throwable {
		try {
			String TransactionHeaderFile = data.get("TransactionHeader");
			String TransactionData =FileUtil.readFile(TransactionHeaderFile);

			//String TransactionFile = data.get("TransactionFile");
			String TransactionDataType =FileUtil.readFile(TransactionFile);

			Object TransactionObject = JsonUtil.getJsonObject(TransactionDataType, "$."+transactionType+"");

			String StationName =JsonUtil.getJsonObjectElement(TransactionObject, ".StationName");

			//String StationName =JsonUtil.getJsonElement(TransactionDataType, "$."+transactionType+".StationName");


			TransactionDate = creationDate;
			PrestigeStationNumber = Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$."+StationName+".PrestigeStationNumber"));
			TicketHall = Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.TicketHall"));
			DeviceLocation = Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.DeviceLocation"));
			/*DeviceType = Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$."+StationName+".DeviceType"));
			Generation = Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.Generation"));*/
			DeviceType = 4;
			Generation = 12;
			PlinthNumber = Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$."+StationName+".PlinthNumber"));
			SpecialResetCount = Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.SpecialResetCount"));
			TransactionNumber = transactionNo;
			OperatorIdentity = Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.OperatorIdentity"));
			TransactionTime = creationTime;
			TransactionType= Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.TransactionType"));

			createbinary(TransactionDate,"Binary",2,0);
			createbinary(PrestigeStationNumber,"Binary",2,0);
			createbinary(TicketHall,"Binary",1,0);
			createbinary(DeviceLocation,"Binary",1,0);
			createbinary(DeviceType,"BitField",1,4);
			createbinary(Generation,"BitField",1,4);
			/*createBitbinary(Generation, "Bits", 4);
			createBitbinary(DeviceType, "Bits", 4);*/
			createbinary(PlinthNumber,"Binary",1,0);
			createbinary(SpecialResetCount,"Binary",1,0);
			createbinary(TransactionNumber,"Binary",2,0);
			createbinary(OperatorIdentity,"BCD",3,0);
			createbinary(TransactionTime,"Binary",2,0);
			createbinary(TransactionType,"Binary",1,0);

			action.successReport("Verify the Transaction Header is Created", "Transaction Header is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			action.failureReport("Verify the Transaction Header is Created", "Transaction Header is NOT Created");
			throw new RuntimeException(e);
		}

	}
	
	/**
	 * This method is used to generate a Entry transaction header when supplied with desired parameters.
	 * @param data
	 * @param action
	 * @throws Throwable
	 */
	public void generateETH(Hashtable<String, String> data,AppiumActions action) throws Throwable{
		try {
			String TransactionHeaderFile = data.get("TransactionHeader");
			String TransactionData =FileUtil.readFile(TransactionHeaderFile);
			TransactionSubType= Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.TransactionSubType"));
			LinkingSequenceNo= Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.LinkingSequenceNo"));
			LinkingStatus=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.LinkingStatus"));

			createbinary(TransactionSubType,"Binary",1,0);
			createbinary(LinkingSequenceNo,"Binary",1,0);
			createbinary(LinkingStatus,"Binary",1,0);
			action.successReport("Verify the ETH Header is Created", "ETH Header is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			action.failureReport("Verify the ETH Header is Created", "ETH Header is NOT Created");
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * This method is used to generate a Bus transaction header when supplied with desired parameters.
	 * @param data
	 * @param action
	 * @param TransactionFile
	 * @param transactionType
	 * @throws Throwable
	 */
	public void generateBusTH(Hashtable<String, String> data,AppiumActions action,String TransactionFile, String transactionType) throws Throwable {
		try {
			String TransactionHeaderFile = data.get("TransactionHeader");
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);

			//String TransactionFile = data.get("TransactionFile");
			String TransactionDataType =FileUtil.readFile(TransactionFile);

			Object TransactionObject = JsonUtil.getJsonObject(TransactionDataType, "$."+transactionType+"");

			String StationName =JsonUtil.getJsonObjectElement(TransactionObject, ".StationName");

			BusTransactionType = Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.BusTransactionType"));
			BusReaderTransactionType = Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.BusReaderTransactionType"));
			BusTransactionNumber = Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.BusTransactionNumber"));
			BusTransactionLength = Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.BusTransactionLength"));
			TransactionDate = creationDate;
			TransactionTime = creationTime;
			ReaderID = Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+StationName+".ReaderID"));
			ControllerID = Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+StationName+".ControllerID"));
			BusReaderPosition = Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+StationName+".BusReaderPosition"));
			ControllerTrayID = Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+StationName+".ControllerTrayID"));
			ReaderContainerID = Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+StationName+".ReaderContainerID"));
			GarageNumber = Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+StationName+".GarageNumber"));
			FleetNumber = JsonUtil.getJsonElement(TransactionHeaderData, "$."+StationName+".FleetNumber");
			RegistrationNumber = JsonUtil.getJsonElement(TransactionHeaderData, "$."+StationName+".RegistrationNumber");
			IncidentNumber = Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+StationName+".IncidentNumber"));



			createbinary(BusTransactionType,"Binary",1,0);
			createbinary(BusReaderTransactionType,"Binary",1,0);
			createbinary(BusTransactionNumber,"Binary",2,0);
			createbinary(BusTransactionLength,"Binary",2,0);
			createbinary(TransactionDate,"Binary",2,0);
			createbinary(TransactionTime,"Binary",2,0);
			createbinary(ReaderID,"Binary",3,0);
			createbinary(ControllerID,"Binary",3,0);
			createbinary(BusReaderPosition,"Binary",1,0);
			createbinary(ControllerTrayID,"Binary",3,0);
			createbinary(ReaderContainerID,"Binary",3,0);
			createbinary(GarageNumber,"Binary",2,0);
			createAsciiBinary(FleetNumber,8);
			createAsciiBinary(RegistrationNumber,7);
			createbinary(IncidentNumber,"Binary",2,0);

			action.successReport("Verify the BUS Transaction Header is Created", "BUS Transaction Header is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			action.failureReport("Verify the BUS Transaction Header is Created", "BUS Transaction Header is NOT Created");
			throw new RuntimeException(e);
		}

	}
	
	/**
	 * This method is used to generate a RTD bus transaction header when supplied with desired parameters.
	 * @param data
	 * @param TransactionFile
	 * @param transactionType
	 * @param action
	 * @throws Throwable
	 */
	public void generateRTDBusTransactionHeader(Hashtable<String, String> data,String TransactionFile,String transactionType,AppiumActions action) throws Throwable{
		try {
			String TransactionHeaderFile = data.get("TransactionHeader");			
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);

			//String TransactionFile = data.get("TransactionFile");
			String TransactionDataType =FileUtil.readFile(TransactionFile);

			String cardNumber = data.get("PrestigeCardNumber");

			Object TransactionObject = JsonUtil.getJsonObject(TransactionDataType, "$."+transactionType+"");

			String stationName =JsonUtil.getJsonObjectElement(TransactionObject, ".StationName");

			busRHD0Bit7= Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.busRHD0Bit7"));
			txnTypeBus= Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.txnTypeBus"));
			txnTypeV5= Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+transactionType+".txnTypeV5"));
			TestTrainingFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.TestTrainingFlag"));
			CurrencyType=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.CurrencyType"));
			fulFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.fulFlag"));
			busRHD4Bit4=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.busRHD4Bit4"));
			disablePPToverLoading=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.disablePPToverLoading"));
			disableSVOverriding=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.disableSVOverriding"));
			ntmCardUpdated=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.ntmCardUpdated"));
			overallTxnlength=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+transactionType+".overallTxnlength"));
			currentTxnLength=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+transactionType+".currentTxnLength"));
			txnDate=creationDate;
			if (transactionType.equalsIgnoreCase("Exit")) {
				txnTime=getCurrentUTCTime();
			}
			else{
				txnTime=creationTime;
			}

			NTMno=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+cardNumber+".NTMno"));
			NTMNoCheckDigits=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+cardNumber+".NTMNoCheckDigits"));
			CSN=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+cardNumber+".CSN"));
			if (transactionType.equalsIgnoreCase("PAYG")) {
				NTMTxnSeqNo= Long.parseLong(getDBColumnValue("Oracle","SELECT MAX(CARDSEQNO) FROM DD_CardTransaction WHERE prestigeid ='"+cardNumber+"' ORDER BY createddate DESC", "MAX(CARDSEQNO)"));
				NTMTxnSeqNo=NTMTxnSeqNo+1;
			}
			else{
				if (NTMTxnSeqNo==0) {
					NTMTxnSeqNo= Long.parseLong(getDBColumnValue("Oracle","SELECT MAX(CARDSEQNO) FROM DD_CardTransaction WHERE prestigeid ='"+cardNumber+"' ORDER BY createddate DESC", "MAX(CARDSEQNO)"));
				}
				NTMTxnSeqNo=NTMTxnSeqNo+1;
			}

			RTDID=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".RTDID"));
			String RTDIDString = JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".RTDID");
			if (transactionType.equalsIgnoreCase("PAYG")) {
				String RTDSeqNO = getDBColumnValue("Oracle","select MAX(RTDSEQUENCENO) from tdms_rtdtransactionheader WHERE RTDID = "+RTDIDString+" and DAYKEY = "+getCurrentdayKey(),"MAX(RTDSEQUENCENO)");
				if (RTDSeqNO==null) {
					RTDTxnSeqNo=1;
				}else{
					RTDTxnSeqNo=Long.parseLong(getDBColumnValue("Oracle","select MAX(RTDSEQUENCENO) from tdms_rtdtransactionheader WHERE RTDID = "+RTDIDString+" and DAYKEY = "+getCurrentdayKey(),"MAX(RTDSEQUENCENO)"))+1;
				}
			}
			else{
				if (RTDTxnSeqNo==0) {
					RTDTxnSeqNo=Long.parseLong(getDBColumnValue("Oracle","select MAX(RTDSEQUENCENO) from tdms_rtdtransactionheader WHERE RTDID = "+RTDIDString+" and DAYKEY = "+getCurrentdayKey(),"MAX(RTDSEQUENCENO)"));
				}
				RTDTxnSeqNo=RTDTxnSeqNo+1;
			}
			TxnTimeSecs=FileUtil.generateRandomNumber(50,30);
			TxnPerfTime=FileUtil.generateRandomNumber(900,300);
			busReaderTimeOffLine =Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.BusReaderTimeOffLine"));
			PhysicalCardType=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.PhysicalCardType"));
			FULCurrentASN=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.FULCurrentASN"));
			FULASNStatuses=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.FULASNStatuses"));
			busRouteID=JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".busRouteID");
			busBoardingFareStageNo=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData,  "$."+stationName+".busBoardingFareStageNo"));
			busStopIDStatus=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.busStopIDStatus"));
			busStopID=JsonUtil.getJsonElement(TransactionHeaderData, "$.busStopID");
			busDirectionOfTravel=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.busDirectionOfTravel"));
			busTripType=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.busTripType"));
			busTripNumber=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.busTripNumber"));
			busRHD35=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$.busRHD35"));

			createbinary(busRHD0Bit7,"BitField",1,1);
			createbinary(txnTypeBus,"BitField",1,7);
			createbinary(txnTypeV5,"Binary",1,0);
			createbinary(TestTrainingFlag,"BitField",1,1);
			createbinary(CurrencyType,"BitField",1,1);
			createbinary(fulFlag,"BitField",1,1);
			createbinary(busRHD4Bit4,"BitField",1,2);
			createbinary(disablePPToverLoading,"BitField",1,1);
			createbinary(disableSVOverriding,"BitField",1,1);
			createbinary(ntmCardUpdated,"BitField",1,1);
			createbinary(overallTxnlength,"Binary",1,1);
			createbinary(currentTxnLength,"Binary",1,1);
			createbinary(txnDate,"Binary",2,0);
			createbinary(txnTime,"Binary",2,0);
			createbinary(NTMno,"Binary",4,0);
			createbinary(NTMNoCheckDigits,"Binary",1,0);
			createbinary(CSN,"Binary",7,0);
			createbinary(NTMTxnSeqNo,"Binary",2,0);
			createbinary(RTDID,"Binary",3,0);
			createbinary(RTDTxnSeqNo,"Binary",2,0);
			createbinary(TxnTimeSecs,"Binary",1,0);
			createbinary(TxnPerfTime,"Binary",2,0);
			createbinary(busReaderTimeOffLine,"Binary",2,0);
			createbinary(PhysicalCardType,"Binary",1,0);
			createbinary(FULCurrentASN,"Binary",1,0);
			createbinary(FULASNStatuses,"Binary",1,0);
			createAsciiBinary(busRouteID,4);
			createbinary(busBoardingFareStageNo,"Binary",2,0);
			createbinary(busStopIDStatus,"Binary",1,0);
			createAsciiBinary(busStopID,12);
			createbinary(busDirectionOfTravel,"Binary",1,0);
			createbinary(busTripType,"Binary",1,0);
			createbinary(busTripNumber,"Binary",2,0);
			createbinary(busRHD35,"Binary",1,0);


			action.successReport("Verify the RTD Transaction Header is Created", "RTD Transaction Header is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			action.failureReport("Verify the RTD TransactionHeader is Created", "RTD Transaction Header is NOT Created");
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * This method is used to generate a RTD transaction header when supplied with desired parameters.
	 * @param data
	 * @param TransactionFile
	 * @param transactionType
	 * @param action
	 * @throws Throwable
	 */
	public void generateRTDTransactionHeader(Hashtable<String, String> data,String TransactionFile,String transactionType,AppiumActions action) throws Throwable{
		try {
			String TransactionHeaderFile = data.get("TransactionHeader");			
			String TransactionData =FileUtil.readFile(TransactionHeaderFile);

			//String TransactionFile = data.get("TransactionFile");
			String TransactionDataType =FileUtil.readFile(TransactionFile);

			String cardNumber = data.get("PrestigeCardNumber");

			Object TransactionObject = JsonUtil.getJsonObject(TransactionDataType, "$."+transactionType+"");

			String stationName =JsonUtil.getJsonObjectElement(TransactionObject, ".StationName");

			//String stationName =JsonUtil.getJsonElement(TransactionDataType, "$."+transactionType+".StationName");

			txnType= Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.txnType"));

			txnTypeV6= Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$."+transactionType+".txnTypeV6"));




			TestTrainingFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.TestTrainingFlag"));
			CurrencyType=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.CurrencyType"));
			fulFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.fulFlag"));
			padding=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.padding"));
			disablePPToverLoading=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.disablePPToverLoading"));
			disableSVOverriding=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.disableSVOverriding"));
			ntmCardUpdated=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$.ntmCardUpdated"));
			overallTxnlength=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$."+transactionType+".overallTxnlength"));
			currentTxnLength=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$."+transactionType+".currentTxnLength"));
			txnDate=creationDate;
			txnTime=creationTime;
			if (entryTime!=0) {
				timeOfFirstEntry=entryTime;
			}
			if (firstEntryTime!=0) {
				timeOfFirstEntry=firstEntryTime;
			}

			/*if (transactionType.equalsIgnoreCase("Exit")) {
				txnTime=getCurrentUTCTime();
			}
			else{
				txnTime=creationTime;
				//timeOfFirstEntry=txnTime;
				if (entryTime!=0) {
					timeOfFirstEntry=entryTime;
				}
				if (firstEntryTime!=0) {
					timeOfFirstEntry=firstEntryTime;
				}
			}*/

			NTMno=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$."+cardNumber+".NTMno"));
			NTMNoCheckDigits=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$."+cardNumber+".NTMNoCheckDigits"));
			CSN=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$."+cardNumber+".CSN"));
			/*if (transactionType.equalsIgnoreCase("PAYG")) {
				NTMTxnSeqNo= Long.parseLong(getDBColumnValue("Oracle","SELECT MAX(CARDSEQNO) FROM DD_CardTransaction WHERE prestigeid ='"+cardNumber+"' ORDER BY createddate DESC", "MAX(CARDSEQNO)"));
				NTMTxnSeqNo=NTMTxnSeqNo+1;
			}
			else{
				if (NTMTxnSeqNo==0) {
					NTMTxnSeqNo= Long.parseLong(getDBColumnValue("Oracle","SELECT MAX(CARDSEQNO) FROM DD_CardTransaction WHERE prestigeid ='"+cardNumber+"' ORDER BY createddate DESC", "MAX(CARDSEQNO)"));
				}
				NTMTxnSeqNo=NTMTxnSeqNo+1;
			}*/

			if (NTMTxnSeqNo==0) {
				NTMTxnSeqNo= Long.parseLong(getDBColumnValue("Oracle","SELECT MAX(CARDSEQNO) FROM DD_CardTransaction WHERE prestigeid ='"+cardNumber+"' ORDER BY createddate DESC", "MAX(CARDSEQNO)"));
			}
			NTMTxnSeqNo=NTMTxnSeqNo+1;

			RTDID=Long.parseLong(JsonUtil.getJsonElement(TransactionData, "$."+stationName+".RTDID"));
			String RTDIDString = JsonUtil.getJsonElement(TransactionData, "$."+stationName+".RTDID");
			if (transactionType.equalsIgnoreCase("PAYG")) {
				String RTDSeqNO = getDBColumnValue("Oracle","select MAX(RTDSEQUENCENO) from tdms_rtdtransactionheader WHERE RTDID = "+RTDIDString+" and DAYKEY = "+getCurrentdayKey(),"MAX(RTDSEQUENCENO)");
				if (RTDSeqNO==null) {
					RTDTxnSeqNo=1;
				}else{
					RTDTxnSeqNo=Long.parseLong(getDBColumnValue("Oracle","select MAX(RTDSEQUENCENO) from tdms_rtdtransactionheader WHERE RTDID = "+RTDIDString+" and DAYKEY = "+getCurrentdayKey(),"MAX(RTDSEQUENCENO)"))+1;
				}
			}
			else{
				String RTDSeqNO = getDBColumnValue("Oracle","select MAX(RTDSEQUENCENO) from tdms_rtdtransactionheader WHERE RTDID = "+RTDIDString+" and DAYKEY = "+getCurrentdayKey(),"MAX(RTDSEQUENCENO)");
				if (RTDSeqNO==null) {
					RTDTxnSeqNo=1;
				}
				if (RTDTxnSeqNo==0) {
					RTDTxnSeqNo=Long.parseLong(getDBColumnValue("Oracle","select MAX(RTDSEQUENCENO) from tdms_rtdtransactionheader WHERE RTDID = "+RTDIDString+" and DAYKEY = "+getCurrentdayKey(),"MAX(RTDSEQUENCENO)"));
				}
				RTDTxnSeqNo=RTDTxnSeqNo+1;
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


			action.successReport("Verify the RTD Transaction Header is Created", "RTD Transaction Header is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			action.failureReport("Verify the RTD TransactionHeader is Created", "RTD Transaction Header is NOT Created");
			throw new RuntimeException(e);
		}
	}

	/**
	 * This method is used to generate a Entry transaction body when supplied with desired parameters.
	 * @param data
	 * @param TransactionFile
	 * @param EntryStationName
	 * @param transactionType
	 * @param action
	 * @throws Throwable
	 */
	public void generateEntryTransactionBody(Hashtable<String, String> data,String TransactionFile,String EntryStationName, String transactionType,AppiumActions action) throws Throwable{
		try {
			String EntryTransactionFile = data.get("EntryTransaction");
			String TransactionEntryData =FileUtil.readFile(EntryTransactionFile);
			String TransactionHeaderFile = data.get("TransactionHeader");
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);

			//String TransactionFile = data.get("TransactionFile");
			String TransactionData =FileUtil.readFile(TransactionFile);
			Object TransactionObject = JsonUtil.getJsonObject(TransactionData, "$."+transactionType+"");

			//String stationName = JsonUtil.getJsonObjectElement(TransactionObject, ".StationName");
			String stationName = EntryStationName;
			String cardNumber = data.get("PrestigeCardNumber");

			resultCode=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.resultCode"));
			stationNLC=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".NLC"));
			/*if (!(data.get("TransactionType").equalsIgnoreCase("TKT"))) {
				fullEntryAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".fullEntryAdjustmentDeducted"));
				entryAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".entryAdjustmentDeducted"));
			}*/

			if (data.get("TransactionType").equalsIgnoreCase("TKT")&&data.get("TravelOutsideZoneRange")!=null&&data.get("TravelOutsideZoneRange").equalsIgnoreCase("true")) {
				fullEntryAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".fullEntryAdjustmentDeducted"));
				entryAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".entryAdjustmentDeducted"));
			}
			else if (!(data.get("TransactionType").equalsIgnoreCase("TKT"))&&data.get("OSIEntry")!=null && data.get("OSIEntry").equalsIgnoreCase("true")) {
				fullEntryAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".fullEntryAdjustmentDeducted"));
				entryAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".entryAdjustmentDeducted"));
			}
			else{
				fullEntryAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".fullEntryAdjustmentDeducted"));
				entryAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".entryAdjustmentDeducted"));
			}


			if (SVBalance==0) {
				SVBalance=Long.parseLong(getDBColumnValue("Oracle","SELECT * FROM DD_CardTransaction WHERE prestigeid = '"+cardNumber+"' ORDER BY createddate DESC", "PAYGBALANCE",20));
			}
			if (data.get("AutoCompletedJourney")!=null && data.get("AutoCompletedJourney").equalsIgnoreCase("true")) {
				ppt2UsedFlag=1;
				PPTValidityPageCRC=1;
			}

			if (data.get("OSIEntry")!=null && data.get("OSIEntry").equalsIgnoreCase("true")&& exitAdjustmentDeducted!=0) {
				osiEntryFlag=1;
				fullEntryAdjustmentDeducted = 32767;
				//fullEntryAdjustmentDeducted = 0;
				entryAdjustmentDeducted=-(exitAdjustmentDeducted);
			}
			/*if(entryAdjustmentDeducted==0){
				entryAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".entryAdjustmentDeducted"));
			}*/
			if (data.get("JourneyType")!=null && data.get("JourneyType").equalsIgnoreCase("RiverBus")) {
				oysterFareType=3;
			}
			else{
				oysterFareType=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.oysterFareType"));
			}


			//SVBalance=SVBalance-fullEntryAdjustmentDeducted;
			SVBalance=SVBalance-entryAdjustmentDeducted;
			autoContinutionFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.autoContinutionFlag"));
			autoCompletionFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.autoCompletionFlag"));
			//osiEntryFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.osiEntryFlag"));
			svInvlovedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.svInvlovedFlag"));
			ppt3UsedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.ppt3UsedFlag"));

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


			action.successReport("Verify the Entry Transaction Body is Created", "Entry Transaction Body is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			action.failureReport("Verify the Entry Transaction Body is Created", "Entry Transaction Body is NOT Created");
			throw new RuntimeException(e);
		}
	}

	/**
	 * This method is used to generate a Void Entry transaction body when supplied with desired parameters.
	 * @param data
	 * @param TransactionFile
	 * @param EntryStationName
	 * @param transactionType
	 * @param action
	 * @throws Throwable
	 */
	public void generateVoidEntryTransactionBody(Hashtable<String, String> data,String TransactionFile,String EntryStationName, String transactionType,AppiumActions action) throws Throwable{
		try {
			String voidTransactionFile = data.get("VoidEntryTransaction");
			String TransactionVoidEntryData =FileUtil.readFile(voidTransactionFile);
			String TransactionHeaderFile = data.get("TransactionHeader");
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);
			//String TransactionFile = data.get("TransactionFile");
			String TransactionData =FileUtil.readFile(TransactionFile);
			Object TransactionObject = JsonUtil.getJsonObject(TransactionData, "$."+transactionType+"");
			String stationName = JsonUtil.getJsonObjectElement(TransactionObject, ".StationName");
			String cardNumber = data.get("PrestigeCardNumber");




			EntryAdjustmentRestored = Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+EntryStationName+".entryAdjustmentDeducted"));
			if (SVBalance==0) {
				SVBalance=Long.parseLong(getDBColumnValue("Oracle","SELECT * FROM DD_CardTransaction WHERE prestigeid = '"+cardNumber+"' ORDER BY createddate DESC", "PAYGBALANCE",20));
			}
			SVBalance=SVBalance+EntryAdjustmentRestored;
			autoContinutionFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionVoidEntryData, "$.autoContinutionFlag"));
			autoCompletionFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionVoidEntryData, "$.autoCompletionFlag"));
			osiEntryFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionVoidEntryData, "$.osiEntryFlag"));
			svInvlovedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionVoidEntryData, "$.svInvlovedFlag"));
			ppt3UsedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionVoidEntryData, "$.ppt3UsedFlag"));
			ppt2UsedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionVoidEntryData, "$.ppt2UsedFlag"));
			ppt1UsedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionVoidEntryData, "$.ppt1UsedFlag"));
			tokenExit=Long.parseLong(JsonUtil.getJsonElement(TransactionVoidEntryData, "$.tokenExit"));
			sparebit14=Long.parseLong(JsonUtil.getJsonElement(TransactionVoidEntryData, "$.sparebit14"));
			previousValidationErrorFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionVoidEntryData, "$.previousValidationErrorFlag"));
			unStartedPPTExitFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionVoidEntryData, "$.unStartedPPTExitFlag"));
			unCompletedPPTEntryFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionVoidEntryData, "$.unCompletedPPTEntryFlag"));
			exitChargeAppliedFlag = Long.parseLong(JsonUtil.getJsonElement(TransactionVoidEntryData, "$.exitChargeAppliedFlag"));
			passbackAuthorised=Long.parseLong(JsonUtil.getJsonElement(TransactionVoidEntryData, "$.passbackAuthorised"));
			dailyCapping=Long.parseLong(JsonUtil.getJsonElement(TransactionVoidEntryData, "$.dailyCapping"));
			TransferDiscountApp=Long.parseLong(JsonUtil.getJsonElement(TransactionVoidEntryData, "$.TransferDiscountApp"));
			FullEntryAdjustment = Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+EntryStationName+".fullEntryAdjustmentDeducted"));
			stationOfFirstEntry=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+EntryStationName+".NLC"));


			createbinary(EntryAdjustmentRestored,"Binary",3,0);
			createbinary(SVBalance,"Binary",3,0);
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
			createbinary(FullEntryAdjustment,"Binary",3,0);
			createbinary(stationOfFirstEntry,"Binary",2,0);


			action.successReport("Verify the Entry Transaction Body is Created", "Entry Transaction Body is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			action.failureReport("Verify the Entry Transaction Body is Created", "Entry Transaction Body is NOT Created");
			throw new RuntimeException(e);
		}
	}

	/**
	 * This method is used to generate a Auto Completed Entry transaction body when supplied with desired parameters.
	 * @param data
	 * @param TransactionFile
	 * @param transactionType
	 * @param action
	 * @throws Throwable
	 */
	public void generateAutoCompletedEntryTransactionBody(Hashtable<String, String> data,String TransactionFile, String transactionType,AppiumActions action) throws Throwable{
		try {
			String EntryTransactionFile = data.get("EntryTransaction");
			String TransactionEntryData =FileUtil.readFile(EntryTransactionFile);
			String TransactionHeaderFile = data.get("TransactionHeader");
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);

			//String TransactionFile = data.get("TransactionFile");
			String TransactionData =FileUtil.readFile(TransactionFile);
			Object TransactionObject = JsonUtil.getJsonObject(TransactionData, "$."+transactionType+"");

			String stationName = JsonUtil.getJsonObjectElement(TransactionObject, ".StationName");
			String cardNumber = data.get("PrestigeCardNumber");

			resultCode=Long.parseLong(JsonUtil.getJsonElement(TransactionEntryData, "$.resultCode"));
			stationNLC=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".NLC"));
			/*if (!(data.get("TransactionType").equalsIgnoreCase("TKT"))) {
				fullEntryAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".fullEntryAdjustmentDeducted"));
				entryAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".entryAdjustmentDeducted"));
			}*/

			if (data.get("TransactionType").equalsIgnoreCase("TKT")&&data.get("TravelOutsideZoneRange")!=null&&data.get("TravelOutsideZoneRange").equalsIgnoreCase("true")) {
				fullEntryAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".fullEntryAdjustmentDeducted"));
				entryAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".entryAdjustmentDeducted"));
			}
			else if (!(data.get("TransactionType").equalsIgnoreCase("TKT"))) {
				fullEntryAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".fullEntryAdjustmentDeducted"));
				entryAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".entryAdjustmentDeducted"));
			}
			/*fullEntryAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".fullEntryAdjustmentDeducted"));
			entryAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".entryAdjustmentDeducted"));*/

			if (SVBalance==0) {
				SVBalance=Long.parseLong(getDBColumnValue("Oracle","SELECT * FROM DD_CardTransaction WHERE prestigeid = '"+cardNumber+"' ORDER BY createddate DESC", "PAYGBALANCE",20));
			}
			SVBalance=SVBalance-fullEntryAdjustmentDeducted;
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


			action.successReport("Verify the Entry Transaction Body is Created", "Entry Transaction Body is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			action.failureReport("Verify the Entry Transaction Body is Created", "Entry Transaction Body is NOT Created");
			throw new RuntimeException(e);
		}
	}


	/**
	 * This method is used to generate a Exit transaction body when supplied with desired parameters.
	 * @param data
	 * @param TransactionFile
	 * @param transactionType
	 * @param action
	 * @throws Throwable
	 */
	public void generateExitTransactionBody(Hashtable<String, String> data,String TransactionFile, String transactionType,AppiumActions action) throws Throwable{
		try {
			int l =3;
			String ExitTransactionFile = data.get("ExitTransaction");
			String TransactionExitData =FileUtil.readFile(ExitTransactionFile);
			String TransactionHeaderFile = data.get("TransactionHeader");
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);
			//String TransactionFile = data.get("TransactionFile");
			String TransactionData =FileUtil.readFile(TransactionFile);
			Object TransactionObject = JsonUtil.getJsonObject(TransactionData, "$."+transactionType+"");
			String stationName = JsonUtil.getJsonObjectElement(TransactionObject, ".StationName");
			String cardNumber = data.get("PrestigeCardNumber");

			resultCode=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.resultCode"));
			//stationNLC=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".NLC"));
			if (data.get("UnstartedJourney")!=null && data.get("UnstartedJourney").equalsIgnoreCase("true")) {
				stationNLC=65535;
				exitChargeAppliedFlag=1;
			}else{
				if(entryStationName!=null){
					stationNLC=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+entryStationName+".NLC"));	
				}

				exitChargeAppliedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.exitChargeAppliedFlag"));
			}

			/*if (!(data.get("TransactionType").equalsIgnoreCase("TKT"))) {
				fullExitAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+
+"."+stationName+".fullExitAdjustmentDeducted"));
				exitAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".exitAdjustmentDeducted"));
				fullFare=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".fullFare"));
				discountedFare=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".discountedFare"));
			}*/
			if (data.get("TransactionType").equalsIgnoreCase("TKT")&&data.get("TravelOutsideZoneRange")!=null&&data.get("TravelOutsideZoneRange").equalsIgnoreCase("true")) {
				fullExitAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".fullExitAdjustmentDeducted"));
				exitAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".exitAdjustmentDeducted"));
				fullFare=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".fullFare"));
				discountedFare=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".discountedFare"));
			}
			else if(!(data.get("TransactionType").equalsIgnoreCase("TKT"))&&data.get("JourneyType")!=null){
				fullExitAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+data.get("JourneyType")+"."+entryStationName+"."+stationName+".fullExitAdjustmentDeducted"));
				exitAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+data.get("JourneyType")+"."+entryStationName+"."+stationName+".exitAdjustmentDeducted"));
				fullFare=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+data.get("JourneyType")+"."+entryStationName+"."+stationName+".fullFare"));
				discountedFare=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+data.get("JourneyType")+"."+entryStationName+"."+stationName+".discountedFare"));
			}
			/*else if (!(data.get("TransactionType").equalsIgnoreCase("TKT"))){
				fullExitAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".fullExitAdjustmentDeducted"));
				exitAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".exitAdjustmentDeducted"));
				fullFare=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".fullFare"));
				discountedFare=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".discountedFare"));
			}*/
			else if (!(data.get("TransactionType").equalsIgnoreCase("TKT"))&&data.get("OSIEntry")!=null && data.get("OSIEntry").equalsIgnoreCase("true")) {
				fullExitAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".fullExitAdjustmentDeducted"));
				exitAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".exitAdjustmentDeducted"));
				fullFare=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".fullFare"));
				discountedFare=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".discountedFare"));
			}
			else if(data.get("UnstartedJourney")!=null && data.get("UnstartedJourney").equalsIgnoreCase("true")){
				fullExitAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+stationName+".fullExitAdjustmentDeducted"));
				exitAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+stationName+".exitAdjustmentDeducted"));
				fullFare=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+stationName+".fullFare"));
				discountedFare=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+stationName+".discountedFare"));
			}
			else{
				if (entryStationName!=null) {
					fullExitAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".fullExitAdjustmentDeducted"));
					exitAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".exitAdjustmentDeducted"));
					fullFare=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".fullFare"));
					discountedFare=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".discountedFare"));
				}

			}
			/*fullExitAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".fullExitAdjustmentDeducted"));
			exitAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".exitAdjustmentDeducted"));
			fullFare=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".fullFare"));
			discountedFare=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".discountedFare"));*/
			if (data.get("OSIEntry")!=null && data.get("OSIEntry").equalsIgnoreCase("true")&& fullEntryAdjustmentDeducted==32767) {
				osiEntryFlag=1;
				previousValidationErrorFlag=1;
				fullExitAdjustmentDeducted = 32767;
				//fullExitAdjustmentDeducted = 0;
			}

			if (data.get("WimbledonMJT")!=null&&data.get("WimbledonMJT").equalsIgnoreCase("true")) {
				stationNLC = 9440;
				fullExitAdjustmentDeducted =0;
				exitAdjustmentDeducted = 0;
				svInvlovedFlag  =0;
				fullFare =150;
				discountedFare =150;
				travelSetting=6;
				discountType=221;
				PPT2ValidityPageCRC=65;
				pptProductCode=0;


			}
			/*if (data.get("OSIEntry")!=null && data.get("OSIEntry").equalsIgnoreCase("true")) {
				osiEntryFlag=1;
			}*/

			if (SVBalance==0) {
				SVBalance=Long.parseLong(getDBColumnValue("Oracle","SELECT * FROM DD_CardTransaction WHERE prestigeid = '"+cardNumber+"' ORDER BY createddate DESC", "PAYGBALANCE",20));
			}
			//SVBalance=SVBalance-fullExitAdjustmentDeducted;
			SVBalance=SVBalance-exitAdjustmentDeducted;
			autoContinutionFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.autoContinutionFlag"));
			if (data.get("AutoCompletedJourney")!=null && data.get("AutoCompletedJourney").equalsIgnoreCase("true")) {
				autoCompletionFlag=1;
			}else{
				autoCompletionFlag=0;
			}



			//osiEntryFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.osiEntryFlag"));
			svInvlovedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.svInvlovedFlag"));
			ppt3UsedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.ppt3UsedFlag"));
			ppt2UsedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.ppt2UsedFlag"));
			ppt1UsedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.ppt1UsedFlag"));
			tokenExit=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.tokenExit"));
			sparebit14=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.sparebit14"));
			//previousValidationErrorFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.previousValidationErrorFlag"));
			unStartedPPTExitFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.unStartedPPTExitFlag"));
			unCompletedPPTEntryFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.unCompletedPPTEntryFlag"));

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
			//timeOfFirstEntry=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.timeOfFirstEntry"));
			//timeOfFirstEntry Value is captured in the RTD Transaction Header only: Refer RTD Transaction Header variable initilization part
			spareByte=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.spareByte"));
			zonalValidity=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.zonalValidity"));


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


			action.successReport("Verify the Exit Transaction Body is Created", "Exit Transaction Body is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			action.failureReport("Verify the Exit Transaction Body is Created", "Exit Transaction Body is NOT Created");
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * This method is used to generate a Exit transaction body for auto Topup Deduction when supplied with desired parameters.
	 * @param data
	 * @param TransactionFile
	 * @param transactionType
	 * @param action
	 * @throws Throwable
	 */
	public void generateExitTransactionBodyAutoTopUpDeduction(Hashtable<String, String> data,String TransactionFile, String transactionType,AppiumActions action) throws Throwable{
		try {
			String ExitTransactionFile = data.get("ExitTransaction");
			String TransactionExitData =FileUtil.readFile(ExitTransactionFile);
			String TransactionHeaderFile = data.get("TransactionHeader");
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);
			//String TransactionFile = data.get("TransactionFile");
			String TransactionData =FileUtil.readFile(TransactionFile);
			Object TransactionObject = JsonUtil.getJsonObject(TransactionData, "$."+transactionType+"");
			String stationName = JsonUtil.getJsonObjectElement(TransactionObject, ".StationName");
			String cardNumber = data.get("PrestigeCardNumber");

			resultCode=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.resultCode"));
			//stationNLC=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".NLC"));
				if(entryStationName!=null){
					stationNLC=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+entryStationName+".NLC"));	
				}

				exitChargeAppliedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.exitChargeAppliedFlag"));

			
				if (entryStationName!=null) {
					fullExitAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".fullExitAdjustmentDeducted"));
					exitAdjustmentDeducted=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".exitAdjustmentDeducted"));
					fullFare=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".fullFare"));
					discountedFare=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$."+entryStationName+"."+stationName+".discountedFare"));
				}
			if (SVBalance==0) {
				SVBalance=Long.parseLong(getDBColumnValue("Oracle","SELECT * FROM DD_CardTransaction WHERE prestigeid = '"+cardNumber+"' ORDER BY createddate DESC", "PAYGBALANCE",20));
			}
			//SVBalance=SVBalance-fullExitAdjustmentDeducted;
			SVBalance=SVBalance-exitAdjustmentDeducted;
			autoContinutionFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.autoContinutionFlag"));
			if (data.get("AutoCompletedJourney")!=null && data.get("AutoCompletedJourney").equalsIgnoreCase("true")) {
				autoCompletionFlag=1;
			}else{
				autoCompletionFlag=0;
			}



			//osiEntryFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.osiEntryFlag"));
			svInvlovedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.svInvlovedFlag"));
			ppt3UsedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.ppt3UsedFlag"));
			ppt2UsedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.ppt2UsedFlag"));
			ppt1UsedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.ppt1UsedFlag"));
			tokenExit=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.tokenExit"));
			sparebit14=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.sparebit14"));
			//previousValidationErrorFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.previousValidationErrorFlag"));
			unStartedPPTExitFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.unStartedPPTExitFlag"));
			unCompletedPPTEntryFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.unCompletedPPTEntryFlag"));

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
			//timeOfFirstEntry=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.timeOfFirstEntry"));
			//timeOfFirstEntry Value is captured in the RTD Transaction Header only: Refer RTD Transaction Header variable initilization part
			spareByte=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.spareByte"));
			zonalValidity=Long.parseLong(JsonUtil.getJsonElement(TransactionExitData, "$.zonalValidity"));


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


			action.successReport("Verify the Exit Transaction Body is Created", "Exit Transaction Body is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			action.failureReport("Verify the Exit Transaction Body is Created", "Exit Transaction Body is NOT Created");
			throw new RuntimeException(e);
		}
	}

	/**
	 * This method is used to generate a Tram transaction body when supplied with desired parameters.
	 * @param data
	 * @param TransactionFile
	 * @param transactionType
	 * @param action
	 * @throws Throwable
	 */
	public void generateTramTransactionBody(Hashtable<String, String> data,String TransactionFile, String transactionType,AppiumActions action) throws Throwable{
		try {
			String TramTransactionFile = data.get("TRAMTransaction");
			String TransactionTramData =FileUtil.readFile(TramTransactionFile);
			String TransactionHeaderFile = data.get("TransactionHeader");
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);
			//String TransactionFile = data.get("TransactionFile");
			String TransactionData =FileUtil.readFile(TransactionFile);
			Object TransactionObject = JsonUtil.getJsonObject(TransactionData, "$."+transactionType+"");
			String stationName = JsonUtil.getJsonObjectElement(TransactionObject, ".StationName");
			String cardNumber = data.get("PrestigeCardNumber");

			resultCode=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.resultCode"));
			stationNLC=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".NLC"));
			routeID =  JsonUtil.getJsonElement(TransactionTramData,"$."+"routeID");
			fullFare=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".fullFare"));
			discountedFare=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".discountedFare"));
			if (SVBalance==0) {
				SVBalance=Long.parseLong(getDBColumnValue("Oracle","SELECT * FROM DD_CardTransaction WHERE prestigeid = '"+cardNumber+"' ORDER BY createddate DESC", "PAYGBALANCE",20));
			}
			SVBalance=SVBalance-fullFare;
			autoContinutionFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.autoContinutionFlag"));
			autoCompletionFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.autoCompletionFlag"));
			osiEntryFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.osiEntryFlag"));
			svInvlovedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.svInvlovedFlag"));
			ppt3UsedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.ppt3UsedFlag"));
			ppt2UsedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.ppt2UsedFlag"));
			ppt1UsedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.ppt1UsedFlag"));
			tokenExit=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.tokenExit"));
			sparebit14=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.sparebit14"));
			previousValidationErrorFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.previousValidationErrorFlag"));
			unStartedPPTExitFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.unStartedPPTExitFlag"));
			unCompletedPPTEntryFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.unCompletedPPTEntryFlag"));

			passbackAuthorised=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.passbackAuthorised"));
			dailyCapping=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.dailyCapping"));
			TransferDiscountApp=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.TransferDiscountApp"));
			PPT1ValidityPageCRC=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.PPT1ValidityPageCRC"));
			PPT2ValidityPageCRC=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.PPT2ValidityPageCRC"));
			PPT3ValidityPageCRC=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.PPT3ValidityPageCRC"));
			passengerType=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.passengerType"));
			discountType=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.discountType"));
			zoneRangeCode=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.zoneRangeCode"));
			cappingSceme=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.cappingSceme"));
			pptProductCode=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.pptProductCode"));
			//timeOfFirstEntry=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.timeOfFirstEntry"));
			//timeOfFirstEntry Value is captured in the RTD Transaction Header only: Refer RTD Transaction Header variable initilization part
			spareByte=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.spareByte"));
			zonalValidity=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.zonalValidity"));
			pptPassengerType=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.pptPassengerType"));




			createbinary(resultCode,"Binary",1,0);
			createbinary(stationNLC,"Binary",2,0);
			createAsciiBinary(routeID,4);
			createbinary(fullFare,"Binary",3,0);
			createbinary(discountedFare,"Binary",3,0);
			createbinary(SVBalance,"Binary",3,0);
			createbinary(autoContinutionFlag,"BitField",1,1);
			createbinary(autoCompletionFlag,"BitField",1,1);
			createbinary(osiEntryFlag,"BitField",1,1);
			createbinary(svInvlovedFlag,"BitField",1,1);
			createbinary(ppt3UsedFlag,"BitField",1,1);
			createbinary(ppt2UsedFlag,"BitField",1,1);
			createbinary(ppt1UsedFlag,"BitField",1,1);
			createbinary(tokenExit,"BitField",1,1);

			//createbinary(fourteento18bits,"BitField",1,5);
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

			createbinary(spareByte,"Binary",1,0);
			createbinary(zonalValidity,"Binary",2,0);

			createbinary(pptPassengerType,"Binary",1,0);


			action.successReport("Verify the Exit Transaction Body is Created", "Exit Transaction Body is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			action.failureReport("Verify the Exit Transaction Body is Created", "Exit Transaction Body is NOT Created");
			throw new RuntimeException(e);
		}
	}

	/**
	 * This method is used to generate a Void Tram transaction body when supplied with desired parameters.
	 * @param data
	 * @param TransactionFile
	 * @param entryTramStationName
	 * @param transactionType
	 * @param action
	 * @throws Throwable
	 */
	public void generateVoidTramTransactionBody(Hashtable<String, String> data,String TransactionFile,String entryTramStationName, String transactionType,AppiumActions action) throws Throwable{
		try {
			String TramTransactionFile = data.get("VoidTRAMTransaction");
			String TransactionTramData =FileUtil.readFile(TramTransactionFile);
			String TransactionHeaderFile = data.get("TransactionHeader");
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);
			//String TransactionFile = data.get("TransactionFile");
			String TransactionData =FileUtil.readFile(TransactionFile);
			Object TransactionObject = JsonUtil.getJsonObject(TransactionData, "$."+transactionType+"");
			String stationName = JsonUtil.getJsonObjectElement(TransactionObject, ".StationName");
			String cardNumber = data.get("PrestigeCardNumber");



			TramStation=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+entryTramStationName+".NLC"));
			EntryAdjustmentRestored = Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+entryTramStationName+".fullFare"));
			if (SVBalance==0) {
				SVBalance=Long.parseLong(getDBColumnValue("Oracle","SELECT * FROM DD_CardTransaction WHERE prestigeid = '"+cardNumber+"' ORDER BY createddate DESC", "PAYGBALANCE",20));
			}
			SVBalance=SVBalance+fullFare;
			autoContinutionFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.autoContinutionFlag"));
			autoCompletionFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.autoCompletionFlag"));
			osiEntryFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.osiEntryFlag"));
			svInvlovedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.svInvlovedFlag"));
			ppt3UsedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.ppt3UsedFlag"));
			ppt2UsedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.ppt2UsedFlag"));
			ppt1UsedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.ppt1UsedFlag"));
			tokenExit=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.tokenExit"));
			sparebit14=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.sparebit14"));
			previousValidationErrorFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.previousValidationErrorFlag"));
			unStartedPPTExitFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.unStartedPPTExitFlag"));
			unCompletedPPTEntryFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.unCompletedPPTEntryFlag"));
			exitChargeAppliedFlag = Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.exitChargeAppliedFlag"));
			passbackAuthorised=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.passbackAuthorised"));
			dailyCapping=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.dailyCapping"));
			TransferDiscountApp=Long.parseLong(JsonUtil.getJsonElement(TransactionTramData, "$.TransferDiscountApp"));
			EntryAdjustmentBeforeDiscount = Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+entryTramStationName+".fullFare"));



			createbinary(TramStation,"Binary",2,0);
			createbinary(EntryAdjustmentRestored,"Binary",3,0);
			createbinary(SVBalance,"Binary",3,0);
			createbinary(autoContinutionFlag,"BitField",1,1);
			createbinary(autoCompletionFlag,"BitField",1,1);
			createbinary(osiEntryFlag,"BitField",1,1);
			createbinary(svInvlovedFlag,"BitField",1,1);
			createbinary(ppt3UsedFlag,"BitField",1,1);
			createbinary(ppt2UsedFlag,"BitField",1,1);
			createbinary(ppt1UsedFlag,"BitField",1,1);
			createbinary(tokenExit,"BitField",1,1);

			//createbinary(fourteento18bits,"BitField",1,5);
			createbinary(sparebit14,"BitField",1,1);
			createbinary(previousValidationErrorFlag,"BitField",1,1);
			createbinary(unStartedPPTExitFlag,"BitField",1,1);
			createbinary(unCompletedPPTEntryFlag,"BitField",1,1);
			createbinary(exitChargeAppliedFlag,"BitField",1,1);
			createbinary(passbackAuthorised,"BitField",1,1);
			createbinary(dailyCapping,"BitField",1,1);
			createbinary(TransferDiscountApp,"BitField",1,1);
			createbinary(EntryAdjustmentBeforeDiscount,"Binary",3,1);




			action.successReport("Verify the Exit Transaction Body is Created", "Exit Transaction Body is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			action.failureReport("Verify the Exit Transaction Body is Created", "Exit Transaction Body is NOT Created");
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * This method is used to generate a Bus transaction body when supplied with desired parameters.
	 * @param data
	 * @param TransactionFile
	 * @param transactionType
	 * @param action
	 * @throws Throwable
	 */
	public void generateBusTransactionBody(Hashtable<String, String> data,String TransactionFile, String transactionType,AppiumActions action) throws Throwable{
		try {
			String BusTransactionFile = data.get("BUSTransaction");
			String TransactionBusData =FileUtil.readFile(BusTransactionFile);
			String TransactionHeaderFile = data.get("TransactionHeader");
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);
			//String TransactionFile = data.get("TransactionFile");
			String TransactionData =FileUtil.readFile(TransactionFile);
			Object TransactionObject = JsonUtil.getJsonObject(TransactionData, "$."+transactionType+"");
			String stationName = JsonUtil.getJsonObjectElement(TransactionObject, ".StationName");
			String cardNumber = data.get("PrestigeCardNumber");

			resultCode=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.resultCode"));
			WAYCONClass=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.WAYCONClass"));
			AlightingFareStage=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.AlightingFareStage"));
			busRouteID =  JsonUtil.getJsonElement(TransactionHeaderData,"$."+stationName+".busRouteID");
			fullFare=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".fullFare"));
			discountedFare=Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+stationName+".discountedFare"));
			if (SVBalance==0) {
				SVBalance=Long.parseLong(getDBColumnValue("Oracle","SELECT * FROM DD_CardTransaction WHERE prestigeid = '"+cardNumber+"' ORDER BY createddate DESC", "PAYGBALANCE",20));
			}
			SVBalance=SVBalance-fullFare;
			autoContinutionFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.autoContinutionFlag"));
			autoCompletionFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.autoCompletionFlag"));
			osiEntryFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.osiEntryFlag"));
			svInvlovedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.svInvlovedFlag"));
			ppt3UsedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.ppt3UsedFlag"));
			ppt2UsedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.ppt2UsedFlag"));
			ppt1UsedFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.ppt1UsedFlag"));
			tokenExit=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.tokenExit"));
			sparebit14=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.sparebit14"));
			previousValidationErrorFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.previousValidationErrorFlag"));
			unStartedPPTExitFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.unStartedPPTExitFlag"));
			unCompletedPPTEntryFlag=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.unCompletedPPTEntryFlag"));

			passbackAuthorised=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.passbackAuthorised"));
			dailyCapping=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.dailyCapping"));
			TransferDiscountApp=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.TransferDiscountApp"));
			PPT1ValidityPageCRC=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.PPT1ValidityPageCRC"));
			PPT2ValidityPageCRC=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.PPT2ValidityPageCRC"));
			PPT3ValidityPageCRC=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.PPT3ValidityPageCRC"));
			passengerType=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.passengerType"));
			discountType=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.discountType"));
			zoneRangeCode=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.zoneRangeCode"));
			cappingSceme=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.cappingSceme"));
			pptProductCode=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.pptProductCode"));
			spareByte=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.spareByte"));
			zonalValidity=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.zonalValidity"));
			pptPassengerType=Long.parseLong(JsonUtil.getJsonElement(TransactionBusData, "$.pptPassengerType"));




			createbinary(resultCode,"Binary",1,0);
			createbinary(WAYCONClass,"Binary",2,0);
			createbinary(AlightingFareStage,"Binary",2,0);
			createAsciiBinary(busRouteID,4);
			createbinary(fullFare,"Binary",3,0);
			createbinary(discountedFare,"Binary",3,0);
			createbinary(SVBalance,"Binary",3,0);
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

			createbinary(spareByte,"Binary",1,0);
			createbinary(zonalValidity,"Binary",2,0);

			createbinary(pptPassengerType,"Binary",1,0);


			action.successReport("Verify the Exit Transaction Body is Created", "Exit Transaction Body is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			action.failureReport("Verify the Exit Transaction Body is Created", "Exit Transaction Body is NOT Created");
			throw new RuntimeException(e);
		}
	}


	/**
	 * This method is used to generate a IVAL transaction body when supplied with desired parameters.
	 * @param data
	 * @param TransactionFile
	 * @param EntryStationName
	 * @param transactionType
	 * @param action
	 * @throws Throwable
	 */
	public void generateIVALTransactionBody(Hashtable<String, String> data,String TransactionFile,String EntryStationName,String transactionType,AppiumActions action ) throws Throwable{
		try {
			String IVALTransactionFile = data.get("IVALTransaction");
			String TransactionIVALData =FileUtil.readFile(IVALTransactionFile);

			String TransactionHeaderFile = data.get("TransactionHeader");
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);
			/*String TransactionData =FileUtil.readFile(TransactionFile);
			Object TransactionObject = JsonUtil.getJsonObject(TransactionData, "$."+transactionType+"");

			String txnStationName = JsonUtil.getJsonObjectElement(TransactionObject, ".StationName");*/
			if (entryStationName!=null) {
				stationOfFirstEntryNLC =  Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+entryStationName+".NLC"));
			}
			else{
				stationOfFirstEntryNLC = Long.parseLong(JsonUtil.getJsonElement(TransactionHeaderData, "$."+EntryStationName+".NLC"));	
			}

			timeOfFirstEntryIVAL = creationTime;
			R65_3_2BitFlag = Long.parseLong(JsonUtil.getJsonElement(TransactionIVALData, "$.R65_3_2BitFlag"));
			CrossDayJourneyFlag = Long.parseLong(JsonUtil.getJsonElement(TransactionIVALData, "$.CrossDayJourneyFlag"));
			RelevantRoutingNodeFlag = Long.parseLong(JsonUtil.getJsonElement(TransactionIVALData, "$.RelevantRoutingNodeFlag"));
			ZonalIndicator = Long.parseLong(JsonUtil.getJsonElement(TransactionIVALData, "$.ZonalIndicator"));


			createbinary(stationOfFirstEntryNLC,"Binary",2,0);
			createbinary(timeOfFirstEntryIVAL,"Binary",2,0);
			createbinary(R65_3_2BitFlag,"BitField",1,2);
			createbinary(CrossDayJourneyFlag,"BitField",1,1);
			createbinary(RelevantRoutingNodeFlag,"BitField",1,1);
			createbinary(ZonalIndicator,"BitField",1,4);


		} catch (Exception e) {
			// TODO: handle exception
		}


	}


	/**
	 * This method is used to generate a TKT transaction body when supplied with desired parameters.
	 * @param data
	 * @param transactionType
	 * @param requestSequenceNumber
	 * @param selectedDate
	 * @param zoneRanges
	 * @param action
	 * @throws Throwable
	 */
	public void generateTKTTransactionBody(Hashtable<String, String> data,String transactionType,String requestSequenceNumber,String selectedDate,String zoneRanges,AppiumActions action) throws Throwable{
		try {
			String TKTTransactionFile = data.get("TKTTransaction");
			String TransactionTKTData =FileUtil.readFile(TKTTransactionFile);

			/*String TransactionHeaderFile = data.get("TransactionHeader");
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);

			String TransactionFile = data.get("TKTTransactionSetFile");
			String TransactionData =FileUtil.readFile(TransactionFile);
			Object TransactionObject = JsonUtil.getJsonObject(TransactionData, "$."+transactionType+"");
			String stationName = JsonUtil.getJsonObjectElement(TransactionObject, ".StationName");*/
			String cardNumber = data.get("PrestigeCardNumber");

			//TKTRequestSeqNo = Long.parseLong(JsonUtil.getJsonElement(TransactionTKTData, "$.TKTRequestSeqNo"));

			/*String TKTRequestSequenceNo = getDBColumnValue("Oracle","select * from cdsprod1.ful_pendingrequests where prestigeid = '"+cardNumber+"' and status = 'Not Sent' order by REQUESTSEQNO DESC", "REQUESTSEQNO",45);
			if (TKTRequestSequenceNo!=null) {
				TKTRequestSeqNo = Long.parseLong(TKTRequestSequenceNo);
			}
			if (TKTRequestSeqNo==0) {
				//TKTRequestSeqNo = Long.parseLong(getDBColumnValue("Oracle","select * from cdsprod1.ful_pendingrequests where prestigeid = '"+cardNumber+"' and status = 'Pending' order by REQUESTSEQNO DESC", "REQUESTSEQNO"));
			}
			else if(TKTRequestSeqNo==0){
				TKTRequestSeqNo = Long.parseLong(getDBColumnValue("Oracle","select * from cdsprod1.ful_pendingrequests where prestigeid = '"+cardNumber+"' and status = 'Distributed' order by REQUESTSEQNO DESC", "REQUESTSEQNO"));
			}
			else if(TKTRequestSeqNo==0){
				TKTRequestSeqNo = Long.parseLong(JsonUtil.getJsonElement(TransactionTKTData, "$.TKTRequestSeqNo"));
			}*/
			if(!(requestSequenceNumber==null)){
				TKTRequestSeqNo = Long.parseLong(requestSequenceNumber);
			}else{
				TKTRequestSeqNo = Long.parseLong(JsonUtil.getJsonElement(TransactionTKTData, "$.TKTRequestSeqNo"));
			}



			TKTPPTSlotNo = Long.parseLong(JsonUtil.getJsonElement(TransactionTKTData, "$.PPTSlotNo"));
			TKTPPTValidityPageCRC = Long.parseLong(JsonUtil.getJsonElement(TransactionTKTData, "$.TKTPPTValidityPageCRC"));
			TKTPassengerType = Long.parseLong(JsonUtil.getJsonElement(TransactionTKTData, "$.PassengerType"));
			TKTDiscountApplied = Long.parseLong(JsonUtil.getJsonElement(TransactionTKTData, "$.DiscountApplied"));
			TKTPaymentMethod = Long.parseLong(JsonUtil.getJsonElement(TransactionTKTData, "$.PaymentMethod"));
			TKTProductCode = Long.parseLong(JsonUtil.getJsonElement(TransactionTKTData, "$.ProductCode"));
			TKTRTVCalendarMonths = Long.parseLong(JsonUtil.getJsonElement(TransactionTKTData, "$.RTVCalendarMonths"));
			TKTRTVDays = Long.parseLong(JsonUtil.getJsonElement(TransactionTKTData, "$.RTVDays"));
			TKTRTVPricingStructure = Long.parseLong(JsonUtil.getJsonElement(TransactionTKTData, "$.RTVPricingStructure"));
			TKTRTVSelector = Long.parseLong(JsonUtil.getJsonElement(TransactionTKTData, "$.RTVSelector"));
			TKTRTVReportingSegmentation = Long.parseLong(JsonUtil.getJsonElement(TransactionTKTData, "$.RTVReportingSegmentation"));
			TKTPassengerTypeAge = Long.parseLong(JsonUtil.getJsonElement(TransactionTKTData, "$.PassengerTypeAge"));
			TKTTicketClass = Long.parseLong(JsonUtil.getJsonElement(TransactionTKTData, "$.TicketClass"));
			//TKTZonalValidity = Long.parseLong(JsonUtil.getJsonElement(TransactionTKTData, "$.ZonalValidity"));
			TKTZonalValidity = Long.parseLong(JsonUtil.getJsonElement(TransactionTKTData, "$.ZoneRanges"+"."+zoneRanges+".ZonalValidity"));
			TKTRoute = Long.parseLong(JsonUtil.getJsonElement(TransactionTKTData, "$.Route"));
			TKTDestination = Long.parseLong(JsonUtil.getJsonElement(TransactionTKTData, "$.Destination"));
			TKTTravelcardZones = Long.parseLong(JsonUtil.getJsonElement(TransactionTKTData, "$.TravelcardZones"));
			TKTMediumType = Long.parseLong(JsonUtil.getJsonElement(TransactionTKTData, "$.MediumType"));
			TKTTimeRestriction = Long.parseLong(JsonUtil.getJsonElement(TransactionTKTData, "$.TimeRestriction"));
			TKTDiscount = Long.parseLong(JsonUtil.getJsonElement(TransactionTKTData, "$.Discount"));
			TKTStartDate = getDayKey(selectedDate);
			TKTExtraDays=Long.parseLong(JsonUtil.getJsonElement(TransactionTKTData, "$.ExtraDays"));
			TKTspareBit=Long.parseLong(JsonUtil.getJsonElement(TransactionTKTData, "$.spareBit"));

			createbinary(TKTRequestSeqNo,"Binary",4,0);
			createbinary(TKTPPTSlotNo,"Binary",1,0);
			createbinary(TKTPPTValidityPageCRC,"Binary",2,0);
			createbinary(TKTPassengerType,"BitField",1,3);
			createbinary(TKTDiscountApplied,"BitField",1,5);
			createbinary(TKTPaymentMethod,"Binary",2,0);
			createBitbinary(TKTProductCode,"Bits",9);
			createBitbinary(TKTRTVCalendarMonths,"Bits",4);
			createBitbinary(TKTRTVDays,"Bits",5);
			createBitbinary(TKTRTVPricingStructure,"Bits",2);
			createBitbinary(TKTRTVSelector,"Bits",1);
			createBitbinary(TKTRTVReportingSegmentation,"Bits",4);
			createBitbinary(TKTPassengerTypeAge,"Bits",3);
			createBitbinary(TKTTicketClass,"Bits",2);
			createBitbinary(TKTZonalValidity,"Bits",16);
			createBitbinary(TKTRoute,"Bits",4);
			createBitbinary(TKTDestination,"Bits",14);
			createBitbinary(TKTTravelcardZones,"Bits",1);
			createBitbinary(TKTMediumType,"Bits",2);
			createBitbinary(TKTTimeRestriction,"Bits",3);
			createBitbinary(TKTDiscount,"Bits",5);
			createBitbinary(TKTStartDate,"Bits",15);
			createBitbinary(TKTExtraDays,"Bits",6);
			System.out.println("Total bytes count ="+count);



			action.successReport("Verify the TKT Transaction Body is Created", "TKT Transaction Body is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			action.failureReport("Verify the TKT Transaction Body is Created", "TKT Transaction Body is NOT Created");
			throw new RuntimeException(e);
		}
	}

	/**
	 * This method is used to generate a Transaction Trailer when supplied with desired parameters.
	 * @param action
	 */
	public void generateTransactionTrailer(AppiumActions action){
		try {
			MAC = FileUtil.generateRandomNumber(225819999,225811111);
			createbinary(MAC,"Binary",4,0);
			action.successReport("Verify the Transaction Trailer is Created", "Transaction Trailer is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			action.failureReport("Verify the Transaction Trailer is Created", "Transaction Trailer is NOT Created");
			throw new RuntimeException(e);
		}


	}
	
	/**
	 * This method is used to generate a File Trailer when supplied with desired parameters.
	 * @param data
	 * @param action
	 * @throws Throwable
	 */
	public void generateFileTrailer(Hashtable<String, String> data,AppiumActions action) throws Throwable{
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
			action.successReport("Verify the File Trailer is Created", "File Trailer is Created Successfully");
		} catch (Exception e) {
			// TODO: handle exception
			action.failureReport("Verify the File Trailer is Created", "File Trailer is NOT Created");
			throw new RuntimeException(e);
		}

	}
	
	/**
	 * This method is used to generate a PAYG Transaction body when supplied with desired parameters.
	 * @param data
	 * @param requestSequenceNumber
	 * @param action
	 * @param topupAmount
	 * @throws Throwable
	 */
	public void generatePAYGTransactionBody(Hashtable<String, String> data,String requestSequenceNumber,AppiumActions action, String topupAmount) throws Throwable{
		try {
			String PAYGTransaction = data.get("PAYGTransaction");
			String PAYGTransactionData =FileUtil.readFile(PAYGTransaction);
			String cardNumber = data.get("PrestigeCardNumber");

			//System.out.println("Request Seq no is "+JsonUtil.getJsonElement(PAYGTransactionData, "$.RequestSeqNo"));
			//RequestSeqNo = Long.parseLong(JsonUtil.getJsonElement(PAYGTransactionData, "$.RequestSeqNo"));
			//String RequestSeqNoString = getDBColumnValue("select * from cdsprod1.ful_pendingrequests where prestigeid = '"+cardNumber+"' and status = 'Not Sent'", "REQUESTSEQNO",100);
			//System.out.println("RequestSeqNoString is "+RequestSeqNoString);
			/*String requestSeqNo = getDBColumnValue("Oracle","select * from cdsprod1.ful_pendingrequests where prestigeid = '"+cardNumber+"' and status = 'Not Sent' order by REQUESTSEQNO DESC", "REQUESTSEQNO",60);
			if (requestSeqNo!=null) {
				RequestSeqNo = Long.parseLong(requestSeqNo);
			}
			if (RequestSeqNo==0) {
				RequestSeqNo = Long.parseLong(getDBColumnValue("Oracle","select * from cdsprod1.ful_pendingrequests where prestigeid = '"+cardNumber+"' and status = 'Pending' order by REQUESTSEQNO DESC", "REQUESTSEQNO"));
			}
			else if(RequestSeqNo==0){
				RequestSeqNo = Long.parseLong(getDBColumnValue("Oracle","select * from cdsprod1.ful_pendingrequests where prestigeid = '"+cardNumber+"' and status = 'Distributed' order by REQUESTSEQNO DESC", "REQUESTSEQNO"));
			}
			else if(RequestSeqNo==0){
				RequestSeqNo = Long.parseLong(JsonUtil.getJsonElement(PAYGTransactionData, "$.RequestSeqNo"));
			}*/
			if (!(requestSequenceNumber==null)) {
				RequestSeqNo = Long.parseLong(requestSequenceNumber);
			}
			else{
				RequestSeqNo = Long.parseLong(JsonUtil.getJsonElement(PAYGTransactionData, "$.RequestSeqNo"));
			}

			if (topupAmount.equalsIgnoreCase("")) {
				svAdded = Long.parseLong(JsonUtil.getJsonElement(PAYGTransactionData, "$.svAdded"));

			}
			else{
				svAdded =  Long.parseLong(topupAmount);
			}

			paymentMethod = Long.parseLong(JsonUtil.getJsonElement(PAYGTransactionData, "$.paymentMethod"));
			if (SVBalance==0) {
				SVBalance= Long.parseLong(getDBColumnValue("Oracle","SELECT * FROM DD_CardTransaction WHERE prestigeid like '%"+cardNumber+"' ORDER BY createddate DESC", "PAYGBALANCE",20));				
			}
			SVBalance=SVBalance+svAdded;

			createbinary(RequestSeqNo,"Binary",4,0);
			createbinary(svAdded,"Binary",3,0);
			createbinary(paymentMethod,"Binary",2,0);
			createbinary(SVBalance,"Binary",3,0);
			action.successReport("Verify the PAYG Transaction Body is Created", "PAYG Transaction Body is Created Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			action.failureReport("Verify the PAYG Transaction Body is Created", "PAYG Transaction Body is NOT Created");
			throw new RuntimeException(e);
		}

	}


	/**
	 * This method is used to generate a PAYG transaction and returns the filename as a string when supplied with desired parameters.
	 * @param data
	 * @param TransactionFile
	 * @param transactionType
	 * @param requestSequenceNumber
	 * @param action
	 * @param topupAmount
	 * @return
	 * @throws Throwable
	 */
	public String generatePAYG(Hashtable<String, String> data,String TransactionFile,String transactionType,String requestSequenceNumber, AppiumActions action, String topupAmount) throws Throwable{
		String FileName= null;
		try {
			fileCounter=fileCounter+1;

			String TransactionHeaderFile = data.get("TransactionHeader");			
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);

			//String TransactionFile = data.get("TransactionFile");
			String TransactionData =FileUtil.readFile(TransactionFile);
			Object TransactionObject = JsonUtil.getJsonObject(TransactionData, "$."+transactionType+"");

			String paygOrderCollectionStation = JsonUtil.getJsonObjectElement(TransactionObject, ".StationName");
			String txnFileType = JsonUtil.getJsonElement(TransactionHeaderData, "$."+paygOrderCollectionStation+".TXNFileType");

			FileName = txnFileType+"_"+getCurrentdayKey()+"_000"+fileCounter+".dat";
			FileUtil.deleteSpecificFile(FileName);

			transactionFilePath = System.getProperty("user.dir")+"//"+FileName;

			output = new FileOutputStream(transactionFilePath, true);

			generateFileHeader(data,action,transactionType);
			generateRecordHeader(data,action,transactionType);
			generateTH(data,action,TransactionFile,transactionType);
			generateETH(data,action);
			generateRTDTransactionHeader(data,TransactionFile,transactionType,action);
			generatePAYGTransactionBody(data,requestSequenceNumber,action,topupAmount);
			generateTransactionTrailer(action);
			generateFileTrailer(data,action);

			output.flush();
			output.close();
			//AppiumActions actions = new AppiumActions();
			action.successReport("Verify PAYG TAP File Creation", "PAYG Transaction TAP is created successfully");

		} catch (Exception e) {
			e.printStackTrace();
			action.failureReport("Verify PAYG TAP File Creation", "PAYG Transaction TAP is NOT created");
			throw new RuntimeException(e);
		}
		return FileName;

	}

	/**
	 * This method is used to generate a IVAL transaction and returns the filename as a string when supplied with desired parameters.
	 * @param data
	 * @param TransactionFile
	 * @param EntryStationName
	 * @param transactionType
	 * @param action
	 * @return
	 * @throws Throwable
	 */
	public String generateIvalTransaction(Hashtable<String, String> data,String TransactionFile, String EntryStationName, String transactionType,AppiumActions action) throws Throwable{
		String FileName= null;
		try {
			fileCounter=fileCounter+1;

			ivalTxnType = transactionType;

			String TransactionHeaderFile = data.get("TransactionHeader");			
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);

			//String TransactionFile = data.get("TransactionFile");
			String TransactionData =FileUtil.readFile(TransactionFile);
			Object TransactionObject = JsonUtil.getJsonObject(TransactionData, "$."+transactionType+"");

			String txnStationName = JsonUtil.getJsonObjectElement(TransactionObject, ".StationName");
			String txnFileType = JsonUtil.getJsonElement(TransactionHeaderData, "$."+txnStationName+".TXNFileType");

			FileName = txnFileType+"_"+getCurrentdayKey()+"_000"+fileCounter+".dat";
			FileUtil.deleteSpecificFile(FileName);

			transactionFilePath = System.getProperty("user.dir")+"//"+FileName;
			output = new FileOutputStream(transactionFilePath, true);

			generateFileHeader(data,action,transactionType);
			generateRecordHeader(data,action,transactionType);
			generateTH(data,action,TransactionFile,transactionType);
			generateETH(data,action);
			generateRTDTransactionHeader(data,TransactionFile,transactionType,action);
			generateIVALTransactionBody(data,TransactionFile,EntryStationName,transactionType,action);
			generateTransactionTrailer(action);
			generateFileTrailer(data,action);
			output.flush();
			output.close();
			//AppiumActions actions = new AppiumActions();
			action.successReport("Verify PAYG TAP File Creation", "PAYG Transaction TAP is created successfully");

		} catch (Exception e) {
			e.printStackTrace();
			action.failureReport("Verify PAYG TAP File Creation", "PAYG Transaction TAP is NOT created");
			throw new RuntimeException(e);
		}
		return FileName;
	}
	
	/**
	 * This method is used to generate Entry transaction and returns the filename as a string when supplied with desired parameters.
	 * @param data
	 * @param TransactionFile
	 * @param EntryStationName
	 * @param transactionType
	 * @param action
	 * @return
	 * @throws Throwable
	 */
	public String generateEntryTransaction(Hashtable<String, String> data,String TransactionFile, String EntryStationName, String transactionType,AppiumActions action) throws Throwable{
		String FileName= null;
		try {
			fileCounter=fileCounter+1;

			String TransactionHeaderFile = data.get("TransactionHeader");			
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);

			//String TransactionFile = data.get("TransactionFile");
			String TransactionData =FileUtil.readFile(TransactionFile);
			Object TransactionObject = JsonUtil.getJsonObject(TransactionData, "$."+transactionType+"");


			//entryStationName = JsonUtil.getJsonObjectElement(TransactionObject, ".StationName");
			entryStationName = EntryStationName;
			String txnFileType = JsonUtil.getJsonElement(TransactionHeaderData, "$."+entryStationName+".TXNFileType");

			FileName = txnFileType+"_"+getCurrentdayKey()+"_000"+fileCounter+".dat";
			FileUtil.deleteSpecificFile(FileName);
			transactionFilePath = System.getProperty("user.dir")+"//"+FileName;
			output = new FileOutputStream(transactionFilePath, true);
			generateFileHeader(data,action,transactionType);
			generateRecordHeader(data,action,transactionType);
			generateTH(data,action,TransactionFile,transactionType);
			generateETH(data,action);
			generateRTDTransactionHeader(data,TransactionFile,transactionType,action);
			generateEntryTransactionBody(data,TransactionFile,EntryStationName,transactionType,action);
			generateTransactionTrailer(action);
			generateFileTrailer(data,action);

			output.flush();
			output.close();
			action.successReport("Verify Entry Transaction TAP File Creation", "Entry Transaction TAP is created successfully");

		} catch (Exception e) {
			e.printStackTrace();
			action.failureReport("Verify Entry Transaction TAP File Creation", "Entry Transaction TAP  is NOT created");
			throw new RuntimeException(e);
		}
		return FileName;

	}

	/**
	 * This method is used to generate a Void Entry transaction and returns the filename as a string when supplied with desired parameters.
	 * @param data
	 * @param TransactionFile
	 * @param EntryStationName
	 * @param transactionType
	 * @param action
	 * @return
	 * @throws Throwable
	 */
	public String generateVoidEntryTransaction(Hashtable<String, String> data,String TransactionFile, String EntryStationName, String transactionType,AppiumActions action) throws Throwable{
		String FileName= null;
		try {
			fileCounter=fileCounter+1;

			String TransactionHeaderFile = data.get("TransactionHeader");			
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);

			//String TransactionFile = data.get("TransactionFile");
			String TransactionData =FileUtil.readFile(TransactionFile);
			Object TransactionObject = JsonUtil.getJsonObject(TransactionData, "$."+transactionType+"");


			//entryStationName = JsonUtil.getJsonObjectElement(TransactionObject, ".StationName");
			entryStationName = EntryStationName;
			String txnFileType = JsonUtil.getJsonElement(TransactionHeaderData, "$."+entryStationName+".TXNFileType");

			FileName = txnFileType+"_"+getCurrentdayKey()+"_000"+fileCounter+".dat";
			FileUtil.deleteSpecificFile(FileName);
			transactionFilePath = System.getProperty("user.dir")+"//"+FileName;
			//FileUtil.deleteFileOrFolder(fileOrFolderPath);
			output = new FileOutputStream(transactionFilePath, true);
			generateFileHeader(data,action,transactionType);
			generateRecordHeader(data,action,transactionType);
			generateTH(data,action,TransactionFile,transactionType);
			generateETH(data,action);
			generateRTDTransactionHeader(data,TransactionFile,transactionType,action);
			generateVoidEntryTransactionBody(data,TransactionFile,EntryStationName,transactionType,action);
			generateTransactionTrailer(action);
			generateFileTrailer(data,action);

			output.flush();
			output.close();
			action.successReport("Verify Entry Transaction TAP File Creation", "Entry Transaction TAP is created successfully");

		} catch (Exception e) {
			e.printStackTrace();
			action.failureReport("Verify Entry Transaction TAP File Creation", "Entry Transaction TAP  is NOT created");
			throw new RuntimeException(e);
		}
		return FileName;

	}
	
	/**
	 * This method is used to generate a Auto Completed Transaction and returns the filename as a string when supplied with desired parameters.
	 * @param data
	 * @param TransactionFile
	 * @param transactionType
	 * @param action
	 * @return
	 * @throws Throwable
	 */
	public String generateAutoCompletedTransaction(Hashtable<String, String> data,String TransactionFile, String transactionType,AppiumActions action) throws Throwable{
		String FileName= null;
		try {
			fileCounter=fileCounter+1;

			String TransactionHeaderFile = data.get("TransactionHeader");			
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);

			//String TransactionFile = data.get("TransactionFile");
			String TransactionData =FileUtil.readFile(TransactionFile);
			Object TransactionObject = JsonUtil.getJsonObject(TransactionData, "$."+transactionType+"");

			//entryStationName = "ElephantAndCastle";
			String txnFileType = JsonUtil.getJsonElement(TransactionHeaderData, "$."+entryStationName+".TXNFileType");

			FileName = txnFileType+"_"+getCurrentdayKey()+"_000"+fileCounter+".dat";
			FileUtil.deleteSpecificFile(FileName);
			transactionFilePath = System.getProperty("user.dir")+"//"+FileName;
			//FileUtil.deleteFileOrFolder(fileOrFolderPath);
			output = new FileOutputStream(transactionFilePath, true);
			//transactionType = "EXIT";
			generateFileHeader(data,action,transactionType);
			generateRecordHeaderForAutoCompletedJourney(data,action,transactionType);
			generateTH(data,action,TransactionFile,transactionType);
			generateETH(data,action);
			generateRTDTransactionHeader(data,TransactionFile,transactionType,action);
			generateExitTransactionBody(data,TransactionFile,transactionType,action);
			generateTransactionTrailer(action);

			transactionType = "Entry";
			String TransactionData2 =FileUtil.readFile(TransactionFile);
			Object TransactionObject2 = JsonUtil.getJsonObject(TransactionData2, "$."+transactionType+"");
			entryStationName = JsonUtil.getJsonObjectElement(TransactionObject2, ".StationName");

			generateRTDTransactionHeader(data,TransactionFile,transactionType,action);
			generateEntryTransactionBody(data,TransactionFile,entryStationName,transactionType,action);
			generateTransactionTrailer(action);
			generateFileTrailer(data,action);
			output.flush();
			output.close();
			action.successReport("Verify Entry Transaction TAP File Creation", "Entry Transaction TAP is created successfully");

		} catch (Exception e) {
			e.printStackTrace();
			action.failureReport("Verify Entry Transaction TAP File Creation", "Entry Transaction TAP  is NOT created");
			throw new RuntimeException(e);
		}
		return FileName;

	}
	
	/**
	 * This is method is used to set the Entry Station name value to a variable.
	 * @param entryStation
	 */
	public void setEntryStationName(String entryStation){
		entryStationName = entryStation;
	}

	/**
	 * This method is used to generate a Exit transaction and returns the filename as a string when supplied with desired parameters.
	 * @param data
	 * @param TransactionFile
	 * @param transactionType
	 * @param action
	 * @return
	 * @throws Throwable
	 */
	public String generateExitTransaction(Hashtable<String, String> data,String TransactionFile, String transactionType,AppiumActions action) throws Throwable{
		String FileName= null;
		try {
			fileCounter=fileCounter+1;
			String TransactionHeaderFile = data.get("TransactionHeader");			
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);

			//String TransactionFile = data.get("TransactionFile");
			String TransactionData =FileUtil.readFile(TransactionFile);
			Object TransactionObject = JsonUtil.getJsonObject(TransactionData, "$."+transactionType+"");

			String StationName = JsonUtil.getJsonObjectElement(TransactionObject, ".StationName");
			String txnFileType = JsonUtil.getJsonElement(TransactionHeaderData, "$."+StationName+".TXNFileType");

			FileName = txnFileType+"_"+getCurrentdayKey()+"_000"+fileCounter+".dat";
			FileUtil.deleteSpecificFile(FileName);
			transactionFilePath = System.getProperty("user.dir")+"//"+FileName;

			output = new FileOutputStream(transactionFilePath, true);

			generateFileHeader(data,action,transactionType);
			generateRecordHeader(data,action,transactionType);
			generateTH(data,action,TransactionFile,transactionType);
			generateETH(data,action);
			generateRTDTransactionHeader(data,TransactionFile,transactionType,action);
			generateExitTransactionBody(data,TransactionFile,transactionType,action);
			generateTransactionTrailer(action);
			generateFileTrailer(data,action);
			output.flush();
			output.close();
			action.successReport("Verify Exit Transaction TAP File Creation", "Exit Transaction TAP is created successfully");

		} catch (Exception e) {
			e.printStackTrace();
			action.failureReport("Verify Exit Transaction TAP File Creation", "Exit Transaction TAP is NOT created");
			throw new RuntimeException(e);
		}
		return FileName;

	}
	
	/**
	 * This method is used to generate a Exit transaction for Auto TopUp Deduction and returns the filename as a string when supplied with desired parameters.
	 * @param data
	 * @param TransactionFile
	 * @param transactionType
	 * @param action
	 * @return
	 * @throws Throwable
	 */
	public String generateExitTransactionAutoTopUpDeduction(Hashtable<String, String> data,String TransactionFile, String transactionType,AppiumActions action) throws Throwable{
		String FileName= null;
		try {
			fileCounter=fileCounter+1;
			String TransactionHeaderFile = data.get("TransactionHeader");			
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);

			//String TransactionFile = data.get("TransactionFile");
			String TransactionData =FileUtil.readFile(TransactionFile);
			Object TransactionObject = JsonUtil.getJsonObject(TransactionData, "$."+transactionType+"");

			String StationName = JsonUtil.getJsonObjectElement(TransactionObject, ".StationName");
			String txnFileType = JsonUtil.getJsonElement(TransactionHeaderData, "$."+StationName+".TXNFileType");

			FileName = txnFileType+"_"+getCurrentdayKey()+"_000"+fileCounter+".dat";
			FileUtil.deleteSpecificFile(FileName);
			transactionFilePath = System.getProperty("user.dir")+"//"+FileName;

			output = new FileOutputStream(transactionFilePath, true);

			generateFileHeader(data,action,transactionType);
			generateRecordHeader(data,action,transactionType);
			generateTH(data,action,TransactionFile,transactionType);
			generateETH(data,action);
			generateRTDTransactionHeader(data,TransactionFile,transactionType,action);
			generateExitTransactionBodyAutoTopUpDeduction(data,TransactionFile,transactionType,action);
			generateTransactionTrailer(action);
			generateFileTrailer(data,action);
			output.flush();
			output.close();
			action.successReport("Verify Exit Transaction TAP File Creation", "Exit Transaction TAP is created successfully");

		} catch (Exception e) {
			e.printStackTrace();
			action.failureReport("Verify Exit Transaction TAP File Creation", "Exit Transaction TAP is NOT created");
			throw new RuntimeException(e);
		}
		return FileName;

	}
	
	/**
	 * This method is used to generate a Tram Tap and returns the filename as a string when supplied with desired parameters.
	 * @param data
	 * @param TransactionFile
	 * @param transactionType
	 * @param action
	 * @return
	 * @throws Throwable
	 */
	public String generateTramTap(Hashtable<String, String> data,String TransactionFile,String transactionType,AppiumActions action) throws Throwable{
		String FileName= null;
		try {
			fileCounter=fileCounter+1;
			String TransactionHeaderFile = data.get("TransactionHeader");			
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);

			//String TransactionFile = data.get("TransactionFile");
			String TransactionData =FileUtil.readFile(TransactionFile);
			Object TransactionObject = JsonUtil.getJsonObject(TransactionData, "$."+transactionType+"");

			String StationName = JsonUtil.getJsonObjectElement(TransactionObject, ".StationName");
			String txnFileType = JsonUtil.getJsonElement(TransactionHeaderData, "$."+StationName+".TXNFileType");

			FileName = txnFileType+"_"+getCurrentdayKey()+"_000"+fileCounter+".dat";
			FileUtil.deleteSpecificFile(FileName);
			transactionFilePath = System.getProperty("user.dir")+"//"+FileName;

			output = new FileOutputStream(transactionFilePath, true);

			generateFileHeader(data,action,transactionType);
			generateRecordHeader(data,action,transactionType);
			generateTH(data,action,TransactionFile,transactionType);
			generateETH(data,action);
			generateRTDTransactionHeader(data,TransactionFile,transactionType,action);
			generateTramTransactionBody(data,TransactionFile,transactionType,action);
			generateTransactionTrailer(action);
			generateFileTrailer(data,action);
			output.flush();
			output.close();
			action.successReport("Verify Exit Transaction TAP File Creation", "Exit Transaction TAP is created successfully");

		} catch (Exception e) {
			e.printStackTrace();
			action.failureReport("Verify Exit Transaction TAP File Creation", "Exit Transaction TAP is NOT created");
			throw new RuntimeException(e);
		}
		return FileName;

	}


	/**
	 * This method is used to generate a Void Tram Tap and returns the filename as a string when supplied with desired parameters.
	 * @param data
	 * @param TransactionFile
	 * @param entryTramStationName
	 * @param transactionType
	 * @param action
	 * @return
	 * @throws Throwable
	 */
	public String generateViodTramTap(Hashtable<String, String> data,String TransactionFile,String entryTramStationName,String transactionType,AppiumActions action) throws Throwable{
		String FileName= null;
		try {
			fileCounter=fileCounter+1;
			String TransactionHeaderFile = data.get("TransactionHeader");			
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);

			//String TransactionFile = data.get("TransactionFile");
			String TransactionData =FileUtil.readFile(TransactionFile);
			Object TransactionObject = JsonUtil.getJsonObject(TransactionData, "$."+transactionType+"");

			String StationName = JsonUtil.getJsonObjectElement(TransactionObject, ".StationName");
			String txnFileType = JsonUtil.getJsonElement(TransactionHeaderData, "$."+StationName+".TXNFileType");

			FileName = txnFileType+"_"+getCurrentdayKey()+"_000"+fileCounter+".dat";
			FileUtil.deleteSpecificFile(FileName);
			transactionFilePath = System.getProperty("user.dir")+"//"+FileName;

			output = new FileOutputStream(transactionFilePath, true);

			generateFileHeader(data,action,transactionType);
			generateRecordHeader(data,action,transactionType);
			generateTH(data,action,TransactionFile,transactionType);
			generateETH(data,action);
			generateRTDTransactionHeader(data,TransactionFile,transactionType,action);
			generateVoidTramTransactionBody(data,TransactionFile,entryTramStationName,transactionType,action);
			generateTransactionTrailer(action);
			generateFileTrailer(data,action);
			output.flush();
			output.close();
			action.successReport("Verify Exit Transaction TAP File Creation", "Exit Transaction TAP is created successfully");

		} catch (Exception e) {
			e.printStackTrace();
			action.failureReport("Verify Exit Transaction TAP File Creation", "Exit Transaction TAP is NOT created");
			throw new RuntimeException(e);
		}
		return FileName;

	}

	/**
	 * This method is used to generate a Bus Tap and returns the filename as a string when supplied with desired parameters.
	 * @param data
	 * @param TransactionFile
	 * @param transactionType
	 * @param action
	 * @return
	 * @throws Throwable
	 */
	public String generateBusTap(Hashtable<String, String> data,String TransactionFile,String transactionType,AppiumActions action) throws Throwable{
		String FileName= null;
		try {
			fileCounter=fileCounter+1;
			String TransactionHeaderFile = data.get("TransactionHeader");			
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);

			//String TransactionFile = data.get("TransactionFile");
			String TransactionData =FileUtil.readFile(TransactionFile);
			Object TransactionObject = JsonUtil.getJsonObject(TransactionData, "$."+transactionType+"");

			String StationName = JsonUtil.getJsonObjectElement(TransactionObject, ".StationName");
			String txnFileType = JsonUtil.getJsonElement(TransactionHeaderData, "$."+StationName+".TXNFileType");

			FileName = txnFileType+"_"+getCurrentdayKey()+"_000"+fileCounter+".dat";
			FileUtil.deleteSpecificFile(FileName);
			transactionFilePath = System.getProperty("user.dir")+"//"+FileName;

			output = new FileOutputStream(transactionFilePath, true);

			generateFileHeader(data,action,transactionType);
			generateRecordHeader(data,action,transactionType);
			generateBusTH(data,action,TransactionFile,transactionType);
			generateRTDBusTransactionHeader(data,TransactionFile,transactionType,action);
			generateBusTransactionBody(data,TransactionFile,transactionType,action);
			generateTransactionTrailer(action);
			generateFileTrailer(data,action);
			output.flush();
			output.close();
			action.successReport("Verify Exit Transaction TAP File Creation", "Exit Transaction TAP is created successfully");

		} catch (Exception e) {
			e.printStackTrace();
			action.failureReport("Verify Exit Transaction TAP File Creation", "Exit Transaction TAP is NOT created");
			throw new RuntimeException(e);
		}
		return FileName;

	}
	
	/**
	 * This method is used to generate a TKT transaction and returns the filename as a string when supplied with desired parameters.
	 * @param context
	 * @param data
	 * @param TransactionFile
	 * @param transactionType
	 * @param requestSequenceNumber
	 * @param selectedDate
	 * @param zoneRanges
	 * @param action
	 * @return
	 * @throws Throwable
	 */
	public String generateTKTTransaction(ITestContext context,Hashtable<String, String> data,String TransactionFile,String transactionType,String requestSequenceNumber,String selectedDate,String zoneRanges, AppiumActions action) throws Throwable{
		String FileName= null;
		try {
			fileCounter=fileCounter+1;
			String TransactionHeaderFile = data.get("TransactionHeader");			
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);

			//String TransactionFile = data.get("TransactionFile");
			String TransactionData =FileUtil.readFile(TransactionFile);
			Object TransactionObject = JsonUtil.getJsonObject(TransactionData, "$."+transactionType+"");

			String StationName = JsonUtil.getJsonObjectElement(TransactionObject, ".StationName");
			String txnFileType = JsonUtil.getJsonElement(TransactionHeaderData, "$."+StationName+".TXNFileType");

			FileName = txnFileType+"_"+getCurrentdayKey()+"_000"+fileCounter+".dat";
			FileUtil.deleteSpecificFile(FileName);
			transactionFilePath = System.getProperty("user.dir")+"//"+FileName;
			output = new FileOutputStream(transactionFilePath, true);
			if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")&&(selectedDate.contains("Start date"))) {
				selectedDate = (selectedDate.substring(11, selectedDate.length() - 1).trim());
			}
			generateFileHeader(data,action,transactionType);
			generateRecordHeader(data,action,transactionType);
			generateTH(data,action,TransactionFile,transactionType);
			generateETH(data,action);
			generateRTDTransactionHeader(data,TransactionFile,transactionType,action);
			generateTKTTransactionBody(data,transactionType,requestSequenceNumber,selectedDate,zoneRanges,action);
			generateTransactionTrailer(action);
			generateFileTrailer(data,action);
			output.flush();
			output.close();
			action.successReport("Verify Exit Transaction TAP File Creation", "Exit Transaction TAP is created successfully");

		} catch (Exception e) {
			e.printStackTrace();
			action.failureReport("Verify Exit Transaction TAP File Creation", "Exit Transaction TAP is NOT created");
			throw new RuntimeException(e);
		}
		return FileName;

	}

	/**
	 * This method is used to generate multiple TKT transactions and returns the filename as a string when supplied with desired parameters.
	 * @param context
	 * @param data
	 * @param TransactionFile
	 * @param transactionType
	 * @param requestSequenceNumber1
	 * @param requestSequenceNumber2
	 * @param selectedDate1
	 * @param selectedDate2
	 * @param zoneRanges1
	 * @param zoneRanges2
	 * @param action
	 * @return
	 * @throws Throwable
	 */
	public String generateMultipleTKTTransactions(ITestContext context,Hashtable<String, String> data,String TransactionFile,String transactionType,String requestSequenceNumber1,String requestSequenceNumber2,String selectedDate1,String selectedDate2,String zoneRanges1,String zoneRanges2, AppiumActions action) throws Throwable{
		String FileName= null;
		try {
			fileCounter=fileCounter+1;
			String TransactionHeaderFile = data.get("TransactionHeader");			
			String TransactionHeaderData =FileUtil.readFile(TransactionHeaderFile);

			//String TransactionFile = data.get("TransactionFile");
			String TransactionData =FileUtil.readFile(TransactionFile);
			Object TransactionObject = JsonUtil.getJsonObject(TransactionData, "$."+transactionType+"");

			String StationName = JsonUtil.getJsonObjectElement(TransactionObject, ".StationName");
			String txnFileType = JsonUtil.getJsonElement(TransactionHeaderData, "$."+StationName+".TXNFileType");

			FileName = txnFileType+"_"+getCurrentdayKey()+"_000"+fileCounter+".dat";
			FileUtil.deleteSpecificFile(FileName);
			transactionFilePath = System.getProperty("user.dir")+"//"+FileName;
			output = new FileOutputStream(transactionFilePath, true);
			if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")&&(selectedDate1.contains("Start date"))&&(selectedDate2.contains("Start date"))) {
				selectedDate1 = (selectedDate1.substring(11, selectedDate1.length() - 1).trim());
				selectedDate2 = (selectedDate2.substring(11, selectedDate2.length() - 1).trim());
			}
			generateFileHeader(data,action,transactionType);
			generateTKTRecordHeader(data,action,transactionType);
			generateTH(data,action,TransactionFile,transactionType);
			generateETH(data,action);
			generateRTDTransactionHeader(data,TransactionFile,transactionType,action);
			generateTKTTransactionBody(data,transactionType,requestSequenceNumber1,selectedDate1,zoneRanges1,action);
			generateTransactionTrailer(action);
			generateRTDTransactionHeader(data,TransactionFile,transactionType,action);
			generateTKTTransactionBody(data,transactionType,requestSequenceNumber2,selectedDate2,zoneRanges2,action);
			generateTransactionTrailer(action);
			generateFileTrailer(data,action);
			output.flush();
			output.close();
			action.successReport("Verify Exit Transaction TAP File Creation", "Exit Transaction TAP is created successfully");

		} catch (Exception e) {
			e.printStackTrace();
			action.failureReport("Verify Exit Transaction TAP File Creation", "Exit Transaction TAP is NOT created");
			throw new RuntimeException(e);
		}
		return FileName;

	}

	/**
	 * This method is used to verify whether the transactions are correctly exported to CS when supplied with desired parameters.
	 * @param data
	 * @param action
	 * @throws InterruptedException
	 */
	public void verifyTrasactionExportedToCS(Hashtable<String, String> data,AppiumActions action) throws InterruptedException{

		String cardNumber = data.get("PrestigeCardNumber");
		String NTMTxnSeqNumbr = Long.toString(NTMTxnSeqNo);
		String svBalance = Long.toString(SVBalance);
		int counter = 0;

		String ConnectionString = "SELECT  MAX(CARDSEQNO) FROM DD_CARDTRANSACTIONBATCH A, DD_CARDTRANSACTION B WHERE A. BATCHID = B . BATCHID AND B . PRESTIGEID = "+cardNumber+" AND B . DAYKEY = "+getCurrentdayKey()+" ORDER BY CARDSEQNO DESC";
		while (!(NTMTxnSeqNumbr.equalsIgnoreCase(getDBColumnValue("Oracle",ConnectionString, "MAX(CARDSEQNO)",100)))) {
			counter = counter +1;
			System.out.println(counter+" *** Fetching Record Dynamically from Database ***");
			if (counter==700) {
				//Thread.sleep(1000);
				break;
			}
			else{
				continue;
			}

		}
		if (NTMTxnSeqNumbr.equalsIgnoreCase(getDBColumnValue("Oracle",ConnectionString, "MAX(CARDSEQNO)",100))) {
			String ConnectionString2 = "SELECT  * FROM DD_CARDTRANSACTIONBATCH A, DD_CARDTRANSACTION B WHERE A. BATCHID = B . BATCHID AND B . PRESTIGEID = "+cardNumber+" AND B . DAYKEY = "+getCurrentdayKey()+"AND CARDSEQNO ="+NTMTxnSeqNumbr+"";
			String FileStatus = getDBColumnValue("Oracle",ConnectionString2, "STATEDESCRIPTION");
			System.out.println("FILE status is >>>>>>>>>>>>>>>>>>> "+FileStatus);
			String FinalBalance = getDBColumnValue("Oracle",ConnectionString2, "PAYGBALANCE");
			while(FileStatus==null||!(FileStatus.equalsIgnoreCase("EXPORTED"))){
				counter = counter +1;
				FileStatus = getDBColumnValue("Oracle",ConnectionString2, "STATEDESCRIPTION");
				System.out.println(counter+". FILE status After is <<<<<<<<<<<<<< "+FileStatus);
				if (counter==1000) {
					//Thread.sleep(1000);
					break;
				}
				else{
					continue;
				}
			}
			if (FileStatus.equalsIgnoreCase("EXPORTED")&&FinalBalance!=null&&FinalBalance.equalsIgnoreCase(svBalance)) {
				System.out.println("**** RECORD EXPORTED SUCCESSFULLY ****");
				action.successReport("Verify the Transaction Record Status in the CS Database", "Transaction status is marked as 'EXPORTED' and sent successfully to OYBO servers and balanace is updated to '"+svBalance+"'");

			}
			/*else if (FileStatus.equalsIgnoreCase("EXPORTED")) {
				System.out.println("**** RECORD EXPORTED SUCCESSFULLY ****");
				action.successReport("Verify the Transaction Record Status in the CS Database", "Transaction status is marked as 'EXPORTED' and sent successfully to OYBO servers and balanace is updated to '"+svBalance+"'");

			}*/
			else if(FileStatus.equalsIgnoreCase("EXPORTED")&&ivalTxnType!=null&&ivalTxnType.equalsIgnoreCase("IVAL")){
				System.out.println("**** RECORD EXPORTED SUCCESSFULLY ****");
				action.successReport("Verify the Transaction Record Status in the CS Database", "Transaction status is marked as 'EXPORTED' and sent successfully to OYBO servers '");
			}
			else{
				System.out.println("**** RECORD NOT EXPORTED ****");
				action.failureReport("Verify the Transaction Record Status in the CS Database", "Transaction status is NOT marked as 'EXPORTED' and NOT sent to OYBO servers");

			}
		}
	}

	/**
	 * This method is used to verify whether the transactions are correctly exported to OYBO when supplied with desired parameters.
	 * @param data
	 * @param action
	 * @return
	 */
	public HashMap<String, String>  verifyTrasactionExportedToOYBO(Hashtable<String, String> data,AppiumActions action){
		String fileReceivedID ="";
		int counter = 0;
		try {
			String cardNumber = data.get("PrestigeCardNumber");
			/*if (ivalTxnType!=null&&ivalTxnType.equalsIgnoreCase("IVAL")) {
				NTMTxnSeqNo = NTMTxnSeqNo -1;
			}*/
			String NTMTxnSeqNumbr = Long.toString(NTMTxnSeqNo);
			String svBalance = Long.toString(SVBalance);

			String fetchTransactionSeqNoConnectionString = "SELECT * FROM DD_CardTransaction WHERE prestigeid = '"+cardNumber+"' and CARDSEQNO = "+NTMTxnSeqNumbr+" and DAYKEY = "+getCurrentdayKey()+"";
			String transactionSeqNumber = getDBColumnValue("Oracle",fetchTransactionSeqNoConnectionString, "TXNSEQNO");
			String SqlOyboConnectionString = "select * from otfp.OysterTransaction where PrestigeID = "+cardNumber+" and TxnSeqNo="+transactionSeqNumber+"";
			fileReceivedID = getDBColumnValue("Sqlserver",SqlOyboConnectionString, "FileReceivedId",100);

			while(fileReceivedID==null){
				counter ++;
				fileReceivedID = getDBColumnValue("Sqlserver",SqlOyboConnectionString, "FileReceivedId");
				System.out.println(counter + ".fileReceivedID After is <<<<<<<<<<<<<< "+fileReceivedID);
				if (counter==400) {
					//Thread.sleep(1000);
					break;
				}
				else{
					continue;
				}
			}
			if (fileReceivedID!=null) {
				hmap.put("FileReceivedId", fileReceivedID);
			}
			String oybo_ntmSeqNo = getDBColumnValue("Sqlserver",SqlOyboConnectionString, "CardSeqNo");
			String oybo_svBalance = getDBColumnValue("Sqlserver",SqlOyboConnectionString, "PAYGBalance");
			if (oybo_ntmSeqNo!=null&&oybo_ntmSeqNo.equalsIgnoreCase(NTMTxnSeqNumbr)&&oybo_svBalance!=null&&oybo_svBalance.equalsIgnoreCase(svBalance)){
				hmap.put("FinalBalance", svBalance);
				System.out.println("**** RECORD EXPORTED TO OYBO SUCCESSFULLY ****");
				action.successReport("Verify the Transaction Record Status in the OYBO servers", "Transaction record is successfully Received to OYBO serevers and balanace is updated to '"+oybo_svBalance+"'");
			}
			else if(oybo_ntmSeqNo!=null&&oybo_ntmSeqNo.equalsIgnoreCase(NTMTxnSeqNumbr)){
				hmap.put("FinalBalance", svBalance);
				System.out.println("**** RECORD EXPORTED TO OYBO SUCCESSFULLY ****");
				action.successReport("Verify the Transaction Record Status in the OYBO servers", "Transaction record is successfully Received to OYBO serevers and balanace is updated to '"+svBalance+"'");
			}
			else{
				System.out.println("**** RECORD NOT EXPORTED ****");
				action.failureReport("Verify the Transaction Record Status in the OYBO servers", "Transaction records were NOT  Received to OYBO servers");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
			// TODO: handle exception
		}
		return hmap;

	}

	/**
	 * This method is used to verify whether the transactions are correctly exported to mobile when supplied with desired parameters.
	 * @param data
	 * @param action
	 * @param fileReceivedID
	 */
	public void  verifyFileSentToMobile(Hashtable<String, String> data,AppiumActions action,String fileReceivedID){
		try {
			int counter = 0;
			String ConnectionString = "select * from otfp.FileSentToMobile where FileReceivedId = "+fileReceivedID+"";
			String completed = getDBColumnValue("Sqlserver",ConnectionString, "completed");
			System.out.println("completed value is "+completed);
			while(completed==null||completed.equalsIgnoreCase("0")){
				counter ++;
				completed = getDBColumnValue("Sqlserver",ConnectionString, "completed");
				System.out.println(counter + ". completed value is "+completed);
				if (counter==400) {
					//Thread.sleep(1000);
					break;
				}
				else{
					continue;
				}

			}
			if (completed!=null&&completed.equalsIgnoreCase("1")) {
				System.out.println("**** FILE  EXPORTED TO MOBILE SUCCESSFULLY ****");
				action.successReport("Verify the FILE status in the OYBO servers", "File sent to Mobile successfully from the OYBO Servers");

			}
			else{
				System.out.println("**** FILE NOT EXPORTED  ****");
				action.failureReport("Verify the FILE status in the OYBO servers", "File NOT sent to Mobile from the OYBO Servers");

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}


	/**
	 * This method is used to verify whether the file is correctly copied to DGC when supplied with desired parameters.
	 * @param sourceFileName
	 * @param destinationPath
	 * @param action
	 * @throws InterruptedException
	 */
	public void copyFileToDGC(String sourceFileName, String destinationPath,AppiumActions action) throws InterruptedException{
		//File source = new File(System.getProperty("user.dir")+"//"+sourceFileName);
		//File dest = new File(destinationPath+"//"+sourceFileName);
		final String sourcePath = System.getProperty("user.dir")+"//"+sourceFileName;
		final String destPath = destinationPath+"//"+sourceFileName;
		final String userName = AppConstants.DGCAccessUserName;
		final String password = AppConstants.DGCAccessPassword;
		final String domain = AppConstants.DGCDomainName;
		try {
			copyFileUsingJcifs(domain,userName, password, sourcePath, destPath);
			action.successReport("Verify Transaction File Copied to DGC", "Transaction file has been copied to DGC successfully");
		} catch (IOException e) {
			e.printStackTrace();
			action.failureReport("Verify File Copied to DGC", "Transaction file is NOT copied to DGC");
		}
		finally{
			FileUtil.deleteSpecificFile(sourcePath);
		}
	} 

	/**
	 * This method is used to copy the when supplied with desired parameters.
	 * @param domain
	 * @param userName
	 * @param password
	 * @param sourcePath
	 * @param destinationPath
	 * @throws IOException
	 */
	public void copyFileUsingJcifs(final String domain,final String userName,final String password, final String sourcePath,final String destinationPath) throws IOException {

		try {
			final NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(domain, userName, password);
			final SmbFile sFile = new SmbFile(destinationPath, auth);
			final SmbFileOutputStream smbFileOutputStream = new SmbFileOutputStream(sFile);
			final FileInputStream fileInputStream = new FileInputStream(new File(sourcePath));
			final byte[] buf = new byte[16 * 1024 * 1024];
			int len;
			while ((len = fileInputStream.read(buf)) > 0) {
				smbFileOutputStream.write(buf, 0, len);
			}
			fileInputStream.close();
			smbFileOutputStream.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}


	/**
	 * This method is used to verify whether the file is correctly copied to BUS DGC when supplied with desired parameters.
	 * @param sourcePath
	 * @param destinationPath
	 * @param action
	 */
	public void copyFileToBUSDGC(String sourcePath, String destinationPath,AppiumActions action){
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
	
	/**
	 * This method is used to verify web agency code and get the request sequence number and return the same as a string when supplied with desired parameters.
	 * @param data
	 * @param action
	 * @return
	 */
	public String verifyWebAgencyCodeAndGetRequestSequenceNumber(Hashtable<String, String> data,AppiumActions action){
		String requestSeqNo = null;
		try {
			String cardNumber = data.get("PrestigeCardNumber");
			/*if(context.getCurrentXmlTest().getXmlClasses().toString().contains("TC_FULPAYGTKT")){
				requestSeqNo  = getDBColumnValue("Oracle","select * from cdsprod1.ful_pendingrequests where prestigeid = '"+cardNumber+"' order by REQUESTSEQNO DESC", "REQUESTSEQNO",45);
			}else {
				requestSeqNo  = getDBColumnValue("Oracle","select * from cdsprod1.ful_pendingrequests where prestigeid = '"+cardNumber+"' and status = 'Not Sent' order by REQUESTSEQNO DESC", "REQUESTSEQNO",45);
			}*/
			requestSeqNo  = getDBColumnValue("Oracle","select * from cdsprod1.ful_pendingrequests where prestigeid = '"+cardNumber+"' and status = 'Not Sent' order by REQUESTSEQNO DESC", "REQUESTSEQNO",45);
			String webAgencyCode = getDBColumnValue("Oracle","select * from cdsprod1.ful_pendingrequests where prestigeid = '"+cardNumber+"' and status = 'Not Sent' order by REQUESTSEQNO DESC", "WEBAGENCYCODE",45);
			if (webAgencyCode.equalsIgnoreCase("7")) {
				action.successReport("Verify webAgency Code", "Web Agency code verified successfully ");			
			}
			else{
				action.failureReport("Verify webAgency Code", "Web Agency code is NOT verified");
			}
			String prestigeID = getDBColumnValue("Oracle","select * from cdsprod1.ful_pendingrequests where prestigeid = '"+cardNumber+"' and status = 'Not Sent' order by REQUESTSEQNO DESC", "PRESTIGEID",45);
			if (prestigeID.equalsIgnoreCase(cardNumber)) {
				action.successReport("Verify prestigeID", "prestigeID verified successfully and displayed as "+prestigeID);			
			}
			else{
				action.failureReport("Verify prestigeID", "Prestige ID not verified");
			}

			if (requestSeqNo==null) {
				requestSeqNo  = getDBColumnValue("Oracle","select * from cdsprod1.ful_pendingrequests where prestigeid = '"+cardNumber+"' and status = 'Pending' order by REQUESTSEQNO DESC", "REQUESTSEQNO",45);			
			}


		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("The request sequence number is :::: " + requestSeqNo);
		return requestSeqNo;

	}

	/**
	 * This method is used to verify the current available balance on the card and creates journey scenarios when the balance is greater than 60 when supplied with desired parameters.
	 * @param context
	 * @param data
	 * @param action
	 * @param balance
	 * @throws Throwable
	 */
	public void verifyBalanceLimit(ITestContext context,Hashtable<String, String> data,AppiumActions action,  String balance) throws Throwable{
		int actualFinalBalance = 0;
		try {		
			if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("android")){
				balance = action.getText(HomePage.oysterCardBalance, "oysterCardBalance");
			}
			String[] output = balance.split("\\.");
			if(output[0].contains("£")) {
				String[] output1 =output[0].split("£");
				actualFinalBalance = Integer.parseInt(output1[1]);
			}else {
				actualFinalBalance = Integer.parseInt(output[0]);
			}
			
			System.out.println("Acutal Final Balance is :::: " + actualFinalBalance);
			if (actualFinalBalance>=60) {
				action.successReport("Verify the existing balance limit in the card", "Balance limit is more than 60 hence executing the balance deduction program");
				for (int i = 0; i < 2; i++) {
					createJourneyScenarios(data, action);
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * This method is used to create Journey Scenarios when supplied with desired parameters.
	 * @param data
	 * @param action
	 * @throws Throwable
	 */
	public void createJourneyScenarios(Hashtable<String, String> data,AppiumActions action) throws Throwable{
	HashMap<String, String> txnDetailsMap = new HashMap<String, String>();
		try {
			System.out.println("************** Creating top up deduction program ***************");
			String EntryTransactionType = "Entry";
			String ExitTransactionType = "Exit";
			String txnFilePath = "";
			OysterCreateTransaction txn = new OysterCreateTransaction();
			String TransactionFile = System.getProperty("user.dir")+"//TestData//PAYGTransactionSet//PAYGGatwickEntry.json";
			String TransactionData = FileUtil.readFile(TransactionFile);
			Object EntryTransactionObject = JsonUtil.getJsonObject(TransactionData, "$.Entry");
			String entryStationName = JsonUtil.getJsonObjectElement(EntryTransactionObject, ".StationName");
			try {
				txnFilePath = txn.generateEntryTransaction(data,TransactionFile,entryStationName,EntryTransactionType,action);
				txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
				txnFilePath = txn.generateExitTransactionAutoTopUpDeduction(data,TransactionFile,ExitTransactionType,action);
				txn.copyFileToDGC(txnFilePath, AppConstants.DGC_PATH,action);
				txn.verifyTrasactionExportedToCS(data,action);
				/*System.out.println("Waiting for sql server connection");
				Thread.sleep(2*60*1000);*/
				txnDetailsMap = txn.verifyTrasactionExportedToOYBO(data,action);
				txn.verifyFileSentToMobile(data, action,txnDetailsMap.get("FileReceivedId"));

			} catch (Exception e) {
				// TODO: handle exception
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally{
			clearTransactionGeneratorStaticVariables();
		}
		
	}

	/**
	 * This method is used to verify the current available balance on the card and returns the balance as a int when supplied with desired parameters.
	 * @param context
	 * @param data
	 * @param action
	 * @return
	 * @throws Throwable
	 */
	public int returnBalance(ITestContext context,Hashtable<String, String> data,AppiumActions action) throws Throwable{
		int ActualfinalBalance = 0;
		try {
			String TransactionHeaderFile = data.get("TransactionHeader");
	String TransactionData =FileUtil.readFile(TransactionHeaderFile);
	String cardNumber = data.get("PrestigeCardNumber");
	
	HomePage.homePageLocators(context); 
	String oysterCardVisibleNumberOnApp = "000"+cardNumber+JsonUtil.getJsonElement(TransactionData, "$."+cardNumber+".NTMNoCheckDigits");
	action.swipeFromMidScreenToBottom();
	List<String> myList = new ArrayList<String>();
	if (context.getCurrentXmlTest().getParameter("platformName").equalsIgnoreCase("iOS")){
		finalBalance = OysterPayAsYouGo.SwipeToNextCardiOS(context, data, action, oysterCardVisibleNumberOnApp);
		action.swipeFromMidScreenToBottom();
	}
	else {
		for (int i = 0; i < 6; i++){
			if (!(action.getText(HomePage.oysterCardNumber, "oysterCardNumber").equalsIgnoreCase(oysterCardVisibleNumberOnApp))) {
			action.swipeRightToLeft();
		}
		else{
			break;
		}
	}
	finalBalance = action.getText(HomePage.oysterCardBalance, "oysterCardBalance");
	}
	String[] output = finalBalance.split("\\.");
	if(output[0].contains("£")) {
		String[] output1 =output[0].split("£");
		ActualfinalBalance = Integer.parseInt(output1[1]);
	}else {
		ActualfinalBalance = Integer.parseInt(output[0]);
	}
	
	System.out.println("Acutal Final Balance is :::: " + ActualfinalBalance);
	} catch (Exception e) {
		// TODO: handle exception
			}
			return ActualfinalBalance;
	}
	
	/**
	 * This method is used to clear all variables created during the class.  
	 */
	public void clearTransactionGeneratorStaticVariables(){
		creationDate =0;
		creationTime =0;

		//Record Header
		RecordType =0;
		RecordNumber =0;
		RecordLength =0;

		//TH
		TransactionDate =0;
		PrestigeStationNumber =0;
		TicketHall =0;
		DeviceLocation =0;
		DeviceType =0;
		Generation =0;
		PlinthNumber =0;
		SpecialResetCount =0 ;
		TransactionNumber =0;
		OperatorIdentity =0;
		TransactionTime =0;
		TransactionType=0;

		//Bus TH
		BusTransactionType=0;
		BusReaderTransactionType=0;
		BusTransactionNumber=0;
		BusTransactionLength=0;
		// TransactionDate = 13798=0;
		// TransactionTime = 834=0;
		ReaderID=0;
		ControllerID =0;
		BusReaderPosition=0;
		ControllerTrayID=0;
		ReaderContainerID=0;
		GarageNumber=0;
		//	FleetNumber=0;
		//	RegistrationNumber=0;
		IncidentNumber=0;

		//ETH
		TransactionSubType =0;
		LinkingSequenceNo  =0;
		LinkingStatus  =0;

		//RTD Transaction Header
		txnType=0;
		txnTypeV6=0;
		TestTrainingFlag=0;
		CurrencyType=0;
		fulFlag=0;
		padding=0;
		disablePPToverLoading=0;
		disableSVOverriding=0;
		ntmCardUpdated=0;
		overallTxnlength=0;
		currentTxnLength=0;
		txnDate=0;
		txnTime=0;
		NTMno=0;
		NTMNoCheckDigits=0;
		CSN =0;
		NTMTxnSeqNo=0;
		RTDID =0;
		RTDTxnSeqNo=0;
		plinthNumber=0;
		NLC=0;
		deviceType=0;
		SubSystemID=0;
		TxnTimeSecs=0;
		TxnPerfTime=0;
		PhysicalCardType=0;
		LinkingSeqNo=0;
		FULCurrentASN=0;
		FULASNStatuses =0;
		PreviousTranSummaries=0;

		//RTD Bus Transaction Header
		//	busRHD0= 0;
		//	Bit7 =0;
		txnTypeBus=0;
		txnTypeV5=0;
		busRHD4Bit4 =0;	
		busReaderTimeOffLine =0;
		//busRouteID=0;
		busBoardingFareStageNo=0;
		busStopIDStatus=0;
		busDirectionOfTravel=0;
		busTripType=0;
		busTripNumber=0;
		busRHD35=0;

		//Transaction Body
		resultCode=0;
		stationNLC=0;
		fullEntryAdjustmentDeducted=0;
		entryAdjustmentDeducted=0;
		SVBalance=0;
		autoContinutionFlag=0;
		autoCompletionFlag=0;
		osiEntryFlag=0;
		svInvlovedFlag=0;
		ppt3UsedFlag=0;
		ppt2UsedFlag=0;
		ppt1UsedFlag=0;
		tokenExit=0;
		sparebit14=0;
		previousValidationErrorFlag=0;
		unStartedPPTExitFlag=0;
		unCompletedPPTEntryFlag=0;
		exitChargeAppliedFlag=0;
		passbackAuthorised =0;
		dailyCapping =0;
		TransferDiscountApp=0;
		PPTValidityPageCRC=0;
		passengerType=0;
		discountType=0;
		zoneRangeCode=0;
		cappingSceme=0;
		pptProductCode=0;
		oepUsedFlag=0;
		differentStationRegion=0;
		sameStationReentryFlag=0;
		relevantRoutingNodeFlag=0;
		zonalIndicator=0;
		spareBit35=0;
		tktDiscountApplied=0;
		oysterFareType=0;
		crossDayJourneyFlag=0;
		travelSetting=0;

		//PAYG Transaction Body
		RequestSeqNo=0;
		svAdded=0;
		paymentMethod=0;

		//EXIT Transaction Body
		fullExitAdjustmentDeducted=0;
		exitAdjustmentDeducted=0;
		PPT1ValidityPageCRC=0;
		PPT2ValidityPageCRC=0;
		PPT3ValidityPageCRC=0;
		timeOfFirstEntry=0;
		spareByte=0;
		zonalValidity=0;
		fullFare=0;
		discountedFare=0;
		pptPassengerType=0;
		twoBitFlag=0;
		sameStationExitFlag=0;

		//TKT Transaction Body
		TKTRequestSeqNo=0;
		TKTPPTSlotNo=0;
		TKTPPTValidityPageCRC=0;
		TKTPassengerType=0;
		TKTDiscountApplied=0;
		TKTPaymentMethod=0;
		TKTProductCode=0;
		TKTProductID=0;
		TKTRTVCalendarMonths=0;
		TKTRTVDays=0;
		TKTRTVPricingStructure=0;
		TKTChargeBasis =0;
		TKTRTVSelector=0;
		TKTRTVReportingSegmentation=0;
		TKTPassengerTypeAge=0;
		TKTTicketClass=0;
		TKTZonalValidity=0;
		TKTRoute=0;
		TKTDestination=0;
		TKTTravelcardZones=0;
		TKTMediumType=0;
		TKTTimeRestriction=0;
		TKTDiscount=0;
		TKTStartDate=0;
		TKTExtraDays=0;
		TKTspareBit=0;

		//TRAM Transaction Body
		//routeID=0;

		//Void Tram Transaction Body
		TramStation=0;
		EntryAdjustmentRestored=0;
		ExitChargeAppliedFlag=0;
		EntryAdjustmentBeforeDiscount=0;

		//Void Entry Body
		FullEntryAdjustment=0;
		stationOfFirstEntry=0;





		//IVAL Transaction Body
		stationOfFirstEntryNLC=0;
		timeOfFirstEntryIVAL=0;
		R65_3_2BitFlag=0;
		CrossDayJourneyFlag=0;
		RelevantRoutingNodeFlag=0;
		ZonalIndicator=0;

		//TransactionTrailer
		MAC=0;

		//Record Header for File Trailer
		fileTrailerRecordType=0;
		fileTrailerRecordNumber=0;
		fileTrailerRecordLength=0;

		//File Trailer
		completionDate=0;
		completionTime=0;
		numberofRecords=0;
		NTMTxnSeqNo = 0;

		firstEntryTime = 0;
		ivalTxnType =  null;
		entryStationName = null;
	}
	/*public OysterCreateTransaction(){
		try {
			file = new File(System.getProperty("user.dir")+"\\outputBinary.dat");
			//output = new FileOutputStream(System.getProperty("user.dir")+"\\tra_"+getCurrentdayKey()+"_0001.dat", true);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}*/






}
