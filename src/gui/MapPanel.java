package gui;

import helper.PhotoListRenderer;
import helper.PictureRenderer;
import helper.WaypointPlus;

import java.awt.GridBagLayout;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.HashSet;
import java.util.List;

import javax.swing.JSplitPane;

import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.jdesktop.swingx.mapviewer.WaypointPainter;

import persistence.PersistentPicture;
import persistence.PersistentTrip;
import persistence.PictureMapper;
import persistence.TripMapper;

public class MapPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel jLeftPanel = null;
	private JPanel jRightPanel = null;
	private JScrollPane jTripScrollPane = null;
	private JScrollPane jPicsScrollPane = null;
	private JList jTripList = null;
	private JList jPicList = null;
	private JPanel jButtonPanel = null;
	private JButton jAddLocationButton = null;
	private JButton jGoToButton = null;
	private MapKit JMapKit = null;
	private JSplitPane jSplitPane = null;
	private	DefaultListModel myTripListModel = null;
	private	DefaultListModel myPicListModel = null;
	private HashSet<WaypointPlus> waypoints = null;  //  @jve:decl-index=0:
	private Boolean mapping = false;  //  @jve:decl-index=0:
	
	//  @jve:decl-index=0:
	
	/**
	 * This is the default constructor
	 */
	public MapPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		GridLayout gridLayout = new GridLayout();
		gridLayout.setRows(1);
		this.setLayout(gridLayout);
		this.setSize(591, 379);
		this.add(getJSplitPane(), null);
	}

	/**
	 * This method initializes jLeftPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJLeftPanel() {
		if (jLeftPanel == null) {
			jLeftPanel = new JPanel();
			jLeftPanel.setLayout(new BoxLayout(getJLeftPanel(), BoxLayout.Y_AXIS));
			jLeftPanel.add(getJTripScrollPane(), null);
			jLeftPanel.add(getJPicsScrollPane(), null);
		}
		return jLeftPanel;
	}

	/**
	 * This method initializes jRightPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJRightPanel() {
		if (jRightPanel == null) {
			jRightPanel = new JPanel();
			jRightPanel.setLayout(new BoxLayout(getJRightPanel(), BoxLayout.Y_AXIS));
			jRightPanel.add(getJButtonPanel(), null);
			jRightPanel.add(getJMapKit(), null);
		}
		return jRightPanel;
	}

	/**
	 * This method initializes jTripScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJTripScrollPane() {
		if (jTripScrollPane == null) {
			jTripScrollPane = new JScrollPane();
			jTripScrollPane.setViewportView(getJTripList());
		}
		return jTripScrollPane;
	}

	/**
	 * This method initializes jPicsScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJPicsScrollPane() {
		if (jPicsScrollPane == null) {
			jPicsScrollPane = new JScrollPane();
			jPicsScrollPane.setViewportView(getJPicList());
		}
		return jPicsScrollPane;
	}

	/**
	 * This method initializes jTripList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJTripList() {
		if (jTripList == null) {
			generateTripList();
			jTripList = new JList(getTripListModel());
			jTripList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTripList
					.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
						public void valueChanged(javax.swing.event.ListSelectionEvent e) {
							if(!jTripList.isSelectionEmpty()) {
								generatePicList((PersistentTrip) jTripList.getSelectedValue());
								paintPictures((PersistentTrip) jTripList.getSelectedValue());
							} 
						}
					});
		}
		return jTripList;
	}

	/**
	 * This method initializes jPicList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJPicList() {
		if (jPicList == null) {
			PhotoListRenderer plr = new PhotoListRenderer();
			
			jPicList = new JList(getPicListModel());
			jPicList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jPicList.setCellRenderer(plr);
			jPicList
					.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
						public void valueChanged(javax.swing.event.ListSelectionEvent e) {
							if(!jPicList.isSelectionEmpty()) {
								gotoPicture((PersistentPicture) jPicList.getSelectedValue());
							} 
						}
					});
			jPicList.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if (e.getClickCount()==2 && !jPicList.isSelectionEmpty()){
						ImageIcon img = new ImageIcon(((PersistentPicture) jPicList.getSelectedValue()).getFileName());
				        ImageIcon scaled = new ImageIcon(img.getImage().getScaledInstance(640, -1,Image.SCALE_SMOOTH ));      
				        JOptionPane.showMessageDialog(MapPanel.this,  scaled, "Large size view", JOptionPane.PLAIN_MESSAGE);
						
			        }
				}
			});
		}
		return jPicList;
	}

	

	/**
	 * This method initializes jButtonPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJButtonPanel() {
		if (jButtonPanel == null) {
			jButtonPanel = new JPanel();
			jButtonPanel.setLayout(new FlowLayout());
			jButtonPanel.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
			jButtonPanel.add(getJAddLocationButton(), null);
			jButtonPanel.add(getJGoToButton(), null);
		}
		return jButtonPanel;
	}

	/**
	 * This method initializes jAddLocationButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJAddLocationButton() {
		if (jAddLocationButton == null) {
			jAddLocationButton = new JButton();
			jAddLocationButton.setText("Add / change location");
			jAddLocationButton.setEnabled(false);
			jAddLocationButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(!getJPicList().isSelectionEmpty()){
						startLocationMode((PersistentPicture) getJPicList().getSelectedValue());
					}
				}
			});
		}
		return jAddLocationButton;
	}

	

	/**
	 * This method initializes jGoToButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJGoToButton() {
		if (jGoToButton == null) {
			jGoToButton = new JButton();
			jGoToButton.setText("Go to picture location");
			jGoToButton.setEnabled(false);
			jGoToButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(!getJPicList().isSelectionEmpty()){
						PersistentPicture pp = (PersistentPicture) getJPicList().getSelectedValue();
						GeoPosition pos = new GeoPosition(pp.getLocationLat(), pp.getLocationLong());
						getJMapKit().setAddressLocation(pos);
					}
					
				}
			});
		}
		return jGoToButton;
	}

	/**
	 * This method initializes JMapKit	
	 * 	
	 * @return gui.MapKit	
	 */
	private MapKit getJMapKit() {
		if (JMapKit == null) {
			JMapKit = new MapKit();
			JMapKit.getMainMap().addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2 && mapping == true  && !getJPicList().isSelectionEmpty()){
						int n= JOptionPane.showConfirmDialog(null, 
		                		"Assign this location to the picture?", "Add location", 
		                        JOptionPane.YES_NO_OPTION,
		                        JOptionPane.QUESTION_MESSAGE);
		                if(n==0){ // means "YESSIREE"
		                	// Get the picture
		                	PersistentPicture pp = (PersistentPicture) getJPicList().getSelectedValue();
		                	PersistentPicture localPic = PictureMapper.getInstance().getPictureForOid(pp.getId());
		                    // Set the coords
		                	double lat = getJMapKit().getMainMap().convertPointToGeoPosition(e.getPoint()).getLatitude(); 
		                	double lon = getJMapKit().getMainMap().convertPointToGeoPosition(e.getPoint()).getLongitude();
		                	System.out.println("latitude from event: " + lat);
		                	System.out.println("longitude from event: "+ lon);
		                	localPic.setLocationLat(lat); 
		                    localPic.setLocationLong(lon);
		                    System.out.println("latitude from local pp: " + localPic.getLocationLat());
		                	System.out.println("longitude from local pp: "+ localPic.getLocationLong());
		                    // update in db
		                    PictureMapper.getInstance().updatePicture(localPic);
		                    System.out.println("latitude from db: " + PictureMapper.getInstance().getPictureForOid(localPic.getId()).getLocationLat());
		                	System.out.println("longitude from db: "+ PictureMapper.getInstance().getPictureForOid(localPic.getId()).getLocationLong());
		                    // stop mapping mode
		                    mapping = false;
		                    // paint the pictures again
		                    PersistentTrip pt = (PersistentTrip) getJTripList().getSelectedValue();
		                    PersistentTrip localTrip = TripMapper.getInstance().getTripForOid(pt.getId());
		                    paintPictures(localTrip);
		                    // go to the new picture location
		                    gotoPicture(localPic);
		                    
		                }
		                
		                repaint();
						}
				}
			});
			
		}
		return JMapKit;
	}

	/**
	 * This method initializes jSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JSplitPane getJSplitPane() {
		if (jSplitPane == null) {
			jSplitPane = new JSplitPane();
			jSplitPane.setLeftComponent(getJLeftPanel());
			jSplitPane.setRightComponent(getJRightPanel());
		}
		return jSplitPane;
	}

	/**
	 * This method generates jTripList
	 * 	
	 * @return void
	 */
	private void generateTripList() {
		getTripListModel().clear();
		
		List<String> tripNames = TripMapper.getInstance().getTripNames();
		
		for (String name: tripNames) {
			PersistentTrip LocalTrip = TripMapper.getInstance().getTrip(name);
			getTripListModel().addElement(LocalTrip);
		}

	}
	/**
	 * This method initializes myTripListModel
	 * 	
	 * @return DefaultListModel
	 */
	private DefaultListModel getTripListModel() {
        if (this.myTripListModel == null) {
                this.myTripListModel = new DefaultListModel();
        }
        return this.myTripListModel;
	}
	
	/**
	 * This method generates jPicList
	 * 
	 * @param  PersistentTrip	
	 * @return void
	 */
	private void generatePicList(PersistentTrip trip) {
		getPicListModel().clear();
		
		List<String> fileNames = PictureMapper.getInstance().getPicturesForTrip(trip);
		
		for (String filename: fileNames) {
			PersistentPicture localPic = PictureMapper.getInstance().getPicture(filename);
			getPicListModel().addElement(localPic);
		}

	}
	
	/**
	 * This method initializes myPicsListModel
	 * 	
	 * @return DefaultListModel
	 */
	private DefaultListModel getPicListModel() {
        if (this.myPicListModel == null) {
                this.myPicListModel = new DefaultListModel();
        }
        return this.myPicListModel;
	}
	
	/**
	 * This method moves the map to the correct location
	 * 
	 * @param  PersistentPicture
	 * @return void
	 */
	private void gotoPicture(PersistentPicture pp) {
		if(pp.getLocationLat() ==0 || pp.getLocationLong() ==0){
			enableLocationMode(true);
		} else {
			GeoPosition pos = new GeoPosition(pp.getLocationLat(), pp.getLocationLong());
			enableLocationMode(false);
			getJMapKit().getMainMap().setAddressLocation(pos);
		}
		
	}
	
	/**
	 * This method enables location selection
	 * 
	 * @param  Boolean
	 * @return void
	 */
	private void enableLocationMode(Boolean value){
		if(value){
			getJAddLocationButton().setEnabled(true);
			getJGoToButton().setEnabled(false);
		} else {
			getJAddLocationButton().setEnabled(true);
			getJGoToButton().setEnabled(true);
		}
	}
	
	/**
	 * This method starts location selection for the argument
	 * 
	 * @param  PersistentPicture
	 * @return void
	 */	
	private void startLocationMode(PersistentPicture picture) {
			// First show a dialog
		JOptionPane.showMessageDialog(null, 
        		"Please doubleclick on the map to set the picture location. ", "Add Location",
                JOptionPane.INFORMATION_MESSAGE);
			// enable the mouselistener on the mainmap to accept doubleclicks
			mapping = true;
			// the rest is handled in that mouselistener
			
	}
	/**
	 * This method paints pictures on the map
	 * 
	 * @param  PersistentTrip
	 * @return void
	 */	
	private void paintPictures(PersistentTrip pt){
		if(waypoints == null){
			waypoints  = new HashSet<WaypointPlus>();
		} else {
			waypoints.clear();
		}
		
		// add all the waypoints
		if(pt!=null){
			PersistentTrip localTrip = TripMapper.getInstance().getTripForOid(pt.getId());
			List<String> fileNames = PictureMapper.getInstance().getPicturesForTrip(localTrip);
			
			for (String filename: fileNames) {
				PersistentPicture localPic = PictureMapper.getInstance().getPicture(filename);
				if(localPic.getLocationLat() !=0 && localPic.getLocationLong() !=0){
					waypoints.add(new WaypointPlus(localPic.getLocationLat(),localPic.getLocationLong(), localPic.getFileName()));
				}
			}
		}
		
		// create a WaypointPainter to draw the points
	    WaypointPainter painter = new WaypointPainter();
	    // add the points
	    painter.setWaypoints(waypoints);
	    // set the correct renderer
	    painter.setRenderer(new PictureRenderer());
	    // assign to the map
	    getJMapKit().getMainMap().setOverlayPainter(painter);
		
	}
	
	/**
	 * This method updates the GUI
	 * 
	 * @return void
	 */	
	public void update(){
		generateTripList();
		clear();
		paintPictures(null);
	}
	
	/**
	 * This method clears the GUI
	 * 
	 * @return void
	 */
	private void clear(){
		getPicListModel().clear();
		getJGoToButton().setEnabled(false);
		getJAddLocationButton().setEnabled(false);
		
	}
		
	
	
}  //  @jve:decl-index=0:visual-constraint="10,10"
