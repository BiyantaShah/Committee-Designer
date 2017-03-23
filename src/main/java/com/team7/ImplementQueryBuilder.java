package com.team7;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ImplementQueryBuilder implements QueryBuilder{
	private static String AuthorTable = "Author";
	private static String PaperTable = "Paper";
	private static String ConferenceTable = "Conference";
	
	private static String AuthorTableAlias = "a";
	private static String PaperTableAlias = "p";
	private static String ConferenceTableAlias = "c";
	
	public String createQuery(List<SearchParameter> searchParam) {
		String query = null;
		
		if(validateQuery(searchParam)){
			
			String columnNames = formQueryParams(searchParam, "colNames");
			
			String joinCondition = formJoinCondition();
			
			query = "SELECT " + columnNames + " FROM " + joinCondition + " WHERE ";  
			
			query += formQueryParams(searchParam, "whereParameters");		
	        }
		
		return query;
		}
	
        public boolean validateQuery(List<SearchParameter> searchParam) {
		        	
		boolean result = false;
		for(SearchParameter s : searchParam){
			
			
			if(s.getSearchFilter() == "name"){	
				if(checkValidityOfSearchParameters(s.getSearchValue())){
					return false;
				}
				else
					result = true;
			}
			
			if(s.getSearchFilter() == "Committe"){
				if(checkValidityOfSearchParameters(s.getSearchValue())){
					return false;
				}
				else
					result = true;
			}
			
            if(s.getSearchFilter() == "year" && Integer.parseInt(s.getSearchValue()) > 0){
            	result = true;
			}
              
            if(s.getSearchFilter() == "keyword"){
            	
            	for(int i =0;i<s.getSearchValue().split(",").length;i++){
            	if(checkValidityOfSearchParameters(s.getSearchValue().split(",")[i])){
					return false;				  
            	}
				else
					result = true; 	
             }           	
            }
		}
		return result;
	}
        private boolean checkValidityOfSearchParameters(String searchParameter){
    		
    		Pattern p = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
    		Matcher m = p.matcher(searchParameter);
    		return m.find();
    	}
        
        private String formQueryParams(List<SearchParameter> searchParam, String clause){
    		
    		String queryParams = "";
    		
    		if(clause == "colNames"){		
    			queryParams = "a.name AS Author, p.title AS Title";
    			return queryParams;
    	}
    		else{
    			
    			for(SearchParameter s : searchParam){
    				
    				if(s.getSearchFilter() == "keyword"){
    					
    					if(!queryParams.contains("LIKE")){
    						queryParams += "AND " + PaperTableAlias+ ".title " + s.getSearchComparator()+ " '%"+ s.getSearchValue()+ "%' ";
    					}
    					else{
    						queryParams += " OR " + PaperTableAlias + ".title " + s.getSearchComparator()+ " '%"+ s.getSearchValue()+ "%' ";	
    					}   					
    					
    				}
    				
    				else if(s.getSearchFilter() == "year"){
    					if(!queryParams.contains("year")){
    						queryParams += "AND " + PaperTableAlias + "."+ s.getSearchFilter() + s.getSearchComparator()+ s.getSearchValue()+" ";
    					}
    					else{
    						queryParams += " OR " + PaperTableAlias + "."+ s.getSearchFilter() + s.getSearchComparator()+ s.getSearchValue()+" ";	
    					}
    				}
    				
    				else if(s.getSearchFilter() == "name"){
    					if(!queryParams.contains("name")){
    						queryParams += "AND " + AuthorTableAlias + "."+ s.getSearchFilter() + s.getSearchComparator()+"'"+s.getSearchValue()+"' ";
    					}
    					else{
    						queryParams += " OR " + AuthorTableAlias + "."+ s.getSearchFilter() + s.getSearchComparator()+ "'"+ s.getSearchValue()+"' ";	
    					}
    				}   				
    				else{
    				   queryParams += "' AND "+ AuthorTableAlias +"." +s.getSearchFilter() + s.getSearchComparator()+"'"+ s.getSearchValue()+" ";
    				}
    			}	
    			queryParams = queryParams.substring(4, queryParams.length());
    			return queryParams;
    		}
    	}
        
        private String formJoinCondition(){
    		
    		String joinCondition;
    		
    		joinCondition = AuthorTable+ " " + AuthorTableAlias+ " INNER JOIN " +
    				        PaperTable+ " " + PaperTableAlias+ " ON " +
    				        AuthorTableAlias+ ".name = " + PaperTableAlias + ".author INNER JOIN " +
    				        ConferenceTable+ " " + ConferenceTableAlias + " ON " +
    				        PaperTableAlias+ ".confName = " + ConferenceTableAlias + ".name";
    				        
    		return joinCondition;
    	}
        
        public List<String> sendQuery(String searchQuery) throws SQLException {
    		
    		ImplementSchemaDB implementSchemaObj = new ImplementSchemaDB();
    		Connection conn = implementSchemaObj.getConnection();
    		Statement stmt = conn.createStatement();
    		ResultSet rs =stmt.executeQuery(searchQuery);	
    		List<String> result = new ArrayList<String>();
    		
    		while (rs.next()) {
                result.add(rs.getString("Author"));
                result.add(rs.getString("Title"));
            }
    		
    		return result;
    	}
}

