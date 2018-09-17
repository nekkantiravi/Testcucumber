package com.macys.sdt.projects.Customer.OESBB.utils.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.macys.sdt.projects.Customer.OESBB.utils.Utilities;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "title", "firstName", "middleName", "lastName", "attnLine", "addrLine1", "addrLine2", "addrLine3",
		"city", "state", "country", "zipcode" })
public class BillingAddr {

	@JsonProperty("title")
	private String title;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("middleName")
	private String middleName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonProperty("attnLine")
	private String attnLine;
	@JsonProperty("addrLine1")
	private String addrLine1;
	@JsonProperty("addrLine2")
	private String addrLine2;
	@JsonProperty("addrLine3")
	private String addrLine3;
	@JsonProperty("city")
	private String city;
	@JsonProperty("state")
	private String state;
	@JsonProperty("country")
	private String country;
	@JsonProperty("zipcode")
	private String zipcode;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * 
	 * @return The title
	 */
	@JsonProperty("title")
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @param title
	 *            The title
	 */
	@JsonProperty("title")
	public void setTitle(String title) {
		this.title = title;
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
	 * @return The attnLine
	 */
	@JsonProperty("attnLine")
	public String getAttnLine() {
		return attnLine;
	}

	/**
	 * 
	 * @param attnLine
	 *            The attnLine
	 */
	@JsonProperty("attnLine")
	public void setAttnLine(String attnLine) {
		this.attnLine = attnLine;
	}

	/**
	 * 
	 * @return The addrLine1
	 */
	@JsonProperty("addrLine1")
	public String getAddrLine1() {
		return addrLine1;
	}

	/**
	 * 
	 * @param addrLine1
	 *            The addrLine1
	 */
	@JsonProperty("addrLine1")
	public void setAddrLine1(String addrLine1) {
		this.addrLine1 = addrLine1;
	}

	/**
	 * 
	 * @return The addrLine2
	 */
	@JsonProperty("addrLine2")
	public String getAddrLine2() {
		return addrLine2;
	}

	/**
	 * 
	 * @param addrLine2
	 *            The addrLine2
	 */
	@JsonProperty("addrLine2")
	public void setAddrLine2(String addrLine2) {
		this.addrLine2 = addrLine2;
	}

	/**
	 * 
	 * @return The addrLine3
	 */
	@JsonProperty("addrLine3")
	public String getAddrLine3() {
		return addrLine3;
	}

	/**
	 * 
	 * @param addrLine3
	 *            The addrLine3
	 */
	@JsonProperty("addrLine3")
	public void setAddrLine3(String addrLine3) {
		this.addrLine3 = addrLine3;
	}

	/**
	 * 
	 * @return The city
	 */
	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	/**
	 * 
	 * @param city
	 *            The city
	 */
	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 
	 * @return The state
	 */
	@JsonProperty("state")
	public String getState() {
		return state;
	}

	/**
	 * 
	 * @param state
	 *            The state
	 */
	@JsonProperty("state")
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * 
	 * @return The country
	 */
	@JsonProperty("country")
	public String getCountry() {
		return country;
	}

	/**
	 * 
	 * @param country
	 *            The country
	 */
	@JsonProperty("country")
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * 
	 * @return The zipcode
	 */
	@JsonProperty("zipcode")
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * 
	 * @param zipcode
	 *            The zipcode
	 */
	@JsonProperty("zipcode")
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
	
	public String getAddress(){
		return Utilities.returnCamelCase(this.getFirstName()) +
		          " "+ Utilities.returnCamelCase(this.getLastName()) +
        		  this.getAddrLine1() + 
        		  this.getAddrLine2() +
        		  this.getAddrLine3() +
        		  this.getCity() + ", "
					+ this.getState() + " "
					+ this.getZipcode();
	}

}