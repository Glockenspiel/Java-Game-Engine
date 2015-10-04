package Tiles;

import java.awt.image.BufferedImage;
import Display.Drawer;

public class GridTile implements Tile{
	
	//display size of tiles
	private static final int tileWidth=32, tileHeight=32;
	
	private int x,y; //index of x and y in 2d array
	private int sheetX, sheetY;
	private boolean hasCollision;
	TileMap map;
	
	
	public GridTile(TileMap map, int sheetX, int sheetY, int indexX, int indexY, boolean hasCollision){
		x = indexX;
		y = indexY;
		this.sheetX=sheetX;
		this.sheetY=sheetY;
		this.hasCollision=hasCollision;
		this.map=map;
	}

	@Override
	public void draw(Drawer g, int x, int y) {
		g.drawImage(map.getSpriteFromSheet(sheetX, sheetY) , x*tileWidth, y*tileHeight, tileWidth, tileHeight);
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
}
