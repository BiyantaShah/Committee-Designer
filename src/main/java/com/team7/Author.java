package com.team7;

// This class gives information about the Author (name and the papers written)
public class Author {

	String name;
	String paperKey;

	public Author(String author, String key) {
		// TODO Auto-generated constructor stub
		this.name = author;
		this.paperKey = key;
	}

	// Getters and setters
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
