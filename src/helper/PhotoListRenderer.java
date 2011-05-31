package helper;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import persistence.*;

public class PhotoListRenderer extends JLabel
	implements ListCellRenderer {

		/**
		 * This is the default constructor
		 *
		 */
		public PhotoListRenderer() {
				setOpaque(true);
				setHorizontalAlignment(CENTER);
				setVerticalAlignment(CENTER);
		}

		/**
		* This method finds the image and text corresponding
		* to the selected value and returns the label, set up
		* to display the text and image.
		* 
		* @param JList, Object, int, boolean, boolean
		* @return Component
		*/
		public Component getListCellRendererComponent(
		                JList list,
		                Object value,
		                int index,
		                boolean isSelected,
		                boolean cellHasFocus) {

		PersistentPicture valueP = PictureMapper.getInstance().getPictureForOid(((PersistentPicture) value).getId());
		if (isSelected) {
		setBackground(list.getSelectionBackground());
		setForeground(list.getSelectionForeground());
		} else {
		setBackground(list.getBackground());
		setForeground(list.getForeground());
		}
		
		//Set the icon and text
		ImageIcon img = new ImageIcon(valueP.getFileName());
        ImageIcon scaled = new ImageIcon(img.getImage().getScaledInstance(64, -1,Image.SCALE_FAST ));      
		setIcon(scaled);
		setText(valueP.getDescription());
		
		return this;
		}
		
}
