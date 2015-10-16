package Collision;

import debugging.Debug;
import display.Drawer;
import framework.Component;
import framework.GameObject;
import framework.Vector;

public class CollisionCircle implements CollisionShape, Debug {

	private static final String TYPE="CIRCLE";
	private boolean isAlive, isTrigger;
	private int x,y,r;
	private String tag="";
	private Vector objPosition = new Vector(0,0);
	
	public CollisionCircle(int x, int y, int radius, boolean isTrigger, boolean isAlive){
		this.x=x;
		this.y=y;
		this.r=radius;
		this.isAlive=isAlive;
		this.isTrigger=isTrigger;
	}
	
	@Override
	public void debugDraw(Drawer g, Vector objPos) {
		g.drawCircle(x+objPos.intX(),y+objPos.intY(),r);
	}

	@Override
	public boolean getAlive() {
		return isAlive;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public boolean getIsTrigger() {
		return isTrigger;
	}

	@Override
	public void setIsTrigger(boolean isTrigger) {
		this.isTrigger=isTrigger;
	}

	@Override
	public String getTag() {
		return tag;
	}

	@Override
	public void setTag(String tag) {
		this.tag=tag;
	}

	@Override
	public void update(Vector position) {
		objPosition = position;
	}
	
	@Override
	public void setAlive(boolean isAlive) {
		this.isAlive=isAlive;
	}

}
