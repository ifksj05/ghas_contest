package windows;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;

import base.comp.BaseComboBox;
import base.comp.BaseFrame;
import base.comp.ImageLabel;
import data.DataManager;
import image.ImageModel;
import jdbc.DbManager;
import message.MessageShow;
import res.ResModels;

public class MainFrame extends BaseFrame {

	private ImageLabel jlTop;
	private BaseComboBox jcAll;
	private JButton jbLogin;
	private JButton jbJoin;
	private JButton jbBookList;
	private JButton jbMyPage;
	private JButton jbBookRead;
	private JButton jbExit;
	private JButton jbLogout;
	private JButton jbBookManager;
	private JButton jbBookAdd;
	private JButton jbChart;

	public MainFrame() {
		// TODO Auto-generated constructor stub
		super.setFrame("메인", 1000, 844, null);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub

		jlTop = new ImageLabel("로그인 후 이용해주세요.", "메인1", 1000, 500).setFontSize(30).setTextCenter().setTextColorWhite();

		// 콤보 박스
		// 4p 2)조건
//		jcAll = new BaseCombo().setDivisionData("전체");
		jcAll = new BaseComboBox(ResModels.getDivisionTableAll());

		// 회원
		jbLogin = new JButton("로그인");
		jbJoin = new JButton("회원가입");
		jbBookList = new JButton("도서 목록");
		jbMyPage = new JButton("마이페이지");
		jbBookRead = new JButton("책 읽기");
		jbExit = new JButton("종료");

		jbLogout = new JButton("로그아웃");

		// 관리자
		jbBookManager = new JButton("도서관리");
		jbBookAdd = new JButton("도서등록");
		jbChart = new JButton("대출통계");

	}

//	@Override
//	public synchronized int getState() {
//		// TODO Auto-generated method stub
//		int i = super.getState();
//		System.out.println(i);
//		return i;
//	}
	@Override
	public void setDesign() {
		// TODO Auto-generated method stub

		jpTop.add(jlTop);

		// 인기 도서
		jpCenter.setTitle("인기 도서", 20);
		jpCenter.addChild();
		jpCenter.jpTop.setFlowLayoutLeft().add(jcAll);

		jpCenter.jpCenter.setGridLayout(1, 5, 30, 0);

		gridChange();

		jpBottom.setFlowLayout();

//		logout();

		// 테스트 로그인 상태
		Vector<Vector<String>> user = DbManager.db.getData("SELECT * FROM 2023지방_2.user where u_id = 'user01'");
		ResModels.login = user.get(0);
		login();

	}

	@Override
	public void setAction() {
		// TODO Auto-generated method stub

		// 회원
		// 5p 5)-1조건
		jbLogin.addActionListener(e -> {
			new LoginFrame(this);
		});

		// 5p 5)-2조건
		jbJoin.addActionListener(e -> {
			new JoinFrame(this);
		});

		// 5p 5)-3조건
		jbBookList.addActionListener(e -> {
			new BookListFrame(this);
		});

		// 5p 5)-4조건
		jbMyPage.addActionListener(e -> {
			new MyPageFrame(this);
		});

		// 5p 5)-5조건
		jbBookRead.addActionListener(e -> {

			new BookReadFrame();
		});

		jbLogout.addActionListener(e -> {
			logout();
		});

		// 관리자

		// 공통
		jcAll.addActionListener(e -> {
			gridChange();
		});

		// 메인 종료 구현
		jbExit.addActionListener(e -> {
			close();
		});
	}

	// 4p 3)조건
	private void gridChange() {
		// TODO Auto-generated method stub

		jpCenter.jpCenter.removeAll();

		int comboIndex = jcAll.getSelectedIndex();

		String d_no = jcAll.datas.get(comboIndex).get(1);

		Vector<ImageModel> datas = DbManager.db.getImageData("SELECT b.*, d.* FROM 2023지방_2.book as b\r\n"
				+ "	join division as d\r\n" + "		on b.d_no = d.d_no\r\n" + "	join rental as r\r\n"
				+ "		on b.b_no = r.b_no\r\n" + "	where d.d_no like ?\r\n" + "    group by b.b_no\r\n"
				+ "    order by count(b.b_no) desc, b.b_no\r\n" + "    limit 5;", 7, d_no);

		for (ImageModel row : datas) {
			String b_name = row.getData().get(1);
			String b_author = row.getData().get(3);
			String b_exp = row.getData().get(6);

			ImageLabel jlImg = new ImageLabel(b_name, row.getIcon(), 150, 180).setBottom().setLine().setCenter()
					.setFontSize(14);

			MainFrame mainFrame = this;
			jlImg.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);

					if (e.getClickCount() != 2) {
						return;
					}

					if (ResModels.login == null) {
						MessageShow.error("로그인 후 이용하세요.");
						return;
					}

					if (ResModels.login.size() == 0) {
						System.out.println("관리자");
						return;
					}

					new BookInfoFrame(mainFrame, row);
				}
			});

			// 5p 4)-1조건
			b_author = DataManager.subString(b_author, 13, "");
			b_exp = DataManager.subString(b_exp, 13, "");

			String toolTipText = "<html>" + "저자 : " + b_author + " <br/> " + "설명 : " + b_exp + " </html>";

			jlImg.setToolTipText(toolTipText);

			jpCenter.jpCenter.add(jlImg);
		}

		super.reFresh();
	}

	// [그림 1]
	private void logout() {
		// TODO Auto-generated method stub

		ResModels.login = null;

		jlTop.setText("로그인 후 이용해주세요.");

		jpBottom.removeAll();
		// 멤버 페널
		jpBottom.add(jbLogin);
		jpBottom.add(jbJoin);
		jpBottom.add(jbBookList);
		jpBottom.add(jbMyPage);
		jpBottom.add(jbBookRead);
		jpBottom.add(jbExit);

		jbJoin.setEnabled(true);

		jbBookList.setEnabled(false);
		jbMyPage.setEnabled(false);
		jbBookRead.setEnabled(false);

		super.reFresh();
	}

	// [그림 1-3]
	public void login() {
		// TODO Auto-generated method stub

		jlTop.setText(ResModels.login.get(1) + "님 환영합니다.");

		jpBottom.removeAll();
		// 멤버 페널
		jpBottom.add(jbLogout);
		jpBottom.add(jbJoin);
		jpBottom.add(jbBookList);
		jpBottom.add(jbMyPage);
		jpBottom.add(jbBookRead);
		jpBottom.add(jbExit);

		jbJoin.setEnabled(false);

		jbBookList.setEnabled(true);
		jbMyPage.setEnabled(true);
		jbBookRead.setEnabled(true);

		super.reFresh();
	}

	// [그림 1-4]
	public void managerLogin() {
		// TODO Auto-generated method stub

		jlTop.setText("관리자님 환영합니다.");

		jpBottom.removeAll();

		// 관리자 페널
		jpBottom.add(jbLogout);
		jpBottom.add(jbBookManager);
		jpBottom.add(jbBookAdd);
		jpBottom.add(jbChart);
		jpBottom.add(jbExit);

		super.reFresh();
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		System.exit(0);
	}
}
