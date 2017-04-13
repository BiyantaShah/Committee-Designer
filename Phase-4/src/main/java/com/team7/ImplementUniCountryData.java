package com.team7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//Parsing the csv file provided in csrankings.org
//extracting information about regions of each university.
public class ImplementUniCountryData implements UniversityAuthorData {

	public String ParseFiles(File csvFile) throws IOException, SQLException {
		// TODO Auto-generated method stub

		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		final int batchSize = 45;
		int i=0;

		ImplementSchemaDB db = new ImplementSchemaDB();
		Connection conn = db.getConnection();

		// updating the author table with university name.
		PreparedStatement stmt = conn.prepareStatement("update Author set uniRegion=? where university=?");

		try {

			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] region = line.split(cvsSplitBy);

				if (region[1].equals("region")) // the header row ignored
					continue;

				stmt.setString(1, region[1]);
				stmt.setString(2, region[0]);
				stmt.addBatch();

				if (++i % batchSize == 0){ 
					stmt.executeBatch();  
				}

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return null;

	}


}
