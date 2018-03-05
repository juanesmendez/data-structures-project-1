package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
import model.data_structures.Stack;
import model.logic.utils.Utils;
import model.sort.Insertion;
import model.sort.Merge;
import model.sort.Quick;
import model.sort.Quick3way;
import model.world.CommunityAreaByDateRange;
import model.world.Company;
import model.world.CompanyByDateRange;
import model.world.CompanyTaxi;
import model.world.DateTimeRange;
import model.world.DistanceRange;
import model.world.InfoTaxiRange;
import model.world.Service;
import model.world.ServicesByDate;
import model.world.ServicesValuePayed;
import model.world.Taxi;

public class TaxiTripsManager implements ITaxiTripsManager {

	// TODO
	// Definition of data model 
	public static final String DIRECCION_SMALL_JSON = "./data/taxi-trips-wrvz-psew-subset-small.json";
	public static final String DIRECCION_MEDIUM_JSON = "./data/taxi-trips-wrvz-psew-subset-medium.json";
	public static final String DIRECCION_LARGE_JSON = "./data/taxi-trips-wrvz-psew-subset-large.json";
	public static final String DIRECCION_LARGE_JSON_DIA_1 = "./data/taxi-trips-wrvz-psew-subset-large/taxi-trips-wrvz-psew-subset-02-02-2017.json";
	public static final String DIRECCION_LARGE_JSON_DIA_2 = "./data/taxi-trips-wrvz-psew-subset-large/taxi-trips-wrvz-psew-subset-03-02-2017.json";
	public static final String DIRECCION_LARGE_JSON_DIA_3 = "./data/taxi-trips-wrvz-psew-subset-large/taxi-trips-wrvz-psew-subset-04-02-2017.json";
	public static final String DIRECCION_LARGE_JSON_DIA_4 = "./data/taxi-trips-wrvz-psew-subset-large/taxi-trips-wrvz-psew-subset-05-02-2017.json";
	public static final String DIRECCION_LARGE_JSON_DIA_5 = "./data/taxi-trips-wrvz-psew-subset-large/taxi-trips-wrvz-psew-subset-06-02-2017.json";
	public static final String DIRECCION_LARGE_JSON_DIA_6 = "./data/taxi-trips-wrvz-psew-subset-large/taxi-trips-wrvz-psew-subset-07-02-2017.json";
	public static final String DIRECCION_LARGE_JSON_DIA_7 = "./data/taxi-trips-wrvz-psew-subset-large/taxi-trips-wrvz-psew-subset-08-02-2017.json";
	
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
	public boolean cargarSistema(String[] serviceFilesArray) { //Receives array with a service file path in each array position
		// TODO Auto-generated method stub
		
		boolean cargo = false;
		JSONParser parser = new JSONParser();
		int contServicios = 0;
		
		this.services = new List<>();
		this.companies = new List<>();
		this.taxis = new List<>();
		for(int i=0;i<serviceFilesArray.length;i++) {


			try {
				Object obj = parser.parse(new FileReader(serviceFilesArray[i]));


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
					if(aux==null) {
						aux = "0";
					}
					extras = Float.parseFloat(aux);
					//System.out.println("Extras: "+extras);
					aux = (String) jsonObject.get("fare");
					if(aux==null) {
						aux = "0";
					}
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
					if(aux == null) {
						aux = "0";
					}
					tips = Float.parseFloat(aux);
					//System.out.println("Tips: "+tips);
					aux = (String) jsonObject.get("tolls");
					if(aux == null) {
						aux = "0";
					}
					tolls = Float.parseFloat(aux);
					//System.out.println("Tolls: "+tolls);
					//Continue here
					//Parsing date timestamp
					auxDate = (String) jsonObject.get("trip_end_timestamp");
					if(auxDate == null) {
						tripEnd = LocalDateTime.now(); //Assigning actual date
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
					if(aux == null) {
						aux = "0";
					}
					tripMiles = Float.parseFloat(aux);
					//System.out.println("Trip Miles: "+tripMiles);
					aux = (String) jsonObject.get("trip_seconds");
					if(aux == null) {
						aux = "0";
					}
					tripSeconds = Integer.parseInt(aux);
					//System.out.println("Trip seconds: "+tripSeconds);
					auxDate = (String) jsonObject.get("trip_start_timestamp");

					if(auxDate == null) {
						tripStart = LocalDateTime.now();
					}else {
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
					}

					//System.out.println("Trip start timestamp: " +tripStart.toString());
					aux = (String) jsonObject.get("trip_total");
					if(aux==null) {
						aux = "0";
					}
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


					//CONTINUAR CON VERIFICAR ORDENAMIENTO DE LISTA DE COMPA√ëIAS 

					contServicios++;
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

		}
		
		//ACA CHEQUEO ISNTANCIAMIENTO CORRECTO DEL MUNDO
		/*
		System.out.println("COMPA√ëIAS:");
		for(Company c: this.companies) {
			System.out.println(c.toString());
		}*/
		/*
		for(Taxi t:this.taxis) {
			System.out.println("Taxi ID: "+t.getTaxiId().toString());
		}*/
		/*
		System.out.println();
		for(Company c:this.companies) {
			System.out.println("Compa√±ia: "+c.toString());
			for(Taxi t: c.getTaxis()) {
				System.out.println("\t\tTaxi ID: "+t.getTaxiId());
				System.out.println("\t\tSize Services List: "+t.getServices().size());
				for(Service s: t.getServices()) {
					System.out.println("\t\t\t\t\t\tService ID: "+s.getTripId());
					
				}
				
			}
		}*/
		System.out.println();
		System.out.println("Se cargaron "+contServicios+ " datos del .JSON.");
		System.out.println();
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
		
		Service[] arrayServices = new Service[this.services.size()];
		int sizeArray = 0;
		/*
		for(int i=0;i<services.size();i++) { //Is there a way to make this more efficient?
			arrayServices[i] = services.get(i);
			System.out.println(sizeArray++);
		}*/
		Iterator it = services.iterator();
		int j=0;
		while(it.hasNext()) {
			arrayServices[j] = (Service) it.next();
			j++;
		}
		
		Quick.sort(arrayServices, comparator);
		
		for(int i=0;i<arrayServices.length;i++) {
			if(((arrayServices[i].getTripStart().compareTo(initialDate) > 0 || arrayServices[i].getTripStart().compareTo(initialDate) == 0) && (arrayServices[i].getTripStart().compareTo(endDate) < 0 || arrayServices[i].getTripStart().compareTo(endDate)==0)) 
					&& ((arrayServices[i].getTripEnd().compareTo(initialDate) > 0 || arrayServices[i].getTripEnd().compareTo(initialDate) == 0) && (arrayServices[i].getTripEnd().compareTo(endDate) < 0 || arrayServices[i].getTripEnd().compareTo(endDate)==0))) {
				
				listaAux.add(arrayServices[i]);
			}
			if(arrayServices[i].getTripStart().isAfter(endDate)) {
				break;
			}
		}
		/*
		for(Service s:this.services) {
			if(((s.getTripStart().compareTo(initialDate) > 0 || s.getTripStart().compareTo(initialDate) == 0) && (s.getTripStart().compareTo(endDate) < 0 || s.getTripStart().compareTo(endDate)==0)) 
					&& ((s.getTripEnd().compareTo(initialDate) > 0 || s.getTripEnd().compareTo(initialDate) == 0) && (s.getTripEnd().compareTo(endDate) < 0 || s.getTripEnd().compareTo(endDate)==0))) {
				
				listaAux.add(s, comparator);
			}
		}*/
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
					taxi.setTotalServicesInRange(mayor);
				}
			}
		}else {
			System.out.println("NO se encontro la compa√±ia");
		}
		//Check if it is better to create a new Taxi object with the number of services between the range received as a parameter..
		System.out.println();
		System.out.println("MAYOR NUMERO DE SERVICIOS EN RANGO: "+mayor);
		System.out.println();
		return taxi;
	}


	@Override
	public InfoTaxiRange darInformacionTaxiEnRango(String id, DateTimeRange rango) {
		// TODO Auto-generated method stub
		InfoTaxiRange infoTaxi = null;
		Taxi taxiABuscar = new Taxi(id);
		
		LocalDateTime array[] = Utils.convertDateTimeRangeToLocalDateTimeArray(rango);
		LocalDateTime initialDate = array[0];
		LocalDateTime endDate = array[1];
		
		Taxi taxi = this.taxis.get(taxiABuscar);
		
		if(taxi!=null) {
			String idTaxi = taxi.getTaxiId();
			String company = taxi.getCompany().getName();
			double moneyEarned=0;
			double totalDistanceTraveled = 0;
			int totalTime = 0;
			LinkedList<Service> servicesInRange = new List<>();
			for(Service s: taxi.getServices()) {
				if(((s.getTripStart().compareTo(initialDate) > 0 || s.getTripStart().compareTo(initialDate) == 0) && (s.getTripStart().compareTo(endDate) < 0 || s.getTripStart().compareTo(endDate)==0)) 
						&& ((s.getTripEnd().compareTo(initialDate) > 0 || s.getTripEnd().compareTo(initialDate) == 0) && (s.getTripEnd().compareTo(endDate) < 0 || s.getTripEnd().compareTo(endDate)==0))) {
					moneyEarned += s.getTripTotal();
					totalDistanceTraveled += s.getTripMiles();
					totalTime += s.getTripSeconds();
					servicesInRange.add(s);
				}
			}
			infoTaxi = new InfoTaxiRange(idTaxi, rango, company, moneyEarned, totalDistanceTraveled, totalTime, servicesInRange);
		}else {
			System.out.println("No se encontro el taxi");
		}
		
		return infoTaxi;
	}


	@Override
	public LinkedList<DistanceRange> darListaRangosDistancia(String fecha, String horaInicial, String horaFinal) {
		// TODO Auto-generated method stub
		
		LocalDate date = Utils.obtainLocalDateObject(fecha);
		LocalTime initialTime = Utils.obtainLocalTimeObject(horaInicial);
		LocalTime endTime = Utils.obtainLocalTimeObject(horaFinal);
		
		DistanceRange distanceRange;
		DistanceRange aux;
		LinkedList<DistanceRange> distanceRangeList = new List<>();
		double miles;
		//LocalDate date = LocalDate.of
		for(Service s:this.services){
			if((s.getTripStart().toLocalDate().isEqual(date) && s.getTripEnd().toLocalDate().isEqual(date))&& (s.getTripStart().toLocalTime().compareTo(initialTime) == 0 || s.getTripStart().toLocalTime().compareTo(initialTime)>0)&&(s.getTripEnd().toLocalTime().compareTo(endTime) == 0 || s.getTripEnd().toLocalTime().compareTo(endTime)<0)){
				
				miles = s.getTripMiles();
				System.out.println();
				System.out.println("Miles: "+miles);
				int roundedMiles = (int) Math.round(miles);
				double nextDown = Math.floor(miles);
				double nextUp = Math.ceil(miles);
				
				System.out.println("Next down: "+nextDown);
				System.out.println("Next up: "+nextUp);
				System.out.println("Rounded miles: "+roundedMiles);
				if(miles == nextUp) {
					nextUp = miles+1;
				}
				distanceRange = new DistanceRange(nextUp, nextDown);
				aux = distanceRangeList.get(distanceRange);
				if(aux == null){
					distanceRangeList.addInOrder(distanceRange);
					distanceRange.addService(s);
				}else {
					distanceRange = aux;
					distanceRange.addService(s);
				}
			}
		}
		
		
		return distanceRangeList;
	}


	@Override
	public LinkedList<Company> darCompaniasTaxisInscritos() {
		// TODO Auto-generated method stub
		LinkedList <Company> companyList = new List<>();
		
		for (Company c: this.companies)
		{
			if (c.getTaxis().size()>0)
			{
				companyList.addInOrder(c);
			}
		}
		
		return companyList;
	}


	@Override
	public Taxi darTaxiMayorFacturacion(DateTimeRange rango, String nomCompania) {
		// TODO Auto-generated method stub
		Company comp;
		Company compParam = new Company (nomCompania);
		Taxi taxi = null;
		
		float mayor = 0;
		float contFareServices=0;
		
		LocalDateTime array[] = Utils.convertDateTimeRangeToLocalDateTimeArray(rango);
		LocalDateTime initialDate = array[0];
		LocalDateTime endDate = array[1];
		comp = this.companies.get(compParam);
		
		if (comp != null)
		{
			for (Taxi t:comp.getTaxis())
			{
				
				//System.out.println("Taxi ID :"+t.getTaxiId());
				for(Service s:t.getServices()) {
					if(((s.getTripStart().compareTo(initialDate) > 0 || s.getTripStart().compareTo(initialDate) == 0) && (s.getTripStart().compareTo(endDate) < 0 || s.getTripStart().compareTo(endDate)==0)) 
							&& ((s.getTripEnd().compareTo(initialDate) > 0 || s.getTripEnd().compareTo(initialDate) == 0) && (s.getTripEnd().compareTo(endDate) < 0 || s.getTripEnd().compareTo(endDate)==0))) {
						contFareServices+=s.getFare();	
						
					}
				}
				//System.out.println("\t\tContador cuenta servicios: "+ contFareServices);
				if(contFareServices > mayor) {
					mayor = contFareServices;
					taxi = t;
				}
		
			
			//Check if it is better to create a new Taxi object with the number of services between the range received as a parameter..
			}
		}
		else 
			{
				System.out.println("No se encontro la compa√±ia");
			}
		System.out.println();
		System.out.println("Taxi con mayor facturacion: "+ taxi.getTaxiId()+ "\t\t Facturacion total: "+mayor);
		System.out.println();
		return taxi;
	}


	@Override
	public ServicesValuePayed[] darServiciosZonaValorTotal(DateTimeRange rango, String idZona) {
		// TODO Auto-generated method stub
		ServicesValuePayed[] zonaServ = new ServicesValuePayed[3];
		List<Service> zonaRecogido = new List<Service>();
		List<Service> zonaRecogidoYTer = new List<Service>();
		List<Service> zonaRecogidoEnOtra = new List<Service>();
		
		LocalDateTime array[] = Utils.convertDateTimeRangeToLocalDateTimeArray(rango);
		LocalDateTime initialDate = array[0];
		LocalDateTime endDate = array[1];
		
		
		float valorTotalPagadoR =0;
		
		float valorTotalPagadoRT = 0;
		
		float valorTotal = 0;
		
		int codArea = Integer.parseInt(idZona);
		
		if (services.size()!=0)
		{
			for (Service s: this.services)
			{
				if (s.getPickupCommunityArea()==codArea)
				{
					if (s.getDropoffCommunityArea()!=codArea)
					{
						if(((s.getTripStart().compareTo(initialDate) > 0 || s.getTripStart().compareTo(initialDate) == 0) && (s.getTripStart().compareTo(endDate) < 0 || s.getTripStart().compareTo(endDate)==0)) 
								&& ((s.getTripEnd().compareTo(initialDate) > 0 || s.getTripEnd().compareTo(initialDate) == 0) && (s.getTripEnd().compareTo(endDate) < 0 || s.getTripEnd().compareTo(endDate)==0)))
						{
							
							
							zonaRecogido.addInOrder(s);
							valorTotalPagadoR+=s.getFare();
							
							
						}
					}
					
					if(s.getDropoffCommunityArea()==codArea)
					{
						if(((s.getTripStart().compareTo(initialDate) > 0 || s.getTripStart().compareTo(initialDate) == 0) && (s.getTripStart().compareTo(endDate) < 0 || s.getTripStart().compareTo(endDate)==0)) 
								&& ((s.getTripEnd().compareTo(initialDate) > 0 || s.getTripEnd().compareTo(initialDate) == 0) && (s.getTripEnd().compareTo(endDate) < 0 || s.getTripEnd().compareTo(endDate)==0)))
						{
							zonaRecogidoYTer.addInOrder(s);
							valorTotalPagadoRT += s.getFare();
						}
					}
					
				}
				
				if (s.getPickupCommunityArea()!=codArea)
				{
					if (s.getPickupCommunityArea()==codArea)
					{
						if(((s.getTripStart().compareTo(initialDate) > 0 || s.getTripStart().compareTo(initialDate) == 0) && (s.getTripStart().compareTo(endDate) < 0 || s.getTripStart().compareTo(endDate)==0)) 
								&& ((s.getTripEnd().compareTo(initialDate) > 0 || s.getTripEnd().compareTo(initialDate) == 0) && (s.getTripEnd().compareTo(endDate) < 0 || s.getTripEnd().compareTo(endDate)==0)))
						{
							zonaRecogidoEnOtra.addInOrder(s);
							valorTotal+=s.getFare();
						}
					}
				}
				
			}
			zonaServ[0] = new ServicesValuePayed(valorTotalPagadoR);
			zonaServ[0].setAssociatedServices(zonaRecogido);
			
			zonaServ[1] = new ServicesValuePayed(valorTotalPagadoRT);
			zonaServ[1].setAssociatedServices(zonaRecogidoYTer);
			
			zonaServ[2] = new ServicesValuePayed(valorTotal);
			zonaServ[2].setAssociatedServices(zonaRecogidoEnOtra);
			
		}
	
		
		
		System.out.println("Valor total de servicios recogidos en la zona y terminados en otra: "+zonaServ[0].getAccumulatedValue()+"\nLa lista de servicios en esa zona, en ese rango de tiempo es: ");
		for(Service s: zonaServ[0].getAssociatedServices())
		{
			if(zonaServ[0].getAssociatedServices().size()!=0)
			{
			System.out.println(s.getTripId());
			}
			else
			{
				System.out.println("No hay servicios");
			}
		}
		
		System.out.println("Valor total de servicios recogidos y terminados en la zona: "+zonaServ[1].getAccumulatedValue()+"\nLa lista de servicios en esa zona, en ese rango de tiempo es: ");
		for(Service s: zonaServ[1].getAssociatedServices())
		{
			if(zonaServ[1].getAssociatedServices().size()!=0)
			{
			System.out.println(s.getTripId());
			}
			else
			{
			System.out.println("No hay servicios");
			}
		}
		
		System.out.println("Valor total de servicios recogidos en otra zona y terminados en la zona buscada: "+zonaServ[2].getAccumulatedValue()+"\nLa lista de servicios en esa zona, en ese rango de tiempo es: ");
		for(Service s: zonaServ[2].getAssociatedServices())
		{
			if(zonaServ[2].getAssociatedServices().size()!=0)
			{
			System.out.println(s.getTripId());
			}
			else
			{
			System.out.println("No hay servicios");
			}
		}
		
		return zonaServ;
	}


	@Override
	public LinkedList<CommunityAreaByDateRange> darZonasServicios(DateTimeRange rango)  {
		// TODO Auto-generated method stub
		LinkedList <CommunityAreaByDateRange> comArea = new List<>();
		
		LocalDateTime array[] = Utils.convertDateTimeRangeToLocalDateTimeArray(rango);
		LocalDateTime initialDate = array[0];
		LocalDateTime endDate = array[1];
		
		CommunityAreaByDateRange comAreaId;
		CommunityAreaByDateRange aux = null;
		
		List <Service> listaS= new List <Service>();
		
		int numServicios=0;
		
		for (Service s: this.services)
		{
			comAreaId = new CommunityAreaByDateRange(s.getDropoffCommunityArea());
			
			
			if (comArea.size()==0)
			{
				comArea.addInOrder(comAreaId);
			}
			else 
			{
				for (CommunityAreaByDateRange c: comArea)
				{
					aux = comArea.get(comAreaId);
				
					if (aux == null)
					{
						comArea.addInOrder(comAreaId);
						if(((s.getTripStart().compareTo(initialDate) > 0 || s.getTripStart().compareTo(initialDate) == 0) && (s.getTripStart().compareTo(endDate) < 0 || s.getTripStart().compareTo(endDate)==0)) 
								&& ((s.getTripEnd().compareTo(initialDate) > 0 || s.getTripEnd().compareTo(initialDate) == 0) && (s.getTripEnd().compareTo(endDate) < 0 || s.getTripEnd().compareTo(endDate)==0)))
						{
							numServicios++;
							ServicesByDate ss = new ServicesByDate(initialDate, numServicios);
							listaS.addInOrder(s);
							ss.setAssociatedServices(listaS);
							comAreaId.getServicesDates().addInOrder(ss);
						}
						System.out.println("El id de esta ·rea es: "+comAreaId.getIdCommunityArea());
						System.out.println("La lista de servicios de esta ·rea (en el rango de horas buscado) es: ");
						for (ServicesByDate sss: comAreaId.getServicesDates())
						{
							
							if (sss.getNumServices()!=0)
							{
								for (Service ssss: sss.getAssociatedServices())
								{
									System.out.println(ssss.getTripId());
								}
							}
							else
							{
								System.out.println("Esta ·rea no cuenta con servicios en el rango de horas buscado.");
							}
						}
					
					}
					else 
					{
						comAreaId = aux;
					}
	
				}
			}

		}
		
		
		
		return comArea;
	}


	@Override
	public LinkedList<CompanyByDateRange> companiasMasServicios(DateTimeRange rango, int n) {
		// TODO Auto-generated method stub
		
		//Check if its necesarry to associate companies to their services list directly for a better algorithm
		int contServicios=0;
		LocalDateTime array[] = Utils.convertDateTimeRangeToLocalDateTimeArray(rango);
		LocalDateTime initialDate = array[0];
		LocalDateTime endDate = array[1];
		
		CompanyByDateRange companyByDate;
		LinkedList<CompanyByDateRange> companyByDateList = new List<CompanyByDateRange>();
		LinkedList<CompanyByDateRange> sortedList = new List<>();
		
		for(Company c:this.companies) {
			companyByDate = new CompanyByDateRange(c.getName());
			companyByDateList.add(companyByDate);
			for(Taxi t:c.getTaxis()) {
				for(Service s: t.getServices()) {
					if(((s.getTripStart().compareTo(initialDate) > 0 || s.getTripStart().compareTo(initialDate) == 0) && (s.getTripStart().compareTo(endDate) < 0 || s.getTripStart().compareTo(endDate)==0)) 
							&& ((s.getTripEnd().compareTo(initialDate) > 0 || s.getTripEnd().compareTo(initialDate) == 0) && (s.getTripEnd().compareTo(endDate) < 0 || s.getTripEnd().compareTo(endDate)==0))){
						companyByDate.addService(s);
					}
				}
			}
		}
		CompanyByDateRange[] arrayCompanyByDate = new CompanyByDateRange[companyByDateList.size()];
		for(int i=0;i<arrayCompanyByDate.length;i++) {
			arrayCompanyByDate[i] = companyByDateList.get(i);
		}
		System.out.println();
		Comparator comparator = new CompanyByDateRange.NumberOfServicesReverseComparator();
		Insertion.sort(arrayCompanyByDate, comparator); //I use Insertion sort, check later if another soprting algorithm works better.
		for(int i=0;i<n;i++) { //Fill up the list with CompanyByDateRange objects up to the "top n" number received as a parameter
			sortedList.add(arrayCompanyByDate[i]);
		}
		return sortedList;
	}


	@Override
	public LinkedList<CompanyTaxi> taxisMasRentables() {
		// TODO Auto-generated method stub
		double totalMoneyEarned;
		double totalDistanceTraveled;
		double relation;
		double greatestRelation=0;
		
		LinkedList<CompanyTaxi> companyTaxiList = new List<CompanyTaxi>();
		CompanyTaxi companyTaxi=null;
		for(Company c: this.companies) {
			greatestRelation = 0;
			for(Taxi t: c.getTaxis()) {
				totalMoneyEarned = 0;
				totalDistanceTraveled = 0;
				for(Service s:t.getServices()) {
					totalMoneyEarned += s.getTripTotal();
					totalDistanceTraveled += s.getTripMiles();
				}
				relation = totalMoneyEarned/totalDistanceTraveled;
				if(relation > greatestRelation) {
					greatestRelation = relation;
					companyTaxi = new CompanyTaxi(c.getName(), t);
				}
			}
			companyTaxiList.add(companyTaxi);
			
		}

		return companyTaxiList;
	}


	@Override
	public IStack<Service> darServicioResumen(String taxiId, String horaInicial, String horaFinal, String fecha, int maxMillas) {
		// TODO Auto-generated method stub
		
		LocalDate date = Utils.obtainLocalDateObject(fecha);
		LocalTime initialTime = Utils.obtainLocalTimeObject(horaInicial);
		LocalTime endTime = Utils.obtainLocalTimeObject(horaFinal);
		
		LinkedList<Service> listaAux = new List<>();
		IStack<Service> stack = new Stack<>();
		IStack<Service> stackCompressedServices = new Stack<>();
		Comparator<Service> comparator = new Service.TripStartReverseComparator();
		
		double contMillas = 0;
		int idCompressedService = 0;
		Service auxService = null;
		Service compressedService;
		
		LocalDateTime menorTripStart;
		LocalDateTime mayorTripEnd;
		double sumatoriaDistancia;
		int sumatoriaTripSeconds;
		double sumatoriaTripTotal;
		
		Taxi taxiAux = new Taxi(taxiId);
		Taxi taxi = this.taxis.get(taxiAux); //I search for the taxi in the taxis list.
		

		for(Service s:taxi.getServices()) {
			if((s.getTripStart().toLocalDate().equals(date) && s.getTripEnd().toLocalDate().equals(date)) && (s.getTripStart().toLocalTime().compareTo(initialTime) == 0 || s.getTripStart().toLocalTime().compareTo(initialTime)>0)
					&&(s.getTripEnd().toLocalTime().compareTo(endTime) == 0 || s.getTripEnd().toLocalTime().compareTo(endTime)<0)) {
				listaAux.add(s, comparator);
			}
		}


		for(Service s:listaAux) {
			stack.push(s);
			contMillas += s.getTripMiles();
			if(contMillas >= maxMillas) {
				menorTripStart = LocalDateTime.now();
				mayorTripEnd = LocalDateTime.of(2000, 11, 11, 11, 30, 0, 0);
				sumatoriaDistancia = 0;
				sumatoriaTripSeconds = 0;
				sumatoriaTripTotal = 0;
				while(contMillas > 0) { //Check why its no geater than the N amount
					auxService = stack.pop();
					System.out.println("contadorMillas: "+ contMillas);
					contMillas -= auxService.getTripMiles();
					
					if(s.getTripStart().isBefore(menorTripStart)) {
						menorTripStart = s.getTripStart();
					}
					if(s.getTripEnd().isAfter(mayorTripEnd)) {
						mayorTripEnd = s.getTripEnd();
					}
					sumatoriaDistancia += auxService.getTripMiles();
					sumatoriaTripSeconds += auxService.getTripSeconds();
					sumatoriaTripTotal += auxService.getTripTotal();
					
				}
				compressedService = new Service("Compressed Service "+idCompressedService, s.getCompany(), 0, 0, "N/A", 0, 0, mayorTripEnd, menorTripStart, 0, 0, sumatoriaTripSeconds, sumatoriaDistancia, sumatoriaTripTotal);
				compressedService.setTaxi(taxi);
				stackCompressedServices.push(compressedService);
				idCompressedService++;
			}
		}
		while(!stackCompressedServices.isEmpty()) {
			stack.push(stackCompressedServices.pop());
		}
		listaAux = new List<Service>();
		while(!stack.isEmpty()) {
			listaAux.add(stack.pop(), comparator);
		}
		for(Service s: listaAux) {
			stack.push(s);
		}
		
		return stack;
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
		//this.services.addInOrder(service);//Not a good idea to add them in order.
		this.services.add(service);
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
