package tiles;

import display.Drawer;


//world object that has a static position
public interface Tile_I {

	public void draw(Drawer g, int x, int y, int width, int height);
	public boolean hasCollision();
	public int getID();
	public String getName();
}
