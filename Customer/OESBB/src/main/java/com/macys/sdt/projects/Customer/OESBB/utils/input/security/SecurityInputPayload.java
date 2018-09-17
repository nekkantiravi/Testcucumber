package com.macys.sdt.projects.Customer.OESBB.utils.input.security;

import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * @author nmangali
 *
 */
public class SecurityInputPayload {

	public QA613_1A_BLCOM payload;

	/**
	 * @throws JAXBException
	 * 
	 */
	public SecurityInputPayload(File xmlFile) throws JAXBException {
		this.payload = (QA613_1A_BLCOM) JAXBContext.newInstance(QA613_1A_BLCOM.class).createUnmarshaller()
				.unmarshal(xmlFile);
	}

	public String getXML() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(QA613_1A_BLCOM.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		final StringWriter stringWriter = new StringWriter();
		jaxbMarshaller.marshal(payload, stringWriter);
		return stringWriter.toString();
	}

	public QA613_1A_BLCOM getPayloadObject() {
		return this.payload;
	}

}
