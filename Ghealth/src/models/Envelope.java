/**
 * TODO This is the class description
 */

package models;
import enums.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Envelope implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7171959639692110621L;
	//private ArrayList<Object> objList;
	private List<Object> objList;
	private task type;
	private Status st;
	private DoctorSpeciallity sp;
	
	public Envelope() {
		this.objList = new ArrayList<Object>();
	}

	
	public task getType() {
		return type;
	}
	public void setType(task type) {
		this.type = type;
	}

	public List<Object> getobjList() {
		return objList;
	}

	public void setobjList(List<Object> obj) {
		this.objList = obj;
	}
	
	public void addobjList(Object obj)
	{
		this.objList.add(obj);
	}
	
	public Object getSingleObject()
	{
		return this.objList.get(0);
	}


	public Status getStatus() {
		return st;
	}


	public void setStatus(Status st) {
		this.st = st;
	}


	public DoctorSpeciallity getSpeciality() {
		return sp;
	}


	public void setSpeciality(DoctorSpeciallity sp) {
		this.sp = sp;
	}


	
}
