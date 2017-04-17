package com.team7.interfaces;

import java.io.IOException;
import java.sql.SQLException;

import javax.xml.bind.JAXBException;

/**
 * The Interface ParseXml.
 */
public interface ParseXml {
	
	/**
	 * Parses the xml.
	 *
	 * @return the string
	 * @throws JAXBException the JAXB exception
	 * @throws SQLException the SQL exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	//Parses XML to human readable format 
	public String parseXml() throws JAXBException, SQLException,IOException;
}
