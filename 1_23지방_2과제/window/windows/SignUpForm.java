package windows;

import javax.swing.JButton;
import javax.swing.JTextField;

import bases.BaseFrame;
import bases.BaseJLabel;
import jdbc.DbManager;

public class SignUpForm extends BaseFrame {
	private JTextField jtfName;
	private JTextField jtfId;
	private JTextField jtfPw;
	private JButton jbSignUp;
	private DbManager db;

	public SignUpForm() {
		// TODO Auto-generated constructor stub
		setFrame("회원가입", 450, 350);
	}

	@Override
	public void made() {
		// TODO Auto-generated method stub
		jtfName = new JTextField();
		jtfId = new JTextField();
		jtfPw = new JTextField();

		jbSignUp = new JButton("회원가입");

	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpMain.setBorder(30, 30, 30, 30);

		jpTop.add(new BaseJLabel("회원가입").setTextCenter().setFontSize(30));

		jpCenter.addChild();
		jpCenter.jpLeft.setGrid(3, 1, 0, 0);
		jpCenter.jpLeft.add(new BaseJLabel("이름"));
		jpCenter.jpLeft.add(new BaseJLabel("아이디"));
		jpCenter.jpLeft.add(new BaseJLabel("비밀번호"));

		jpCenter.jpCenter.setGrid(3, 1, 0, 0);
		jpCenter.jpCenter.add(jtfName);
		jpCenter.jpCenter.add(jtfId);
		jpCenter.jpCenter.add(jtfPw);

		jpBottom.add(jbSignUp);

	}

	@Override
	public void event() {
		// TODO Auto-generated method stub
		db = new DbManager();
		jbSignUp.addActionListener(e -> {

			String name = jtfName.getText();
			String id = jtfId.getText();
			String pw = jtfPw.getText();

			System.out.println("input name : " + name);
			System.out.println("input id : " + id);
			System.out.println("input pw : " + pw);

			db.setDb("INSERT INTO `2023지방_2`.`user` (`u_name`, `u_id`, `u_pw`) VALUES (?, ?, ?);", name, id, pw);

			System.out.println("회원가입 완료");

			super.dispose();

		});
	}

}
