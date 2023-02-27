package bases;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BaseJLabel extends JLabel {
	public BaseJLabel() {
		// TODO Auto-generated constructor stub
	}

	public BaseJLabel(String text) {
		// TODO Auto-generated constructor stub
		super.setText(text);
	}

	public BaseJLabel setCenter() {
		// TODO Auto-generated constructor stub
		super.setHorizontalTextPosition(JLabel.CENTER);
		super.setVerticalTextPosition(JLabel.CENTER);

		return this;
	}

	public BaseJLabel setTextCenter() {
		// TODO Auto-generated constructor stub
		super.setHorizontalAlignment(JLabel.CENTER);

		return this;
	}

	public BaseJLabel setTextLeft() {
		// TODO Auto-generated constructor stub
		super.setHorizontalAlignment(JLabel.LEFT);

		return this;
	}

	public BaseJLabel setTextRight() {
		// TODO Auto-generated constructor stub
		super.setHorizontalAlignment(JLabel.RIGHT);

		return this;
	}

	public BaseJLabel setTextColor(Color color) {
		// TODO Auto-generated constructor stub
		super.setForeground(color);

		return this;
	}

	public BaseJLabel setTextSize(int size) {
		// TODO Auto-generated constructor stub
		super.setFont(new Font(null, Font.BOLD, size));

		return this;
	}

	public BaseJLabel setFontSize(int size) {
		// TODO Auto-generated constructor stub
		super.setFont(new Font("HY헤드라인M", Font.PLAIN, size));

		return this;
	}

	public BaseJLabel addImg(String url, int width, int height) {
		// TODO Auto-generated method stub
		ImageIcon icon = new ImageIcon(url);
		Image img = icon.getImage();
		img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);
		super.setIcon(icon);

		return this;
	}

	public BaseJLabel addImg(ImageIcon icon, int width, int height) {
		// TODO Auto-generated method stub
		Image img = icon.getImage();
		img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);
		super.setIcon(icon);

		return this;
	}

}
