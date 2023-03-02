package bases;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;

public class BaseLabel extends JLabel {
	public BaseLabel() {
		// TODO Auto-generated constructor stub
	}

	public BaseLabel(String string) {
		// TODO Auto-generated constructor stub
		super.setText(string);
	}

	public BaseLabel setTextCenter() {
		// TODO Auto-generated constructor stub
		super.setHorizontalAlignment(JLabel.CENTER);
		return this;

	}

	public BaseLabel setFontSize(int i) {
		// TODO Auto-generated method stub
		super.setFont(new Font("HY헤드라인M", Font.PLAIN, i));
		return this;
	}

	public BaseLabel setTextSize(int i) {
		// TODO Auto-generated method stub
		super.setFont(new Font(null, Font.PLAIN, i));
		return this;
	}
}
