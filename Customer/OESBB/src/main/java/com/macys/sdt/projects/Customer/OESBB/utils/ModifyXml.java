package com.macys.sdt.projects.Customer.OESBB.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModifyXml {

	private static final Logger log = LoggerFactory.getLogger(ModifyXml.class);
	public static void updateReservationNumber(String filepath, String reservationNumber) {
		
		try {
			log.debug("File path:"+filepath);
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
	         
			// Get the root element
			Node mailMessagePayload = (Node) doc.getFirstChild();
			
			NodeList list = mailMessagePayload.getChildNodes();

					

			for (int i = 0; i < list.getLength(); i++) {
				Node node = list.item(i);
			   if ("reservationNumber".equals(node.getNodeName())) {
				node.setTextContent(reservationNumber);				
			   }
			}

			log.debug("Reservation number updated");
			writeContentsToFile(doc, filepath);

		   } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		   } catch (IOException ioe) {
			ioe.printStackTrace();
		   } catch (SAXException sae) {
			sae.printStackTrace();
		   }
		}
	
	public static void removeNodes(String filepath, String sectionName) {
		
		try {
			log.debug("File path:"+filepath);
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
	         
			// Get the root element
			Node mailMessagePayload = (Node) doc.getFirstChild();			
			NodeList list = mailMessagePayload.getChildNodes();
			for (int i = 0; i < list.getLength(); i++) {

	           Node node = list.item(i);           
	           
	           //Payment detail
	           if(sectionName.equals(node.getNodeName())){	        	  
	        	   mailMessagePayload.removeChild(node);
	        	   break;	   	        	   
	           }
	           
	           //Item detail
	           if("shipmentDetail".equals(node.getNodeName())){	        	  
	        	   NodeList shipmentDetail = node.getChildNodes();
	        	   for (int j = 0; j < shipmentDetail.getLength(); j++) {
	        		   Node shipdetails = shipmentDetail.item(j);
	        		   if(sectionName.equals(shipdetails.getNodeName())){
	        			   node.removeChild(shipdetails);
	        		   }
	        	   }	   	        	   
	           }
			}					
			
			String destinationPath = "Customer/OESBB/src/main/resources/data/payloads/mcom_remove_sections_and_trigger.xml";
			writeContentsToFile(doc, destinationPath);							

		   } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		   } catch (IOException ioe) {
			ioe.printStackTrace();
		   } catch (SAXException sae) {
			sae.printStackTrace();
		   }		
	}
	
	private static void writeContentsToFile(Document doc, String destinationPath) {
		// write the content into xml file
		try{
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);	
					
		Result output = new StreamResult( new FileOutputStream(destinationPath) );
		transformer.transform(source, output);
		}
		catch (TransformerException tfe) {
			tfe.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}												
	}

}
