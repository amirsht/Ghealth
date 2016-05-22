package client;

import enums.task;
import models.Envelope;
import models.Patient;
import models.User;

public class UserControl {

public static User UserCon(User us,task ts){
    	
    	Envelope En = new Envelope();
      
        En.addobjList(us);
        En.setType(ts);
        En =  Controller.communicate(En);
        us = (User)(En.getSingleObject());
        
        //TODO 
    	return us; 
    }
    
    
    
    public static User GetExistUser(User us)
    {
		return UserCon(us,task.GET_USER);
    }
    
    
    public static void main(String[] args) {
    	
    	User pinto = new User("5000");
    	
    	pinto = GetExistUser(pinto);
        
    	
    	System.out.println(pinto);
        
        
    }
	
	
}
