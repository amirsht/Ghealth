/**
 * TODO This is the class description
 */


package models;

import java.io.Serializable;

public class PrivateClinic implements Serializable{

	/* Table in DB, patient ID is the PK */
	private String PrivateClinicName;
	private String PrivateClinicEmail;
	
	
	public PrivateClinic()
	{
		super();
	}
	public PrivateClinic(String privateClinicName, String privateClinicEmail) {
		super();
		setPrivateClinicName(privateClinicName);
		setPrivateClinicEmail(privateClinicEmail);
	}


	public String getPrivateClinicName() {
		return PrivateClinicName;
	}


	public void setPrivateClinicName(String privateClinicName) {
		PrivateClinicName = privateClinicName;
	}


	public String getPrivateClinicEmail() {
		return PrivateClinicEmail;
	}


	public void setPrivateClinicEmail(String privateClinicEmail) {
		PrivateClinicEmail = privateClinicEmail;
	}

	
	
	
	
	
}
