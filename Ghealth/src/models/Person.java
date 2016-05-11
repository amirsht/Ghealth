package models;

public abstract class Person {
	
	
	private int pID; //set value only in constructor
	private String pName; //set value only in constructor
	private int pPhone;
	private String pHomeAddr;
	
	public Person(int pID,String pName, int pPhone,String pHomeAddr){
		
		this.pID = pID;
		this.pName = pName;
		this.pPhone = pPhone;
		this.pHomeAddr = pHomeAddr;
		
	}
	
	public int getpID(){ return this.pID; }
	public String getpName(){ return this.pName;}
	public void setpPhone(int phone){this.pPhone = phone;}
	public int getpPhone(){ return this.pPhone;}
	public void setpHomeAddr(String pHomeAddr){ this.pHomeAddr= pHomeAddr;}
	public String getpHomeAddr(){return this.pHomeAddr;}
	
	
	
	
	
	
	
	
	
}

