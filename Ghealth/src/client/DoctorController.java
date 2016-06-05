package client;
import models.*;
import enums.*;
import GUI.*;

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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class DoctorController {
      
	private DoctorGUI docGUI;
	private Doctor_Pt_GUI docPtGUI;
	private Patient pt;
	private Doctor_rec_GUI doc_rec;
	private String DoctorID;
	
	/*  ~~~~~~~~~~~~~~~~~~~~~~~~   GUI Constractors ~~~~~~~~~~~~~~~~~~~~~~~~  */

	
	
	/**
	 * 
	 * constractor for the find patient screen GUI
	 */
	public DoctorController(DoctorGUI doc,String docID)
	{
		DoctorID = docID;
		docGUI = doc;
		docGUI.findPatientActionListener(new findPatientListener());

	}
	
	public DoctorController(Doctor_Pt_GUI doc_pt,Patient pt,String docID)
	{
		this.pt = pt;
		this.DoctorID = docID;
		docPtGUI = doc_pt;
		docPtGUI.SetPatient(pt);
		docPtGUI.RecordAppointActionListener(new RecAppointListener());
		//docPtGUI.SearchPatientActionListener(new SearchPatientListener());
		//docPtGUI.LogOutActionListener(new LogOutListener());
		//docPtGUI.cancelAppointActionListener(new CancelAppointListener());	
	}
	
	public int GET_CURRENT_APPOINTMENT(String ptID, String docID)
	{
		String [] patientID_doctorID = {ptID,docID};
		Envelope en = Controller.Control( patientID_doctorID, task.GET_CURRENT_APPOINTMENT_ID);
		
		if(en.getStatus() == Status.NOT_EXIST)
		{
			System.out.println("There is no open appointments to RECORD!");
			return 0;
		}else{
		
		int apptID = (int)en.getSingleObject();
			
		
		System.out.println("the appointement ID back from SERVER:" + apptID );
		
		return apptID;}
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
  

	class findPatientListener  implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("trying to find a patient " + docGUI.getPtID());
			
		if(docGUI.getPtID().equals("") || docGUI.getPtID().equals("Insert ID here...")) 		//If fields are empty , show error message
       	 {
       		 JOptionPane.showMessageDialog(null,"Please insert Patient ID!","Error", JOptionPane.INFORMATION_MESSAGE);    
       		 return;	//return to the find patient window
       	 }//if
		
			// This is new verison of client controller func call:
			Patient findpt = new Patient(docGUI.getPtID());
			Envelope en = Controller.Control(findpt,task.GET_PATIENT);

			/* if Patient exist */
			if (en.getStatus() == Status.EXIST)
			{
				findpt = (Patient)en.getSingleObject();
				System.out.println("Patient Exist!\n"+findpt);
				docGUI.dispose();
				
				docPtGUI = new Doctor_Pt_GUI();
				DoctorController doc_cnt = new DoctorController(docPtGUI,findpt,DoctorID);

			}
			else{
				JOptionPane.showMessageDialog(null, "The Patient '"+findpt.getpID()+"' Patient NOT Exists!" + "\n","Confirm",JOptionPane.OK_OPTION);	
			}
			
		}
		
	}
	
	class RecAppointListener  implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(GET_CURRENT_APPOINTMENT(pt.getpID(),DoctorID) != 0){
				
				Doctor_rec_GUI docRec = new Doctor_rec_GUI();
				docRec.SetPatient(pt);
				
			}
			else{
			JOptionPane.showMessageDialog(null, "The Patient '"+pt.getpFirstName() +"'HAS NO APPOINTMENT!" + "\n","Confirm",JOptionPane.OK_OPTION);
			System.out.println("NO APPOINTMENT");}
		}
		
	}
	
	
} //PationControl

