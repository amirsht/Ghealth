/**
 * TODO This is the class description
 */


package models;

import java.io.Serializable;

public class PersonalDoctor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4471079822624266319L;
	
	/* Table in DB, patient ID is the PK */
	private int PersonalDoctorID;
	private String PersonalDoctorName;
	private String PersonalDoctorEmail;
	/**
	 * @param PersonalDoctorName
	 * @param PersonalDoctorEmail
	 */
	
	public PersonalDoctor(int idonly)
	{
		super();
		this.PersonalDoctorID = idonly;
	}
	
	public PersonalDoctor(int PersonalDoctorID,String PersonalDoctorName, String PersonalDoctorEmail) {
		super();
		this.setPersonalDoctorID(PersonalDoctorID);
		this.PersonalDoctorName = PersonalDoctorName;
		this.PersonalDoctorEmail = PersonalDoctorEmail;
	}
	
	public String getPersonalDoctorName() {
		return PersonalDoctorName;
	}
	public void setPersonalDoctorName(String personalDoctorName) {
		PersonalDoctorName = personalDoctorName;
	}
	public String getPersonalDoctorEmail() {
		return PersonalDoctorEmail;
	}
	public void setPersonalDoctorEmail(String personalDoctorEmail) {
		PersonalDoctorEmail = personalDoctorEmail;
	}

	public int getPersonalDoctorID() {
		return PersonalDoctorID;
	}

	public void setPersonalDoctorID(int personalDoctorID) {
		PersonalDoctorID = personalDoctorID;
	}
	
	
}
