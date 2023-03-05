package windows;


import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JTextArea;

import base.comp.BaseFrame;
import base.comp.BaseLabel;
import base.comp.BasePanel;
import base.comp.ImageLabel;
import image.ImageModel;
import jdbc.DbManager;
import message.MessageShow;
import res.ResModels;

public class BookInfoFrame extends BaseFrame{

	private ImageModel data;
	private JButton jbRental;
	private JTextArea jtExp;
	private BookListFrame bookListFrame;

	public BookInfoFrame(MainFrame mainFrame, ImageModel data) {
		// TODO Auto-generated constructor stub
		this.data = data;
		super.setFrame("도서 정보", 408, 438, mainFrame);
	}

	public BookInfoFrame(MainFrame mainFrame, BookListFrame bookListFrame, ImageModel data) {
		// TODO Auto-generated constructor stub
		this.data = data;
		this.bookListFrame = bookListFrame;
				
		super.setFrame("도서 정보", 408, 438, bookListFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub
		jbRental = new JButton("대출");
		
		jtExp = new JTextArea();
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub

		// p9 1)조건
		String b_no = data.getData().get(9);
		String b_autor = "저자 : " + data.getData().get(3);
		String b_count_page = "재고 : " + data.getData().get(4) + "권 / 페이지 : " + data.getData().get(5) + "p";
		String b_exp = data.getData().get(6);
		
		jpTop.add(new BaseLabel(data.getData().get(1), 30));


		jpLeft.add(new ImageLabel("", data.getIcon(), 200, 250));
		
		jpCenter.addChild();
		
		jpCenter.jpTop.setFlowLayoutLeft().add(new BaseLabel(b_no, 20).setLine());

		jpCenter.jpCenter.setGridLayout(4, 1);

		jpCenter.jpCenter.add(new BaseLabel(b_autor, 15).setBottomLine());
		jpCenter.jpCenter.add(new BaseLabel(b_count_page, 15).setBottomLine());
		jpCenter.jpCenter.add(new BasePanel());
		jpCenter.jpCenter.add(new BasePanel());
		
		
		jpBottom.addChild();
		jtExp.setText(b_exp);


		jpBottom.jpCenter.add(new BasePanel(jtExp).setTitle("설명", 20));
		jpBottom.jpBottom.setFlowLayoutRight().add(jbRental);
		

		// p9 2)-3조건
		if(data.getData().get(4).equals("0")) {
			jbRental.setEnabled(false);
		}

		jtExp.setEditable(false);
		jtExp.setLineWrap(true);

		jpMain.setEmptyBorder(10);
		
		super.pack();
	}

	@Override
	public void setAction() {
		// TODO Auto-generated method stub
		jbRental.addActionListener(e -> {
			String b_no = this.data.getData().get(0);
			String u_no = ResModels.login.get(0);
			
			// p9 2)-2조건
			Vector<Vector<String>> datas = DbManager.db.getData(
				 "select u_no from rental where u_no = ? and (r_returnday is null or r_returnday = '0000-00-00') and date_add(r_date, interval (r_count + 14) day) < now();",
				 u_no
			 );
			 if(datas.size() != 0) {
				 MessageShow.error("현재 연체 중인 도서가 있습니다.\n도서를 반납하시고 이용해주세요.");
				 return;
			 }
			 
			// p9 2)-1조건
			 datas = DbManager.db.getData(
					 "SELECT r_no, u_no, b_no, r_date, r_count, r_reading, \r\n"
			 + "	if(r_returnday = null or r_returnday = '0000-00-00', null, r_returnday)\r\n"
			 + "    FROM 2023지방_2.rental where u_no = ? and b_no = ?;",
			 u_no, b_no);
			 
			 if(datas.size() != 0) {
				 MessageShow.error("이미 대출 중인 도서입니다.");
				 return;
			 }

			 
			 // p9 2)-4조건
			DbManager.db.setData("INSERT INTO `2023지방_2`.`rental` (`u_no`, `b_no`, `r_date`, `r_count`, `r_reading`) VALUES (?, ?, now(), '0', '0');"
					, u_no, b_no);

			DbManager.db.setData("UPDATE `2023지방_2`.`book` SET `b_count` = b_count - 1 WHERE (`b_no` = ?);"
					, b_no);
			
			MessageShow.info("대출이 완료되었습니다.");

			close();
			if(bookListFrame != null) bookListFrame.close();
		});
	}
}
