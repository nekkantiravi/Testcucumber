package com.macys.sdt.projects.Customer.OESBB.utils.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)




@JsonPropertyOrder({ "keyFOBId","loyaltyId", "firstName", "middleName", "lastName","emailAddr",
		"division", "tier", "earningLevel","accountStatus","accountStatusReason","rewardBal",
		"pendingRewardBal","rewardEarned","pendingRewardEarned","offerOnlyFlag","awardAmt",
		"bonusRewardBal","bonusPendingRewardBal","bonusRewardEarned","bonusPendingRewardEarned","amexRewards",
		"pendingRewards","pwrptRewardBal","pwrptPendRewardBal","pwrptRewardEarned","pwrptPendRewardEarned",
		"rewardsToNextAward","amexRewardsBns",
		"enrollmentConfirmation", "enrollmentLocation",
		"adhocText", "addedByApp", "rewardsCardAmt",
		"rewardsCardEffectiveDate", "rewardsCardExpirationDate",
		"rewardsCardVrcBarCodeUrl", "vrcBarCodeNbr", "rewardsCardCid",
		"programCode","programId","rewards","balance","availableRewards","pendingRewardsEarned","rewardsRedeemed","bounsPendingRewardsEarned","uslAcctStatus",
		"adjustmentInternetOrderNbr", "adjustmentOrigTransactionDate", "adjustmentOrigOrderTotal"})

public class Loyalty {

	@JsonProperty("keyFOBId")
	private String keyFOBId;
	@JsonProperty("loyaltyId")
	private String loyaltyId;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("middleName")
	private String middleName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("emailAddr")
	private String emailAddr;
	@JsonProperty("division")
	private String division;
	@JsonProperty("tier")
	private String tier;
	@JsonProperty("earningLevel")
	private String earningLevel;
	@JsonProperty("accountStatus")
	private String accountStatus;
	@JsonProperty("accountStatusReason")
	private String accountStatusReason;
	@JsonProperty("rewardBal")
	private String rewardBal;
	@JsonProperty("pendingRewardBal")
	private String pendingRewardBal;
	@JsonProperty("rewardEarned")
	private String rewardEarned;
	@JsonProperty("pendingRewardEarned")
	private String pendingRewardEarned;
	@JsonProperty("offerOnlyFlag")
	private String offerOnlyFlag;
	@JsonProperty("awardAmt")
	private String awardAmt;
	@JsonProperty("bonusRewardBal")
	private String bonusRewardBal;
	@JsonProperty("bonusPendingRewardBal")
	private String bonusPendingRewardBal;
	@JsonProperty("bonusRewardEarned")
	private String bonusRewardEarned;
	@JsonProperty("bonusPendingRewardEarned")
	private String bonusPendingRewardEarned;
	@JsonProperty("amexRewards")
	private String amexRewards;
	@JsonProperty("pendingRewards")
	private String pendingRewards;
	@JsonProperty("pwrptRewardBal")
	private String pwrptRewardBal;
	@JsonProperty("pwrptPendRewardBal")
	private String pwrptPendRewardBal;
	@JsonProperty("pwrptRewardEarned")
	private String pwrptRewardEarned;
	@JsonProperty("pwrptPendRewardEarned")
	private String pwrptPendRewardEarned;
	@JsonProperty("rewardsToNextAward")
	private String rewardsToNextAward;
	@JsonProperty("amexRewardsBns")
	private String amexRewardsBns;



	@JsonProperty("enrollmentConfirmation")
	private String enrollmentConfirmation;
	@JsonProperty("enrollmentLocation")
	private String enrollmentLocation;

	@JsonProperty("adhocText")
	private String adhocText;
	@JsonProperty("addedByApp")
	private String addedByApp;

	@JsonProperty("rewardsCardAmt")
	private String rewardsCardAmt;
	@JsonProperty("rewardsCardEffectiveDate")
	private String rewardsCardEffectiveDate;

	@JsonProperty("rewardsCardExpirationDate")
	private String rewardsCardExpirationDate;
	@JsonProperty("rewardsCardVrcBarCodeUrl")
	private String rewardsCardVrcBarCodeUrl;

