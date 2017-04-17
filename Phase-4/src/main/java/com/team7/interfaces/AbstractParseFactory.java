package com.team7.interfaces;

import java.io.IOException;
import java.sql.SQLException;

import javax.xml.bind.JAXBException;

public abstract class AbstractParseFactory {

	public abstract ParseXml getXml(String xml) throws JAXBException, SQLException, IOException;
	public abstract ParseCsvFiles getCsv(String csv) throws IOException, SQLException;
	public abstract ParseTextFile getText(String txt);
	
}
