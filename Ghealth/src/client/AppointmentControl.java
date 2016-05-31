package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import GUI.CS_GUI_Appoint;
import GUI.CS_GUI_findPatient;
import GUI.CS_GUI_newAppoint;
import GUI.LoginGUI;
import enums.DoctorSpeciallity;
import enums.task;
import models.AppointmentSettings;
import models.Envelope;
import models.Patient;
import models.User;

public class AppointmentControl {


	private CS_GUI_Appoint csGUI_Appoint;
	private CS_GUI_newAppoint csGUI_CreateNewAppoint;
	private Patient pt;
	private AppointmentSettings as;
	private List<Object> objList_str;
	
	public AppointmentControl(CS_GUI_newAppoint cs,Patient pt)
	{
		this.pt=pt;
		as = new AppointmentSettings();
		as.setApsPtID(pt.getpID());
		csGUI_CreateNewAppoint = cs;
		csGUI_CreateNewAppoint.setfName(pt.getpFirstName());
		csGUI_CreateNewAppoint.setPtID(pt.getpID());
		csGUI_CreateNewAppoint.SelectDocTypeActionListener(new SelectDoctorTypeListener());
		csGUI_CreateNewAppoint.DoctorBoxActionListener(new SelectClnicAndDoctor());
		csGUI_CreateNewAppoint.DoctorHoursBoxActionListener(new SelectHour());
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
	

	public List<String> GET_DOCTOR_CLINIC(String ptID,DoctorSpeciallity ds)
	{
		/* GET_DOCTORS_IN_CLINIC_BY_TYPE */
		
		System.out.println("INSIDE GET_DOCTOR_CLINIC FUNC");
		List<Object> objList = new ArrayList<Object>();
		objList_str = new ArrayList<Object>();
		
		objList.add(ptID);
		objList.add(ds);
		Envelope en = Controller.Control(objList,task.GET_DOCTORS_IN_CLINIC_BY_TYPE);
		List<String> strList = new ArrayList<String>();
		for (Object obj : en.getobjList())
		{
			objList_str.add(((User)obj).getuID());
			strList.add(((User)obj).toStringClinicList());
			System.out.println(((User)obj).toStringClinicList());
		}
		
		return strList;
	}
	
	public List<String> GET_DOCTOR_HOURS(String ptID,String date)
	{
		/* GET_AVAILIBLE_DOCTOR_HOURS */
		Envelope en;
		List<Object> objList = new ArrayList<Object>();
		objList.add(date);
		objList.add(ptID);
		en = Controller.Control(objList,task.GET_AVAILIBLE_DOCTOR_HOURS);
		List<String> strList = new ArrayList<String>();
		for (Object obj : en.getobjList())
		{
			System.out.println("Hour - ");
			System.out.println(obj.toString());
			strList.add(obj.toString());
		}
		
		return strList;
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
	
	class InsertNewAppointToDBListener  implements ActionListener 
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
			System.out.println("Trying to GET Select Doctor type: " + csGUI_CreateNewAppoint.getDocBox().getSelectedItem().toString());
					
			 
			DoctorSpeciallity ds = DoctorSpeciallity.valueOf(csGUI_CreateNewAppoint.getDocBox().getSelectedItem().toString());
			List<String> objList = GET_DOCTOR_CLINIC(pt.getpID(),ds);
			csGUI_CreateNewAppoint.getDoctorBox().setModel(new DefaultComboBoxModel(objList.toArray()));
			csGUI_CreateNewAppoint.getDoctorBox().setVisible(true);
			csGUI_CreateNewAppoint.getDoctorHoursBox().setVisible(false);
		}
		
	}
	
	class SelectClnicAndDoctor  implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Trying to GET Select Clinic & Doctor: " + csGUI_CreateNewAppoint.getDoctorBox().getSelectedItem().toString());
			List<String> objList = GET_DOCTOR_HOURS(pt.getpID(),"2016-07-15");
			
			/* Set Selected Doctor ID to Appointment Settings. */
			int SelectedIndex = csGUI_CreateNewAppoint.getDoctorBox().getSelectedIndex();
			as.setApsDocID(objList_str.get(SelectedIndex).toString());
			
			System.out.println(as.getApsDocID());
			csGUI_CreateNewAppoint.getDoctorHoursBox().setModel(new DefaultComboBoxModel(objList.toArray()));
			csGUI_CreateNewAppoint.getDoctorHoursBox().setVisible(true);
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
	class SelectHour implements ActionListener 
    {
    	@Override
    	public void actionPerformed(ActionEvent e)
    	{
    		as.setApsTime(csGUI_CreateNewAppoint.getDoctorHoursBox().getSelectedItem().toString());
    		System.out.println("Hour Choosen " + as.getApsTime());
    	}	
    }//action
	
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
