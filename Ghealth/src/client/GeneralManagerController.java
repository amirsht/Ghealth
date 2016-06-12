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
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class GeneralManagerController {
      
	private GM_GUI General_M_GUI;
	private String gm_ID;
	
	
	
	public GeneralManagerController(){
		
	}
	
	
	/*  ~~~~~~~~~~~~~~~~~~~~~~~~   GUI Constractors ~~~~~~~~~~~~~~~~~~~~~~~~  */

	/**
	 * constractor for the Adding patient screen GUI
	 * @param 
	 */
	public GeneralManagerController(GM_GUI gm,String uID)
	{
		General_M_GUI = gm;
		General_M_GUI.weeklyActionListener(new showWeeklyListener());
		General_M_GUI.monthlyActionListener(new showMonthlyListener());
		this.gm_ID = uID;
	//	General_M_GUI.getChoosenDateOK( new showMonthlyClusterListener() );
		
	}
	

	
	
  

	class showWeeklyListener  implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			
			System.out.println("Trying to show weekly");
			
		}
		
	}
	
	class showMonthlyListener  implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			System.out.println("Trying to show monthly");
			
		
		}
		

	}
	class showMonthlyClusterListener  implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) 
	
		{
			
			List<Object> strings_to_server = new ArrayList<Object>();
			System.out.println("Trying to show monthly");
			
			strings_to_server.add("2016-06-01");
			strings_to_server.add("2016-06-17");
			strings_to_server.add("2"); //clinic id 
			
			/*-- call server --*/
			Envelope en = Controller.Control(strings_to_server,task.GET_CLINIC_CLUSTER_MONTHLY_REPORT);
			
			
			List<Object> listObj =  en.getobjList();
		
			System.out.println("Got cluster monthly report");
			
			@SuppressWarnings("unused")
			showClusterReports showRepo = new showClusterReports(listObj);
		
		}
		
		
		
	
}
	
	
	public void show(){
		
		
		List<Object> strings_to_server = new ArrayList<Object>();
		System.out.println("Trying to show monthly");
		
		strings_to_server.add("2016-06-01");
		strings_to_server.add("2016-07-30");
		strings_to_server.add("2"); //clinic id 
		
		/*-- call server --*/
		Envelope en = Controller.Control(strings_to_server,task.GET_CLINIC_CLUSTER_MONTHLY_REPORT);
		
		
		List<Object> listObj =  en.getobjList();
	
		System.out.println("Got cluster monthly report");
		
		@SuppressWarnings("unused")
		showClusterReports showRepo = new showClusterReports(listObj);
	
	}
	
	public static void main(String []args){
		GeneralManagerController sh = new GeneralManagerController();
		
		
		sh.show();
		
		
	}
} //PationControl

