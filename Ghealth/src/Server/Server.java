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




public class Server {
	
	/** 
	 * 
	 * 
	 * 
	 * 
	 */
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private ObjectInputStream inStream = null;
    private ObjectOutputStream outputStream = null;
    private Envelop env;
    public Connection conn;
    public Status status;
    
    
    public Patient pt = null;
    public User us = null;
    
    
    
    public Server() {
    }

    public void communicate() {
    	
        System.out.println("Server-> Start - Before Socket Coonnection.");
        try {
        	int port=10007;
        	/* Server listening and connection */
            serverSocket = new ServerSocket(port);
            System.out.println("Server: Waiting for connection...");
            socket = serverSocket.accept();
            System.out.println("Server: Connected");
            
            /* getting object */
            inStream = new ObjectInputStream(socket.getInputStream());
            
           // System.out.println("Object is = " + inStream);
            
            /* parsing and switching are needed here */
            env  = (Envelop) inStream.readObject();
            
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
            	
            default:
				break;
            
            }
            
                        
            
            
            /* Sending data back to client */
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(env);

           serverSocket.close();
           socket.close();
           System.out.println("Server-> Finish - Socket close.");

        } catch (SocketException se) {
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
       }
    }
    
    public static void main(String[] args) {
    	
        Server server;
        
        while(true) 
        {
        	server = new Server();
        	server.communicate();
        }
    }
}
