package model.world;

import model.data_structures.LinkedList;
import model.data_structures.List;

public class DateTimeRange {
	private String initialDate;
	private String endDate;
	private String initialHour;
	private String endHour;
	private LinkedList<Service> servicesInRange;
	
	public DateTimeRange(String initialDate, String endDate, String initialHour, String endHour) {
		
		this.initialDate = initialDate;
		this.endDate = endDate;
		this.initialHour = initialHour;
		this.endHour = endHour;
		this.servicesInRange = new List<Service>();
	}

	public String getInitialDate() {
		return initialDate;
	}

	public void setInitialDate(String initialDate) {
		this.initialDate = initialDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getInitialHour() {
		return initialHour;
	}

	public void setInitialHour(String initialHour) {
		this.initialHour = initialHour;
	}

	public String getEndHour() {
		return endHour;
	}

	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}

	public LinkedList<Service> getServicesInRange() {
		return servicesInRange;
	}

	public void setServicesInRange(LinkedList<Service> servicesInRange) {
		this.servicesInRange = servicesInRange;
	}
	
	
	
}
