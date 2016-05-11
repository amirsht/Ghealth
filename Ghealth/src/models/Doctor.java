package models;

import enums.*;

public class Doctor extends User {
	
	private String dSpeciality; 
	

	
	public Doctor(String uID, String upassword, String uName, Roles uRole, String uEmail, Clinic uClinic, String dSpeciality) {
		
		super(uID, upassword, uName, uRole, uEmail, uClinic);
		
		this.dSpeciality = dSpeciality;
	}


	
	public String getDotorSpeciality(){ return this.dSpeciality;}

}
