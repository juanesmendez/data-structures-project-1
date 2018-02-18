package model.world;

import model.data_structures.List;

public class Company implements Comparable<Company>{
	private String name;
	private int numTaxis;
	private List<Taxi> taxis;

	public Company(String name) {
		this.name = name;
		this.numTaxis = 0;
		this.taxis = new List<>();
	}

	public String getName() {
		return name;

	}

	public void setName(String name) {
		this.name = name;
	}


	public int getNumTaxis() {
		return numTaxis;
	}

	public void setNumTaxis(int numTaxis) {
		this.numTaxis = numTaxis;
	}

	@Override
	public int compareTo(Company comp) {
		// TODO Auto-generated method stub
		if(this.name.compareTo(comp.getName()) == 0) {
			return 0;
		}else if(this.name.compareTo(comp.getName()) < 0) {
			return -1;
		}else if(this.name.compareTo(comp.getName()) > 0) {
			return 1;
		}

		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Company Name: "+this.name;
	}
	public void addTaxi(Taxi taxi) {
		//Doesn't need to verify if it already exists because it was verified in the loadServices() methd in TaxiServiceManager
		Taxi aux;
		aux = this.taxis.get(taxi);
		if(aux == null) {
			this.taxis.add(taxi);
		}
	}

}
