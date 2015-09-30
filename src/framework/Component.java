package framework;

import java.awt.Graphics;

import Display.Drawer;

public abstract class Component{
	
	//game object tag is the name of the GameObject which this component belongs to
	private String gameObjectTag="";
	
	
	protected void setGameObjectTag(String tag){
		gameObjectTag = tag;
	}
	
	public String getGameObjectTag(){
		return gameObjectTag;
	}

	
	public abstract String getType();
	
	//init is called once all GameObjects and Components have been loaded and before the main loop starts
	public abstract void init(GameObject obj);
	
	//update is where to do the game's logic
	public abstract void update(GameObject obj);
	
	//draw is where draw calls should be placed and is called after update
	public abstract void draw(Drawer g, GameObject obj);
}
