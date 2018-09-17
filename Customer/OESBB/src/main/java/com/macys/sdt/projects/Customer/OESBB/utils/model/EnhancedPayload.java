/**
 * 
 */
package com.macys.sdt.projects.Customer.OESBB.utils.model;

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

/**
 * @author m509575
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "recordData", "mergeRule", "triggerData" })
public class EnhancedPayload {
	
	@JsonProperty("recordData")
	private RecordData recordData;
	@JsonProperty("mergeRule")
	private MergeRule mergeRule;
	@JsonProperty("triggerData")
	private List<TriggerDatum> triggerData = new ArrayList<TriggerDatum>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The recordData
	 */
	@JsonProperty("recordData")
	public RecordData getRecordData() {
		return recordData;
	}

	/**
	 * 
	 * @param recordData
	 *            The recordData
	 */
	@JsonProperty("recordData")
	public void setRecordData(RecordData recordData) {
		this.recordData = recordData;
	}

	/**
	 * 
	 * @return The mergeRule
	 */
	@JsonProperty("mergeRule")
	public MergeRule getMergeRule() {
		return mergeRule;
	}

	/**
	 * 
	 * @param mergeRule
	 *            The mergeRule
	 */
	@JsonProperty("mergeRule")
	public void setMergeRule(MergeRule mergeRule) {
		this.mergeRule = mergeRule;
	}

	/**
	 * 
	 * @return The triggerData
	 */
	@JsonProperty("triggerData")
	public List<TriggerDatum> getTriggerData() {
		return triggerData;
	}

	/**
	 * 
	 * @param triggerData
	 *            The triggerData
	 */
	@JsonProperty("triggerData")
	public void setTriggerData(List<TriggerDatum> triggerData) {
		this.triggerData = triggerData;
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
