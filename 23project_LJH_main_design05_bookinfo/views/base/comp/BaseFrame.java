package base.comp;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;

import base.IDesign;
import jdbc.DbManager;
import message.MessageShow;
import windows.MainFrame;

public abstract class BaseFrame extends JFrame implements IDesign{

	public BasePanel jpMain;
	
	public BasePanel jpCenter;
	public BasePanel jpLeft;
	public BasePanel jpRight;
	public BasePanel jpTop;
	public BasePanel jpBottom;

	private BaseFrame preFrame;

	public void setFrame(String title, int w, int h, BaseFrame preFrame) {
		// TODO Auto-generated constructor stub
		
		super.setSize(w, h);
		super.setTitle(title);
		
		
		this.preFrame = preFrame;
		
		jpMain = new BasePanel();

		jpCenter = new BasePanel();
		jpLeft = new BasePanel();
		jpRight = new BasePanel();
		jpTop = new BasePanel();
		jpBottom = new BasePanel();
		
		jpMain.add(jpCenter, BorderLayout.CENTER);
		jpMain.add(jpLeft, BorderLayout.WEST);
		jpMain.add(jpRight, BorderLayout.EAST);
		jpMain.add(jpTop, BorderLayout.NORTH);
		jpMain.add(jpBottom, BorderLayout.SOUTH);
		
		super.add(jpMain, BorderLayout.CENTER);

		setComp();
		setDesign();
		setAction();
		
		// 테스트용
		super.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				super.componentResized(e);
				System.out.println(getSize());
			}
		});
		
		// design02에서 추가
		ImageIcon icon = new ImageIcon("./datafiles/logo.png");
		super.setIconImage(icon.getImage());
		
		
		// design03에서 추가
		super.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
//				super.windowClosing(e);
				close();
			}
		});
		
		// design03에서 추가
		// X 버튼 변화 없게 하기
		super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		super.setLocationRelativeTo(null);
		super.setVisible(true);
	}
	

	public void reFresh() {
		// TODO Auto-generated method stub
		super.repaint();
		super.validate();
	}

	public void close() {
		// TODO Auto-generated method stub
		this.dispose();
		
		if(preFrame != null) {
//			preFrame.setAlwaysOnTop(true);
			preFrame.setVisible(true);
			
//			System.out.println(preFrame.getState());	// 출력해보면 조금 더 자세히 알 수 있다.
				// 최소화 한 상태에서는 상태(state)는 1이다. 이 때는 setVisible(true) 해도 화면 안나오고 가려진다.
			preFrame.setState(JFrame.NORMAL);	// 이거 입력 해야 최소화 된 창을 열 수 있다.
			// 최소화 된 상태를 노멀 -> 기본 모드로 변경한 것.
		}

//		preFrame.setAlwaysOnTop(false);
	}
}
