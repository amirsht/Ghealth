/**
 * TODO This is the class description
 */



package models;
import java.io.Serializable;

import enums.*;


public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4147848156651925332L;
	
	private String uID; //worker id 
	private String uPassword;
	private String uFirstName;
	private String uLastName;
	private Roles uRole; 
	private String uEmail;
	private Clinic uClinic;
	
	/**
	 * This is the default constructor
	 */
	public User()
	{
		super();
	}
	
	public User(String uID)
	{
		super();
		this.uID = uID;
	}
	
	/**
	 * @param uID
	 * @param uPassword
	 * @param uFirstName
	 * @param uLastName
	 * @param uRole
	 * @param uEmail
	 * @param uClinic
	 */
	public User(String uID, String uPassword, String uFirstName, String uLastName, Roles uRole, String uEmail,
			Clinic uClinic) 
	{
		super();
		this.uID = uID;
		this.uPassword = uPassword;
		this.setuFirstName(uFirstName);
		this.setuLastName(uLastName);
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
	
	public String getuPassword() {
		return uPassword;
	}
	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
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

	public String getuFirstName() {
		return uFirstName;
	}

	public void setuFirstName(String uFirstName) {
		this.uFirstName = uFirstName;
	}

	public String getuLastName() {
		return uLastName;
	}

	public void setuLastName(String uLastName) {
		this.uLastName = uLastName;
	}

	//TODO - only for us.
	@Override
	public String toString() {
		return "User [uID=" + uID + ", uPassword=" + uPassword + ", uFirstName=" + uFirstName + ", uLastName="
				+ uLastName + ", uRole=" + uRole + ", uEmail=" + uEmail + ", Clinic Name=" + uClinic.getcName() + "]";
	}
	
	
}
