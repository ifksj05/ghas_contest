package windows;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bases.BaseFrame;
import bases.BaseJLabel;
import bases.BasePanel;
import jdbc.DbManager;
import model.ImageModel;

public class BookListForm extends BaseFrame {

	private JComboBox<String> jcbSearchType;
	private JTextField jtfSearch;
	private JButton jbSearch;
	private DefaultTableModel dtm;
	private JTable jtbBookType;
	private JScrollPane jspBookImgs;
	private BasePanel jpBookImgs;
	private BaseJLabel jlSearchTime;
	private DbManager db;
	private Vector<Vector<String>> bookType;
	private Vector<String> cols;
	private JScrollPane jspBookType;

	public BookListForm() {
		// TODO Auto-generated constructor stub
		db = new DbManager();
		setFrame("도서목록", 800, 500);
	}

	@Override
	public void made() {
		// TODO Auto-generated method stub
		jcbSearchType = new JComboBox<String>();
		jtfSearch = new JTextField(13);
		jbSearch = new JButton("검색");

		// ----
		bookType = new Vector<Vector<String>>();
		initBookTypeData();

		dtm = new DefaultTableModel(bookType, cols);
		jtbBookType = new JTable(dtm);
		jspBookType = new JScrollPane(jtbBookType);

		jpBookImgs = new BasePanel().setGrid(0, 4, 10, 0);
		jspBookImgs = new JScrollPane(jpBookImgs);

		initBookImgs();

//		jcpBookImgs.setBackground(Color.white);

		jlSearchTime = new BaseJLabel("검색건수 : ").setTextSize(13);
	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpTop.addChild();
		jpTop.jpTop.add(new BaseJLabel("도서 목록").setFontSize(25).setTextCenter());
		jpTop.jpBottom.setFlowRight();
		jpTop.jpBottom.add(new BaseJLabel("검색"));
		jpTop.jpBottom.add(jcbSearchType);
		jpTop.jpBottom.add(jtfSearch);
		jpTop.jpBottom.add(jbSearch);

		jpCenter.addChild();
		jpCenter.jpCenter.add(jspBookType);
		jpCenter.jpRight.add(jspBookImgs);

		jpBottom.setFlowRight();
		jpBottom.add(jlSearchTime);
	}

	@Override
	public void event() {
		// TODO Auto-generated method stub

	}

	private void initBookTypeData() {
		// TODO Auto-generated method stub
		Vector<String> row0 = new Vector<String>();
		row0.add("전체");
		bookType.add(row0);

		Vector<Vector<String>> rows = db.getDb("SELECT d_name FROM 2023지방_2.division;");
		for (Vector<String> vector : rows) {

			bookType.add(vector);

		}

		cols = new Vector<String>();
		cols.add("분류");

	}

	private void initBookImgs() {
		// TODO Auto-generated method stub

		Vector<ImageModel> bookImgsData = DbManager.db.getImageModel("SELECT * FROM 2023지방_2.book;", 7);

		for (ImageModel row : bookImgsData) {
			BasePanel tmp = new BasePanel();
			tmp.setLine();
			tmp.addChild();
			tmp.jpCenter.add(new BaseJLabel().addImg(row.getIcon(), 150, 160));
//			tmp.jpBottom.add(new BaseJLabel(row.getData().get(1)));
			jpBookImgs.add(tmp);

		}

	}

}
