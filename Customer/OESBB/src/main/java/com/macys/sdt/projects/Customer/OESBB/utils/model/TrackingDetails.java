package com.macys.sdt.projects.Customer.OESBB.utils.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "trackingNbr","trackingUrl","totalNbrOfPackages","carrier"})
public class TrackingDetails {
	
	@JsonProperty("trackingNbr")
	private String trackingNbr;
	@JsonProperty("trackingUrl")
	private String trackingUrl;
	@JsonProperty("totalNbrOfPackages")
	private String totalNbrOfPackages;
	@JsonProperty("carrier")
	private String carrier;
	
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getTotalNbrOfPackages() {
		return totalNbrOfPackages;
	}
	public void setTotalNbrOfPackages(String totalNbrOfPackages) {
		this.totalNbrOfPackages = totalNbrOfPackages;
	}
	public String getTrackingNbr() {
		return trackingNbr;
	}
	public void setTrackingNbr(String trackingNbr) {
		this.trackingNbr = trackingNbr;
	}
	public String getTrackingUrl() {
		return trackingUrl;
	}
	public void setTrackingUrl(String trackingUrl) {
		this.trackingUrl = trackingUrl;
	}
	

}
