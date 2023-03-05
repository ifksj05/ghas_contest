package windows;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import base.comp.BaseComboBox;
import base.comp.BaseFrame;
import base.comp.BaseLabel;
import base.comp.BasePanel;
import base.comp.BaseTable;
import base.comp.ImageLabel;
import base.comp.NullPanel;
import image.ImageModel;
import jdbc.DbManager;
import message.MessageShow;
import res.ResModels;

public class BookListFrame extends BaseFrame {

	private BaseComboBox jcSearch;
	private JTextField jtSearch;
	private JButton jbSearch;
	private BaseTable jspLeft;

	private JScrollPane jspCenter;
	private BasePanel jpGrid;
	private BaseLabel jlCnt;
	private MainFrame mainFrame;

	public BookListFrame(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.mainFrame = mainFrame;
		super.setFrame("도서목록", 900, 500, mainFrame);
	}

	@Override
	public void setComp() {
		// TODO Auto-generated method stub

		// 8p 4)조건
		jcSearch = new BaseComboBox("도서명", "저자");
		jtSearch = new JTextField(10);
		jbSearch = new JButton("검색");

		// 테이블
//		jTable = new BaseTable(ResModels.divisionTable, 0, "", "분류");
//		jTable = new BaseTable(ResModels.divisionTable, 1, "분류", "");
//		super.firstDataAdd(
//			ResModels.getDivisionTable(), 
//			"전체"
//		);
		jspLeft = new BaseTable(ResModels.getDivisionTableAll(), 1, "분류", "");

		jpGrid = new BasePanel().setGridLayout(0, 4, 10, 0);
		jspCenter = new JScrollPane(jpGrid);

		jlCnt = new BaseLabel("", 16);
	}

	@Override
	public void setDesign() {
		// TODO Auto-generated method stub

		jpTop.addChild();
		jpTop.jpCenter.add(new BaseLabel("도서 목록", 25).setCenter());
		jpTop.jpBottom.setFlowLayoutRight();
		jpTop.jpBottom.add(new BaseLabel("검색"));
		jpTop.jpBottom.add(jcSearch);
		jpTop.jpBottom.add(jtSearch);
		jpTop.jpBottom.add(jbSearch);

		jpLeft.add(jspLeft);

		jpCenter.add(jspCenter);

		// 8p 1)조건
		jspLeft.jTable.changeSelection(0, 0, false, false);
		gridChange();

		jtSearch.setPreferredSize(new Dimension(0, 30));

		jpBottom.setFlowLayoutRight();
		jpBottom.add(jlCnt);

		jspLeft.setSize(120, 0);

		jpMain.setEmptyBorder(20);
		jpCenter.setEmptyBorder(10, 0, 10, 0);

//		jspLeft.jTable.setSelect
	}

	@Override
	public void setAction() {
		// TODO Auto-generated method stub

		jbSearch.addActionListener(e -> {
			gridChange();
		});
	}

	private void gridChange() {
		// TODO Auto-generated method stub

		jpGrid.removeAll();

		int comboIndex = jcSearch.getSelectedIndex();
		int tableIndex = jspLeft.jTable.getSelectedRow();

		String name = "%";
		String author = "%";

		String division = jspLeft.datas.get(tableIndex).get(1);
		String search = jtSearch.getText().trim();

		// 8p 5)-2조건
		if (comboIndex == 0) {
			name = search.replaceAll(" ", "");
		} else {
			author = search.replaceAll(" ", "");
		}

		// 8p 5)-1, 5)-2, 5)-3조건
		Vector<ImageModel> datas = DbManager.db.getImageData("SELECT * FROM 2023지방_2.book as b\r\n"
				+ "	join division as d\r\n" + "		on b.d_no = d.d_no\r\n" + "	where d.d_no like ?\r\n"
				+ "		and replace(b.b_name, ' ', '') like concat('%', ?, '%')\r\n"
				+ "		and replace(b.b_author, ' ', '') like concat('%', ?, '%')       \r\n" + "    group by b.b_no;",
				7, division, name, author);

		// 8p 5)-4조건
		if (datas.size() == 0) {
			MessageShow.error("검색결과가 없습니다.");
			jspLeft.jTable.changeSelection(0, 0, false, false);
			jtSearch.setText("");
			gridChange();
			return;
		}

		for (ImageModel row : datas) {
			String b_no = row.getData().get(0);
			String b_name = row.getData().get(1);

			ImageLabel jlImg = new ImageLabel(b_name, row.getIcon(), 140, 150).setBottom().setLine().setCenter()
					.setFontSize(11);

			// 8p 2)조건
			BaseLabel likeLabel = new BaseLabel("♡", 20);

			Vector<Vector<String>> likeData = DbManager.db.getData(
					"SELECT * FROM 2023지방_2.likebook\r\n" + "	where u_no = ? and b_no = ?;", ResModels.login.get(0),
					b_no);

			if (likeData.size() != 0) {
				likeLabel.setText("♥");
			}

			BasePanel tempPanel = new NullPanel(160, 180, jlImg, 0, 0, 150, 180, likeLabel, 5, 10, 20, 20);

			likeLabel.setForeground(Color.RED);

			// 8p 3)조건
			likeLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);
					if (likeLabel.getText().equals("♥")) {

						DbManager.db.setData("DELETE FROM `2023지방_2`.`likebook` WHERE (u_no = ? and b_no = ?);",
								ResModels.login.get(0), b_no);

						likeLabel.setText("♡");
					} else {
						DbManager.db.setData("INSERT INTO `2023지방_2`.`likebook` (`u_no`, `b_no`) VALUES (?, ?);",
								ResModels.login.get(0), b_no);
						likeLabel.setText("♥");
					}
				}
			});

			// 8p 6)조건
			BookListFrame bookListFrame = this;
			jlImg.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					super.mousePressed(e);
					new BookInfoFrame(mainFrame, bookListFrame, row);
				}
			});

			tempPanel.add(likeLabel);
			tempPanel.add(jlImg);

			jpGrid.add(tempPanel);
		}

		// 빈 여백
		if (datas.size() < 8) {
			for (int i = 0; i < 8 - datas.size(); i++) {
				jpGrid.add(new BasePanel());
			}
			jspCenter.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		} else {
			jspCenter.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		}
		jspCenter.getVerticalScrollBar().setValue(0);

		jlCnt.setText("검색건수 : " + datas.size() + "건");

		super.reFresh();
	}
}
