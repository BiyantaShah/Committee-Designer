package com.team7;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class ImplementRegister implements Register {
	
	String secretKey = "SECRETKEY";
	String password = null;

	private static Base64 base64 = new Base64(true);
	
	public String createUser(String userName, String plainPass, String role, String confName) throws SQLException {

		if(verifyIfUserExists(userName))
		{			
			return "exists";

		}else{
			// encrypt the password
			try {
				SecretKeySpec skeyspec=new SecretKeySpec(secretKey.getBytes(),"Blowfish");
				Cipher cipher = Cipher.getInstance("Blowfish");
				cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
				password = base64.encodeToString(cipher.doFinal(plainPass.getBytes("UTF8")));

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			User user = new User(userName,password,role,confName);
			ImplementSchemaDB db= new ImplementSchemaDB();
			boolean res = db.insertData(user);

			if(res == true){
				return "true";
			}
		}
		return "false";

	}

	public boolean verifyIfUserExists(String userName) throws SQLException {

		if(userName != null)
		{
			ImplementSchemaDB db = new ImplementSchemaDB();
			Connection conn = db.getConnection();
			Statement stmt = conn.createStatement();

			String sql = "Select count(*) from user where username = " + "'" +userName+ "'"; 

			ResultSet rs = stmt.executeQuery(sql);

			if(rs.next()){

				if(rs.getInt(1) > 0){
					return true; 
				}
			}
		}

		return false;
	}

}
