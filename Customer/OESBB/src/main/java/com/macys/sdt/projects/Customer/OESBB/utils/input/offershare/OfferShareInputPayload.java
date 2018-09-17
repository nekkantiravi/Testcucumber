package com.macys.sdt.projects.Customer.OESBB.utils.input.offershare;

import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * @author snanda
 *
 */
public class OfferShareInputPayload {

	public QA650_1A_MCOM payload;

	/**
	 * @throws JAXBException
	 * 
	 */
	public OfferShareInputPayload(File xmlFile) throws JAXBException {
		this.payload = (QA650_1A_MCOM) JAXBContext.newInstance(QA650_1A_MCOM.class).createUnmarshaller()
				.unmarshal(xmlFile);
	}

	public String getXML() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(QA650_1A_MCOM.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		final StringWriter stringWriter = new StringWriter();
		jaxbMarshaller.marshal(payload, stringWriter);
		return stringWriter.toString();
	}

	public QA650_1A_MCOM getPayloadObject() {
		return this.payload;
	}

}