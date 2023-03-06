package bases;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextField;

public class BaseTextField extends JTextField {
	public BaseTextField() {
		// TODO Auto-generated constructor stub
	}

	public BaseTextField(int i) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setSize(int width, int height) {
		// TODO Auto-generated method stub
		super.setPreferredSize(new Dimension(width, height));
	}

}
