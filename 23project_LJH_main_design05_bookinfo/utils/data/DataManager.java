package data;

import java.util.Vector;

import javax.swing.JTextField;

import message.MessageShow;

public class DataManager {
	
	public static boolean isBlank(Vector<JTextField> jts) {
		// TODO Auto-generated method stub
		for (JTextField jt : jts) {
			if(jt.getText().isBlank()) {
				MessageShow.error("빈칸이 있습니다.");
				return true;
			}
		}
		
		return false;
	}
	
	public static Vector<Vector<String>> firstDataAdd(Vector<Vector<String>> data, String...vals) {
		// TODO Auto-generated constructor stub
		Vector<String> row = new Vector<String>();
		
		for (String val : vals) {
			row.add(val);
		}
		
		data.add(0, row);
		
		return data;
	}
	
	public static String subString(String text, int size, String newText) {
		// TODO Auto-generated method stub
		return (text.length() <= 13? text: text.substring(0, 13)) + newText;
	}

	public static boolean jtInit(Vector<JTextField> jts) {
		// TODO Auto-generated method stub
		for (JTextField jt : jts) {
			jt.setText("");
		}
		
		jts.get(0).requestFocus();
		return false;
	}

}
