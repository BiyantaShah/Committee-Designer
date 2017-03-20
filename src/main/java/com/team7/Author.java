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

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
