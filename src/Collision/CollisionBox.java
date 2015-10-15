package Collision;

import java.awt.Color;

import debugging.Debug;
import display.Drawer;
import framework.Component;
import framework.GameObject;
import framework.Vector;


public class CollisionBox implements CollisionShape, Debug {

	private boolean isAlive, isTrigger;
	private int x,y,w,h;
	private static final String TYPE="COLLISIONBOX";
	private String tag;
	Vector objPosition=new Vector(0,0);
	
	public CollisionBox(int x, int y, int w, int h, boolean isTrigger, boolean isAlive){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.isAlive=isAlive;
		this.isTrigger=isTrigger;
		this.tag=tag;
	}
	
	public int minX(){
		return objPosition.intX()+x;
	}
	
	public int minY(){
		return objPosition.intY()+y;
	}
	
	public int maxX(){
		return objPosition.intX()+x+w;
	}
	
	public int maxY(){
		return objPosition.intY()+y+h;
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
	public void debugDraw(Drawer g, Vector objPos) {
		g.drawBox(objPos.intX()+x, objPos.intY()+y, w, h, Color.GREEN);
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
		objPosition=position;
	}

}
