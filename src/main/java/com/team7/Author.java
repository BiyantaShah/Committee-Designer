package com.team7;

public class Author {

   String name;
   String key;

 	public Author() {
	// TODO Auto-generated constructor stub
 	}

 	public Author(String name, String key) {
 		// TODO Auto-generated constructor stub
 		this.name = name;
 		this.key = key;
 	}

	public String getAuthor() {
        return name;
    }

    public void setAuthor(String author) {
        this.name = author;
    }
    

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
