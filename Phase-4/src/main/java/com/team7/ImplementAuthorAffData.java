package com.team7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//Parsing the csv file provided in csrankings.org
//extracting information about affiliated universities of author.
public class ImplementAuthorAffData implements UniversityAuthorData {

	public String ParseFiles(File csvFile) throws IOException, SQLException {
		// TODO Auto-generated method stub
		BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        
        final int batchSize = 1000;
		int i=0;
        
        ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		
		// updating the author table with university name.
        PreparedStatement stmt = conn.prepareStatement("update Author set affiliatedUni=? where name=?");
        
        try {
        	
        	br = new BufferedReader(new FileReader(csvFile));
        	while ((line = br.readLine()) != null) {

        		// use comma as separator
        		String[] affiliated = line.split(cvsSplitBy);
        		
        		if (affiliated[1].equals("affiliation")) // the header row ignored
        			continue;
        		
        		stmt.setString(1, affiliated[1]);
        		stmt.setString(2, affiliated[0]);
        		stmt.addBatch();
        		
        		if (++i % batchSize == 0){ 
					stmt.executeBatch();  
				}
        		
        	}
        	stmt.executeBatch();
			br.close();
        	if (i != 0)
        		return "success";
        	
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
		return "No data";
	}

}
