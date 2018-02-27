package controller;

import api.ITaxiTripsManager;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.LinkedList;
import model.logic.TaxiTripsManager;
import model.world.*;

public class Controller {

	/**
	 * Reference to the services manager
	 */
	private static ITaxiTripsManager  manager = new TaxiTripsManager();
	
	public static boolean cargarSistema(String serviceFile) {
		//Chequear  como cambiar de archivo
		
		//Cambiar la siguiente linea:
		//serviceFile = "/Users/juanestebanmendez/Documents/Los Andes/Estructuras de Datos/proyecto_201810_sec_3_team_9/data/taxi-trips-wrvz-psew-subset-small.json";
		boolean cargo = false;
		String[] serviceFilesArray = null;
		if(serviceFile == TaxiTripsManager.DIRECCION_LARGE_JSON) {
			serviceFilesArray = new String[7];
			serviceFilesArray[0] = TaxiTripsManager.DIRECCION_LARGE_JSON_DIA_1;
			serviceFilesArray[1] = TaxiTripsManager.DIRECCION_LARGE_JSON_DIA_2;
			serviceFilesArray[2] = TaxiTripsManager.DIRECCION_LARGE_JSON_DIA_3;
			serviceFilesArray[3] = TaxiTripsManager.DIRECCION_LARGE_JSON_DIA_4;
			serviceFilesArray[4] = TaxiTripsManager.DIRECCION_LARGE_JSON_DIA_5;
			serviceFilesArray[5] = TaxiTripsManager.DIRECCION_LARGE_JSON_DIA_6;
			serviceFilesArray[6] = TaxiTripsManager.DIRECCION_LARGE_JSON_DIA_7;
		}else if(serviceFile == TaxiTripsManager.DIRECCION_SMALL_JSON) {
			serviceFilesArray = new String[1];
			serviceFilesArray[0] = TaxiTripsManager.DIRECCION_SMALL_JSON;
		}else if(serviceFile == TaxiTripsManager.DIRECCION_MEDIUM_JSON) {
			serviceFilesArray = new String[1];
			serviceFilesArray[0] = TaxiTripsManager.DIRECCION_MEDIUM_JSON;
		}
		
		cargo = manager.cargarSistema(serviceFilesArray);
		return cargo;
	}
	
	public static IQueue<Service> darServiciosEnRango(DateTimeRange rangoReq1A) {
		return manager.darServiciosEnPeriodo(rangoReq1A);
	}
	
	public static Taxi darTaxiConMasServiciosEnCompaniaYRango( DateTimeRange rangoReq2A, String companyReq2A) {
		return manager.darTaxiConMasServiciosEnCompaniaYRango(rangoReq2A, companyReq2A);	
	}
	
	public static InfoTaxiRange darInformacionTaxiEnRango(String idTaxiReq3A, DateTimeRange rangoReq3A) {
		return manager.darInformacionTaxiEnRango(idTaxiReq3A, rangoReq3A);
	}
	
	public static LinkedList<DistanceRange> darListaRangosDistancia(String fechaReq4A, String horaInicialReq4A, String horaFinalReq4A){
		return manager.darListaRangosDistancia(fechaReq4A, horaInicialReq4A, horaFinalReq4A);
	}
	
	public static LinkedList<Company> darCompaniasTaxisInscritos(){
		return manager.darCompaniasTaxisInscritos();
	}
	
	public static Taxi darTaxiMayorFacturacion(DateTimeRange rangoReq2B, String compania2B) {
		return manager.darTaxiMayorFacturacion(rangoReq2B, compania2B);
	}
	
	public static ServicesValuePayed[] darServiciosZonaValorTotal(DateTimeRange rango3B, String zona3B) {
		return manager.darServiciosZonaValorTotal(rango3B, zona3B);
	}
	
	public static LinkedList<CommunityAreaByDateRange>darZonasServicios(DateTimeRange rango4B) {
		return manager.darZonasServicios(rango4B);
	}
	
	public static LinkedList<CompanyByDateRange> companiasMasServicios(DateTimeRange rango2C, int n2C) {
		return manager.companiasMasServicios(rango2C, n2C);
	}
	public static LinkedList<CompanyTaxi> taxisMasRentables() {
		return manager.taxisMasRentables();
	}
	
	public static IStack <Service> darServicioResumen(String idTaxi4C, String horaInicialReq4C, String horaFinalReq4C, String fechaReq4C, int maxMillas){
		return manager.darServicioResumen(idTaxi4C, horaInicialReq4C, horaFinalReq4C, fechaReq4C, maxMillas);
	}
}
