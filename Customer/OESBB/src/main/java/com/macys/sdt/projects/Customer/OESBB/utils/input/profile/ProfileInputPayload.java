package com.macys.sdt.projects.Customer.OESBB.utils.input.profile;

import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * @author sbharadwaja
 *
 */
public class ProfileInputPayload {

	public QA602_1C_BLCOM payload;

	/**
	 * @throws JAXBException
	 * 
	 */
	public ProfileInputPayload(File xmlFile) throws JAXBException {
		this.payload = (QA602_1C_BLCOM) JAXBContext.newInstance(QA602_1C_BLCOM.class).createUnmarshaller()
				.unmarshal(xmlFile);
	}

	public String getXML() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(QA602_1C_BLCOM.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		final StringWriter stringWriter = new StringWriter();
		jaxbMarshaller.marshal(payload, stringWriter);
		return stringWriter.toString();
	}

	public QA602_1C_BLCOM getPayloadObject() {
		return this.payload;
	}

}
