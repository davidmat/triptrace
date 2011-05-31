package gui;

import helper.ImageFilter;
import helper.ImagePreview;
import helper.PhotoListRenderer;


import java.awt.Image;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.BorderLayout;


import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;

import javax.swing.ListSelectionModel;

import persistence.PersistentPicture;
import persistence.PersistentTrip;
import persistence.PictureMapper;
import persistence.TripMapper;
import program.Picture;



import javax.swing.BoxLayout;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import java.awt.Font;



/**
 * @author David, Daan
 *
 */
public class PhotoManager extends JPanel {

	private DefaultListModel myTripsListModel = null;
	private DefaultListModel myPicsListModel = null;
	
	private static final long serialVersionUID = 1L;
	private JPanel jTripsPanel = null;
	private JLabel jTripsLabel = null;
	private JScrollPane jTripListScrollPane = null;
	private JList jTripsList = null;
	private JPanel jPicsPanel = null;
	private JScrollPane jPicListScrollPane = null;
	private JLabel jPicsLabel = null;
	private JList jPicsList = null;
	private JPanel jPropertiesPanel = null;
	private JLabel jPropertiesLabel = null;
	private JPanel jTripPropertyPanel = null;
	private JLabel jTripPropertyLabel = null;
	private JComboBox jTripPropertyComboBox = null;
	private JPanel jDatePropertyPanel = null;
	private JLabel jDatePropertyLabel = null;
	private JTextField jDatePropertyField = null;
	private JPanel jLocationPropertyPanel = null;
	private JLabel jLocationPropertyLabel = null;
	private JTextField jLocationPropertyField = null;
	private JPanel jLatitudePropertyPanel = null;
	private JLabel jLatitudePropertyLabel = null;
	private JTextField jLatitudePropertyField = null;
	private JPanel jLongitudePropertyPanel = null;
	private JLabel jLongitudePropertyLabel = null;
	private JTextField jLongitudePropertyField = null;
	private JPanel jDescriptionPropertyPanel = null;
	private JLabel jDescriptionPropertyLabel = null;
	private JScrollPane jDescriptionPropertyScrollPane = null;
	private JTextPane jDescriptionPropertyPane = null;
	private JPanel jButtonsPanel = null;
	private JButton jSaveButton = null;
	private JButton jResetButton = null;
	private JButton jDeletePictureButton = null;
	private JPanel jAddPicturePanel = null;
	private JButton jAddPictureButton = null;
	/**
	 * This is the default constructor
	 */
	public PhotoManager() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(640,480);
		this.setLayout(new BorderLayout());
		this.add(getJTripsPanel(), BorderLayout.WEST);
		this.add(getJPicsPanel(), BorderLayout.CENTER);
		this.add(getJPropertiesPanel(), BorderLayout.EAST);
		this.add(getJAddPicturePanel(), BorderLayout.NORTH);
	}

	/**
	 * This method initializes jTripsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJTripsPanel() {
		if (jTripsPanel == null) {
			jTripsLabel = new JLabel();
			jTripsLabel.setText("Trips");
			jTripsLabel.setFont(new Font("Dialog", Font.BOLD, 14));
			jTripsPanel = new JPanel();
			jTripsPanel.setLayout(new BoxLayout(getJTripsPanel(), BoxLayout.Y_AXIS));
			jTripsPanel.setPreferredSize(new Dimension(200, 600));
			jTripsPanel.add(jTripsLabel, null);
			jTripsPanel.add(getJTripListScrollPane(), null);
		}
		return jTripsPanel;
	}

	/**
	 * This method initializes jTripListScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJTripListScrollPane() {
		if (jTripListScrollPane == null) {
			jTripListScrollPane = new JScrollPane();
			jTripListScrollPane.setViewportView(getJTripsList());
		}
		return jTripListScrollPane;
	}

	/**
	 * This method initializes jTripsList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJTripsList() {
		if (jTripsList == null) {
			generateTripsList();
			jTripsList = new JList(getTripsListModel());
			jTripsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jTripsList
					.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
						public void valueChanged(javax.swing.event.ListSelectionEvent e) {
							if(!jTripsList.isSelectionEmpty()) {
								generatePicsList((PersistentTrip) jTripsList.getSelectedValue());
							} 
						}
					});
		}
		return jTripsList;
	}

	/**
	 * This method initializes jPicsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPicsPanel() {
		if (jPicsPanel == null) {
			jPicsLabel = new JLabel();
			jPicsLabel.setText("Pictures");
			jPicsLabel.setFont(new Font("Dialog", Font.BOLD, 14));
			jPicsPanel = new JPanel();
			jPicsPanel.setLayout(new BoxLayout(getJPicsPanel(), BoxLayout.Y_AXIS));
			jPicsPanel.add(jPicsLabel, null);
			jPicsPanel.add(getJPicListScrollPane(), null);
			jPicsPanel.add(getJDeletePictureButton(), null);
		}
		return jPicsPanel;
	}

	/**
	 * This method initializes jPicListScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJPicListScrollPane() {
		if (jPicListScrollPane == null) {
			jPicListScrollPane = new JScrollPane();
			jPicListScrollPane.setViewportView(getJPicsList());
		}
		return jPicListScrollPane;
	}

	/**
	 * This method initializes jPicsList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJPicsList() {
		if (jPicsList == null) {
			PhotoListRenderer plr = new PhotoListRenderer();
			
			jPicsList = new JList(getPicsListModel());
			jPicsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jPicsList.setCellRenderer(plr);
			jPicsList
					.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
						public void valueChanged(javax.swing.event.ListSelectionEvent e) {
							if(!jPicsList.isSelectionEmpty()) {
								generatePicProperties((PersistentPicture) jPicsList.getSelectedValue());
							} 
						}
					});
			jPicsList.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					if (e.getClickCount()==2 && !jPicsList.isSelectionEmpty()){
						ImageIcon img = new ImageIcon(((PersistentPicture) jPicsList.getSelectedValue()).getFileName());
				        ImageIcon scaled = new ImageIcon(img.getImage().getScaledInstance(640, -1,Image.SCALE_SMOOTH ));      
			            JOptionPane.showMessageDialog(PhotoManager.this,  scaled, "Large size view", JOptionPane.PLAIN_MESSAGE);
						
			        }
				}
			});
		}
		return jPicsList;
	}

	/**
	 * This method initializes jPropertiesPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPropertiesPanel() {
		if (jPropertiesPanel == null) {
			jPropertiesLabel = new JLabel();
			jPropertiesLabel.setText("Properties");
			jPropertiesLabel.setFont(new Font("Dialog", Font.BOLD, 14));
			jPropertiesPanel = new JPanel();
			jPropertiesPanel.setLayout(new BoxLayout(getJPropertiesPanel(), BoxLayout.Y_AXIS));
			jPropertiesPanel.setPreferredSize(new Dimension(200, 600));
			jPropertiesPanel.add(jPropertiesLabel, null);
			jPropertiesPanel.add(getJTripPropertyPanel(), null);
			jPropertiesPanel.add(getJDatePropertyPanel(), null);
			jPropertiesPanel.add(getJLocationPropertyPanel(), null);
			jPropertiesPanel.add(getJLatitudePropertyPanel(), null);
			jPropertiesPanel.add(getJLongitudePropertyPanel(), null);
			jPropertiesPanel.add(getJDescriptionPropertyPanel(), null);
			jPropertiesPanel.add(getJButtonsPanel(), null);
		}
		return jPropertiesPanel;
	}

	/**
	 * This method initializes jTripPropertyPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJTripPropertyPanel() {
		if (jTripPropertyPanel == null) {
			jTripPropertyLabel = new JLabel();
			jTripPropertyLabel.setText("Trip");
			jTripPropertyPanel = new JPanel();
			jTripPropertyPanel.setLayout(new FlowLayout());
			jTripPropertyPanel.add(jTripPropertyLabel, null);
			jTripPropertyPanel.add(getJTripPropertyComboBox(), null);
		}
		return jTripPropertyPanel;
	}

	/**
	 * This method initializes jTripPropertyComboBox	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getJTripPropertyComboBox() {
		if (jTripPropertyComboBox == null) {
			jTripPropertyComboBox = new JComboBox();
			jTripPropertyComboBox.setPreferredSize(new Dimension(150, 25));
			jTripPropertyComboBox.setEditable(true);
		}
		return jTripPropertyComboBox;
	}

	/**
	 * This method initializes jDatePropertyPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJDatePropertyPanel() {
		if (jDatePropertyPanel == null) {
			jDatePropertyLabel = new JLabel();
			jDatePropertyLabel.setText("Date");
			jDatePropertyPanel = new JPanel();
			jDatePropertyPanel.setPreferredSize(new Dimension(200, 20));
			jDatePropertyPanel.setLayout(new FlowLayout());
			jDatePropertyPanel.add(jDatePropertyLabel, null);
			jDatePropertyPanel.add(getJDatePropertyField(), null);
		}
		return jDatePropertyPanel;
	}

	/**
	 * This method initializes jDatePropertyField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJDatePropertyField() {
		if (jDatePropertyField == null) {
			jDatePropertyField = new JTextField();
			jDatePropertyField.setPreferredSize(new Dimension(150, 20));
		}
		return jDatePropertyField;
	}

	/**
	 * This method initializes jLocationPropertyPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJLocationPropertyPanel() {
		if (jLocationPropertyPanel == null) {
			jLocationPropertyLabel = new JLabel();
			jLocationPropertyLabel.setText("Location");
			jLocationPropertyPanel = new JPanel();
			jLocationPropertyPanel.setLayout(new FlowLayout());
			jLocationPropertyPanel.add(jLocationPropertyLabel, null);
			jLocationPropertyPanel.add(getJLocationPropertyField(), null);
		}
		return jLocationPropertyPanel;
	}

	/**
	 * This method initializes jLocationPropertyField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJLocationPropertyField() {
		if (jLocationPropertyField == null) {
			jLocationPropertyField = new JTextField();
			jLocationPropertyField.setPreferredSize(new Dimension(150, 20));
		}
		return jLocationPropertyField;
	}

	/**
	 * This method initializes jLatitudePropertyPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJLatitudePropertyPanel() {
		if (jLatitudePropertyPanel == null) {
			jLatitudePropertyLabel = new JLabel();
			jLatitudePropertyLabel.setText("Latitude");
			jLatitudePropertyPanel = new JPanel();
			jLatitudePropertyPanel.setLayout(new FlowLayout());
			jLatitudePropertyPanel.add(jLatitudePropertyLabel, null);
			jLatitudePropertyPanel.add(getJLatitudePropertyField(), null);
		}
		return jLatitudePropertyPanel;
	}

	/**
	 * This method initializes jLatitudePropertyField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJLatitudePropertyField() {
		if (jLatitudePropertyField == null) {
			jLatitudePropertyField = new JTextField();
			jLatitudePropertyField.setEditable(false);
			jLatitudePropertyField.setPreferredSize(new Dimension(150, 20));
			jLatitudePropertyField.setEnabled(false);
		}
		return jLatitudePropertyField;
	}

	/**
	 * This method initializes jLongitudePropertyPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJLongitudePropertyPanel() {
		if (jLongitudePropertyPanel == null) {
			jLongitudePropertyLabel = new JLabel();
			jLongitudePropertyLabel.setText("Longitude");
			jLongitudePropertyPanel = new JPanel();
			jLongitudePropertyPanel.setLayout(new FlowLayout());
			jLongitudePropertyPanel.add(jLongitudePropertyLabel, null);
			jLongitudePropertyPanel.add(getJLongitudePropertyField(), null);
		}
		return jLongitudePropertyPanel;
	}

	/**
	 * This method initializes jLongitudePropertyField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJLongitudePropertyField() {
		if (jLongitudePropertyField == null) {
			jLongitudePropertyField = new JTextField();
			jLongitudePropertyField.setEnabled(false);
			jLongitudePropertyField.setPreferredSize(new Dimension(150, 20));
			jLongitudePropertyField.setEditable(false);
		}
		return jLongitudePropertyField;
	}

	/**
	 * This method initializes jDescriptionPropertyPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJDescriptionPropertyPanel() {
		if (jDescriptionPropertyPanel == null) {
			jDescriptionPropertyLabel = new JLabel();
			jDescriptionPropertyLabel.setText("Description");
			jDescriptionPropertyLabel.setHorizontalTextPosition(SwingConstants.CENTER);
			jDescriptionPropertyLabel.setHorizontalAlignment(SwingConstants.CENTER);
			jDescriptionPropertyPanel = new JPanel();
			jDescriptionPropertyPanel.setLayout(new BoxLayout(getJDescriptionPropertyPanel(), BoxLayout.Y_AXIS));
			jDescriptionPropertyPanel.add(jDescriptionPropertyLabel, null);
			jDescriptionPropertyPanel.add(getJDescriptionPropertyScrollPane(), null);
		}
		return jDescriptionPropertyPanel;
	}

	/**
	 * This method initializes jDescriptionPropertyScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJDescriptionPropertyScrollPane() {
		if (jDescriptionPropertyScrollPane == null) {
			jDescriptionPropertyScrollPane = new JScrollPane();
			jDescriptionPropertyScrollPane.setViewportView(getJDescriptionPropertyPane());
		}
		return jDescriptionPropertyScrollPane;
	}

	/**
	 * This method initializes jDescriptionPropertyPane	
	 * 	
	 * @return javax.swing.JTextPane	
	 */
	private JTextPane getJDescriptionPropertyPane() {
		if (jDescriptionPropertyPane == null) {
			jDescriptionPropertyPane = new JTextPane();
			jDescriptionPropertyPane.setPreferredSize(new Dimension(150, 50));
		}
		return jDescriptionPropertyPane;
	}

	/**
	 * This method initializes jButtonsPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJButtonsPanel() {
		if (jButtonsPanel == null) {
			jButtonsPanel = new JPanel();
			jButtonsPanel.setLayout(new FlowLayout());
			jButtonsPanel.add(getJSaveButton(), null);
			jButtonsPanel.add(getJResetButton(), null);
		}
		return jButtonsPanel;
	}

	/**
	 * This method initializes jSaveButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJSaveButton() {
		if (jSaveButton == null) {
			jSaveButton = new JButton();
			jSaveButton.setText("Save");
			jSaveButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(!jPicsList.isSelectionEmpty()){
						updatePicProperties((PersistentPicture) jPicsList.getSelectedValue());
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
			jResetButton.setText("Reset");
			jResetButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(!jPicsList.isSelectionEmpty()){
						generatePicProperties((PersistentPicture) jPicsList.getSelectedValue());
					}
				}
			});
		}
		return jResetButton;
	}
	
	
	

	/**
	 * This method initializes jDeletePictureButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJDeletePictureButton() {
		if (jDeletePictureButton == null) {
			jDeletePictureButton = new JButton();
			jDeletePictureButton.setText("Delete selected picture");
			jDeletePictureButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(!jPicsList.isSelectionEmpty()){
						PictureMapper.getInstance().deletePicture((PersistentPicture) jPicsList.getSelectedValue());
						generatePicsList((PersistentTrip) jTripsList.getSelectedValue());
					}
				}
			});
		}
		return jDeletePictureButton;
	}

	/**
	 * This method initializes jAddPicturePanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJAddPicturePanel() {
		if (jAddPicturePanel == null) {
			jAddPicturePanel = new JPanel();
			jAddPicturePanel.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
			jAddPicturePanel.setLayout(new FlowLayout());
			jAddPicturePanel.add(getJAddPictureButton(), null);
		}
		return jAddPicturePanel;
	}

	
	

	/**
	 * This method initializes jAddPictureButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJAddPictureButton() {
		if (jAddPictureButton == null) {
			jAddPictureButton = new JButton();
			jAddPictureButton.setText("Add picture to selected trip");
			jAddPictureButton.setIcon(new ImageIcon("res/photo_add.png"));
			jAddPictureButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(!jTripsList.isSelectionEmpty()){	
						addPicture();
					} else {
						JOptionPane.showMessageDialog(PhotoManager.this,
							    "Please select a trip first",
							    "warning",
							    JOptionPane.WARNING_MESSAGE);
					}
				}
						
			});
		}
		return jAddPictureButton;
	}

	/**
	 * This method populates jTripsList
	 * 	
	 * @return void
	 */
	private void generateTripsList() {
		getTripsListModel().clear();
		
		List<String> tripNames = TripMapper.getInstance().getTripNames();
		
		for (String name: tripNames) {
			PersistentTrip LocalTrip = TripMapper.getInstance().getTrip(name);
			getTripsListModel().addElement(LocalTrip);
		}

	}
	/**
	 * This method initialilizes myTripsListModel
	 * 	
	 * @return DefaultListModel
	 */
	private DefaultListModel getTripsListModel() {
        if (this.myTripsListModel == null) {
                this.myTripsListModel = new DefaultListModel();
        }
        return this.myTripsListModel;
	}
	
	/**
	 * This method populates jPicsList
	 * 
	 * @param PersistentTrip	
	 * @return void
	 */
	private void generatePicsList(PersistentTrip trip) {
		getPicsListModel().clear();
		
		List<String> fileNames = PictureMapper.getInstance().getPicturesForTrip(trip);
		
		for (String filename: fileNames) {
			PersistentPicture localPic = PictureMapper.getInstance().getPicture(filename);
			getPicsListModel().addElement(localPic);
		}

	}
	
	/**
	 * This method initializes myPicsListModel
	 * 	
	 * @return DefaultListModel
	 */
	private DefaultListModel getPicsListModel() {
        if (this.myPicsListModel == null) {
                this.myPicsListModel = new DefaultListModel();
        }
        return this.myPicsListModel;
	}
	
	/**
	 * This method adds a picture to the list
	 * 
	 * @return Persistentpicture
	 */
	private PersistentPicture addPicture(){
		String path = null;
		try {
	         JFileChooser chooser = new JFileChooser();
	         chooser.addChoosableFileFilter(new ImageFilter());
	         chooser.setAcceptAllFileFilterUsed(false);
	         chooser.setAccessory(new ImagePreview(chooser));
	         chooser.showOpenDialog(this);
	         chooser.setDialogTitle("Select a file to open");
	         path = chooser.getSelectedFile().getPath();
      
		} catch(Exception exc)
	 	{
			  JOptionPane.showMessageDialog(this,
					    "Please make sure you selected a valid image file",
					    "warning",
					    JOptionPane.WARNING_MESSAGE);
			  return null;
	 	}
		Picture pic = new Picture(path);
		PersistentTrip localTrip = TripMapper.getInstance().getTripForOid(((PersistentTrip) getJTripsList().getSelectedValue()).getId());
		pic.setTrip(localTrip);
		PersistentPicture pp = PictureMapper.getInstance().createPicture(pic);
		
		generatePicsList((PersistentTrip) jTripsList.getSelectedValue());
		return pp;
	}
		
	/**
	 * This method displays the properties of a picture
	 * 
	 * @param PersistentPicture
	 * @return void
	 */
	private void generatePicProperties(PersistentPicture pp){
		PersistentPicture pic = PictureMapper.getInstance().getPictureForOid(pp.getId());
		generateTripComboBox(pic);
		getJDatePropertyField().setText(pic.getDate().toString());
		getJLocationPropertyField().setText(pic.getLocationName());
		getJLatitudePropertyField().setText("" + pic.getLocationLat());
		getJLongitudePropertyField().setText("" + pic.getLocationLong());
		getJDescriptionPropertyPane().setText(pic.getDescription());
		
	}
	
	/**
	 * This method populates jTripPropertyCombobox
	 * 
	 * @param PersistentPicture
	 * @return void
	 */
	private void generateTripComboBox(PersistentPicture pp) {
		getJTripPropertyComboBox().removeAllItems();
		List<String> tripNames = TripMapper.getInstance().getTripNames();
		
		for (String name: tripNames) {
			PersistentTrip localTrip = TripMapper.getInstance().getTrip(name);
			getJTripPropertyComboBox().addItem(localTrip);
		}
		
		getJTripPropertyComboBox().setSelectedItem(pp.getTrip());
	
	}

	/**
	 * This method commits changes to the picture to database
	 * 
	 * @param PersistentPicture
	 * @return void
	 */
	private void updatePicProperties(PersistentPicture pp){
		if (checkInput()) {
			pp.setTrip((PersistentTrip) getJTripPropertyComboBox().getSelectedItem());
			pp.setDate(java.sql.Date.valueOf(getJDatePropertyField().getText()));
			pp.setLocationName(getJLocationPropertyField().getText());
			pp.setDescription(getJDescriptionPropertyPane().getText());
			PictureMapper.getInstance().updatePicture(pp);
			getJPicsList().updateUI();
			generatePicProperties(pp);
		}		
		
	}
	
	/**
	 * This method checks if entered values are correct
	 * 
	 * @return Boolean
	 */
	private boolean checkInput(){


		boolean check = true;
		String error = "Incorrect input(s):";
		String date;


		//	check whether location length isn't over 40 charachters

		if(getJLocationPropertyField().getText().length() > 40){
			error= error+"\nLocationname is too long";
			//System.out.println("\nLocationname is too long");
			check = false;
		}

		//	check whether description length isn't over 400 characters

		if(getJDescriptionPropertyPane().getText().length() > 400){
			error= error+"\nDescription is too long";
			//System.out.println("\nDescription is too long");
			check = false;
		}


		//	check possible errors in the date structure (structure how it should be: 2008-04-12)

		date = getJDatePropertyField().getText();
		int year;
		int month;
		int day;
		try {
			year = Integer.parseInt(date.substring(0,4));
			month = Integer.parseInt(date.substring(date.indexOf("-") + 1,date.lastIndexOf("-")));
			day = Integer.parseInt(date.substring(date.lastIndexOf("-") + 1));
			//System.out.println(month);
			//System.out.println(day);
			//System.out.println(year);
			
			if(date.indexOf("-") != 4)	// check whether first "-" is in the correct spot
			{
				error= error+"\nDate must be in format: YYYY-MM-DD";
				//System.out.println("\nDate must be in format: YYYY-MM-DD1");
				check = false;
			} else if (year > 2008)		// check for pictures from the future...
			{
				error= error+"\nDate must be in format: YYYY-MM-DD";
				//System.out.println("\nDate must be in format: YYYY-MM-DD2");
				check = false;
			} else if (date.indexOf("-",5) != 6 && date.indexOf("-",5) != 7)	// check for second "-"
			{
				error= error+"\nDate must be in format: YYYY-MM-DD";
				//System.out.println("\nDate must be in format: YYYY-MM-DD3");
				check = false;
			} else if (month < 1 || month > 12)
			{
				error= error+"\nDate must be in format: YYYY-MM-DD";
				//System.out.println("\nDate must be in format: YYYY-MM-DD4");
				check = false;
			} else if (day < 1 || day > 31)
			{
				error= error+"\nDate must be in format: YYYY-MM-DD";
				//System.out.println("\nDate must be in format: YYYY-MM-DD5");
				check = false;
			}

		} catch (NumberFormatException e) {
			error= error+"\nDate must be in format: YYYY-MM-DD";
			//System.out.println("\nDate must be in format: YYYY-MM-DD6");
			check = false;
		}
		

		

		if(!check){	
			JOptionPane.showMessageDialog(null, 
	        	error, "Error",
	        	JOptionPane.INFORMATION_MESSAGE);
		}
		
		return check;
	}
	/**
	 * This method updates the GUI
	 * 
	 * @return void
	 */	
	public void update(){
		generateTripsList();
		clear();
		
	}
	
	/**
	 * This method clears the GUI
	 * 
	 * @return void
	 */	
	private void clear(){
		getPicsListModel().clear();
		getJDatePropertyField().setText("");
		getJLocationPropertyField().setText("");
		getJLatitudePropertyField().setText("");
		getJLongitudePropertyField().setText("");
		getJDescriptionPropertyPane().setText("");
		getJTripPropertyComboBox().removeAll();
	}

}
