package com.macys.sdt.projects.Customer.OESBB.utils.input.price.error;

import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * @author Saravanan Alagarsamy saravanan.alagarsamy@macys.com
 *
 */
public class PriceErrorInputPayload {

	public QA409_28_MCOM payload;

	/**
	 * @throws JAXBException
	 * 
	 */
	public PriceErrorInputPayload(File xmlFile) throws JAXBException {
		this.payload = (QA409_28_MCOM) JAXBContext.newInstance(QA409_28_MCOM.class).createUnmarshaller()
				.unmarshal(xmlFile);
	}

	public String getXML() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(QA409_28_MCOM.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		final StringWriter stringWriter = new StringWriter();
		jaxbMarshaller.marshal(payload, stringWriter);
		return stringWriter.toString();
	}

	public QA409_28_MCOM getPayloadObject() {
		return this.payload;
	}

}