package bases;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.JComboBox;

public class BaseComboBox extends JComboBox<String> {
	public BaseComboBox(String... strVals) {
		// TODO Auto-generated constructor stub
		for (String val : strVals) {
			super.addItem(val);
		}
	}

	public BaseComboBox addData(Vector<Vector<String>> data, int index) {
		// TODO Auto-generated constructor stub

		super.addItem("");
		for (Vector<String> row : data) {
			super.addItem(row.get(index));
		}

		return this;

	}

	public BaseComboBox addDataBlank(Vector<Vector<String>> data, int index) {
		// TODO Auto-generated constructor stub

		super.addItem("");
		for (Vector<String> row : data) {
			super.addItem(row.get(index));
		}

		return this;

	}

	public BaseComboBox addDataAll(Vector<Vector<String>> data, int index) {
		// TODO Auto-generated constructor stub

		super.addItem("전체");
		for (Vector<String> row : data) {
			super.addItem(row.get(index));
		}

		return this;

	}

}
