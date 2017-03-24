package com.team7;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImplementSearchDisplay implements SearchDisplay {
	
	// Keeps track of all filter criteria and their values
	private static List<SearchParameter> filterMap = new ArrayList<SearchParameter>();

	public List<SearchParameter> updateFilterValues(int index, SearchParameter newFilter) {
		
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
	}

	public List<String> search(List<SearchParameter> searchParam) throws SQLException {
		
		ImplementQueryBuilder queryBuilderObject = new ImplementQueryBuilder();
		List<String> query = queryBuilderObject.createQuery(searchParam);
		
		if(query!=null){
		    return queryBuilderObject.sendQuery(query);
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
	}

	public String candidateDetails(Author authorObject) {
		// TODO Auto-generated method stub
		return null;		
	}*/
	
	public void clearFilterMap(){
		ImplementSearchDisplay.filterMap.clear();
	}
}
