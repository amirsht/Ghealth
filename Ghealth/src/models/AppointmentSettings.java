package models;

import java.sql.Time;
import java.util.Date;



//class for creating appt. by customer service 

public class AppointmentSettings {
	
	public enum apptStatus{
		
		SCHEDUELD, CANCELED, ARRIVED, NOSHOW
	}

	private int appID;
	private Date appDate;
	private Time appTime;
	private Date appcreatDate;
	private apptStatus AppStatus; //
	
	public AppointmentSettings(int appID, Date appDate, Time appTime, Date appcreatDate, apptStatus appStatus) 
	{
		super();
		this.appID = appID;
		this.appDate = appDate;
		this.appTime = appTime;
		this.appcreatDate = appcreatDate;
		this.AppStatus = appStatus;
	}
	

	public int getAppID() 
	{
		return appID;
	}	
	public void setAppID(int appID) 
	{
		this.appID = appID;
	}
	public Date getAppDate() 
	{
		return appDate;
	}
	public void setAppDate(Date appDate) 
	{
		this.appDate = appDate;
	}
	public Time getAppTime() 
	{
		return appTime;
	}
	public void setAppTime(Time appTime) 
	{
		this.appTime = appTime;
	}
	public Date getAppcreatDate() 
	{
		return appcreatDate;
	}
	public void setAppcreatDate(Date appcreatDate) 
	{
		this.appcreatDate = appcreatDate;
	}
	public apptStatus getAppStatus() 
	{
		return AppStatus;
	}
	public void setAppStatus(apptStatus appStatus) 
	{
		AppStatus = appStatus;
	}
	
	
	
}
