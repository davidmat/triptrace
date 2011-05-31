/**
 * 
 */
package test;

/**
 * @author David Mat, Daan Willems
 *
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;

import storage.*;
import gui.*;

public class TripTraceWindows {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
	        UIManager.setLookAndFeel(
	        		 "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	    } catch (Exception e) { }

		
		MainGUI trace = new MainGUI();
		
		trace.setVisible(true);
		
		trace.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BufferedImage loadImg = null;
		try {
			loadImg = ImageIO.read(new File("res/map.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		trace.setIconImage(loadImg);

	}

}
