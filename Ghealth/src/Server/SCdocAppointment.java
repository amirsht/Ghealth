package Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import models.*;
import enums.*;

public class SCdocAppointment {
		
	public static Envelope GetCurrentAppointment(String ptID, String apsDocID)
	{
		Statement stmt;
		String querystr;
		ResultSet result;
		Envelope en = new Envelope();
	
		Doctor doctor;
		
		querystr="SELECT apsID"
				+ " FROM appointmentsettings"
				+ " WHERE apsPtID='"+ptID+"' AND apsStatus='SCHEDUELD' AND apsDocID='"+apsDocID+"'";
		
		try 
		{
			stmt = mysqlConnection.conn.createStatement();
			//System.out.println("Create new appointment in DB: " + querystr);
			result = stmt.executeQuery(querystr);
			en.setStatus(Status.NOT_EXIST);
			
			while (result.next())
            {
				int appointementID = result.getInt(1);
				
				en.addobjList(appointementID);
				System.out.println("the appointement id received from DB was:" + appointementID);
				en.setStatus(Status.EXIST);
            }   
			
			en.setType(task.GET_CURRENT_APPOINTMENT_ID);
			mysqlConnection.conn.close();
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          System.out.println("SQLException: " + ex.getMessage());
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
          en.setStatus(Status.FAILED_EXCEPTION);
          return en;
        }
		
		return en;

	}

		
	public static Status RecordAppointment(String apsID, String summery)
	{
		Statement stmt;
		
		String querystr;
		
		int result;
		
		querystr="UPDATE appointmentsettings "
				+ "SET apsStatus='ARRIVED', apsSummery ='"+summery+"'"
				+ "WHERE apsID='"+apsID+"'";
		
		try 
		{
			stmt = mysqlConnection.conn.createStatement();
			System.out.println("UPDATE Patient Arrived to appointment in DB: " + querystr);
			result = stmt.executeUpdate(querystr);
		
			mysqlConnection.conn.close();
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          System.out.println("SQLException: " + ex.getMessage());
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
          return Status.FAILED_EXCEPTION;
        }
		
		return Status.ARRIVED;

	}
	
}
