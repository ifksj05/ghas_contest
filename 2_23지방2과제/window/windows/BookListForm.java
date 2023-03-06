package windows;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import bases.BaseComboBox;
import bases.BaseFrame;
import bases.BaseImgLabel;
import bases.BaseLabel;
import bases.BaseNullPanel;
import bases.BasePanel;
import bases.BaseTable;
import bases.BaseTextField;
import bases.DataModel;
import jdbc.DbManager;
import messge.Msg;
import model.ImageModel;

public class BookListForm extends BaseFrame {
	private BaseComboBox jcbBookType;
	private JTextField jtfSearch;
	private JButton jbSearch;
	private BaseTable jtbType;
	private JScrollPane jspImgs;
	private BaseLabel jlRowSize;
	private BasePanel jpImgs;

	public BookListForm() {
		// TODO Auto-generated constructor stub
		setFrame("도서목록", 830, 500);
	}

	@Override
	public void made() {
		// TODO Auto-generated method stub
		jcbBookType = new BaseComboBox("도서명", "저자");
		jtfSearch = new JTextField(12);
		jbSearch = new JButton("검색");

		jtbType = new BaseTable(DataModel.getAllType(), "분류");

		jpImgs = new BasePanel().setGrid(0, 4, 10, 0);
		jspImgs = new JScrollPane(jpImgs);

		jlRowSize = new BaseLabel("검색건수 : ");

		jtbType.jtb.changeSelection(0, 0, false, false);
		jspImgsChange();

	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpTop.addChild();

		jpTop.jpTop.add(new BaseLabel("도서 목록").setFontSize(25).setTextCenter());
		jpTop.jpBottom.setFlowRight();
		jpTop.jpBottom.add(new BaseLabel("검색"));
		jpTop.jpBottom.add(jcbBookType);
		jpTop.jpBottom.add(jtfSearch);
		jpTop.jpBottom.add(jbSearch);

		jpCenter.addChild();
		jpCenter.jpLeft.add(jtbType).setPreferredSize(new Dimension(100, 0));
		jpCenter.jpCenter.add(jspImgs);

		jpBottom.setFlowRight().add(jlRowSize);

	}

	@Override
	public void event() {
		// TODO Auto-generated method stub
		jbSearch.addActionListener(e -> {
			jspImgsChange();
		});
	}

	public void jspImgsChange() {
		// TODO Auto-generated method stub

		jpImgs.removeAll();

		String bookType = "";
		String bookName = "";
		String bookAuthor = "";

		bookType = (jtbType.jtb.getSelectedRow() == 0 ? "" : jtbType.jtb.getSelectedRow() + "");
		if (jcbBookType.getSelectedIndex() == 0) {
			bookName = jtfSearch.getText();

		} else {
			bookAuthor = jtfSearch.getText();

		}

		// 4.5).(3)스페이스바만 검색할 경우 스페이스 무시
		bookName = (bookName.isBlank() ? "" : bookName);
		bookAuthor = (bookAuthor.isBlank() ? "" : bookAuthor);

		System.out.println("bookType : " + bookType);
		System.out.println("bookName : " + bookName);
		System.out.println("bookAuthor : " + bookAuthor);

		bookName.replace(" ", "");
		bookAuthor.replace(" ", "");

		Vector<ImageModel> dataBook = DbManager.db.getImageModel(
				"SELECT *  FROM 2_2023지방_2.book\r\n" + "where d_no like concat('%',?) \r\n"
						+ "and replace(b_name, ' ', '') like concat('%',?,'%')\r\n"
						+ "and replace(b_author, ' ', '') like concat('%',?,'%')\r\n" + ";",
				7, bookType, bookName, bookAuthor);

		for (ImageModel row : dataBook) {

//			BasePanel tmpBookImg = new BasePanel().setLine();
//			tmpBookImg.setPreferredSize(new Dimension(150, 175));

			BaseImgLabel imgLabel = new BaseImgLabel(row.getData().get(1), row.getIcon(), 130, 160).setTextBottom()
					.setHCenter();

//			tmpBookImg.add(imgLabel);

			JLabel jlLike = new JLabel("♡");

			BaseNullPanel jpNullPanel = new BaseNullPanel(150, 200, imgLabel, 0, 0, 150, 180, jlLike, 5, 5, 20, 20);

			jpNullPanel.setLine();

			jpImgs.add(jpNullPanel);
		}

		jlRowSize.setText("검색건수 : " + dataBook.size() + "건");

		if (dataBook.size() < 8) {

			for (int i = 0; i < 7 - dataBook.size(); i++) {
				jpImgs.add(new BaseLabel());
			}

		}

		if (dataBook.size() == 0) {
			jtbType.jtb.changeSelection(0, 0, false, false);
			jtfSearch.setText("");
			Msg.error("검색결과가 없습니다.");
			jspImgsChange();
		}

		super.refresh();
	}

}
