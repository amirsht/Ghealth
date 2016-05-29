package client;
import models.*;
import enums.*;
import GUI.*;
import client.LoginControl.CancelListener;
import client.LoginControl.LoginListener;

import java.awt.Container;
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

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class PatientControl {
      
	private CS_GUI_addPatient csGUI_addPatient;
	private CS_GUI_findPatient csGUI_findPatient;
	
	/*  ~~~~~~~~~~~~~~~~~~~~~~~~   GUI Constractors ~~~~~~~~~~~~~~~~~~~~~~~~  */

	
	/**
	 * 
	 * constractor for the Adding patient screen GUI
	 * @param 
	 */
	public PatientControl(CS_GUI_addPatient cs,String pID)
	{
		csGUI_addPatient = cs;
		csGUI_addPatient.addPatientActionListener(new AddPatientListener());
		csGUI_addPatient.setPationID(pID);
		//csGUI_addPatient.addCancelActionListener(this.dispose());	
		//csGUI_addPatient.addCancelActionListener(new CancelListener());	
	}
	
	/**
	 * 
	 * constractor for the find patient screen GUI
	 */
	public PatientControl(CS_GUI_findPatient cs)
	{
		csGUI_findPatient = cs;
		csGUI_findPatient.findPatientActionListener(new findPatientListener());
		//csGUI_findPatient.addCancelActionListener(new CancelListener());
		//csGUI_findPatient.createNewPtActionListener(new createNewPtActionListener());
	}
	
	

	
	
	
	/*  ~~~~~~~~~~~~~~~~~~~~~~~~   Controller Function ~~~~~~~~~~~~~~~~~~~~~~~~  */
	/*
    public static Patient PatientCon(Patient pt,task ts){
    	
    	Envelope En = new Envelope();
      
    	//Build the envelope that will be send to server 
        En.addobjList(pt);
        En.setType(ts);
        /* communicate will send to the server 
         * and get from the server
         *  the envelope 
        En =  Controller.communicate(En);
        if (En.getStatus() == Status.NOT_EXIST)
        	return null;
        pt = (Patient)(En.getSingleObject());
    	return pt; 
    	
    } /*  END Controller Function ~~~~~~~~~~~~~~~~~~~~~~~~  */
    
    

	/*  ~~~~~~~~~~~~~~~~~~~~~~~~   ActionListener Functions ~~~~~~~~~~~~~~~~~~~~~~~~  */
  

	class AddPatientListener  implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			System.out.println("Add Patient Lis");
			System.out.println(csGUI_addPatient.getClinicBox().getSelectedItem());
		
			Patient newpt = new Patient(csGUI_addPatient.getPationID(),
										csGUI_addPatient.getfName(),
										csGUI_addPatient.getlName(),
										csGUI_addPatient.geteMail(),
										csGUI_addPatient.getPhone(),
										(String)csGUI_addPatient.getClinicBox().getSelectedItem(),
										csGUI_addPatient.getDoctorID()	);
			
			Controller.Control(newpt,task.ADD_PATIENT); //New Controller Call
			//PatientCon(newpt,task.ADD_PATIENT); --> OLD Controller call.
			JOptionPane.showMessageDialog(null,"The Patient: "+newpt.getpFirstName()+" "+newpt.getpLastName()
												+" Was successfully added!","Error", JOptionPane.INFORMATION_MESSAGE);
			csGUI_addPatient.dispose();
			
		}
		
	}
	
	class findPatientListener  implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("trying to find a patient " + csGUI_findPatient.getPtID());
			csGUI_findPatient.getPtID();
			
			// This is new verison of client controller func call:
			Patient findpt = new Patient(csGUI_findPatient.getPtID());
			Envelope en = Controller.Control(findpt,task.GET_PATIENT);
			
			
			//PatientCon(findpt,task.GET_PATIENT);  This is old verison of client controller func call
			
			
			/* if Pation exist */
			if (en.getStatus() == Status.EXIST)
			{
				findpt = (Patient)en.getSingleObject();
				JOptionPane.showMessageDialog(null,"Patient Exists! \n" + findpt,"INFO", JOptionPane.INFORMATION_MESSAGE);
				csGUI_findPatient.dispose();
				CS_GUI_Appoint appoint = new CS_GUI_Appoint();
				AppointmentControl pt_appoint = new AppointmentControl(appoint,findpt);
			}
			else{

				int response = JOptionPane.showConfirmDialog(null, "The Patient '"+findpt.getpID()+"' Patient NOT Exists! "
						+ "\nWould you like to create new pation?","Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    if (response == JOptionPane.NO_OPTION)   {
				      System.out.println("No button clicked");
				    } 
				    else if (response == JOptionPane.YES_OPTION) {
				      System.out.println("Yes button clicked");
						csGUI_findPatient.dispose();
						System.out.println("Create new patient form fill");
						PatientControl addpt_CL = new PatientControl(new CS_GUI_addPatient(),findpt.getpID());
				    } 
				    else if (response == JOptionPane.CLOSED_OPTION) {
				      System.out.println("JOptionPane closed");
				    }
			
			}
			
		}
		
	}
	
	
	
	
	
} //PationControl

