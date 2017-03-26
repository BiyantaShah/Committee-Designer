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

	// Parsing the dblp.xml to extract required data, 
	// converting into java objects and inserting into the database
	public String parseXml(File file) throws JAXBException, SQLException {

		JAXBContext jaxbContext = JAXBContext.newInstance(dblp.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

		// to allow access to dblp.dtd
		System.setProperty("javax.xml.accessExternalDTD", "all");

		// for files with size > 1 GB
		System.setProperty("jdk.xml.maxGeneralEntitySizeLimit","0");
		System.setProperty("jdk.xml.entityExpansionLimit","0");

		// Creating the dblp object from the xml file.
		dblp data = (dblp)  jaxbUnmarshaller.unmarshal(file);

		// getting connection to the database
		Connection conn = db.getConnection();
		final int batchSize = 10000; 
		int i=0, j=0, k=0, l=0, m=0;

		// Extracting required information from the dblp object
		// Information about the home-pages, authors, papers, conferences and 
		// articles have been extracted and inserted into respective tables in the database

		if(data.getWww() != null){

			PreparedStatement statement_authorD =  conn.prepareStatement("insert into author_details(name,url) values (?,?)");

			for(AuthorDetails auth : data.getWww()){

				// If the author name and url is empty then do not insert
				if(auth.author != null && auth.url != null){

					for(String name : auth.author)
					{
						statement_authorD.setString(1,name);
						statement_authorD.setString(2,auth.url);
						statement_authorD.addBatch();
					}					
					if (++i % batchSize == 0){			
						statement_authorD.executeBatch();
					}
				}
			}	
			statement_authorD.executeBatch();
		}

		if (data.getInproceedings() != null) {

			PreparedStatement statement_inproceedings = conn.prepareStatement("insert into Paper(title,year,pages,confName,paperKey)"
					+ "values(?,?,?,?,?)");

			PreparedStatement statement_author = conn.prepareStatement("insert into author(name,paperKey) values (?,?)");

			for (Paper paper: data.getInproceedings()) {
				
				// author is null then don't make an object of either paper or author 
				if ((paper.getAuthor() == null)) 
					continue;

				// UTF8  misinterpreted characters have been found in the title, 
				// such titles have not been inserted into the database
				if(!isUTF8MisInterpreted(paper.getTitle(),"Windows-1252")){
					continue;
				}

				// papers having empty titles are not needed
				if (paper.title.equals("")) {
					continue;
				}

				// extracting conference name from the paper key
				String[] output  = new String[3];
				output = paper.key.split("/");
				String confName = null;

				if (output[0].equals("conf")){    	
					confName = output[1];
				}

				// Inserting records into paper and authors only for the 4 
				// required conferences
				if(confName != null) {
					if ((confName.equalsIgnoreCase("oopsla")) 
							|| (confName.equalsIgnoreCase("pldi"))
							|| (confName.equalsIgnoreCase("ecoop")) 
							|| (confName.equalsIgnoreCase("icfp"))) {

						statement_inproceedings.setString(1,paper.title);
						statement_inproceedings.setInt(2,paper.year);
						statement_inproceedings.setString(3,paper.pages);
						statement_inproceedings.setString(4,confName);
						statement_inproceedings.setString(5, paper.key);
						statement_inproceedings.addBatch();

						for (String author: paper.author) {
							Author auth = new Author(author, paper.key);

							statement_author.setString(1, auth.name);
							statement_author.setString(2, auth.paperKey);
							statement_author.addBatch();
						}

						if (++j % batchSize == 0){
							statement_inproceedings.executeBatch();
						}

						if (++m % batchSize == 0) {
							statement_author.executeBatch();
						}
					}
				}
			}
			statement_inproceedings.executeBatch();
			statement_author.executeBatch();
		}

		if (data.getProceedings() != null) {

			PreparedStatement statement_conference = conn.prepareStatement("insert into conference(confKey,name,confDetail) values (?,?,?)");

			for (Conference conf: data.getProceedings()) {
				if (conf.booktitle != null) {
					if ((conf.booktitle.equalsIgnoreCase("oopsla")) 
							|| (conf.booktitle.equalsIgnoreCase("pldi"))
							|| (conf.booktitle.equalsIgnoreCase("ecoop"))
							|| (conf.booktitle.equalsIgnoreCase("icfp"))) {

						statement_conference.setString(1, conf.key);
						statement_conference.setString(2, conf.booktitle);
						statement_conference.setString(3, conf.title);
						statement_conference.addBatch(); 

						if (++k % batchSize == 0){
							statement_conference.executeBatch();
						}
					}
				}
			}
			statement_conference.executeBatch();
		}

		if (data.getArticle() != null) {

			PreparedStatement statement_article = conn.prepareStatement("insert into article(author,title,journal,year,month,ee) values (?,?,?,?,?,?)");

			for (Article article: data.getArticle()) {

				if (article.author ==  null)
					continue;

				// UTF8  misinterpreted characters have been found in the title, 
				// such titles have not been inserted into the database
				if(!isUTF8MisInterpreted(article.getTitle(),"Windows-1252")){
					continue;
				}

				if (article.title.equals("")) {
					continue;
				}

				if (article.key == null) {
					continue;
				}

				// extracting journal name from the article key
				String[] output  = new String[3];
				output = article.key.split("/");
				String journalName = null;

				if (output[0].equals("journals")){    	
					journalName = output[1];
				}

				// Inserting records into the article table only for the 2 
				// required journals
				if (journalName != null) {
					if (journalName.equalsIgnoreCase("tse") || 
							journalName.equalsIgnoreCase("toplas")) {
						
						for (String author: article.author) {
							statement_article.setString(1, author);
							statement_article.setString(2, article.title);
							statement_article.setString(3, journalName);
							statement_article.setInt(4, article.year);
							statement_article.setString(5, article.month);
							statement_article.setString(6, article.ee);

							statement_article.addBatch();

							if (++l % batchSize == 0)
								statement_article.executeBatch();
						}
					}
				}
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

	// to check for UTF 8 misinterpreted characters.
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
