package windows;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bases.BaseFrame;
import bases.BaseJLabel;
import jdbc.DbManager;

public class BookSettingForm extends BaseFrame {
	private JComboBox<String> jcbSearchType;
	private JTextField jtfBookName;
	private JButton jbSearch;
	private JScrollPane jspMain;
	private BaseJLabel jlSearchSize;
	private JTable jtbMain;
	private DefaultTableModel dtmMain;
	private Vector<String> cols;
	private Vector<Vector<String>> bookList;
	private int searchCount;

	public BookSettingForm() {
		// TODO Auto-generated constructor stub
		setFrame("도서관리", 700, 500);
	}

	@Override
	public void made() {
		// TODO Auto-generated method stub
		jcbSearchType = new JComboBox<String>();
		jtfBookName = new JTextField(20);
		jbSearch = new JButton("검색");

		cols = new Vector<String>();
		cols.add("이미지");
		cols.add("도서명");
		cols.add("분류");
		cols.add("저자");
		cols.add("수량");
		cols.add("페이지");

		updateBookList();
		dtmMain = new DefaultTableModel(bookList, cols);
		jtbMain = new JTable(dtmMain);
		jspMain = new JScrollPane(jtbMain);

		jlSearchSize = new BaseJLabel("검색 건수 : " + searchCount + "권");

	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpTop.addChild();
		jpTop.jpTop.add(new BaseJLabel("도서 관리").setFontSize(25).setTextCenter());
		jpTop.jpBottom.setFlowRight();
		jpTop.jpBottom.add(jcbSearchType);
		jpTop.jpBottom.add(jtfBookName);
		jpTop.jpBottom.add(jbSearch);

		jpCenter.add(jspMain);

		jpBottom.setFlowRight();
		jpBottom.add(jlSearchSize);

	}

	@Override
	public void event() {
		// TODO Auto-generated method stub

	}

	private void updateBookList() {
		// TODO Auto-generated method stub

		bookList = DbManager.db
				.getDb("SELECT \r\n" + "b.b_img, b.b_name, d.d_name, b.b_author, b.b_count, b.b_page \r\n"
						+ "FROM 2023지방_2.book as b\r\n" + "join division as d\r\n" + "on b.d_no = d.d_no;");

		searchCount = bookList.size();

	}
}
