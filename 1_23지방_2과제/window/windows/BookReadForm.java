package windows;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bases.BaseFrame;
import bases.BasePanel;

public class BookReadForm extends BaseFrame {
	private BasePanel jpBookImg;
	private JScrollPane jspBookNameList;
	private JButton jbRead;
	private JTable jtbBookNameList;
	private DefaultTableModel dtmBookNameList;

	public BookReadForm() {
		// TODO Auto-generated constructor stub
		setFrame("책읽기", 650, 400);
	}

	@Override
	public void made() {
		// TODO Auto-generated method stub
		jpBookImg = new BasePanel().setLine();
		jpBookImg.setPreferredSize(new Dimension(200, 0));

		Vector<String> colsBookNameList = new Vector<String>();
		colsBookNameList.add("도서명");

		dtmBookNameList = new DefaultTableModel(null, colsBookNameList);
		jtbBookNameList = new JTable(dtmBookNameList);

		jspBookNameList = new JScrollPane(jtbBookNameList);

		jbRead = new JButton("읽기");

	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpCenter.addChild();
		jpCenter.jpLeft.add(jpBookImg);
		jpCenter.jpCenter.add(jspBookNameList);

		jpBottom.add(jbRead);
	}

	@Override
	public void event() {
		// TODO Auto-generated method stub

	}

}