	@JsonProperty("vrcBarCodeNbr")
	private String vrcBarCodeNbr;
	@JsonProperty("rewardsCardCid")
	private String rewardsCardCid;

	@JsonProperty("programCode")
	private String programCode;
	@JsonProperty("programId")
	private String programId;

	@JsonProperty("rewards")
	private String rewards;
	@JsonProperty("balance")
	private String balance;

	@JsonProperty("availableRewards")
	private String availableRewards;
	@JsonProperty("pendingRewardsEarned")
	private String pendingRewardsEarned;

	@JsonProperty("rewardsRedeemed")
	private String rewardsRedeemed;
	@JsonProperty("bounsPendingRewardsEarned")
	private String bounsPendingRewardsEarned;
	@JsonProperty("adjustmentInternetOrderNbr")
	private String adjustmentInternetOrderNbr;
	@JsonProperty("adjustmentOrigTransactionDate")
	private String adjustmentOrigTransactionDate;
	@JsonProperty("adjustmentOrigOrderTotal")
	private String adjustmentOrigOrderTotal;




	@JsonProperty("loyaltyPrograms")
	private List<LoyaltyPrograms> loyaltyPrograms = new ArrayList<LoyaltyPrograms>();

	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 *
	 * @return The loyaltyId
	 */
	@JsonProperty("loyaltyId")
	public String getLoyaltyId() {
		return loyaltyId;
	}

	/**
	 *
	 * @param loyaltyId
	 *            The loyaltyId
	 */
	@JsonProperty("loyaltyId")
	public void setLoyaltyId(String loyaltyId) {
		this.loyaltyId = loyaltyId;
	}

