package client;

import enums.task;
import models.Envelope;
import models.Patient;
import models.User;

public class LabControllerBeta {

public static void LabController(task ts){
    	
    	Envelope En = new Envelope();
        En.setType(ts);
        En =  Controller.communicate(En);
        
        
    }
    
    

    
    public static void GetFileFromServer()
    {
    	LabController(task.GET_LAB_REF);
    }
    
    public static void SendFileToServer()
    {
    	LabController(task.UPDATE_LAB_REF);
    }
    
    public static void main(String[] args) {
    	
    	SendFileToServer();
    	System.out.println("Done!!!!!");
        
    }
    
    
}
