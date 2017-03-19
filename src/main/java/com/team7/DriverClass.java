package com.team7;

import java.io.File;
import java.sql.SQLException;

import javax.xml.bind.JAXBException;

@SuppressWarnings("restriction")
public class DriverClass {

	/*
	 * This class contains main method.
	 */

	public static void main (String []args) throws ClassNotFoundException, SQLException, JAXBException {
		
		System.out.println("Start");

		File file = new File("input/dblp.xml");
		ImplementSchemaDB db = new ImplementSchemaDB();
		ImplementParseDatabase parse = new ImplementParseDatabase();
		System.setProperty("jdk.xml.entityExpansionLimit", "0");	
		
		db.dbSetUp();
		parse.parseXml(file);
		
		System.out.println("end");
		
	}

}
