package client;

import models.GhealthClientModel;
import controllers.GhealthClientController;
import view.GhealthClientGUI;

/**
 * The Main Class That starts Ghealth Application.
 *
 */
public class GhealthApp {

	public static GhealthClient clien;

	public static void main(String[] args) {
		
		GhealthClientGUI clientView = new GhealthClientGUI();
		GhealthClientModel clientModel = new GhealthClientModel();
        GhealthClientController clientController = new GhealthClientController(clientView,clientModel);

	}

}
