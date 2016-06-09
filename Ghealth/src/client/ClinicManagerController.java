
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

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class ClinicManagerController {
      
	private CM_GUI Clinic_M_GUI;
	private String cm_ID;
	
	
	/*  ~~~~~~~~~~~~~~~~~~~~~~~~   GUI Constractors ~~~~~~~~~~~~~~~~~~~~~~~~  */

	/**
	 * constractor for the Adding patient screen GUI
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
	
	
} //PationControl

