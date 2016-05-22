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
      
	 
    public static Patient PatientCon(Patient pt,task ts){
    	
    	Envelope En = new Envelope();
      
        En.addobjList(pt);
        En.setType(ts);
        En =  Controller.communicate(En);
        pt = (Patient)(En.getSingleObject());
        
        
        //TODO 
    	return pt; 
    }
    
    
    public static Patient CreateNewPatient(Patient pt)
    {
    	return PatientCon(pt,task.ADD_PATIENT);
    }
    
    public static Patient GetExistPatient(Patient pt)
    {
    	return PatientCon(pt,task.GET_PATIENT);
    }
    
    
    public static void main(String[] args) {
    	
    	//Patient temp = new Patient("333333");
    	//Patient newpt = new Patient("200113","Ori","Arel","temp@gmail.com","12345","Klalit",1);
    	Patient temp = new Patient("200113");
    	User pinto = new User("5000");
    	
    	
    	//CreateNewPatient(newpt);
    	temp=GetExistPatient(temp);
    	System.out.println(temp);
        
    }
}
