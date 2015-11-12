package framework;

import misc.Vector;
import display.Drawer;

public abstract class Component{	

	//update is where to do the game's logic
	public void update(GameObject obj){};
	
	//draw is where draw calls should be placed and is called after update
	public void draw(Drawer g, Vector objPos){};
	
	//called on deletion
	public void dispose(){};
}
