package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import enums.task;
import models.Envelop;
import models.Patient;
import models.PersonalDoctor;

public class Controller {
	 	private static Socket socket = null;
	    private static ObjectInputStream inputStream = null;
	    private static ObjectOutputStream outputStream = null;
	    private static boolean isConnected = false;
	    private static Envelop En = null;
	    private static Envelop GetEn = null;
	    
	    
	    
	    
	   public static Envelop communicate(Envelop En) {
	    	
	    	String ip = "127.0.0.1";
	    	

	        while (!isConnected) { //loop not used, for future purposes
	            try {
	            	
	            	/* Connection details + socket creation */
	            	socket = new Socket(ip,10007);
	          
	                System.out.println("Client->Controller: Socket created");
	                
	                /* Output stream creation and related object sending */
	                outputStream = new ObjectOutputStream(socket.getOutputStream());
	               
	                
	                
	                System.out.println("Object to be written = " + 	En);
	                
	                
	                /* Object sending */
	                outputStream.writeObject(En);
	                isConnected = true;
	                
	            } catch (SocketException se) {
	                se.printStackTrace();
	                // System.exit(0);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            
	            /* Receiving response from server */
	            try {
	                inputStream = new ObjectInputStream(socket.getInputStream());
	                try {
	                	
	                	GetEn  = (Envelop) inputStream.readObject();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	                
	                
	                
	         
	                System.out.println("Client: Object received = " + GetEn);
	            
	                /* Flushing and closing stream */
	                outputStream.flush();
	                outputStream.close();
	                
	            } catch (SocketException se) {
	                se.printStackTrace();
	                // System.exit(0);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }//end while
	        
	        isConnected = false; 
	        return GetEn;
	        
	        
	    }//end function

	    
	    
	    
}
