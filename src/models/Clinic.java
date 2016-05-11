package models;

import java.awt.List;
import java.util.Map;

public class Clinic {
	
	private int cID;
	private String cLocation; 
	
	
	Map<String,List<Doctor>> DoctorsBySpeciality;
	  
	
	public Clinic(int cID, String cLocation)
	{
		this.cID = cID;
		this.cLocation = cLocation; 
		
		//TO DO : add list of doctors by
		
	}

}
