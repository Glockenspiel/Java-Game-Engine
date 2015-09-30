package Display;

import java.awt.Graphics;
import java.awt.Image;

public class SwingDrawer implements GraphicDrawer {

	Graphics g;
	
	public SwingDrawer(){}
	
	public void setGraphics(Graphics g){
		this.g = g;
	}
	
	@Override
	public void drawImage(Image img, int x, int y, int width, int height) {
		g.drawImage(img, x, y, width, height, null);
	}

}
