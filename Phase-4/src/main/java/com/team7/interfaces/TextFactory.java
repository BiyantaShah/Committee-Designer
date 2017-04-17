package com.team7.interfaces;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.xml.bind.JAXBException;

import com.team7.parsing.ImplementCommittees;

public class TextFactory extends AbstractParseFactory {

	@Override
	public ParseXml getXml(String xml) throws JAXBException, SQLException, IOException {
		return null;
	}

	@Override
	public ParseCsvFiles getCsv(String csv) throws IOException, SQLException {
		return null;
	}

	@Override
	public ParseTextFile getText(String txt) {
		
		if (txt == null) {
			return null;
		}
		
		if (txt.equals("input/committees/") || txt.equals("input/committes")) {			
			return new ImplementCommittees(new File(txt));			
		}
		
		return null;
	}

}
