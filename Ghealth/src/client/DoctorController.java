package client;

import models.*;
import enums.*;
import GUI.*;
//import client.AppointmentControl.cancelAppointmentFromDB;
import Server.Notification;

import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
/* This class represents our client side 
 * of the system communication protocol.
 * the client will be personal for every component in the program
 * and will act as a "control unit"
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


public class DoctorController {
      
	private DoctorGUI docGUI;
	private Doctor_Pt_GUI docPtGUI;
	private Doctor_Create_Lab_GUI docLabGUI;
	private Patient pt;
	private Doctor_rec_GUI doc_recGUI;
	private Doctor_History_GUI doc_hist_GUI;
	private String DoctorID;
	
	private AppointmentSettings as;
	private List<Object> objList_stra;
	private List<Object> objList_strb;
	private List<String> appointmentList;
	private List<String> labList;

	
	
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
		docPtGUI.CreateLabActionListener(new CreateLabListener());
		docPtGUI.SearchPatientActionListener(new SearchAnotherPatientListener());
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
		Notification nt = new Notification();
		nt.appSummery = AppSummery;
		nt.patient = pt;
		
		
		Thread t = new Thread(new Runnable() {
	        @Override
		    public void run() {
		    	URL url = null;
				try {
					url = new URL("http://findicons.com/files/icons/42/basic/64/tick.png");//http://www.archisevilla.org/wp-content/themes/archisevilla/images/loading.gif");
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ImageIcon icon = new ImageIcon(url);
		    	JOptionPane.showMessageDialog(null, "Record saved and mail sent!", "Please wait!", JOptionPane.INFORMATION_MESSAGE, icon);
		    	Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		    }
		});
		t.start();
		Controller.Control( nt, task.SEND_PERSONAL_DOC_MAIL);
	}
	
	public List<String> GET_ARRIVED_APPOINTMENTS(String ptID)
	{
		
		Envelope en = Controller.Control(new Patient(ptID),task.GET_ARRIVED_APPOINTMENTS);
		List<String> strList = new ArrayList<String>();
		objList_stra = en.getobjList();
		
		if(en.getStatus() == Status.NOT_EXIST)
		{
			System.out.println("There is no open appointments to cancel!");
			return null;
		}
		for (Object obj : en.getobjList())
		{
			strList.add(((AppointmentSettings)obj).toStringCancelAppoint());
			System.out.println((AppointmentSettings)obj);
		}
				
		return strList;
	}
	
	public List<String> GET_ARRIVED_LABS(String ptID)
	{
		
		Envelope en = Controller.Control(new Patient(ptID),task.GET_ARRIVED_LABS);
		List<String> strList = new ArrayList<String>();
		objList_strb = en.getobjList();
		
		if(en.getStatus() == Status.NOT_EXIST)
		{
			System.out.println("There is no open labs!");
			return null;
		}
		for (Object obj : en.getobjList())
		{
			strList.add(((LabSettings)obj).tostringShowLabs());
			System.out.println((LabSettings)obj);
		}
				
		return strList;
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
				JOptionPane.showMessageDialog(null, "The Patient '"+findpt.getpID()+"' Patient is NOT Registered!" + "\n","Confirm",JOptionPane.OK_OPTION);	
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
			JOptionPane.showMessageDialog(null, "The Patient '"+pt.getpFirstName() +"'HAS NO APPOINTMENT FOR YOU!" + "\n","Confirm",JOptionPane.OK_OPTION);
			System.out.println("NO APPOINTMENT");}
		}
		
	}
	
	
	class RecPatientListener  implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) {
						
			int appID = GET_CURRENT_APPOINTMENT(pt.getpID(),DoctorID);		
			String strAppID = appID + "";
			
			if(doc_recGUI.getRecordField().equals("Please fill appointment record here..."))
			{
				//TODO - pop up message
				System.out.println("Please fill appointment!");
				return;
			}

			SET_APPOINTMENT_RECORD(strAppID,doc_recGUI.getRecordField());
			doc_recGUI.dispose();
		}
		
	}
	
	class ViewHistoryListener  implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Tring to VIEW PATIENT HISTORY");
			
			appointmentList = GET_ARRIVED_APPOINTMENTS(pt.getpID());
			labList = GET_ARRIVED_LABS(pt.getpID());
			
			
			
			
			if(appointmentList == null && labList == null)
			{
				System.out.println("There is no open appointment for "+pt.getpFirstName()+" "+pt.getpLastName()+"!!");
				JOptionPane.showMessageDialog(null,"There are no recorded Appointments and Labs to show for "+pt.getpFirstName()+" "+pt.getpLastName()+"!!","No recorded Medical History", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			

			doc_hist_GUI = new Doctor_History_GUI();
			doc_hist_GUI.SetPatient(pt);
			doc_hist_GUI.LabResultBoxActionListener(new LabResultBoxListener());	
			doc_hist_GUI.AppointmentHistoryBoxActionListener(new AppointmentHistoryBoxListener());
			doc_hist_GUI.getAppHistoryButton().addActionListener(new ShowAppointmentHistoryListener());;
			doc_hist_GUI.getLabHistoryButton().addActionListener(new ShowLabHistoryListener());;
			doc_hist_GUI.getAppointmentHistoryBox().setVisible(false);
			doc_hist_GUI.getLabResultBox().setVisible(false);
			doc_hist_GUI.getTitleLabel().setVisible(false);
			
			if(labList != null)
			{
				doc_hist_GUI.AppointmentHistoryBoxActionListener(new AppointmentHistoryBoxListener());
				doc_hist_GUI.getLabResultBox().setModel(new DefaultComboBoxModel(labList.toArray()));
			}
			if(appointmentList != null)
			{
				doc_hist_GUI.getAppointmentHistoryBox().setModel(new DefaultComboBoxModel(appointmentList.toArray()));
				System.out.println("There is no open appointment for "+pt.getpFirstName()+" "+pt.getpLastName()+"!!");
			}
		}
		
	}
	
	class AppointmentHistoryBoxListener  implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Tring to VIEW Appointment History!!");
			doc_hist_GUI.getimagePan().setVisible(false);

			int selectedIndex = doc_hist_GUI.getAppointmentHistoryBox().getSelectedIndex();
			System.out.println("" + selectedIndex);
			AppointmentSettings aaa = (AppointmentSettings)objList_stra.get(selectedIndex);
			System.out.println(aaa);
			//Envelope en = Controller.Control(as,task.CANCEL_APPOINTMENT_FROM_DB);
			System.out.println("Tring to VIEW Appointment Summery INDEX:" + selectedIndex);	
			doc_hist_GUI.SetSummery(aaa.getApsSummery());

		}
		
	}
	
	class LabResultBoxListener  implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			doc_hist_GUI.getimagePan().setVisible(false);
			int selectedIndex = doc_hist_GUI.getLabResultBox().getSelectedIndex();
			LabSettings ls = (LabSettings)objList_strb.get(selectedIndex);
			
			doc_hist_GUI.SetSummery(ls.getLabWorkerSummery());
			
			Controller.Control(ls,task.SEND_FILE_TO_CLIENT);
			
			System.out.println(ls.getFilePath());
			if(!ls.getFilePath().equals("NO FILE"))
			{
				//Controller.Control(ls,task.SEND_FILE_TO_CLIENT);
				System.out.println(ls.getFileExt());
				doc_hist_GUI.setAddToImagePan("src\\images\\lab_file."+ls.getFileExt());
				doc_hist_GUI.getimagePan().setVisible(true);
			}
			else doc_hist_GUI.getimagePan().setVisible(false);
			System.out.println("Tring to VIEW LAB RESULT!!");
			
			
		}
		
	}
	
	
	class CreateLabListener  implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Trying to create Lab");
			docLabGUI = new Doctor_Create_Lab_GUI();
			docLabGUI.SetPatient(pt);
			docLabGUI.CreateNewLabRefActionListener(new CreateLabinDBListener());
		}
	}
	
	
	
	class CreateLabinDBListener  implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(docLabGUI.getRecordField().equals("Please fill your requirements for the lab worker.") || docLabGUI.getRecordField().equals("") )
			{
				JOptionPane.showMessageDialog(null,"Please fill your requirements!","Can't create lab ref!", JOptionPane.INFORMATION_MESSAGE);
				return;	//return to the find patient window
			}
			
			LabSettings ls = new LabSettings();
			ls.setLabPtID(pt.getpID());
			ls.setLabDocID(LoginControl.getUserId());
			ls.setLabDoctorReq(docLabGUI.getRecordField());
			Controller.Control(ls,task.CREATE_LAB_REF);
			System.out.println("Create Lab in DB!!");
			JOptionPane.showMessageDialog(null,"Lab Request open success","Lab request complete", JOptionPane.INFORMATION_MESSAGE);
			docLabGUI.dispose();
		
		}
	
	}
	
	
	class SearchAnotherPatientListener  implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			docPtGUI.dispose();
			DoctorGUI doc_gui = new DoctorGUI();	        		   		   
	   		DoctorController docCon = new DoctorController(doc_gui,LoginControl.getUserId());
		
		}
	}
	
	
	class ShowLabHistoryListener  implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(labList != null)
			{
				System.out.println("Show Labs!");
				doc_hist_GUI.getLabResultBox().setVisible(true);
				doc_hist_GUI.getTitleLabel().setText("Lab Result History");
				doc_hist_GUI.getTitleLabel().setVisible(true);	
			}
			else{ 
				JOptionPane.showMessageDialog(null,"There is no Lab Record for this Patient!","No Lab History!", JOptionPane.INFORMATION_MESSAGE);
				doc_hist_GUI.getTitleLabel().setVisible(false);
			}
			doc_hist_GUI.getAppointmentHistoryBox().setVisible(false);
		}
	}
	
	
	class ShowAppointmentHistoryListener  implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(appointmentList != null)
			{
				System.out.println("Show Appointments!");
				doc_hist_GUI.getAppointmentHistoryBox().setVisible(true);
				doc_hist_GUI.getTitleLabel().setText("Appointment History");
				doc_hist_GUI.getTitleLabel().setVisible(true);	
			}
			else
			{
				doc_hist_GUI.getTitleLabel().setVisible(false);
				JOptionPane.showMessageDialog(null,"There is no Appointment Record for this Patient!","No Appointment History!", JOptionPane.INFORMATION_MESSAGE);
			}

			doc_hist_GUI.getimagePan().setVisible(false);
			doc_hist_GUI.getLabResultBox().setVisible(false);
		}
	}
} //PationControl

