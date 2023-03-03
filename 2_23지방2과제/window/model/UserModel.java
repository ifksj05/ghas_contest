package model;

import java.util.Vector;

public class UserModel {

	public static boolean log = false;
	public static boolean admin = false;
	public static Vector<String> userData;

	public static void login(Vector<String> userData) {
		// TODO Auto-generated method stub
		UserModel.log = true;
		UserModel.userData = userData;

	}

	public static void logout() {
		// TODO Auto-generated method stub
		UserModel.log = false;
		UserModel.admin = false;
		UserModel.userData = null;

	}

	public static void admin() {
		// TODO Auto-generated method stub
		UserModel.admin = true;
		UserModel.userData = null;

	}

}
