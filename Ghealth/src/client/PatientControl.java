package client;
import models.*;
import enums.*;
import GUI.*;
import client.UserControl.CancelListener;
import client.UserControl.LoginListener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
      
	 
	private CostumerServiceGUI csGUI;
	private Patient pt;
	
	
	public PatientControl(CostumerServiceGUI cs)
	{
		csGUI = cs;
		pt = new Patient();
		csGUI.addPatientActionListener(new AddPatientListener());
		csGUI.addCancelActionListener(new CancelListener());	
	}
	
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
    
    
    
    
    class CancelListener implements ActionListener 
    {
    	@Override
    	public void actionPerformed(ActionEvent e)
    	{
    		csGUI.dispose();	//Closes the login window
    	}	
    }//action



	class AddPatientListener  implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			System.out.println("Add Patient Lis");
			//Patient newpt = new Patient("200113","Ori","Arel","temp@gmail.com","12345","Klalit",1);
			Patient newpt = new Patient(csGUI.getPationID(),
										csGUI.getfName(),
										csGUI.getlName(),
										csGUI.geteMail(),
										csGUI.getPhone(),
										csGUI.getpClinic(),
										csGUI.getDoctorID()	);
			CreateNewPatient(newpt);
			
		}
		
	}
    
    /*
    public static void main(String[] args) {
    	
    	//Patient temp = new Patient("333333");
    	Patient newpt = new Patient("200114","Ori","Arel","temp@gmail.com","12345","Klalit","1");
    	//Patient temp = new Patient("200113");
    	//User pinto = new User("5000");
    	
    	
    	CreateNewPatient(newpt);
    	//temp=GetExistPatient(temp);
    	//System.out.println(temp);
        
    }*/

} //PationControl

