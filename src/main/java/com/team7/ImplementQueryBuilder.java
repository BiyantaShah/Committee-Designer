package com.team7;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImplementQueryBuilder implements QueryBuilder{

	public String createQuery(Map<String, String> searchParam) {
		String query = null;
		
		if(validateQuery(searchParam)){
			
			String columnNames = formQueryParams(searchParam, "colNames");
			
			query = "SELECT " + columnNames + " FROM TABLE_NAME WHERE ";  
			
			query += formQueryParams(searchParam, "whereParameters");		
	        }
		
		return query;
		}

	public boolean validateQuery(Map<String, String>  searchParam) {
		
		boolean result = false;
		for(String entryKey : searchParam.keySet()){
			
			if(entryKey == "AuthorName"){
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
			
            if(entryKey == "YearsActive" && Integer.parseInt(searchParam.get(entryKey).split(",")[1]) > 0){
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
            
            if(entryKey == "Keyword"){
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

	public Object sendQuery(String searchQuery) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String formQueryParams(Map<String, String> searchParam, String clause){
		
		String queryParams = "";
		
		if(clause == "colNames"){		
		/*for(String entryKey : searchParam.keySet()){
			queryParams += entryKey + ","; 
		}
		return queryParams.substring(0, queryParams.length()-1);*/
			queryParams = "AuthorName, PapersPublished";
			return queryParams;
	}
		else{
			
			for(String entryKey : searchParam.keySet()){
				
				String[] values = searchParam.get(entryKey).split(",");
				
				if(entryKey == "Keyword"){
					for(int i= 1; i< values.length;i++){
						queryParams += "Papers " + values[0] + " '%"+values[i] + "%' OR ";
					}
					
					queryParams = queryParams.substring(0, queryParams.length()- 3) + "AND ";
				}
				
				else if(entryKey == "YearsActive"){
					queryParams += entryKey + values[0] + values[1] + " AND ";
				}
				
				else{
				   queryParams += entryKey + values[0]+"'"+ values[1] + "' AND ";
				}
			}	
			queryParams = queryParams.substring(0, queryParams.length()-5);
			return queryParams;
		}
	}
	
	private boolean checkValidityOfSearchParameters(String searchParameter){
		
		//Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
		Pattern p = Pattern.compile("[^a-z ]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(searchParameter);
		return m.find();
	}
}

