package bases;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public abstract class BaseFrame extends JFrame implements BaseI {
	public BasePanel jpMain;
	public BasePanel jpTop;
	public BasePanel jpLeft;
	public BasePanel jpCenter;
	public BasePanel jpRight;
	public BasePanel jpBottom;

	public void setFrame(String title, int width, int height) {
		// TODO Auto-generated method stub
		super.setTitle(title);
		super.setSize(width, height);

		jpMain = new BasePanel();
		jpTop = new BasePanel();
		jpLeft = new BasePanel();
		jpCenter = new BasePanel();
		jpRight = new BasePanel();
		jpBottom = new BasePanel();

		jpMain.setBorder(10, 10, 10, 10);

		made();
		design();
		event();

		jpMain.add(jpTop, BorderLayout.NORTH);
		jpMain.add(jpLeft, BorderLayout.WEST);
		jpMain.add(jpCenter, BorderLayout.CENTER);
		jpMain.add(jpRight, BorderLayout.EAST);
		jpMain.add(jpBottom, BorderLayout.SOUTH);

		super.add(jpMain);

		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		super.setLocationRelativeTo(null);
	}

	public void refresh() {
		// TODO Auto-generated method stub
		repaint();
		validate();
	}
}
