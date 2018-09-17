
package com.macys.sdt.projects.Customer.OESBB.utils.model;

/**
 *
 * @author Saravanan Alagarsamy saravanan.alagarsamy@macys.com
 */
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "insertOnNoMatch", "updateOnMatch", "matchColumnName1", "matchColumnName2", "matchOperator",
		"optinValue", "optoutValue", "htmlValue", "textValue", "rejectRecordIfChannelEmpty",
		"defaultPermissionStatus" })
public class MergeRule {

	@JsonProperty("insertOnNoMatch")
	private Boolean insertOnNoMatch;
	@JsonProperty("updateOnMatch")
	private String updateOnMatch;
	@JsonProperty("matchColumnName1")
	private String matchColumnName1;
	@JsonProperty("matchColumnName2")
	private Object matchColumnName2;
	@JsonProperty("matchOperator")
	private String matchOperator;
	@JsonProperty("optinValue")
	private String optinValue;
	@JsonProperty("optoutValue")
	private String optoutValue;
	@JsonProperty("htmlValue")
	private String htmlValue;
	@JsonProperty("textValue")
	private String textValue;
	@JsonProperty("rejectRecordIfChannelEmpty")
	private Object rejectRecordIfChannelEmpty;
	@JsonProperty("defaultPermissionStatus")
	private String defaultPermissionStatus;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The insertOnNoMatch
	 */
	@JsonProperty("insertOnNoMatch")
	public Boolean getInsertOnNoMatch() {
		return insertOnNoMatch;
	}

	/**
	 * 
	 * @param insertOnNoMatch
	 *            The insertOnNoMatch
	 */
	@JsonProperty("insertOnNoMatch")
	public void setInsertOnNoMatch(Boolean insertOnNoMatch) {
		this.insertOnNoMatch = insertOnNoMatch;
	}

	/**
	 * 
	 * @return The updateOnMatch
	 */
	@JsonProperty("updateOnMatch")
	public String getUpdateOnMatch() {
		return updateOnMatch;
	}

	/**
	 * 
	 * @param updateOnMatch
	 *            The updateOnMatch
	 */
	@JsonProperty("updateOnMatch")
	public void setUpdateOnMatch(String updateOnMatch) {
		this.updateOnMatch = updateOnMatch;
	}

	/**
	 * 
	 * @return The matchColumnName1
	 */
	@JsonProperty("matchColumnName1")
	public String getMatchColumnName1() {
		return matchColumnName1;
	}

	/**
	 * 
	 * @param matchColumnName1
	 *            The matchColumnName1
	 */
	@JsonProperty("matchColumnName1")
	public void setMatchColumnName1(String matchColumnName1) {
		this.matchColumnName1 = matchColumnName1;
	}

	/**
	 * 
	 * @return The matchColumnName2
	 */
	@JsonProperty("matchColumnName2")
	public Object getMatchColumnName2() {
		return matchColumnName2;
	}

	/**
	 * 
	 * @param matchColumnName2
	 *            The matchColumnName2
	 */
	@JsonProperty("matchColumnName2")
	public void setMatchColumnName2(Object matchColumnName2) {
		this.matchColumnName2 = matchColumnName2;
	}

	/**
	 * 
	 * @return The matchOperator
	 */
	@JsonProperty("matchOperator")
	public String getMatchOperator() {
		return matchOperator;
	}

	/**
	 * 
	 * @param matchOperator
	 *            The matchOperator
	 */
	@JsonProperty("matchOperator")
	public void setMatchOperator(String matchOperator) {
		this.matchOperator = matchOperator;
	}

	/**
	 * 
	 * @return The optinValue
	 */
	@JsonProperty("optinValue")
	public String getOptinValue() {
		return optinValue;
	}

	/**
	 * 
	 * @param optinValue
	 *            The optinValue
	 */
	@JsonProperty("optinValue")
	public void setOptinValue(String optinValue) {
		this.optinValue = optinValue;
	}

	/**
	 * 
	 * @return The optoutValue
	 */
	@JsonProperty("optoutValue")
	public String getOptoutValue() {
		return optoutValue;
	}

	/**
	 * 
	 * @param optoutValue
	 *            The optoutValue
	 */
	@JsonProperty("optoutValue")
	public void setOptoutValue(String optoutValue) {
		this.optoutValue = optoutValue;
	}

	/**
	 * 
	 * @return The htmlValue
	 */
	@JsonProperty("htmlValue")
	public String getHtmlValue() {
		return htmlValue;
	}

	/**
	 * 
	 * @param htmlValue
	 *            The htmlValue
	 */
	@JsonProperty("htmlValue")
	public void setHtmlValue(String htmlValue) {
		this.htmlValue = htmlValue;
	}

	/**
	 * 
	 * @return The textValue
	 */
	@JsonProperty("textValue")
	public String getTextValue() {
		return textValue;
	}

	/**
	 * 
	 * @param textValue
	 *            The textValue
	 */
	@JsonProperty("textValue")
	public void setTextValue(String textValue) {
		this.textValue = textValue;
	}

	/**
	 * 
	 * @return The rejectRecordIfChannelEmpty
	 */
	@JsonProperty("rejectRecordIfChannelEmpty")
	public Object getRejectRecordIfChannelEmpty() {
		return rejectRecordIfChannelEmpty;
	}

	/**
	 * 
	 * @param rejectRecordIfChannelEmpty
	 *            The rejectRecordIfChannelEmpty
	 */
	@JsonProperty("rejectRecordIfChannelEmpty")
	public void setRejectRecordIfChannelEmpty(Object rejectRecordIfChannelEmpty) {
		this.rejectRecordIfChannelEmpty = rejectRecordIfChannelEmpty;
	}

	/**
	 * 
	 * @return The defaultPermissionStatus
	 */
	@JsonProperty("defaultPermissionStatus")
	public String getDefaultPermissionStatus() {
		return defaultPermissionStatus;
	}

	/**
	 * 
	 * @param defaultPermissionStatus
	 *            The defaultPermissionStatus
	 */
	@JsonProperty("defaultPermissionStatus")
	public void setDefaultPermissionStatus(String defaultPermissionStatus) {
		this.defaultPermissionStatus = defaultPermissionStatus;
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