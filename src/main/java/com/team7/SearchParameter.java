package com.team7;

public class SearchParameter {
	
	String searchFilter;
	String searchComparator;
	String searchValue;
	String joinFilter;
	
	SearchParameter(){
		
	}
	
	SearchParameter(String searchFilter, String searchComparator, String searchValue, String joinFilter){
		this.searchFilter =searchFilter;
		this.searchComparator = searchComparator;
		this.searchValue = searchValue;	
		if(joinFilter != null){
				this.joinFilter = joinFilter;
			}
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
	
	public String getjoinFilter(){
		return this.joinFilter;
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
	
	public String setJoinFilter(String newJoinFilter ){
		this.joinFilter = newJoinFilter;
		return this.joinFilter;
	}

}
