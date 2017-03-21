package com.team7;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

@SuppressWarnings("restriction")
public class Article {
	
	List<String> author;
	String title;
	int year;
	String month;
	String ee;
	
	public Article () {
		
	}
	
	public Article (String title, int year, String month, String ee) {
		this.title = title;
		this.year = year;
		this.month = month;
		this.ee = ee;
	}
	
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
	
	public int getYear() {
		return year;
	}
	
	@XmlElement
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getMonth() {
		return month;
	}
	
	@XmlElement
	public void setMonth(String month) {
		this.month = month;
	}
	
	public String getEe() {
		return ee;
	}
	
	@XmlElement
	public void setEe(String ee) {
		this.ee = ee;
	}
	
	

}
