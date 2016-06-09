package Server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import enums.DoctorSpeciallity;
import enums.Status;
import enums.task;
import models.AppointmentSettings;
import models.Clinic;
import models.Doctor;
import models.Envelope;

public class WeeklyReports {

	private String [][] sevenDaysData = new String[7][4];


	public void WeeklyReports(){
		
	}
	
	
	
	//for automation 
	public void createAllWeeklyReports(){
	
		ResultSet result = null;
		Statement stmt;
		String querystr = "SELECT C.cID FROM clinic C";
		Integer cID;
		
		try {
			mysqlConnection ms = new mysqlConnection();
			stmt = ms.conn.createStatement();

			result = stmt.executeQuery(querystr);
			
			/*-- create weekly report for every clinic --*/
			while(result.next())
			{
				cID = result.getInt(1);
				createReport(cID.intValue());
				reportToFile("clinic_" + cID.toString());
			}
			ms.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
	} 
	
	
	private void createReport(Integer clinicID){
		
		ResultSet result = null;
		Statement stmt;
		
		
		
		String query_1 = ""
				+ "SELECT A.apsDate, count(distinct A.apsPtID) as NumOfPatients, AVG(DATEDIFF(A.apsDate,A.apsCreateDate)) as AvgProcessTime, "
				+ "AVG(timediff(A.apsStartTime, A.apsTime)/60) as AvgWaitingTime "
				+ " "
				+ "(SELECT count(*) FROM Patient Where ptLeaveDate =" ;
						
		String query_2 =" ) AS LeaveClients, " + "(SELECT count(*) FROM appointmentsettings Where apsDate = ";
		
		String query_3 = " AND apsStatus = 'NOSHOW') AS NoShow"
				+ "FROM Ghealth.appointmentsettings A "
				+ " "
				+ "WHERE apsDate = ";
		String query_4 =" AND A.apsDocID in (SELECT doc.uID FROM user doc WHERE doc.ucID =";
		String query_5 = ")";
		
		

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar today = Calendar.getInstance();
		
		

		
		try {
			mysqlConnection ms = new mysqlConnection();
			stmt = ms.conn.createStatement();
			
			/*-- for each day of the past week -- */
			for(int i=1; i<7;i++)
			{
				/* --get from DB the 4 fields : apsDate, NumOfPatients, AvgProcessTime, AvgWaitingTime ---*/
				today.add(Calendar.DATE, -i);
				String day_str = formatter.format(today.getTime());
				String query = query_1 + day_str + query_2 + day_str+ query_3 + day_str + query_4 +  clinicID.toString() + query_5  ;
				result = stmt.executeQuery(query);
				result.next();
				/*--Generate matrix with fields  --*/
				this.sevenDaysData[i-1][0] = day_str;
				this.sevenDaysData[i-1][1] = result.getString(2);
				this.sevenDaysData[i-1][2] = result.getString(3);
				this.sevenDaysData[i-1][3] = result.getString(4);
				
			}
			
			ms.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}//end of createReport function 
	
	
	

	/*-- insert week into file--*/
	private void reportToFile(String clinic_nameOfFile){
		
		
		try(  PrintWriter output = new PrintWriter( clinic_nameOfFile +".txt" )  ){
			
			for(int i =0; i<7;i++){
				
				System.out.println(this.sevenDaysData[i][0]);
				
				for(int j=0; j<4; j++ ){
					
					output.println(this.sevenDaysData[i][j]);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//end of reportToFile function
	
	
	public static Envelope getClinicWeeklyReport(Integer cID){
	    
		Envelope en = new Envelope();
        BufferedReader in =null;
        String str;
        int index =0; 
        List<String> list = new ArrayList<String>();
        
		try {
			
			in = new BufferedReader(new FileReader("clinic_" + cID.toString() +".txt"));
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		    en.setStatus(Status.FAILED_EXCEPTION);
		}
		
		
        try {
			while((str = in.readLine()) != null){
			    list.add(str);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
			   en.setStatus(Status.FAILED_EXCEPTION);
		}

		en.addobjList(list);
		en.setStatus(Status.EXIST);		
		en.setType(task.GET_CLINIC_WEEKLY_REPORT);
		
		
		return en;
             
	}//end of getClinicWeeklyReport
	
	
}



