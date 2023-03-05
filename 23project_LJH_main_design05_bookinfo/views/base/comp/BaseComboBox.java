package base.comp;

import java.util.Vector;

import javax.swing.JComboBox;


public class BaseComboBox extends JComboBox<String>{

	public Vector<Vector<String>> datas;

	public BaseComboBox(String...vals) {
		// TODO Auto-generated constructor stub
		for (String val : vals) {
			super.addItem(val);
		}
	}
	
	public BaseComboBox(Vector<Vector<String>> data) {
		// TODO Auto-generated constructor stub
		this.datas = data;
		
		for (Vector<String> row : data) {
			super.addItem(row.get(0));
		}
	}

//
//	public String getValue(int r, int c) {
//		// TODO Auto-generated method stub
//		return data.get(r).get(c);
//	}
	

}
