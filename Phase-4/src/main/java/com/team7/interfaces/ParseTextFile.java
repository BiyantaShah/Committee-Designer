package com.team7.interfaces;

import java.io.IOException;
import java.sql.SQLException;

// An interface for the parsing of the files in the committees folder.
public interface ParseTextFile {

	public String parseText() throws IOException,SQLException;
}
