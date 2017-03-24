package com.team7;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ImplementQueryBuilder implements QueryBuilder{
	
	String whereClauseForPaperAuthor = "";
	String whereClauseForCommittee = "";
	
	String groupByClause = "";
	String queryPaperAuthor = null;
	String queryCommitte = null;
	
	private static String AuthorTable = "Author";
	private static String PaperTable = "Paper";

	private static String AuthorTableAlias = "a";
	private static String PaperTableAlias = "p";
	private static String CommitteeTableAlias = "c";
	
	
	public List<String> createQuery(List<SearchParameter> searchParam) {			
		if(validateQuery(searchParam)){
			
			formQueryParams(searchParam);
			
			getPaperAuthorQuery();
			
			getCommitteQuery();	
	        }
		
		List<String> queries = new ArrayList<String>();
		queries.add(0,queryPaperAuthor);
		queries.add(1, queryCommitte);
		
		return queries;
	}
        
	public boolean validateQuery(List<SearchParameter> searchParam) {        	
		boolean result = false;
				
		for(SearchParameter s : searchParam){
			if(s.getSearchFilter() == "Name"){	
				if(checkValidityOfSearchParameters(s.getSearchValue())){
					return false;
				}
				else
					result = true;
			}
			
			if(s.getSearchFilter() == "ConfName"){	
				if(checkValidityOfSearchParameters(s.getSearchValue())){
					return false;
				}
				else
					result = true;
			}
			
            if(s.getSearchFilter() == "Year" && Integer.parseInt(s.getSearchValue()) > 0){
            	result = true;
			}
              
            if(s.getSearchFilter() == "Keyword"){
            	if(checkValidityOfSearchParameters(s.getSearchValue())){
					return false;				  
            	}
				else
					result = true; 	
             } 
            
            if(s.getSearchFilter() == "Committe.Year" && Integer.parseInt(s.getSearchValue()) > 0){
				return true;
			}
            
            if(s.getSearchFilter() == "Committe.ConfName"){
            	if(checkValidityOfSearchParameters(s.getSearchValue())){
					return false;
				}
				else
					result = true;
			}
		}
		return result;
	}
        
        private boolean checkValidityOfSearchParameters(String searchParameter){
    		
    		Pattern p = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
    		Matcher m = p.matcher(searchParameter);
    		return m.find();
    	}
        
        private String getColumns(){
        	return "a.name AS Author, p.title AS Title";
        }
        
        private void formQueryParams(List<SearchParameter> searchParam){
			   			
        	for(SearchParameter s : searchParam){	
    				if(s.getSearchFilter().contains("Committee")){
    					formCommitteeWhereClause(s);
    				}
    				
    				else{
    					formPaperAuthorWhereClause(s);
    				}
    			}	
    			
    			whereClauseForPaperAuthor = whereClauseForPaperAuthor.substring(0, whereClauseForPaperAuthor.length()-4);   			
    			
    			if(groupByClause.length()>0){
    				whereClauseForPaperAuthor += groupByClause;
    			}
    			
    			if(whereClauseForCommittee.length()>0){
    			    whereClauseForCommittee = whereClauseForCommittee.substring(0, whereClauseForCommittee.length()-4);   			
    			}		
    	}
        
        private String formJoinCondition(){
    		
    		return AuthorTable+ " " + AuthorTableAlias+ " INNER JOIN " +
			        PaperTable+ " " + PaperTableAlias+ " ON " +
			        AuthorTableAlias+ ".paperKey = " + PaperTableAlias + ".paperKey";		
    	}
        
        public List<String> sendQuery(List<String> searchQuery) throws SQLException {
    		ImplementSchemaDB implementSchemaObj = new ImplementSchemaDB();
    		Connection conn = implementSchemaObj.getConnection();
    		Statement stmt = conn.createStatement();
    		ResultSet paperAuthorResultSet =stmt.executeQuery(searchQuery.get(0));	
    		List<String> paperAuthorResult = new ArrayList<String>();
    		
    		while (paperAuthorResultSet.next()) {
    			paperAuthorResult.add(paperAuthorResultSet.getString("Author"));
            }
    		return paperAuthorResult;
    	}
        
        private void formGroupClause(SearchParameter search){ 	
        	groupByClause = " GROUP BY " + AuthorTableAlias +".name HAVING COUNT(*) " + search.getSearchComparator() +search.getSearchValue();
        }
          
        private void formPaperAuthorWhereClause(SearchParameter s){ 	
        	if(s.getSearchFilter() == "Keyword"){
				whereClauseForPaperAuthor += PaperTableAlias+ ".title " + s.getSearchComparator()+ " '%"+ s.getSearchValue()+ "%' " + s.getjoinFilter() + " ";   					
			}
			
			else if(s.getSearchFilter() == "Year"){
				whereClauseForPaperAuthor += PaperTableAlias + "."+ s.getSearchFilter() + s.getSearchComparator()+ s.getSearchValue()+ "" + s.getjoinFilter() + " ";
			}
			
			else if(s.getSearchFilter() == "Name"){
				whereClauseForPaperAuthor += AuthorTableAlias + "."+ s.getSearchFilter() + s.getSearchComparator()+ "'"+ s.getSearchValue()+"' " + s.getjoinFilter() + " ";	
			} 
			
			else if(s.getSearchFilter() == "ConfName"){
				whereClauseForPaperAuthor += PaperTableAlias + "."+ s.getSearchFilter() + s.getSearchComparator()+ "'"+ s.getSearchValue()+"' " + s.getjoinFilter() + " ";	
			} 
			
			else if(s.getSearchFilter() == "CountNoOfPapers"){
				formGroupClause(s);
			}
        }
        
      
        private void formCommitteeWhereClause(SearchParameter s){  	
        	if(s.getSearchFilter() == "Committee.ConfName"){
        		whereClauseForCommittee += CommitteeTableAlias + "."+ s.getSearchFilter().split("\\.")[1] + s.getSearchComparator()+ "'"+ s.getSearchValue()+"' " + s.getjoinFilter() + " ";	
			} 
			
			else if(s.getSearchFilter() == "Committee.Year"){
				whereClauseForCommittee += CommitteeTableAlias + "."+ s.getSearchFilter().split("\\.")[1] + s.getSearchComparator()+ "'"+ s.getSearchValue()+"' " + s.getjoinFilter() + " ";	
			} 
        }
        
        
        private void getPaperAuthorQuery(){
            
        	String columnNames = getColumns();
			String joinCondition = formJoinCondition();
			queryPaperAuthor = "SELECT a.name AS Author FROM " + joinCondition + " WHERE ";  
			queryPaperAuthor += whereClauseForPaperAuthor;
        }
        
        private void getCommitteQuery(){
        	
        	if(whereClauseForCommittee.length()>0){
        		queryCommitte = "SELECT c.Author FROM  CommitteeTable c WHERE ";  
        		queryCommitte += whereClauseForCommittee;
        	}
        }
}

