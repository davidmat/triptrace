package test;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import persistence.*;
import program.*;

public class TripTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Trip trip1 = new Trip("Normandië");
		TripMapper tm = TripMapper.getInstance();
		
//		//tm.createTrip(trip1);
//			System.out.println(" getting trips... ");
//		
//		List<String> tripNames = tm.getTripNames();
//			
//			System.out.println(" print triplist...");
//		
//		for (String name: tripNames){
//			System.out.println("naam: " + name);
//		}
			
//			System.out.println("getting trip by name..");
//		
//		PersistentTrip pTrip1 = tm.getTrip(trip1.getTrip_name());
//		
//			System.out.println("updating tripname...");
//		
//		//pTrip1.setTrip_name("Italy 2009");
//		
//		tm.updateTrip(pTrip1);
//		
//			System.out.println("getting triplist again...");
//		
//		tripNames = tm.getTripNames();
//		
//		for (String name: tripNames){
//			System.out.println("naam: " + name);
//		}
//		
//		PersistentTrip toDelete = tm.getTrip("Italy 2009");
//		
//		tm.deleteTrip(toDelete);
		
		
		try {
			DriverManager.getConnection("jdbc:derby:myDB;user=me;password=mine;shutdown=true");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
