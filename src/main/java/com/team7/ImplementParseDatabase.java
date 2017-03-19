package com.team7;

import java.io.*;
import java.sql.SQLException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


@SuppressWarnings("restriction")
public class ImplementParseDatabase implements ParseDatabase {
	
	ImplementSchemaDB db = new ImplementSchemaDB();


	public void parseXml(File file) throws JAXBException, SQLException {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(dblp.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		
		System.setProperty("javax.xml.accessExternalDTD", "all");
		System.setProperty("jdk.xml.maxGeneralEntitySizeLimit","0");
		System.setProperty("jdk.xml.entityExpansionLimit","0");
		
		dblp data = (dblp)  jaxbUnmarshaller.unmarshal(file);	
		
		
		if(data.getInproceedings() != null) {
			
			for (Paper paper: data.getInproceedings()) {
				
				db.insertData(paper);
				
				if(paper.getAuthor() == null)
					continue;
					
				for (String author: paper.author) {
					Author auth = new Author(author, paper.key);
					db.insertData(auth);
				}		
			}
			
			for (Conference conf: data.getProceedings()) {
	//			System.out.println(conf.booktitle);
				db.insertData(conf);
			}
		}
			
	}

}