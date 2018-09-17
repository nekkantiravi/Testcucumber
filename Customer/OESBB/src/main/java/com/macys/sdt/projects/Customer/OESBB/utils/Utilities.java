package com.macys.sdt.projects.Customer.OESBB.utils;

import com.google.common.base.CaseFormat;

public class Utilities {

	public static String returnCamelCase(String str) {
		return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, str);
	}

}
