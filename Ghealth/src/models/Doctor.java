/**
 * TODO This is the class description
 */


package models;

import enums.*;

public class Doctor extends User {
	
	private String dSpeciality;
	private int dcid;
	/**
	 * @param dSpeciality
	 */
	
	// Constractor With dSpeciality
	public Doctor(String uID, String uPassword, String uFirstName, String uLastName, Roles uRole, String uEmail,
			Clinic uClinic,String dSpeciality) 
	{
		super(uID, uPassword, uFirstName, uLastName, uRole, uEmail, uClinic);
		this.setdSpeciality(dSpeciality);
	}
	
	//Constractor without dSpeciality
	public Doctor(String uID, String uPassword, String uFirstName, String uLastName, Roles uRole, String uEmail,
			Clinic uClinic) 
	{
		super(uID, uPassword, uFirstName, uLastName, uRole, uEmail, uClinic);
	}

	public String getdSpeciality() {
		return dSpeciality;
	}

	public void setdSpeciality(String dSpeciality) {
		this.dSpeciality = dSpeciality;
	}

	public int getDcid() {
		return dcid;
	}

	public void setDcid(int dcid) {
		this.dcid = dcid;
	}
	

}
