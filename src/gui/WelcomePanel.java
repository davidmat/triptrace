package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class WelcomePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel jLogoLabel = null;
	/**
	 * This is the default constructor
	 */
	public WelcomePanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		jLogoLabel = new JLabel();
		jLogoLabel.setIcon(new ImageIcon("res/main.png"));
		jLogoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		jLogoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.setLayout(new BorderLayout());
		this.add(jLogoLabel, BorderLayout.CENTER);
	}

}
