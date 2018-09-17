package com.macys.sdt.projects.Customer.OESBB.utils.model;

/**
 *
 * @author Saravanan Alagarsamy saravanan.alagarsamy@macys.com
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "fieldValues" })
public class Record {

	@JsonProperty("fieldValues")
	public List<String> fieldValues = new ArrayList<String>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The fieldValues
	 */
	@JsonProperty("fieldValues")
	public List<String> getFieldValues() {
		return fieldValues;
	}

	/**
	 * 
	 * @param fieldValues
	 *            The fieldValues
	 */
	@JsonProperty("fieldValues")
	public void setFieldValues(List<String> fieldValues) {
		this.fieldValues = fieldValues;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}