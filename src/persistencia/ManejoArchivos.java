package persistencia;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import mundo.SistemaTaxis;

public class ManejoArchivos {

	
	public static void leerArchivo(SistemaTaxis sistema) {
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = parser.parse(new FileReader("datos.json"));
			
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
}
