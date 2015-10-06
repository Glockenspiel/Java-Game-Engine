package Tiles;

import java.awt.image.BufferedImage;

import Display.Drawer;

public class Tile implements Tile_I {
	
	private BufferedImage image;
	private int w,h;
	private boolean hasCollision;
	private int id;
	private String name;
	
	public Tile(BufferedImage image, int width, int height, int id, String name, boolean hasCollision){
		this.image=image;
		w=width;
		h=height;
		this.id=id;
		this.name=name;
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

	@Override
	public String getName() {
		return name;
	}
	
	
	
}
