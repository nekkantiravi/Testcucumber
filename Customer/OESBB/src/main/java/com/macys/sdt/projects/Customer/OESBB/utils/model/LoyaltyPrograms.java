package com.macys.sdt.projects.Customer.OESBB.utils.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "programCode", "programId", "rewards","uslAcctStatus"})
public class LoyaltyPrograms {
	@JsonProperty("programCode")
	private String programCode;	
	@JsonProperty("programId")
	private String programId;
	@JsonIgnore
	@JsonProperty("uslAcctStatus")
	private List<String> uslAcctStatus = new ArrayList<String>();
	@JsonIgnore
	@JsonProperty("rewards")
	private List<Rewards> rewards = new ArrayList<Rewards>();

	
	

	/**
	 * 
	 * @return The programId
	 */
	@JsonProperty("programId")
	public String getProgramId() {
		return programId;
	}

	/**
	 * 
	 * @param programId
	 *            The programId
	 */
	@JsonProperty("programId")
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	
	/**
	 * 
	 * @return The programCode
	 */
	@JsonProperty("programCode")
	public String getProgramCode() {
		return programCode;
	}

	/**
	 * 
	 * @param programCode
	 *            The programCode
	 */
	@JsonProperty("programCode")
	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}
	
	/**
	 * 
	 * @return The uslAcctStatus
	 *//*
	@JsonProperty("uslAcctStatus")
	public String getUslAcctStatus() {
		return uslAcctStatus;
	}

	*//**
	 * 
	 * @param uslAcctStatus
	 *            The uslAcctStatus
	 *//*
	@JsonProperty("uslAcctStatus")
	public void setUslAcctStatus(String uslAcctStatus) {
		this.uslAcctStatus = uslAcctStatus;
	}	*/
	
	/**
	 * 
	 * @return The rewards
	 */
	//@JsonProperty("rewards")
	//public List<Rewards> getRewards() {
		//return rewards;
	//}
}