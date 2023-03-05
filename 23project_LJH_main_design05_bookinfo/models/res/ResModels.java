package res;

import java.util.Vector;

import data.DataManager;
import jdbc.DbManager;

public class ResModels {

	public static Vector<String> login = null;

	
	public static Vector<Vector<String>> getDivisionTableAll() {
		Vector<Vector<String>> data = DbManager.db.getData("SELECT d_name, d_no FROM 2023지방_2.division;");

		return DataManager.firstDataAdd(data, "전체", "%");
	
	}

	public static Vector<Vector<String>> getDivisionTableBlank() {
		Vector<Vector<String>> data = DbManager.db.getData("SELECT d_name, d_no FROM 2023지방_2.division;");
		
		return DataManager.firstDataAdd(data, "", "%");
	}
}
