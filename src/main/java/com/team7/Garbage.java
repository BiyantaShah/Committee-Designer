package com.team7;

import javax.xml.bind.annotation.XmlElement;

//This class is only for testing purposes
@SuppressWarnings("restriction")
public class Garbage {

	private String test;

	@XmlElement
	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

}
