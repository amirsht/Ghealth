package models;
import enums.*;


public class User  {

	private String uID; //worker id 
	private String Upassword;
	private String uName; 
	private Roles uRole; 
	private String uEmail;
	private Clinic uClinic;
	
	
	public User(String uID, String upassword, String uName, Roles uRole, String uEmail, Clinic uClinic) {
		super();
		this.uID = uID;
		Upassword = upassword;
		this.uName = uName;
		this.uRole = uRole;
		this.uEmail = uEmail;
		this.uClinic = uClinic;
	}
	
	public String getuID() {
		return uID;
	}
	public void setuID(String uID) {
		this.uID = uID;
	}
	
	public String getUpassword() {
		return Upassword;
	}
	public void setUpassword(String upassword) {
		Upassword = upassword;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public Roles getuRole() {
		return uRole;
	}
	public void setuRole(Roles uRole) {
		this.uRole = uRole;
	}
	public String getuEmail() {
		return uEmail;
	}
	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}
	public Clinic getuClinic() {
		return uClinic;
	}
	public void setuClinic(Clinic uClinic) {
		this.uClinic = uClinic;
	}
	
	
}
