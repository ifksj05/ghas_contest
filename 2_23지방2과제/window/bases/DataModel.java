package bases;

import java.util.Vector;

import jdbc.DbManager;

public class DataModel {

	public static Vector<Vector<String>> getAllType() {
		// TODO Auto-generated method stub
		Vector<Vector<String>> resultData = new Vector<Vector<String>>();
		Vector<Vector<String>> bookType = DbManager.db.getDb("SELECT d_name FROM 2_2023지방_2.division;");

		Vector<String> tmpRow = new Vector<String>();
		tmpRow.add("전체");

		resultData.add(tmpRow);
		for (Vector<String> row : bookType) {
			resultData.add(row);
		}

		return resultData;
	}

}
