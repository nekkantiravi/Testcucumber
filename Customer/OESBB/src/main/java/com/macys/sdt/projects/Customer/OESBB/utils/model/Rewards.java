package com.macys.sdt.projects.Customer.OESBB.utils.model;

import java.io.IOException;
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
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "balance"})

public class Rewards {
	
	
	
	@JsonProperty("balance")
	private Balance balance;	
	
	/**
	 * 
	 * @return The balance
	 */
	@JsonProperty("balance")
	public Balance getBalance() {
		return balance;
	}

	
}
