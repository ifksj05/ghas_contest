package windows;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import bases.BaseFrame;
import bases.BaseImgLabel;
import bases.BaseLabel;
import bases.BasePanel;
import jdbc.DbManager;
import model.ImageModel;

public class BookListForm extends BaseFrame {
	private JComboBox<String> jcbSearchType;
	private JTextField jtfSearch;
	private JButton jbSearch;
	private JScrollPane jspBookImgs;
	private JScrollPane jspBookType;
	private JTable jtpBookType;
	private DefaultTableModel dtmBookType;
	private Vector<String> cols;
	private BasePanel jpBookImgs;

	public BookListForm() {
		// TODO Auto-generated constructor stub
		setFrame("도서목록", 900, 600);
	}

	@Override
	public void made() {
		// TODO Auto-generated method stub
		jcbSearchType = new JComboBox<String>();
		jcbSearchType.addItem("도서명");
		jcbSearchType.addItem("저자");

		jtfSearch = new JTextField();
		jtfSearch.setPreferredSize(new Dimension(200, 27));

		jbSearch = new JButton("검색");

		cols = new Vector<String>();
		cols.add("분류");

		dtmBookType = new DefaultTableModel(null, cols);
		jtpBookType = new JTable(dtmBookType);
		jspBookType = new JScrollPane(jtpBookType);

		jpBookImgs = new BasePanel().setGrid(0, 4, 10, 0);
		jspBookImgs = new JScrollPane(jpBookImgs);
	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpTop.addChild();
		jpTop.jpTop.add(new BaseLabel("도서 목록").setFontSize(25).setTextCenter());
		jpTop.jpBottom.setFlowRight();
		jpTop.jpBottom.add(new BaseLabel("검색"));
		jpTop.jpBottom.add(jcbSearchType);
		jpTop.jpBottom.add(jtfSearch);
		jpTop.jpBottom.add(jbSearch);

		jpCenter.addChild();
		jpCenter.jpCenter.add(jspBookType);
		jpCenter.jpRight.add(jspBookImgs);
		jpCenter.jpRight.setPreferredSize(new Dimension(700, HEIGHT));

		updateImg();

		jpMain.setEmtBorder(30, 20, 20, 20);
	}

	@Override
	public void event() {
		// TODO Auto-generated method stub

	}

	private void updateImg() {
		// TODO Auto-generated method stub
		jpBookImgs.removeAll();

		Vector<ImageModel> bookData = DbManager.db.getImageModel("SELECT * FROM 2_2023지방_2.book;", 7);

		for (ImageModel row : bookData) {

			BasePanel jpBookTmp = new BasePanel();
			jpBookTmp.add(new BaseImgLabel(row.getData().get(1), row.getIcon(), 120, 150).setTextBottom().setHCenter());

			jpBookTmp.setLine();
			jpBookTmp.setPreferredSize(new Dimension(150, 170));

			jpBookImgs.add(jpBookTmp);

		}

		super.refresh();

	}

}
