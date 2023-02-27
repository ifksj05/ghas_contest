package windows;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bases.BaseFrame;
import bases.BaseJLabel;

public class MyPageForm extends BaseFrame {
	private BaseJLabel jlUserName;
	private JRadioButton jrbBorrowList;
	private JRadioButton jrbLikeBook;
	private JScrollPane jsp;
	private JTable jtb;
	private DefaultTableModel dtm;
	private BaseJLabel jlBorrowCount;
	private BaseJLabel jlBorrowOkey;
	private BaseJLabel jlBorrowOver;
	private BaseJLabel jlBorrowing;
	private JButton jbDelete;
	private JButton jbPrint;

	public MyPageForm() {
		// TODO Auto-generated constructor stub
		setFrame("마이페이지", 800, 400);
	}

	@Override
	public void made() {
		// TODO Auto-generated method stub
		jlUserName = new BaseJLabel("회원 : ");
		jrbBorrowList = new JRadioButton("대출내역");
		jrbBorrowList.setBackground(Color.white);
		jrbLikeBook = new JRadioButton("관심도서");
		jrbLikeBook.setBackground(Color.white);

		dtm = new DefaultTableModel();
		jtb = new JTable(dtm);
		jsp = new JScrollPane(jtb);

		jlBorrowCount = new BaseJLabel("총 대여이력 : ");
		jlBorrowOkey = new BaseJLabel("반납완료 : ");
		jlBorrowOver = new BaseJLabel("연체 중 : ");
		jlBorrowing = new BaseJLabel("대출 중 : ");

		jbDelete = new JButton("삭제하기");
		jbPrint = new JButton("pdf출력");

	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpTop.addChild();
		jpTop.jpLeft.setFlowLeft();
		jpTop.jpLeft.add(jlUserName);

		jpTop.jpRight.setFlowRight();
		jpTop.jpRight.add(jrbBorrowList);
		jpTop.jpRight.add(jrbLikeBook);

		jpCenter.add(jsp);

		jpBottom.addChild();
		jpBottom.jpLeft.setFlowLeft();
		jpBottom.jpLeft.add(jlBorrowCount);
		jpBottom.jpLeft.add(jlBorrowOkey);
		jpBottom.jpLeft.add(jlBorrowOver);
		jpBottom.jpLeft.add(jlBorrowing);

		jpBottom.jpRight.setFlowRight();
		jpBottom.jpRight.add(jbDelete);
		jpBottom.jpRight.add(jbPrint);

	}

	@Override
	public void event() {
		// TODO Auto-generated method stub

	}

}
