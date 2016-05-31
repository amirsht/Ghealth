package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import GUI.CS_GUI_Appoint;
import GUI.CS_GUI_findPatient;
import GUI.CS_GUI_newAppoint;
import GUI.LoginGUI;
import enums.DoctorSpeciallity;
import enums.task;
import models.Envelope;
import models.Patient;
import models.User;

public class AppointmentControl {


	private CS_GUI_Appoint csGUI_Appoint;
	private CS_GUI_newAppoint csGUI_CreateNewAppoint;
	private Patient pt;
	
	public AppointmentControl(CS_GUI_newAppoint cs,Patient pt)
	{
		csGUI_CreateNewAppoint = cs;
		csGUI_CreateNewAppoint.setfName(pt.getpFirstName());
		csGUI_CreateNewAppoint.setPtID(pt.getpID());
		csGUI_CreateNewAppoint.SelectDocTypeActionListener(new SelectDoctorTypeListener());
		//csGUI_CreateNewAppoint.cancelNewAppointActionListener(new CancelListener());	
	}
	
	/**
	 * 
	 * constractor for the find patient screen GUI
	 * @param
	 * @param
	 * 
	 */
	public AppointmentControl(CS_GUI_Appoint cs,Patient pt)
	{
		this.pt = pt;
		
		csGUI_Appoint = cs;
		csGUI_Appoint.setfName(pt.getpFirstName());
		csGUI_Appoint.setlName(pt.getpLastName());
		csGUI_Appoint.seteMail(pt.getPtEmail());
		csGUI_Appoint.setPhone(pt.getPtPhone());
		csGUI_Appoint.setpClinic(pt.getPtPrivateClinic());
		csGUI_Appoint.setPationID(pt.getpID());
		csGUI_Appoint.setDoctorID(pt.getPd());
		csGUI_Appoint.createAppointActionListener(new createNewAppointListener());
		csGUI_Appoint.SearchPatientActionListener(new SearchPatientListener());
		csGUI_Appoint.LogOutActionListener(new LogOutListener());
		csGUI_Appoint.cancelAppointActionListener(new CancelAppintListener());	
	}
	

	class createNewAppointListener  implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			System.out.println("trying to create appoint ");
			
			CS_GUI_newAppoint newApp_gui = new CS_GUI_newAppoint();
			
			AppointmentControl newApp_ctrl = new AppointmentControl(newApp_gui,pt);

		}
		
	}
	
	
		
	
	
	class SelectDoctorTypeListener  implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
		
			System.out.println("Trying to GET Select Doctor type: " + csGUI_CreateNewAppoint.docBox.getSelectedItem().toString());

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
	
	class LogOutListener  implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			
			csGUI_Appoint.dispose();
			LoginControl userctrl = new LoginControl(new LoginGUI());

		}
		
	}
	
	class SearchPatientListener  implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			csGUI_Appoint.dispose();
	   		PatientControl pt = new PatientControl(new CS_GUI_findPatient());

		}
		
	}
	
    class CancelListener implements ActionListener 
    {
    	@Override
    	public void actionPerformed(ActionEvent e)
    	{
    		csGUI_CreateNewAppoint.dispose();
    		//csGUI_Appoint.dispose();	//Closes the login window
    	}	
    }//action
}
