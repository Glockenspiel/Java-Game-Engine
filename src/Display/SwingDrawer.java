package Display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class SwingDrawer implements Drawer {

	Graphics g;
	
	public SwingDrawer(){}
	
	public void setGraphics(Graphics g){
		this.g = g;
	}
	
	@Override
	public void drawImage(Image img, int x, int y, int width, int height) {
		g.drawImage(img, x, y, width, height, null);
	}

	@Override
	public void drawLine(int x, int y, int xDirection, int yDirection, Color color) {
		g.setColor(color);
		g.drawLine(x, y, x+xDirection, y+yDirection);
	}

	@Override
	public void drawBox(int x, int y, int width, int height, Color color) {
		g.setColor(color);
		g.drawRect(x, y, width, height);
	}
	
	

}
