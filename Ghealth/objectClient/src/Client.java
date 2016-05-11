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

public class Client {
    private Socket socket = null;
    private ObjectInputStream inputStream = null;
    private ObjectOutputStream outputStream = null;
    private boolean isConnected = false;
    private Employee emp = null;

    /* Empty constructor */
    public Client() {
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
                Employee emp = new Employee();
                emp.address = "shimshon 14 haifa israel earth";
                //emp.connection="disconnect";
                System.out.println("Object to be written = " + emp);
                /* Object sending */
                outputStream.writeObject(emp);
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
					emp  = (Employee) inputStream.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                System.out.println("Client: Object received (address) = " + emp.address);
            
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
        Client client = new Client();
        client.communicate(args);
    }
}
