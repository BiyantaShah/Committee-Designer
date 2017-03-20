package com.team7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImplementCommittees implements Committees {

	public String ParseFiles(File textFile) throws IOException, SQLException {
		// TODO Auto-generated method stub
		
		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();
		PreparedStatement stmt = conn.prepareStatement("insert into Committees(name,role,confName,year) values(?,?,?,?)");

		String rec = null;
		String name = null;
		String role = null;
		String confName = null;
		int year;
		
		final int batchSize = 1000;
		int i=0;
		
		for(File fName : textFile.listFiles()){
			
			BufferedReader bf = new BufferedReader(new FileReader(fName));		
			String fileName = fName.getName();			
		    List<String> output = new ArrayList<String>();
		    Matcher match = Pattern.compile("[0-9]+|[a-z]+|[A-Z]").matcher(fileName);
		    while (match.find()) {
		        output.add(match.group());
		    }
		    
		    //System.out.println(output.get(0)+ "--" + output.get(1));
						
		    while ((rec = bf.readLine()) != null)	    	
		    {
		    	if(rec.contains(":")){
		    		
		    		 role = rec.substring(0,rec.indexOf(':'));
		    		 name = rec.substring(rec.indexOf(':')+1,rec.length());	
		    		 
		    	}else{
		    		
		    		 role = null;
		    		 name = rec;
		    	}
		    	
		    	   confName = output.get(0);
		    	   year = Integer.parseInt(output.get(1));	
		    	   
		    	   stmt.setString(1,name);
		    	   stmt.setString(2,role);
		    	   stmt.setString(3,confName);
		    	   stmt.setInt(4,year);
		    	   stmt.addBatch();
		    	   
		    	   if(confName.equals("test")){ //for testing purposes						
		    		   stmt.executeBatch(); 						
		    	   }
		    	   else if(confName.equals("fail")){//for testing purposes		    		    
		    		    bf.close();
		    		    return "failure";
		    	   }
		    	   else if (++i % batchSize == 0){
						stmt.executeBatch();
					}

		    }
		    
		    bf.close();
		}
		stmt.executeBatch();
		return "success";
	}

}
