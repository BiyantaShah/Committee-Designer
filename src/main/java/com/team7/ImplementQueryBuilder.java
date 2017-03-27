package com.team7;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ImplementQueryBuilder implements QueryBuilder{
	
	String whereClauseForPaperAuthor = "";
	String whereClauseForCommittee = "";
	String whereClauseForArticle = "";
	
	String groupByClause = "";
	String queryPaperAuthor = null;
	String queryCommitte = null;
	String queryArticle = null;
	
	private static String AuthorTable = "Author";
	private static String PaperTable = "Paper";

	private static String AuthorTableAlias = "a";
	private static String PaperTableAlias = "p";
	private static String CommitteeTableAlias = "c";
	private static String ArticleTableAlias = "ar";
	
	
	public List<String> createQuery(List<SearchParameter> searchParam) {			
		if(validateQuery(searchParam)){
			
			formQueryParams(searchParam);
			
			getPaperAuthorQuery();
			
			getCommitteQuery();	
			
			getArticleQuery();
	        }
		
		List<String> queries = new ArrayList<String>();
		queries.add(0,queryPaperAuthor);
		queries.add(1, queryCommitte);
		queries.add(2, queryArticle);
		
		return queries;
	}
        
	public boolean validateQuery(List<SearchParameter> searchParam) {        	
		boolean result = false;
		for(SearchParameter s : searchParam){
			if(s.getSearchFilter() == "Author Name"){	
				s.setSearchFilter("Name");
				if(checkValidityOfSearchParameters(s.getSearchValue())){					
					return false;
				}
				else
					result = true;
			}
			
			if(s.getSearchFilter() == "Conference Name"){
				s.setSearchFilter("ConfName");
				if(checkValidityOfSearchParameters(s.getSearchValue())){
					return false;
				}
				else
					result = true;
			}
			
			if(s.getSearchFilter() == "Paper Published Year"){
				s.setSearchFilter("Year");
	            if(s.getSearchFilter() == "Year" && Integer.parseInt(s.getSearchValue()) > 0){
	            	result = true;
				}
			}
              
            if(s.getSearchFilter() == "Keyword in Title"){
            	s.setSearchFilter("Keyword");
            	if(checkValidityOfSearchParameters(s.getSearchValue())){
					return false;				  
            	}
				else
					result = true; 	
             } 
            
            if(s.getSearchFilter() == "Count Of Papers"){            	
            	s.setSearchFilter("CountNoOfPapers");
	            if(s.getSearchFilter() == "CountNoOfPapers" && Integer.parseInt(s.getSearchValue()) > 0){
					return true;
				}
            }    
            
	        if(s.getSearchFilter() == "Committee Year")  {  
	        	s.setSearchFilter("Committee.Year");
	            if(s.getSearchFilter() == "Committee.Year" && Integer.parseInt(s.getSearchValue()) > 0){					
	            	return true;
	            }
			}
            
            if(s.getSearchFilter() == "Committee Conf Name"){
	        	s.setSearchFilter("Committee.ConfName");
            	if(checkValidityOfSearchParameters(s.getSearchValue())){
					return false;
				}
				else
					result = true;
			}
            
            if(s.getSearchFilter() == "Journal Name"){
				s.setSearchFilter("JournalName");
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
        
        private void formQueryParams(List<SearchParameter> searchParam){
			   			
        	for(SearchParameter s : searchParam){	
    				if(s.getSearchFilter().contains("Committee")){
    					formCommitteeWhereClause(s);
    				}
    				
    				else if(s.getSearchFilter() == "JournalName"){
    					
    					whereClauseForArticle += ArticleTableAlias + ".journal" + s.getSearchComparator()+ " '"+ s.getSearchValue()+"' " + s.getjoinFilter() + " ";    			  
    				}
    				
    				else{
    					formPaperAuthorWhereClause(s);
    				}
    			}	
        
        		if(whereClauseForPaperAuthor.length()>0){
        			whereClauseForPaperAuthor = whereClauseForPaperAuthor.substring(0, whereClauseForPaperAuthor.length()-5);
        		}   				
    			
    			if(groupByClause.length()>0){
    				whereClauseForPaperAuthor += groupByClause;
    			}
    			
    			if(whereClauseForCommittee.length()>0){
    			    whereClauseForCommittee = whereClauseForCommittee.substring(0, whereClauseForCommittee.length()-5);   

    			}
    			
    			if(whereClauseForArticle.length()>0){
    				whereClauseForArticle = whereClauseForArticle.substring(0, whereClauseForArticle.length()-5);   

    			}
    	}
        
        private String formJoinCondition(){
    		
    		return AuthorTable+ " " + AuthorTableAlias+ " INNER JOIN " +
			        PaperTable+ " " + PaperTableAlias+ " ON " +
			        AuthorTableAlias+ ".paperKey = " + PaperTableAlias + ".paperKey";		
    	}
        
        public List<String> getResultForDisplay(List<String> searchQuery) throws SQLException{
        	     ResultSet paperAuthorResultSet = sendQuery(searchQuery.get(0));
        	     List<String> finalResult = new ArrayList<String>();
        	     List<String> paperAuthorResult = new ArrayList<String>();
         		
         		 while (paperAuthorResultSet.next()) {
         			paperAuthorResult.add(paperAuthorResultSet.getString("Author"));
                 }
         		 
         		 if(searchQuery.get(1) != null){
         			ResultSet committeeResultSet = sendQuery(searchQuery.get(1));
           	        List<String> committeeResult = new ArrayList<String>();
            		
            		 while (committeeResultSet.next()) {
            			 committeeResult.add(committeeResultSet.getString("Author"));
                    }
            		 
            		 paperAuthorResult.retainAll(committeeResult);         		
         		 }
         		 
         		if(searchQuery.get(2) != null){
         			ResultSet articleResultSet = sendQuery(searchQuery.get(2));
           	        List<String> articleResult = new ArrayList<String>();
            		
            		 while (articleResultSet.next()) {
            			 articleResult.add(articleResultSet.getString("Author"));
                    }
            		 
            		 paperAuthorResult.retainAll(articleResult);         		
         		 }        		 
         		 
         		     finalResult.addAll(new HashSet<String>(paperAuthorResult));        		 
         			 return finalResult;     		
        }
         
        public ResultSet sendQuery(String searchQuery) throws SQLException {
    		ImplementSchemaDB implementSchemaObj = new ImplementSchemaDB();
    		Connection conn = implementSchemaObj.getConnection();
    		Statement stmt = conn.createStatement();
    		ResultSet result =stmt.executeQuery(searchQuery);	
    		return result;
    	}
        
        private void formGroupClause(SearchParameter search){ 	
        	groupByClause = " GROUP BY " + AuthorTableAlias +".name HAVING COUNT(*) " + search.getSearchComparator() +search.getSearchValue();
        }
          
        private void formPaperAuthorWhereClause(SearchParameter s){ 	
        	
        	if(s.getSearchFilter() == "Keyword"){
				whereClauseForPaperAuthor += PaperTableAlias+ ".title " + s.getSearchComparator()+ " '%"+ s.getSearchValue()+ "%' " + s.getjoinFilter() + " ";   					
        	}
			
			else if(s.getSearchFilter() == "Year"){
				whereClauseForPaperAuthor += PaperTableAlias + "."+ s.getSearchFilter() + s.getSearchComparator()+ s.getSearchValue()+ " " + s.getjoinFilter() + " ";
			}
			
			else if(s.getSearchFilter() == "Name"){
				if(s.getSearchComparator() == "="){
					whereClauseForPaperAuthor += AuthorTableAlias + "."+ s.getSearchFilter() + s.getSearchComparator()+ "'"+ s.getSearchValue()+"' " + s.getjoinFilter() + " ";	
				}
				else{
					whereClauseForPaperAuthor += AuthorTableAlias + "."+ s.getSearchFilter() + " "+ s.getSearchComparator()+ " '%"+ s.getSearchValue()+ "%' " + s.getjoinFilter() + " ";   					
				}
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
//        	String columnNames = getColumns();
			String joinCondition = formJoinCondition();
			queryPaperAuthor = "SELECT a.name AS Author FROM " + joinCondition + " WHERE ";  
			queryPaperAuthor += whereClauseForPaperAuthor;
        }
        
        private void getCommitteQuery(){
        	
        	if(whereClauseForCommittee.length()>0){
        		queryCommitte = "SELECT c.AuthorName AS Author FROM  Committee c WHERE ";  
        		queryCommitte += whereClauseForCommittee;
        	}
        }
        
        private void getArticleQuery(){
        	
        	if(whereClauseForArticle.length()>0){
        		queryArticle = "SELECT ar.Author AS Author FROM  Article ar WHERE ";  
        		queryArticle += whereClauseForArticle;
        	}
        }
        
        public String createQueryForAuthorDetails(Set<String> authors){
        	
        	String query = "SELECT a.name AS Author, p.title As PaperTitle, p.confName AS Conference,"
        			+ "p.year as Year FROM AUTHOR a INNER JOIN PAPER p ON a.paperKey = p.paperKey where a.name IN (";   	
        	for(String author : authors){
        		query +="'" + author +"',";	
        	}     	
        	query = query.substring(0, query.length()-1) + ")"; 	
        	return query;   	
        }
}

