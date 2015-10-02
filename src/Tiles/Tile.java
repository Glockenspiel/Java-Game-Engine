package Tiles;

import Display.Drawer;


//world object that has a static position
public interface Tile {

	public void draw(Drawer g);
	public boolean hasCollision();
}
