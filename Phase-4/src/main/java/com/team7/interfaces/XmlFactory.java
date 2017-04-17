package com.team7.interfaces;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.xml.bind.JAXBException;

import com.team7.parsing.ImplementParseDatabase;

public class XmlFactory extends AbstractParseFactory {

	public ParseXml getXml(String xml) throws JAXBException, SQLException, IOException {
		
		if (xml == null) {
			return null;
		}
		
		if (xml.equals("input/dblp.xml") || xml.equals("msdxml.xml")) {
			
			return new ImplementParseDatabase(new File (xml));			
		}
		
		return null;
	}

	public ParseCsvFiles getCsv(String csv) {
		return null;
	}


	public ParseTextFile getText(String txt) {
		return null;
	}
}
