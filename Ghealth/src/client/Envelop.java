package client;
import enums.*;
import java.io.Serializable;

public class Envelop implements Serializable {

	private Object obj;
	private task type;
	
	public Envelop()
	{
		
	}
	
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public task getType() {
		return type;
	}
	public void setType(task type) {
		this.type = type;
	}
	
	
}
