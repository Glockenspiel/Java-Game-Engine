package display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import framework.Game;

public class SwingDrawer implements Drawer {

	Graphics g;
	
	public SwingDrawer(){}
	
	public void setGraphics(Graphics g){
		this.g = g;
	}
	
	@Override
	public void drawImage(Image img, int x, int y, int width, int height) {
		x+=cameraPosX();
		y+=cameraPosY();
		g.drawImage(img, x, y, width, height, null);
	}

	@Override
	public void drawLine(int x, int y, int xDirection, int yDirection, Color color) {
		x+=cameraPosX();
		y+=cameraPosY();
		g.setColor(color);
		g.drawLine(x, y, x+xDirection, y+yDirection);
	}

	@Override
	public void drawBox(int x, int y, int width, int height, Color color) {
		x+=cameraPosX();
		y+=cameraPosY();
		g.setColor(color);
		g.drawRect(x, y, width, height);
	}
	
	private int cameraPosX(){
		return Game.getCamera().getPosition().intX();
	}
	
	private int cameraPosY(){
		return Game.getCamera().getPosition().intY();
	}

	@Override
	public void drawHUDImage(Image img, int x, int y, int w, int h) {
		g.drawImage(img, x, y, w, h, null);
	}

	@Override
	public void drawHUDText(String string, int x, int y, Color color) {
		g.setColor(color);
		g.drawString(string, x, y);
	}

	@Override
	public void drawText(String string, int x, int y, Color color) {
		x+=cameraPosX();
		y+=cameraPosY();
		g.setColor(color);
		g.drawString(string, x, y);
	}

	@Override
	public void drawCircle(int x, int y, int radius) {
		x+=cameraPosX();
		y+=cameraPosY();
		g.drawOval(x-radius, y-radius, 2*radius, 2*radius);
	}
	
	
	

}