	/**
	 *
	 * @return The firstName
	 */
	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	/**
	 *
	 * @param firstName
	 *            The firstName
	 */
	@JsonProperty("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 *
	 * @return The middleName
	 */
	@JsonProperty("middleName")
	public String getMiddleName() {
		return middleName;
	}

	/**
	 *
	 * @param middleName
	 *            The middleName
	 */
	@JsonProperty("middleName")
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 *
	 * @return The lastName
	 */
	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	/**
	 *
	 * @param lastName
	 *            The lastName
	 */
	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 *
	 * @return The division
	 */
	@JsonProperty("division")
	public String getDivision() {
		return division;
	}

	/**
	 *
	 * @param division
	 *            The division
	 */
	@JsonProperty("division")
	public void setDivision(String division) {
		this.division = division;
	}

	/**
	 *
	 * @return The tier
	 */
	@JsonProperty("tier")
	public String getTier() {
		return tier;
	}

	/**
	 *
	 * @param tier
	 *            The tier
	 */
	@JsonProperty("tier")
	public void setTier(String tier) {
		this.tier = tier;
	}

	/**
	 *
	 * @return The enrollmentConfirmation
	 */
	@JsonProperty("enrollmentConfirmation")
	public String getEnrollmentConfirmation() {
		return enrollmentConfirmation;
	}

	/**
	 *
	 * @param enrollmentConfirmation
	 *            The enrollmentConfirmation
	 */
	@JsonProperty("enrollmentConfirmation")
	public void setEnrollmentConfirmation(String enrollmentConfirmation) {
		this.enrollmentConfirmation = enrollmentConfirmation;
	}

	/**
	 *
	 * @return The enrollmentLocation
	 */
	@JsonProperty("enrollmentLocation")
	public String getEnrollmentLocation() {
		return enrollmentLocation;
	}

	/**
	 *
	 * @param enrollmentLocation
	 *            The enrollmentLocation
	 */
	@JsonProperty("enrollmentLocation")
	public void setEnrollmentLocation(String enrollmentLocation) {
		this.enrollmentLocation = enrollmentLocation;
	}

	/**
	 *
	 * @return The adhocText
	 */
	@JsonProperty("adhocText")
	public String getAdhocText() {
		return adhocText;
	}

	/**
	 *
	 * @param adhocText
	 *            The adhocText
	 */
	@JsonProperty("adhocText")
	public void setAdhocText(String adhocText) {
		this.adhocText = adhocText;
	}

	/**
	 *
	 * @return The addedByApp
	 */
	@JsonProperty("addedByApp")
	public String getAddedByApp() {
		return addedByApp;
	}

	/**
	 *
	 * @param addedByApp
	 *            The addedByApp
	 */
	@JsonProperty("addedByApp")
	public void setAddedByApp(String addedByApp) {
		this.addedByApp = addedByApp;
	}

	/**
	 *
	 * @return The rewardsCardAmt
	 */
	@JsonProperty("rewardsCardAmt")
	public String getRewardsCardAmt() {
		return rewardsCardAmt;
	}

	/**
	 *
	 * @param rewardsCardAmt
	 *            The rewardsCardAmt
	 */
	@JsonProperty("rewardsCardAmt")
	public void setRewardsCardAmt(String rewardsCardAmt) {
		this.rewardsCardAmt = rewardsCardAmt;
	}

	/**
	 *
	 * @return The rewardsCardEffectiveDate
	 */
	@JsonProperty("rewardsCardEffectiveDate")
	public String getRewardsCardEffectiveDate() {
		return rewardsCardEffectiveDate;
	}

	/**
	 *
	 * @param rewardsCardEffectiveDate
	 *            The rewardsCardEffectiveDate
	 */
	@JsonProperty("rewardsCardEffectiveDate")
	public void setRewardsCardEffectiveDate(String rewardsCardEffectiveDate) {
		this.rewardsCardEffectiveDate = rewardsCardEffectiveDate;
	}

	/**
	 *
	 * @return The rewardsCardExpirationDate
	 */
	@JsonProperty("rewardsCardExpirationDate")
	public String getRewardsCardExpirationDate() {
		return rewardsCardExpirationDate;
	}

	/**
	 *
	 * @param rewardsCardExpirationDate
	 *            The rewardsCardExpirationDate
	 */
	@JsonProperty("rewardsCardExpirationDate")
	public void setRewardsCardExpirationDate(String rewardsCardExpirationDate) {
		this.rewardsCardExpirationDate = rewardsCardExpirationDate;
	}

	/**
	 *
	 * @return The rewardsCardVrcBarCodeUrl
	 */
	@JsonProperty("rewardsCardVrcBarCodeUrl")
	public String getRewardsCardVrcBarCodeUrl() {
		return rewardsCardVrcBarCodeUrl;
	}

	/**
	 *
	 * @param rewardsCardVrcBarCodeUrl
	 *            The rewardsCardVrcBarCodeUrl
	 */
	@JsonProperty("rewardsCardVrcBarCodeUrl")
	public void setRewardsCardVrcBarCodeUrl(String rewardsCardVrcBarCodeUrl) {
		this.rewardsCardVrcBarCodeUrl = rewardsCardVrcBarCodeUrl;
	}

	/**
	 *
	 * @return The vrcBarCodeNbr
	 */
	@JsonProperty("vrcBarCodeNbr")
	public String getVrcBarCodeNbr() {
		return vrcBarCodeNbr;
	}

	/**
	 *
	 * @param vrcBarCodeNbr
	 *            The vrcBarCodeNbr
	 */
	@JsonProperty("vrcBarCodeNbr")
	public void setVrcBarCodeNbr(String vrcBarCodeNbr) {
		this.vrcBarCodeNbr = vrcBarCodeNbr;
	}

	/**
	 *
	 * @return The rewardsCardCid
	 */
	@JsonProperty("rewardsCardCid")
	public String getRewardsCardCid() {
		return rewardsCardCid;
	}

	/**
	 *
	 * @param rewardsCardCid
	 *            The rewardsCardCid
	 */
	@JsonProperty("rewardsCardCid")
	public void setRewardsCardCid(String rewardsCardCid) {
		this.rewardsCardCid = rewardsCardCid;
	}

	/**
	 *
	 * @return The keyFOBId
	 */
	@JsonProperty("keyFOBId")
	public String getKeyFOBId() {
		return keyFOBId;
	}

	/**
	 *
	 * @param keyFOBId
	 *            The keyFOBId
	 */
	@JsonProperty("keyFOBId")
	public void setKeyFOBId(String keyFOBId) {
		this.keyFOBId = keyFOBId;
	}

	/**
	 *
	 * @return The emailAddr
	 */
	@JsonProperty("emailAddr")
	public String getEmailAddr() {
		return emailAddr;
	}

	/**
	 *
	 * @param emailAddr
	 *            The emailAddr
	 */
	@JsonProperty("emailAddr")
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	/**
	 *
	 * @return The earningLevel
	 */
	@JsonProperty("earningLevel")
	public String getEarningLevel() {
		return earningLevel;
	}

	/**
	 *
	 * @param earningLevel
	 *            The earningLevel
	 */
	@JsonProperty("earningLevel")
	public void setEarningLevel(String earningLevel) {
		this.earningLevel = earningLevel;
	}


	/**
	 *
	 * @return The accountStatus
	 */
	@JsonProperty("accountStatus")
	public String getAccountStatus() {
		return earningLevel;
	}

	/**
	 *
	 * @param accountStatus
	 *            The accountStatus
	 */
	@JsonProperty("accountStatus")
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}


