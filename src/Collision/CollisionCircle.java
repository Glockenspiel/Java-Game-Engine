package Collision;

import debugging.Debug;
import display.Drawer;
import framework.Component;
import framework.GameObject;
import framework.Vector;

public class CollisionCircle implements CollisionShape, Debug {

	private static final String TYPE="COLLISIONCIRCLE";
	private boolean isAlive, isTrigger;
	private int x,y,r;
	private String tag="";
	private Vector objPosition = new Vector(0,0);
	
	//constructor
	public CollisionCircle(int x, int y, int radius, boolean isTrigger, boolean isAlive){
		this.x=x;
		this.y=y;
		this.r=radius;
		this.isAlive=isAlive;
		this.isTrigger=isTrigger;
	}
	
	//draw a debug circle
	@Override
	public void debugDraw(Drawer g, Vector objPos) {
		g.drawCircle(x+objPos.intX(),y+objPos.intY(),r);
	}

	//returns isAlive
	@Override
	public boolean getAlive() {
		return isAlive;
	}
	
	//set isAlive
	@Override
	public void setAlive(boolean isAlive) {
		this.isAlive=isAlive;
	}

	//returns the component type
	@Override
	public String getType() {
		return TYPE;
	}

	//returns isTrigger
	@Override
	public boolean getIsTrigger() {
		return isTrigger;
	}

	//sets isTrigger
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

	////updates the current position of the GameObject this collision shape belongs to
	@Override
	public void update(Vector position) {
		objPosition = position;
	}
}
