package Server;
/**
 * main class that start the server
 *
 */
public class StartServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		serverLogGui servLog = new serverLogGui();
	    servLog.dispose();
	    ServerGui serv = new ServerGui();
	    mysqlConnection servCon = new mysqlConnection(serv,servLog);
	}

}
