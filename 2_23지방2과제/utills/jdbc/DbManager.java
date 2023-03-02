package jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import messge.Msg;
import model.ImageModel;

public class DbManager {

	private String url = "jdbc:mysql://localhost/2_2023지방_2?" + "ChacterEncoding=UTF-8&" + "serverTimezone=UTC&"
			+ "allowPublicKeyRetrieval=true&" + "allowLoadLocalInfile=true&" + "allowMultiQueries=true";
	private String id = "user";
	private String pw = "1234";
	private Connection con;
	private PreparedStatement pstmt;

	public static DbManager db = new DbManager();

	public DbManager() {
		// TODO Auto-generated constructor stub
		try {
			con = DriverManager.getConnection(url, id, pw);
			System.out.println("con 연결 성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int setDb(String sql, Object... vals) {
		// TODO Auto-generated method stub
		try {
			pstmt = con.prepareStatement(sql);

			int cnt = 1;
			for (Object val : vals) {
				pstmt.setObject(cnt++, val);

			}

			System.out.println("setDb 성공");
			return pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("setDb 실패");
			return -1;
		}
	}

	public Vector<Vector<String>> getDb(String sql, Object... vals) {
		// TODO Auto-generated method stub
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
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					row.add(rs.getObject(i + 1) + "");
				}

				data.add(row);

			}

			System.out.println("getDb 성공");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("getDb 실패");
			return null;
		}
		return data;
	}

	public Vector<ImageModel> getImageModel(String sql, int imgIndex, Object... vals) {
		// TODO Auto-generated method stub
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
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					row.add(rs.getObject(i + 1) + "");
				}

				Blob blob = rs.getBlob(imgIndex + 1);
				ImageIcon img = new ImageIcon(blob.getBinaryStream().readAllBytes());

				ImageModel model = new ImageModel(row, img);

				data.add(model);

			}

			System.out.println("getDb 성공");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("getDb 실패");
			return null;
		}
		return data;
	}
}
