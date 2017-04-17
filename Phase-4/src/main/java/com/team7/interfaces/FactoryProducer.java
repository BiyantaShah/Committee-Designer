package com.team7.interfaces;

public class FactoryProducer {
	
	public static AbstractParseFactory getFactory(String choice) {
		
		if (choice.equalsIgnoreCase("XML")) {
			return new XmlFactory();
		}
		else if (choice.equalsIgnoreCase("CSV")) {
			return new CsvFactory();
		}
		
		else if (choice.equalsIgnoreCase("TXT")) {
			return new TextFactory();
		}
		return null;
		
	}

}
