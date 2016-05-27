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

import javax.swing.JOptionPane;


public class PatientControl {
      
	 
	private CS_GUI_addPatient csGUI;
	private CS_GUI_findPatient csGUI_findPatient;
	private CS_GUI_Appoint csGUI_Appoint;
	
	private Patient pt;
	
	/**
	 * 
	 * constractor for the Adding patient screen GUI
	 * @param 
	 */
	public PatientControl(CS_GUI_addPatient cs)
	{
		csGUI = cs;
		pt = new Patient();
		csGUI.addPatientActionListener(new AddPatientListener());
		csGUI.addCancelActionListener(new CancelListener());	
	}
	
	/**
	 * 
	 * constractor for the find patient screen GUI
	 */
	public PatientControl(CS_GUI_findPatient cs)
	{
		csGUI_findPatient = cs;
		pt = new Patient();
		csGUI_findPatient.findPatientActionListener(new findPatientListener());
		csGUI_findPatient.addCancelActionListener(new CancelListener());
		csGUI_findPatient.createNewPtActionListener(new createNewPtActionListener());
	}
	
	
	/**
	 * 
	 * constractor for the find patient screen GUI
	 * @param
	 * @param
	 * 
	 */
	public PatientControl(CS_GUI_Appoint cs,Patient pt)
	{
		csGUI_Appoint = cs;
		csGUI_Appoint.setfName(pt.getpFirstName());
		csGUI_Appoint.setlName(pt.getpLastName());
		csGUI_Appoint.seteMail(pt.getPtEmail());
		csGUI_Appoint.setPhone(pt.getPtPhone());
		csGUI_Appoint.setpClinic(pt.getPtPrivateClinic());
		csGUI_Appoint.setPationID(pt.getpID());
		csGUI_Appoint.setDoctorID(pt.getPd());
		csGUI_Appoint.createAppointActionListener(new createAppointListener());
		csGUI_Appoint.cancelAppointActionListener(new CancelAppintListener());	
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
			
			csGUI.dispose();
			
		}
		
	}
	
	class findPatientListener  implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			System.out.println("trying to find a patient " + csGUI_findPatient.getfName());
			csGUI_findPatient.getfName();
			Patient findpt = new Patient(csGUI_findPatient.getfName(),"","","","","","");
			
			findpt=GetExistPatient(findpt);
			
			if (findpt.getpID()!= null){
				JOptionPane.showMessageDialog(null,"Patient Exists! \n" + findpt,"INFO", JOptionPane.INFORMATION_MESSAGE);
				
				csGUI_findPatient.dispose();
				
				CS_GUI_Appoint appoint = new CS_GUI_Appoint();
				
				PatientControl pt_appoint = new PatientControl(appoint,findpt);
				
			}
			else{
				csGUI_findPatient.dispose();
				
				CS_GUI_findPatient csGUI_addOpt = new CS_GUI_findPatient();
				csGUI_addOpt.addPatientOpt();
				
				PatientControl addPtOpt_CL = new PatientControl(csGUI_addOpt);
				
				JOptionPane.showMessageDialog(null,"Patient NOT Exists!","Error", JOptionPane.INFORMATION_MESSAGE);
				
				
			}
			
		}
		
	}
	
	class createAppointListener  implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			System.out.println("trying to create appoint ");
			
			
		}
		
	}
	
	class CancelAppintListener  implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			System.out.println("trying to cancel appoint ");
			
			
		}
		
	}
	
	class createNewPtActionListener  implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			System.out.println("create new patient form fill");
			
			CS_GUI_addPatient addpt = new CS_GUI_addPatient();
			PatientControl addpt_CL = new PatientControl(addpt);
			
			
		}
		
	}
	
    

} //PationControl

