package display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import framework.Game;

public class SwingDrawer implements Drawer {

	Graphics g;
	
	//default constructor
	public SwingDrawer(){}
	
	//set the graphics
	public void setGraphics(Graphics g){
		this.g = g;
	}
	
	//draw an image
	@Override
	public void drawImage(Image img, int x, int y, int width, int height) {
		x+=cameraPosX();
		y+=cameraPosY();
		x = unitsToWindowPosX(x);
		width = unitsToWindowPosX(width)+1; //+1 to prevent bug with window ratio
		y = unitsToWindowPosY(y);
		height = unitsToWindowPosY(height)+1;
		g.drawImage(img, x, y, width, height, null);
	}

	//draw a line
	@Override
	public void drawLine(int x, int y, int xDirection, int yDirection, Color color) {
		x+=cameraPosX();
		y+=cameraPosY();
		x = unitsToWindowPosX(x);
		xDirection = unitsToWindowPosX(xDirection);
		y = unitsToWindowPosY(y);
		yDirection = unitsToWindowPosY(yDirection);
		g.setColor(color);
		g.drawLine(x, y, x+xDirection, y+yDirection);
	}

	//draw a box
	@Override
	public void drawBox(int x, int y, int width, int height, Color color) {
		x+=cameraPosX();
		y+=cameraPosY();
		x = unitsToWindowPosX(x);
		width = unitsToWindowPosX(width);
		y = unitsToWindowPosY(y);
		height = unitsToWindowPosY(height);
		g.setColor(color);
		g.drawRect(x, y, width, height);
	}
	
	
	private int cameraPosX(){
		return Game.getServices().getCamera().getPosition().intX();
	}
	
	//returns the cameras position on Y-axis
	private int cameraPosY(){
		return Game.getServices().getCamera().getPosition().intY();
	}

	//draw an image on the HUD
	@Override
	public void drawHUDImage(Image img, int x, int y, int w, int h) {
		x = unitsToWindowPosX(x);
		w = unitsToWindowPosX(w);
		y = unitsToWindowPosY(y);
		h = unitsToWindowPosY(h);
		g.drawImage(img, x, y, w, h, null);
	}

	//draw text on the HUD
	@Override
	public void drawHUDText(String string, int x, int y, Color color) {
		x = unitsToWindowPosX(x);
		y = unitsToWindowPosY(y);
		g.setColor(color);
		g.drawString(string, x, y);
	}

	//draw text 
	@Override
	public void drawText(String string, int x, int y, Color color) {
		x+=cameraPosX();
		y+=cameraPosY();
		x = unitsToWindowPosX(x);
		y = unitsToWindowPosY(y);
		g.setColor(color);
		g.drawString(string, x, y);
	}

	//draw circle
	@Override
	public void drawCircle(int x, int y, int radius) {
		x+=cameraPosX();
		y+=cameraPosY();
		x = unitsToWindowPosX(x);
		y = unitsToWindowPosY(y);
		g.drawOval(x-radius, y-radius, 2*radius, 2*radius);
	}
	
	//convert a world unit on x-axis to the windows scale
	private int unitsToWindowPosX(int val){
		
		return (int)(val*Game.getServices().getWindow().getWindowScale().getX());
	}
	//convert a world unit on y-axis to the windows scale
	private int unitsToWindowPosY( int val){
		return (int)(val*Game.getServices().getWindow().getWindowScale().getY());
	}
	

}
