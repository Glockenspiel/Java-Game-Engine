package Tiles;

import Display.Drawer;


//world object that has a static position
public interface Tile {

	public void draw(Drawer g, int x, int y);
	public boolean hasCollision();
	public int getID();
	public String getName();
}
