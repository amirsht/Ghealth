/**
 * TODO This is the class description
 */


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

public class SClab {

	
	
	public static Envelope Get_SCHEDUELD_labs(String ptID)
	{
		Statement stmt;
		String querystr;
		ResultSet result;
		Envelope en = new Envelope();
		LabSettings ls;
		User doctor;
		Clinic cl;
		
		querystr="SELECT * "
				+ "FROM labsettings,user,clinic  "
				+ "WHERE labDocID=uID AND labPtID='"+ptID+"' AND labStatus='SCHEDUELD' AND cID = ucID";
		
		try 
		{
			stmt = mysqlConnection.conn.createStatement();
			System.out.println("Create new appointment in DB: " + querystr);
			result = stmt.executeQuery(querystr);
			en.setStatus(Status.NOT_EXIST);
			while (result.next())
            {
				Status st =  Status.valueOf(result.getString("labStatus"));
				
				ls = new LabSettings(result.getInt("labID"),result.getString("labPtID"), result.getString("labCreateDate"), result.getString("labCreateTime"), st,
						result.getString("labDocID"), result.getString("labDocReq"));
				
				
				doctor = new User();
				doctor.setuID(result.getString("labDocID"));
				doctor.setuFirstName(result.getString("uFirstName"));
				doctor.setuLastName(result.getString("uLastName"));
				
				cl = new Clinic();
				cl.setcID(result.getInt("cID"));
				cl.setcLocation(result.getString("cLocation"));
				cl.setcName("cName");
				doctor.setuClinic(cl);
				ls.setLabWorker(doctor);
				
				en.addobjList(ls);
				System.out.println(ls.toStringOpenLabs());
				en.setStatus(Status.EXIST);
            }   
			
			en.setType(task.GET_SCHEDUELD_LAB);
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

	
	
	
	public static void UpdateLabRecord(int labID,String record)
	{
		Statement stmt;
		String querystr;
		int result;
		
		querystr="UPDATE labsettings "
				+ "SET labStatus='ARRIVED',labWorkerSummery='"+record+"' "
				+ "WHERE labID='"+labID+"'";
		
		try 
		{
			stmt = mysqlConnection.conn.createStatement();
			System.out.println("Update lab in DB: " + querystr);
			result = stmt.executeUpdate(querystr);
		
			mysqlConnection.conn.close();
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          System.out.println("SQLException: " + ex.getMessage());
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
          //return Status.FAILED_EXCEPTION;
        }
		

	}




	public static void UpdateLabFilePath(String filename,int labID) {
		Statement stmt;
		String querystr;
		int result;
		
		querystr="UPDATE labsettings "
				+ "SET labPhotoPath='"+filename+"' "
				+ "WHERE labID='"+labID+"'";
		
		try 
		{
			stmt = mysqlConnection.conn.createStatement();
			System.out.println("Update lab in DB: " + querystr);
			result = stmt.executeUpdate(querystr);
		
			mysqlConnection.conn.close();
		}
		catch (SQLException ex) 
   	    {/* handle any errors*/
          System.out.println("SQLException: " + ex.getMessage());
          System.out.println("SQLState: " + ex.getSQLState());
          System.out.println("VendorError: " + ex.getErrorCode());
          //return Status.FAILED_EXCEPTION;
        }
		
	}
	
}
