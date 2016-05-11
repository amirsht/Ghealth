package models;

import java.io.Serializable;

public class PersonalDoctor implements Serializable{

	/* Table in DB, patient ID is the PK */
	private String PersonalDoctorName;
	private String PersonalDoctorEmail;
	
	public PersonalDoctor(String personalDoctorName, String personalDoctorEmail) {
		super();
		PersonalDoctorName = personalDoctorName;
		PersonalDoctorEmail = personalDoctorEmail;
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
	
	
}