	/**
	 *
	 * @return The accountStatusReason
	 */
	@JsonProperty("accountStatusReason")
	public String getAccountStatusReason() {
		return accountStatusReason;
	}

	/**
	 *
	 * @param accountStatusReason
	 *            The accountStatusReason
	 */
	@JsonProperty("accountStatusReason")
	public void setAccountStatusReason(String accountStatusReason) {
		this.accountStatusReason = accountStatusReason;
	}

	/**
	 *
	 * @return The rewardBal
	 */
	@JsonProperty("rewardBal")
	public String getRewardBal() {
		return rewardBal;
	}

	/**
	 *
	 * @param rewardBal
	 *            The rewardBal
	 */
	@JsonProperty("rewardBal")
	public void setRewardBal(String rewardBal) {
		this.rewardBal = rewardBal;
	}

	/**
	 *
	 * @return The pendingRewardBal
	 */
	@JsonProperty("pendingRewardBal")
	public String getPendingRewardBal() {
		return pendingRewardBal;
	}

	/**
	 *
	 * @param pendingRewardBal
	 *            The pendingRewardBal
	 */
	@JsonProperty("pendingRewardBal")
	public void setPendingRewardBal(String pendingRewardBal) {
		this.pendingRewardBal = pendingRewardBal;
	}

	/**
	 *
	 * @return The rewardEarned
	 */
	@JsonProperty("rewardEarned")
	public String getRewardEarned() {
		return rewardEarned;
	}

	/**
	 *
	 * @param rewardEarned
	 *            The rewardEarned
	 */
	@JsonProperty("rewardEarned")
	public void setRewardEarned(String rewardEarned) {
		this.rewardEarned = rewardEarned;
	}

	/**
	 *
	 * @return The pendingRewardEarned
	 */
	@JsonProperty("pendingRewardEarned")
	public String getPendingRewardEarned() {
		return pendingRewardEarned;
	}

	/**
	 *
	 * @param pendingRewardEarned
	 *            The pendingRewardEarned
	 */
	@JsonProperty("pendingRewardEarned")
	public void setPendingRewardEarned(String pendingRewardEarned) {
		this.pendingRewardEarned = pendingRewardEarned;
	}

	/**
	 *
	 * @return The offerOnlyFlag
	 */
	@JsonProperty("offerOnlyFlag")
	public String getOfferOnlyFlag() {
		return offerOnlyFlag;
	}

	/**
	 *
	 * @param offerOnlyFlag
	 *            The offerOnlyFlag
	 */
	@JsonProperty("offerOnlyFlag")
	public void setOfferOnlyFlag(String offerOnlyFlag) {
		this.offerOnlyFlag = offerOnlyFlag;
	}

	/**
	 *
	 * @return The awardAmt
	 */
	@JsonProperty("awardAmt")
	public String getAwardAmt() {
		return awardAmt;
	}

