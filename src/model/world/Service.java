package model.world;


public class Service implements Comparable<Service> {
	private String tripId;
	private String taxiId;
	private String company;
	private int dropoffCommunityArea;
	private int pickupCommunityArea;
	private int tripSeconds;
	private double tripMiles;
	private double tripTotal;
	/**
	 * @return id - Trip_id
	 */
	
	public Service(String tripId,String taxiId,String company,int dropoffCommunityArea,int pickupCommunityArea,int tripSeconds,double tripMiles,double tripTotal) {
		this.tripId = tripId;
		this.taxiId = taxiId;
		this.company = company;
		this.dropoffCommunityArea = dropoffCommunityArea;
		this.pickupCommunityArea = pickupCommunityArea;
		this.tripSeconds = tripSeconds;
		this.tripMiles = tripMiles;
		this.tripTotal = tripTotal;
	}
	
	public String getTripId() {
		// TODO Auto-generated method stub
		return this.tripId;
	}	
	
	/**
	 * @return id - Taxi_id
	 */
	public String getTaxiId() {
		// TODO Auto-generated method stub
		return this.taxiId;
	}	
	
	public String getCompany() {
		return this.company;
	}
	
	public int getDropoffCommunityArea() {
		return this.dropoffCommunityArea;
	}
	
	public int getPickupCommunityArea() {
		return this.pickupCommunityArea;
	}
	/**
	 * @return time - Time of the trip in seconds.
	 */
	public int getTripSeconds() {
		// TODO Auto-generated method stub
		return this.tripSeconds;
	}

	/**
	 * @return miles - Distance of the trip in miles.
	 */
	public double getTripMiles() {
		// TODO Auto-generated method stub
		return this.tripMiles;
	}
	
	/**
	 * @return total - Total cost of the trip
	 */
	public double getTripTotal() {
		// TODO Auto-generated method stub
		return this.tripTotal;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Trip ID: "+this.tripId+"\nTaxi ID: "+this.taxiId+"\nCompany: "+this.company+"\nDropoff Community Area: "+this.dropoffCommunityArea+"\nPickup Community Area: "+this.pickupCommunityArea+"\nTrip Seconds: "+this.tripSeconds+"\nTrip Miles: "+this.tripMiles+"\nTrip Total: "+this.tripTotal;
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((taxiId == null) ? 0 : taxiId.hashCode());
		result = prime * result + ((tripId == null) ? 0 : tripId.hashCode());
		long temp;
		temp = Double.doubleToLongBits(tripMiles);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + tripSeconds;
		temp = Double.doubleToLongBits(tripTotal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Service other = (Service) obj;
		if (taxiId == null) {
			if (other.taxiId != null)
				return false;
		} else if (!taxiId.equals(other.taxiId))
			return false;
		if (tripId == null) {
			if (other.tripId != null)
				return false;
		} else if (!tripId.equals(other.tripId))
			return false;
		if (Double.doubleToLongBits(tripMiles) != Double.doubleToLongBits(other.tripMiles))
			return false;
		if (tripSeconds != other.tripSeconds)
			return false;
		if (Double.doubleToLongBits(tripTotal) != Double.doubleToLongBits(other.tripTotal))
			return false;
		return true;
	}
}
