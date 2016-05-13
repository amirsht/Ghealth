package Server;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Envelop;
import models.Patient;



public class mysqlConnection {
	
	public static Connection conn; //TODO - will be changed (maby) to private

	public mysqlConnection() 
	{
		try 
		{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {/* handle the error*/}
        
        try 
        {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world","root","a4m3i2r1");
            //Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.3.68/test","root","Root");
            System.out.println("SQL connection succeed");

     	} catch (SQLException ex) 
     	    {/* handle any errors*/
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            }
   	}
	
	
	
	
	
	

	public String printPhysician()
	{
		
		Statement stmt;
		String tableStr = "Physician Table:\n";
		
		try 
		{
			stmt = this.conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Physician;");
	 		while(rs.next())
	 		{
				 // Print out the values
				 //System.out.println(rs.getString(1)+"\t\t" +rs.getString(2));
				 // save the values in tableStr
				 
				 tableStr += rs.getString(1)+"     "+rs.getString(2)+"\n";
			} 
			rs.close();
			
			return tableStr;
			
		} catch (SQLException e) {e.printStackTrace();return "Error getting DB";}
	}
	
	public boolean searchPhysician(String docname){
		
		Statement stmt;
		
		try 
		{
			stmt = this.conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Physician;");
	 		while(rs.next())
	 		{
				 if (String.valueOf(rs.getString(1)).equals(String.valueOf(docname))) {
					 rs.close();
					 return true;
				 }
					 
			} 
			rs.close();
			return false;
			
			
		} catch (SQLException e) {e.printStackTrace();return false;}		
		
	}
	

}


