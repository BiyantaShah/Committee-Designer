package com.team7;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

public interface Committees {

	public String ParseFiles(File textFile) throws IOException,SQLException;
}
