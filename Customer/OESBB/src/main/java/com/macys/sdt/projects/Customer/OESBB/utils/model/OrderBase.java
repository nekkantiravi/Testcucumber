package com.macys.sdt.projects.Customer.OESBB.utils.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class OrderBase {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonPropertyOrder({ "smsPhone","smsFlag","uslId"})
	
	@JsonProperty("smsPhone")
	private String smsPhone;


	@JsonProperty("smsFlag")
	private String smsFlag;
	
	@JsonProperty("uslId")
	private String uslId;
	
	
	public String getSmsPhone() {
		return smsPhone;
	}

	public void setSmsPhone(String smsPhone) {
		this.smsPhone = smsPhone;
	}

	public String getSmsFlag() {
		return smsFlag;
	}

	public void setSmsFlag(String smsFlag) {
		this.smsFlag = smsFlag;
	}

	public String getUslId() {
		return uslId;
	}

	public void setUslId(String uslId) {
		this.uslId = uslId;
	}
	

}
