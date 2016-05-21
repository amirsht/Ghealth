/** This class represents our server side 
 * of the system communication protocol.
 * there will be only one unique implementation of it
 * and it will contain: parser, big switch case, 
 * DB communication protocol and access, TBD...
 */

package Server;
import models.*;
import enums.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;




public class Server extends Thread
{
	
	/** 
	 * 
	 * 
	 * 
	 * 
	 */
    private ServerSocket serverSocket = null;
    public Connection conn;
    public Status status;
    public Patient pt = null;
    public User us = null;
    public String filename;
    
    
    public Server(int port) 
    {
    	try 
    	{
			serverSocket = new ServerSocket(port);
			System.out.println("Server: Waiting for connection...");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    
    public void run()
    {
    	while(true)
	    	try {
				Socket clientSocket = serverSocket.accept();
				System.out.println("Server: Connected");
				communicate(clientSocket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
    }

    public void communicate(Socket cs) {
    	
        //System.out.println("Server-> Start - Before Socket Coonnection.");
        try {
        	Envelop env;
        	ObjectInputStream inputStream;
        	ObjectOutputStream outputStream;
        	
            
            /* getting object */
        	inputStream = new ObjectInputStream(cs.getInputStream());
            
           // System.out.println("Object is = " + inStream);
            
            /* parsing and switching are needed here */
            env  = (Envelop)inputStream.readObject();
            
            System.out.println("Object received (address) = ");
            //System.out.println("Object received (address) = " + env.address);
            
            
            
            
            /* ----- getting data from DB ------ */
            mysqlConnection msql = new mysqlConnection();
            
            System.out.println(env.getType());
            
            switch(env.getType()){
            
            
            /*---      User Tasks:    ---*/
            case GET_USER:
            	us = (User)env.getSingleObject();
            	System.out.println("case GET_USER");
            	env=SCuser.GetExistUser(us.getuID());
            	break;
            
            	
            	
            /*---     Patient Tasks:   ---*/
            case ADD_PATIENT:
            	pt = (Patient)env.getSingleObject();
            	System.out.println("case ADD_PATIENT");
    
            	status=SCpatient.CreatePatient(pt.getpID(),pt.getpFirstName(),pt.getpLastName(),pt.getPtEmail(),pt.getPtPhone(),pt.getPtPrivateClinic(),pt.getPd());
            	env.setStatus(status);
            	if(status == Status.EXIST)
            		System.out.println("The Patient '"+pt.getpID()+"' is already exist in GHEALTH!");
            	break;

            case GET_PATIENT:
            	pt = (Patient)env.getSingleObject();
            	System.out.println("case GET_PATIENT");
            	env=SCpatient.GetExistPatient(pt.getpID());
            	break;
            	
            	
            	
            /*---     Lab-Ref Tasks:   ---*/
            case GET_LAB_REF:
            /* Sending file to client */
            	/* TODO: SQL query returns filename as string */
            	filename="src//Server//files//bbb.jpg";
            	sendFile(filename,cs);
            	break;
            
            	
            case UPDATE_LAB_REF:
            /* Geting file from client */
            	/* TODO: Save & generate path to SQL table */
            	filename="src//Server//files//newfile.jpg";
            	saveFile(filename,cs);
            	break;
            
            	
            default:
				break;
            
            }
            
                        
            
            System.out.println("Before sending object back");
            
            
            
            if(env.getType() != task.GET_LAB_REF && env.getType() != task.UPDATE_LAB_REF)
            {
	            /* Sending data back to client */
	            outputStream = new ObjectOutputStream(cs.getOutputStream());
	            outputStream.writeObject(env);
            }
           //serverSocket.close();
           //cs.close();
           System.out.println("Server-> Finish - Socket waiting for new connection.");

        } catch (SocketException se) {
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
       }
        
        
        
    }
    
    
    public void sendFile(String filename,Socket s) throws IOException {
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		FileInputStream fis = new FileInputStream("src//Server//files//bbb.jpg");
		byte[] buffer = new byte[4096];
		
		while (fis.read(buffer) > 0) {
			dos.write(buffer);
		}
		
		fis.close();
		dos.close();	
	}
    
    
    private void saveFile(String filename,Socket clientSock) throws IOException {
		DataInputStream dis = new DataInputStream(clientSock.getInputStream());
		FileOutputStream fos = new FileOutputStream(filename);
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
    
    
    public static void main(String[] args) {
    	
        Server server;
        
        server = new Server(10007);
        server.start();
     
    }
}
