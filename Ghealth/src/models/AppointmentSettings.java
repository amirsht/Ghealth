/**
 * TODO This is the class description
 */


package models;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

import enums.Status;



//class for creating appt. by customer service 

public class AppointmentSettings  implements Serializable {
	
	
	private int apsID;
	private String apsPtID;
	private String apsDate;
	private String apsTime;
	private String CreateDate;
	private String CreateTime;
	private Status apsStatus;
	private String apsDocID;
	
	
	
	public AppointmentSettings()
	{
		super();
	}
	
	
	public AppointmentSettings(String apsPtID, String apsDate, String apsTime, String createTimeDate, String createTime,
			Status apsStatus, String apsDocID) {
		super();
		this.apsPtID = apsPtID;
		this.apsDate = apsDate;
		this.apsTime = apsTime;
		CreateDate = createTimeDate;
		CreateTime = createTime;
		this.apsStatus = apsStatus;
		this.apsDocID = apsDocID;
	}
	
	public AppointmentSettings(int apsID, String apsPtID, String apsDate, String apsTime, String createTimeDate,
			String createTime, Status apsStatus, String apsDocID) {
		super();
		this.apsID = apsID;
		this.apsPtID = apsPtID;
		this.apsDate = apsDate;
		this.apsTime = apsTime;
		CreateDate = createTimeDate;
		CreateTime = createTime;
		this.apsStatus = apsStatus;
		this.apsDocID = apsDocID;
	}
	public void setApsID(int apsID) {
		this.apsID = apsID;
	}
	public void setApsPtID(String apsPtID) {
		this.apsPtID = apsPtID;
	}
	public void setApsDate(String apsDate) {
		this.apsDate = apsDate;
	}
	public void setApsTime(String apsTime) {
		this.apsTime = apsTime;
	}
	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}
	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}
	public void setApsStatus(Status apsStatus) {
		this.apsStatus = apsStatus;
	}
	public void setApsDocID(String apsDocID) {
		this.apsDocID = apsDocID;
	}

	public int getApsID() {
		return apsID;
	}

	public String getApsPtID() {
		return apsPtID;
	}

	public String getApsDate() {
		return apsDate;
	}

	public String getApsTime() {
		return apsTime;
	}

	public String getCreateDate() {
		return CreateDate;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public Status getApsStatus() {
		return apsStatus;
	}

	public String getApsDocID() {
		return apsDocID;
	}
	
	
	
	
}
