package gui;

import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.mapviewer.DefaultTileFactory;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.TileFactoryInfo;

public class MapKit extends JXMapKit {

	/**
	 * This is the default constructor
	 */
	public MapKit() {
		super();
		DefaultTileFactory tileFactory = new DefaultTileFactory(new TileFactoryInfo(0, 16, 17, 256, true, true, "http://mt.google.com/mt?w=2.43&t=h", "x", "y", "zoom"));
        setTileFactory(tileFactory);
        setCenterPosition(new GeoPosition(50.877586,4.698544));
        setZoom(5);
	}

}
