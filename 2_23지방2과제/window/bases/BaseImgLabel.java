package bases;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class BaseImgLabel extends JLabel {
	public BaseImgLabel() {
		// TODO Auto-generated constructor stub
	}

	public BaseImgLabel(String text) {
		// TODO Auto-generated constructor stub
		setText(text);
	}

	public BaseImgLabel(String text, String url, int width, int height) {
		// TODO Auto-generated constructor stub
		super.setText(text);

		ImageIcon icon = new ImageIcon(url);
		Image img = icon.getImage();
		img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);
		super.setIcon(icon);
	}

	public BaseImgLabel(String text, ImageIcon icon, int width, int height) {
		// TODO Auto-generated constructor stub
		super.setText(text);

//		ImageIcon icon = new ImageIcon(url);
		Image img = icon.getImage();
		img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);
		super.setIcon(icon);
	}

	public BaseImgLabel addImg(String url, int width, int height) {
		// TODO Auto-generated method stub
		ImageIcon icon = new ImageIcon(url);
		Image img = icon.getImage();
		img = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		icon = new ImageIcon(img);
		super.setIcon(icon);

		return this;
	}

	public BaseImgLabel setTextCenter() {
		// TODO Auto-generated method stub
		super.setHorizontalTextPosition(JLabel.CENTER);
		super.setVerticalTextPosition(JLabel.CENTER);

		return this;

	}

	public BaseImgLabel setTextBottom() {
		// TODO Auto-generated method stub
		super.setHorizontalTextPosition(JLabel.CENTER);
		super.setVerticalTextPosition(JLabel.BOTTOM);
		return this;

	}

	public BaseImgLabel setTextColor(Color color) {
		// TODO Auto-generated method stub
		super.setForeground(color);
		return this;

	}

	public BaseImgLabel setFontSize(int size) {
		// TODO Auto-generated method stub
		super.setFont(new Font("HY헤드라인M", Font.PLAIN, size));
		return this;

	}

	public BaseImgLabel setHCenter() {
		// TODO Auto-generated method stub
		super.setHorizontalAlignment(JLabel.CENTER);
		return this;
	}

}
