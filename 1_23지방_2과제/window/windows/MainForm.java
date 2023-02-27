package windows;

import java.awt.Color;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import bases.BaseFrame;
import bases.BaseJLabel;
import bases.BasePanel;
import jdbc.DbManager;

public class MainForm extends BaseFrame {
	private JComboBox<String> jcbBookState;
	private JButton jbLogin;
	private JButton jbSignUp;
	private JButton jbBookList;
	private JButton jbMyPage;
	private JButton jbBookRead;
	private JButton jbExit;
	private BaseJLabel jlMainTitle;
	private BasePanel jpBookImgs;
	private DbManager db;
	private Vector<Vector<String>> bookRank;

	public MainForm() {
		// TODO Auto-generated constructor stub

		db = new DbManager();
		setFrame("메인", 850, 700);

	}

	@Override
	public void made() {
		// TODO Auto-generated method stub

		jlMainTitle = new BaseJLabel("로그인 후 이용해주세요").setCenter().setTextColor(Color.white).setFontSize(25)
				.addImg("./datafiles/메인1.jpg", 850, 400);

		jcbBookState = new JComboBox<String>();
		jpBookImgs = new BasePanel().setGrid(1, 5, 15, 0);

		jbLogin = new JButton("로그인");
		jbSignUp = new JButton("회원가입");
		jbBookList = new JButton("도서 목록");
		jbMyPage = new JButton("마이페이지");
		jbBookRead = new JButton("책 읽기");
		jbExit = new JButton("종료");

	}

	@Override
	public void design() {
		// TODO Auto-generated method stub

		jpTop.add(jlMainTitle);

		jpCenter.addChild();
		jpCenter.setLine("인기 도서");
		jpCenter.jpTop.setFlowLeft();
		jpCenter.jpTop.add(jcbBookState);

		jpCenter.jpCenter.add(jpBookImgs);
		initBookImgs();

		jpBottom.setFlowCenter();
		jpBottom.add(jbLogin);
		jpBottom.add(jbSignUp);
		jpBottom.add(jbBookList);
		jpBottom.add(jbMyPage);
		jpBottom.add(jbBookRead);
		jpBottom.add(jbExit);

	}

	private void initBookImgs() {
		// TODO Auto-generated method stub
		bookRank = db.getDb("SELECT \r\n" + "b.b_no, b.b_name, count(b.b_no), d.d_no, d.d_name\r\n" + "FROM \r\n"
				+ "2023지방_2.rental as r\r\n" + "join book as b\r\n" + "join division as d\r\n"
				+ "on r.b_no = b.b_no and b.d_no = d.d_no\r\n" + "\r\n" + "group by b.b_no\r\n"
				+ "order by count(b.b_no) desc, b.b_no\r\n" + "\r\n" + "limit 5\r\n" + ";");

		for (Vector<String> row : bookRank) {

			ImageIcon img = db.getImg("SELECT b_img, b_no FROM 2023지방_2.book where b_no = ?;", row.get(0));

			BasePanel tmp = new BasePanel();
			tmp.addChild();
			tmp.jpCenter.add(new BaseJLabel().addImg(img, 120, 150).setTextCenter());
			tmp.jpBottom.add(new BaseJLabel(row.get(1)).setTextCenter());

			tmp.setLine();
			jpBookImgs.add(tmp);

		}

	}

	@Override
	public void event() {
		// TODO Auto-generated method stub

	}

}
