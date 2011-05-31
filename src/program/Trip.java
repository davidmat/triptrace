package program;

public class Trip {
	private String trip_name = "default";
	private java.sql.Date start_date = new java.sql.Date(new java.util.Date().getTime());
	private java.sql.Date end_date = new java.sql.Date(new java.util.Date().getTime());
	private String location = "none";
	private Picture picture = new Picture("res/trip.png");
	private String description = "none";
	
   /** 
    * Trip constructor.
    *
    * @param trip_name the name of the trip
    */

	public Trip(String trip_name) {
		this.trip_name = trip_name;
	}

   /** 
    * Returns the description of the selected trip
    *
    * @return description the description of the trip
    */

	public String getDescription() {
		return description;
	}

   /** 
    * Sets the description of a certain trip
    *
    * @param description the description of the trip
    */

	public void setDescription(String description) {
		this.description = description;
	}

   /** 
    * Returns the end date of the selected trip
    *
    * @return end_date the end date of the trip
    */

	public java.sql.Date getEnd_date() {
		return end_date;
	}

   /** 
    * Sets the end date of a certain trip
    *
    * @param end_date the end date of the trip
    */

	public void setEnd_date(java.sql.Date end_date) {
		this.end_date = end_date;
	}

   /** 
    * Returns the location of the selected trip
    *
    * @return location the location of the trip
    */

	public String getLocation() {
		return location;
	}

   /** 
    * Sets the location of a certain trip
    *
    * @param location the location of the trip
    */

	public void setLocation(String location) {
		this.location = location;
	}

   /** 
    * Returns the picture of the selected trip
    *
    * @return picture the picture of the trip
    */

	public Picture getPicture() {
		return picture;
	}

   /** 
    * Sets the picture of a certain trip
    *
    * @param picture the picture of the trip
    */

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

   /** 
    * Returns the start date of the selected trip
    *
    * @return start_date the start date of the trip
    */

	public java.sql.Date getStart_date() {
		return start_date;
	}

   /** 
    * Sets the start date of a certain trip
    *
    * @param start_date the start date of the trip
    */

	public void setStart_date(java.sql.Date start_date) {
		this.start_date = start_date;
	}

   /** 
    * Returns the name of the selected trip
    *
    * @return trip_name the name of the trip
    */

	public String getTrip_name() {
		return trip_name;
	}

   /** 
    * Sets the name of a certain trip
    *
    * @param trip_name the name of the trip
    */

	public void setTrip_name(String trip_name) {
		this.trip_name = trip_name;
	}
	
   /** 
    * Overrides the toString method and returns the name of the selected trip
    *
    * @return trip_name the name of the trip
    */

	@Override
	public String toString(){
		
		return trip_name;
	}
	
	

	
}