	/**
	 *
	 * @param awardAmt
	 *            The awardAmt
	 */
	@JsonProperty("awardAmt")
	public void setAwardAmt(String awardAmt) {
		this.awardAmt = awardAmt;
	}

	/**
	 *
	 * @return The bonusRewardBal
	 */
	@JsonProperty("bonusRewardBal")
	public String getBonusRewardBal() {
		return bonusRewardBal;
	}

	/**
	 *
	 * @param bonusRewardBal
	 *            The bonusRewardBal
	 */
	@JsonProperty("bonusRewardBal")
	public void setBonusRewardBal(String bonusRewardBal) {
		this.bonusRewardBal = bonusRewardBal;
	}


	/**
	 *
	 * @return The bonusPendingRewardBal
	 */
	@JsonProperty("bonusPendingRewardBal")
	public String getBonusPendingRewardBal() {
		return bonusPendingRewardBal;
	}

	/**
	 *
	 * @param bonusPendingRewardBal
	 *            The bonusPendingRewardBal
	 */
	@JsonProperty("bonusPendingRewardBal")
	public void setBonusPendingRewardBal(String bonusPendingRewardBal) {
		this.bonusPendingRewardBal = bonusPendingRewardBal;
	}

	/**
	 *
	 * @return The bonusRewardEarned
	 */
	@JsonProperty("bonusRewardEarned")
	public String getBonusRewardEarned() {
		return bonusRewardEarned;
	}

	/**
	 *
	 * @param bonusRewardEarned
	 *            The bonusRewardEarned
	 */
	@JsonProperty("bonusRewardEarned")
	public void setBonusRewardEarned(String bonusRewardEarned) {
		this.bonusRewardEarned = bonusRewardEarned;
	}

	/**
	 *
	 * @return The bonusPendingRewardEarned
	 */
	@JsonProperty("bonusPendingRewardEarned")
	public String getBonusPendingRewardEarned() {
		return bonusPendingRewardEarned;
	}

	/**
	 *
	 * @param bonusPendingRewardEarned
	 *            The bonusPendingRewardEarned
	 */
	@JsonProperty("bonusPendingRewardEarned")
	public void setBonusPendingRewardEarned(String bonusPendingRewardEarned) {
		this.bonusPendingRewardEarned = bonusPendingRewardEarned;
	}

	/**
	 *
	 * @return The amexRewards
	 */
	@JsonProperty("amexRewards")
	public String getAmexRewards() {
		return amexRewards;
	}

	/**
	 *
	 * @param amexRewards
	 *            The amexRewards
	 */
	@JsonProperty("amexRewards")
	public void setAmexRewards(String amexRewards) {
		this.amexRewards = amexRewards;
	}

	/**
	 *
	 * @return The pendingRewards
	 */
	@JsonProperty("pendingRewards")
	public String getPendingRewards() {
		return pendingRewards;
	}

	/**
	 *
	 * @param pendingRewards
	 *            The pendingRewards
	 */
	@JsonProperty("pendingRewards")
	public void setPendingRewards(String pendingRewards) {
		this.pendingRewards = pendingRewards;
	}

	/**
	 *
	 * @return The pwrptRewardBal
	 */
	@JsonProperty("pwrptRewardBal")
	public String getPwrptRewardBal() {
		return pwrptRewardBal;
	}

	/**
	 *
	 * @param pwrptRewardBal
	 *            The pwrptRewardBal
	 */
	@JsonProperty("pwrptRewardBal")
	public void setPwrptRewardBal(String pwrptRewardBal) {
		this.pwrptRewardBal = pwrptRewardBal;
	}

	/**
	 *
	 * @return The pwrptPendRewardBal
	 */
	@JsonProperty("pwrptPendRewardBal")
	public String getPwrptPendRewardBal() {
		return pwrptPendRewardBal;
	}

	/**
	 *
	 * @param pwrptPendRewardBal
	 *            The pwrptPendRewardBal
	 */
	@JsonProperty("pwrptPendRewardBal")
	public void setPwrptPendRewardBal(String pwrptPendRewardBal) {
		this.pwrptPendRewardBal = pwrptPendRewardBal;
	}

