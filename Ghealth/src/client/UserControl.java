package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


import enums.task;
import models.Envelope;
import models.Patient;
import models.User;
import GUI.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.Executors;


public class UserControl {
	
	private LoginGUI loginG;
	private User UserLogin;
	//private User user;
	//private User U;
	
	
	/**
	 * 
	 * constractor
	 */
	public UserControl (LoginGUI lC )
	{
		loginG = lC;
		UserLogin = new User();
		loginG.addLoginActionListener(new LoginListener());
		loginG.addCancelActionListener(new CancelListener());	
	}


public static User UserCon(User us,task ts){
    	
    	Envelope En = new Envelope();
      
        En.addobjList(us);
        En.setType(ts);
        En =  Controller.communicate(En);
        us = (User)(En.getSingleObject());
        
        //TODO 
    	return us; 
    }
    
    
    
    public static User GetExistUser(User us)
    {
		return UserCon(us,task.GET_USER);
    }
    
    
    
    class CancelListener implements ActionListener 
    {
    	@Override
    	public void actionPerformed(ActionEvent e)
    	{
    	loginG.dispose();	//Closes the login window
    	}	
    }//action



    class LoginListener implements ActionListener
    {
         public void actionPerformed(ActionEvent ev)
         {      		
        	 String pass = loginG.getPasswordField();	//Gets the password the user entered
        	 String user = loginG.getUserField();		//Gets the user name the user entered
        	 
        	 System.out.println("Tried to login with user and pw " + user +" "+ pass);
        	
        	 if(pass.equals("")|| user.equals("")) 		//If fields are empty , show error message
        	 {
        		 JOptionPane.showMessageDialog(null,"Empty Fields!","Error", JOptionPane.INFORMATION_MESSAGE);    
        		 return;								//return to the login window
        	 }//if
        	 else
        	 {
        			 
        		 try
        		 {										 //set the user name and password and send to server
        		   UserLogin.setuPassword(pass);
        		   UserLogin.setuID(user);
        		   
        		   User us = GetExistUser(UserLogin);
        		   
        		   if(UserLogin.getuPassword().equals(us.getuPassword()))
        		   {
        			   System.out.println("Password Match!");
        		   	   loginG.dispose();	//Closes the login window
        		   	   
        		   	   switch(us.getuRole())
        		   	   {
	        		   	   case CUSTOMER_SERVICE:
	        		   		   CS_GUI_findPatient cs = new CS_GUI_findPatient();
	        		   		   PatientControl pt = new PatientControl(cs);
	        		   		//TODO: open the next window (menu).
	        		   		   break;
	        		   	   case LAB_WORKER:
	        		   		//TODO: open the next window (menu).
	        		   		   break;
	        		   	   case CLINIC_MANAGER:
	        		   		//TODO: open the next window (menu).
	        		   		   break;
	        		   	   case DOCTOR:
	        		   		   
	        		   		DoctorGUI doctor = new DoctorGUI();
	        		   		//TODO: open the next window (menu).
	        		   	   		break;
	        		   	   case GENERAL_MANAGER:
	        		   		//TODO: open the next window (menu).
	        		   		   break;
	        		   		default:
	        		   		//TODO: open the next window (menu).
	        		   			break;
        		   	   }
        		   	   
        		   }
        		   else{ 
        			   System.out.println("Pass not match");
        			   JOptionPane.showMessageDialog(null,"Pass not match!!!!","Error", JOptionPane.INFORMATION_MESSAGE);
        		   }
        		   System.out.println("pass: " + us.getuPassword() + " " + us.getuRole());
        		   
        		   
        		 }
        		 catch(Exception e){System.out.println(e);     	 }
    	      }//else
          }
    }//action
}
