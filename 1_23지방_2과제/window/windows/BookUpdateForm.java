package windows;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bases.BaseFrame;
import bases.BaseJLabel;
import bases.BasePanel;

public class BookUpdateForm extends BaseFrame {
	private BasePanel jpBookImg;
	private JComboBox<String> jcbBookType;
	private JTextField jtfBookName;
	private JTextField jtfBookMaker;
	private JTextField jtfBookCount;
	private JTextField jtfBookPage;
	private JTextArea jtaExp;
	private JButton jbUpdate;

	public BookUpdateForm() {
		// TODO Auto-generated constructor stub
		setFrame("도서수정", 500, 450);
	}

	@Override
	public void made() {
		// TODO Auto-generated method stub
		jpBookImg = new BasePanel().setLine();
		jpBookImg.setPreferredSize(new Dimension(200, 0));

		jcbBookType = new JComboBox<String>();
		jtfBookName = new JTextField();
		jtfBookMaker = new JTextField();
		jtfBookCount = new JTextField();
		jtfBookPage = new JTextField();

		jtaExp = new JTextArea();
		jtaExp.setPreferredSize(new Dimension(0, 100));

		jbUpdate = new JButton("수정");

	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpCenter.addChild();
		jpCenter.jpLeft.add(jpBookImg);
		jpCenter.jpCenter.addChild();
		jpCenter.jpCenter.jpLeft.setGrid(5, 1, 0, 0);
		jpCenter.jpCenter.jpLeft.add(new BaseJLabel("분류"));
		jpCenter.jpCenter.jpLeft.add(new BaseJLabel("도서명"));
		jpCenter.jpCenter.jpLeft.add(new BaseJLabel("저자"));
		jpCenter.jpCenter.jpLeft.add(new BaseJLabel("수량"));
		jpCenter.jpCenter.jpLeft.add(new BaseJLabel("페이지"));

		jpCenter.jpCenter.jpCenter.setGrid(5, 1, 0, 0);
		jpCenter.jpCenter.jpCenter.add(jcbBookType);
		jpCenter.jpCenter.jpCenter.add(jtfBookName);
		jpCenter.jpCenter.jpCenter.add(jtfBookMaker);
		jpCenter.jpCenter.jpCenter.add(jtfBookCount);
		jpCenter.jpCenter.jpCenter.add(jtfBookPage);

		jpBottom.addChild();
		jpBottom.jpCenter.addChild();
		jpBottom.jpCenter.jpTop.setFlowLeft();
		jpBottom.jpCenter.jpTop.add(new BaseJLabel("설명"));

		jpBottom.jpCenter.jpCenter.setLine();
		jpBottom.jpCenter.jpCenter.add(jtaExp);

		jpBottom.jpBottom.setFlowRight();
		jpBottom.jpBottom.add(jbUpdate);
	}

	@Override
	public void event() {
		// TODO Auto-generated method stub

	}

}
