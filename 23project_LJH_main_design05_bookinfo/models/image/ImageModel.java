package image;
import java.util.Vector;

import javax.swing.ImageIcon;

public class ImageModel {

	private Vector<String> datas;
	private ImageIcon icon;
	
	public ImageModel(Vector<String> data, ImageIcon icon) {
		this.datas = data;
		this.icon = icon;
	}
	
	public Vector<String> getData() {
		return datas;
	}
	public void setData(Vector<String> data) {
		this.datas = data;
	}
	public ImageIcon getIcon() {
		return icon;
	}
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
	
	
}
