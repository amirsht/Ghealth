package Server;

import java.io.Serializable;
import java.util.Date;

import models.Patient;

/**
 * @author G5 lab group
 * Contains notification details.
 */
public class Notification implements Serializable {
	
	/** The date. */
	public Date date = null;
	
	/** The sdate. */
	public String sdate = null;
	
	/** The time. */
	public String time = null;
	
	/** The mail. */
	public String mail = null;
	
	/** The location. */
	public String location = null;
	
	/** The doc name. */
	public String docName = null;	
	
	/** The pt name. */
	public String ptName = null;
	
	/** The app summery. */
	public String appSummery = null;
	
	/** The patient. */
	public Patient patient = new Patient();
}
