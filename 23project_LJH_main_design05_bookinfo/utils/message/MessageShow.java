package message;

import javax.swing.JOptionPane;

public class MessageShow {

	public static void info(String msg) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, msg, "정보", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void error(String msg) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, msg, "경고", JOptionPane.ERROR_MESSAGE);
	}

}
