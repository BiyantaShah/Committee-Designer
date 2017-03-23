package com.team7;

public class SearchParameter {
	
	String searchFilter;
	String searchComparator;
	String searchValue;
	
	SearchParameter(String searchFilter, String searchComparator, String searchValue ){
		this.searchFilter =searchFilter;
		this.searchComparator = searchComparator;
		this.searchValue = searchValue;	
	}
	
	public String getSearchFilter(){
		return this.searchFilter;
	}
	
	public String getSearchComparator(){
		return this.searchComparator;
	}
	
	public String getSearchValue(){
		return this.searchValue;
	}
	
	public String setSearchFilter(String newSearchFilter){
		this.searchFilter = newSearchFilter;
		return this.searchFilter;
	}
	
	public String setSearchComparator(String newSearchComparator){
		this.searchComparator = newSearchComparator;
		return this.searchComparator;
	}
	
	public String setSearchValue(String newSearchValue){
		this.searchValue = newSearchValue;
		return this.searchValue;
	}

}
