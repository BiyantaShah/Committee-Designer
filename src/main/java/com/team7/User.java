package com.team7;

public class User {

    private String userName;
    private String password;
    private String role;
    private String confName;
    private String email;

    public User(String userName, String password, String role, String confName, String email) {
		// TODO Auto-generated constructor stub
    	this.userName = userName;
    	this.password = password;
    	this.role = role;
    	this.confName = confName;
    	this.email = email;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
