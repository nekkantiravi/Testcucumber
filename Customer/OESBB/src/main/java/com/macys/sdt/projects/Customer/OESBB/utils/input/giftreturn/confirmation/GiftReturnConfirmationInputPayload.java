package com.macys.sdt.projects.Customer.OESBB.utils.input.giftreturn.confirmation;

import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * @author Saravanan Alagarsamy saravanan.alagarsamy@macys.com
 *
 */
public class GiftReturnConfirmationInputPayload {

	public QA244_2_MCOM payload;

	/**
	 * @throws JAXBException
	 * 
	 */
	public GiftReturnConfirmationInputPayload(File xmlFile) throws JAXBException {
		this.payload = (QA244_2_MCOM) JAXBContext.newInstance(QA244_2_MCOM.class).createUnmarshaller()
				.unmarshal(xmlFile);
	}

	public String getXML() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(QA244_2_MCOM.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		final StringWriter stringWriter = new StringWriter();
		jaxbMarshaller.marshal(payload, stringWriter);
		return stringWriter.toString();
	}

	public QA244_2_MCOM getPayloadObject() {
		return this.payload;
	}

}