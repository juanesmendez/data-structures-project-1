package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
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
import model.data_structures.Queue;
import model.logic.utils.Utils;
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
	
	
	private LinkedList<Service> services;
	private LinkedList<Company> companies;
	private LinkedList<Taxi> taxis;
	
	public TaxiTripsManager() {
		this.services = new List<>();
		this.companies = new List<>();
		this.taxis = new List<>();
	}
	
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
				//System.out.println("Company: "+companyName);
				dropoffCensusTract = (String) jsonObject.get("dropoff_census_tract");
				//System.out.println("Dropoff Census Tract: "+dropoffCensusTract);
				dropoffCentroidLatitude = (String) jsonObject.get("dropoff_centroid_latitude");
				//System.out.println("Dropoff Centroid Latitude: "+dropoffCentroidLatitude);
				//Add dropoff_centroid_location which is an array
				dropoffCentroidLongitude = (String) jsonObject.get("dropoff_centroid_longitude");
				//System.out.println("Dropoff Centroid Longitude: "+dropoffCentroidLongitude);
				aux = (String) jsonObject.get("dropoff_community_area");
				if(aux!=null) {
					dropoffCommunityArea = Integer.parseInt(aux);
					//System.out.println("Dropoff Community Area: "+dropoffCommunityArea);
				}else {
					dropoffCommunityArea = 0; //If community area is null assign 0, it means that there is no information about the community area
					//System.out.println("Dropoff Community Area: "+dropoffCommunityArea);
				}
				aux = (String) jsonObject.get("extras");
				extras = Float.parseFloat(aux);
				//System.out.println("Extras: "+extras);
				aux = (String) jsonObject.get("fare");
				fare = Float.parseFloat(aux);
				//System.out.println("Fare: "+fare);
				paymentType = (String) jsonObject.get("payment_type");
				//System.out.println("Payment Type: "+paymentType);
				pickupCensusTract = (String) jsonObject.get("pickup_census_tract");
				//System.out.println("Pickup Census Tract: "+pickupCensusTract);
				pickupCentroidLatitude = (String) jsonObject.get("pickup_centroid_latitude");
				//System.out.println("Pickup Centroid Latitude: "+pickupCentroidLatitude);
				// Add pickup_centroid_location which is an array
				pickupCentroidLongitude = (String) jsonObject.get("pickup_centroid_longitude");
				//System.out.println("Pickup Centroid Longitude: "+pickupCentroidLongitude);
				aux = (String) jsonObject.get("pickup_community_area");
				if(aux!=null) {
					pickupCommunityArea = Integer.parseInt(aux);
				//	System.out.println("Pickup Community Area: "+pickupCommunityArea);
				}else {
					pickupCommunityArea = 0; //If community area is null assign 0, it means that there is not enough information about the community area.
				//	System.out.println("Pickup Community Area: "+pickupCommunityArea);
				}
				idTaxi = (String) jsonObject.get("taxi_id");
				//System.out.println("Taxi ID: "+idTaxi);
				aux = (String) jsonObject.get("tips");
				tips = Float.parseFloat(aux);
				//System.out.println("Tips: "+tips);
				aux = (String) jsonObject.get("tolls");
				tolls = Float.parseFloat(aux);
				//System.out.println("Tolls: "+tolls);
				//Continue here
				//Parsing date timestamp
				auxDate = (String) jsonObject.get("trip_end_timestamp");
				if(auxDate == null) {
					tripEnd = null;
				//	System.out.println("Trip end timestamp: NO HAY INFORMACION");
				}else {
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
				//	System.out.println("Trip end timestamp: "+tripEnd.toString());
				}
				
				idTrip = (String) jsonObject.get("trip_id");
				//System.out.println("Trip id: "+idTrip);
				aux = (String) jsonObject.get("trip_miles");
				tripMiles = Float.parseFloat(aux);
				//System.out.println("Trip Miles: "+tripMiles);
				aux = (String) jsonObject.get("trip_seconds");
				if(aux == null) {
					aux = "0";
				}
				tripSeconds = Integer.parseInt(aux);
				//System.out.println("Trip seconds: "+tripSeconds);
				auxDate = (String) jsonObject.get("trip_start_timestamp");
				StringTokenizer tokenizer;
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
				//System.out.println("Trip start timestamp: " +tripStart.toString());
				aux = (String) jsonObject.get("trip_total");
				tripTotal = Float.parseFloat(aux);
				//System.out.println("Trip total: "+tripTotal);
				
				// From here I start CREATING WORLD CLASSES
				
				//Add to list in alphabetical order.
				company = this.addCompany(companyName); //Return object always gonna be different to null
				taxi = this.addTaxi(idTaxi); //Return object always gonna be different to null
				//Associating company object to taxi object and adding taxi to company's list:
				this.associateCompanyToTaxi(taxi, company);
				this.addTaxiToCompany(taxi, company);
				
				service = this.addService(idTrip, companyName, extras, fare, paymentType, tips, tolls, tripEnd, tripStart, dropoffCommunityArea, pickupCommunityArea, tripSeconds, tripMiles, tripTotal);
				this.addServiceToTaxi(taxi, service);
				this.associateTaxiToService(taxi, service);
				
				
				//CONTINUAR CON VERIFICAR ORDENAMIENTO DE LISTA DE COMPAÑIAS 
				
				
				//System.out.println();
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ParseException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}finally {
			cargo = true;
		}
		
		//ACA CHEQUEO ISNTANCIAMIENTO CORRECTO DEL MUNDO
		
		System.out.println("COMPAÑIAS:");
		for(Company c: this.companies) {
			System.out.println(c.toString());
		}
		/*
		for(Taxi t:this.taxis) {
			System.out.println("Taxi ID: "+t.getTaxiId().toString());
		}*/
		/*
		System.out.println();
		for(Company c:this.companies) {
			System.out.println("Compañia: "+c.toString());
			for(Taxi t: c.getTaxis()) {
				System.out.println("\t\tTaxi ID: "+t.getTaxiId());
				System.out.println("\t\tSize Services List: "+t.getServices().size());
				for(Service s: t.getServices()) {
					System.out.println("\t\t\t\t\t\tService ID: "+s.getTripId());
					
				}
				
			}
		}*/

		return cargo;
	}

	@Override
	public IQueue<Service> darServiciosEnPeriodo(DateTimeRange rango) {
		// TODO Auto-generated method stub
		IQueue<Service> cola = new Queue<Service>();
		LinkedList<Service> listaAux = new List<Service>();
		LocalDateTime array[];
		array = Utils.convertDateTimeRangeToLocalDateTimeArray(rango);

		LocalDateTime initialDate = array[0];
		LocalDateTime endDate = array[1];
		
		Comparator<Service> comparator = new Service.TripStartComparator();
		
		for(Service s:this.services) {
			if(((s.getTripStart().compareTo(initialDate) > 0 || s.getTripStart().compareTo(initialDate) == 0) && (s.getTripStart().compareTo(endDate) < 0 || s.getTripStart().compareTo(endDate)==0)) 
					&& ((s.getTripEnd().compareTo(initialDate) > 0 || s.getTripEnd().compareTo(initialDate) == 0) && (s.getTripEnd().compareTo(endDate) < 0 || s.getTripEnd().compareTo(endDate)==0))) {
				
				listaAux.add(s, comparator);
			}
		}
		for(Service s:listaAux) {
			cola.enqueue(s);
		}
		return cola;
	}


	@Override
	public Taxi darTaxiConMasServiciosEnCompaniaYRango(DateTimeRange rango, String company) {
		// TODO Auto-generated method stub
		Company comp;
		Company compABuscar = new Company(company);
		Taxi taxi = null;
		int mayor = 0;
		int contServicios = 0;
		LocalDateTime array[] = Utils.convertDateTimeRangeToLocalDateTimeArray(rango);
		LocalDateTime initialDate = array[0];
		LocalDateTime endDate = array[1];


		comp = this.companies.get(compABuscar);

		if(comp != null) {
			for(Taxi t:comp.getTaxis()) {
				contServicios = 0;
				System.out.println("Taxi ID :"+t.getTaxiId() );
				for(Service s:t.getServices()) {
					if(((s.getTripStart().compareTo(initialDate) > 0 || s.getTripStart().compareTo(initialDate) == 0) && (s.getTripStart().compareTo(endDate) < 0 || s.getTripStart().compareTo(endDate)==0)) 
							&& ((s.getTripEnd().compareTo(initialDate) > 0 || s.getTripEnd().compareTo(initialDate) == 0) && (s.getTripEnd().compareTo(endDate) < 0 || s.getTripEnd().compareTo(endDate)==0))) {
						contServicios++;	
						
					}
				}
				System.out.println("\t\tContador Servicios: "+contServicios);
				if(contServicios > mayor) {
					mayor = contServicios;
					taxi = t;
				}
			}
		}else {
			System.out.println("NO se encontro la compañia");
		}
		System.out.println();
		System.out.println("MAYOR NUMERO DE SERVICIOS EN RANGO: "+mayor);
		return taxi;
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
			  //this.companies.add(company);
			this.companies.addInOrder(company);
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
			//this.taxis.add(taxi);
			this.taxis.addInOrder(taxi);
		}else {
			taxi = aux;
		}
		return taxi;
	}
	
	public Service addService(String idTrip, String companyName, float extras, float fare, String paymentType, float tips, float tolls, LocalDateTime tripEnd, LocalDateTime tripStart, int dropoffCommunityArea, int pickupCommunityArea, int tripSeconds, double tripMiles, double tripTotal) {
		Service service = new Service(idTrip, companyName, extras, fare, paymentType, tips, tolls, tripEnd, tripStart, dropoffCommunityArea, pickupCommunityArea, tripSeconds, tripMiles, tripTotal);
		//this.services.add(service);
		this.services.addInOrder(service);
		return service;
		
	}

	public void associateCompanyToTaxi(Taxi taxi,Company company) {

		if(taxi.getCompany() == null) {
			taxi.setCompany(company);
		}

	}

	public void addTaxiToCompany(Taxi taxi, Company company) {
		company.addTaxi(taxi);
	}
	
	public void addServiceToTaxi(Taxi taxi, Service service) {
		taxi.addService(service);
	}
	
	public void associateTaxiToService(Taxi taxi, Service service) {
		if(service.getTaxi() == null) {
			service.setTaxi(taxi);
		}
	}
}
