package base.comp;

import java.awt.Dimension;

import javax.swing.JLabel;

public class NullPanel extends BasePanel{

	public NullPanel(int w, int h,
			JLabel jlImg1, 
			int x1, int y1, int w1, int h1,
			JLabel jlImg2, 
			int x2, int y2, int w2, int h2) {
		// TODO Auto-generated constructor stub
		
		super.setLayout(null);
		super.setPreferredSize(new Dimension(w, h));

		
		jlImg1.setBounds(x1, y1, w1, h1);
		jlImg2.setBounds(x2, y2, w2, h2);
		
		super.add(jlImg1);
		super.add(jlImg2);
	}

}

