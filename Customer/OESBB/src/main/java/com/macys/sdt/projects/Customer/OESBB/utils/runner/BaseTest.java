package com.macys.sdt.projects.Customer.OESBB.utils.runner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;

import com.macys.sdt.projects.Customer.OESBB.utils.OesbbApplication;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.macys.sdt.projects.Customer.OESBB.utils.EnvironmentProperties;
import com.macys.sdt.projects.Customer.OESBB.utils.ModifyXml;
import com.macys.sdt.projects.Customer.OESBB.utils.model.EnhancedPayload;
import com.macys.sdt.projects.Customer.OESBB.utils.input.eod.InputPayloadForEod;
import com.macys.sdt.projects.Customer.OESBB.utils.input.offershare.OfferShareInputPayload;
import com.macys.sdt.projects.Customer.OESBB.utils.input.orderadjustment.OrderAdjustmentInputPayload;
import com.macys.sdt.projects.Customer.OESBB.utils.input.profile.ProfileInputPayload;
import com.macys.sdt.projects.Customer.OESBB.utils.input.security.SecurityInputPayload;
import com.macys.sdt.projects.Customer.OESBB.utils.input.subscrption.SubscriptionInputPayload;
import com.macys.sdt.projects.Customer.OESBB.utils.csp.SendEmailInputData;
import com.macys.sdt.projects.Customer.OESBB.utils.ui_csp.CustomerServiceTool;
import com.macys.sdt.projects.Customer.OESBB.utils.ui_csp.SendEmailPage;
import com.macys.sdt.projects.Customer.OESBB.utils.service.ArchivedMessageRepository;
import com.macys.sdt.projects.Customer.OESBB.utils.service.MessageRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebAppConfiguration
@ContextConfiguration(classes = OesbbApplication.class)
public class BaseTest {
    @Autowired
    Environment env;

    // Connects to Message table provided in application.properties
    @Autowired
    MessageRepository message;

    // Connects to Message table provided cspServerin application.properties
    @Autowired
    ArchivedMessageRepository archive;

    @Value("${csp.server}")
    private String cspServer;
    @Value("${cella.server}")
    private String cellaServer;
    @Value("${cellb.server}")
    private String cellbServer;
    @Value("${dal.server}")
    private String dalServer;

    @PostConstruct
    public void init() {
    }

    public BaseTest() {
    }

/*
	PropertyStore propertyStore = PropertyStore.getInstance("BaseTest.Init");

	Environment env = (Environment)propertyStore.get_value("Environment");

	// Connects to Message table provided in application.properties
	MessageRepository message = (MessageRepository)propertyStore.get_value("MessageRepository");

	// Connects to Message table provided cspServerin application.properties
	ArchivedMessageRepository archive = (ArchivedMessageRepository)propertyStore.get_value("ArchivedMessageRepository");

	private String cspServer = (String)propertyStore.get_value("csp.server");
	private String cellaServer = (String)propertyStore.get_value("cella.server");
	private String cellbServer = (String)propertyStore.get_value("cellb.server");
	private String dalServer = (String)propertyStore.get_value("dal.server");
*/

	// Enhanced Payload
	protected static EnhancedPayload enhancedPayloadExpected,
			enhancedPayloadActual;

	ObjectMapper mapper = new ObjectMapper();
	public Map<String, String> template = new HashMap<String, String>();
	public OrderAdjustmentInputPayload orderAdjustment;
	SubscriptionInputPayload subscriptionAdjustment;
	ProfileInputPayload profileAdjustment;
	SecurityInputPayload securityAdjustment;
	OfferShareInputPayload offerShareAdjustment;
	InputPayloadForEod inputPayloadForEod;
	CustomerServiceTool cst;
	SendEmailPage sendEmailPage;
	SendEmailInputData sendEmailInputData = new SendEmailInputData();
	String fullPath = "";
	public static String eod_template_name;

	private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

	public String getSourceSystemID() {
		return sendEmailInputData.getSourceSystemID();
	}

	public Response getHttpStatuscode(String url) throws IOException {
		Response response = Jsoup.connect(url).execute();
		return response;
	}

