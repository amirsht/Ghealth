package client;
import models.*;
import enums.*;
/* This class represents our client side 
 * of the system communication protocol.
 * the client will be personal for every component in the program
 * and will act as a "control unit"
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class PatientControl {
    private Socket socket = null;
    private ObjectInputStream inputStream = null;
    private ObjectOutputStream outputStream = null;
    private boolean isConnected = false;
    private Envelop En = null;
    private Envelop GetEn = null;
    
    
    /* Empty constructor */
    public PatientControl() {
    }
    
   /* The protocol function  */
    public void communicate(String[] args) {

        while (!isConnected) { //loop not used, for future purposes
            try {
            	/* Connection details + socket creation */
            	if (args.length < 2)
            		socket = new Socket("127.0.0.1", 10007);
            	else
            		socket = new Socket(args[0], Integer.parseInt(args[1]));
                System.out.println("Client: Socket created");
                
                /* Output stream creation and related object sending */
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                //Student student = new Student(1, "Bijoy");
                
                En = new Envelop();
                Patient temp = new Patient(12345,"Moshe Pinto","pinto@gmail.com","p00000","Klalit", new PersonalDoctor("Ibrahim","Ibrahim@gmail.com"));
                
                System.out.println("Test" +temp.getpName());
                //int pID, String pName, String ptEmail, String ptPhone, String ptPrivateClinic, PersonalDoctor pd
                En.setObj(temp);
                En.setType(task.ADD_PATIENT);
                
                //System.out.println("Test" +En.getpName());
                
                System.out.println("Object to be written = " + temp);
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
                
               Patient pTemp = (Patient)(GetEn.getObj());
                
                System.out.println("Client: Object received (address) = " + pTemp.getpName());
            
                /* Flushing and closing stream */
                outputStream.flush();
                outputStream.close();
                
            } catch (SocketException se) {
                se.printStackTrace();
                // System.exit(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    
    public static void main(String[] args) {
        PatientControl client = new PatientControl();
        client.communicate(args);
    }
}
