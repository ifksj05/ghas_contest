package model;

import java.util.Vector;

import javax.swing.ImageIcon;

public class ImageModel {

	private Vector<String> data;
	private ImageIcon icon;

	public ImageModel(Vector<String> data, ImageIcon icon) {
		// TODO Auto-generated constructor stub
		this.data = data;
		this.icon = icon;

	}

	public void setData(Vector<String> data) {
		this.data = data;
	}

	public Vector<String> getData() {
		return data;
	}

	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}

	public ImageIcon getIcon() {
		return icon;
	}

}
