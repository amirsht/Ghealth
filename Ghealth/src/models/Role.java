package models;

public class Role {
	
	public enum Roles{
		
		CUSTOMER_SERVICE,LAB_WORKER, CLINIC_MANAGER,DOCTOR, GENERAL_MANAGER
	}

	private Roles userRole; 
	
	public Role(Roles userRole){ this.userRole = userRole;}
	
	public Roles getUserRole(){return this.userRole;} 
	
}
