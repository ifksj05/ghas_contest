package jdbc;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ImageIcon;

import image.ImageModel;

public class DbManager {
//	+ "zeroDateTimeBehavior=convertToNull&"
	
	private static final String URL = "jdbc:mysql://localhost/2023지방_2?" 
			+ "useSSL=false&"
			+ "allowPublicKeyRetrieval=true&" 
			+ "ChacterEncoding=UTF-8&" 
			+ "serverTimezone=UTC&"
			+ "allowLoadLocalInfile=true&"
			+ "allowMultiQueries=true";

	// 보보문시
	// 데여

	private static final String ID = "root";
//	private static final String ID = "user";
	private static final String PW = "1234";
	private Connection con;
	private PreparedStatement pstmt;

	public static DbManager db = new DbManager();

	public DbManager() {
		// TODO Auto-generated constructor stub
		try {
			con = DriverManager.getConnection(URL, ID, PW);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int setData(String sql, Object... vals) {
		// TODO Auto-generated constructor stub

		try {
			pstmt = con.prepareStatement(sql);

			int cnt = 1;
			for (Object val : vals) {
				pstmt.setObject(cnt++, val);
			}

			return pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}

	public Vector<Vector<String>> getData(String sql, Object... vals) {
		// TODO Auto-generated constructor stub
		Vector<Vector<String>> data = new Vector<Vector<String>>();

		try {
			pstmt = con.prepareStatement(sql);

			int cnt = 1;
			for (Object val : vals) {
				pstmt.setObject(cnt++, val);
			}

			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();

			while (rs.next()) {
				Vector<String> row = new Vector<String>();

				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					row.add(rs.getObject(i) + "");
				}

				data.add(row);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}

	public Vector<ImageModel> getImageData(String sql, int imageColIndex, Object... vals) {
		// TODO Auto-generated constructor stub

		Vector<ImageModel> data = new Vector<ImageModel>();

		try {
			pstmt = con.prepareStatement(sql);

			int cnt = 1;
			for (Object val : vals) {
				pstmt.setObject(cnt++, val);
			}

			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();

			while (rs.next()) {
				Vector<String> row = new Vector<String>();

				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					row.add(rs.getObject(i) + "");
				}

				// blob 이미지 불러오기
				Blob blob = rs.getBlob(imageColIndex + 1);
				ImageIcon icon = null;
				try {
					icon = new ImageIcon(blob.getBinaryStream().readAllBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				ImageModel model = new ImageModel(row, icon);

				data.add(model);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return data;
	}
}
