package Tiles;

import java.awt.image.BufferedImage;

import Display.Drawer;

public class BasicTile implements Tile{
	private int x,y,w,h;
	private BufferedImage image;
	private boolean hasCollision;
	
	public BasicTile(BufferedImage image, int x, int y, int w, int h, boolean hasCollision){
		this.image=image;
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.hasCollision=hasCollision;
	}

	@Override
	public void draw(Drawer g, int x, int y) {
		g.drawImage(image, x, y, w, h);
	}

	@Override
	public boolean hasCollision() {
		return hasCollision;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
}
