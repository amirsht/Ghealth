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

import javax.mail.MessagingException;

import enums.DoctorSpeciallity;
import enums.Status;
import models.AppointmentSettings;
import models.Clinic;
import models.Doctor;
import models.Patient;

/**
 *	Controls the automated tasks
 */
public class Automation extends Thread{
	private Timer timer = new Timer();
	//public static Email mail= new Email();
	public static List<Notification> notLst = new ArrayList<Notification>();
	public static ResultSet result = null;
	public Statement stmt; 
	String querystr;
	AppointmentSettings as;
	Doctor doctor;
	Calendar c = new GregorianCalendar();
	Calendar cal = new GregorianCalendar();
	
	public void run(){
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			/** 
			 * here we can enter a starting date to schedule mailing at a certain hour
			 * if there isn't a future date it will start immediately and repeat with the given value
			 */
			//Date startDate = dateFormatter.parse("2016-06-04 19:14:30");
			//timer.schedule(new PeriodicNotification(),startDate, 60 * 1000);  //Every 24 hours at 8AM
			Date startDate = dateFormatter.parse("2016-06-04 19:14:30");
			timer.schedule(new PeriodicNotification(),startDate, 24 * 60 * 60 * 1000);  //Every 24 hours at 8AM
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			timer.schedule(new PeriodicReport(), 0);
	}
	
	//-------------------------------------------------------------------------------------
	/**
	 * Execute automatic periodical reports
	 */
	public class PeriodicReport extends TimerTask{
		public void run(){
			
			/* TODO Creating weekly report */
			SCweeklyReports rep = SCweeklyReports.getInstance();
			rep.createAllClinicsWeeklyReports();
			
			timer.schedule(new PeriodicReport(), 24 * 60 * 60 * 1000); // every day for the past week
		}
	}
	
	//-------------------------------------------------------------------------------------
	
	/**
	 * Execute automatic periodical notifications
	 */
	public class PeriodicNotification extends TimerTask{
		public void run(){
			/* TODO Checks for changed status appointment notifications */

			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar tommorow = Calendar.getInstance();
			tommorow.add(Calendar.DATE, 1);
			String tommorow_b = formatter.format(tommorow.getTime());

			//-------------------------------------------------------------------------------------
			
			querystr = "SELECT  apsID,apsPtID,apsDate,apsTime,apsCreateDate,apsCreateTime,apsStatus,apsDocID,uFirstName,uLastName,cID,cName,cLocation,dSpeciality,ptEmail,ptFirstName,ptLastName "
					+ "FROM appointmentsettings,user,clinic,doctor,patient "
					+ "WHERE apsPtID=patient.ptID AND apsStatus='SCHEDUELD' AND uID=apsDocID AND cID=ucID AND dID=uID AND apsDate='"+tommorow_b+"'";//SELECT mother fucker appointments";
			
			System.out.println(querystr);

			getSql(querystr);
			
			/*  -------------parsing---------------  */
			try {
				while (result.next())
				{
					/* -------------parsing tuple--------------- */ 
					System.out.println("PASS THROUGH TUPLE");
					Status st =  Status.valueOf(result.getString(7));
					as = new AppointmentSettings(result.getInt(1),result.getString(2),result.getString(3),result.getString(4),
					result.getString(5),result.getString(6),st,result.getString("apsDocID"));
					Patient pt= new Patient();
					pt.setPtEmail(result.getString("ptEmail"));
					Clinic clinic = new Clinic(result.getInt("cID"),result.getString("cName"),result.getString("cLocation"));
					DoctorSpeciallity ds = DoctorSpeciallity.valueOf(result.getString("dSpeciality"));
					doctor = new Doctor(result.getString("apsDocID"),result.getString("uFirstName"),result.getString("uLastName"),clinic,ds);
					as.setDoctor(doctor);
					/* -------------end of parsing--------------- */ 
					
					/* Preparing notification object */
					Notification nt = new Notification();
					Date dt = formatter.parse(as.getApsDate());
					nt.date = dt;
					nt.ptName = result.getString("ptFirstName") +" "+ result.getString("ptLastName");
					nt.sdate = as.getApsDate();
					nt.time = as.getApsTime();
					nt.docName="Dr. " + doctor.getuLastName() + " " + doctor.getuFirstName();
					nt.location=clinic.getcLocation();
					nt.mail=pt.getPtEmail();
					System.out.println(nt.date + " "+ nt.docName+" "+nt.mail+ " "+nt.location+ " ");
					/* Preparing notification object - end */
					
					//-------------------------------------------------------------------------------------
					/* checking if this is todays notification */
					/*
					//c.getTime();
					cal.setTime(dt);
					int diffrence = cal.get(Calendar.DAY_OF_MONTH)-c.get(Calendar.DAY_OF_MONTH);
					System.out.println("diff: "+diffrence);
					if(diffrence <= 1 && diffrence >=0)
					*/
					//-------------------------------------------------------------------------------------
					
					/** Sending mail **/
					sendMail(nt);
					/** Sending mail **/
				}
			} catch (SQLException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//-------------------------------------------------------------------------------------
			/*  trash that we'll maybe need later
			Date startDate;
			try {
				startDate = dateFormatter.parse("2016-06-04 19:14:30");
			//	timer.schedule(new PeriodicNotification(), startDate); // Every 24 hours at 8AM
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/
			//-------------------------------------------------------------------------------------
		}
		/** 
		 * Sending mail function 
		 * @param nt
		 */
		private void sendMail(Notification nt) {
			// TODO Auto-generated method stub
			try {
				Email.generateAndSendEmail(nt);
				System.out.println("\n\n ===> The System has just sent an Email successfully. Check your email..");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//-------------------------------------------------------------------------------------
		
		/**
		 * Getting SQL query results back from DB
		 * @param query
		 */
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
		
		//-------------------------------------------------------------------------------------
		
		/**
		 * Searching if user in session
		 * @param mail
		 * @return
		 */
		public boolean searchUserSession(String mail){
	    	for(Notification notf: notLst) {
	    	    if(notf.mail.equals(mail))
	    	       return true;
	    	}
	    	return false;
	    }
	}
	
	
	
	
}
