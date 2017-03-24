package com.team7;

public class Author {

	String name;
	String paperKey;
	
	public Author(String author, String key) {
		// TODO Auto-generated constructor stub
		this.name = author;
		this.paperKey = key;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPaperKey() {
		return paperKey;
	}
	
	public void setPaperKey(String paperKey) {
		this.paperKey = paperKey;
	}
}
