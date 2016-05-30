package Server;

import java.io.PrintStream;

/**
 * main class that start the server
 *
 */
public class StartServer {

	public static serverLogGui servLog = new serverLogGui();
    public static PrintStream ps = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	    servLog.dispose();
	    ServerGui serv = new ServerGui();
	    mysqlConnection servCon = new mysqlConnection(serv,servLog);
   		//ps = StartServer.activateSYSOHook();
		//System.setOut(ps);
	    ps = activateSYSOHook();
	    System.setOut(ps);
		
	}
	
	public static PrintStream activateSYSOHook(){
		PrintStream myStream = new PrintStream(System.out) {
		    @Override
		    public void println(String x) {
		        //super.println(System.currentTimeMillis() + ": " + x);
		    	servLog.setLog(x);
		    }
		};
		//System.setOut(myStream);
		return myStream;
	}

}
