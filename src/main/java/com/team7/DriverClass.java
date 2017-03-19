package com.team7;

import java.io.File;
import java.sql.SQLException;

import javax.xml.bind.JAXBException;

@SuppressWarnings("restriction")
public class DriverClass {

	/*
	 * This class contains main method.
	 */

	public static void main (String []args) throws JAXBException, ClassNotFoundException, SQLException {
	
		System.out.println("Start");
		
		File file = new File("input/dblp.xml");
		// Parsing the xml to create objects
		ImplementParseDatabase parse = new ImplementParseDatabase();
		ImplementSchemaDB db = new ImplementSchemaDB();
		db.dbSetUp();
		parse.parseXml(file);
		System.out.println("ENDD");
	}
}

	