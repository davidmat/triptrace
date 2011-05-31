package gui;

import helper.ImageFilter;
import helper.ImagePreview;

import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.util.List;


import persistence.PersistentTrip;
import persistence.TripMapper;
import program.Picture;
import program.Trip;


import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.KeyEvent;

public class TripManager extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton jAddButton = null;
	private JTextField jNewTripNameField = null;
	private JScrollPane jTripsScrollPane = null;
	private JLabel jTripListLabel = null;
	private JLabel jPropertiesLabel = null;
	private JLabel jNameLabel = null;
	private JTextField jNameField = null;
	private JLabel jStartDateLabel = null;
	private JLabel jEndDateLabel = null;
	private JTextField jStartDateField = null;
	private JTextField jEndDateField = null;
	private JLabel jLocationLabel = null;
	private JButton jSaveButton = null;
	private JButton jResetButton = null;
	private JTextField jLocationField = null;
	private JLabel jPictureLabel = null;
	private JLabel jPictureIconLabel = null;
	private JButton jSetPictureButton = null;
	private JList jTripList = null;
	private JTextPane jDescriptionPane = null;
	private JLabel jDescriptionLabel = null;
	private DefaultListModel myListModel = null;
	private JButton jDeleteButton = null;
	private JPanel jCurrentTripsPanel = null;
	private JPanel jPropertiesPanel = null;
	private JPanel jButtonsPanel = null;
	private JPanel jIconPanel = null;
	private JScrollPane jDescriptionScrollPane = null;
	private JPanel jAddTripsPanel = null;
	/**
	 * This is the default constructor
	 */
	public TripManager() {
		super();
		initialize();
		
		
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		jDescriptionLabel = new JLabel();
		jDescriptionLabel.setText("Description");
		jPictureIconLabel = new JLabel();
		jPictureIconLabel.setText("");
		jPictureIconLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		jPictureIconLabel.setHorizontalAlignment(SwingConstants.CENTER);
		jPictureLabel = new JLabel();
		jPictureLabel.setText("Picture");
		jPictureLabel.setHorizontalAlignment(SwingConstants.CENTER);
		jPictureLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		jPictureLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		jPictureLabel.setVerticalAlignment(SwingConstants.TOP);
		jLocationLabel = new JLabel();
		jLocationLabel.setText("Location");
		jEndDateLabel = new JLabel();
		jEndDateLabel.setText("End date");
		jStartDateLabel = new JLabel();
		jStartDateLabel.setText("Start date");
		jNameLabel = new JLabel();
		jNameLabel.setText("Name");
		jPropertiesLabel = new JLabel();
		jPropertiesLabel.setText("Properties");
		jPropertiesLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		jTripListLabel = new JLabel();
		jTripListLabel.setText("Current Trips");
		jTripListLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		
		this.setLayout(new BorderLayout());
		this.add(getJCurrentTripsPanel(), BorderLayout.WEST);
		this.add(getJPropertiesPanel(), BorderLayout.EAST);
		this.add(getJAddTripsPanel(), BorderLayout.NORTH);
		this.add(getJIconPanel(), BorderLayout.CENTER);
	}

	/**
	 * This method initializes jAddButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJAddButton() {
		if (jAddButton == null) {
			jAddButton = new JButton();
			jAddButton.setText("Add new trip");
			jAddButton.setIcon(new ImageIcon("res/world_add.png"));
			jAddButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					addTrip(jNewTripNameField.getText());
				}
			});
		}
		return jAddButton;
	}

	/**
	 * This method initializes jNewTripNameField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJNewTripNameField() {
		if (jNewTripNameField == null) {
			jNewTripNameField = new JTextField();
			jNewTripNameField.setText("New trip name...");
			jNewTripNameField.setPreferredSize(new Dimension(200, 20));
			jNewTripNameField.addFocusListener(new java.awt.event.FocusAdapter() {
				public void focusGained(java.awt.event.FocusEvent e) {
					jNewTripNameField.setText("");
				}
			});
			jNewTripNameField.addKeyListener(new java.awt.event.KeyAdapter() {
				public void keyPressed(java.awt.event.KeyEvent e) {
					int key = e.getKeyCode();
				    if (key == KeyEvent.VK_ENTER) {
				    	addTrip(jNewTripNameField.getText());
				        }

				}
			});
		}
		return jNewTripNameField;
	}

	/**
	 * This method initializes jTripsScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJTripsScrollPane() {
		if (jTripsScrollPane == null) {
			jTripsScrollPane = new JScrollPane();
			jTripsScrollPane.setViewportView(getJTripList());
		}
		return jTripsScrollPane;
	}

	/**
	 * This method initializes jNameField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJNameField() {
		if (jNameField == null) {
			jNameField = new JTextField();
		}
		return jNameField;
	}

	/**
	 * This method initializes jStartDateField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJStartDateField() {
		if (jStartDateField == null) {
			jStartDateField = new JTextField();
		}
		return jStartDateField;
	}

	/**
	 * This method initializes jEndDateField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJEndDateField() {
		if (jEndDateField == null) {
			jEndDateField = new JTextField();
		}
		return jEndDateField;
	}

	/**
	 * This method initializes jSaveButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJSaveButton() {
		if (jSaveButton == null) {
			jSaveButton = new JButton();
			jSaveButton.setText("Save changes");
			jSaveButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(!jTripList.isSelectionEmpty()) {
						updateProperties((PersistentTrip) jTripList.getSelectedValue());
					} 
				}
			});
		}
		return jSaveButton;
	}

	/**
	 * This method initializes jResetButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJResetButton() {
		if (jResetButton == null) {
			jResetButton = new JButton();
			jResetButton.setText("Reset values");
			jResetButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(!jTripList.isSelectionEmpty()) {
						generateProperties((PersistentTrip) jTripList.getSelectedValue());
					} 
				}
			});
		}
		return jResetButton;
	}

	/**
	 * This method initializes jLocationField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJLocationField() {
		if (jLocationField == null) {
			jLocationField = new JTextField();
		}
		return jLocationField;
	}

	/**
	 * This method initializes jSetPictureButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJSetPictureButton() {
		if (jSetPictureButton == null) {
			jSetPictureButton = new JButton();
			jSetPictureButton.setText("Change picture");
			jSetPictureButton.setEnabled(false);
			jSetPictureButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(!jTripList.isSelectionEmpty()) {
						choosePicture((PersistentTrip) jTripList.getSelectedValue());
					} 
				}
			});
		}
		return jSetPictureButton;
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
			jTripList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
						public void valueChanged(javax.swing.event.ListSelectionEvent e) {
							if(!jTripList.isSelectionEmpty()) {
								generateProperties((PersistentTrip) jTripList.getSelectedValue());
							} 
						}
					});
		}
		return jTripList;
	}

	/**
	 * This method initializes jDescriptionPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getJDescriptionPane() {
		if (jDescriptionPane == null) {
			jDescriptionPane = new JTextPane();
			jDescriptionPane.setPreferredSize(new Dimension(6, 400));
		}
		return jDescriptionPane;
	}
	
	/**
	 * This method initializes jDeleteButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJDeleteButton() {
		if (jDeleteButton == null) {
			jDeleteButton = new JButton();
			jDeleteButton.setText("Delete selected trip");
			jDeleteButton.setPreferredSize(new Dimension(200, 26));
			jDeleteButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(!jTripList.isSelectionEmpty()) {
						deleteTrip((PersistentTrip) jTripList.getSelectedValue());
					} 
				}
			});
		}
		return jDeleteButton;
	}
	/**
	 * This method populates jTripList
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
	 * This method initializes myListModel	
	 * 	
	 * @return DefaultListModel
	 */
	private DefaultListModel getTripListModel() {
        if (this.myListModel == null) {
                this.myListModel = new DefaultListModel();
        }
        return this.myListModel;
	}
	
	/**
	 * This method updates the properties field
	 * 	
	 * @param PersistentTrip
	 * @return void
	 */
	private void generateProperties(PersistentTrip pt){
		getJNameField().setText(pt.getTrip_name());
		getJStartDateField().setText(pt.getStart_date().toString());
		getJEndDateField().setText(pt.getEnd_date().toString());
		getJLocationField().setText(pt.getLocation());
		getJDescriptionPane().setText(pt.getDescription());
		// need to get old picture again from database, 
		// choosePicture affects this PersistentTrip object
		ImageIcon img = new ImageIcon(TripMapper.getInstance().getTripForOid(pt.getId()).getPicture().getFileName());
        ImageIcon scaled = new ImageIcon(img.getImage().getScaledInstance(320, -1,Image.SCALE_SMOOTH ));
		jPictureIconLabel.setIcon(scaled);
		getJSetPictureButton().setEnabled(true);
		
	}
	
	/**
	 * This commits property changes to database
	 * 	
	 * @param PersistentTrip
	 * @return void
	 */
	private void updateProperties(PersistentTrip pt){
		if (checkInputTrip()) {
			pt.setTrip_name(getJNameField().getText());
			pt.setStart_date(java.sql.Date.valueOf(getJStartDateField()
					.getText()));
			pt.setEnd_date(java.sql.Date.valueOf(getJEndDateField().getText()));
			pt.setLocation(getJLocationField().getText());
			// picture already set by 'set button'
			pt.setDescription(getJDescriptionPane().getText());
			TripMapper.getInstance().updateTrip(pt);
			generateProperties(pt);
			generateTripList();
		}		
	}
	
	/**
	 * This method allows you to add a trip
	 * 	
	 * @param String
	 * @return PersistentTrip
	 */
	private PersistentTrip addTrip(String trip_name){
		PersistentTrip pt = null;
		if(trip_name.equals("New trip name...") || trip_name.isEmpty()){
			JOptionPane.showMessageDialog(this, "Please enter a trip name first. ", "Trip name error",
	                JOptionPane.INFORMATION_MESSAGE);
		} else if(trip_name.length() >= 40 ){
			JOptionPane.showMessageDialog(this, "Trip name too long! ", "Trip name error",
	                JOptionPane.INFORMATION_MESSAGE);
			
		} else {
			Trip newTrip = new Trip(trip_name);
			pt = TripMapper.getInstance().createTrip(newTrip);
			
			generateTripList();
		}
		
		return pt;
	}
	
	/**
	 * This method allows you to remove a trip
	 * 	
	 * @param PersistentTrip
	 * @return void
	 */
	private void deleteTrip(PersistentTrip pt){
		//jTripList.clearSelection();
		TripMapper.getInstance().deleteTrip(pt);
		generateTripList();
		clearProperties();
	}
	/**
	 * This method allows you to assign a picture to a trip
	 * 	
	 * @param PersistentTrip
	 * @return void
	 */
	private void choosePicture(PersistentTrip pt){
		try {
	         JFileChooser chooser = new JFileChooser();
	         chooser.addChoosableFileFilter(new ImageFilter());
	         chooser.setAcceptAllFileFilterUsed(false);
	         chooser.setAccessory(new ImagePreview(chooser));
	         chooser.showOpenDialog(this);
	         String path = chooser.getSelectedFile().getPath();
	         Picture pic = new Picture(path);
	         // Change the PersistentTrip
	         pt.setPicture(pic);       
	         // Change the icon
	         ImageIcon img = new ImageIcon(path);
	         ImageIcon scaled = new ImageIcon(img.getImage().getScaledInstance(320, -1,Image.SCALE_SMOOTH ));
	 		 jPictureIconLabel.setIcon(scaled);
	 		 //update db
	 		 PersistentTrip localTrip = TripMapper.getInstance().getTripForOid(pt.getId());
	 		 localTrip.setPicture(pic);
	 		 TripMapper.getInstance().updateTrip(localTrip);
	      } catch(Exception exc)
	 		{
	 			//exc.printStackTrace();
	    	  	System.out.println("Please verify that you selected a valid image file");	
	 		}
	}

	/**
	 * This method initializes jCurrentTripsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJCurrentTripsPanel() {
		if (jCurrentTripsPanel == null) {
			jCurrentTripsPanel = new JPanel();
			jCurrentTripsPanel.setLayout(new BoxLayout(getJCurrentTripsPanel(), BoxLayout.Y_AXIS));
			jCurrentTripsPanel.add(jTripListLabel, null);
			jCurrentTripsPanel.add(getJTripsScrollPane(), null);
			jCurrentTripsPanel.add(getJDeleteButton(), null);
		}
		return jCurrentTripsPanel;
	}

	/**
	 * This method initializes jPropertiesPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPropertiesPanel() {
		if (jPropertiesPanel == null) {
			jPropertiesPanel = new JPanel();
			jPropertiesPanel.setLayout(new BoxLayout(getJPropertiesPanel(), BoxLayout.Y_AXIS));
			jPropertiesPanel.add(jPropertiesLabel, null);
			jPropertiesPanel.add(jNameLabel, null);
			jPropertiesPanel.add(getJNameField(), null);
			jPropertiesPanel.add(jStartDateLabel, null);
			jPropertiesPanel.add(getJStartDateField(), null);
			jPropertiesPanel.add(jEndDateLabel, null);
			jPropertiesPanel.add(getJEndDateField(), null);
			jPropertiesPanel.add(jLocationLabel, null);
			jPropertiesPanel.add(getJLocationField(), null);
			jPropertiesPanel.add(jDescriptionLabel, null);
			jPropertiesPanel.add(getJDescriptionScrollPane(), null);
			jPropertiesPanel.add(getJButtonsPanel(), null);
		}
		return jPropertiesPanel;
	}

	/**
	 * This method initializes jButtonsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJButtonsPanel() {
		if (jButtonsPanel == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setHgap(0);
			flowLayout.setVgap(0);
			jButtonsPanel = new JPanel();
			jButtonsPanel.setLayout(flowLayout);
			jButtonsPanel.add(getJSaveButton(), null);
			jButtonsPanel.add(getJResetButton(), null);
		}
		return jButtonsPanel;
	}

	/**
	 * This method initializes jIconPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJIconPanel() {
		if (jIconPanel == null) {
			BorderLayout borderLayout = new BorderLayout();
			jIconPanel = new JPanel();
			jIconPanel.setLayout(borderLayout);
			jIconPanel.add(jPictureLabel, BorderLayout.NORTH);
			jIconPanel.add(jPictureIconLabel, BorderLayout.CENTER);
			jIconPanel.add(getJSetPictureButton(), BorderLayout.SOUTH);
		}
		return jIconPanel;
	}

	/**
	 * This method initializes jDescriptionScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJDescriptionScrollPane() {
		if (jDescriptionScrollPane == null) {
			jDescriptionScrollPane = new JScrollPane();
			jDescriptionScrollPane.setViewportView(getJDescriptionPane());
		}
		return jDescriptionScrollPane;
	}

	/**
	 * This method initializes jAddTripsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJAddTripsPanel() {
		if (jAddTripsPanel == null) {
			jAddTripsPanel = new JPanel();
			jAddTripsPanel.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
			jAddTripsPanel.setLayout(new FlowLayout());
			jAddTripsPanel.add(getJNewTripNameField(), null);
			jAddTripsPanel.add(getJAddButton(), null);
		}
		return jAddTripsPanel;
	}
	/**
	 * This method clears the properties pane
	 * 
	 * @return void
	 */	
	private void clearProperties(){
		getJNameField().setText("");
		getJStartDateField().setText("");
		getJEndDateField().setText("");
		getJDescriptionPane().setText("");
		getJLocationField().setText("");
		jPictureIconLabel.setIcon(null);
	}
	
	/**
	 * This method updates the GUI
	 * 
	 * @return void
	 */	
	public void update(){
		generateTripList();
		clearProperties();
	}
	
	/**
	 * This method checks if entered values are correct
	 * 
	 * @return Boolean
	 */
	private boolean checkInputTrip(){
		boolean check = true;
		String error = "Incorrect input(s):";
		//	check whether properties name length isn't over 40 charachters
		if(getJNameField().getText().length() > 40){
			error = error + "\nProperties are too long";
			check = false;
		}
		//	check whether location length isn't over 40 charachters
		if(getJLocationField().getText().length() > 40){
			error = error + "\nLocationname is too long";
			check = false;
		}
		//	check whether description length isn't over 400 characters
		if(getJDescriptionPane().getText().length() > 400){
			error = error + "\nDescription is too long"; 
			check = false;
		}
		//	check possible errors in the start date structure (structure how it should be: 2008-04-12)
		try{
			String date = getJStartDateField().getText();
			int startyear = Integer.parseInt(date.substring(0,4));
			int startmonth = Integer.parseInt(date.substring(date.indexOf("-") + 1,date.lastIndexOf("-")));
			int startday = Integer.parseInt(date.substring(date.lastIndexOf("-") + 1));

			if(date.indexOf("-") != 4)	// check whether first "-" is in the correct spot
			{
				error = error + "\nStart Date must be in format: YYYY-MM-DD";
				check = false;
			} else if (startyear > 2008)		// check for pictures from the future...
			{
				error = error + "\nStart Date must be in format: YYYY-MM-DD";
				check = false;
			} else if (date.indexOf("-",5) != 6 && date.indexOf("-",5) != 7)	// check for second "-"
			{
				error = error + "\nStart Date must be in format: YYYY-MM-DD";
				check = false;
			} else if (startmonth < 1 || startmonth > 12)
			{
				error = error + "\nStart Date must be in format: YYYY-MM-DD";
				check = false;
			} else if (startday < 1 || startday > 31)
			{
				error = error + "\nStart Date must be in format: YYYY-MM-DD";
				check = false;
			}
		} catch(Exception exc)
		{
			error = error + "\nStart Date must be in format: YYYY-MM-DD";
			check = false;
		}

		//	check possible errors in the end date structure (structure how it should be: 2008-04-12)
		try{
			String date = getJEndDateField().getText();
			int endyear = Integer.parseInt(date.substring(0,4));
			int endmonth = Integer.parseInt(date.substring(date.indexOf("-") + 1,date.lastIndexOf("-")));
			int endday = Integer.parseInt(date.substring(date.lastIndexOf("-") + 1));

			if(date.indexOf("-") != 4)	// check whether first "-" is in the correct spot
			{
				error = error + "\nEnd Date must be in format: YYYY-MM-DD";
				check = false;
			} else if (endyear > 2008)		// check for pictures from the future...
			{
				error = error + "\nEnd Date must be in format: YYYY-MM-DD";
				check = false;
			} else if (date.indexOf("-",5) != 6 && date.indexOf("-",5) != 7)	// check for second "-"
			{
				error = error + "\nEnd Date must be in format: YYYY-MM-DD";
				check = false;
			} else if (endmonth < 1 || endmonth > 12)
			{
				error = error + "\nEnd Date must be in format: YYYY-MM-DD";
				check = false;
			} else if (endday < 1 || endday > 31)
			{
				error = error + "\nEnd Date must be in format: YYYY-MM-DD";
				check = false;
			}
		} catch(Exception exc)
		{
			error = error + "\nEnd Date must be in format: YYYY-MM-DD";
			check = false;
		}
		// check if start date is before end date
		try{
			String startdate = getJStartDateField().getText();
			int startyear = Integer.parseInt(startdate.substring(0,4));
			int startmonth = Integer.parseInt(startdate.substring(startdate.indexOf("-") + 1,startdate.lastIndexOf("-")));
			int startday = Integer.parseInt(startdate.substring(startdate.lastIndexOf("-") + 1));
			
			String enddate = getJEndDateField().getText();
			int endyear = Integer.parseInt(enddate.substring(0,4));
			int endmonth = Integer.parseInt(enddate.substring(enddate.indexOf("-") + 1,enddate.lastIndexOf("-")));
			int endday = Integer.parseInt(enddate.substring(enddate.lastIndexOf("-") + 1));
			
			if(endyear<startyear)
			{
				error = error + "\nTrip dates aren't chronologically correct";
				check = false;
			} else if(endmonth<startmonth)
			{
				error = error + "\nTrip dates aren't chronologically correct";
				check = false;
			} else if(endday<startday)
			{
				error = error + "\nTrip dates aren't chronologically correct";
				check = false;				
			}

		} catch(Exception exc)
		{
			// no action required
		}



		if(!check){	
			JOptionPane.showMessageDialog(null, 
	        	error, "Error",
	        	JOptionPane.INFORMATION_MESSAGE);
		}
		
		return check;
	}



}  //  @jve:decl-index=0:visual-constraint="10,10"
