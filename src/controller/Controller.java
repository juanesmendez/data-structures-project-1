package controller;

import api.ITaxiTripsManager;
import model.data_structures.LinkedList;
import model.logic.TaxiTripsManager;
import model.world.Service;
import model.world.Taxi;

public class Controller {

	/**
	 * Reference to the services manager
	 */
	private static ITaxiTripsManager  manager = new TaxiTripsManager();
	
	public static void loadWorld() {
		String serviceFile = "/Users/juanestebanmendez/Documents/Los Andes/Estructuras de Datos/proyecto_201810_sec_3_team_9/data/taxi-trips-wrvz-psew-subset-small.json";
		manager.loadWorld(serviceFile);
	}
	
	public static void loadServices( ) {
		// To define the dataset file's name 
		String serviceFile = "./data/taxi-trips-wrvz-psew-subset-small.json";
		
		manager.loadServices( serviceFile );
	}
		
	public static LinkedList<Taxi> getTaxisOfCompany (String company) {
		return manager.getTaxisOfCompany(company);
	}
	
	public static LinkedList<Service> getTaxiServicesToCommunityArea(int communityArea) {
		return manager.getTaxiServicesToCommunityArea( communityArea );
	}
}
