package windows;

import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import bases.BaseFrame;
import bases.BaseJLabel;
import bases.BasePanel;
import jdbc.DbManager;

public class BookInfoForm extends BaseFrame {
	private DbManager db;
	private BaseJLabel jlBookName;
	private BaseJLabel jlBookType;
	private BaseJLabel jlBookMaker;
	private BaseJLabel jlBookNum;
	private BaseJLabel jlBookEx;
	private JButton jbBorrow;
	private String bookName;
	private String bookType;
	private String bookMaker;
	private String bookNum;
	private String bookEx;
	private String b_no;
	private ImageIcon bookImg;
	private BaseJLabel jlBookImg;
	private BasePanel jpBookEx;
	private BasePanel jpBookType;

	public BookInfoForm(String bookIndex) {
		// TODO Auto-generated constructor stub
		db = new DbManager();

		Vector<String> bookInfo = db.getDb("SELECT \r\n"
				+ "b.b_no, b.b_name, d.d_name, b.b_author, b_count, b_page, b_exp \r\n" + "FROM 2023지방_2.book as b\r\n"
				+ "join division as d\r\n" + "on b.d_no = d.d_no\r\n" + "where b.b_no = ?;", bookIndex).get(0);

		b_no = bookInfo.get(0);
		bookName = bookInfo.get(1);
		bookImg = db.getImg("SELECT b_img, b_no FROM 2023지방_2.book where b_no = ?;", b_no);
		bookType = bookInfo.get(2);
		bookMaker = "저자 : " + bookInfo.get(3);
		bookNum = "재고 : " + bookInfo.get(4) + "권 / 페이지 : " + bookInfo.get(5) + "p";
		bookEx = bookInfo.get(6);

		System.out.println(bookName);
		System.out.println(bookType);
		System.out.println(bookMaker);
		System.out.println(bookNum);
		System.out.println(bookEx);

		setFrame("도서 정보", 380, 450);
	}

	@Override
	public void made() {
		// TODO Auto-generated method stub
		jlBookName = new BaseJLabel(bookName).setFontSize(30);
		jlBookImg = new BaseJLabel().addImg(bookImg, 180, 230);
		jlBookType = new BaseJLabel(bookType).setTextCenter().setFontSize(15);
		jpBookType = new BasePanel().setLine();
		jpBookType.add(jlBookType);

		jlBookMaker = new BaseJLabel(bookMaker).setTextSize(15);
		jlBookNum = new BaseJLabel(bookNum).setTextSize(15);
		jlBookEx = new BaseJLabel(bookEx);
		jpBookEx = new BasePanel().setLine("설명");
		jpBookEx.add(jlBookEx);

		jbBorrow = new JButton("대출");
	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpTop.addChild();
		jpTop.jpTop.add(jlBookName);
		jpTop.jpCenter.addChild();
		jpTop.jpCenter.jpLeft.add(jlBookImg);

		jpTop.jpCenter.jpCenter.setGrid(5, 1, 0, 0);
		jpTop.jpCenter.jpCenter.add(jpBookType);
		jpTop.jpCenter.jpCenter.add(jlBookMaker);
		jpTop.jpCenter.jpCenter.add(jlBookNum);

		jpCenter.add(jpBookEx);

		jpBottom.setFlowRight();
		jpBottom.add(jbBorrow);

	}

	@Override
	public void event() {
		// TODO Auto-generated method stub

	}
}
