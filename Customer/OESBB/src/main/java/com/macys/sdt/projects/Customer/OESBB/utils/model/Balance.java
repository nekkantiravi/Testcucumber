package com.macys.sdt.projects.Customer.OESBB.utils.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"availableRewards","pendingRewardsEarned","rewardsRedeemed","bounsPendingRewardsEarned"})

public class Balance {
	
	@JsonIgnore
	@JsonProperty("availableRewards")
	private String availableRewards;
	@JsonIgnore
	@JsonProperty("pendingRewardsEarned")
	private String pendingRewardsEarned;
	@JsonIgnore
	@JsonProperty("rewardsRedeemed")
	private String rewardsRedeemed;	
	@JsonIgnore
	@JsonProperty("bounsPendingRewardsEarned")
	private String bounsPendingRewardsEarned;
	
	

	/**
	 * 
	 * @param availableRewards
	 *            The availableRewards
	 */
	@JsonProperty("availableRewards")
	public void setAvailableRewards(String availableRewards) {
		this.availableRewards = availableRewards;
	}	
	
	/**
	 * 
	 * @param availableRewards
	 *            The availableRewards
	 * @return 
	 */
	@JsonProperty("availableRewards")
	public String getAvailableRewards() {
		return availableRewards;
	}	
	
	/**
	 * 
	 * @return The bounsPendingRewardsEarned
	 */
	@JsonProperty("bounsPendingRewardsEarned")
	public String getBounsPendingRewardsEarned() {
		return bounsPendingRewardsEarned;
	}

	/**
	 * 
	 * @param bounsPendingRewardsEarned
	 *            The bounsPendingRewardsEarned
	 */
	@JsonProperty("bounsPendingRewardsEarned")
	public void setBounsPendingRewardsEarned(String bounsPendingRewardsEarned) {
		this.bounsPendingRewardsEarned = bounsPendingRewardsEarned;
	}	
	
	/**
	 * 
	 * @return The rewardsRedeemed
	 */
	@JsonProperty("rewardsRedeemed")
	public String getRewardsRedeemed() {
		return rewardsRedeemed;
	}

	/**
	 * 
	 * @param rewardsRedeemed
	 *            The rewardsRedeemed
	 */
	@JsonProperty("rewardsRedeemed")
	public void setRewardsRedeemed(String rewardsRedeemed) {
		this.rewardsRedeemed = rewardsRedeemed;
	}	
	
	/**
	 * 
	 * @return The pendingRewardsEarned
	 */
	@JsonProperty("pendingRewardsEarned")
	public String getPendingRewardsEarned() {
		return pendingRewardsEarned;
	}

	/**
	 * 
	 * @param pendingRewardsEarned
	 *            The pendingRewardsEarned
	 */
	@JsonProperty("pendingRewardsEarned")
	public void setPendingRewardsEarned(String pendingRewardsEarned) {
		this.pendingRewardsEarned = pendingRewardsEarned;
	}	
}
