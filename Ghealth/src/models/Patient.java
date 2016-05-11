package models;

public class Patient extends Person{
	private String ptEmail;
	private String ptClinic;
	private PersonalDoctor pd;
	

	
	
	public Patient(int pID, String pName, int pPhone, String pHomeAddr, String ptEmail, String ptClinic,
			PersonalDoctor pd) {
		super(pID, pName, pPhone, pHomeAddr);
		this.ptEmail = ptEmail;
		this.ptClinic = ptClinic;
		this.pd = pd;
	}
	
	public String getPtEmail() {
		return ptEmail;
	}
	public void setPtEmail(String ptEmail) {
		this.ptEmail = ptEmail;
	}
	public String getPtClinic() {
		return ptClinic;
	}
	public void setPtClinic(String ptClinic) {
		this.ptClinic = ptClinic;
	}


	public PersonalDoctor getPd() {
		return pd;
	}


	public void setPd(PersonalDoctor pd) {
		this.pd = pd;
	}
		
}
