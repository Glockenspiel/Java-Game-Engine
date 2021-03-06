package tiles;

import java.awt.image.BufferedImage;

import display.Drawer;

public class Tile implements Tile_I {
	
	private BufferedImage image;
	private boolean hasCollision;
	private int id;
	private String name;
	
	//create new tile
	public Tile(BufferedImage image, int id, String name, boolean hasCollision){
		this.image=image;
		this.id=id;
		this.name=name;
		this.hasCollision=hasCollision;
	}

	//draw the tile in the given position
	@Override
	public void draw(Drawer g, int x, int y, int w, int h) {
		g.drawImage(image, x, y, w, h);
	}

	//returns true if this tile has collision
	@Override
	public boolean hasCollision() {
		return hasCollision;
	}

	//return the tile id
	@Override
	public int getID() {
		return id;
	}

	//return the name of the tile
	@Override
	public String getName() {
		return name;
	}
	
	
	
}
