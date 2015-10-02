package Tiles;

import java.awt.image.BufferedImage;

import Display.Drawer;

public class GridTile implements Tile{
	
	//display size of tiles
	private static final int tileWidth=32, tileHeight=32;
	
	private int x,y; //index of x and y in 2d array
	private BufferedImage image;
	private boolean hasCollision;
	
	
	public GridTile(BufferedImage image, int indexX, int indexY, boolean hasCollision){
		this.image=image;
		x = indexX;
		y = indexY;
		this.hasCollision=hasCollision;
	}

	@Override
	public void draw(Drawer g) {
		g.drawImage(image , x*tileWidth, y*tileHeight, tileWidth, tileHeight);
	}

	@Override
	public boolean hasCollision() {
		return hasCollision;
	}
}
