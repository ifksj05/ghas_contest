package jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
	public static void main(String[] args) {
		new DbManager();
	}

	private String URL = "jdbc:mysql://localhost/2023지방_2?" + "ChacterEncoding=UTF-8&" + "serverTimezone=UTC&"
			+ "allowPublickeyRetrieval=true&" + "allowLoadLocalInfile=true&" + "allowMultiQueries=true";
	private String USER = "root";
	private String PASSWORD = "1234";
	private Connection con;
	private PreparedStatement pstmt;

	public static DbManager db = new DbManager();

	public DbManager() {
		// TODO Auto-generated constructor stub
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("con 연결 성공");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int setDb(String sql, Object... values) {
		// TODO Auto-generated method stub
		try {
			pstmt = con.prepareStatement(sql);

			int cnt = 1;
			for (Object value : values) {
				pstmt.setObject(cnt++, value);
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

	public Vector<Vector<String>> getDb(String sql, Object... values) {
		// TODO Auto-generated method stub
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		try {
			pstmt = con.prepareStatement(sql);

			int cnt = 1;
			for (Object value : values) {
				pstmt.setObject(cnt++, value);
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

	public Vector<ImageModel> getImageModel(String sql, int ImgColIndex, Object... values) {
		// TODO Auto-generated method stub
		Vector<ImageModel> data = new Vector<ImageModel>();
		try {
			pstmt = con.prepareStatement(sql);

			int cnt = 1;
			for (Object value : values) {
				pstmt.setObject(cnt++, value);
			}

			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();

			while (rs.next()) {

				Vector<String> row = new Vector<String>();
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					row.add(rs.getObject(i + 1) + "");
				}

				Blob blob = rs.getBlob(ImgColIndex + 1);

				ImageIcon icon = new ImageIcon(blob.getBinaryStream().readAllBytes());
				ImageModel model = new ImageModel(row, icon);

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

	public ImageIcon getImg(String sql, Object... values) {
		// TODO Auto-generated method stub
		ImageIcon img = new ImageIcon();
		try {
			pstmt = con.prepareStatement(sql);

			int cnt = 1;
			for (Object value : values) {
				pstmt.setObject(cnt++, value);
			}

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {

				img = new ImageIcon(rs.getBlob(1).getBinaryStream().readAllBytes());

			}

			System.out.println("getImg 성공");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("getImg 실패");
			return null;
		}
		return img;
	}

}
