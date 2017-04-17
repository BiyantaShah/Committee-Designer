package com.team7.interfaces;

import java.io.IOException;
import java.sql.SQLException;

// An interface for the parsing of the csv file containing information 
// about the university of author, their home page and the authors affiliated universities.
public interface ParseCsvFiles {
	
	public String parseCsv() throws IOException, SQLException;

}
