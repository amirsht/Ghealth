package Server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Patient;



public class mysqlConnection {
	
	public Connection conn;

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
	
	
	public Patient GetExistPatient(String pID)
	{
		ResultSet result = null;
		Statement stmt;
		String querystr;
		Patient pt = null;
		/* Return patient row if exist */
		querystr="SELECT *,count(*) FROM patient WHERE pID = '"+pID+"';";
		try 
		{
			stmt = this.conn.createStatement();
			System.out.println(querystr+"\n(Check if patient: '"+pID+"' is exist in DB:)");
			result = stmt.executeQuery(querystr);
			result.next();
			if(result.getInt(6) == 1)
			{
				pt = new Patient();
				pt.setpID(result.getString("pID"));
				pt.setpName(result.getString("pName"));
				pt.setPtEmail(result.getString("ptEmail"));
				pt.setPtPhone(result.getString("ptPhone"));
				pt.setPtPrivateClinic(result.getString("ptPrivateClinic"));
			}
			System.out.println("ResultSet - pID - "+result.getString("pID") );
			conn.close();
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          System.out.println("SQLException: " + ex.getMessage());
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
          return null;
        }
		
		return pt;
	}
	
	public int CreatePatient(String pID,String pName,String pEmail,String pPhone,String pPrivateClinic)
	{
		Statement stmt;
		String querystr;
		ResultSet result = null;
		Patient pt = null;
		
		
		querystr="INSERT INTO patient " + " VALUES ('"+pID+"','"+pName+"', '"+pEmail+"', '"+pPhone+"', '"+pPrivateClinic+"')";
		
		try 
		{

			pt = GetExistPatient(pID); //Check if patient exist in DB.
			if(pt != null)
			{
				System.out.println("The Patient '"+pt+"' exist in DB");
				return 10;
			}
			
			stmt = this.conn.createStatement();
			System.out.println("Create new patient in DB: " + querystr);
			stmt.executeUpdate(querystr);
			conn.close();
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          System.out.println("SQLException: " + ex.getMessage());
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
          return 0;
        }
		
		return 1;

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


