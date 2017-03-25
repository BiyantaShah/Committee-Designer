package com.team7;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.*;
import javax.mail.internet.*;


public class ImplementSearchDisplay implements SearchDisplay {
	
	// Keeps track of all filter criteria and their values
/*	private static List<SearchParameter> filterMap = new ArrayList<SearchParameter>();

	public List<SearchParameter> updateFilterValues(int index, String filterCriteria, String filterComparator, String filterValue, String filterJoin) {
		
		SearchParameter newFilter = new SearchParameter(filterCriteria, filterComparator, filterValue, filterJoin); 
		ImplementSearchDisplay.filterMap.set(index, newFilter);
		return ImplementSearchDisplay.filterMap;
	} 

	public List<SearchParameter> deleteFilterValues(int index) {
		
		ImplementSearchDisplay.filterMap.remove(index);
		return ImplementSearchDisplay.filterMap;
	}

	public List<SearchParameter> saveFilterValue(String filterCriteria, String filterComparator, String filterValue, String filterJoin) {
		
		SearchParameter newCriteria = new SearchParameter(filterCriteria, filterComparator, filterValue, filterJoin); 
		ImplementSearchDisplay.filterMap.add(newCriteria);
		return ImplementSearchDisplay.filterMap;
	}*/
	
	/*public void clearFilterMap(){
	ImplementSearchDisplay.filterMap.clear();
	}*/

	
	// check
	public List<String> search(List<SearchParameter> searchParameter) throws SQLException {
		
		ImplementQueryBuilder queryBuilderObject = new ImplementQueryBuilder();
		List<String> query = queryBuilderObject.createQuery(searchParameter);
		
		if(query!=null){
		    return queryBuilderObject.getResultForDisplay(query);
		}
		else
		    return null;
	}

//	public String display(String result) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public String saveAuthor(Set<String> saveAuthors) {
		// TODO Auto-generated method stub
		return null;		
	}


	public List<String> candidateDetails(List<String> authors) throws SQLException {
		ImplementQueryBuilder queryBuilderObject = new ImplementQueryBuilder();
		String query = queryBuilderObject.createQueryForAuthorDetails(authors);
		List<String> result = new ArrayList<String>();
		
		
		if(query!=null){
		    ResultSet detailsResultSet = queryBuilderObject.sendQuery(query);
		    
		    while (detailsResultSet.next()) {
		    	result.add(detailsResultSet.getString("Author"));
		    	result.add(detailsResultSet.getString("PaperTitle"));
		    	result.add(detailsResultSet.getString("Conference"));
		    	result.add(detailsResultSet.getString("Year"));
             }    
     	 return result; 
		    
		}
		else
		    return null;	
	}

	public void sendEmail(Set<String> authors, String userName) throws SQLException {
		// TODO Auto-generated method stub
		// get the conference of the current user
		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		Statement stmt = conn.createStatement();
		String conference = null;
		
		ResultSet rs1 = stmt.executeQuery("select confName from user where username='"+userName+"'");
		while (rs1.next()) {
			conference = rs1.getString(1);
		}
		
		// In the user table, extract user names of all authors in the same conference 
		// as the current user. 
		ResultSet rs2 = stmt.executeQuery("select username from user where confName='" +conference+"'");
		
		// Then send an email to that list of users with the set of authors obtained from UI
		while (rs2.next()) {
			// Recipient's email ID
			String to = rs2.getString("username");
			
			//Sender's email ID
			String from = "dummy4235@gmail.com"; // This is a valid email ID which has been configured
			
			// Get system properties and set up mail server
			Properties props = new Properties();
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");
	        
	        // Get the default Session object.
	          Session session = Session.getInstance(props,
	               new javax.mail.Authenticator() {
	                 protected PasswordAuthentication getPasswordAuthentication() {
	                	 // set actual password
	                     return new PasswordAuthentication("dummy4235@gmail.com", "qazwsxedc123");
	                 }
	               });
			
			try {
				// Create a default MimeMessage object
				MimeMessage message = new MimeMessage(session);
				
				// Set From: header field of the header.
				message.setFrom(new InternetAddress(from));
				
				// Set To: header field of the header
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				
				// Subject line
				message.setSubject("List of authors selected by " + userName );
				
				// Format the incoming list into a string
				StringBuilder sb = new StringBuilder();
				sb.append("This is my preferred list of authors for committee of " + conference +":");
				sb.append("\n");
				sb.append("\n");
				for (String str: authors) {
					sb.append(str + "\n");
				}
				
				// Body of the email
				message.setText(sb.toString());
				
				Transport.send(message);
				
				System.out.println("Sent message successfully....");
			}
			catch(MessagingException mex) {
				mex.printStackTrace();
			}
		}
		
	}
}
