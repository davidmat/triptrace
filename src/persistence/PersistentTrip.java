package persistence ;

import program.Trip;

/**
 * The persistent object of a trip. This extends the trip 
 * object with a unique identifier, so it can be easily handled
 * by the database
 */
public class PersistentTrip extends Trip{
	private int trip_id ;
	
	/**
	 * Constructor to create a new persistent trip
	 * @param id The object ID
	 * @param trip_name th trip name
	 * 
	 **/
	public PersistentTrip(int id, String trip_name) {
		super(trip_name);
		trip_id = id ;
	}
	
	/**
	 * Gets the object ID
	 * @return The object ID
	 */
	public int getId() {
		return trip_id ;
	}
}
