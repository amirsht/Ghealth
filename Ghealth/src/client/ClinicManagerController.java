
package client;
import models.*;
import enums.*;
import GUI.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* This class represents our client side 
 * of the system communication protocol.
 * the client will be personal for every component in the program
 * and will act as a "control unit"
 */
import java.util.List;


public class ClinicManagerController {
      
	private CM_GUI Clinic_M_GUI;
	private String cm_ID;
	
	
	/*  ~~~~~~~~~~~~~~~~~~~~~~~~   GUI Constructors ~~~~~~~~~~~~~~~~~~~~~~~~  */

	/**
	 * Constructor for the Adding patient screen GUI
	 * @param 
	 */
	public ClinicManagerController(CM_GUI cm,String uID)
	{
		Clinic_M_GUI = cm;
		Clinic_M_GUI.weeklyActionListener(new showWeeklyListener());
		Clinic_M_GUI.monthlyActionListener(new showMonthlyListener());
		this.cm_ID = uID;
	}
	

	
	

	class showWeeklyListener  implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			Envelope en = Controller.Control(LoginControl.getUserClinic(),task.GET_CLINIC_WEEKLY_REPORT);
			List<Object> listObj =  en.getobjList();
		
			System.out.println("Got weekly report");
			
			@SuppressWarnings("unused")
			ShowWeeklyReports showRepo = new ShowWeeklyReports(listObj);
		}
		
	}
	
	class showMonthlyListener  implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			/*-- call server --*/
			Envelope en = Controller.Control(LoginControl.getUserClinic(),task.GET_CLINIC_MONTHLY_REPORT);
			List<Object> listObj =  en.getobjList();
		
			System.out.println("Got monthly report");
			
			@SuppressWarnings("unused")
			ShowMonthlyReport showRepo = new ShowMonthlyReport(listObj);
			
		
		}
		
	}
	
	
} //ClinicManagerController

