
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


public class SCmonthlyReports {

	
	
	private static SCmonthlyReports instance = null;
	private List<Object> ReportToEnv;
	List<String[]> clinicMonthlyReport;
	

	
	
	private SCmonthlyReports(){	
		// Exists only to defeat instantiation.
		System.out.println("in MonthlyReports constructor");
	}
	
	
	 public static SCmonthlyReports getInstance() {
	      if(instance == null) {
	         instance = new SCmonthlyReports();
	      }
	      return instance;
	   }
	
	 

	
	
	private void createReport(int clinicID){
		
		clinicMonthlyReport = new ArrayList<String[]>();
		this.ReportToEnv =  new ArrayList<Object>();
		
		ResultSet result = null;
		Statement stmt;
		

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar today = Calendar.getInstance();
		
		today.add(Calendar.DATE, 20);
		String to_date_str = formatter.format(today.getTime());
		
		today.add(Calendar.DATE, -30);
		String from_date_str = formatter.format(today.getTime());

		String query1 = ""
				+ "Create or replace view Tablea as "
				+ "(SELECT monthname(A.apsdate) AS MonthN, "
				+ "		week(A.apsdate) AS weekNum, "
				+ "		AVG(DATEDIFF(A.apsDate,A.apsCreateDate)) as AvgProcessTime, "
				+ "		AVG(timediff(A.apsStartTime, A.apsTime)/60) as AvgWaitingTime, "
				+ "       Count(DISTINCT A.apsptid) AS NumOfPatients "
				+ "FROM   appointmentsettings A "
				+ "WHERE  A.apsstatus = 'ARRIVED' AND "
				+ "       A.apsdate >= '" + from_date_str
				+ "' AND "
				+ "       A.apsdate <= '" + to_date_str
				+ "' AND "
				+ "       A.apsDocID in (SELECT doc.uID FROM user doc where doc.ucID=" + clinicID
				+ ") "
				+ "GROUP BY weekNum);";


				String query2 = ""
				+ "Create or replace view Tableb as "
				+ "(SELECT monthname(ptleavedate) AS Monthb, "
				+ "	   week(ptleavedate)  AS weekNumb, "
				+ "	   0 as AvgProcessTimeb, "
				+ "	   0 as AvgWaitingTimeb, "
				+ "	   0 as NumOfPatientsb , "
				+ "       Count(p.ptleavedate) AS LeaveClients "
				+ "FROM   patient P "
				+ "WHERE  ptleavedate != 'null'  AND "
				+ "       ptleavedate >= '" + from_date_str
				+ "' AND "
				+ "       ptleavedate <= '" + to_date_str 
				+ "' "
				+ "GROUP BY weekNumb);";


				String query3 = ""
				+ "Create or replace view Tablec as "
				+ "( "
				+ "SELECT monthname(A.apsdate) as monthc, "
				+ "	    week(A.apsdate) AS weekNumc, "
				+ "       Count(A.apsStatus) AS NoShow "
				+ "FROM   appointmentsettings A "
				+ "WHERE  A.apsstatus = 'NOSHOW'  AND "
				+ "       A.apsdate >= '" + from_date_str
				+ "' AND "
				+ "       A.apsdate <= '" + to_date_str
				+ "' AND "
				+ "       A.apsDocID in (SELECT doc.uID FROM user doc where doc.ucID=" + clinicID
				+ ") "
				+ "GROUP BY weekNumc "
				+ ");";


				String query4 = ""
				+ "create or replace view tabled as "
				+ "select MonthN,weekNum,AvgProcessTime,AvgWaitingTime,NumOfPatients,NoShow from "
				+ " "
				+ "	SELECT * FROM tablea "
				+ "		LEFT JOIN tablec ON tablea.weekNum = tablec.weeknumc "
				+ "		GROUP by weeknum "
				+ "	UNION "
				+ "	SELECT * FROM tablea "
				+ "		RIGHT JOIN tablec ON tablea.weekNum = tablec.weeknumc "
				+ "		Group by weeknum  "
				+ "	 "
				+ ";";


				String query5 = ""
				+ "create or replace view tablee as "
				+ "SELECT MonthN,weekNum, "
				+ "ifnull(AvgProcessTime,0) as AvgProcessTime, "
				+ "ifnull(AvgWaitingTime,0) as AvgWaitingTime, "
				+ "ifnull(NumOfPatients,0) as NumOfPatients, "
				+ "ifnull(LeaveClients,0) as LeaveClients, "
				+ "ifnull(NoShow,0) as NoShow "
				+ "FROM "
				+ " "
				+ "	SELECT * FROM tabled "
				+ "	LEFT JOIN tableb ON tabled.weekNum = tableb.weeknumb "
				+ "	UNION "
				+ "	SELECT * FROM tabled "
				+ "	RIGHT JOIN tableb ON tabled.weekNum = tableb.weeknumb "
				+ ";";


				String query6 = ""
				+ "select * from tablee GROUP BY weekNum "
				+ "UNION "
				+ "SELECT 'Min' as Op, "
				+ "'' as MonthN, "
				+ "MIN(AvgProcessTime) as AvgProcessTime, "
				+ "MIN(AvgWaitingTime) as AvgWaitingTime, "
				+ "Min(NumOfPatients) as NumOfPatients, "
				+ "Min(LeaveClients) as LeaveClients, "
				+ "Min(NoShow) as NoShow "
				+ " FROM tablee "
				+ "UNION "
				+ "SELECT  'Max' as Op, "
				+ "'' as MonthN, "
				+ "Max(AvgProcessTime),Max(AvgWaitingTime),Max(NumOfPatients), "
				+ "Max(LeaveClients) as LeaveClients, "
				+ "Max(NoShow) as NoShow "
				+ " FROM tablee "
				+ " UNION "
				+ " SELECT  'Avg' as Op, "
				+ "'' as MonthN, "
				+ " AVG(AvgProcessTime),AVG(AvgWaitingTime),AVG(NumOfPatients), "
				+ " AVG(LeaveClients) as LeaveClients, "
				+ " AVG(NoShow) as NoShow "
				+ " FROM tablee "
				+ " UNION "
				+ " SELECT  'SD' as Op, "
				+ "'' as MonthN, "
				+ " STDDEV(AvgProcessTime),STDDEV(AvgWaitingTime),STDDEV(NumOfPatients), "
				+ " STDDEV(LeaveClients) as LeaveClients, "
				+ " STDDEV(NoShow) as NoShow "
				+ " FROM tablee";
	
		
		
		try {
			mysqlConnection ms = new mysqlConnection();
			stmt = ms.conn.createStatement();
			
			stmt.executeUpdate(query1);
			stmt.executeUpdate(query2);
			stmt.executeUpdate(query3);
			stmt.executeUpdate(query4);
			stmt.executeUpdate(query5);
			result = stmt.executeQuery(query6);
			
			
			/*-- for each day of the past week -- */
			while(result.next())
			{
				Calendar tempDay = Calendar.getInstance();
				tempDay.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(result.getString(2)));
				int weekNumOfMonth = tempDay.get(Calendar.WEEK_OF_MONTH);
				
				/* --get from DB the 4 fields : apsDate, NumOfPatients, AvgProcessTime, AvgWaitingTime ---*/
				clinicMonthlyReport.add(new String[]{result.getString(1), String.valueOf(weekNumOfMonth), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7) });	
				this.ReportToEnv.add(new String[]{result.getString(1), String.valueOf(weekNumOfMonth), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7) });	
				
			}
			
			
		//	allReports.add(monthlyData);
	
			
			ms.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}//end of createReport function 
	
	
	
	public Envelope getClinicMonthlyReport(int cID){
	    
		Envelope en = new Envelope();    
	//	List<Object> list = new ArrayList<Object>();
	           
        this.createReport(cID);
        
		try {
			
			//this.ReportToEnv.add(this.clinicMonthlyReport);
			//list =  (List<Object>) this.ReportToEnv.get(0);
			
			en.setobjList(this.ReportToEnv);
			en.setStatus(Status.EXIST);		
			en.setType(task.GET_CLINIC_MONTHLY_REPORT);
		
			
		} catch (ArrayIndexOutOfBoundsException e) {
			
			e.printStackTrace();
		    en.setStatus(Status.FAILED_EXCEPTION);
		}
		
		return en;
             
	}//end of getClinicMonthlyReport
	
	
	
}



