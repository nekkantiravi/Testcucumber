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
import com.google.common.base.Strings;

@JsonInclude(JsonInclude.Include.NON_NULL)

@JsonPropertyOrder({ "productName","productUrl","imageUrl","reviewRating","reviewNbr" })

public class Recommendations {
	@JsonProperty("productName")
	private String productName=null;
	
	@JsonProperty("productUrl")
	private String productUrl=null;
	
	@JsonProperty("imageUrl")
	private String imageUrl=null;
	
	@JsonProperty("reviewRating")
	private String reviewRating=null;
	
	@JsonProperty("reviewNbr")
	private String reviewNbr=null;
	
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	

	/**
	 * 
	 * @return The productName
	 */
	@JsonProperty("productName")
	public String getProductName() {
		if(Strings.isNullOrEmpty(productName)){return null;}
		return productName;
	}

	/**
	 * 
	 * @param productName
	 *            The productName
	 */
	@JsonProperty("productName")
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 
	 * @return The productUrl
	 */
	@JsonProperty("productUrl")
	public String getProductUrl() {
		if(Strings.isNullOrEmpty(productUrl)){return null;}
		return productUrl;
	}

	/**
	 * 
	 * @param productUrl
	 *            The productUrl
	 */
	@JsonProperty("productUrl")
	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}
	/**
	 * 
	 * @return The imageUrl
	 */
	@JsonProperty("imageUrl")
	public String getImageUrl() {
		if(Strings.isNullOrEmpty(imageUrl)){return null;}
		return imageUrl;
	}

	/**
	 * 
	 * @param imageUrl
	 *            The imageUrl
	 */
	@JsonProperty("imageUrl")
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	/**
	 * 
	 * @return The reviewRating
	 */
	@JsonProperty("reviewRating")
	public String getReviewRating() {
		if(Strings.isNullOrEmpty(reviewRating)){return null;}
		return reviewRating;
	}

	/**
	 * 
	 * @param reviewRating
	 *            The reviewRating
	 */
	@JsonProperty("reviewRating")
	public void setReviewRating(String reviewRating) {
		this.reviewRating = reviewRating;
	}
	/**
	 * 
	 * @return The reviewNbr
	 */
	@JsonProperty("reviewNbr")
	public String getReviewNbr() {
		if(Strings.isNullOrEmpty(reviewNbr)){return null;}
		return reviewNbr;
	}

	/**
	 * 
	 * @param reviewNbr
	 *            The reviewNbr
	 */
	@JsonProperty("reviewNbr")
	public void setReviewNbr(String reviewNbr) {
		this.reviewNbr = reviewNbr;
	}
}

	

	
