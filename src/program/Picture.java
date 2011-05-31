package program;

import persistence.PersistentTrip;
import persistence.TripMapper;

public class Picture {
	private String fileName = "C:\\image.jpg";
	private String locationName = "none";
	private double locationLat = 0.0;
	private double locationLong = 0.0;
	private PersistentTrip trip = null;
	private java.sql.Date date = new java.sql.Date(new java.util.Date().getTime());
	private String Description ="no description";
	

   /** 
    * Picture constructor.
    *
    * @param filename the path of the picture
    */

	public Picture(String fileName){
		this.fileName = fileName;
	}

   /** 
    * Returns the date of the selected picture
    *
    * @return date the date of the picture
    */

	public java.sql.Date getDate() {
		return date;
	}

   /** 
    * Sets the date of a certain picture
    *
    * @param date the date of the picture
    */

	public void setDate(java.sql.Date date) {
		this.date = date;
	}

   /** 
    * Returns the description of the selected picture
    *
    * @return Description the description of the picture
    */

	public String getDescription() {
		return Description;
	}

   /** 
    * Sets the description of a certain picture
    *
    * @param Description the description of the picture
    */

	public void setDescription(String description) {
		Description = description;
	}

   /** 
    * Returns the path of the selected picture
    *
    * @return fileName the path of the picture
    */

	public String getFileName() {
		return fileName;
	}

   /** 
    * Sets the path of a certain picture
    *
    * @param fileName the path of the picture
    */

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

   /** 
    * Returns the Latitude of the selected picture
    *
    * @return locationLat the latitude of the picture
    */

	public double getLocationLat() {
		return locationLat;
	}

   /** 
    * Sets the latitude of a certain picture
    *
    * @param locationLat the latitude of the picture
    */

	public void setLocationLat(double locationLat) {
		this.locationLat = locationLat;
	}

   /** 
    * Returns the longitude of the selected picture
    *
    * @return locationLong the longitude of the picture
    */

	public double getLocationLong() {
		return locationLong;
	}

   /** 
    * Sets the longitude of a certain picture
    *
    * @param locationLong the longitude of the picture
    */

	public void setLocationLong(double locationLong) {
		this.locationLong = locationLong;
	}

   /** 
    * Returns the location of the selected picture
    *
    * @return locationName the location of the picture
    */

	public String getLocationName() {
		return locationName;
	}

   /** 
    * Sets the location of a certain picture
    *
    * @param locationName the location of the picture
    */

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

   /** 
    * Returns the trip of the selected picture, returns NULL if no trip has been added for this picture yet.
    *
    * @return PersistentTrip the trip linked with the picture
    */

	public PersistentTrip getTrip() {
		if (trip==null){
			return null;
		} else {
			return TripMapper.getInstance().getTripForOid(trip.getId());
		}
	}

   /** 
    * Sets the trip of a certain picture
    *
    * @param trip the trip of the picture
    */

	public void setTrip(PersistentTrip trip) {
		this.trip = TripMapper.getInstance().getTripForOid(trip.getId());
	}
	
   /** 
    * Overrides the toString method and returns the description of the selected picture
    *
    * @return Description the description of the picture
    */

	@Override
	public String toString(){
		
		return Description;
	}
	
	
}
