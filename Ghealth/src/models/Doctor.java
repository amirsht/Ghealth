/**
 * TODO This is the class description
 */


package models;

import enums.*;

public class Doctor extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3874467060459550851L;
	private DoctorSpeciallity dSpeciality;
	private int dcid;
	/**
	 * @param dSpeciality
	 */
	
	// Constractor With dSpeciality
	public Doctor(String uID, String uPassword, String uFirstName, String uLastName, Roles uRole, String uEmail,
			Clinic uClinic,DoctorSpeciallity dSpeciality) 
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

	public DoctorSpeciallity getdSpeciality() {
		return dSpeciality;
	}

	public void setdSpeciality(DoctorSpeciallity dSpeciality) {
		this.dSpeciality = dSpeciality;
	}

	public int getDcid() {
		return dcid;
	}

	public void setDcid(int dcid) {
		this.dcid = dcid;
	}
	

}
