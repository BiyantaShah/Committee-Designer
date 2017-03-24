package com.team7;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.xml.bind.JAXBException;

@SuppressWarnings("restriction")
public class DriverClass {

	/*
	 * This class contains main method.
	 */

	public static void main (String []args) throws JAXBException, ClassNotFoundException, SQLException, IOException {
	
		System.out.println("Start");
		
		File file = new File("input/dblp.xml");
		File comData = new File("input/committees/");
		// Parsing the xml to create objects
		ImplementParseDatabase parse = new ImplementParseDatabase();
		ImplementSchemaDB db = new ImplementSchemaDB();
		ImplementCommittees com = new ImplementCommittees();
		
		db.dbSetUp();   //set up initial database
		parse.parseXml(file);	//parse xml data
		com.ParseFiles(comData); //parse committee data
		
		System.out.println("End");
	}

}

	