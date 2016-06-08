package client;
import models.*;
import enums.*;
import GUI.*;
import client.DoctorController.findPatientListener;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileFilter;
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
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;


public class LabController {
      
	private LabGUI labGUI;
	private Patient pt;
	private String LabWorkerID;
	private List<Object> objList_str;
	private LabSettings lb;
	private Lab_Rec_GUI rec;
	private boolean UploadFile=false;
	
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
					labGUI.getLabHistoryComboBox().setVisible(true);
					labGUI.getbtnChooseLab().setVisible(true);
					labGUI.getLabHistoryComboBox().setModel(new DefaultComboBoxModel(objList.toArray()));
				}
				
			}
			else{
				JOptionPane.showMessageDialog(null, "The Patient '"+pt.getpID()+"' Patient NOT Exists!" + "\n","Confirm",JOptionPane.OK_OPTION);	
			}
			
		}
	}

		
	class BrowseFileListener  implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(UploadFile == true)
			{
				JOptionPane.showMessageDialog(null,
                        "You already upload file, can't load more files! ",
                         "Upload more files.",
                         JOptionPane.OK_OPTION);
				return;
			}
			//rec.getFileChooser().showOpenDialog(this)
			JFileChooser fc = new JFileChooser();
			int val = fc.showOpenDialog(rec);
			String extension;
			try 
			{
				File unfiltered_picture = fc.getSelectedFile();
				extension=unfiltered_picture.getPath();
		        int index=extension.indexOf(".");
		        //get the extension of the file
		        extension=extension.substring(index+1, extension.length());
			       if(val==JFileChooser.APPROVE_OPTION) 
			       {
			           
			           //if the file is not jpg, png, or jpeg, reject it and send a message to the user.
			           if(!extension.matches("[jJ][pP][gG]") && !extension.matches("[pP][nN][gG]") && !extension.matches("[jJ][pP][eE][gG]")) 
			           {
			              JOptionPane.showMessageDialog(null,
			                                            "cannot load file. File must be of type png, jpeg, or jpg. \n Your file is of type " + extension,
			                                             "Error: improper file",
			                                             JOptionPane.OK_OPTION);
			              return;
			            //if the file is of the proper type, display it to the user on the img JLabel.
			           }
			       }
				    System.out.println("Going to upload: "+unfiltered_picture);
					int response = JOptionPane.showConfirmDialog(null,unfiltered_picture+" This is the file you choose\n"
							+ "Upload this file to lab record?","Confirm",
				        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				    if (response == JOptionPane.NO_OPTION)   
				    {
				    	
				    	System.out.println("No option!");
						return;	//return
				      	
				    } 
				    else if (response == JOptionPane.YES_OPTION) 
				    {
				    	lb.setFilePath(unfiltered_picture.toString());
				    	lb.setFileExt(extension);
				    	Controller.Control(lb,task.UPLOAD_FILE_TO_LAB_RECORD);
				    	UploadFile=true;
				    } 
				    else if (response == JOptionPane.CLOSED_OPTION) {
				      System.out.println("JOptionPane closed");
				    }
			} catch(Exception f)
			{ System.out.println(f); }
		}
	}
	
		class ChooseLabListener  implements ActionListener 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{

				labGUI.getLabHistoryComboBox().setVisible(false);
				labGUI.getbtnChooseLab().setVisible(false);
				lb = (LabSettings)objList_str.get(labGUI.getLabHistoryComboBox().getSelectedIndex());
				lb.toStringOpenLabs();
				rec = new Lab_Rec_GUI(lb);
				rec.RecordLabActionListener(new RecordLab());
				rec.getBrowseButton().addActionListener(new BrowseFileListener());
				rec.SetPatient(pt);
				
				
				
				
			}
			
		}
		
		
		class jpgFilter  implements FileFilter 
		{

			@Override
			public boolean accept(File f) {
	            return f.getName().endsWith(".jpg");
			}
			

			
		}
		
		class RecordLab  implements ActionListener 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{

				if(rec.getRecordField().equals("Add your lab record here..."))
				{
					System.out.println("Pleae fill the lab record!");
					JOptionPane.showMessageDialog(null,"Pleae fill the lab record!"
					,"Can't find open lab reference", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				else
				{
					lb.setLabWorkerSummery(rec.getRecordField());
					lb.setLabWorkerID(LoginControl.getUserId());
					Controller.Control(lb,task.UPDATE_LAB_RECORD);
					JOptionPane.showMessageDialog(null,"Lab record was updated!"
							,"Done.", JOptionPane.INFORMATION_MESSAGE);

					UploadFile=false;
					rec.dispose();
				}
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

