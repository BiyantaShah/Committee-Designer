package com.team7;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@SuppressWarnings("restriction")
@XmlRootElement
public class dblp {

	List<Paper> inproceedings;
	List<Conference> proceedings;
	List<Garbage> garbage; // only for testing purposes

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
	
	public List<Garbage> getGarbage() {
		return garbage;
	}
	
	@XmlElement
	public void setGarbage(List<Garbage> garbage) {
		this.garbage = garbage;
	}
	

	
}

