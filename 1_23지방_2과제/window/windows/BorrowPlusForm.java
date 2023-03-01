package windows;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Iterator;

import bases.BaseFrame;
import bases.BaseJLabel;
import bases.BasePanel;

public class BorrowPlusForm extends BaseFrame {
	private BaseJLabel jlDate;
	private BaseJLabel jlDateUp;
	private BaseJLabel jlDateDown;
	private Calendar cal;
	private int Year;
	private int Month;

	public BorrowPlusForm() {
		// TODO Auto-generated constructor stub
		// 시작 현제 월로 시작.
		cal = Calendar.getInstance();
		Year = cal.get(Calendar.YEAR);
		Month = cal.get(Calendar.MONTH);
		cal.set(Year, Month, 1);

		setFrame("대출연장", 430, 450);
	}

	@Override
	public void made() {
		// TODO Auto-generated method stub
		jlDateUp = new BaseJLabel("▶").setFontSize(25);
		jlDate = new BaseJLabel(Year + "년 " + String.format("%02d", (Month + 1)) + "월").setFontSize(20);
		jlDateDown = new BaseJLabel("◀").setFontSize(25);

	}

	@Override
	public void design() {
		// TODO Auto-generated method stub
		jpTop.addChild();
		jpTop.jpTop.setFlowCenter();
		jpTop.jpTop.add(jlDateDown);
		jpTop.jpTop.add(jlDate);
		jpTop.jpTop.add(jlDateUp);

		jpTop.jpBottom.setGrid(1, 7, 5, 5);
		jpTop.jpBottom.add(new BaseJLabel("일").setTextCenter());
		jpTop.jpBottom.add(new BaseJLabel("월").setTextCenter());
		jpTop.jpBottom.add(new BaseJLabel("화").setTextCenter());
		jpTop.jpBottom.add(new BaseJLabel("수").setTextCenter());
		jpTop.jpBottom.add(new BaseJLabel("목").setTextCenter());
		jpTop.jpBottom.add(new BaseJLabel("금").setTextCenter());
		jpTop.jpBottom.add(new BaseJLabel("일").setTextCenter());

		jpCenter.setGrid(6, 7, 5, 5);
		setDateRefresh();

	}

	@Override
	public void event() {
		// TODO Auto-generated method stub
		jlDateUp.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);

				System.out.println("날짜 증가");
				monthUp();
				setDateRefresh();

			}

		});
		jlDateDown.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mousePressed(e);

				System.out.println("날짜 감소");
				monthDown();
				setDateRefresh();

			}

		});
	}

	private void monthUp() {
		// TODO Auto-generated method stub

		cal.add(Calendar.MONTH, +1);

		Year = cal.get(Calendar.YEAR);
		Month = cal.get(Calendar.MONTH);

		System.out.println("now year : " + Year);
		System.out.println("now month : " + (Month + 1));

	}

	private void monthDown() {
		// TODO Auto-generated method stub

		cal.add(Calendar.MONTH, -1);

		Year = cal.get(Calendar.YEAR);
		Month = cal.get(Calendar.MONTH);

		System.out.println("now year : " + Year);
		System.out.println("now month : " + (Month + 1));
	}

	private void setDateRefresh() {
		// TODO Auto-generated method stub
		jpCenter.removeAll();

		int startWeek = cal.get(Calendar.DAY_OF_WEEK);
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		System.out.println("weekStart : " + startWeek);
		System.out.println("lastDay : " + lastDay);

		jlDate.setText(Year + "년 " + String.format("%02d", (Month + 1)) + "월");

		for (int i = 0; i < startWeek - 1; i++) {
			jpCenter.add(new BaseJLabel());
		}

		for (int i = 1; i <= lastDay; i++) {
			BasePanel tmp = new BasePanel().setLine();
			tmp.add(new BaseJLabel(i + "").setTextCenter());
			jpCenter.add(tmp);
		}

		for (int i = 0; i < 42 - startWeek - lastDay; i++) {
			jpCenter.add(new BaseJLabel());
		}

		super.refresh();

	}

}
