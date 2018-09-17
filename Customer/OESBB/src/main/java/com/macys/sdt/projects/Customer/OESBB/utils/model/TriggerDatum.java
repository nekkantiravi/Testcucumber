package com.macys.sdt.projects.Customer.OESBB.utils.model;

import java.io.IOException;
/**
 *
 * @author Saravanan Alagarsamy saravanan.alagarsamy@macys.com
 */
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
@JsonPropertyOrder({ "optionalData" })
public class TriggerDatum {

	@JsonProperty("optionalData")
	private List<OptionalDatum> optionalData = new ArrayList<OptionalDatum>();
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	ObjectMapper mapper = new ObjectMapper();	

	private Order orderValue;
	private BillingAddr billingInfo;
	private List<Payments> payments; 
	private List<Shipments> shipments;
	private List<Recommendations> recommendations;
	private Loyalty loyalty; 
	private OrderTotals orderTotals;
	private OrderBase orderBaseData;
	private Website website;
	/**
	 * 
	 * @return The optionalData
	 */
	@JsonProperty("optionalData")
	public List<OptionalDatum> getOptionalData() {
		return optionalData;
	}

	/**
	 * 
	 * @param optionalData
	 *            The optionalData
	 */
	@JsonProperty("optionalData")
	public void setOptionalData(List<OptionalDatum> optionalData) {
		this.optionalData = optionalData;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	private OptionalDatum getNodeByNodeName(String nodeName) {
		for (OptionalDatum node : this.getOptionalData()) {
			if (node.getName().equals(nodeName)) {
				return node;
			}
		}
		return null;
	}

	public OptionalDatum getOESTransactionID() {
		return this.getNodeByNodeName("OES_TRANSACTION_ID");
	}

	public OptionalDatum getBrandType() {
		return this.getNodeByNodeName("BRAND_TYPE");
	}

	public OptionalDatum getMailType() {
		return this.getNodeByNodeName("MAIL_TYPE");
	}

	public OptionalDatum getMailSubType() {
		return this.getNodeByNodeName("MAIL_SUB_TYPE");
	}

	public OptionalDatum getClientID() {
		return this.getNodeByNodeName("CLIENT_ID");
	}

	public OptionalDatum getReservationNumber() {
		return this.getNodeByNodeName("RESERVATION_NUMBER");
	}

	public OptionalDatum getInternetOrderNumber() {
		return this.getNodeByNodeName("INTERNET_ORDER_NUMBER");
	}
	
	 public OptionalDatum getOrderBase() {
		  return this.getNodeByNodeName("orderBase");
		 }
		 public OrderBase getOrderBaseData() throws JsonParseException, JsonMappingException, IOException {
		  return orderBaseData = mapper.readValue(this.getNodeByNodeName("orderBase").getValue(),OrderBase.class);
		 }

	//public OptionalDatum getOrderBase() {
		//return this.getNodeByNodeName("orderBase");
	//}

	public OptionalDatum getOrder() {
		return this.getNodeByNodeName("order");
	}
	
	public Order getOrderData() throws JsonParseException, JsonMappingException, IOException{
		orderValue = mapper.readValue(this.getNodeByNodeName("order").getValue(),Order.class);
		return orderValue;
	}
	
	public OptionalDatum getOrderTotals() {
		return this.getNodeByNodeName("orderTotals");
	}
	
	public OrderTotals getOrderTotalsData() throws JsonParseException, JsonMappingException, IOException{
		return orderTotals = mapper.readValue(this.getNodeByNodeName("orderTotals").getValue(),OrderTotals.class);
	}

	public OptionalDatum getBillingAddr() {
		return this.getNodeByNodeName("billingAddr");
		
	}
	
	public BillingAddr getBillingData() throws JsonParseException, JsonMappingException, IOException{
		billingInfo = mapper.readValue(this.getNodeByNodeName("billingAddr").getValue(),BillingAddr.class);
		return billingInfo;
	}

	public OptionalDatum getPayments() {
		return this.getNodeByNodeName("payments");
	}
	
	public List<Payments> getPaymentData() throws JsonParseException, JsonMappingException, IOException{
		//payments =  (List<Payments>) mapper.readValue(this.getNodeByNodeName("payments").getValue(),Payments.class);
		payments = mapper.readValue(this.getNodeByNodeName("payments").getValue(),TypeFactory.defaultInstance().constructCollectionType(List.class,Payments.class));
		return  payments;
	}

	public OptionalDatum getShipments(){
		return this.getNodeByNodeName("shipments");
	}
	
	public List<Shipments> getShipmentsData() throws JsonParseException, JsonMappingException, IOException{
		shipments = mapper.readValue(this.getNodeByNodeName("shipments").getValue(),TypeFactory.defaultInstance().constructCollectionType(List.class,Shipments.class));
		return  shipments;
		//this.getNodeByNodeName("shipments").getValue();
		//return null;
	}

	public OptionalDatum getLoyalty() {
		return this.getNodeByNodeName("loyalty");
	}
	
	public Loyalty getLoyaltyData() throws JsonParseException, JsonMappingException, IOException{
		loyalty = mapper.readValue(this.getNodeByNodeName("loyalty").getValue(),Loyalty.class);
		//		loyalty = mapper.readValue(this.getNodeByNodeName("loyalty").getValue(),TypeFactory.defaultInstance().constructCollectionType(List.class,Loyalty.class));
		return  loyalty;
	}

	public OptionalDatum getRecommendations() {
		return this.getNodeByNodeName("recommendations");
	}

	public List<Recommendations> getRecommendationsData() throws JsonParseException, JsonMappingException, IOException{
		recommendations = mapper.readValue(this.getNodeByNodeName("recommendations").getValue(),TypeFactory.defaultInstance().constructCollectionType(List.class,Recommendations.class));
		return  recommendations;
	}
	public OptionalDatum getWebsite() {
		return this.getNodeByNodeName("website");
	}
	
	public Website getWebsiteData() throws JsonParseException, JsonMappingException, IOException{
		website = mapper.readValue(this.getNodeByNodeName("website").getValue(),Website.class);
		return  website;
	}
	
	public OptionalDatum getReplyToName() {
		return this.getNodeByNodeName("REPLY_TO_DISPLAY_NAME");
	}


}
