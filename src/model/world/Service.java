package model.world;

import java.time.LocalDateTime;
import java.util.Comparator;

public class Service implements Comparable<Service> {
	private String tripId;
	private Taxi taxi;
	private String company;
	private float extras;
	private float fare;
	private String paymentType;
	private float tips;
	private float tolls;
	private LocalDateTime tripEnd;
	private LocalDateTime tripStart;
	private int dropoffCommunityArea;
	private int pickupCommunityArea;
	private int tripSeconds;
	private double tripMiles;
	private double tripTotal;
	/**
	 * @return id - Trip_id
	 */
	
	
	public Service(String tripId, String company, float extras, float fare, String paymentType, float tips,
			float tolls, LocalDateTime tripEnd, LocalDateTime tripStart, int dropoffCommunityArea,
			int pickupCommunityArea, int tripSeconds, double tripMiles, double tripTotal) {
		this.tripId = tripId;
		this.taxi = null;
		this.company = company;
		this.extras = extras;
		this.fare = fare;
		this.paymentType = paymentType;
		this.tips = tips;
		this.tolls = tolls;
		this.tripEnd = tripEnd;
		this.tripStart = tripStart;
		this.dropoffCommunityArea = dropoffCommunityArea;
		this.pickupCommunityArea = pickupCommunityArea;
		this.tripSeconds = tripSeconds;
		this.tripMiles = tripMiles;
		this.tripTotal = tripTotal;
	}

	/**
	 * @return id - Taxi_id
	 */
		
	public String getTripId() {
		// TODO Auto-generated method stub
		return this.tripId;
	}	
	
	public Taxi getTaxi() {
		return taxi;
	}

	public void setTaxi(Taxi taxi) {
		this.taxi = taxi;
	}

	public String getCompany() {
		return company;
	}

	public float getExtras() {
		return extras;
	}

	public float getFare() {
		return fare;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public float getTips() {
		return tips;
	}

	public float getTolls() {
		return tolls;
	}

	public LocalDateTime getTripEnd() {
		return tripEnd;
	}

	public LocalDateTime getTripStart() {
		return tripStart;
	}

	public int getDropoffCommunityArea() {
		return dropoffCommunityArea;
	}

	public int getPickupCommunityArea() {
		return pickupCommunityArea;
	}

	public int getTripSeconds() {
		return tripSeconds;
	}

	public double getTripMiles() {
		return tripMiles;
	}

	public double getTripTotal() {
		return tripTotal;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Trip ID: "+this.tripId+"\nTaxi ID: "+this.taxi.getTaxiId()+"\nCompany: "+this.company+"\nExtras: "+this.extras+"\nFare: "+this.fare+"\nPayment Type: "+this.paymentType+"\nTips: "+this.tips+"\nTolls: "+this.tolls+"\nTrip End: "+this.tripEnd.toString()+"\nTrip Start :"+this.tripStart.toString()+"\nDropoff Community Area: "+this.dropoffCommunityArea+"\nPickup Community Area: "+this.pickupCommunityArea+"\nTrip Seconds: "+this.tripSeconds+"\nTrip Miles: "+this.tripMiles+"\nTrip Total: "+this.tripTotal;
	}
	
	@Override
	public int compareTo(Service o){
		// TODO Auto-generated method stub
		if(this.tripId.compareTo(o.getTripId())<0) {
			return -1;
		}else if(this.tripId.compareTo(o.getTripId())>0){
			return 1;
		}
		
		return 0;
	}

	@Override
	public int hashCode() {
		
		int result=0;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		return true;
	}
	
	public static class TripStartComparator implements Comparator<Service> {

		@Override
		public int compare(Service s1, Service s2) {
			// TODO Auto-generated method stub
			if(s1.getTripStart().isBefore(s2.getTripStart())) {
				return -1;
			}else {
				if(s1.getTripStart().isAfter(s2.getTripStart())) {
					return 1;
				}
			}
			
			return 0;
		}

	}
}
