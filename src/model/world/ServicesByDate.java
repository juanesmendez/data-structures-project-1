package model.world;

import java.time.LocalDateTime;

import model.data_structures.LinkedList;
import model.data_structures.List;

public class ServicesByDate implements Comparable<ServicesByDate>{
	private LocalDateTime date;
	private int numServices;
	private LinkedList<Service> associatedServices;
	
	public ServicesByDate(LocalDateTime date, int numServices) {
		this.date = date;
		this.numServices = numServices;
		this.associatedServices = new List<Service>();
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public int getNumServices() {
		return numServices;
	}

	public void setNumServices(int numServices) {
		this.numServices = numServices;
	}

	public LinkedList<Service> getAssociatedServices() {
		return associatedServices;
	}

	public void setAssociatedServices(LinkedList<Service> associatedServices) {
		this.associatedServices = associatedServices;
	}

	@Override
	public int compareTo(ServicesByDate servByDate) {
		// TODO Auto-generated method stub
		if(this.getDate().compareTo(servByDate.getDate())<0) {
			return -1;
		}else if(this.getDate().compareTo(servByDate.getDate())>0) {
			return 1;
		}
		return 0;
	}
	
	
}
