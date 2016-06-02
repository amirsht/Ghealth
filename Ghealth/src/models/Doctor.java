/**
 * TODO This is the class description
 */


package models;

import java.io.Serializable;

import javax.management.relation.Role;

import enums.*;

public class Doctor extends User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3874467060459550851L;
	private DoctorSpeciallity dSpeciality;
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
	
	//Constractor without password & roll
	public Doctor(String uID,String uFirstName,String uLastName,Clinic uClinic,DoctorSpeciallity dSpeciality) 
	{
		super(uID,"****",uFirstName,uLastName,Roles.DOCTOR,"",uClinic);
		this.setdSpeciality(dSpeciality);
	}

	public DoctorSpeciallity getdSpeciality() {
		return dSpeciality;
	}

	public void setdSpeciality(DoctorSpeciallity dSpeciality) {
		this.dSpeciality = dSpeciality;
	}

	

}
