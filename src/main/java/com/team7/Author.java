package com.team7;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

@SuppressWarnings("restriction")
public class Author {

   List<String> author;
   String url;
  
 	public Author() {
	// TODO Auto-generated constructor stub
 	}
 	
 	public Author(List<String> name,String url) {
 		
	// TODO Auto-generated constructor stub
 		this.author = name;
 		this.url = url;
 	
 	}

	public 	List<String> getAuthor() {
        return 	author;
    }

	@XmlElement
    public void setAuthor(List<String> name) {
        this.author = name;
    }
	
    public String getUrl() {
        return url;
    }

	@XmlElement
    public void setUrl(String url) {
        this.url = url;
    }
}
