package helper;

import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.Waypoint;

public class WaypointPlus extends Waypoint {
	private String filename = null;
	
	/**
	 * This is one of the constructors
	 *
	 */
	public WaypointPlus() {
		super();
	}

	/**
	 * This is one of the constructors
	 * 
	 * @param GeoPosition
	 *
	 */
	public WaypointPlus(GeoPosition coord) {
		super(coord);
		
	}

	/**
	 * This is one of the constructors
	 *
	 * @param double, double
	 */
	public WaypointPlus(double latitude, double longitude) {
		super(latitude, longitude);
		
	}
	
	/**
	 * This is one of the constructors
	 *
	 *@param GeoPosition, String
	 */
	public WaypointPlus(GeoPosition coord, String filename) {
		super(coord);
		this.filename = filename;
		
	}

	/**
	 * This is one of the constructors
	 * 
	 * @param double, double, String
	 *
	 */
	public WaypointPlus(double latitude, double longitude, String filename) {
		super(latitude, longitude);
		this.filename = filename;
		
	}

	/**
	 * This  returns the filename
	 * 
	 * @return String
	 *
	 */
	public String getFileName() {
		return filename;
	}
	/**
	 * This sets the filename
	 * 
	 * @param String
	 *
	 */
	public void setId(String filename) {
		this.filename = filename;
	}
	
}
