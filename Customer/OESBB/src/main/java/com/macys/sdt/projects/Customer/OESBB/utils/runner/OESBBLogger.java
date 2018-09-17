package com.macys.sdt.projects.Customer.OESBB.utils.runner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;

import org.apache.commons.lang3.StringUtils;

import cucumber.api.Scenario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OESBBLogger {

	private static String path = "log/";
	private static String payloadPath = path + "payloads/";
	private static String logFileName = path + "Logger.csv";
	private static File logFile = null;
	
	private static String[] config = {null,null,null,null,null,null,null,null,null,null};
	private static String[] header = {"Sno","Template","Source","Type","SubType","Brand","Payload","SourceId","Modification","Status"};
	public static int SNO=0, TEMPLATE=1, SOURCE=2, TYPE=3, SUBTYPE=4, BRAND=5, PAYLOAD=6, SOURCEID=7, MODIFICATION=8, STATUS=9; 
	
	private static String[] currentLine = config;

	private static final Logger log = LoggerFactory.getLogger(OESBBLogger.class);
	
	private static void intializeLogFile() {
		currentLine = config;
		try {
			File directory = new File(path);
		    if (! directory.exists()){
		        directory.mkdir();
		    }
		    
		    directory = new File(payloadPath);
		    if (! directory.exists()){
		        directory.mkdir();
		    }
		    
		    logFile = new File(logFileName);
		    if (!logFile.exists()) {
			     logFile.createNewFile();
			     out(header);
		    }
		} catch (IOException e) {
	         System.out.println("exception occoured"+ e);
			 log.error("exception occoured"+ e);
	    }
	}
	
	public static void savePayload(String guid, String payload) {
		if (logFile == null) {
			intializeLogFile();
		}
		try {
			String payloadFilePath = payloadPath + guid + ".xml";
			File payloadFile = new File(payloadFilePath);
			if (payloadFile.exists()) {
				payloadFile.delete();
			}
		    BufferedWriter out = new BufferedWriter(new FileWriter(payloadFile,true));
		    out.write(payload);
			out.close();
		} catch (IOException e) {
			System.out.println("exception occoured"+ e);
			log.error("exception occoured"+ e);
	    }
	}
	
	public static void deletePayload(String guid) {
		String payloadFilePath = payloadPath + guid + ".xml";
		File payloadFile = new File(payloadFilePath);
		if (payloadFile.exists()) {
			payloadFile.delete();
		}
	}
	
	public static String getNow() {
		ZonedDateTime zdt = ZonedDateTime.now();
		String tmp = zdt.getYear() + String.format("%02d", zdt.getMonthValue()) + String.format("%02d", zdt.getDayOfMonth()) + "_" + zdt.getHour() + ":" + zdt.getMinute() + ":" + zdt.getSecond();
		return tmp;
	}
	
	public static void out(Scenario scenario) {
		if (!scenario.isFailed()) {
			setCurrentConfiguration(STATUS,scenario.getStatus());
			//deletePayload(currentLine[SOURCEID]);
		}
		out(currentLine);
		currentLine = config;
	}
	
	private static void out(String[] output) {
		if (logFile == null) {
			intializeLogFile();
		}
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(logFile,true));		
			out.write(StringUtils.join(output,','));
			out.newLine();
			out.close();
		}
		catch (IOException e) {
			System.out.println("exception occoured"+ e);
			log.error("exception occoured"+ e);
	    }
	}
	
	public static boolean configurationFailedPreviousNone() {
		if (logFile == null) {
			return false;
		}
		boolean retv = false;
		String[] c = currentLine;
		try {
				BufferedReader in = new BufferedReader(new FileReader(logFile));
				while (!retv && in.ready()) {
					String[] l = in.readLine().split(",");	
					if (l[SNO].equals(c[SNO]) &&
						l[TEMPLATE].equals(c[TEMPLATE]) && 
						l[SOURCE].equals(c[SOURCE]) && 
						l[TYPE].equals(c[TYPE]) && 
						l[SUBTYPE].equals(c[SUBTYPE]) && 
						l[BRAND].equals(c[BRAND]) && 
						l[PAYLOAD].equals(c[PAYLOAD]) && 
						l[MODIFICATION].equals("MOD-0") &&
						!l[STATUS].equals("passed")) {
						retv = true;
						currentLine[STATUS] = "FAILED_PREVIOUS_NONE";
					}
				}
				in.close();
		}
		catch (IOException e) {
	         System.out.println("exception occoured"+ e);
	         log.error("exception occoured"+ e);
	    } 
		return retv;
	}
	
	public static void setCurrentConfiguration(int location, String value) {
		currentLine[location] = value;
	}
	
	public static void setCurrentConfiguration(String[] config) {
		for (int i=0; i<config.length; i++) {
			if (config[i] != null) {
				currentLine[i] = config[i];
			}
		}
	}
	
}