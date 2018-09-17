package com.macys.sdt.projects.Customer.OESBB.utils.input.subscrption;

import java.io.File;
import java.io.IOException;
import javax.xml.bind.JAXBException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

/**
 * @author sbharadwaja
 *
 */
public class SubscriptionInputPayload {

	public QA621_1A_MCOM payload;

	/**
	 * @throws JsonParseException
	 * @throws JAXBException
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonGenerationException
	 * 
	 */
	public SubscriptionInputPayload(File jsonFile) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		this.payload = mapper.readValue(jsonFile, QA621_1A_MCOM.class);
	}

	public String getJSON()  {
		Gson gson = new Gson();
		return gson.toJson(payload);
	}

	public QA621_1A_MCOM getPayloadObject() {
		return this.payload;
	}
}
