package com.macys.sdt.projects.Customer.OESBB.utils.runner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PayloadXml {

	private static final Logger log = LoggerFactory.getLogger(PayloadXml.class);

	public static String getXmlFromFile(String path) {
		String retv = null;
		
		try {
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(new FileReader(path));
			while (br.ready()) {
				sb.append(br.readLine().trim());
			}
			br.close();
			retv = sb.toString();
		} catch (Exception e) {
			retv = "UNABLE_TO_READ_FILE";
		}
		
		return retv;
	}
	
	private static String replaceXML(String xml, String find, String replace) {
		Pattern p = Pattern.compile(find,Pattern.DOTALL); //DOTSLL is for multi line but currently have stripped all new lines
		Matcher m = p.matcher(xml);
		return m.replaceAll(replace);
	}

	private static String clearTag(String xml, String tagName, boolean remove) {
		log.debug("clearTag(xml," + tagName + "," + remove + ")");
		String find = "<TAG_NAME>.*?</TAG_NAME>".replaceAll("TAG_NAME", tagName);
		String replace = "<TAG_NAME></TAG_NAME>".replaceAll("TAG_NAME", tagName);
		if (remove) {
			replace = "";
		}
		xml = replaceXML(xml, find, replace);

		find = "<TAG_NAME/>".replaceAll("TAG_NAME", tagName);
		replace = "<TAG_NAME></TAG_NAME>".replaceAll("TAG_NAME", tagName);
		if (remove) {
			replace = "";
		}
		return replaceXML(xml, find, replace);
	}

	private static String clearTagElement(String xml, String tagName, String elementName, boolean remove) {
		log.debug("clearTagElement(xml," + tagName + "," + elementName + "," + remove + ")");
		String find = "<TAG_NAME>(.*?)<ELEMENT_NAME>.*?</ELEMENT_NAME>(.*?)</TAG_NAME>".replaceAll("TAG_NAME", tagName).replaceAll("ELEMENT_NAME", elementName);
		String replace = "<TAG_NAME>$1<ELEMENT_NAME></ELEMENT_NAME>$2</TAG_NAME>".replaceAll("TAG_NAME", tagName).replaceAll("ELEMENT_NAME", elementName);
		if (remove) {
			replace = "<TAG_NAME>$1$2</TAG_NAME>".replaceAll("TAG_NAME", tagName);
		}
		xml = replaceXML(xml, find, replace);

        find = "<TAG_NAME>(.*?)<ELEMENT_NAME/>(.*?)</TAG_NAME>".replaceAll("TAG_NAME", tagName).replaceAll("ELEMENT_NAME", elementName);
		replace = "<TAG_NAME>$1<ELEMENT_NAME></ELEMENT_NAME>$2</TAG_NAME>".replaceAll("TAG_NAME", tagName).replaceAll("ELEMENT_NAME", elementName);
		if (remove) {
			replace = "<TAG_NAME>$1$2</TAG_NAME>".replaceAll("TAG_NAME", tagName);
		}

		return replaceXML(xml, find, replace);
	}
	
	private static String clearTagElements(String xml, String tagName, String[] elementNames, boolean remove) {
		for(int i=0; i<elementNames.length; i++) {
			xml = clearTagElement(xml, tagName, elementNames[i], remove);
		}
		return xml;
	}
	
	public static String modifyXml(String xml, String mod) throws Throwable {
		String[] mods = mod.split(":");
		String op = mods[0];
		
		switch (op) {
			case "MOD-0": //none
				break;
			case "MOD-1": //clearPaymentItemElements
				String[] clearPaymentElementNames = {"paymentType","accountNumber","amount","paymentCategoryFlag","egcCertificateType"};
				xml = clearTagElements(xml, "paymentDetail", clearPaymentElementNames, false);
			    String[] clearItemElementNames = {"description","price","requestedQuantity","availableQuantity","shippedQuantity","totalAmount",
	                    "expectedDate","shipmentType","status","vendor","upcId","returnExpectedDate","size","color"};
			    xml = clearTagElements(xml, "itemDetail", clearItemElementNames, false);
				break;
			case "MOD-2": //remove_billingDetail
				xml = clearTag(xml, "billingDetail", true);
				break;
			case "MOD-3": //remove_paymentDetail
				xml = clearTag(xml, "payentDetail", true);
				break;
			case "MOD-4": //remove_shipmentDetail
				xml = clearTag(xml, "shipmentDetail", true);
				break;
			case "MOD-5": //removeItemAndTracking
				String[] shipmentElementNames2 = {"itemDetail","trackingDetail"};
				xml = clearTagElements(xml, "shipmentDetail", shipmentElementNames2, true);
				break;
			case "MOD-6": //removePaymentShipmentItemTrackingElements
				String[] paymentElementNames = {"amount","paymentCategoryFlag","egcCertificateType"};
				xml = clearTagElements(xml, "paymentDetail", paymentElementNames, true);
				String[] shipmentElementNames = {"returnSubmittedDate","giftReceiptFlag"};
				xml = clearTagElements(xml, "shipmentDetail", shipmentElementNames, true);
				String[] itemElementNames = {"color","size","description","upcId"};
				xml = clearTagElements(xml, "itemDetail", itemElementNames, true);
				String[] trackingElementNames = {"trackingNumber","numberofPackages"};
				xml = clearTagElements(xml, "trackingDetail", trackingElementNames, true);
				break;
			case "MOD-11":
				xml = clearOptionalTags(xml, mods[1], 1, true);
				break;
			case "MOD-12":
				xml = clearOptionalTags(xml, mods[1], 2, true);
				break;
			case "MOD-13":
				xml = clearOptionalTags(xml, mods[1], 3, true);
				break;
			case "MOD-21":
				xml = clearOptionalTags(xml, mods[1], 1, false);
				break;
			case "MOD-22":
				xml = clearOptionalTags(xml, mods[1], 2, false);
				break;
			case "MOD-23":
				xml = clearOptionalTags(xml, mods[1], 3, false);
				break;
			default:
				xml = "UNKNOWN_MODIFICATION:(" + op + ")";
				break;
		}
		
		return xml;
	}
	
	public static String clearOptionalTags(String xml, String sno, int targetLevel, boolean remove) throws Throwable {
		JSONObject json = getJsonFromFile("log/auto_templates.json");
		json = json.getJSONObject("specifications");
		json = json.getJSONObject(sno);
		json = json.getJSONObject("hierarchy");
		xml = clearOptionalTagsAtLevel(json,xml,null,targetLevel,1, remove);
		return xml;
	}
	
	public static String clearOptionalTagsAtLevel(JSONObject json, String xml, String parent, int target, int level, boolean remove) throws Throwable {
		if (level > target) {
			log.debug("clearOptionalTagsAtLevel::LeveL_ERROR");
		} else if (level == target) {
			xml = clearOptionalTags(json,xml,parent,remove);
		} else {
			JSONObject elements = json.getJSONObject("elements");
			JSONArray names = elements.names();
			for(int i=0; i<names.length(); i++){
				String name = names.getString(i);
		 		JSONObject obj = elements.optJSONObject(name);
		 		if (!(obj == null)) {
		 			xml = clearOptionalTagsAtLevel(obj, xml, name, target, level+1, remove);
		 		}
			}
		}
		return xml;
	}
		
    public static String clearOptionalTags(JSONObject json, String xml, String parent, boolean remove) throws Throwable {
    	JSONObject elements = json.getJSONObject("elements");
		JSONArray names = elements.names();
		for(int i=0; i<names.length(); i++){
			String name = names.getString(i);
	 		JSONObject obj = elements.optJSONObject(name);
	 		Boolean isRequired;
	 		if (obj == null) {
	 			isRequired = elements.getBoolean(name);
	 		} else {
	 			isRequired = elements.getJSONObject(name).getBoolean("required");
	 		}
	 		if(!isRequired) {
		 		if(parent == null) {
		 			xml = clearTag(xml,name,remove);
		 		} else {
		 			xml = clearTagElement(xml,parent,name,remove);
		 		}
	 		}
		}
		return xml;
	}
	
	public static JSONObject getJsonFromFile(String path) throws Throwable {
		JSONObject json = null;
		
		try {
			StringBuilder sb = new StringBuilder();
			BufferedReader br = new BufferedReader(new FileReader(path));
			while (br.ready()) {
				sb.append(br.readLine().trim());
			}
			br.close();
			json = new JSONObject(sb.toString()); 
		} catch (Exception e) {
			json = new JSONObject("{\"ERROR\":\"UNABLE_TO_READ_FILE\"}");
		}
		
		return json;
	}
}