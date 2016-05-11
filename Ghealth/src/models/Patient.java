package models;

import java.io.Serializable;

public class Patient implements Serializable{
	private int pID;
	private String pName;
	private String ptEmail;
	private String ptPhone;
	private String ptPrivateClinic;
	private PersonalDoctor pd;
	
	
	
	public Patient(int pID, String pName, String ptEmail, String ptPhone, String ptPrivateClinic, PersonalDoctor pd) {
		super();
		this.pID = pID;
		this.pName = pName;
		this.ptEmail = ptEmail;
		this.ptPhone = ptPhone;
		this.ptPrivateClinic = ptPrivateClinic;
		this.pd = pd;
	}
	
	
	public int getpID() {
		return pID;
	}
	public void setpID(int pID) {
		this.pID = pID;
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
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public PersonalDoctor getPd() {
		return pd;
	}
	public void setPd(PersonalDoctor pd) {
		this.pd = pd;
	}
	

	

}
