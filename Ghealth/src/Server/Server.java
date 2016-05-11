package Server;
import models.*;
import enums.*;
import client.Envelop;

/* This class represents our server side 
 * of the system communication protocol.
 * there will be only one unique implementation of it
 * and it will contain: parser, big switch case, 
 * DB communication protocol and access, TBD...
 */
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;




public class Server {
	
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private ObjectInputStream inStream = null;
    private ObjectOutputStream outputStream = null;
    private Envelop env;
    public Connection conn;
    
    public Server() {
    }

    public void communicate() {
        try {
        	/* Server listening and connection */
            serverSocket = new ServerSocket(10007);
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
            
            //TODO    /* ----- getting data from DB ------ */
            mysqlConnection msql = new mysqlConnection();
            
            Patient temp = (Patient)env.getObj();
            System.out.println(env.getType());
            switch(env.getType()){
            
            case ADD_PATIENT:
            	System.out.println("case ADD_PATIENT");
            	msql.updateValue(temp);
            	break;
            
            default:
				break;
            
            }
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            /* Sending data back to client */
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(env);
           
            
           socket.close();

        } catch (SocketException se) {
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException cn) {
            cn.printStackTrace();
       }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.communicate();
    }
}
