package helper;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import org.jdesktop.swingx.JXMapViewer;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.jdesktop.swingx.mapviewer.WaypointRenderer;

import persistence.PersistentPicture;
import persistence.PictureMapper;

public class PictureRenderer implements WaypointRenderer {

//	public boolean paintWaypoint(Graphics2D g, JXMapViewer map,
//			WaypointPlus waypoint) {
//		
//		PersistentPicture pic = PictureMapper.getInstance().getPictureForOid(waypoint.getId());
//		ImageIcon img = new ImageIcon(pic.getFileName());
//        ImageIcon scaled = new ImageIcon(img.getImage().getScaledInstance(64, -1,Image.SCALE_SMOOTH ));      
//		BufferedImage bi = convert(scaled.getImage());
//        g.drawImage(bi, null, 0, 0);
//        
//        return true;
//	}
	/**
	 * This paints the waypoints
	 * 
	 * @param Graphics2D, JXMapViewer, Waypoint
	 * @return boolean
	 * 
	 */
	public boolean paintWaypoint(Graphics2D g, JXMapViewer map, Waypoint waypoint) {
		//int id = ((WaypointPlus) waypoint).getId();
		//PersistentPicture pic = PictureMapper.getInstance().getPictureForOid(id);
		ImageIcon img = new ImageIcon(((WaypointPlus) waypoint).getFileName());
        ImageIcon scaled = new ImageIcon(img.getImage().getScaledInstance(64, -1,Image.SCALE_SMOOTH ));      
		BufferedImage bi = convert(scaled.getImage());
        g.drawImage(bi, null, 0, 0);
        
        return true;
	}
	/**
	 * This methods converts an Image to a BufferedImage
	 * 
	 * @param Image
	 * @return BufferedImage
	 */
	private BufferedImage convert(Image im)
    {
       BufferedImage bi = new BufferedImage(im.getWidth(null),im.getHeight(null),BufferedImage.TYPE_INT_RGB);
       Graphics bg = bi.getGraphics();
       bg.drawImage(im, 0, 0, null);
       bg.dispose();
       return bi;
    }

}
