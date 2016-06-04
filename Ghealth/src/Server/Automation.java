package Server;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import enums.DoctorSpeciallity;
import enums.Status;
import models.AppointmentSettings;
import models.Clinic;
import models.Doctor;
import models.Patient;

public class Automation extends Thread{
	private Timer timer = new Timer();
	public static List<Notification> notLst = new ArrayList<Notification>();
	public static ResultSet result = null;
	public Statement stmt; 
	String querystr;
	AppointmentSettings as;
	Doctor doctor;
	Calendar c = new GregorianCalendar();
	Calendar cal = new GregorianCalendar();
	
	public void run(){
		timer.schedule(new PeriodicReport(),0);
		timer.schedule(new PeriodicNotification(),0);
	}
	
	
	public class PeriodicReport extends TimerTask{
		public void run(){
			
			/* TODO Creating weekly report */
			
			timer.schedule(new PeriodicReport(), 7 * 24 * 60 * 60 * 1000); // every week
		}
	}

	public class PeriodicNotification extends TimerTask{
		public void run(){
			/* TODO Checks for changed status appointment notifications */
			querystr = "SELECT  apsID,apsPtID,apsDate,apsTime,apsCreateDate,apsCreateTime,apsStatus,apsDocID,uFirstName,uLastName,cID,cName,cLocation,dSpeciality,ptEmail "
					+ "FROM appointmentsettings,user,clinic,doctor,patient "
					+ "WHERE apsPtID=patient.ptID AND apsStatus='SCHEDUELD' AND uID=apsDocID AND cID=ucID AND dID=uID";//SELECT mother fucker appointments";

			getSql(querystr);
			
			/*  -------------parsing---------------  */
			try {
				while (result.next())
				{
					Status st =  Status.valueOf(result.getString(7));
					as = new AppointmentSettings(result.getInt(1),result.getString(2),result.getString(3),result.getString(4),
							result.getString(5),result.getString(6),st,result.getString("apsDocID"));
					Patient pt= new Patient();
					pt.setPtEmail(result.getString("ptEmail"));
					Clinic clinic = new Clinic(result.getInt("cID"),result.getString("cName"),result.getString("cLocation"));
					DoctorSpeciallity ds = DoctorSpeciallity.valueOf(result.getString("dSpeciality"));
					doctor = new Doctor(result.getString("apsDocID"),result.getString("uFirstName"),result.getString("uLastName"),clinic,ds);
					as.setDoctor(doctor);
					/* prep notification obj */
					Notification nt = new Notification();
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
					Date dt = formatter.parse(as.getApsDate());
					nt.date = dt;
					//nt.date=as.getApsDate();
					nt.docName="Dr. " + doctor.getuLastName() + " " + doctor.getuFirstName();
					nt.location=clinic.getcLocation();
					nt.mail=pt.getPtEmail();
					System.out.println(nt.date + " "+ nt.docName+" "+nt.mail+ " "+nt.location+ " ");
					/* checking if this is todays notification */
					Date today = c.getTime();
					cal.setTime(dt);
					int diffrence = cal.get(Calendar.DAY_OF_MONTH)-c.get(Calendar.DAY_OF_MONTH);
					System.out.println("diff: "+diffrence);
					if(diffrence <= 1 && diffrence >=0)
						sendMail(nt);
				}
			} catch (SQLException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/* -------------end of parsing--------------- */ 

			timer.schedule(new PeriodicNotification(), 5 * 10000000); // Suppose to be every hour but now its every 15 seconds for us to notice in testing
		}
		
		private void sendMail(Notification nt) {
			// TODO Auto-generated method stub
			
			
		}

		public void getSql(String query){
		//	Statement stmt;
			try {
				stmt = mysqlConnection.autoConn.createStatement();
				result = stmt.executeQuery(querystr);
			//	mysqlConnection.conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		public boolean searchUserSession(String mail){
	    	for(Notification notf: notLst) {
	    	    if(notf.mail.equals(mail))
	    	       return true;
	    	}
	    	return false;
	    }
	}
	
	
	
	
}
