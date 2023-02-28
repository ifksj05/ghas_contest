package windows;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bases.BaseFrame;
import bases.BaseJLabel;
import jdbc.DbManager;

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
	private String u_no;
	private Vector<Vector<String>> tableData;

	public MyPageForm(String u_no) {
		// TODO Auto-generated constructor stub
		this.u_no = u_no;
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

		Vector<String> cols = new Vector<String>();
		cols.add("이미지");
		cols.add("도서명");
		cols.add("읽은 페이지");
		cols.add("대출일");
		cols.add("반납일");

		updateTableData();

		dtm = new DefaultTableModel(tableData, cols);
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

	private void updateTableData() {
		// TODO Auto-generated method stub
		tableData = DbManager.db
				.getDb("SELECT \r\n" + "b.b_name,\r\n" + "b.b_name,\r\n" + "concat(r.r_reading, '/', b_page),\r\n"
						+ "r.r_date,\r\n" + "r.r_returnday\r\n" + "FROM 2023지방_2.rental as r\r\n" + "join book as b\r\n"
						+ "on r.b_no = b.b_no\r\n" + "where r.u_no = ?;", u_no);
	}

}
