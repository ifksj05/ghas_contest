package bases;

import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BaseTable extends JScrollPane {
	public DefaultTableModel dtm;
	public JTable jtb;

	public BaseTable(Vector<Vector<String>> data, String... colsData) {
		// TODO Auto-generated constructor stub

		Vector<String> cols = new Vector<String>();
		for (String row : colsData) {
			System.out.println(cols);
			cols.add(row);
		}
		dtm = new DefaultTableModel(data, cols);
		jtb = new JTable(dtm);

		super.setViewportView(jtb);
	}
}
