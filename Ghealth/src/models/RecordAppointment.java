/**
 * TODO This is the class description
 */


package models;

public class RecordAppointment {

	private String AppSummery;
	private PersonalDoctorRef PdoctorRef;
	/**
	 * @param AppSummery
	 * @param PdoctorRef
	 */
	
	public String getAppSummery() {
		return AppSummery;
	}
	public void setAppSummery(String appSummery) {
		AppSummery = appSummery;
	}
	public PersonalDoctorRef getPdoctorRef() {
		return PdoctorRef;
	}
	public void setPdoctorRef(PersonalDoctorRef pdoctorRef) {
		PdoctorRef = pdoctorRef;
	}
	
	
}
