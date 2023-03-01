package windows;

import java.awt.Color;
import java.util.Vector;

import bases.BaseFrame;
import bases.BaseJLabel;
import bases.BasePanel;
import jdbc.DbManager;

public class GraphForm extends BaseFrame {
	private BasePanel jpGraph;
	private Vector<Vector<String>> bookRankInfo;
	private Vector<Color> colorData;

	public GraphForm() {
		// TODO Auto-generated constructor stub
		setFrame("통계", 550, 450);
	}

	@Override
	public void made() {
		// TODO Auto-generated method stub

		colorData = new Vector<Color>();
		colorData.add(Color.red);
		colorData.add(Color.orange);
		colorData.add(Color.yellow);
		colorData.add(Color.green);
		colorData.add(Color.blue);

		jpGraph = new BasePanel().setGrid(1, 5, 40, 0);
	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpTop.add(new BaseJLabel("인기 도서").setFontSize(25).setTextCenter());
		jpCenter.add(jpGraph);

		drawGraph();
		
		jpMain.setBorder(10, 60, 40, 60);
	}

	@Override
	public void event() {
		// TODO Auto-generated method stub

	}

	private void drawGraph() {
		// TODO Auto-generated method stub
		bookRankInfo = DbManager.db.getDb("SELECT \r\n" + "b_name,\r\n" + "count(r.b_no) \r\n"
				+ "FROM 2023지방_2.rental as r\r\n" + "join book as b\r\n" + "on r.b_no = b.b_no\r\n"
				+ "group by r.b_no\r\n" + "order by count(r.b_no) desc, b.b_no\r\n" + "limit 5\r\n" + ";");

		int maxValue = Integer.parseInt(bookRankInfo.get(0).get(1));

		int i = 0;
		for (Vector<String> row : bookRankInfo) {

			int value = Integer.parseInt(row.get(1));
			System.out.println(value);

			BasePanel tmp = new BasePanel();
			tmp.addChild();
			tmp.jpTop.add(new BaseJLabel(value + "건").setTextCenter());
			tmp.jpTop.setBorder((maxValue - value) * 15, 0, 0, 0);

			tmp.jpCenter.setBackground(colorData.get(i++));
			tmp.jpCenter.setLine();
			tmp.jpBottom.add(new BaseJLabel(row.get(0)));

			jpGraph.add(tmp);

		}

	}

}
