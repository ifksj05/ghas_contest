package windows;

import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import base.comp.BaseFrame;
import base.comp.BaseLabel;
import jdbc.DbManager;
import message.MessageShow;

public class JoinFrame extends BaseFrame{

	private Vector<JTextField> compInput;
	private JTextField jtName;
	private JTextField jtId;
	private JTextField jtPw;
	private JButton jbJoin;

	public JoinFrame(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		super.setFrame("회원가입", 361, 258, mainFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		
		compInput = new Vector<JTextField>();
		compInput.add(jtName = new JTextField());
		compInput.add(jtId = new JTextField());
		compInput.add(jtPw = new JTextField());
		
		
		jbJoin = new JButton("회원가입");
		
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub
		
		jpTop.add(new BaseLabel("회원가입", 25).setCenter());
		
		jpLeft.setGridLayout(3, 1, 5, 10);
		jpLeft.add(new BaseLabel("이름"));
		jpLeft.add(new BaseLabel("아이디"));
		jpLeft.add(new BaseLabel("비밀번호"));

		jpCenter.setGridLayout(3, 1, 5, 10);
		jpCenter.add(jtName);
		jpCenter.add(jtId);
		jpCenter.add(jtPw);
		
		jpBottom.add(jbJoin);
		
		jpMain.setEmptyBorder(23);
		jpLeft.setEmptyBorder(0, 15, 0, 10);
		jpCenter.setEmptyBorder(0, 5, 0, 10);
	}

	@Override
	public void setAction() {
		// TODO Auto-generated method stub
		
		jbJoin.addActionListener(e -> {
			String name = jtName.getText().trim();
			String id = jtId.getText().trim();
			String pw = jtPw.getText().trim();

			// 7p 1)-1조건
			if(name.isBlank() || id.isBlank() || pw.isBlank()) {
				MessageShow.error("빈칸이 있습니다.");
				return;
			}
			
			// 7p 1)-2조건 이름
			// https://hbase.tistory.com/160 참고
			// 처음과 끝이 한글 2글자 이상
			String patternName = "^([ㄱ-ㅎㅏ-ㅣ가-힣]+){2,}$";
			
			if(!Pattern.matches(patternName, name)) {
				MessageShow.error("이름은 한글로 2글자 이상만 가능합니다.");
				
				jtName.setText("");
				jtName.requestFocus();
				return;
			}
			
			// 7p 1)-2조건 아이디
			// 아이디 : 한글이 포함된 경우
			String patternId = "^(?=.*[ㄱ-ㅎㅏ-ㅣ가-힣]).+$";
			if(Pattern.matches(patternId, id)) {
				MessageShow.error("아이디에 한글은 사용이 불가능합니다.");
				
				jtPw.setText("");
				jtPw.requestFocus();
				return;
			}
			
			// 아이디 : 이미 있는 아이디일 경우
			Vector<Vector<String>> datas = DbManager.db.getData("SELECT * FROM 2023지방_2.user where u_id = ?;", 
					id);
			
			if(datas.size() != 0 || id.equals("admin")) {
				MessageShow.error("이미 있는 아이디 입니다.");
				
				jtId.setText("");
				jtId.requestFocus();
				return;
			}

			// 7p 1)-3조건 비밀번호
			String patternPW = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$]).{6,}$";
			
			if(!Pattern.matches(patternPW, pw)) {
				MessageShow.error("비밀번호 형식이 일치하지 않습니다.");
				
				jtPw.setText("");
				jtPw.requestFocus();
				return;
			}
			
			
			// 7p 1)-4조건(핵심)
			DbManager.db.setData("INSERT INTO `2023지방_2`.`user` (`u_name`, `u_id`, `u_pw`) VALUES (?, ?, ?);", 
					name, id, pw);
			
			MessageShow.info(name + "님 회원가입이 완료되었습니다.");	
			super.close();
			
			// 문제 조건대로만 할 때 에러
			// 1. 지금 상태로는 숫자 아이디 만들기 가능
			// 2. 이름 5자리 이하, 아이디 10자리 이하, 비밀번호 10자리 이하까지 입력 가능하도록 제어해야 한다.
			//		-> 테이블이 그렇게 설계됨.
			// 등
		});
		
	}
}
