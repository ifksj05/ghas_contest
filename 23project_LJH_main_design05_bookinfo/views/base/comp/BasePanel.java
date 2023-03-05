package base.comp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class BasePanel extends JPanel{

	public BasePanel jpCenter;
	public BasePanel jpLeft;
	public BasePanel jpRight;
	public BasePanel jpTop;
	public BasePanel jpBottom;
	
	private Border blackLine;
	private TitledBorder tb;

	public BasePanel() {
		// TODO Auto-generated constructor stub
		super.setLayout(new BorderLayout());
		super.setBackground(Color.WHITE);
	}
	
	public BasePanel(JComponent com) {
		// TODO Auto-generated constructor stub
		this();
		addChild();
		jpCenter.add(com);
	}

	public BasePanel setGridLayout(int r, int c) {
		// TODO Auto-generated constructor stub
		super.setLayout(new GridLayout(r, c));
		
		return this;
	}

	public BasePanel setGridLayout(int r, int c, int h, int v) {
		// TODO Auto-generated constructor stub
		super.setLayout(new GridLayout(r, c, h, v));
		
		return this;
	}
	
	public BasePanel setFlowLayout() {
		// TODO Auto-generated constructor stub
		super.setLayout(new FlowLayout());
		
		return this;
	}
	
	public BasePanel setFlowLayoutLeft() {
		// TODO Auto-generated constructor stub
		super.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		return this;
	}
	
	public BasePanel setFlowLayoutRight() {
		// TODO Auto-generated constructor stub
		super.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		return this;
	}
	public BasePanel setTitle(String title, int size) {
		// TODO Auto-generated constructor stub
		
		blackLine = BorderFactory.createLineBorder(Color.BLACK);
		
		tb = BorderFactory.createTitledBorder(blackLine, title);
		tb.setTitleFont(new Font("HY헤드라인m", Font.PLAIN, size));
		super.setBorder(tb);

		return this;
	}
	
	public BasePanel setLine() {
		// TODO Auto-generated constructor stub
//		BorderFactory.createLin
		//		blackLine = BorderFactory.createLineBorder(Color.BLACK);
		super.setBorder(new LineBorder(Color.black));

		return this;
	}
	
	public BasePanel setEmptyBorder(int l, int r, int t, int b) {
		// TODO Auto-generated method stub
		super.setBorder(new EmptyBorder(t, l, b, r));
		
		return this;

	}
	
	public BasePanel setEmptyBorder(int i) {
		// TODO Auto-generated method stub
		super.setBorder(new EmptyBorder(i, i, i, i));
		
		return this;
	}

	public BasePanel setPSize(int width, int height) {
		// TODO Auto-generated method stub
		super.setPreferredSize(new Dimension(width, height));
		return this;
	}

	public BasePanel setColorBack() {
		// TODO Auto-generated constructor stub

		super.setBackground(Color.BLACK);
		
		return this;
	}
	
	public void addChild() {
		// TODO Auto-generated method stub
		jpCenter = new BasePanel();
		jpLeft = new BasePanel();
		jpRight = new BasePanel();
		jpTop = new BasePanel();
		jpBottom = new BasePanel();
		
		super.add(jpCenter, BorderLayout.CENTER);
		super.add(jpLeft, BorderLayout.WEST);
		super.add(jpRight, BorderLayout.EAST);
		super.add(jpTop, BorderLayout.NORTH);
		super.add(jpBottom, BorderLayout.SOUTH);
	}




}
