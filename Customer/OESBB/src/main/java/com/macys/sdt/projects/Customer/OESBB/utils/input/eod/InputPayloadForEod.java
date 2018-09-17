package com.macys.sdt.projects.Customer.OESBB.utils.input.eod;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.google.gson.Gson;


public class InputPayloadForEod {
	private QA264_14E_MCOM payload;

	public InputPayloadForEod(File jsonFile) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		this.payload = mapper.readValue(jsonFile, QA264_14E_MCOM.class);
	}

	public String getJSON() {
		Gson gson = new Gson();
		return gson.toJson(payload);
	}

	public QA264_14E_MCOM getPayload() {
		return this.payload;
	}

}
