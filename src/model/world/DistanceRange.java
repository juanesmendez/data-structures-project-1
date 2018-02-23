package model.world;

import model.data_structures.LinkedList;
import model.data_structures.List;

public class DistanceRange implements Comparable<DistanceRange> {
	private double upperLimit;
	private double lowerLimit;
	private LinkedList<Service> servicesInRange;
	
	public DistanceRange(double upperLimit, double lowerLimit) {
		this.upperLimit = upperLimit;
		this.lowerLimit = lowerLimit;
		this.servicesInRange = new List<Service>();
	}

	public double getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(double upperLimit) {
		this.upperLimit = upperLimit;
	}

	public double getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(double lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	public LinkedList<Service> getServicesInRange() {
		return servicesInRange;
	}

	public void setServicesInRange(LinkedList<Service> servicesInRange) {
		this.servicesInRange = servicesInRange;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Lower Limit: "+this.lowerLimit+" Upper Limit: "+this.upperLimit;
	}

	@Override
	public int compareTo(DistanceRange distRange) {
		// TODO Auto-generated method stub
		if(this.lowerLimit < distRange.lowerLimit) {
			return -1;
		}else if(this.lowerLimit > distRange.lowerLimit) {
			return 1;
		}
		return 0;
	}
	
	public void addService(Service s) {
		if(s!=null) {
			this.servicesInRange.add(s);
		}
	}
	
	
}
