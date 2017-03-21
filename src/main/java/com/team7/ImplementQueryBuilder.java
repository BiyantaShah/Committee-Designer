package com.team7;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImplementQueryBuilder implements QueryBuilder{
	private static String AuthorTable = "Author";
	private static String PaperTable = "Paper";
	private static String ConferenceTable = "Conference";
	
	private static String AuthorTableAlias = "a";
	private static String PaperTableAlias = "p";
	private static String ConferenceTableAlias = "c";
	
	
	public String createQuery(Map<String, String> searchParam) {
		String query = null;
		
		if(validateQuery(searchParam)){
			
			String columnNames = formQueryParams(searchParam, "colNames");
			
			String joinCondition = formJoinCondition();
			
			query = "SELECT " + columnNames + " FROM " + joinCondition + " WHERE ";  
			
			query += formQueryParams(searchParam, "whereParameters");		
	        }
		
		return query;
		}

	public boolean validateQuery(Map<String, String>  searchParam) {
		
		boolean result = false;
		for(String entryKey : searchParam.keySet()){
			
			if(entryKey == "name"){
				String[] values =  searchParam.get(entryKey).split(","); 
				if(checkValidityOfSearchParameters(values[1])){
					return false;
				}
				else
					result = true;
			}
			
			if(entryKey == "Committe"){
				String[] values =  searchParam.get(entryKey).split(","); 
				if(checkValidityOfSearchParameters(values[1])){
					return false;
				}
				else
					result = true;
			}
			
            if(entryKey == "year" && Integer.parseInt(searchParam.get(entryKey).split(",")[1]) > 0){
            	result = true;
			}
            
            if(entryKey == "Region"){
            	String[] values =  searchParam.get(entryKey).split(","); 
            	if(checkValidityOfSearchParameters(values[1])){
					return false;
				}
				else
					result = true; 	
            }
            
            if(entryKey == "keyword"){
            	String[] values =  searchParam.get(entryKey).split(","); 
            	if(checkValidityOfSearchParameters(values[1])){
					return false;
				}
				else
					result = true; 	
            }
		}
		return result;
	}
	
	private String formQueryParams(Map<String, String> searchParam, String clause){
		
		String queryParams = "";
		
		if(clause == "colNames"){		
		/*for(String entryKey : searchParam.keySet()){
			queryParams += entryKey + ","; 
		}
		return queryParams.substring(0, queryParams.length()-1);*/
			queryParams = "a.name, p.title";
			return queryParams;
	}
		else{
			
			for(String entryKey : searchParam.keySet()){
				
				String[] values = searchParam.get(entryKey).split(",");
				
				if(entryKey == "keyword"){
					for(int i= 1; i< values.length;i++){
						queryParams += PaperTableAlias+ ".title " + values[0] + " '%"+values[i] + "%' OR ";
					}
					
					queryParams = queryParams.substring(0, queryParams.length()- 3) + "AND ";
				}
				
				else if(entryKey == "year"){
					queryParams += PaperTableAlias + "."+ entryKey + values[0] + values[1] + " AND ";
				}
				
				else{
				   queryParams += AuthorTableAlias +"." +entryKey + values[0]+"'"+ values[1] + "' AND ";
				}
			}	
			queryParams = queryParams.substring(0, queryParams.length()-5);
			return queryParams;
		}
	}
	
	private boolean checkValidityOfSearchParameters(String searchParameter){
		
		Pattern p = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(searchParameter);
		return m.find();
	}
	
	private String formJoinCondition(){
		
		String joinCondition;
		
		joinCondition = AuthorTable+ " " + AuthorTableAlias+ " INNER JOIN " +
				        PaperTable+ " " + PaperTableAlias+ " ON " +
				        AuthorTableAlias+ ".author = " + PaperTableAlias + ".author INNER JOIN " +
				        ConferenceTable+ " " + ConferenceTableAlias + " INNER JOIN ON " +
				        PaperTableAlias+ ".confName = " + ConferenceTableAlias + ".name";
				        
		return joinCondition;
	}

	public Object sendQuery(String searchQuery) {
		// TODO Auto-generated method stub
		return null;
	}
}

