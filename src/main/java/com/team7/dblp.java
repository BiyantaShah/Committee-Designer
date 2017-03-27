package com.team7;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// This class represents the root element of the xml file.
// Contains objects of all the tags whose information is required.
// Information of all these objects will be extracted by JAXB parser.
@SuppressWarnings("restriction")
@XmlRootElement
public class dblp {

	List<Paper> inproceedings;
	List<Conference> proceedings;
	List<AuthorDetails> www;
	List<Article> article;
	List<Garbage> garbage; // only for testing purposes


	// getters and setters
	public List<Paper> getInproceedings() {
		return inproceedings;
	}

	@XmlElement
	public void setInproceedings(List<Paper> inproceedings) {
		this.inproceedings = inproceedings;
	}

	public List<Conference> getProceedings() {
		return proceedings;
	}

	@XmlElement
	public void setProceedings(List<Conference> proceedings) {
		this.proceedings = proceedings;
	}

	public List<Article> getArticle() {
		return article;
	}

	@XmlElement
	public void setArticle(List<Article> article) {
		this.article = article;
	}

	public List<AuthorDetails> getWww() {
		return www;
	}

	@XmlElement
	public void setWww(List<AuthorDetails> www) {
		this.www = www;
	}

	public List<Garbage> getGarbage() {
		return garbage;
	}

	@XmlElement
	public void setGarbage(List<Garbage> garbage) {
		this.garbage = garbage;
	}

}

