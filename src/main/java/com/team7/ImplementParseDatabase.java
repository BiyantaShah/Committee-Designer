package com.team7;

import java.io.*;
<<<<<<< HEAD
=======
import java.sql.Connection;
import java.sql.PreparedStatement;
>>>>>>> master
import java.sql.SQLException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

@SuppressWarnings("restriction")
public class ImplementParseDatabase implements ParseDatabase {
	
	ImplementSchemaDB db = new ImplementSchemaDB();


	public String parseXml(File file) throws JAXBException, SQLException {
		
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


	public String parseXml1(File file) throws JAXBException, SQLException {
		
			JAXBContext jaxbContext = JAXBContext.newInstance(dblp.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			
			System.setProperty("javax.xml.accessExternalDTD", "all");
			System.setProperty("jdk.xml.maxGeneralEntitySizeLimit","0");
			System.setProperty("jdk.xml.entityExpansionLimit","0");
			
			dblp data = (dblp)  jaxbUnmarshaller.unmarshal(file);

			Connection conn = db.getConnection();
			final int batchSize = 10000;
			int i=0, j=0, k=0;
			
			if (data.getInproceedings() != null) {
				PreparedStatement statement_inproceedings = conn.prepareStatement("insert into Paper(title,year,pages,paperKey)"
					 	+ "values(?,?,?,?)");
				
				PreparedStatement statement_author = conn.prepareStatement("insert into author(name,paper_key) values (?,?)");
			
				for (Paper paper: data.getInproceedings()) {
					
					if ((paper.getAuthor() == null)) 
						// author is null then don't make an 
						// object of either paper or author
						continue;
					
					statement_inproceedings.setString(1,paper.title);
					statement_inproceedings.setInt(2,paper.year);
					statement_inproceedings.setString(3,  paper.pages);
					statement_inproceedings.setString(4, paper.key);
	     			statement_inproceedings.addBatch();
					
					for (String author: paper.author) {
						Author auth = new Author(author, paper.key);
						
						statement_author.setString(1, auth.name); 
						statement_author.setString(2, auth.key);
						statement_author.addBatch();
					}
					
					if (++i % batchSize == 0){
						statement_inproceedings.executeBatch();
					}

					if (++j % batchSize == 0){
						statement_author.executeBatch();
					}
					
				}
				statement_inproceedings.executeBatch();
				statement_author.executeBatch();
			}
			
			if (data.getProceedings() != null) {
				
				PreparedStatement statement_conference = conn.prepareStatement("insert into conference(conf_key,name,conf_detail) values (?,?,?)");
				
				for (Conference conf: data.getProceedings()) {
					statement_conference.setString(1, conf.key);
					statement_conference.setString(2, conf.booktitle);
					statement_conference.setString(3, conf.title);
					statement_conference.addBatch(); 

					if (++k % batchSize == 0){
						statement_conference.executeBatch();
					}
				}
				statement_conference.executeBatch();
			}
			
			// only for testing purposes
			if (data.getGarbage() != null) {
				for (Garbage gar : data.getGarbage()) {
					if (gar.test.equals("This is a test"))
						return "success";
					else 
						return "failure";
				}	
			}
			
			return "success";
		}		
	}
