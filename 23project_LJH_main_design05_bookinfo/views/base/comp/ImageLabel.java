package base.comp;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToolTip;
import javax.swing.border.Border;

public class ImageLabel extends JLabel {

	private Border blackLine;

//	public ImageLabel(String title, String path) {
//		// TODO Auto-generated constructor stub
//		super(title);
//		
//		ImageIcon icon = new ImageIcon("./datafiles/" + path + ".jpg");
//		
//		super.setIcon(icon);
//	}
//	
//	public ImageLabel(String title, int size) {
//		// TODO Auto-generated constructor stub
//		super(title);
//		super.setFont(new Font("HY견고딕", Font.BOLD, size));
//
//	}


	public ImageLabel(String title, String path, int w, int h) {
		// TODO Auto-generated constructor stub
		super(title);
		
		ImageIcon icon = new ImageIcon("./datafiles/" + path + ".jpg");
		Image img = icon.getImage();
		
		img = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);
		
		super.setIcon(new ImageIcon(img));
		
	}
	
	public ImageLabel(String title, ImageIcon icon, int w, int h) {
		// TODO Auto-generated constructor stub
		super(title);

		Image img = icon.getImage();

		img = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);

		super.setIcon(new ImageIcon(img));

	}
	
	public ImageLabel setImage(String path, int w, int h) {
		// TODO Auto-generated constructor stub
		ImageIcon icon = new ImageIcon("./datafiles/" + path + ".jpg");
		Image img = icon.getImage();

		img = img.getScaledInstance(w, h, Image.SCALE_SMOOTH);

		super.setIcon(new ImageIcon(img));
		
		return this;
	}

	public ImageLabel setLine() {
		// TODO Auto-generated constructor stub

		blackLine = BorderFactory.createLineBorder(Color.BLACK);

		super.setBorder(blackLine);

		return this;
	}

//	public ImageLabel(String path) {
//		// TODO Auto-generated constructor stub
//		
//		ImageIcon icon = new ImageIcon("./datafiles/" + path + ".jpg");
//		
//		super.setIcon(icon);
//	}

	public ImageLabel setTextCenter() {
		// TODO Auto-generated method stub
		super.setVerticalTextPosition(JLabel.CENTER);
		super.setHorizontalTextPosition(JLabel.CENTER);

		return this;
	}

	public ImageLabel setCenter() {
		// TODO Auto-generated method stub
		super.setHorizontalAlignment(JLabel.CENTER);
		return this;
	}

	public ImageLabel setBottom() {
		// TODO Auto-generated method stub
		super.setVerticalTextPosition(JLabel.BOTTOM);
		super.setHorizontalTextPosition(JLabel.CENTER);

		return this;
	}

	public ImageLabel setFontSize(int size) {
		// TODO Auto-generated method stub
		super.setFont(new Font("HY헤드라인m", Font.PLAIN, size));

		return this;
	}

	@Override
	public void setSize(int width, int height) {
		// TODO Auto-generated method stub
		super.setSize(width, height);
		super.setPreferredSize(new Dimension(width, height));
	}

	public ImageLabel setTextColorWhite() {
		// TODO Auto-generated method stub
		super.setForeground(Color.WHITE);
		return this;
	}
	
	@Override
	public JToolTip createToolTip() {
		// TODO Auto-generated method stub
        JToolTip toolTip = super.createToolTip();
        toolTip.setBackground(Color.WHITE);
//        toolTip.setSize(, HEIGHT);i
        System.out.println(toolTip.getPreferredSize());
        return toolTip;
	}

}
