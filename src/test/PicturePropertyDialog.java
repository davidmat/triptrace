package test;

import javax.swing.JPanel;
import java.awt.Frame;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.Rectangle;
import javax.swing.JTextField;

import program.Picture;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;

public class PicturePropertyDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JButton jCancelButton = null;

	private JButton jSetButton = null;

	private JLabel jDescriptionLabel = null;

	private JTextField jDescriptionField = null;
	
	private Picture pic;

	/**
	 * @param owner
	 */
	public PicturePropertyDialog(Picture pic) {
		super();
		this.pic = pic;
		initialize();
		
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 400);
		this.setModal(true);
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jDescriptionLabel = new JLabel();
			jDescriptionLabel.setText("Description");
			jDescriptionLabel.setBounds(new Rectangle(17, 52, 65, 16));
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getJCancelButton(), null);
			jContentPane.add(getJSetButton(), null);
			jContentPane.add(jDescriptionLabel, null);
			jContentPane.add(getJDescriptionField(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jCancelButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJCancelButton() {
		if (jCancelButton == null) {
			jCancelButton = new JButton();
			jCancelButton.setText("Cancel");
			jCancelButton.setBounds(new Rectangle(55, 311, 73, 26));
			jCancelButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					CancelButtonClicked();
				}
			});
		}
		return jCancelButton;
	}

	/**
	 * This method initializes jSetButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJSetButton() {
		if (jSetButton == null) {
			jSetButton = new JButton();
			jSetButton.setText("Set");
			jSetButton.setBounds(new Rectangle(180, 311, 53, 26));
			jSetButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					SetButtonClicked();
				}
			});
		}
		return jSetButton;
	}

	/**
	 * This method initializes jDescriptionField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJDescriptionField() {
		if (jDescriptionField == null) {
			jDescriptionField = new JTextField();
			jDescriptionField.setBounds(new Rectangle(14, 74, 207, 29));
			jDescriptionField.setText(pic.getDescription());
		}
		return jDescriptionField;
	}
	
	private void CancelButtonClicked(){
		this.setVisible(false);
	}
	
	private void SetButtonClicked(){
		String description = jDescriptionField.getText();
		pic.setDescription(description);
		this.setVisible(false);
	}
	
	
	private Picture getUpdatedPicture(){
		return pic;		
	}
		

}  //  @jve:decl-index=0:visual-constraint="95,32"