	public boolean triggerCSP(String tempalteName) throws JAXBException,
			IOException {
		String[] config = tempalteName.split("_");
		template.put("source", config[1]);
		template.put("type", config[2]);
		template.put("subtype", config[3]);
		template.put("brand", config[0]);
		sendEmailInputData.setTypeID(template.get("type"));
		sendEmailInputData.setSubTypeID(template.get("subtype"));
		sendEmailInputData.setBrand(template.get("brand"));
		sendEmailInputData.setSourceSystemType(template.get("source"));
		// String fullPath ="";

		String template_name = template.get("type") + "_"
				+ template.get("subtype") + "_" + template.get("brand");
		 eod_template_name = template.get("source") + "_"
				+ template.get("type") + "_" + template.get("subtype") + "_"
				+ template.get("brand");

		Properties properties = new Properties();

		InputStream inputStream = new FileInputStream(
				"Customer/OESBB/src/main/resources/data/config/InputPayloadTypes.properties");

		properties.load(inputStream);
		if (Arrays.asList(config).contains("optional")) {
			fullPath = "Customer/OESBB/src/main/resources/data/payloads/"
					+ template.get("brand") + "/" + template.get("source")
					+ "/" + "optional/" + tempalteName + ".xml";
		} else if (properties.get("mcom_beauty_box_subscription_email")
				.toString().contains(template_name)) {

			fullPath = "Customer/OESBB/src/main/resources/data/payloads/"
					+ template.get("brand") + "/" + template.get("source")
					+ "/" + tempalteName + ".json";
		}
		// else
		// if(properties.get("mcom_shipment_confirmation_eod_email").toString().contains(eod_template_name)){
		else if (eod_template_name.toString().contains("eod")) {

			fullPath = "Customer/OESBB/src/main/resources/data/payloads/"
					+ template.get("brand") + "/" + template.get("source")
					+ "/" + tempalteName + ".json";
		}

		else {
			fullPath = "Customer/OESBB/src/main/resources/data/payloads/"
					+ template.get("brand") + "/" + template.get("source")
					+ "/" + tempalteName + ".xml";
		}

		if (properties.get("mcom_loyalty_xml").toString()
				.contains(tempalteName)) {

			ModifyXml.updateReservationNumber(fullPath,
					properties.get("mcom_loyalty_reservation_number")
							.toString());

		}

		if (properties.get("bcom_loyalty_xml").toString()
				.contains(tempalteName)) {

			ModifyXml.updateReservationNumber(fullPath,
					properties.get("bcom_loyalty_reservation_number")
							.toString());
		}

		File file = new File(fullPath);

		if (properties.get("profile_templates_name").toString()
				.contains(template_name)) {
			profileAdjustment = new ProfileInputPayload(file);
			sendEmailInputData.setPayloadXml(profileAdjustment.getXML());
		} else if (properties.get("security_templates_name").toString()
				.contains(template_name)) {
			securityAdjustment = new SecurityInputPayload(file);
			sendEmailInputData.setPayloadXml(securityAdjustment.getXML());
		} else if (properties.get("offer_share_template_name").toString()
				.contains(template_name)) {
			offerShareAdjustment = new OfferShareInputPayload(file);
			sendEmailInputData.setPayloadXml(offerShareAdjustment.getXML());
		} else if (properties.get("mcom_beauty_box_subscription_email")
				.toString().contains(template_name)) {
			subscriptionAdjustment = new SubscriptionInputPayload(file);
			sendEmailInputData.setPayloadXml(subscriptionAdjustment.getJSON());
		}
		else if (eod_template_name.toString().contains("eod")) {
			String eodInputPayload = getEODPayloadAsString(file, sendEmailInputData.getSourceSystemID());
			eodInputPayload = eodInputPayload.replace("oes.macys@gmail.com", sendEmailInputData.getEmailAddress());
			sendEmailInputData.setPayloadXml(eodInputPayload);
		}

		else {
			orderAdjustment = new OrderAdjustmentInputPayload(file);
			sendEmailInputData.setPayloadXml(orderAdjustment.getXML());
		}

		OESBBLogger.setCurrentConfiguration(OESBBLogger.STATUS, "TRIGGER_CSP");
		String serverType = EnvironmentProperties.getProdEnvironment();
		String cspServerHost = null;

		if (serverType == null) {
			cspServerHost = cspServer;
		} else if (serverType.equals("cellA")) {
			cspServerHost = cellaServer;
		} else if (serverType.equals("cellB")) {
			cspServerHost = cellbServer;
		} else if (serverType.equals("DAL")) {
			cspServerHost = dalServer;
		}

		cst = new CustomerServiceTool(cspServerHost);
		if (serverType == null) {
			cst.loginAsUser("espmigration", "espmigration");
		} else {
			cst.loginAsUser("yc05sg3", "yc05sg3");
		}
		sendEmailPage = new SendEmailPage();
		return sendEmailPage.populatePageWithData(sendEmailInputData);
	}
	
