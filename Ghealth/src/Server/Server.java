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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




public class Server extends Thread
{
	/** 
	 * 
	 * 
	 */
    private ServerSocket serverSocket = null;
    public Connection conn;
    public Status status;
    public Patient pt = null;
    public User us = null;
    public AppointmentSettings as = null;
    public String filename;
    public static List<String> sessionList = new ArrayList<String>();
    
    
    
    
    
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
	    		System.out.println("where am i");
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
        	Envelope env;
        	List<Object> objList;
        	ObjectInputStream inputStream;
        	ObjectOutputStream outputStream;
        	
            
            /* getting object */
        	inputStream = new ObjectInputStream(cs.getInputStream());
            
           // System.out.println("Object is = " + inStream);
            
            /* parsing and switching are needed here */
            env  = (Envelope)inputStream.readObject();
            
            System.out.println("Object received (address) = ");
            //System.out.println("Object received (address) = " + env.address);
            
            
            
            
            /* ----- getting data from DB ------ */
            mysqlConnection msql = new mysqlConnection();
            
            System.out.println(env.getType());
     
            
            
            switch(env.getType()){
         
            /*---      User Tasks:    ---*/
            case GET_USER:
            	us = (User)env.getSingleObject();
            	if(searchUserSession(us.getuID())==true){
            		((User)env.getSingleObject()).setuID("0");
            		break;
            	}
            	else{
            		sessionList.add(us.getuID());
	            	System.out.println("case GET_USER");
	            	env=SCuser.GetExistUser(us.getuID());
	            	break;
            	}
            	
            	
            /*---     Patient Tasks:   ---*/
            case ADD_PATIENT:
            	System.out.println("case ADD_PATIENT");
            	pt = (Patient)env.getSingleObject();
            	status=SCpatient.CreatePatient(pt);
            	env.setStatus(status);
            	break;

            case GET_PATIENT:
            	pt = (Patient)env.getSingleObject();
            	System.out.println("case GET_PATIENT");
            	env=SCpatient.GetExistPatient(pt.getpID());
            	break;
            	
            case GET_PRIVATE_CLINIC_LIST:
            	env = SCpatient.GetClinicList();
            	break;
            	
            	
            /*---   Appointment Tasks: ---*/  
            case CREATE_NEW_APPOINTMENT:
            	System.out.println("CREATE_NEW_APPOINTMENT");
            	as = (AppointmentSettings) env.getSingleObject();
            	status=SCappointment.CreateAppointment(as);
            	env.setStatus(status);
            	break;
            	
            case GET_DOCTORS_IN_CLINIC_BY_TYPE:
            	
            	objList = env.getobjList();
            	System.out.println(objList.get(0).toString() + objList.get(1).toString());
            	env = SCappointment.GetClinicDoctorList(objList.get(0).toString(),objList.get(1).toString());
            	System.out.println("GET_DOCTORS_IN_CLINIC_BY_TYPE");
            	break;
            	
            case GET_AVAILIBLE_DOCTOR_HOURS:
            	objList = env.getobjList();
            	System.out.println(objList.get(0).toString() + objList.get(1).toString());
            	System.out.println("GET_AVAILIBLE_DOCTOR_HOURS");
            	env = SCappointment.GetAvailibleDoctorHours(objList.get(0).toString(),objList.get(1).toString());
            	break;
            	
            	
            case GET_OPEN_APPOINTMENTS:
            	System.out.println("GET_OPEN_APPOINTMENTS");
            	pt = (Patient)env.getSingleObject();
            	env = SCappointment.GetSCHEDUELDAppointments(pt.getpID());
            	break;
            	
            	
            case CANCEL_APPOINTMENT_FROM_DB:
            	System.out.println("CANCEL_APPOINTMENT_FROM_DB");
            	as = (AppointmentSettings) env.getSingleObject();
            	env.setStatus(SCappointment.CancelAppointment(as.getApsID()));
            	break;
            	
            	
            	/*--- Doctor flow Tasks  ----*/
            case GET_CURRENT_APPOINTMENT_ID:
            	System.out.println("GET_CURRENT_APPOINTMENT_ID");
            	String[] patiend_doc =(String[])env.getSingleObject();
            	env = SCdocAppointment.GetCurrentAppointment(patiend_doc[0], patiend_doc[1]);
            	
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
            	
            case LOG_OUT:
                /* client is logging out */
            	us = (User)env.getSingleObject();
            	removeSession(us.getuID());
                	break;
            
            	
            default:
				break;
            
            }
            
                        
            
            System.out.println("Before sending object back");
            
            
            /* if the task is not to send FILE to client */
            if(env.getType() != task.GET_LAB_REF && env.getType() != task.UPDATE_LAB_REF)
            {
	            /* Sending data back to client */
            	System.out.println("before new output stream");
	            outputStream = new ObjectOutputStream(cs.getOutputStream());
	            System.out.println("before write env to out stream");
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
    
    
    public void removeSession(String getuID) {
    	System.out.println("before remove");
		sessionList.remove(getuID);
	}



	public boolean searchUserSession(String uid){
    	for(String str: sessionList) {
    	    if(str.trim().equals(uid))
    	       return true;
    	}
    	return false;
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
    
    
  /*
    public static void main(String[] args) {
    	
        Server server;     
        server = new Server(5555);
        server.start();
     
    }*/
}