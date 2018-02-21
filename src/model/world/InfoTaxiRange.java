package model.world;

import model.data_structures.LinkedList;
import model.data_structures.List;

public class InfoTaxiRange {
	private String idTaxi;
	private DateTimeRange range;
	private String company;
	private double moneyEarned;
	private double totalDistanceTraveled;
	private int totalTime;
	private LinkedList<Service> servicesInRange;
	
	
	
	public InfoTaxiRange(String idTaxi, DateTimeRange range, String company, double moneyEarned,double totalDistanceTraveled, int totalTime, LinkedList<Service> servicesInRange) {
		this.idTaxi = idTaxi;
		this.range = range;
		this.company = company;
		this.moneyEarned = moneyEarned;
		this.totalDistanceTraveled = totalDistanceTraveled;
		this.totalTime = totalTime;
		this.servicesInRange = servicesInRange;
	}
	public String getIdTaxi() {
		return idTaxi;
	}
	public void setIdTaxi(String idTaxi) {
		this.idTaxi = idTaxi;
	}
	public DateTimeRange getRange() {
		return range;
	}
	public void setRange(DateTimeRange range) {
		this.range = range;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public double getMoneyEarned() {
		return moneyEarned;
	}
	public void setMoneyEarned(double moneyEarned) {
		this.moneyEarned = moneyEarned;
	}
	public double getTotalDistanceTraveled() {
		return totalDistanceTraveled;
	}
	public void setTotalDistanceTraveled(double totalDistanceTraveled) {
		this.totalDistanceTraveled = totalDistanceTraveled;
	}
	public int getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	public LinkedList<Service> getServicesInRange() {
		return servicesInRange;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Taxi ID: "+this.idTaxi+"\nCompany: "+this.company+"\nMoney earned: "+this.moneyEarned+"\nTotal distance traveled: "+
				this.totalDistanceTraveled+"\nTotal time: "+this.totalTime+"\nNumber of services: "+this.servicesInRange.size();
	}
	
}
