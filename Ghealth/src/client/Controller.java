package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import enums.task;
import models.Envelope;
import models.Patient;
import models.PersonalDoctor;

public class Controller {
	 	private static Socket socket = null;
	    private static ObjectInputStream inputStream = null;
	    private static ObjectOutputStream outputStream = null;
	    private static boolean isConnected = false;
	    private static Envelope En = null;
	    private static Envelope GetEn = null;
	    
	    
	    public static void sendFile(String filename) throws IOException {
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			FileInputStream fis = new FileInputStream(filename);
			byte[] buffer = new byte[4096];
			
			while (fis.read(buffer) > 0) {
				dos.write(buffer);
			}
			
			fis.close(); //Git Test
			dos.close();	
			
			
			
		}
		
		
		/* method copied from Server */
		private static void saveFile() throws IOException {
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			FileOutputStream fos = new FileOutputStream("src//client//files//temp_file.jpg");
			byte[] buffer = new byte[4096];
			
			int filesize = 15123; // Send file size in separate msg
			int read = 0;
			int totalRead = 0;
			int remaining = filesize;
			while((read = dis.read(buffer, 0, Math.min(buffer.length, remaining))) > 0) {
				totalRead += read;
				remaining -= read;
				System.out.println("read " + totalRead + " bytes.");
				fos.write(buffer, 0, read);
			}
			
			fos.close();
			dis.close();
		} 
	    
	   public static Envelope communicate(Envelope En) {
	    	
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
	            	
	                /* Choose if get file or object */
	                if(En.getType() == task.GET_LAB_REF)
	                	saveFile();
	                else if(En.getType() == task.UPDATE_LAB_REF)
	                	sendFile("src//client//files//afasdf.jpg");
	                else
	                {
		                inputStream = new ObjectInputStream(socket.getInputStream());
		                try {
		                	
		                	GetEn  = (Envelope) inputStream.readObject();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}	     
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
