package windows;

import bases.BaseFrame;
import bases.BaseJLabel;

public class BorrowPlusForm extends BaseFrame {
	private BaseJLabel jlDate;
	private BaseJLabel jlDateUp;
	private BaseJLabel jlDateDown;

	public BorrowPlusForm() {
		// TODO Auto-generated constructor stub
		setFrame("대출연장", 430, 450);
	}

	@Override
	public void made() {
		// TODO Auto-generated method stub
		jlDateUp = new BaseJLabel("▶").setFontSize(25);
		jlDate = new BaseJLabel("2023년 04월").setFontSize(20);
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

		jpTop.jpBottom.setGrid(1, 7, 0, 0);
		jpTop.jpBottom.add(new BaseJLabel("일").setTextCenter());
		jpTop.jpBottom.add(new BaseJLabel("월").setTextCenter());
		jpTop.jpBottom.add(new BaseJLabel("화").setTextCenter());
		jpTop.jpBottom.add(new BaseJLabel("수").setTextCenter());
		jpTop.jpBottom.add(new BaseJLabel("목").setTextCenter());
		jpTop.jpBottom.add(new BaseJLabel("금").setTextCenter());
		jpTop.jpBottom.add(new BaseJLabel("일").setTextCenter());

		jpCenter.setGrid(6, 7, 0, 0);

	}

	@Override
	public void event() {
		// TODO Auto-generated method stub

	}

}
