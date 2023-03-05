package windows;


import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import base.comp.BaseFrame;
import base.comp.BaseLabel;
import data.DataManager;
import jdbc.DbManager;
import message.MessageShow;
import res.ResModels;

public class LoginFrame extends BaseFrame{

	private JTextField jtId;
	private JPasswordField jpPw;
	private JButton jbLogin;
	private Vector<JTextField> compInput;
	private Vector<Vector<String>> datas;
	private MainFrame mainFrame;
	private int cnt = 0;

	
	
	public LoginFrame(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.mainFrame = mainFrame;
		super.setFrame("로그인", 413, 227, mainFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		
		compInput = new Vector<JTextField>();
		compInput.add(jtId = new JTextField());
		
		// 6p 1)조건
		compInput.add(jpPw = new JPasswordField());
		
		
		jbLogin = new JButton("로그인");
		
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		
		jpTop.add(new BaseLabel("로그인", 25).setCenter());
		
		jpLeft.setGridLayout(2, 1, 5, 10);
		jpLeft.add(new BaseLabel("아이디"));
		jpLeft.add(new BaseLabel("비밀번호"));

		jpCenter.setGridLayout(2, 1, 5, 10);
		jpCenter.add(jtId);
		jpCenter.add(jpPw);
		
		jpBottom.add(jbLogin);
		
		jpMain.setEmptyBorder(20);
		jpLeft.setEmptyBorder(0, 15, 0, 20);
		jpCenter.setEmptyBorder(0, 5, 0, 20);
	}

	@Override
	public void setAction() {
		// TODO Auto-generated method stub

		// 6p 2)
		jbLogin.addActionListener(e -> {
			
			
			String id = jtId.getText().trim();
			String pw = jpPw.getText().trim();
			
			// 6p 2)-1조건
			if(DataManager.isBlank(compInput)) return;
			
			// 6p 2)-4조건(핵심)
			if(id.equals("admin") && pw.equals("1234")) {
				ResModels.login = new Vector<>();
				mainFrame.managerLogin();
				close();
				return;
			}
			
			datas = DbManager.db.getData("SELECT * FROM 2023지방_2.user where u_id = ? and u_pw = ?;", 
					id, pw);
			

			
			if(datas.size() == 0) {
				// 6p 2)-2조건
				MessageShow.error("아이디 또는 비밀번호를 확인하세요.");
				DataManager.jtInit(compInput);
								
				// 6p 2)-5조건
				cnt ++;
				
				if(cnt == 3) {
					MessageShow.error("3회 오류로 종료합니다.");
					close();
				}
				
				return;
			}
			
			// 6p 2)-3조건(핵심)
			ResModels.login = datas.get(0);
			mainFrame.login();
			close();
			
		});
	}


}
