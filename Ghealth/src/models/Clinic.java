/**
 * TODO This is the class description
 */


package models;


import java.io.Serializable;
import java.util.*;

public class Clinic implements Serializable{
	
	private int cID;
	private String cName;
	private String cLocation; 
	
	
	Map<String,List<Doctor>> DoctorsBySpeciality;
	  
	public Clinic()
	{
		super();
	}
	
	public Clinic(int cID,String cName,String cLocation)
	{
		super();
		this.cID = cID;
		this.setcName(cName);
		this.cLocation = cLocation;
		
		//TODO : add list of doctors by speciality. each speciality has doctors list for DB
		
	}

	public int getcID() {
		return cID;
	}

	public void setcID(int cID) {
		this.cID = cID;
	}

	public String getcLocation() {
		return cLocation;
	}

	public void setcLocation(String cLocation) {
		this.cLocation = cLocation;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}
	
	
	
	//TODO add get list of doctors. but a doctor can't reach to this function 
	

}