	/**
	 *
	 * @return The pwrptRewardEarned
	 */
	@JsonProperty("pwrptRewardEarned")
	public String getPwrptRewardEarned() {
		return pwrptRewardEarned;
	}

	/**
	 *
	 * @param pwrptRewardEarned
	 *            The pwrptRewardEarned
	 */
	@JsonProperty("pwrptRewardEarned")
	public void setPwrptRewardEarned(String pwrptRewardEarned) {
		this.pwrptRewardEarned = pwrptRewardEarned;
	}

	/**
	 *
	 * @return The pwrptPendRewardEarned
	 */
	@JsonProperty("pwrptPendRewardEarned")
	public String getPwrptPendRewardEarned() {
		return pwrptPendRewardEarned;
	}

	/**
	 *
	 * @param pwrptPendRewardEarned
	 *            The pwrptPendRewardEarned
	 */
	@JsonProperty("pwrptPendRewardEarned")
	public void setPwrptPendRewardEarned(String pwrptPendRewardEarned) {
		this.pwrptPendRewardEarned = pwrptPendRewardEarned;
	}

	/**
	 *
	 * @return The rewardsToNextAward
	 */
	@JsonProperty("rewardsToNextAward")
	public String getRewardsToNextAward() {
		return rewardsToNextAward;
	}

	/**
	 *
	 * @param rewardsToNextAward
	 *            The rewardsToNextAward
	 */
	@JsonProperty("rewardsToNextAward")
	public void setRewardsToNextAward(String rewardsToNextAward) {
		this.rewardsToNextAward = rewardsToNextAward;
	}

	/**
	 *
	 * @return The amexRewardsBns
	 */
	@JsonProperty("amexRewardsBns")
	public String getAmexRewardsBns() {
		return amexRewardsBns;
	}

	/**
	 *
	 * @param amexRewardsBns
	 *            The amexRewardsBns
	 */
	@JsonProperty("amexRewardsBns")
	public void setAmexRewardsBns(String amexRewardsBns) {
		this.amexRewardsBns = amexRewardsBns;
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

	/**
	 *
	 * @return The availableRewards
	 */
	@JsonProperty("availableRewards")
	public String getAvailableRewards() {
		return availableRewards;
	}

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
	 * @return The balance
	 */
	@JsonProperty("balance")
	public String getBalance() {
		return balance;
	}

	/**
	 *
	 * @param balance
	 *            The balance
	 */
	@JsonProperty("balance")
	public void setBalance(String balance) {
		this.balance = balance;
	}

	/**
	 *
	 * @return The rewards
	 */
	@JsonProperty("rewards")
	public String getRewards() {
		return rewards;
	}

	/**
	 *
	 * @param rewards
	 *            The rewards
	 */
	@JsonProperty("rewards")
	public void setRewards(String rewards) {
		this.rewards = rewards;
	}

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
	 * @return The loyaltyPrograms
	 */
	@JsonProperty("loyaltyPrograms")
	public List<LoyaltyPrograms> getLoyaltyPrograms() {
		return loyaltyPrograms;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
	@JsonAnyGetter
	public String getAdjustmentInternetOrderNbr() {
		return adjustmentInternetOrderNbr;
	}
	@JsonAnySetter
	public void setAdjustmentInternetOrderNbr(String adjustmentInternetOrderNbr) {
		this.adjustmentInternetOrderNbr = adjustmentInternetOrderNbr;
	}
	@JsonAnyGetter
	public String getAdjustmentOrigTransactionDate() {
		return adjustmentOrigTransactionDate;
	}
	@JsonAnySetter
	public void setAdjustmentOrigTransactionDate(String adjustmentOrigTransactionDate) {
		this.adjustmentOrigTransactionDate = adjustmentOrigTransactionDate;
	}
	@JsonAnyGetter
	public String getAdjustmentOrigOrderTotal() {
		return adjustmentOrigOrderTotal;
	}
	@JsonAnySetter
	public void setAdjustmentOrigOrderTotal(String adjustmentOrigOrderTotal) {
		this.adjustmentOrigOrderTotal = adjustmentOrigOrderTotal;
	}
}