package Server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import enums.Status;
import enums.task;
import models.Envelope;


public class SCweeklyReports {

	
	
	private static SCweeklyReports instance = null;
	private List<Object> allReports;

	
	
	private SCweeklyReports(){	
		// Exists only to defeat instantiation.
		System.out.println("in WeeklyReports constructor");
	}
	
	
	 public static SCweeklyReports getInstance() {
	      if(instance == null) {
	         instance = new SCweeklyReports();
	      }
	      return instance;
	   }
	
	 
	 
	//for automation 
	@SuppressWarnings("static-access")
	public void createAllClinicsWeeklyReports(){
	
		this.allReports = new ArrayList<Object>();
		ResultSet result = null;
		Statement stmt;
		String querystr = "SELECT C.cID FROM clinic C"; //all clinics 
		int cID;
		
		try {
			mysqlConnection ms = new mysqlConnection();
			stmt = ms.conn.createStatement();

			result = stmt.executeQuery(querystr);
			
			/*-- create weekly report for every clinic --*/
			while(result.next())
			{
				cID = result.getInt(1);
				createReport(cID);
				
			}
			ms.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		
	} //end of createAllWeeklyReports
	
	
	@SuppressWarnings("static-access")
	private void createReport(int clinicID){
		
		List<String[]> weeklyData = new ArrayList<String[]>();
		ResultSet result = null;
		Statement stmt;
		

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar today = Calendar.getInstance();
		
		String to_date_str = formatter.format(today.getTime());
		today.add(Calendar.DATE, -7);
		String from_date_str = formatter.format(today.getTime());


		String query = "CREATE OR REPLACE VIEW TABLEWEEK AS "
				+ "SELECT  A.apsDate, "
				+ "		AVG(DATEDIFF(A.apsDate,A.apsCreateDate)) as AvgProcessTime, "
				+ "		AVG(timediff(A.apsStartTime, A.apsTime)/60) as AvgWaitingTime, "
				+ "        Count(DISTINCT A.apsptid) AS NumOfPatients "
				+ "FROM   appointmentsettings A "
				+ "WHERE   A.apsstatus = 'ARRIVED' "
				+ "       AND A.apsDate <= '" + to_date_str 
				+ "' AND A.apsDate >= '" + from_date_str
				+ "' "
				+ "       AND A.apsDocID in (SELECT doc.uID FROM user doc WHERE doc.ucID='" + clinicID
				+ "') "
				+ "GROUP BY A.apsDate; ";
		
		String query2 = ""
				+ "SELECT * FROM TABLEWEEK "
				+ "UNION "
				+ "SELECT 'Min' as Op, "
				+ "MIN(AvgProcessTime) as AvgProcessTime,MIN(AvgWaitingTime) as AvgWaitingTime,Min(NumOfPatients) as NumOfPatients "
				+ " FROM TABLEWEEK "
				+ "UNION "
				+ "SELECT  'Max' as Op, "
				+ "Max(AvgProcessTime),Max(AvgWaitingTime),Max(NumOfPatients) "
				+ " FROM TABLEWEEK "
				+ " UNION "
				+ " SELECT  'Avg' as Op, "
				+ " AVG(AvgProcessTime),AVG(AvgWaitingTime),AVG(NumOfPatients) "
				+ " FROM TABLEWEEK "
				+ " UNION "
				+ " SELECT  'SD' as Op, "
				+ " STDDEV(AvgProcessTime),STDDEV(AvgWaitingTime),STDDEV(NumOfPatients) "
				+ " FROM TABLEWEEK";
		
		try {
			mysqlConnection ms = new mysqlConnection();
			stmt = ms.conn.createStatement();
			
			stmt.executeUpdate(query);
			result = stmt.executeQuery(query2);
			
			
			/*-- for each day of the past week -- */
			while(result.next())
			{
				/* --get from DB the 4 fields : apsDate, NumOfPatients, AvgProcessTime, AvgWaitingTime ---*/	
				weeklyData.add(new String[]{result.getString(1), result.getString(2), result.getString(3), result.getString(4)});	
			}
			
			
			allReports.add(weeklyData);
			ms.conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}//end of createReport function 
	
	
	
	@SuppressWarnings("unchecked")
	public Envelope getClinicWeeklyReport(int cID){
	    
		Envelope en = new Envelope();    
        List<Object> list = new ArrayList<Object>();
        
		try {	
			list = (List<Object>) this.allReports.get(cID-1);
			
		} catch (ArrayIndexOutOfBoundsException e) {
			
			e.printStackTrace();
		    en.setStatus(Status.FAILED_EXCEPTION);
		}
		
		en.setobjList(list);
		en.setStatus(Status.EXIST);		
		en.setType(task.GET_CLINIC_WEEKLY_REPORT);
		return en;
             
	}//end of getClinicWeeklyReport
	
	

	
}



