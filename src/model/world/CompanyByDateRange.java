package model.world;



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
	public int compareTo(CompanyByDateRange company) {
		// TODO Auto-generated method stub
		if(this.getName().compareTo(company.getName())<0) {
			return -1;
		}else if(this.getName().compareTo(company.getName())>0) {
			return 1;
		}
		return 0;
	}
	
}
