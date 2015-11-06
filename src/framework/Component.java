package framework;

import misc.Vector;
import display.Drawer;

public abstract class Component{
	
	//game object tag is the name of the GameObject which this component belongs to
	private String gameObjectTag="";

	//update is where to do the game's logic
	public abstract void update(GameObject obj);
	
	//draw is where draw calls should be placed and is called after update
	public abstract void draw(Drawer g, Vector objPos);
}
