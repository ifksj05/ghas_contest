package windows;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;

import bases.BaseComboBox;
import bases.BaseFrame;
import bases.BaseImgLabel;
import bases.BasePanel;
import jdbc.DbManager;
import model.ImageModel;
import model.UserModel;

public class MainForm extends BaseFrame {
	private BaseImgLabel jlTitle;
	private JButton jbLog;
	private JButton jbSignUp;
	private JButton jbBookList;
	private JButton jbMyPage;
	private JButton jbBookRead;
	private JButton jbExit;
	private JComboBox<String> jcbBookType;
	private BasePanel jpBookGridImg;
	private BasePanel jpBts;

	public MainForm() {
		// TODO Auto-generated constructor stub
		setFrame("메인", 850, 710);
	}

	@Override
	public void made() {
		// TODO Auto-generated method stub
		jlTitle = new BaseImgLabel("로그인 후 이용해주세요.", "./datafiles/메인1.jpg", 850, 400).setTextCenter()
				.setTextColor(Color.white).setFontSize(25);

		jbLog = new JButton("로그인");
		jbSignUp = new JButton("회원가입");
		jbBookList = new JButton("도서목록");
		jbMyPage = new JButton("마이페이지");
		jbBookRead = new JButton("책 읽기");
		jbExit = new JButton("종료");

		jpBts = new BasePanel().setFlowCenter();
		jpBts.add(jbLog);
		jpBts.add(jbSignUp);
		jpBts.add(jbBookList);
		jpBts.add(jbMyPage);
		jpBts.add(jbBookRead);
		jpBts.add(jbExit);

//		jcbBookType = new JComboBox<String>();

		Vector<Vector<String>> bookTypes = DbManager.db.getDb("SELECT * FROM 2_2023지방_2.division;");

		jcbBookType = new BaseComboBox().addDataAll(bookTypes, 1);
		jpBookGridImg = new BasePanel().setGrid(1, 5, 20, 0);

		updateGird();

		login();
		logRefresh();
	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpCenter.add(jlTitle);

		jpBottom.addChild();

		jpBottom.jpCenter.addChild();
		jpBottom.jpCenter.jpTop.setFlowLeft();
		jpBottom.jpCenter.jpTop.add(jcbBookType);

		jpBottom.jpCenter.add(jpBookGridImg);
		jpBottom.jpCenter.setLine("인기 도서");

		jpBottom.jpBottom.add(jpBts);

	}

	@Override
	public void event() {
		// TODO Auto-generated method stub

		jcbBookType.addActionListener(e -> {
			updateGird();
		});

		jbLog.addActionListener(e -> {

			if (jbLog.getText().equals("로그인")) {
				new LoginForm(this);
				return;
			}

			UserModel.logout();
			logRefresh();
			refresh();

		});
	}

	private void updateGird() {
		// TODO Auto-generated method stub

		jpBookGridImg.removeAll();

		String bookTypeIndex = (jcbBookType.getSelectedIndex() == 0 ? "%" : jcbBookType.getSelectedIndex() + "");
		System.out.println("bookTypeIndex : " + bookTypeIndex);

		Vector<ImageModel> bookImgs = DbManager.db.getImageModel("SELECT \r\n" + "r.b_no,\r\n" + "b.b_name,\r\n"
				+ "count(r.b_no),\r\n" + "d.d_no,\r\n" + "d_name,\r\n" + "b.b_img\r\n"
				+ "FROM 2_2023지방_2.rental as r\r\n" + "join book as b\r\n" + "join division as d\r\n"
				+ "on r.b_no = b.b_no and b.d_no = d.d_no\r\n" + "where d.d_no like ?\r\n" + "group by r.b_no\r\n"
				+ "order by count(r.b_no) desc, b_no\r\n" + "limit 5;", 5, bookTypeIndex);

		for (ImageModel row : bookImgs) {
			BasePanel tmp = new BasePanel();
			tmp.add(new BaseImgLabel(row.getData().get(1), row.getIcon(), 120, 150).setTextBottom().setHCenter());

			tmp.setLine();
			jpBookGridImg.add(tmp);

		}

		super.refresh();

	}

	public void login() {
		// TODO Auto-generated method stub
		jpBottom.jpBottom.removeAll();

		BasePanel tmp = new BasePanel().setFlowCenter();
		tmp.add(jbLog);
		tmp.add(jbSignUp);
		tmp.add(jbBookList);
		tmp.add(jbMyPage);
		tmp.add(jbBookRead);
		tmp.add(jbExit);

		jlTitle.setText(UserModel.userData.get(1) + "님 환영합니다.");
		jbLog.setText("로그아웃");
		jbSignUp.setEnabled(false);
		jbBookList.setEnabled(true);
		jbMyPage.setEnabled(true);
		jbBookRead.setEnabled(true);

		jpBottom.jpBottom.add(tmp);

		refresh();

	}

	public void logout() {
		// TODO Auto-generated method stub
		jpBottom.jpBottom.removeAll();

		BasePanel tmp = new BasePanel().setFlowCenter();
		tmp.add(jbLog);
		tmp.add(jbSignUp);
		tmp.add(jbBookList);
		tmp.add(jbMyPage);
		tmp.add(jbBookRead);
		tmp.add(jbExit);

		jlTitle.setText("로그인 후 이용해주세요.");
		jbLog.setText("로그인");
		jbSignUp.setEnabled(true);
		jbBookList.setEnabled(false);
		jbMyPage.setEnabled(false);
		jbBookRead.setEnabled(false);

		jpBottom.jpBottom.add(tmp);

		refresh();
	}

	public void admin() {
		// TODO Auto-generated method stub
		jpBottom.jpBottom.removeAll();

		BasePanel tmp = new BasePanel().setFlowCenter();
		tmp.add(jbLog);
		tmp.add(jbBookList);
		tmp.add(jbMyPage);
		tmp.add(jbBookRead);
		tmp.add(jbExit);

		jlTitle.setText("관리자님 환영합니다.");
		jbLog.setText("로그아웃");
		jbBookList.setEnabled(true);
		jbMyPage.setEnabled(true);
		jbBookRead.setEnabled(true);

		jpBottom.jpBottom.add(tmp);

		refresh();
	}

	public void logRefresh() {
		// TODO Auto-generated method stub

		if (UserModel.log) {
//			System.out.println("로그인");

			jlTitle.setText(UserModel.userData.get(1) + "님 환영합니다.");
			jbLog.setText("로그아웃");
			jbSignUp.setEnabled(false);
			jbBookList.setEnabled(true);
			jbMyPage.setEnabled(true);
			jbBookRead.setEnabled(true);

			return;
		}

//		System.out.println("비로그인");

		jlTitle.setText("로그인 후 이용해주세요.");
		jbLog.setText("로그인");
		jbSignUp.setEnabled(true);
		jbBookList.setEnabled(false);
		jbMyPage.setEnabled(false);
		jbBookRead.setEnabled(false);

	}

//	public void adminLogin() {
//		// TODO Auto-generated method stub
//		jpBts.remove
//	}

}
