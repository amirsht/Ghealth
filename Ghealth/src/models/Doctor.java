package models;

import models.Role.Roles;

public class Doctor extends User {
	
	
	private String dSpeciality; 
	
	public Doctor(int pID, String pName, int pPhone, String pHomeAddr, String Upassword, int WID, Roles uRole,String dSpeciality) {
		super(pID, pName, pPhone, pHomeAddr, Upassword, WID, uRole);
		this.dSpeciality = dSpeciality;
		
	}

	
	public String getDotorSpeciality(){ return this.dSpeciality;}

}
