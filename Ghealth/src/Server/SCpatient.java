/**
 * TODO This is the class description
 */


package Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import enums.Status;
import models.*;


public class SCpatient {



	public static Envelope GetExistPatient(String ptID)
	{
		int rowCount=0;
		ResultSet result = null;
		Statement stmt;
		String querystr;
		Patient pt = new Patient();
		Envelope en = new Envelope();
		
		/* Return patient row if exist */
		querystr="SELECT * FROM patient WHERE ptID = '"+ptID+"';";
		
		try 
		{
			stmt = mysqlConnection.conn.createStatement();
			System.out.println(querystr+"\n(Check if patient: '"+ptID+"' is exist in DB:)");
			result = stmt.executeQuery(querystr);
			result.last();
			rowCount = result.getRow();
			System.out.println("rowcount="+rowCount );
			result.first();
			
			if(rowCount == 0)
			{
				en.setStatus(Status.NOT_EXIST);
				//en.addobjList(pt);
				System.out.println("Patient Not Exist in DB");
				mysqlConnection.conn.close();
			}
			else
			{
				en.setStatus(Status.EXIST);
				System.out.println("Patient Exist in DB!");
				/* Get & Create the patient from DB */
				
				pt.setpID(result.getString("ptID"));
				pt.setpFirstName(result.getString("ptFirstName"));
				pt.setpLastName(result.getString("ptLastName"));
				pt.setPtEmail(result.getString("ptEmail"));
				pt.setPtPhone(result.getString("ptPhone"));
				pt.setPtPrivateClinic(result.getString("ptPrivateClinic"));
				String ptdid = result.getString("ptDoctorID");
				pt.setptpersonalDoctorID(ptdid);
				
				
				en.addobjList(pt);
				//en.setObj(pt);
				System.out.println("ResultSet - ptID - "+result.getString("ptID") );
				mysqlConnection.conn.close();
			}
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
	
	
	public static Status CreatePatient(String ptID,String pFName,String pLName,String pEmail,String pPhone,String pPrivateClinic,String personalDoctorID)
	{
		Statement stmt;
		String querystr;
		Patient pt = null;
		Envelope en = new Envelope();
		
		querystr="INSERT INTO patient " + " VALUES ('"+ptID+"','"+pFName+"','"+pLName+"', '"+pEmail+"', '"+pPhone+"', '"+pPrivateClinic+"', '"+personalDoctorID+"')";
		//System.out.println(querystr);
		
		try 
		{
			
			//en.addobjList(GetExistPatient(ptID));
			//en.setObj(GetExistPatient(ptID)); //Check if patient exist in DB.
			/*if(en.getSingleObject() != null)
			{
				System.out.println("The Patient '"+pt+"' exist in DB");
				return Status.EXIST;
			}*/
			
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
	
	public static String[] GetClinicList()
	{
		Statement stmt;
		String querystr;
		Patient pt = null;
		ResultSet result = null;
		String [] contactListNames = null;
		
		querystr="SELECT PrivateClinicName "
				+ "FROM privateclniic";
		//System.out.println(querystr);
		
		try 
		{
			
			List rowValues = new ArrayList();
			stmt = mysqlConnection.conn.createStatement();
			System.out.println("Create new patient in DB: " + querystr);
			result = stmt.executeQuery(querystr);
			while (result.next())
            {
				rowValues.add(result.getString(1));
            }   
			contactListNames = (String[]) rowValues.toArray(new String[rowValues.size()]);
			
			mysqlConnection.conn.close();
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          System.out.println("SQLException: " + ex.getMessage());
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
         
        }
		return contactListNames;
		

	}
	
}