	public String getEODPayloadAsString(File file, String dynamicSourceID) {

		StringBuilder buffer = new StringBuilder();
		String payloadText = "";
		String eodSourcePayload = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));

			payloadText = br.readLine();
			
			while (payloadText != null) {
				buffer.append(payloadText.trim());
				payloadText = br.readLine();
			}
			
			eodSourcePayload = buffer.toString();
			eodSourcePayload = eodSourcePayload.replace("EOD_MailSourceID", dynamicSourceID);
						
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return eodSourcePayload;
	}

	public boolean deleteAllEmails() {
		final String uri = "https://macys-oes-email.herokuapp.com/gmail/delete/all";
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		Assert.assertEquals("All oes.macys@gmail.com inbox emial deleted",
				"true", result);
		return true;
	}

	protected boolean getActualPayload() throws IOException {
		OESBBLogger.setCurrentConfiguration(OESBBLogger.STATUS, "GET_ACTUAL_PAYLOAD");
		
		
		Long id =message.getMessageBySourceMailID(sendEmailInputData.getSourceSystemID(), "E").getMailID();
		
		enhancedPayloadActual = null;
		for (int second = 0; second < 30 && enhancedPayloadActual == null; second++) {
			try {
				Thread.sleep(1000);
				enhancedPayloadActual = mapper.readValue(archive
						.getArchivedMessageByMailID(id).getEnchancedPayload(),
						EnhancedPayload.class);
			} catch (EmptyResultDataAccessException e) {
				log.debug("getActualPayload(" + second
						+ ") = EmptyResultDataAccessException");
			} catch (Exception e) {
				e.printStackTrace();
				Assert.assertNotNull(
						"getActualPayload(" + second + ") = " + e.getMessage(),
						enhancedPayloadActual);
			}
		}
		Assert.assertNotNull(
				"getActualPayload(final) = enhancedPayloadActual is null!",
				enhancedPayloadActual);
		return true;
	}

	protected boolean waitForStatusPC(String sourceSystemID) {
		OESBBLogger.setCurrentConfiguration(OESBBLogger.STATUS, "WAIT_FOR_STATUS_PC");
		boolean returnValue = false;
		int second;
		for (second = 0; second <= 60; second++) {
			try {
				if (message.getMessageBySourceMailID(sourceSystemID)
						.getMsgStatCD().equals("PC")) {
					returnValue = true;
					break;
				}
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return returnValue;
	}

	public boolean modifyPayloadAndTriggerCsp(String tempalteName,
			String sectionName) throws JAXBException, IOException {
		String[] config = tempalteName.split("_");
		template.put("source", config[1]);
		template.put("type", config[2]);
		template.put("subtype", config[3]);
		template.put("brand", config[0]);
		sendEmailInputData.setTypeID(template.get("type"));
		sendEmailInputData.setSubTypeID(template.get("subtype"));
		sendEmailInputData.setBrand(template.get("brand"));
		sendEmailInputData.setSourceSystemType(template.get("source"));
		String fullPath = "";

		fullPath = "Customer/OESBB/src/main/resources/data/payloads/" + template.get("brand")
				+ "/" + template.get("source") + "/" + tempalteName + ".xml";

		String template_name = template.get("type") + "_"
				+ template.get("subtype") + "_" + template.get("brand");
		Properties properties = new Properties();

		InputStream inputStream = new FileInputStream(
				"Customer/OESBB/src/main/resources/data/config/InputPayloadTypes.properties");
		properties.load(inputStream);
		ModifyXml.removeNodes(fullPath, sectionName);

		File file = new File(
				"Customer/OESBB/src/main/resources/data/payloads/mcom_remove_sections_and_trigger.xml");

		if (properties.get("profile_templates_name").toString()
				.contains(template_name)) {
			profileAdjustment = new ProfileInputPayload(file);
			sendEmailInputData.setPayloadXml(profileAdjustment.getXML());
		} else if (properties.get("security_templates_name").toString()
				.contains(template_name)) {
			securityAdjustment = new SecurityInputPayload(file);
			sendEmailInputData.setPayloadXml(securityAdjustment.getXML());
		} else if (properties.get("offer_share_template_name").toString()
				.contains(template_name)) {
			offerShareAdjustment = new OfferShareInputPayload(file);
			sendEmailInputData.setPayloadXml(offerShareAdjustment.getXML());
		} else {
			orderAdjustment = new OrderAdjustmentInputPayload(file);
			sendEmailInputData.setPayloadXml(orderAdjustment.getXML());
		}

		OESBBLogger.setCurrentConfiguration(OESBBLogger.STATUS, "TRIGGER_CSP");
		cst = new CustomerServiceTool(cspServer);
		cst.loginAsUser("espmigration", "espmigration");
		sendEmailPage = new SendEmailPage();
		return sendEmailPage.populatePageWithData(sendEmailInputData);
	}
}