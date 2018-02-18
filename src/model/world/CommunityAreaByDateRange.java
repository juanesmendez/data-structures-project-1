package model.world;

import model.data_structures.LinkedList;
import model.data_structures.List;

public class CommunityAreaByDateRange implements Comparable<CommunityAreaByDateRange>{
	private int idCommunityArea;
	private LinkedList<ServicesByDate> servicesDates;
	
	public CommunityAreaByDateRange(int idCommunityArea) {
		this.idCommunityArea = idCommunityArea;
		this.servicesDates = new List<ServicesByDate>();
	}

	public int getIdCommunityArea() {
		return idCommunityArea;
	}

	public void setIdCommunityArea(int idCommunityArea) {
		this.idCommunityArea = idCommunityArea;
	}

	public LinkedList<ServicesByDate> getServicesDates() {
		return servicesDates;
	}

	public void setServicesDates(LinkedList<ServicesByDate> servicesDates) {
		this.servicesDates = servicesDates;
	}

	@Override
	public int compareTo(CommunityAreaByDateRange communityArea) {
		// TODO Auto-generated method stub
		if(this.getIdCommunityArea() < communityArea.getIdCommunityArea()) {
			return -1;
		}else if(this.getIdCommunityArea() > communityArea.getIdCommunityArea()) {
			return 1;
		}
		
		return 0;
	}
	
	
}
