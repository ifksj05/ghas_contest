package windows;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bases.BaseFrame;
import bases.BaseLabel;
import jdbc.DbManager;
import messge.Msg;
import model.UserModel;

public class LoginForm extends BaseFrame {
	private MainForm mainForm;
	private JTextField jtfId;
	private JPasswordField jtfPw;
	private JButton jbLogin;
	private int count;

	public LoginForm(MainForm mainForm) {
		// TODO Auto-generated constructor stub
		this.mainForm = mainForm;

		count = 0;
		setFrame("로그인", 450, 250);
	}

	@Override
	public void made() {
		// TODO Auto-generated method stub
		jtfId = new JTextField();
		jtfPw = new JPasswordField();

		jbLogin = new JButton("로그인");
	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpMain.setEmtBorder(20, 20, 20, 20);

		jpTop.add(new BaseLabel("로그인").setTextCenter().setFontSize(25));

		jpCenter.addChild();
		jpCenter.jpLeft.setGrid(2, 1, 0, 10);
		jpCenter.jpLeft.add(new BaseLabel("아이디"));
		jpCenter.jpLeft.add(new BaseLabel("비밀번호"));

		jpCenter.jpCenter.setGrid(2, 1, 0, 10);
		jpCenter.jpCenter.add(jtfId);
		jpCenter.jpCenter.add(jtfPw);

		jpCenter.setEmtBorder(10, 0, 20, 0);

		jpBottom.add(jbLogin);

	}

	@Override
	public void event() {
		// TODO Auto-generated method stub
		jbLogin.addActionListener(e -> {

			String id = jtfId.getText();
			String pw = jtfPw.getText();

			System.out.println("id : " + id);
			System.out.println("pw : " + pw);

			if (id.isBlank() || pw.isBlank()) {
				Msg.error("빈칸이 있습니다.");
				return;
			}
			if (id.equals("admin") && pw.equals("1234")) {
				System.out.println("관리자 로그인");
				return;
			}
			Vector<String> userData;
			try {
				userData = DbManager.db.getDb("SELECT * FROM 2_2023지방_2.user where u_id = ? and u_pw = ?;", id, pw)
						.get(0);

			} catch (Exception e2) {
				// TODO: handle exception
				Msg.error("아이디 또는 비밀번호를 확인하세요.");
				jtfId.setText("");
				jtfPw.setText("");

				jtfId.requestFocus();

				count += 1;
				System.out.println("틀린 횟수 : " + count);

				if (count == 3) {
					Msg.error("3회 오류로 종료합니다.");
					super.dispose();
					return;
				}

				return;

			}

			UserModel.login(userData);
			System.out.println("로그인 성공");
			mainForm.logRefresh();
			super.dispose();

		});
	}

}
