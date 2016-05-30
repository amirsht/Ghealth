/**
 * TODO This is the class description
 */


package Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import enums.Status;
import models.*;
import enums.*;

public class SCappointment {

	public static Envelope GetClinicDoctorList(String sp)
	{
		int rowCount=0;
		ResultSet result = null;
		Statement stmt;
		String querystr;
		User us = null;
		Envelope en = new Envelope();
		Clinic cl = new Clinic();
		/* Return patient row if exist */
		querystr="SELECT c.cName,c.cLocation,u.uFirstName,u.uLastName"
				+ "FROM user u,clinic c,doctor d"
				+ "WHERE u.role='DOCTOR' AND d.dID=u.uID AND d,Speciality= '"+sp+"';";
		try 
		{
			stmt = mysqlConnection.conn.createStatement();
			result = stmt.executeQuery(querystr);
			result.last();
			rowCount = result.getRow();
			System.out.println("rowcount="+rowCount);
			result.first();
			
			if(rowCount == 1)
			{
				/* Get & Create the exist user from DB */
				us = new User();
				us.setuID(result.getString("uID"));
				us.setuPassword(result.getString("uPassword"));
				us.setuFirstName(result.getString("uFirstName"));
				us.setuLastName(result.getString("uLastName"));
				us.setuEmail(result.getString("uEmail"));
				
				cl.setcID(result.getInt("ucID"));
				cl.setcLocation(result.getString("cLocation"));
				cl.setcName(result.getString("cName"));
				us.setuClinic(cl);
				
				String temp124=result.getString("role");
				us.setuRole(Roles.valueOf(temp124));
				
				en.addobjList(us);
				//en.setObj(pt);
			}
			
			System.out.println("ResultSet - uID - "+result.getString("uID") );
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
	
	

	
	
	
}
