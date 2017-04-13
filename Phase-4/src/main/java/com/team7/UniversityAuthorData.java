package com.team7;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

// An interface for the parsing of the csv file containing information 
// about the university of author, their home page and the authors affiliated universities.
public interface UniversityAuthorData {
	
	public String ParseFiles(File csvFile) throws IOException, SQLException;

}
