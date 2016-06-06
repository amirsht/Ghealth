package client;
import models.*;
import enums.*;
import GUI.*;
import client.DoctorController.findPatientListener;

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


public class LabController {
      
	private LabGUI labGUI;
	private Patient pt;
	private String LabWorkerID;
	private List<Object> objList_str;
	
	/*  ~~~~~~~~~~~~~~~~~~~~~~~~   GUI Constractors ~~~~~~~~~~~~~~~~~~~~~~~~  */

	
	
	/**
	 * 
	 * constractor for the find patient screen GUI
	 */
	
	public LabController(LabGUI lab,String labworker)
	{
		LabWorkerID = labworker;
		labGUI = lab;
		labGUI.findPatientActionListener(new findPatientListener());
		labGUI.getbtnChooseLab().addActionListener(new ChooseLabListener());

	}
	
	
	public int GET_CURRENT_LABS(String ptID, String docID)
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


	class findPatientListener  implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("trying to find a patient " + labGUI.getPtID());
			
		if(labGUI.getPtID().equals("") || labGUI.getPtID().equals("Insert ID here...")) 		//If fields are empty , show error message
       	 {
       		 JOptionPane.showMessageDialog(null,"Please insert Patient ID!","Error", JOptionPane.INFORMATION_MESSAGE);    
       		 return;	//return to the find patient window
       	 }//if
		
			// This is new verison of client controller func call:
			pt = new Patient(labGUI.getPtID());
			Envelope en = Controller.Control(pt,task.GET_PATIENT);

			/* if Patient exist */
			if (en.getStatus() == Status.EXIST)
			{
				pt = (Patient)en.getSingleObject();
				System.out.println("Patient Exist!\n"+pt);
				///pt.getpID()
				List<String> objList = GET_LAB_HISTORY(pt.getpID());
				if(objList == null)
				{
					JOptionPane.showMessageDialog(null,"There is no SCHEDUELD lab for patient: "+pt.getpFirstName()+" "+pt.getpLastName()
					,"Can't find open lab reference", JOptionPane.INFORMATION_MESSAGE);
					return;	//return to the find patient window
				}
				else 
				{
					labGUI.getLabHistoryComboBox().setModel(new DefaultComboBoxModel(objList.toArray()));
				}
				
			}
			else{
				JOptionPane.showMessageDialog(null, "The Patient '"+pt.getpID()+"' Patient NOT Exists!" + "\n","Confirm",JOptionPane.OK_OPTION);	
			}
			
		}
	}

		
		class ChooseLabListener  implements ActionListener 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				//labGUI.dispose();
				LabSettings lb = (LabSettings)objList_str.get(labGUI.getLabHistoryComboBox().getSelectedIndex());
				lb.toStringOpenLabs();
				Lab_Rec_GUI rec = new Lab_Rec_GUI(lb);
				
			}
			
		}
		
		private List<String> GET_LAB_HISTORY(String ptID) 
		{
			
			Envelope en = Controller.Control(new Patient(ptID),task.GET_SCHEDUELD_LAB);
			List<String> strList = new ArrayList<String>();
			objList_str = en.getobjList();
			
			if(en.getStatus() == Status.NOT_EXIST)
			{
				System.out.println("There is no open lab!");
				return null;
			}
			for (Object obj : en.getobjList())
			{
				strList.add(((LabSettings)obj).toStringOpenLabs());
				System.out.println((LabSettings)obj);
			}
					
			return strList;
		}
	
	
} //LabControl

