package base.comp;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

public class BaseLabel extends JLabel{

	public BaseLabel(String title) {
		// TODO Auto-generated constructor stub
		super(title);
	}

	public BaseLabel(String title, int size) {
		// TODO Auto-generated constructor stub
		super(title);
		super.setFont(new Font("HY견고딕", Font.PLAIN, size));
	}

	public BaseLabel setCenter() {
		// TODO Auto-generated method stub
		super.setHorizontalAlignment(JLabel.CENTER);
		return this;
	}
	
	public BaseLabel setLine() {
		// TODO Auto-generated constructor stub

		//		blackLine = BorderFactory.createLineBorder(Color.BLACK);
		super.setBorder(new LineBorder(Color.black));

		return this;
	}
	
	
	public BaseLabel setBottomLine() {
		// TODO Auto-generated constructor stub

		//		blackLine = BorderFactory.createLineBorder(Color.BLACK);
		super.setBorder(new MatteBorder(0, 0, 3, 0, Color.BLACK));

		return this;
	}
	
}
