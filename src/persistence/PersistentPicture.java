package persistence ;

import program.Picture;

/**
 * The persistent object of a picture. This extends the picture 
 * object with a unique identifier, so it can be easily handled
 * by the database
 */
public class PersistentPicture extends Picture{
	private int pic_id;
		
	/**
	 * Constructor to create a new persistent Picture
	 * @param id The object ID
	 * @param filename the file name
	 * 
	 **/
	public PersistentPicture(int id, String filename) {
		super(filename);
		pic_id = id;
	}
	
	/**
	 * Gets the object ID
	 * @return The object ID
	 */
	public int getId() {
		return pic_id ;
	}
}
