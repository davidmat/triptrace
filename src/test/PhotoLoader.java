package test;


import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ListModel;

import program.Picture;


public class PhotoLoader extends JPanel {

	private static final long serialVersionUID = 1L;
	private DefaultListModel myListModel = null;
    private Picture pic;
	private ImageIcon img;
    private ImageIcon scaled;
	private JScrollPane jScrollPane = null;
	private JList jPhotoList = null;
	private JButton jLoadButton = null;
	private JLabel jPictureIconLabel = null;
	private JLabel jPropertyLabel = null;
	/**
	 * This is the default constructor
	 */
	public PhotoLoader() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		jPropertyLabel = new JLabel();
		jPropertyLabel.setText("Picture Title: none");
		jPictureIconLabel = new JLabel();
		jPictureIconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				pictureIconClicked(e); 
			}
		});
		this.setSize(300, 200);
		this.setLayout(new BorderLayout());
		this.add(getJScrollPane(), BorderLayout.EAST);
		this.add(getJLoadButton(), BorderLayout.NORTH);
		this.add(jPictureIconLabel, BorderLayout.WEST);
		this.add(jPropertyLabel, BorderLayout.SOUTH);
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJPhotoList());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jPhotoList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJPhotoList() {
		if (jPhotoList == null) {
			jPhotoList = new JList();
			jPhotoList.setModel(getPhotoListModel());
		}
		return jPhotoList;
	}

	/**
	 * This method initializes jLoadButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJLoadButton() {
		if (jLoadButton == null) {
			jLoadButton = new JButton();
			jLoadButton.setText("Open Picture");
			jLoadButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					loadButtonClicked(e);
				}
			});
		}
		return jLoadButton;
	}
	
	private void loadButtonClicked(ActionEvent e){
	     try {
	         JFileChooser chooser = new JFileChooser();
	         chooser.showOpenDialog(this);
	         String path = chooser.getSelectedFile().getPath();
	         pic = new Picture(path);
	         img = new ImageIcon(path);
	         scaled = new ImageIcon(img.getImage().getScaledInstance(64, -1,Image.SCALE_SMOOTH ));      
	         
	         jPictureIconLabel.setIcon(scaled);
	         PhotoLoader.this.myListModel.addElement(scaled);
	         
	         validate();
       
	      } catch(Exception exc)
	 		{
	 			jPictureIconLabel.setText("Please verify that you selected a valid image file");	
	 		}
	      
	}
	
	private void pictureIconClicked(MouseEvent e){
		if (e.getClickCount()==2){
            //JOptionPane.showMessageDialog(null,  img);
			//jPropertyLabel.setText(pic.getDescription());
			PicturePropertyDialog ppd = new PicturePropertyDialog(pic);
			ppd.setLocationRelativeTo(this);
			ppd.setVisible(true);
        }
	}
	
	
	
	private ListModel getPhotoListModel() {
        if (this.myListModel == null) {
                this.myListModel = new DefaultListModel();
        }
        return this.myListModel;
}
}
