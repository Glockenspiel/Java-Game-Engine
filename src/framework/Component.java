package framework;

import java.awt.Graphics;

public abstract class Component{
	
	//game object tag is the name of the GameObject which this component belongs to
	private String type="", gameObjectTag="";
	
	
	protected void setGameObjectTag(String tag){
		gameObjectTag = tag;
	}
	
	public String getGameObjectTag(){
		return gameObjectTag;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getType(){
		return type;
	}
	
	//init is called once all GameObjects and Components have been loaded and before the main loop starts
	public abstract void init();
	
	//update is where to do the game's logic
	public abstract void update();
	
	//draw is where draw calls should be placed and is called after update
	public abstract void draw(Graphics g);
}
