package models;

import java.io.Serializable;

import enums.Status;

public class LabSettings implements Serializable{
	
	private int labID;
	private String labPtID;
	private String CreateDate;
	private String CreateTime;
	private Status labStatus;
	private String labDocID;
	private String labWorkerID;
	private String labDoctorReq;
	private String labWorkerSummery;
	private String labPhotoPath;
	private User labWorker;
	
	
	public LabSettings(int labID,String labPtID, String createDate, String createTime, Status labStatus, String labDocID,
			String labDoctorReq) {
		super();
		this.labID = labID;
		this.labPtID = labPtID;
		CreateDate = createDate;
		CreateTime = createTime;
		this.labStatus = labStatus;
		this.labDocID = labDocID;
		this.setLabDoctorReq(labDoctorReq);
	}
	
	public LabSettings(String labPtID, String createDate, String createTime, Status labStatus, String labDocID,
			String labDoctorReq) {
		super();
		this.labPtID = labPtID;
		CreateDate = createDate;
		CreateTime = createTime;
		this.labStatus = labStatus;
		this.labDocID = labDocID;
		this.setLabDoctorReq(labDoctorReq);
	}

	
	public int getLabID()
	{
		return labID;
	}
	
	public User getLabWorker() {
		return labWorker;
	}

	public void setLabWorker(User labWorker) {
		this.labWorker = labWorker;
	}
	
	
	public String toStringOpenLabs()
	{
		return "Lab ID:"+labID+" | Doctor Name: "+labWorker.getuFirstName()+" "+labWorker.getuLastName();
	}

	public String getLabDoctorReq() {
		return labDoctorReq;
	}

	public void setLabDoctorReq(String labDoctorReq) {
		this.labDoctorReq = labDoctorReq;
	}
	
}
