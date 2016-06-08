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
	private String filePath;
	private String fileExt;
	
	
	public LabSettings()
	{
		super();
		
	}
	
	public LabSettings(int labID,String labPtID, String createDate, String createTime, Status labStatus, String labDocID,
			String labDoctorReq) {
		super();
		this.labID = labID;
		this.setLabPtID(labPtID);
		CreateDate = createDate;
		setCreateTime(createTime);
		this.setLabStatus(labStatus);
		this.setLabDocID(labDocID);
		this.setLabDoctorReq(labDoctorReq);
	}
	
	public LabSettings(String labPtID, String createDate, String createTime, Status labStatus, String labDocID,
			String labDoctorReq) {
		super();
		this.setLabPtID(labPtID);
		CreateDate = createDate;
		setCreateTime(createTime);
		this.setLabStatus(labStatus);
		this.setLabDocID(labDocID);
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

	public String getLabWorkerSummery() {
		return labWorkerSummery;
	}

	public void setLabWorkerSummery(String labWorkerSummery) {
		this.labWorkerSummery = labWorkerSummery;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	
	public String tostringShowLabs() {
		return "Date= "+CreateDate+",Laborant "+labWorker.getuFirstName()+" "+labWorker.getuLastName()
		+",Clinic="+labWorker.getuClinic().getcName()+"("+labWorker.getuClinic().getcLocation()+")";
	}

	public String getLabWorkerID() {
		return labWorkerID;
	}

	public void setLabWorkerID(String labWorkerID) {
		this.labWorkerID = labWorkerID;
	}

	public Status getLabStatus() {
		return labStatus;
	}

	public void setLabStatus(Status labStatus) {
		this.labStatus = labStatus;
	}

	public String getLabPtID() {
		return labPtID;
	}

	public void setLabPtID(String labPtID) {
		this.labPtID = labPtID;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getLabPhotoPath() {
		return labPhotoPath;
	}

	public void setLabPhotoPath(String labPhotoPath) {
		this.labPhotoPath = labPhotoPath;
	}

	public String getLabDocID() {
		return labDocID;
	}

	public void setLabDocID(String labDocID) {
		this.labDocID = labDocID;
	}
	
	
}
