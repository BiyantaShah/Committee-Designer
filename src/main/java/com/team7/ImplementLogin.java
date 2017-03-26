package com.team7;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JDialog;

import org.apache.commons.codec.binary.Base64;

public class ImplementLogin implements Login {
	
	String secretKey = "SECRETKEY";

	public boolean login(String username, String password) throws SQLException {
		if (username != null){
			ImplementSchemaDB db = new ImplementSchemaDB();
			Connection conn = db.getConnection();
			Statement stmt = conn.createStatement();
			
			String sql = "select password from user where username = '" +username +"'";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				
				String plainText = rs.getString(1);
				String finalVal ="";
				try { // decrypting the password
					byte[] encryptedData = Base64.decodeBase64(plainText);
					SecretKeySpec skeyspec=new SecretKeySpec(secretKey.getBytes(),"Blowfish");
					Cipher cipher=Cipher.getInstance("Blowfish");
					cipher.init(Cipher.DECRYPT_MODE, skeyspec);
					byte[] decrypted=cipher.doFinal(encryptedData);
					finalVal=new String(decrypted);

				} catch (Exception e2) {
					e2.printStackTrace();
				}

				if (finalVal.equals(password)) { // if inserted password and the one from the db
					// is the same then let the user login
					return true;
				}
				else { // if incorrect password then don't let the user enter
					return false;
				}	
			}
		}
		return false; // if user does not exist;
	}

	public String logout(User userObject) {
		// TODO Auto-generated method stub
		return null;
	}
	

	public void messageShow (LoginUI frame, String msg) {

		JDialog d = new JDialog(frame, msg, true);
		d.setSize(500, 100);
		d.setLocationRelativeTo(frame);
		d.setVisible(true);

	}

}
