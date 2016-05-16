/**
 * TODO This is the class description
 */


package models;

import java.io.Serializable;

public class Patient implements Serializable{
	private String ptID;
	private String ptFirstName;
	private String ptLastName;
	private String ptEmail;
	private String ptPhone;
	private String ptPrivateClinic;
	private int ptpersonalDoctorID; 
	//TODO Medical History History Missing
	
	

	/**
	 * This is the default constructor
	 */
	
	public Patient()
	{
		super();
	}
	
	public Patient(String ptID)
	{
		super();
		this.ptID = ptID;
	}
	
	/**
	 * @param pID
	 * @param pName
	 * @param ptEmail
	 * @param ptPhone
	 * @param ptPrivateClinic
	 * @param pd
	 */


	public Patient(String ptID, String ptFirstName, String ptLastName, String ptEmail, String ptPhone,
			String ptPrivateClinic, int ptpersonalDoctorID) {
		super();
		this.ptID = ptID;
		this.ptFirstName = ptFirstName;
		this.ptLastName = ptLastName;
		this.ptEmail = ptEmail;
		this.ptPhone = ptPhone;
		this.ptPrivateClinic = ptPrivateClinic;
		this.ptpersonalDoctorID = ptpersonalDoctorID;
	}


	
	public String getpID() {
		return ptID;
	}
	
	
	public void setpID(String ptID) {
		this.ptID = ptID;
	}



	public String getpFirstName() {
		return ptFirstName;
	}



	public void setpFirstName(String pFirstName) {
		this.ptFirstName = pFirstName;
	}



	public String getpLastName() {
		return ptLastName;
	}



	public void setpLastName(String pLastName) {
		this.ptLastName = pLastName;
	}



	public String getPtEmail() {
		return ptEmail;
	}



	public void setPtEmail(String ptEmail) {
		this.ptEmail = ptEmail;
	}



	public String getPtPhone() {
		return ptPhone;
	}



	public void setPtPhone(String ptPhone) {
		this.ptPhone = ptPhone;
	}



	public String getPtPrivateClinic() {
		return ptPrivateClinic;
	}



	public void setPtPrivateClinic(String ptPrivateClinic) {
		this.ptPrivateClinic = ptPrivateClinic;
	}



	public int getPd() {
		return this.ptpersonalDoctorID;
	}



	public void setptpersonalDoctorID(int pd) {
		this.ptpersonalDoctorID = pd;
	}



	

	
	


	
	



	@Override
	public String toString() {
		return "Patient [Patient ID: " + ptID + " , Name: " + ptFirstName +" "+ ptLastName + ", Email: "
				+ ptEmail + ", Phone=" + ptPhone + ", Private Clinic:" + ptPrivateClinic + ", Personal Doctor ID:" + ptpersonalDoctorID + "]";
	}


}
