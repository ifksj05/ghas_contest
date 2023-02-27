package windows;

import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bases.BaseFrame;
import bases.BaseJLabel;
import jdbc.DbManager;

public class LoginForm extends BaseFrame {
	private JTextField jtfId;
	private JPasswordField jtfPw;
	private JButton jbLogin;
	private DbManager db;

	public LoginForm() {
		// TODO Auto-generated constructor stub
		setFrame("로그인", 450, 280);
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
		jpMain.setBorder(30, 30, 30, 30);

		jpTop.add(new BaseJLabel("로그인").setFontSize(25).setTextCenter());

		jpCenter.addChild();
		jpCenter.jpLeft.setGrid(2, 1, 0, 0);
		jpCenter.jpLeft.add(new BaseJLabel("아이디"));
		jpCenter.jpLeft.add(new BaseJLabel("비밀번호"));

		jpCenter.jpCenter.setGrid(2, 1, 0, 0);
		jpCenter.jpCenter.add(jtfId);
		jpCenter.jpCenter.add(jtfPw);

		jpBottom.add(jbLogin);

	}

	@Override
	public void event() {
		// TODO Auto-generated method stub
		db = new DbManager();
		jbLogin.addActionListener(e -> {

			String id = jtfId.getText();
			String pw = jtfPw.getText();

			System.out.println("input id : " + id);
			System.out.println("input pw : " + pw);

			Vector<String> userData;
			try {
				userData = db.getDb("SELECT * FROM 2023지방_2.user where u_id = ? and u_pw = ?;", id, pw).get(0);

			} catch (Exception e2) {
				// TODO: handle exception
				System.out.println("로그인 실패");
				return;
			}

			System.out.println("로그인 성공");

			super.dispose();

		});
	}
}
