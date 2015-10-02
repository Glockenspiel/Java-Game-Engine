package Display;

import java.awt.Graphics;
import java.awt.Image;

public class SwingDrawer implements Drawer {

	Graphics g;
	
	public SwingDrawer(){}
	
	public void setGraphics(Graphics g){
		this.g = g;
	}
	
	@Override
	public void drawImage(Image img, int offsetX, int offsetY, int width, int height) {
		g.drawImage(img, offsetX, offsetY, width, height, null);
	}

}
