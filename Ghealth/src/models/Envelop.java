package models;
import enums.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Envelop implements Serializable {

	private List<Object> objList;
	private task type;
	
	public Envelop() {
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
	
}
