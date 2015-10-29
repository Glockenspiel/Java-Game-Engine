package Collision;

import java.awt.Color;

import debugging.Debug;
import display.Drawer;
import framework.Component;
import framework.GameObject;
import framework.Vector;


public class CollisionBox implements CollisionShape, Debug {

	private boolean isAlive=false, //if this collision shape is has a physical component
					isTrigger; //true if this collision shape invokes events
	private int x,y,w,h; //position and dimensions
	private static final String TYPE="COLLISIONBOX";
	private String tag; //used for deleting this shape
	
	 //stores the latest position of the GameObject this collision shape belongs to
	Vector objPosition=new Vector(0,0);
	
	//constructor
	public CollisionBox(int x, int y, int w, int h, boolean isTrigger){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.isTrigger=isTrigger;
	}
	
	//returns minimum x value 
	public int minX(){
		return objPosition.intX()+x;
	}
	
	//returns minimum  y value
	public int minY(){
		return objPosition.intY()+y;
	}
	
	//returns maximum x value
	public int maxX(){
		return objPosition.intX()+x+w;
	}
	
	//returns maximum y value
	public int maxY(){
		return objPosition.intY()+y+h;
	}

	//returns the component type
	@Override
	public String getType() {
		return TYPE;
	}

	//draw a debug rectangle
	@Override
	public void debugDraw(Drawer g, Vector objPos) {
		g.drawBox(objPos.intX()+x, objPos.intY()+y, w, h, Color.GREEN);
	}

	//returns isTrigger
	@Override
	public boolean getIsTrigger() {
		return isTrigger;
	}

	//set isTrigger
	@Override
	public void setIsTrigger(boolean isTrigger) {
		this.isTrigger=isTrigger;
	}

	//returns the collision shape's tag
	@Override
	public String getTag() {
		return tag;	
	}

	//sets the collision shape's tag
	@Override
	public void setTag(String tag) {
		this.tag=tag;
	}

	//updates the current position of the GameObject this collision shape belongs to
	@Override
	public void update(Vector position) {
		objPosition=position;
	}

	//set isAlive
	@Override
	public void setAlive(boolean isAlive) {
		this.isAlive=isAlive;
	}

	//return isAlive
	@Override
	public boolean getAlive() {
		return isAlive;
	}
}
