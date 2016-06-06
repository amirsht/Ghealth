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
	private Doctor_rec_GUI doc_recGUI;
	private Doctor_History_GUI doc_hist_GUI;
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
		docPtGUI.ViewHistoryActionListener(new ViewHistoryListener());
	}
	
	public DoctorController(Doctor_rec_GUI docRec,Patient pt,String docID)
	{
		this.pt = pt;
		this.DoctorID = docID;
		doc_recGUI = docRec;
		doc_recGUI.SetPatient(pt);
		doc_recGUI.RecordPatientActionListener(new RecPatientListener());	
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
		
		return apptID;
		}
	}
	
	public void SET_APPOINTMENT_RECORD(String AppID, String AppSummery)
	{
		String [] AppID_AppSummery = {AppID,AppSummery};
		Envelope en = Controller.Control( AppID_AppSummery, task.SET_APPOINTMENT_RECORD);
		
	}
	
	/*  ~~~~~~~~~~~~~~~~~~~~~~~~   Controller Function ~~~~~~~~~~~~~~~~~~~~~~~~  */
	
    
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
				DoctorController doc_rec_cntrl = new DoctorController(docRec,pt,DoctorID);
				
				
			}
			else{
			JOptionPane.showMessageDialog(null, "The Patient '"+pt.getpFirstName() +"'HAS NO APPOINTMENT!" + "\n","Confirm",JOptionPane.OK_OPTION);
			System.out.println("NO APPOINTMENT");}
		}
		
	}
	
	
	class RecPatientListener  implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) {
						
			int appID = GET_CURRENT_APPOINTMENT(pt.getpID(),DoctorID);		
			String strAppID = appID + "";
			
			SET_APPOINTMENT_RECORD(strAppID,doc_recGUI.getRecordField());
			doc_recGUI.dispose();
		}
		
	}
	
	class ViewHistoryListener  implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Tring to VIEW PATIENT HISTORY");
			Doctor_History_GUI doc_histGUI = new Doctor_History_GUI();
			doc_histGUI.SetPatient(pt);
						
		}
		
	}
	
	
} //PationControl

