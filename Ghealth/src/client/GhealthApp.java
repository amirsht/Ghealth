package client;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import GUI.*;
import enums.*;
import models.*;

/**
 * The Main Class That starts Ghealth Application.
 *
 */
public class GhealthApp {

	
	
	public static void main(String[] args) {
		
		
		/*
		List<Object> objList = new ArrayList<Object>();
		Envelope en = Controller.Control(new Patient("4444"),task.GET_OPEN_APPOINTMENTS);
		for (Object obj : en.getobjList())
		{
			System.out.println((AppointmentSettings)obj);
		}
		
		/* GET_DOCTORS_IN_CLINIC_BY_TYPE */
		/*
		List<Object> objList = new ArrayList<Object>();
		objList.add("4444");
		objList.add(DoctorSpeciallity.Orthopedist);
		Envelope en = Controller.Control(objList,task.GET_DOCTORS_IN_CLINIC_BY_TYPE);
		for (Object obj : en.getobjList())
		{
			System.out.println((User)obj);
		}
		
		/* GET_AVAILIBLE_DOCTOR_HOURS */
		/*
		List<Object> objList_2 = new ArrayList<Object>();
		objList_2.add("2016-07-15");
		objList_2.add("5002");
		en = Controller.Control(objList_2,task.GET_AVAILIBLE_DOCTOR_HOURS);
		for (Object obj : en.getobjList())
		{
			System.out.println("Hour - ");
			System.out.println(obj.toString());
		}
	    
		
		*/
		
		
		LoginGUI login1 = new LoginGUI();
		LoginControl userctrl = new LoginControl(login1);
		

	}


}
