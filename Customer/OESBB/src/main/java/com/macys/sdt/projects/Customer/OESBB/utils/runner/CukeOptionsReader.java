package com.macys.sdt.projects.Customer.OESBB.utils.runner;

public class CukeOptionsReader {
	
	private static boolean cucumberOptionsContainsTag(String tagName) {
		String cucumberOptions = System.getProperty("cucumber.options");
		
		if (cucumberOptions == null) {
			cucumberOptions = System.getenv("cucumber.options");
		}
		
		if (cucumberOptions == null) {
			return false;
		}
		
		return cucumberOptions.contains("@"+tagName);
	}
	
	public static String getSourceSystemTypeTag() {
		String[] sourceSystemTypes = {"FIL", "LTY", "COM", "MAF"};
		for (int i=0; i<sourceSystemTypes.length; i++) {
			if (cucumberOptionsContainsTag(sourceSystemTypes[i])) {
				return sourceSystemTypes[i];
			}
		}
		return "UNKNOWN_ENV_SOURCE_SYSTEM_TYPE";
	}
	
	public static String getModificationTag() {
		String[] modificationTypes = {"MOD-0","MOD-1","MOD-2","MOD-3","MOD-4","MOD-5","MOD-6","MOD-11","MOD-12","MOD-13","MOD-21","MOD-22","MOD-23"};
		for (int i=0; i<modificationTypes.length; i++) {
			if (cucumberOptionsContainsTag(modificationTypes[i])) {
				return modificationTypes[i];
			}
		}
		return "none";
	}	
}