package com.team7;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@SuppressWarnings("restriction")
public class Paper {

	List<String> author;
	String title;
	String pages;
	int year;
	String key;
	
	public List<String> getAuthor() {
		return author;
	}

	@XmlElement
	public void setAuthor(List<String> author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}

	public String getPages() {
		return pages;
	}

	@XmlElement
	public void setPages(String pages) {
		this.pages = pages;
	}

	public int getYear() {
		return year;
	}

	@XmlElement
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getKey() {
		return key;
	}

	@XmlAttribute
	public void setKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "[ Author:" + author + " title: "+ 
				title + " pages: " + pages + " year: " +year + "]";
	}
}