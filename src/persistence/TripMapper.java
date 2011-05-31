/*
 * Restaurant Booking System: example code to accompany
 *
 * "Practical Object-oriented Design with UML"
 * Mark Priestley
 * McGraw-Hill (2004)
 */

package persistence;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import program.Picture;
import program.Trip;

import storage.Database;

public class TripMapper extends Mapper {
	// Implementation of hidden cache
	
	// Singleton:
	private static TripMapper uniqueInstance;
	

	/**
	 * Private constructor for singleton
	 */
	private TripMapper() {
	}
	
	/**
	 * Sets up the unique instance of the Singleton
	 * @return The unique instance
	 */
	public static TripMapper getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new TripMapper();
		}
		return uniqueInstance;
	}
	
	/**
	 * Gets a customer from the database by the location
	 * @param locationName The location of the trip
	 * @return The desired trip
	 */
	public PersistentTrip getTrip(String trip_name) {
		PersistentTrip pt = null;
		String select = "SELECT * FROM trips WHERE trip_name = ?";
		try {
			PreparedStatement pstmt = Database.getInstance().getConnection().prepareStatement(select);
			pstmt.setString(1, trip_name);
			pt = this.queryTrip(pstmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pt;
	}
	
	/**
	 * Gets a customer from the database by his object ID
	 * @param oid The object ID of the desired customere
	 * @return The desired customer
	 */
	public PersistentTrip getTripForOid(int trip_id) {
		PersistentTrip pt = queryTrip("SELECT * FROM TRIPS WHERE trip_id="+ trip_id);
		return pt;
	}
	

	/**
	 * Executes an SQL query and formats the result set in a Customer object
	 * @param sql The SQL query to execute
	 * @return The desired customer
	 */
	private PersistentTrip queryTrip(String sql) {
		PersistentTrip pt = null;
		try {
			/* Get the database connection from the current instance in the database 
			 * class. For this connection create a new statement 
			 */ 
			Statement stmt = Database.getInstance().getConnection().createStatement();
			ResultSet rset = stmt.executeQuery(sql);			// Execute a query in the statement
			if (rset.next()) {								// Load the result set
				int trip_id = rset.getInt(1);
				String trip_name = rset.getString(2);
				Date start_date = rset.getDate(3);
				Date end_date = rset.getDate(4);
				String location = rset.getString(5);
				Picture picture = new Picture(rset.getString(6)); // TODO get picture from database
				String description = rset.getString(7);
				
				pt = new PersistentTrip(trip_id, trip_name); // Create a new object from the data
				pt.setStart_date(start_date);
				pt.setEnd_date(end_date);
				pt.setLocation(location);
				pt.setPicture(picture);
				pt.setDescription(description);
				
			}
			rset.close();		// close the result set
			stmt.close();		// close the statement
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pt;
	}
	
	/**
	 * Executes an SQL query from a prepared statement and formats the result set in a Customer object
	 * @param pstmt The prepared statement to execute
	 * @return The desired customer
	 */
	private PersistentTrip queryTrip(PreparedStatement pstmt) {
		PersistentTrip pt = null;
		try {
			ResultSet rset = pstmt.executeQuery();			// Execute the statement
			if (rset.next()) {								// Load the result set
				int trip_id = rset.getInt(1);
				String trip_name = rset.getString(2);
				Date start_date = rset.getDate(3);
				Date end_date = rset.getDate(4);
				String location = rset.getString(5);
				Picture picture = new Picture(rset.getString(6));
				String description = rset.getString(7);
				
				pt = new PersistentTrip(trip_id, trip_name); // Create a new object from the data
				pt.setStart_date(start_date);
				pt.setEnd_date(end_date);
				pt.setLocation(location);
				pt.setPicture(picture);
				pt.setDescription(description);
			}
			rset.close();		// close the result set
			pstmt.close();		// close the statement
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pt;
	}
	
	/**
	 * Gets all trip names
	 * @return A Collection of all trip names
	 */
	public List<String> getTripNames() {
		List<String> v = new LinkedList<String>();
		try {
			Statement stmt = Database.getInstance().getConnection().createStatement();
			ResultSet rset = stmt
			.executeQuery("SELECT trip_name FROM trips ORDER BY trip_name");
			while (rset.next()) {
				v.add(rset.getString(1)); // scroll trough the data and fill the collection
			}
			rset.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v;
	}

	
	/**
	 * Gets all trip ids
	 * @return A Collection of all trip ids
	 */
	public List<Integer> getTripIds() {
		List<Integer> v = new LinkedList<Integer>();
		try {
			Statement stmt = Database.getInstance().getConnection().createStatement();
			ResultSet rset = stmt
			.executeQuery("SELECT trip_id FROM trips ORDER BY trip_id");
			while (rset.next()) {
				v.add(rset.getInt(1)); // scroll trough the data and fill the collection
			}
			rset.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return v;
	}
	
	/**
	 * Stores a trip in the database
	 * @param trip The customer object that needs to be stored
	 * @return The newly stored trip
	 */
	public PersistentTrip createTrip(Trip trip) {
		performUpdate("INSERT INTO trips " +
				"(trip_name, start_date, end_date, location, picture, description) " +
				"VALUES ('"
				+ trip.getTrip_name() + "', {d '" 
 				+ trip.getStart_date().toString() + "'}, {d '" 
				+ trip.getEnd_date().toString() + "'}, '" 
				+ trip.getLocation() + "', '" 
				+ trip.getPicture().getFileName() + "', '" 
				+ trip.getDescription() + "')");
		
		PersistentTrip pt = new PersistentTrip(this.getLastUpdateKey(), trip.getTrip_name());
		pt.setStart_date(trip.getStart_date());
		pt.setEnd_date(trip.getEnd_date());
		pt.setLocation(trip.getLocation());
		pt.setPicture(trip.getPicture());
		pt.setDescription(trip.getDescription());
		
		return pt;
			
		
	}
	
	/**
	 * Deletes a trip from the database
	 * @param Trip The trip that needs to be deleted
	 * @return The number of deleted trips (1 if everything went OK, 0 if the trip was not found)
	 */
	public int deleteTrip(PersistentTrip pt) {
		return performUpdate("DELETE FROM trips WHERE trip_id =" + ((PersistentTrip) pt).getId());
	}
	
	/**
	 * Update a trip in the database
	 * @param pt The trip containing the new data
	 * @return The number of updated trips (1 if everything went OK, 0 if the trip was not found)
	 */
	public int updateTrip(PersistentTrip pt) {
		StringBuffer sql = new StringBuffer(1000) ;
		
		// Build the SQL statement
		sql.append("UPDATE trips SET trip_name = '") ;
		sql.append(pt.getTrip_name()) ;
		sql.append("', start_date = {d '") ;
		sql.append(pt.getStart_date().toString()) ;
		sql.append("' }, end_date = {d '") ;
		sql.append(pt.getEnd_date().toString()) ;
		sql.append("' }, location = '") ;
		sql.append(pt.getLocation()) ;
		sql.append("', picture = '") ;
		sql.append(pt.getPicture().getFileName()) ;
		sql.append("', description = '") ;
		sql.append(pt.getDescription()) ;
		sql.append("' WHERE trip_id = ") ;
		sql.append(pt.getId()) ;
		
		return performUpdate(sql.toString()) ;
	}
}
