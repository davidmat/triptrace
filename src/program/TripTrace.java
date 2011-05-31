/**
 * 
 */
package program;

/**
 * @author David Mat & Daan Willems
 *
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;


import gui.*;

public class TripTrace {

	/**
	 * Main method where the program starts.
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
	        UIManager.setLookAndFeel(
	        		 UIManager.getSystemLookAndFeelClassName());
	    } catch (Exception e) { }
		
		MainGUI trace = new MainGUI();
		
		BufferedImage loadImg = null;
		
		try {
			loadImg = ImageIO.read(new File("res/map.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		trace.setIconImage(loadImg);
		trace.setVisible(true);
		
		trace.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
