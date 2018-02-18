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
	
	public static boolean cargarSistema(String serviceFile) {
		//Chequear  como cambiar de archivo
		
		//Cambiar la siguiente linea:
		serviceFile = "/Users/juanestebanmendez/Documents/Los Andes/Estructuras de Datos/proyecto_201810_sec_3_team_9/data/taxi-trips-wrvz-psew-subset-small.json";
		boolean cargo = false;
		cargo = manager.cargarSistema(serviceFile);
		return cargo;
	}
	
	

}
