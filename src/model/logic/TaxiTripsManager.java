package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import api.ITaxiTripsManager;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.data_structures.LinkedList;
import model.data_structures.List;
import model.world.CommunityAreaByDateRange;
import model.world.Company;
import model.world.CompanyByDateRange;
import model.world.CompanyTaxi;
import model.world.DateTimeRange;
import model.world.DistanceRange;
import model.world.InfoTaxiRange;
import model.world.Service;
import model.world.ServicesValuePayed;
import model.world.Taxi;

public class TaxiTripsManager implements ITaxiTripsManager {

	// TODO
	// Definition of data model 
	public static final String DIRECCION_SMALL_JSON = "./data/taxi-trips-wrvz-psew-subset-small.json";
	public static final String DIRECCION_MEDIUM_JSON = "./data/taxi-trips-wrvz-psew-subset-medium.json";
	public static final String DIRECCION_LARGE_JSON = "./data/taxi-trips-wrvz-psew-subset-large.json";
	
	
	private List<Service> services;
	private List<Company> companies;
	private List<Taxi> taxis;

	
	// 1C
	@Override
	public boolean cargarSistema(String serviceFile) {
		// TODO Auto-generated method stub
		
		boolean cargo = false;
		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader(serviceFile));

			JSONArray jsonArray = (JSONArray)obj;
			JSONObject jsonObject;

			//Instantiating lists
			this.companies = new List<Company>();
			this.services = new List<Service>();
			this.taxis = new List<Taxi>();
			
			String aux;

			String companyName;
			String dropoffCensusTract; // Verify if I need to change it to double
			String dropoffCentroidLatitude;
			//Add declaration for saving dropoff_centroid_location in a variable
			String dropoffCentroidLongitude;
			int dropoffCommunityArea;
			float extras; //Check if leaving it as an integer or if it is better to change it to another type
			float fare;
			String paymentType;
			String pickupCensusTract;
			String pickupCentroidLatitude;
			//Add variable declaration for saving pickup_centroid_location
			String pickupCentroidLongitude;
			int pickupCommunityArea;
			String idTaxi;
			float tips;
			float tolls;
			LocalDateTime tripEnd;
			String idTrip;
			float tripMiles;
			int tripSeconds;
			LocalDateTime tripStart;
			float tripTotal;
			
			
			String auxDate;
			int year;
			int month;
			int day;
			int hour;
			int minutes;
			int seconds;
			int nanoseconds;
			
			//World classes
			Company company;
			Taxi taxi;
			Service service;
			
