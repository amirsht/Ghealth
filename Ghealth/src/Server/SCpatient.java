package Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import enums.Status;
import models.Envelop;
import models.Patient;


public class SCpatient {



	public static Envelop GetExistPatient(String pID)
	{
		ResultSet result = null;
		Statement stmt;
		String querystr;
		Patient pt = null;
		Envelop en = new Envelop();
		/* Return patient row if exist */
		querystr="SELECT *,count(*) FROM patient WHERE pID = '"+pID+"';";
		try 
		{
			stmt = mysqlConnection.conn.createStatement();
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
				en.addobjList(pt);
				//en.setObj(pt);
			}
			
			System.out.println("ResultSet - pID - "+result.getString("pID") );
			mysqlConnection.conn.close();
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          System.out.println("SQLException: " + ex.getMessage());
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
          return null;
        }
		
		return en;
	}
	
	
	public static Status CreatePatient(String pID,String pName,String pEmail,String pPhone,String pPrivateClinic)
	{
		Statement stmt;
		String querystr;
		Patient pt = null;
		Envelop en = new Envelop();
		
		querystr="INSERT INTO patient " + " VALUES ('"+pID+"','"+pName+"', '"+pEmail+"', '"+pPhone+"', '"+pPrivateClinic+"')";
		
		try 
		{
			
			en.addobjList(GetExistPatient(pID));
			//en.setObj(GetExistPatient(pID)); //Check if patient exist in DB.
			if(en.getSingleObject() != null)
			{
				System.out.println("The Patient '"+pt+"' exist in DB");
				return Status.EXIST;
			}
			
			stmt = mysqlConnection.conn.createStatement();
			System.out.println("Create new patient in DB: " + querystr);
			stmt.executeUpdate(querystr);
			mysqlConnection.conn.close();
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          System.out.println("SQLException: " + ex.getMessage());
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
          return Status.FAILED_EXCEPTION;
        }
		
		return Status.CREATED;

	}
	
}
