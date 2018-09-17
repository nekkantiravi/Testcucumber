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
@JsonPropertyOrder({ "fieldNames", "records" })
public class RecordData {

	@JsonProperty("fieldNames")
	private List<String> fieldNames = new ArrayList<String>();
	@JsonProperty("records")
	private List<Record> records = new ArrayList<Record>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The fieldNames
	 */
	@JsonProperty("fieldNames")
	public List<String> getFieldNames() {
		return fieldNames;
	}

	/**
	 * 
	 * @param fieldNames
	 *            The fieldNames
	 */
	@JsonProperty("fieldNames")
	public void setFieldNames(List<String> fieldNames) {
		this.fieldNames = fieldNames;
	}

	/**
	 * 
	 * @return The records
	 */
	@JsonProperty("records")
	public List<Record> getRecords() {
		return records;
	}

	/**
	 * 
	 * @param records
	 *            The records
	 */
	@JsonProperty("records")
	public void setRecords(List<Record> records) {
		this.records = records;
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