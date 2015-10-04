package Tiles;

import java.awt.image.BufferedImage;

import Display.Drawer;

public class Tile2 implements Tile {
	
	private BufferedImage image;
	private int w,h;
	private boolean hasCollision;
	private int id;
	
	public Tile2(BufferedImage image, int width, int height, int id, boolean hasCollision){
		this.image=image;
		w=width;
		h=height;
		this.id=id;
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
		return id;
	}
	
}
