package com.team7.interfaces;

import java.io.IOException;
import java.sql.SQLException;

import javax.xml.bind.JAXBException;

public interface ParseXml {
	
	//Parses XML to human readable format 
	public String parseXml() throws JAXBException, SQLException,IOException;
}
