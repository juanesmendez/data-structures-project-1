package model.world;

public class CompanyTaxi implements Comparable<CompanyTaxi> {
	private String companyName;
	private Taxi taxi;
	
	public CompanyTaxi(String companyName,Taxi taxi) {
		this.companyName = companyName;
		this.taxi = taxi;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Taxi getTaxi() {
		return taxi;
	}

	public void setTaxi(Taxi taxi) {
		this.taxi = taxi;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Company name: "+this.companyName+"\nTaxi: "+this.taxi.getTaxiId();
	}
	
	@Override
	public int compareTo(CompanyTaxi companyTaxi) {
		// TODO Auto-generated method stub
		if(this.companyName.compareTo(companyTaxi.getCompanyName())<0) {
			return 1;
		}else if(this.companyName.compareTo(companyTaxi.getCompanyName())>0) {
			return 1;
		}
		return 0;
	}
	
	
}
