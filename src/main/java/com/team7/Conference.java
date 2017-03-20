package com.team7;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@SuppressWarnings("restriction")
public class Conference {
	
	String booktitle; // name of the conference
	String title; // extra info about the conference
	String key;
	
	public String getBooktitle() {
		return booktitle;
	}
	
	@XmlElement
	public void setBooktitle(String booktitle) {
		this.booktitle = booktitle;
	}
	public String getTitle() {
		return title;
	}
	
	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getKey() {
		return key;
	}
	
	@XmlAttribute
	public void setKey(String key) {
		this.key = key;
	}
	
	
}