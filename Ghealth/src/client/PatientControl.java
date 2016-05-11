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
  
    
    
    
    /* Empty constructor 
    public PatientControl() {
    }
    */
    public static boolean UpdatePatient(Patient pt){
    	
    	Envelop En = new Envelop();
        Envelop GetEn = new Envelop();
      
        
        System.out.println("Test" +pt.getpName());
       
        En.setObj(pt);
        En.setType(task.UPDATE_PATIENT);
        
       
        GetEn =  Controller.communicate(En);
        
        Patient pTemp = (Patient)(GetEn.getObj());
        System.out.println(pTemp.getPtEmail());
        
    	
        //TODO 
    	return false; 
    }
 
    
    public static void main(String[] args) {
    	
    	Patient temp = new Patient(12345,"Moshe Pinto","pinto@gmail.com","p00000","Klalit", new PersonalDoctor("Ibrahim","Ibrahim@gmail.com"));
        //PatientControl client = new PatientControl();
        UpdatePatient(temp);
        
        
    }
}