			Iterator<JSONObject> iterator = jsonArray.iterator();
			while(iterator.hasNext()) {
				jsonObject = (JSONObject) iterator.next();
				companyName = (String) jsonObject.get("company");
				if(companyName == null) {
					companyName = "Independent";
				}
				System.out.println("Company: "+companyName);
				dropoffCensusTract = (String) jsonObject.get("dropoff_census_tract");
				System.out.println("Dropoff Census Tract: "+dropoffCensusTract);
				dropoffCentroidLatitude = (String) jsonObject.get("dropoff_centroid_latitude");
				System.out.println("Dropoff Centroid Latitude: "+dropoffCentroidLatitude);
				//Add dropoff_centroid_location which is an array
				dropoffCentroidLongitude = (String) jsonObject.get("dropoff_centroid_longitude");
				System.out.println("Dropoff Centroid Longitude: "+dropoffCentroidLongitude);
				aux = (String) jsonObject.get("dropoff_community_area");
				if(aux!=null) {
					dropoffCommunityArea = Integer.parseInt(aux);
					System.out.println("Dropoff Community Area: "+dropoffCommunityArea);
				}else {
					dropoffCommunityArea = 0; //If community area is null assign 0, it means that there is no information about the community area
					System.out.println("Dropoff Community Area: "+dropoffCommunityArea);
				}
				aux = (String) jsonObject.get("extras");
				extras = Float.parseFloat(aux);
				System.out.println("Extras: "+extras);
				aux = (String) jsonObject.get("fare");
				fare = Float.parseFloat(aux);
				System.out.println("Fare: "+fare);
				paymentType = (String) jsonObject.get("payment_type");
				System.out.println("Payment Type: "+paymentType);
				pickupCensusTract = (String) jsonObject.get("pickup_census_tract");
				System.out.println("Pickup Census Tract: "+pickupCensusTract);
				pickupCentroidLatitude = (String) jsonObject.get("pickup_centroid_latitude");
				System.out.println("Pickup Centroid Latitude: "+pickupCentroidLatitude);
				// Add pickup_centroid_location which is an array
				pickupCentroidLongitude = (String) jsonObject.get("pickup_centroid_longitude");
				System.out.println("Pickup Centroid Longitude: "+pickupCentroidLongitude);
				aux = (String) jsonObject.get("pickup_community_area");
				if(aux!=null) {
					pickupCommunityArea = Integer.parseInt(aux);
					System.out.println("Pickup Community Area: "+pickupCommunityArea);
				}else {
					pickupCommunityArea = 0; //If community area is null assign 0, it means that there is not enough information about the community area.
					System.out.println("Pickup Community Area: "+pickupCommunityArea);
				}
				idTaxi = (String) jsonObject.get("taxi_id");
				System.out.println("Taxi ID: "+idTaxi);
				aux = (String) jsonObject.get("tips");
				tips = Float.parseFloat(aux);
				System.out.println("Tips: "+tips);
				aux = (String) jsonObject.get("tolls");
				tolls = Float.parseFloat(aux);
				System.out.println("Tolls: "+tolls);
				//Continue here
				//Parsing date timestamp
				auxDate = (String) jsonObject.get("trip_end_timestamp");
				StringTokenizer tokenizer = new StringTokenizer(auxDate, "-");
				year = Integer.parseInt(tokenizer.nextToken());
				month = Integer.parseInt(tokenizer.nextToken());
				aux = tokenizer.nextToken();
				tokenizer = new StringTokenizer(aux, ":");
				day = Integer.parseInt(tokenizer.nextToken("T"));
				aux = tokenizer.nextToken();
				tokenizer = new StringTokenizer(aux, ":");
				hour = Integer.parseInt(tokenizer.nextToken());
				minutes = Integer.parseInt(tokenizer.nextToken());
				aux=tokenizer.nextToken();
				tokenizer = new StringTokenizer(aux, ".");
				seconds = Integer.parseInt(tokenizer.nextToken());
				nanoseconds = Integer.parseInt(tokenizer.nextToken());
				tripEnd = LocalDateTime.of(year, month, day, hour, minutes, seconds, nanoseconds);
				System.out.println("Trip end timestamp: "+tripEnd.toString());
				idTrip = (String) jsonObject.get("trip_id");
				System.out.println("Trip id: "+idTrip);
				aux = (String) jsonObject.get("trip_miles");
				tripMiles = Float.parseFloat(aux);
				System.out.println("Trip Miles: "+tripMiles);
				aux = (String) jsonObject.get("trip_seconds");
				tripSeconds = Integer.parseInt(aux);
				System.out.println("Trip seconds: "+tripSeconds);
				auxDate = (String) jsonObject.get("trip_start_timestamp");
				tokenizer = new StringTokenizer(auxDate, "-");
				year = Integer.parseInt(tokenizer.nextToken());
				month = Integer.parseInt(tokenizer.nextToken());
				aux = tokenizer.nextToken();
				tokenizer = new StringTokenizer(aux, ":");
				day = Integer.parseInt(tokenizer.nextToken("T"));
				aux = tokenizer.nextToken();
				tokenizer = new StringTokenizer(aux, ":");
				hour = Integer.parseInt(tokenizer.nextToken());
				minutes = Integer.parseInt(tokenizer.nextToken());
				aux=tokenizer.nextToken();
				tokenizer = new StringTokenizer(aux, ".");
				seconds = Integer.parseInt(tokenizer.nextToken());
				nanoseconds = Integer.parseInt(tokenizer.nextToken());
				tripStart = LocalDateTime.of(year, month, day, hour, minutes, seconds, nanoseconds);
				System.out.println("Trip start timestamp: " +tripStart.toString());
				aux = (String) jsonObject.get("trip_total");
				tripTotal = Float.parseFloat(aux);
				System.out.println("Trip total: "+tripTotal);
				
				// From here I start CREATING WORLD CLASSES
				
				
				company = this.addCompany(companyName); //Return object always gonna be different to null
				taxi = this.addTaxi(idTaxi); //Return object always gonna be different to null
				//Associating company object to taxi object and adding taxi to company's list:
				this.associateCompanyToTaxi(taxi, company);
				this.addTaxiToCompany(taxi, company);
				
				
				
				
				System.out.println();
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ParseException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		cargo = true;
		return cargo;
	}

	@Override
	public IQueue<Service> darServiciosEnPeriodo(DateTimeRange rango) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Taxi darTaxiConMasServiciosEnCompaniaYRango(DateTimeRange rango, String company) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public InfoTaxiRange darInformacionTaxiEnRango(String id, DateTimeRange rango) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public LinkedList<DistanceRange> darListaRangosDistancia(String fecha, String horaInicial, String horaFinal) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public LinkedList<Company> darCompaniasTaxisInscritos() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Taxi darTaxiMayorFacturacion(DateTimeRange rango, String nomCompania) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ServicesValuePayed[] darServiciosZonaValorTotal(DateTimeRange rango, String idZona) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public LinkedList<CommunityAreaByDateRange> darZonasServicios(DateTimeRange rango) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public LinkedList<CompanyByDateRange> companiasMasServicios(DateTimeRange rango, int n) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public LinkedList<CompanyTaxi> taxisMasRentables() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IStack<Service> darServicioResumen(String taxiId, String horaInicial, String horaFinal, String fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	

	public Company addCompany(String companyName) {
		Company company = new Company(companyName);
		Company aux;
		aux = this.companies.get(company);
		
		if(aux == null) {
			  this.companies.add(company);
		}else {
			company = aux;
		}
		return company;
	}

	public Taxi addTaxi(String  idTaxi) {
		
		Taxi taxi = new Taxi(idTaxi);
		Taxi aux;
		aux = this.taxis.get(taxi);
		
		if(aux == null) {
			this.taxis.add(taxi);
		}else {
			taxi = aux;
		}
		return taxi;
	}

	public void associateCompanyToTaxi(Taxi taxi,Company company) {

		if(taxi.getCompany() == null) {
			taxi.setCompany(company);
		}

	}

	public void addTaxiToCompany(Taxi taxi, Company company) {
		company.addTaxi(taxi);
	}
}
