package client;
import models.*;
import enums.*;
/* This class represents our client side 
 * of the system communication protocol.
 * the client will be personal for every component in the program
 * and will act as a "control unit"
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class PatientControl {
      
	 
    public static boolean PatientCon(Patient pt,task ts){
    	
    	Envelop En = new Envelop();
      
        En.addobjList(pt);
        En.setType(ts);
        En =  Controller.communicate(En);
        Patient pTemp = (Patient)(En.getSingleObject());
        System.out.println(pTemp);
        
        //TODO 
    	return false; 
    }
    
    
    public static boolean CreateNewPatient(Patient pt)
    {
    	PatientCon(pt,task.ADD_PATIENT);
		return false;
    }
    
    public static boolean GetExistPatient(Patient pt)
    {
    	PatientCon(pt,task.GET_PATIENT);
		return false;
    }
    
    
    public static void main(String[] args) {
    	
    	//Patient temp = new Patient("333333");
    	Patient temp = new Patient("333333");
    	
    	//PatientControl client = new PatientControl();
    	CreateNewPatient(temp);	
    	GetExistPatient(temp);
        
        
    }
}
