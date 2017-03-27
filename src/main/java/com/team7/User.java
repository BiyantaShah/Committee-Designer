package com.team7;

// This class contains information about the users of the application 
public class User {

     String userName;
     String password;
     String role;
     String confName;
     
    public User(){
    	
    }

    public User(String userName, String password, String role, String confName) {
		// TODO Auto-generated constructor stub
    	this.userName = userName; //email id 
    	this.password = password;
    	this.role = role;
    	this.confName = confName;
	}

    // getter and setters
	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getConfName() {
        return confName;
    }

    public void setConfName(String confName) {
        this.confName = confName;
    }
}
