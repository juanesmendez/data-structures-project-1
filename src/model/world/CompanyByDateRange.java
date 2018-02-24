package model.world;



import java.util.Comparator;

import model.data_structures.LinkedList;
import model.data_structures.List;

public class CompanyByDateRange implements Comparable<CompanyByDateRange>{
	private String name;
	private LinkedList<Service> services;
	
	public CompanyByDateRange(String name) {
		this.name = name;
		this.services = new List<Service>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedList<Service> getServices() {
		return services;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Company Name: "+this.name+"\nNumber of services: "+this.services.size();
		
		
	}
	
	@Override
	public int compareTo(CompanyByDateRange company) {
		// TODO Auto-generated method stub
		if(this.getName().compareTo(company.getName())<0) {
			return -1;
		}else if(this.getName().compareTo(company.getName())>0) {
			return 1;
		}
		return 0;
	}
	
	public void addService(Service s) {
		this.services.add(s);
	}
	
	public static class NumberOfServicesReverseComparator implements Comparator<CompanyByDateRange>{

		@Override
		public int compare(CompanyByDateRange c1, CompanyByDateRange c2) {
			// TODO Auto-generated method stub
			if(c1.services.size() < c2.services.size()) {
				return 1;
			}else if(c1.services.size() > c2.services.size()) {
				return -1;
			}
			return 0;
		}
		
	}
}
