/**
 * TODO This is the class description
 */


package models;


import java.util.*;

public class Clinic {
	
	private int cID;
	private String cLocation; 
	
	
	Map<String,List<Doctor>> DoctorsBySpeciality;
	  
	
	public Clinic(int cID, String cLocation)
	{
		this.cID = cID;
		this.cLocation = cLocation; 
		
		//TODO : add list of doctors by speciality. each speciality has doctors list for DB
		
	}
	
	
	
	private int getcID(){return this.cID;}
	private String getcLocation(){return this.cLocation;}
	
	//TODO add get list of doctors. but a doctor can't reach to this function 
	

}
