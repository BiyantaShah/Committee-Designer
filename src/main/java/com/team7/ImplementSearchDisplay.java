package com.team7;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	/*public String display(String result) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> sendEmail(User userObject) {
		// TODO Auto-generated method stub
		return null;		
	}

	public String saveAuthor(Author authorObject) {
		// TODO Auto-generated method stub
		return null;		
	}*/

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
}
