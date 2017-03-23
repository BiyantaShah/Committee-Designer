package com.team7;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

			Connection conn = db.getConnection();
			final int batchSize = 10000;
			int i=0, j=0, k=0, l=0;
			
			

			
			if(data.getWww() != null){
				
				PreparedStatement statement_author =  conn.prepareStatement("insert into author(name,url) values (?,?)");
								
				for(Author auth : data.getWww()){
					
					if(auth.author != null && auth.url != null){
												
						for(String name : auth.author)
		        			{
								statement_author.setString(1,name);
								statement_author.setString(2,auth.url);
								statement_author.addBatch();
							}
							
							if (++i % batchSize == 0){
								
							    statement_author.executeBatch();
						    }
										
						}
				}	
				
 				statement_author.executeBatch();

			}
			
			if (data.getInproceedings() != null) {
				
				PreparedStatement statement_inproceedings = conn.prepareStatement("insert into Paper(author,title,year,pages,confName,journalName)"
					 	+ "values(?,?,?,?,?,?)");
				
			
				for (Paper paper: data.getInproceedings()) {
					
					if ((paper.getAuthor() == null)) 
						// author is null then don't make an object of either paper 
						continue;
					
					if(!isUTF8MisInterpreted(paper.getTitle(),"Windows-1252")){
						continue;
					}
										
				    String[] output  = new String[3];
				    output = paper.key.split("/");
				    String confName = null;
				    String journalName = null;
				    
				    if (output[0].equals("conf")){
				    	
				    	confName = output[1];
				    	
				    }else if(output[0].equals("journals")){
				    	
				    	 journalName = output[1];
				    	
				    }
					
					
					for (String author: paper.author) {
												
						statement_inproceedings.setString(1,author);
						statement_inproceedings.setString(2,paper.title);
						statement_inproceedings.setInt(3,paper.year);
						statement_inproceedings.setString(4,paper.pages);
						statement_inproceedings.setString(5,confName);
						statement_inproceedings.setString(6,journalName);
		     			statement_inproceedings.addBatch();
					}
					
					if (++j % batchSize == 0){
						statement_inproceedings.executeBatch();
					}
					
				}
				statement_inproceedings.executeBatch();
			}
			
			if (data.getProceedings() != null) {
				
				PreparedStatement statement_conference = conn.prepareStatement("insert into conference(confKey,name,confDetail) values (?,?,?)");
				
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
			
			if (data.getArticle() != null) {
				
				PreparedStatement statement_article = conn.prepareStatement("insert into article(author,title,year,month,ee) values (?,?,?,?,?)");
				
				for (Article article: data.getArticle()) {
					
					if (article.author ==  null)
						continue;
					
					if(!isUTF8MisInterpreted(article.getTitle(),"Windows-1252")){
						continue;
					}

					
					for (String author: article.author) {
						statement_article.setString(1, author);
						statement_article.setString(2, article.title);
						statement_article.setInt(3, article.year);
						statement_article.setString(4, article.month);
						statement_article.setString(5, article.ee);
						
						statement_article.addBatch();
					}
					if (++l % batchSize == 0)
						statement_article.executeBatch();
				}
				statement_article.executeBatch();
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

	private boolean isUTF8MisInterpreted(String input, String encoding) {
		// TODO Auto-generated method stub
	    CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
	    CharsetEncoder encoder = Charset.forName(encoding).newEncoder();
	    ByteBuffer tmp;
	    try {
	        tmp = encoder.encode(CharBuffer.wrap(input));
	    }

	    catch(CharacterCodingException e) {
	        return false;
	    }

	    try {
	        decoder.decode(tmp);
	        return true;
	    }
	    catch(CharacterCodingException e){
	        return false;
	    }       
	 }		
	}
