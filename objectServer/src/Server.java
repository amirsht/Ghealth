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

public class Server {
	
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private ObjectInputStream inStream = null;
    private ObjectOutputStream outputStream = null;
    private Employee emp;

    
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
            emp  = (Employee) inStream.readObject();
            System.out.println("Object received (address) = " + emp.address);
            
            /* ----- getting data from DB ------ */
            
            /* Sending data back to client */
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(emp);
            
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
