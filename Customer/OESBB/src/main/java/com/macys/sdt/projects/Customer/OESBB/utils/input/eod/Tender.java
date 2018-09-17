package com.macys.sdt.projects.Customer.OESBB.utils.input.eod;


public class Tender {
	private String tenderType;
	private String tenderAmount;
	private String accountNbr;
	private String accountIndicator;
	private String accountIndicatorDescription;
	private String accountType;
	private String accountTypeDescription;
	private String paymentTransactionType;
	private String egcCertificateType;
	private String authorizationAmount;
	private String authorizationPlanType;
	private String authorizationDate;
	private String authorizationExpiredDate;

	public String getTenderType() {
		return tenderType;
	}

	public void setTenderType(String tenderType) {
		this.tenderType = tenderType;
	}

	public String getTenderAmount() {
		return tenderAmount;
	}

	public void setTenderAmount(String tenderAmount) {
		this.tenderAmount = tenderAmount;
	}

	public String getAccountNbr() {
		return accountNbr;
	}

	public void setAccountNbr(String accountNbr) {
		this.accountNbr = accountNbr;
	}

	public String getAccountIndicator() {
		return accountIndicator;
	}

	public void setAccountIndicator(String accountIndicator) {
		this.accountIndicator = accountIndicator;
	}

	public String getAccountIndicatorDescription() {
		return accountIndicatorDescription;
	}

	public void setAccountIndicatorDescription(String accountIndicatorDescription) {
		this.accountIndicatorDescription = accountIndicatorDescription;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountTypeDescription() {
		return accountTypeDescription;
	}

	public void setAccountTypeDescription(String accountTypeDescription) {
		this.accountTypeDescription = accountTypeDescription;
	}

	public String getPaymentTransactionType() {
		return paymentTransactionType;
	}

	public void setPaymentTransactionType(String paymentTransactionType) {
		this.paymentTransactionType = paymentTransactionType;
	}

	public String getEgcCertificateType() {
		return egcCertificateType;
	}

	public void setEgcCertificateType(String egcCertificateType) {
		this.egcCertificateType = egcCertificateType;
	}

	public String getAuthorizationAmount() {
		return authorizationAmount;
	}

	public void setAuthorizationAmount(String authorizationAmount) {
		this.authorizationAmount = authorizationAmount;
	}

	public String getAuthorizationPlanType() {
		return authorizationPlanType;
	}

	public void setAuthorizationPlanType(String authorizationPlanType) {
		this.authorizationPlanType = authorizationPlanType;
	}

	public String getAuthorizationDate() {
		return authorizationDate;
	}

	public void setAuthorizationDate(String authorizationDate) {
		this.authorizationDate = authorizationDate;
	}

	public String getAuthorizationExpiredDate() {
		return authorizationExpiredDate;
	}

	public void setAuthorizationExpiredDate(String authorizationExpiredDate) {
		this.authorizationExpiredDate = authorizationExpiredDate;
	}
}
