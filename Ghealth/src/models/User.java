package models;

import models.Role.Roles;

public class User  {

	private String WID; //worker id 
	private String Upassword;
	private String pName; 
	private Role uRole; 
	
	
		
	public User(String wID, String upassword, String pName, Role uRole) {
		super();
		setWID(wID);
		Upassword = upassword;
		this.setpName(pName);
		this.uRole = uRole;
	}
	

	
	
	
	public boolean checkPassword(String inputPassword)
	{
		if(inputPassword.equals(this.Upassword)){ return true; }
		else { return false; } 
	}





	public String getWID() {
		return WID;
	}





	public void setWID(String wID) {
		WID = wID;
	}





	public String getpName() {
		return pName;
	}





	public void setpName(String pName) {
		this.pName = pName;
	}
	
	
	
	
}
