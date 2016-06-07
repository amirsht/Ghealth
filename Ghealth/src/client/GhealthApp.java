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
 * Initializes login screen
 */
public class GhealthApp {

	
	
	public static void main(String[] args) {
		
		LoggingOut lo = new LoggingOut();
		LoginGUI login1 = new LoginGUI();
		LoginControl userctrl = new LoginControl(login1);
		
	}

}

//logout