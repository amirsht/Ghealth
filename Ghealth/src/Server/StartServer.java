package Server;

import java.io.PrintStream;

/**
 * main class that start the server
 *
 */
public class StartServer {

	public static serverLogGui servLog = new serverLogGui();
    public static PrintStream ps = null;
    public static Server sv = null;
    public static int port = 5555;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	    servLog.dispose();
	    ServerGui serv = new ServerGui();
	    mysqlConnection servCon = new mysqlConnection(serv,servLog);
	    sv = new Server(port);
	    sv.start();
	    ps = activateSYSOHook();
	    System.setOut(ps);
		
	}
	
	public static PrintStream activateSYSOHook(){
		PrintStream myStream = new PrintStream(System.out) {
		    @Override
		    public void println(String x) {
		        //super.println(System.currentTimeMillis() + ": " + x);
		    	super.println(x);
		    	servLog.setLog(x);
		    }
		};
		//System.setOut(myStream);
		return myStream;
	}

}
