package models;

import java.sql.Time;
import java.util.Date;

public class AppointmentSettings {

	private int appID;
	private Date appDate;
	private Time appTime;
	private Date appcreatDate;
	private String AppStatus;
	
	public AppointmentSettings(int appID, Date appDate, Time appTime, Date appcreatDate, String appStatus) 
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
	public String getAppStatus() 
	{
		return AppStatus;
	}
	public void setAppStatus(String appStatus) 
	{
		AppStatus = appStatus;
	}
	
	
	
}
