package gui;

import java.awt.BorderLayout;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jMainContentPane = null;

	private JMenuBar jMainMenuBar = null;

	private JMenu jFileMenu = null;

	private JMenuItem jFileExitMenuItem = null;

	private JToolBar jMainToolBar = null;

	private JButton jMapButton = null;

	private JButton jAddPhotoButton = null;

	private JToolBar jStatusBar = null;

	private JLabel jStatusLabel = null;

	private JPanel jMainPanel = null;

	private WelcomePanel welcomePanel = null;

	private CardLayout cardLayout = null;

	private JButton jTripsButton = null;

	private TripManager tripManager = null;

	private PhotoManager photoManager = null;

	private MapPanel mapPanel = null;

	private JMenu jViewMenu = null;

	private JMenuItem jMapMenuItem = null;

	private JMenuItem jManagePicturesMenuItem = null;

	private JMenuItem jManageTripsMenuItem = null;

	private JMenu jHelpMenu = null;

	private JMenuItem jAboutMenuItem = null;

	/**
	 * This is the default constructor
	 */
	public MainGUI() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(getJMainMenuBar());
		this.setContentPane(getJMainContentPane());
		this.setTitle("Triptrace");
	}

	/**
	 * This method initializes jMainContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJMainContentPane() {
		if (jMainContentPane == null) {
			jMainContentPane = new JPanel();
			jMainContentPane.setLayout(new BorderLayout());
			jMainContentPane.add(getJMainToolBar(), BorderLayout.NORTH);
			jMainContentPane.add(getJStatusBar(), BorderLayout.SOUTH);
			jMainContentPane.add(getJMainPanel(), BorderLayout.CENTER);
		}
		return jMainContentPane;
	}

	/**
	 * This method initializes jMainMenuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getJMainMenuBar() {
		if (jMainMenuBar == null) {
			jMainMenuBar = new JMenuBar();
			jMainMenuBar.add(getJFileMenu());
			jMainMenuBar.add(getJViewMenu());
			jMainMenuBar.add(getJHelpMenu());
		}
		return jMainMenuBar;
	}

	/**
	 * This method initializes jFileMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJFileMenu() {
		if (jFileMenu == null) {
			jFileMenu = new JMenu();
			jFileMenu.setText("File");
			jFileMenu.add(getJFileExitMenuItem());
		}
		return jFileMenu;
	}

	/**
	 * This method initializes jFileExitMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJFileExitMenuItem() {
		if (jFileExitMenuItem == null) {
			jFileExitMenuItem = new JMenuItem();
			jFileExitMenuItem.setText("Exit");
			//jFileExitMenuItem.setIcon(new ImageIcon("res/stop.png"));
			jFileExitMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return jFileExitMenuItem;
	}

	/**
	 * This method initializes jMainToolBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJMainToolBar() {
		if (jMainToolBar == null) {
			jMainToolBar = new JToolBar();
			jMainToolBar.setFloatable(false);
			jMainToolBar.add(getJMapButton());
			jMainToolBar.add(getJAddPhotoButton());
			jMainToolBar.add(getJTripsButton());
		}
		return jMainToolBar;
	}

	/**
	 * This method initializes jMapButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJMapButton() {
		if (jMapButton == null) {
			jMapButton = new JButton();
			jMapButton.setText("Map");
			ImageIcon ImgIcon = new ImageIcon("res/map_go.png");
			jMapButton.setIcon(ImgIcon);
			jMapButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					mapPanel.update();
					cardLayout.show(jMainPanel, "mapPanel"); 
				}
			});
		}
		return jMapButton;
	}

	/**
	 * This method initializes jAddPhotoButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJAddPhotoButton() {
		if (jAddPhotoButton == null) {
			jAddPhotoButton = new JButton();
			jAddPhotoButton.setText("Manage pictures");
			ImageIcon ImgIcon = new ImageIcon("res/photo.png");
			jAddPhotoButton.setIcon(ImgIcon);
			jAddPhotoButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					photoManager.update();
					cardLayout.show(jMainPanel, "photoManager");
				}
			});
		}
		return jAddPhotoButton;
	}

	/**
	 * This method initializes jStatusBar	
	 * 	
	 * @return javax.swing.JToolBar	
	 */
	private JToolBar getJStatusBar() {
		if (jStatusBar == null) {
			jStatusLabel = new JLabel();
			jStatusLabel.setText("Status: OK");
			jStatusBar = new JToolBar();
			jStatusBar.setFloatable(false);
			jStatusBar.add(jStatusLabel);
		}
		return jStatusBar;
	}

	/**
	 * This method initializes jMainPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJMainPanel() {
		if (jMainPanel == null) {
			jMainPanel = new JPanel();
			jMainPanel.setLayout(new CardLayout());
			jMainPanel.add(getWelcomePanel(), getWelcomePanel().getName());
			jMainPanel.add(getMapPanel(), getMapPanel().getName());
			jMainPanel.add(getTripManager(), getTripManager().getName());
			jMainPanel.add(getPhotoManager(), getPhotoManager().getName());
			cardLayout = (CardLayout) jMainPanel.getLayout();
			
		}
		return jMainPanel;
	}

	/**
	 * This method initializes welcomePanel	
	 * 	
	 * @return gui.WelcomePanel	
	 */
	private WelcomePanel getWelcomePanel() {
		if (welcomePanel == null) {
			welcomePanel = new WelcomePanel();
			welcomePanel.setName("welcomePanel");
		}
		return welcomePanel;
	}

	/**
	 * This method initializes jTripsButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJTripsButton() {
		if (jTripsButton == null) {
			jTripsButton = new JButton();
			jTripsButton.setText("Manage trips");
			ImageIcon ImgIcon = new ImageIcon("res/world.png");
			jTripsButton.setIcon(ImgIcon);
			jTripsButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					tripManager.update();
					cardLayout.show(jMainPanel, "tripManager");
				}
			});
		}
		return jTripsButton;
	}

	/**
	 * This method initializes tripManager	
	 * 	
	 * @return gui.TripManager	
	 */
	private TripManager getTripManager() {
		if (tripManager == null) {
			tripManager = new TripManager();
			tripManager.setName("tripManager");
		}
		return tripManager;
	}

	/**
	 * This method initializes photoManager	
	 * 	
	 * @return gui.PhotoManager	
	 */
	private PhotoManager getPhotoManager() {
		if (photoManager == null) {
			photoManager = new PhotoManager();
			photoManager.setName("photoManager");
		}
		return photoManager;
	}

	/**
	 * This method initializes mapPanel	
	 * 	
	 * @return gui.MapPanel	
	 */
	private MapPanel getMapPanel() {
		if (mapPanel == null) {
			mapPanel = new MapPanel();
			mapPanel.setName("mapPanel");
		}
		return mapPanel;
	}

	/**
	 * This method initializes jViewMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJViewMenu() {
		if (jViewMenu == null) {
			jViewMenu = new JMenu();
			jViewMenu.setText("View");
			jViewMenu.add(getJMapMenuItem());
			jViewMenu.add(getJManagePicturesMenuItem());
			jViewMenu.add(getJManageTripsMenuItem());
		}
		return jViewMenu;
	}

	/**
	 * This method initializes jMapMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJMapMenuItem() {
		if (jMapMenuItem == null) {
			jMapMenuItem = new JMenuItem();
			jMapMenuItem.setText("Map");
			jMapMenuItem.setIcon(new ImageIcon("res/map_go.png"));
			jMapMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					mapPanel.update();
					cardLayout.show(jMainPanel, "mapPanel"); 
				}
			});
		}
		return jMapMenuItem;
	}

	/**
	 * This method initializes jManagePicturesMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJManagePicturesMenuItem() {
		if (jManagePicturesMenuItem == null) {
			jManagePicturesMenuItem = new JMenuItem();
			jManagePicturesMenuItem.setText("Manage pictures");
			jManagePicturesMenuItem.setIcon(new ImageIcon("res/photo.png"));
			jManagePicturesMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					photoManager.update();
					cardLayout.show(jMainPanel, "photoManager");
				}
			});
		}
		return jManagePicturesMenuItem;
	}

	/**
	 * This method initializes jManageTripsMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJManageTripsMenuItem() {
		if (jManageTripsMenuItem == null) {
			jManageTripsMenuItem = new JMenuItem();
			jManageTripsMenuItem.setText("Manage Trips");
			jManageTripsMenuItem.setIcon(new ImageIcon("res/world.png"));
			jManageTripsMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					tripManager.update();
					cardLayout.show(jMainPanel, "tripManager");
				}
			});
		}
		return jManageTripsMenuItem;
	}

	/**
	 * This method initializes jHelpMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getJHelpMenu() {
		if (jHelpMenu == null) {
			jHelpMenu = new JMenu();
			jHelpMenu.setText("Help");
			jHelpMenu.add(getJAboutMenuItem());
		}
		return jHelpMenu;
	}

	/**
	 * This method initializes jAboutMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJAboutMenuItem() {
		if (jAboutMenuItem == null) {
			jAboutMenuItem = new JMenuItem();
			jAboutMenuItem.setText("About");
			jAboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JOptionPane.showMessageDialog(MainGUI.this,
						    "Program developed by: \n David Mat \n Daan Willems \n \nFor more info and updates, please visit us at: \n http://triptrace.sourceforge.net",
						    "About",
						    JOptionPane.INFORMATION_MESSAGE);
				}
			});
		}
		return jAboutMenuItem;
	}

}
