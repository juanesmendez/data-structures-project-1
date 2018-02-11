package api;

import model.data_structures.LinkedList;
import model.world.Service;
import model.world.Taxi;

/**
 * Basic API for testing the functionality of the TaxiTrip manager
 */
public interface ITaxiTripsManager {

	
	void loadWorld(String serviceFile);
	/**
	 * Method to load taxi services
	 * @param servicesFile - path to the file 
	 */
	void loadServices(String serviceFile);
	

    /**
	 * Method to return all the taxis for a given comapany
	 * @param company - Taxi company
	 * @return List of taxis
	 */
	public LinkedList<Taxi> getTaxisOfCompany(String company);
	
	/**
	 * Method to return all the services finished in a communityArea
	 * @param communityArea 
	 * @return List of services
	 */
	public LinkedList<Service> getTaxiServicesToCommunityArea(int communityArea);


	
}
