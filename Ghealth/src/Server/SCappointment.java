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
		System.out.println(sp);
		querystr="SELECT c.cName,c.cLocation,u.uFirstName,u.uLastName"
				+ "FROM user u,clinic c,doctor d"
				+ "WHERE u.role='DOCTOR' AND d.dID=u.uID AND d,Speciality= '"+sp+"';";
		try 
		{
			stmt = mysqlConnection.conn.createStatement();
			result = stmt.executeQuery(querystr);
			while (result.next())
            {
				/* Get & Create the exist user from DB */
				us = new User();
				us.setuFirstName(result.getString("uFirstName"));
				us.setuLastName(result.getString("uLastName"));
				cl.setcLocation(result.getString("cLocation"));
				cl.setcName(result.getString("cName"));
				us.setuClinic(cl);
				en.addobjList(us);
				en.setType(task.GET_DOCTORS_IN_CLINIC_BY_TYPE);
			}
			
			//System.out.println("ResultSet - uID - "+result.getString("uID") );
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
