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

	public static Envelope GetClinicDoctorList(String pt,String sp)
	{
		ResultSet result = null;
		Statement stmt; 
		String querystr_a,querystr_b,querystr_c;
		User us = null;
		Envelope en = new Envelope();
		
		/* Return patient row if exist */
		System.out.println(sp);
		
		
		
		querystr_a="CREATE OR REPLACE VIEW AMIR AS"
				+ " SELECT * "
				+ " FROM appointmentsettings a "
				+ " WHERE a.apsPtID='"+pt+"' AND a.apsStatus='ARRIVED';";
		
		querystr_b="SELECT COUNT(*) AS COUNT"
				+ " FROM appointmentsettings a,doctor d"
				+ " WHERE a.apsStatus='SCHEDUELD' AND d.dSpeciality='"+sp+"' AND d.dID=a.apsDocID;";
		
		querystr_c="SELECT DISTINCT uID,uFirstName,uLastName,cLocation,cName "
				+ " FROM user,clinic,doctor LEFT JOIN AMIR on AMIR.apsDocID = doctor.dID "
				+ " WHERE dSpeciality='"+sp+"' AND uID = dID AND cID = ucID"
				+ " ORDER BY apsDate DESC; ";
		
		
		
		System.out.println(querystr_a);
		System.out.println(querystr_b);
		System.out.println(querystr_c);
		try 
		{
			stmt = mysqlConnection.conn.createStatement();
			stmt.executeUpdate(querystr_a);
			System.out.println("after first query");
			result = stmt.executeQuery(querystr_b);
			System.out.println("after second query");
			result.next();
			if(result.getInt("COUNT") > 0)
			{
				System.out.println("There is SCHEDUELD appointment to "+sp+" Cant ceate more appointment to same doctor type!");
				en.setStatus(Status.SCHEDUELD);
			}
			else {
				result = stmt.executeQuery(querystr_c);
				System.out.println("after third query");
				while (result.next())
	            {
					/* Get & Create the exist user from DB */
					us = new User();
					Clinic cl = new Clinic();
					us.setuID(result.getString("uID"));
					us.setuFirstName(result.getString("uFirstName"));
					us.setuLastName(result.getString("uLastName"));
					cl.setcLocation(result.getString("cLocation"));
					cl.setcName(result.getString("cName"));
					us.setuClinic(cl);
					System.out.println(us);
					en.addobjList(us);
					
				}
				
				en.setStatus(Status.ARRIVED);
			}
			en.setType(task.GET_DOCTORS_IN_CLINIC_BY_TYPE);
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
	
	
	public static Envelope GetAvailibleDoctorHours(String date,String uID)
	{
		ResultSet result = null;
		Statement stmt; 
		String querystr;
		Envelope en = new Envelope();
		
		String []hours = new String[]{"8:00:00","8:30:00",
						"9:00:00","9:30:00",
						"10:00:00","10:30:00",
						"11:00:00","11:30:00",
						"12:00:00","12:30:00",
						"13:00:00","13:30:00",
						"14:00:00","14:30:00",
						"15:00:00","15:30:00",
						"16:00:00","16:30:00"};
		
		
		List<String> hoursList =  new ArrayList<String>();
	    Collections.addAll(hoursList, hours); 
	    
		querystr="SELECT apsTime FROM appointmentsettings"
				+ " WHERE apsDocID = '"+uID+"' AND apsDate='"+date+"';";
	
		
		System.out.println(querystr);
		try 
		{
			stmt = mysqlConnection.conn.createStatement();
			result = stmt.executeQuery(querystr);
			while (result.next())
            {
				System.out.println(result.getString("apsTime"));
				String hourRes = result.getString("apsTime");
				hoursList.remove(result.getString("apsTime"));

			}
			
			List<Object> timeList = new ArrayList<Object>(hoursList);

			en.setobjList(timeList);
			en.setType(task.GET_AVAILIBLE_DOCTOR_HOURS);
			
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
	
	
	public static Status CreateAppointment(AppointmentSettings as)
	{
		Statement stmt;
		String querystr;
		
		querystr="INSERT INTO appointmentsettings " + " (apsPtID,apsDate,apsTime,apsCreateDate,apsCreateTime,apsStatus,apsDocID) "
				+ "VALUES ('"+as.getApsPtID()+"','"+as.getApsDate()+"','"+as.getApsTime()+"', '"
		+as.getCreateDate()+"', '"+as.getCreateTime()+"', '"+as.getApsStatus().toString()+"', '"+as.getApsDocID()+"')";
		
		try 
		{
			stmt = mysqlConnection.conn.createStatement();
			System.out.println("Create new appointment in DB: " + querystr);
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

		
	public static Status RecordAppointment(int apsID)
	{
		Statement stmt;
		
		String querystr;
		
		int result;
		
		querystr="UPDATE appointmentsettings "
				+ "SET apsStatus='ARRIVED' "
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