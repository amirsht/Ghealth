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


public class GeneralManagerController {
      
	private GM_GUI General_M_GUI;
	private String gm_ID;
	
	
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

