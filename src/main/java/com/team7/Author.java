package com.team7;

public class Author {

 public  String author;
 public  String key;

 	public Author() {
	// TODO Auto-generated constructor stub
 	}

 	public Author(String author, String key) {
 		// TODO Auto-generated constructor stub
 		this.author = author;
 		this.key = key;
 	}

	public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
