/**
 * TODO This is the class description
 */


package models;

import java.io.Serializable;

public class PrivateClinic implements Serializable{

	/* Table in DB, patient ID is the PK */
	private int PrivateClinicID;
	private String PrivateClinicName;
	private String PrivateClinicEmail;
	public PrivateClinic(int privateClinicID, String privateClinicName, String privateClinicEmail) {
		super();
		setPrivateClinicID(privateClinicID);
		setPrivateClinicName(privateClinicName);
		setPrivateClinicEmail(privateClinicEmail);
	}
	public int getPrivateClinicID() {
		return PrivateClinicID;
	}
	public void setPrivateClinicID(int privateClinicID) {
		PrivateClinicID = privateClinicID;
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
