package client;

import server.*;
import view.*;
import models.Envelope;
import models.User;
import ocsf.client.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Window;
import java.io.*;

import javax.swing.JOptionPane;

import controllers.*;


/**
 * This class overrides some of the methods defined in the abstract
 * superclass in order to give more functionality to the client.

 */
public class GhealthClient extends ObservableClient
{
  //Instance variables **********************************************
  
  /**
   * The interface type variable.  It allows the implementation of 
   * the display method in the client.
   */
  ChatIF clientUI; 
  
  ArrayList<String> Ar;
  private Object currController;
  private User currUser = null;


  
  //Constructors ****************************************************
  
/**
   * Constructs an instance of the chat client.
   *
   * @param host The server to connect to.
   * @param port The port number to connect on.
   * @param clientUI The interface type variable.
   */
  
  public GhealthClient(String host, int port) throws IOException {
	  
    super(host, port); //Call the superclass constructor

    Ar = new ArrayList<>();
    
    openConnection();
  }
  
	private LoginGUI loginG;
  //Instance methods ************************************************
    
  /**
   * This method handles all data that comes in from the server.
   *
   * @param msg The message from the server.
   */
  public synchronized void handleMessageFromServer(Object message)  
  {
	
		if(message instanceof Envelope)
		{
			

			((LoginController)currController).handleDBResult(message);

		}//if
	

	
	if(message instanceof String ){ 
		
	if(((String)message).equals("UserOrPassIncorrect") ){

		 ((LoginController)currController).handleDBResult(message);

	}
		
	
	if(((String)message).equals("NoUser") ){
		 JOptionPane.showMessageDialog(null,"No Such User!","Error", JOptionPane.ERROR_MESSAGE);

	}
	
	
	}
	notify();   
  }
  

  public void handleMessageFromClientUI(String message)
  {
    try
    {
    	sendToServer(message);
    }
    catch(IOException e)
    {
      clientUI.display
        ("Could not send message to server.  Terminating client.");
      quit();
    }
  }
  
  /**
   * This method terminates the client.
   */
  
  public Object getCurrObj() {
	return currController;
}
  
  public User getCurrUser() {
		return currUser;
	}


	public  void setCurrUser(User currUser) {
		this.currUser = currUser;
	}


public void setCurrObj(Object currObj) {
	this.currController = currObj;
}
  public void quit()
  {
    try
    {
      closeConnection();
    }
    catch(IOException e) {}
    System.exit(0);
  }
}
//End of ChatClient class
