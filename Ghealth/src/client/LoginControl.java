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


public class LoginControl {
	
	private LoginGUI loginG;
	private User UserLogin;
	private static String user_full_name;
	private static String uId = null;
	//private User user;
	//private User U;
	
	
	/**
	 * 
	 * constractor
	 */
	public LoginControl (LoginGUI lC )
	{
		loginG = lC;
		UserLogin = new User();
		loginG.addLoginActionListener(new LoginListener());
		//loginG.addCancelActionListener(new CancelListener());	
	}

/*
public static User UserCon(User us,task ts){
    	
    	Envelope En = new Envelope();
      
        En.addobjList(us);
        En.setType(ts);
        En =  Controller.communicate(En);
        us = (User)(En.getSingleObject());
        
    	return us; 
    }
    
    public static User GetExistUser(User us)
    {
		return UserCon(us,task.GET_USER);
    }
    	
    */
    
    public static String getUser_full_name() {
		return user_full_name;
	}

    public static String getUserId() {
    	System.out.println("after get user id");
		return uId;
	}

	public static void setUser_full_name(String user_full_name) {
		LoginControl.user_full_name = user_full_name;
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
        		   
        		   Envelope en = Controller.Control(UserLogin,task.GET_USER);
        		   User us = (User)en.getSingleObject();
        		   uId = us.getuID();
        		 
        		   if(us.getuID() != null && !us.getuID().equals("0"))
        		   {
        			   setUser_full_name(us.getuFirstName()+" "+us.getuLastName());
	        		   if(UserLogin.getuPassword().equals(us.getuPassword()))
	        		   {
	        			   System.out.println("Password Match!");
	        		   	   loginG.dispose();	//Closes the login window
	        		   	   
	        		   	   switch(us.getuRole())
	        		   	   {
		        		   	   case CUSTOMER_SERVICE:
		        		   		   System.out.println("This user is CUSTOMER_SERVICE");
		        		   		   CS_GUI_findPatient cs = new CS_GUI_findPatient();
		        		   		   PatientControl pt = new PatientControl(cs);
		        		   		//TODO: open the next window (menu).
		        		   		   break;
		        		   	   case LAB_WORKER:
		        		   		System.out.println("This user is LAB_WORKER");
		        		   		//TODO: open the next window (menu).
		        		   		   break;
		        		   	   case CLINIC_MANAGER:
			        		   		System.out.println("This user is CLINIC_MANAGER");
		        		   		//TODO: open the next window (menu).
		        		   		   break;
		        		   	   case DOCTOR:
			        		   		System.out.println("This user is DOCTOR");	        		   		   
			        		   		DoctorGUI doctor = new DoctorGUI();
			        		   		//TODO: open the next window (menu).
		        		   	   		break;
		        		   	   case GENERAL_MANAGER:
			        		   		System.out.println("This user is GENERAL_MANAGER");
		        		   		//TODO: open the next window (menu).
		        		   		   break;
		        		   		   
		        		   		default:
		        		   		//TODO: open the next window (menu).
		        		   			break;
	        		   	   }
	        		   	   
	        		   }
	        		   else
	        		   { 
	        			   System.out.println("Password incorrect, Please try again.");
	        			   JOptionPane.showMessageDialog(null,"Pass not match!!!!","Error", JOptionPane.INFORMATION_MESSAGE);
	        		   }
        		   }
        		   else if (us.getuID().equals("0"))
        			   JOptionPane.showMessageDialog(null,"User is in another session!","Error", JOptionPane.INFORMATION_MESSAGE);
        		   else
        			   JOptionPane.showMessageDialog(null,"No such User!!!!","Error", JOptionPane.INFORMATION_MESSAGE); 
        			   	
        		   System.out.println("pass: " + us.getuPassword() + " " + us.getuRole());
        		   
        		   
        		 }
        		 catch(Exception e)
        		 {	
        			 System.out.println("Exception from LoginControl:\n"+e);     	 
        		}
    	      }//else
          }
    }//action
}
