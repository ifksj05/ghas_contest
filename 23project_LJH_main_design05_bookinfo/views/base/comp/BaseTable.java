package base.comp;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class BaseTable extends JScrollPane {

	private DefaultTableModel dtm;
	public JTable jTable;
	public Vector<Vector<String>> datas;

	public BaseTable(Vector<Vector<String>> data, Vector<String> cols) {
		// TODO Auto-generated constructor stub

	}

	public BaseTable(Vector<Vector<String>> data, String... colValues) {
		// TODO Auto-generated constructor stub
		this.datas = data;

		Vector<String> cols = new Vector<String>();
		for (String col : colValues) {
			cols.add(col);
		}

		dtm = new DefaultTableModel(data, cols);
		jTable = new JTable(dtm);

		jTable.getTableHeader().setBackground(Color.WHITE);
		super.getViewport().setBackground(Color.WHITE);

//		tcm.getColumn(0).setCellRenderer(dtcr);  

		super.setViewportView(jTable);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(DefaultListCellRenderer.CENTER);

		for (int i = 0; i < cols.size(); i++) {
			jTable.getColumnModel().getColumn(i).setCellRenderer(dtcr);
		}

	}

	public BaseTable(Vector<Vector<String>> data, int c, String... colValues) {
		// TODO Auto-generated constructor stub
		this(data, colValues);
		for (int i = c; i < colValues.length; i++) {
			jTable.getColumnModel().getColumn(c).setMinWidth(0);
			jTable.getColumnModel().getColumn(c).setMaxWidth(0);
			jTable.getColumnModel().getColumn(c).setWidth(0);
		}
	}

	public BaseTable firstDataAdd(String val) {
		// TODO Auto-generated constructor stub

		Vector<String> row = new Vector<String>();
		row.add(val);
		datas.add(0, row);
		return this;
	}

	@Override
	public void setSize(int width, int height) {
		// TODO Auto-generated method stub
		super.setSize(width, height);
		super.setPreferredSize(new Dimension(width, height));
	}
//    t.getTableHeader().setReorderingAllowed(false);      

//    t.getColumnModel().getColumn(0).setPreferredWidth(20);
//    t.getColumnModel().getColumn(0).setResizable(false);
//    t.getColumnModel().getColumn(1).setPreferredWidth(162);
//    t.getColumnModel().getColumn(3).setPreferredWidth(40);

}
