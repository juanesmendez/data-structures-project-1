package model.world;

import model.data_structures.LinkedList;
import model.data_structures.List;

public class ServicesValuePayed {
	private double accumulatedValue;
	private LinkedList<Service> AssociatedServices;
	
	public ServicesValuePayed(double accumulatedValue) {
		this.accumulatedValue = accumulatedValue;
		this.AssociatedServices = new List<Service>();
	}

	public double getAccumulatedValue() {
		return accumulatedValue;
	}

	public void setAccumulatedValue(double accumulatedValue) {
		this.accumulatedValue = accumulatedValue;
	}

	public LinkedList<Service> getAssociatedServices() {
		return AssociatedServices;
	}

	public void setAssociatedServices(LinkedList<Service> associatedServices) {
		AssociatedServices = associatedServices;
	}
	
	
	
	
}
