/**
 * TODO This is the class description
 */


package Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import enums.Status;
import models.*;


public class SCpatient {



	public static Envelope GetExistPatient(String ptID)
	{
		int rowCount=0;
		ResultSet result = null;
		Statement stmt;
		String querystr;
		Patient pt = null;
		Envelope en = new Envelope();
		/* Return patient row if exist */
		querystr="SELECT *,count(*) FROM patient WHERE ptID = '"+ptID+"';";
		try 
		{
			stmt = mysqlConnection.conn.createStatement();
			System.out.println(querystr+"\n(Check if patient: '"+ptID+"' is exist in DB:)");
			result = stmt.executeQuery(querystr);
			result.last();
			rowCount = result.getRow();
			System.out.println("rowcount="+rowCount);
			result.first();
			if(rowCount == 1)
			{
				/* Get & Create the patient from DB */
				pt = new Patient();
				pt.setpID(result.getString("ptID"));
				pt.setpFirstName(result.getString("ptFirstName"));
				pt.setpLastName(result.getString("ptLastName"));
				pt.setPtEmail(result.getString("ptEmail"));
				pt.setPtPhone(result.getString("ptPhone"));
				pt.setPtPrivateClinic(result.getString("ptPrivateClinic"));
				int ptdid = result.getInt("ptDoctorID");
				pt.setptpersonalDoctorID(ptdid);
				
				
				en.addobjList(pt);
				//en.setObj(pt);
			}
			
			System.out.println("ResultSet - ptID - "+result.getString("ptID") );
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
	
	
	public static Status CreatePatient(String ptID,String pFName,String pLName,String pEmail,String pPhone,String pPrivateClinic,int personalDoctorID)
	{
		Statement stmt;
		String querystr;
		Patient pt = null;
		Envelope en = new Envelope();
		
		querystr="INSERT INTO patient " + " VALUES ('"+ptID+"','"+pFName+"','"+pLName+"', '"+pEmail+"', '"+pPhone+"', '"+pPrivateClinic+"', '"+personalDoctorID+"')";
		
		try 
		{
			
			en.addobjList(GetExistPatient(ptID));
			//en.setObj(GetExistPatient(ptID)); //Check if patient exist in DB.
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
