package com.team7.interfaces;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import com.team7.parsing.ImplementAuthorAffData;
import com.team7.parsing.ImplementHomePageData;
import com.team7.parsing.ImplementUniCountryData;
import com.team7.parsing.ImplementUniversityAuthorData;

public class CsvFactory extends AbstractParseFactory {

	@Override
	public
	ParseXml getXml(String xml) {		
		return null;
	}

	@Override
	public
	ParseCsvFiles getCsv(String csv) throws IOException, SQLException {
		if (csv == null) {
			return null;
		}
		
		if (csv.equals("input/generate-author-info.csv") || csv.equals("input/testUni.csv")) {
			return new ImplementUniversityAuthorData(new File(csv));			
			
		}
		else if (csv.equals("input/country-info.csv") || csv.equals("input/testCountry.csv")) {
			
			return new ImplementUniCountryData(new File(csv));
			
		}
		else if (csv.equals("input/faculty-affiliations.csv") || csv.equals("input/testAff.csv")) {
			
			return new ImplementAuthorAffData(new File(csv));			
		}
		else if (csv.equals("input/homepages.csv") || csv.equals("input/testHome.csv")) {
			
			return new ImplementHomePageData( new File (csv));
			
		}
		return null;
	}

	@Override
	public ParseTextFile getText(String txt) {
		
		return null;
	}


}
