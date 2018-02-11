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
import model.data_structures.LinkedList;
import model.data_structures.List;
import model.world.Service;
import model.world.Taxi;

public class TaxiTripsManager implements ITaxiTripsManager {

	// TODO
	// Definition of data model 

	private List<Service> services;


	@Override
	public void loadWorld(String serviceFile) {
		// TODO Auto-generated method stub
		JSONParser parser = new JSONParser();

		try {
			Object obj = parser.parse(new FileReader(serviceFile));

			JSONArray jsonArray = (JSONArray)obj;
			JSONObject jsonObject;

			String aux;

			String company;
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
			Iterator<JSONObject> iterator = jsonArray.iterator();
			while(iterator.hasNext()) {
				jsonObject = (JSONObject) iterator.next();
				company = (String) jsonObject.get("company");
				System.out.println("Company: "+company);

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
				// Add pickup_centroid_location
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
	}

	public void loadServices (String serviceFile) {
		// TODO Auto-generated method stub
		System.out.println("Inside loadServices with " + serviceFile);

		this.services = new List<>();

		JSONParser parser = new JSONParser();
		JSONObject jsonObject;
		String aux;
		Service service;
		String tripId;
		String taxiId;
		String company;
		int dropoffCommunityArea;
		int pickupCommunityArea;
		int tripSeconds;
		double tripMiles;
		double tripTotal;

		try {
			Object obj = parser.parse(new FileReader(serviceFile));
			JSONArray jsonArray = (JSONArray) obj;
			Iterator<JSONObject> iterator = jsonArray.iterator();
			while(iterator.hasNext()) {
				jsonObject = (JSONObject)iterator.next();
				tripId = (String) jsonObject.get("trip_id");
				taxiId = (String) jsonObject.get("taxi_id");
				company = (String) jsonObject.get("company");
				aux = (String) jsonObject.get("dropoff_community_area");
				if(aux!= null) {
					dropoffCommunityArea = Integer.parseInt(aux);
				}else {
					dropoffCommunityArea = 0;
				}
				aux = (String) jsonObject.get("pickup_community_area");
				if(aux!=null) {
					pickupCommunityArea = Integer.parseInt(aux);
				}else {
					pickupCommunityArea = 0;
				}
				aux = (String) jsonObject.get("trip_seconds");
				tripSeconds = Integer.parseInt(aux);
				aux = (String) jsonObject.get("trip_miles");
				tripMiles = Double.parseDouble(aux);
				aux = (String) jsonObject.get("trip_total");
				tripTotal = Double.parseDouble(aux);
				service = new Service(tripId, taxiId, company,dropoffCommunityArea, pickupCommunityArea,tripSeconds, tripMiles, tripTotal);
				this.services.add(service);
			}
			Iterator it = services.iterator();
			while(it.hasNext()) {
				System.out.println(it.next().toString());
				System.out.println();
			}
			this.services = services;
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ParseException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}

	}

	@Override
	public LinkedList<Taxi> getTaxisOfCompany(String company) {
		// TODO Auto-generated method stub
		System.out.println("Inside getTaxisOfCompany with " + company);
		List<Taxi> taxis = new List<>();
		Taxi taxi;
		String taxiId;
		String comp;
		Service service;
		Iterator<Service> iterator = this.services.iterator();
		while(iterator.hasNext()) {
			service = iterator.next();
			comp = service.getCompany();

			if(comp!=null) {
				if(comp.equals(company)) {
					taxiId = service.getTaxiId();
					taxi = new Taxi(taxiId, company);
					if(taxis.get(taxi) == null) {
						taxis.add(taxi);
					}

				}
			}
		}
		return taxis;
	}

	@Override
	public LinkedList<Service> getTaxiServicesToCommunityArea(int communityArea) {
		// TODO Auto-generated method stub
		System.out.println("Inside getTaxiServicesToCommunityArea with " + communityArea);
		List<Service> taxiServicesToCommunityArea = new List<>();
		Iterator<Service> it = this.services.iterator();
		Service service;
		while(it.hasNext()) {
			service = it.next();
			if(service.getDropoffCommunityArea() == communityArea) {
				taxiServicesToCommunityArea.add(service);
			}
		}
		return taxiServicesToCommunityArea;
	}




}
