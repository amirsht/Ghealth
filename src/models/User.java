package models;

import models.Role.Roles;

public class User extends Person  {

	private String Upassword;
	private int WID; //worker id 
	private Role uRole; 
	
	public User(int pID, String pName, int pPhone, String pHomeAddr, String Upassword, int WID, Roles uRole) {
		super(pID, pName, pPhone, pHomeAddr);
		
		this.Upassword = Upassword; 
		this.WID = WID;
		this.uRole = new Role(uRole);
		
	}

	
	
	public int getWID(){ return this.WID;}
	
	public boolean checkPassword(String inputPassword)
	{
		if(inputPassword.equals(this.Upassword)){ return true; }
		else { return false; } 
	}
	
	
	
	
}
