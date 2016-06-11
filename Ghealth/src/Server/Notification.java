package Server;

import java.io.Serializable;
import java.util.Date;

import models.Patient;


/**
 * Contains notification details
 */
public class Notification implements Serializable {
	public Date date = null;
	public String sdate = null;
	public String time = null;
	public String mail = null;
	public String location = null;
	public String docName = null;	
	public String ptName = null;
	public String appSummery = null;
	public Patient patient = new Patient();
}
