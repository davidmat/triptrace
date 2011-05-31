

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

public class PictureMapper extends Mapper{
	// Implementation of hidden cache
	
	// Singleton:
	private static PictureMapper uniqueInstance;


	/**
	 * Private constructor for singleton
	 */
	private PictureMapper() {
	}
	
	/**
	 * Sets up the unique instance of the Singleton
	 * @return The unique instance
	 */
	public static PictureMapper getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new PictureMapper();
		}
		return uniqueInstance;
	}

	
	/**
	 * Gets a picture from the database by the location
	 * @param filename The location of the picture			
	 * @return The desired picture
	 */
	public PersistentPicture getPicture(String filename) {
		PersistentPicture pt = null;
		String select = "SELECT * FROM pictures WHERE filename = ?";
		try {
			PreparedStatement pstmt = Database.getInstance().getConnection().prepareStatement(select);
			pstmt.setString(1, filename);
			pt = this.queryPicture(pstmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pt;
	}
	
	
	/**
	 * Gets a picture from the database by his object ID
	 * @param oid The object ID of the desired picture	
	 * @return The desired picture
	 */
	public PersistentPicture getPictureForOid(int pic_id) {
		PersistentPicture pt = queryPicture("SELECT * FROM pictures WHERE pic_id ="
				+ pic_id);
		return pt;
	}
	
	

	/**
	 * Executes an SQL query and formats the result set in a Customer object
	 * @param sql The SQL query to execute
	 * @return The desired customer
	 */
	private PersistentPicture queryPicture(String sql) {
		PersistentPicture pt = null;
		try {
			/* Get the database connection from the current instance in the database 
			 * class. For this connection create a new statement 
			 */ 
			Statement stmt = Database.getInstance().getConnection().createStatement();
			ResultSet rset = stmt.executeQuery(sql);			// Execute a query in the statement
			if (rset.next()) {								// Load the result set
				int pic_id = rset.getInt(1);
				String filename = rset.getString(2);
	
				int trip = rset.getInt(3);
				Date date = rset.getDate(4);
				String location = rset.getString(5);
				double latitude = rset.getDouble(6);
				double longitude = rset.getDouble(7);
				String description = rset.getString(8);
				
				pt = new PersistentPicture(pic_id, filename); // Create a new object from the data
				pt.setDate(date);
				pt.setLocationLat(latitude);
				pt.setLocationLong(longitude);
				pt.setLocationName(location);
				pt.setDescription(description);
				pt.setTrip(TripMapper.getInstance().getTripForOid(trip));
				
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
	private PersistentPicture queryPicture(PreparedStatement pstmt) {
		PersistentPicture pt = null;
		try {
			ResultSet rset = pstmt.executeQuery();			// Execute the statement
			if (rset.next()) {								// Load the result set
				int pic_id = rset.getInt(1);
				String filename = rset.getString(2);
				int trip = rset.getInt(3);
				Date date = rset.getDate(4);
				String location = rset.getString(5);
				double latitude = rset.getDouble(6);
				double longitude = rset.getDouble(7);
				String description = rset.getString(7);
				
				pt = new PersistentPicture(pic_id, filename); // Create a new object from the data
				pt.setDate(date);
				pt.setLocationLat(latitude);
				pt.setLocationLong(longitude);
				pt.setLocationName(location);
				pt.setDescription(description);
				pt.setTrip(TripMapper.getInstance().getTripForOid(trip));
				
			}
			rset.close();		// close the result set
			pstmt.close();		// close the statement
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pt;
	}
	
	/**
	 * Gets all picture names
	 * @return A Collection of all picture names
	 */
	public List<String> getPictureNames() {
		List<String> v = new LinkedList<String>();
		try {
			Statement stmt = Database.getInstance().getConnection().createStatement();
			ResultSet rset = stmt
			.executeQuery("SELECT filename FROM pictures ORDER BY filename");
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
	 * Gets all picture names
	 * @return A Collection of all picture names
	 */
	public List<String> getPicturesForTrip(PersistentTrip trip) {
		List<String> v = new LinkedList<String>();
		try {
			Statement stmt = Database.getInstance().getConnection().createStatement();
			ResultSet rset = stmt.executeQuery("SELECT filename FROM pictures WHERE trip=" + trip.getId());
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
	 * Gets all picture ids
	 * @return A Collection of all picture ids
	 */
	public List<Integer> getPictureIds() {
		List<Integer> v = new LinkedList<Integer>();
		try {
			Statement stmt = Database.getInstance().getConnection().createStatement();
			ResultSet rset = stmt
			.executeQuery("SELECT pic_id FROM pictures ORDER BY pic_id");
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
	 * Stores a picture in the database
	 * @param The picture object that needs to be stored
	 * @return The newly stored picture
	 */
	public PersistentPicture createPicture(Picture picture) {
		performUpdate("INSERT INTO pictures " +
				"(filename, trip, date, location, latitude, longitude, description) " +
				"VALUES ('"
				+ picture.getFileName() + "', " 
				+ picture.getTrip().getId() + ", {d '"
 				+ picture.getDate().toString() + "'}, '" 
				+ picture.getLocationName() + "', " 
				+ picture.getLocationLat() + ", " 
				+ picture.getLocationLong() + ", '" 
				+ picture.getDescription() + "')");
		
		PersistentPicture pt = new PersistentPicture(this.getLastUpdateKey(), picture.getFileName());
		pt.setDate(picture.getDate());
		pt.setLocationLat(picture.getLocationLat());
		pt.setLocationLong(picture.getLocationLong());
		pt.setLocationName(picture.getLocationName());
		pt.setDescription(picture.getDescription());
		
		return pt;
			
		
	}
	
	/**
	 * Deletes a picture from the database
	 * @param Picture The picture that needs to be deleted
	 * @return The number of deleted pictures (1 if everything went OK, 0 if the picture was not found)
	 */
	public int deletePicture(PersistentPicture pt) {
		return performUpdate("DELETE FROM pictures WHERE pic_id =" + ((PersistentPicture) pt).getId());
	}
	
	/**
	 * Update a picture in the database
	 * @param pt The picture containing the new data
	 * @return The number of updated pictures (1 if everything went OK, 0 if the picture was not found)
	 */
	public int updatePicture(PersistentPicture pt) {
		StringBuffer sql = new StringBuffer(1000) ;
		
		// Build the SQL statement
		sql.append("UPDATE pictures SET filename = '") ;
		sql.append(pt.getFileName()) ;
		sql.append("', trip = ") ;
		sql.append(pt.getTrip().getId()) ;
		sql.append(", date = {d '") ;
		sql.append(pt.getDate().toString()) ;
		sql.append("' }, location = '") ;
		sql.append(pt.getLocationName()) ;
		sql.append("', latitude = ") ;
		sql.append(pt.getLocationLat()) ;
		sql.append(", longitude = ") ;
		sql.append(pt.getLocationLong()) ;
		sql.append(", description = '") ;
		sql.append(pt.getDescription()) ;
		sql.append("' WHERE pic_id = ") ;
		sql.append(pt.getId()) ;
		
		return performUpdate(sql.toString()) ;
	}
}







