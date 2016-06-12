package client;
import models.*;
import enums.*;
import GUI.*;
//import client.AppointmentControl.SelectDateListener;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class GeneralManagerController {
      
	private GM_GUI General_M_GUI;
	private String gm_ID;
	public String choosenDateFrom = null;
	public String choosenDateTo = null;
	public Date dateFrom = null;
	public Date dateTo = null;
	public String clinicChosen = null;
	
	
	/*  ~~~~~~~~~~~~~~~~~~~~~~~~   GUI Constractors ~~~~~~~~~~~~~~~~~~~~~~~~  */

	/**
	 * Contractor for the Adding patient screen GUI
	 * @param 
	 */
	public GeneralManagerController(GM_GUI gm,String uID)
	{
		General_M_GUI = gm;
		this.gm_ID = uID;
		General_M_GUI.weeklyActionListener(new showWeeklyListener());
		General_M_GUI.monthlyActionListener(new showMonthlyListener());
		General_M_GUI.getChoosenDateFrom().addActionListener(new SelectDateListenerFrom());
		General_M_GUI.getChoosenDateTo().addActionListener(new SelectDateListenerTo());
	}
	

	
	
	class SelectDateListenerFrom implements ActionListener 
	{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			dateFrom = (Date)General_M_GUI.getDatePickerFrom().getModel().getValue();
<<<<<<< HEAD
			JOptionPane.showMessageDialog(null,"You chose date from: " + (int)(dateFrom.getMonth()+1),"", JOptionPane.INFORMATION_MESSAGE);
=======
			JOptionPane.showMessageDialog(null,"You chose date from: " + dateFrom.getDate(),"", JOptionPane.INFORMATION_MESSAGE);
>>>>>>> 471820e58dddf539357695199a1ce6aebe323165
			
			System.out.println("--------" + dateFrom);
		}
	}
	
	class SelectDateListenerTo implements ActionListener 
	{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			dateTo = (Date)General_M_GUI.getDatePickerFrom().getModel().getValue();
<<<<<<< HEAD
			JOptionPane.showMessageDialog(null,"You chose date to: " + (int)(dateTo.getMonth()+1),"", JOptionPane.INFORMATION_MESSAGE);
=======
			JOptionPane.showMessageDialog(null,"You chose date to: " + dateTo.getDate(),"", JOptionPane.INFORMATION_MESSAGE);
>>>>>>> 471820e58dddf539357695199a1ce6aebe323165
			
			System.out.println("--------" + dateTo);
		}
	}
	
	class SendDateRange implements ActionListener 
	{
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			choosenDateFrom = formatter.format(dateFrom);
			choosenDateTo = formatter.format(dateTo);	
		}
	}
<<<<<<< HEAD
	
	
=======
	
	
>>>>>>> 471820e58dddf539357695199a1ce6aebe323165
	class showWeeklyListener implements ActionListener 
	{
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			clinicChosen = General_M_GUI.getClinicSel();
			if(clinicChosen==null){
				JOptionPane.showMessageDialog(null,"Please select clinic before!","", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if(dateFrom==null || dateTo==null){
				JOptionPane.showMessageDialog(null,"Please select both dates!","", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			if(dateFrom.after(dateTo)){
				JOptionPane.showMessageDialog(null,"Please select positive range!","", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			System.out.println("--clinic selected: " + clinicChosen + " " + clinicChosen.charAt(1));
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			choosenDateFrom = formatter.format(dateFrom);
			choosenDateTo = formatter.format(dateTo);	
<<<<<<< HEAD
			SendAndShow(clinicChosen,choosenDateFrom,choosenDateTo);
			
		}
	}
	
	class showMonthlyListener  implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) {
			clinicChosen = General_M_GUI.getClinicSel();
			if(clinicChosen==null){
				JOptionPane.showMessageDialog(null,"Please select clinic before!","", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			int monthBack = (int)General_M_GUI.getMonthBoxIndex()+1;
			System.out.println("monthBack: "+monthBack);
			dateTo = new Date();dateFrom = new Date();
			dateFrom.setMonth(dateTo.getMonth()-monthBack);
			System.out.println("dateTo:  "+dateTo+" \ndateFrom:  "+dateFrom);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			choosenDateFrom = formatter.format(dateFrom);
			choosenDateTo = formatter.format(dateTo);	
			SendAndShow(clinicChosen,choosenDateFrom,choosenDateTo);
		}
	}
	
	public void SendAndShow(String clinic , String from, String to){

		List<Object> strings_to_server = new ArrayList<Object>();
		System.out.println("Trying to show monthly");
		
		strings_to_server.add(from);
		strings_to_server.add(to);
		strings_to_server.add(Character.toString(clinic.charAt(1))); //clinic id 
		
		/*-- call server --*/
		Envelope en = Controller.Control(strings_to_server,task.GET_CLINIC_CLUSTER_MONTHLY_REPORT);

		List<Object> listObj =  en.getobjList();
	
		System.out.println("Got cluster monthly report");
		
		@SuppressWarnings("unused")
		showClusterReports showRepo = new showClusterReports(listObj);
	}
	
=======
			//----------------------------------------------------------------------------------------//
			// 								V  mey's code  V										  //
			//----------------------------------------------------------------------------------------//
			List<Object> strings_to_server = new ArrayList<Object>();
			System.out.println("Trying to show monthly");
			
			strings_to_server.add("2016-06-01");//strings_to_server.add(choosenDateFrom);
			strings_to_server.add("2016-07-30");//strings_to_server.add(choosenDateTo);
			strings_to_server.add("2"); //clinic id //strings_to_server.add(Character.toString(clinicChosen.charAt(1))); //clinic id 
			
			/*-- call server --*/
			Envelope en = Controller.Control(strings_to_server,task.GET_CLINIC_CLUSTER_MONTHLY_REPORT);

			List<Object> listObj =  en.getobjList();
		
			System.out.println("Got cluster monthly report");
			
			@SuppressWarnings("unused")
			showClusterReports showRepo = new showClusterReports(listObj);
		}
	}
	
	class showMonthlyListener  implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) {
			
			int monthBack = (int)General_M_GUI.getMonthBoxIndex()+1;
			System.out.println("monthBack: "+monthBack);
		}
	}
	
	
>>>>>>> 471820e58dddf539357695199a1ce6aebe323165
} //PationControl

