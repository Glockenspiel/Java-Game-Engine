package collision;

import java.awt.Color;

import display.Drawer;
import framework.Component;
import framework.Debug;
import framework.GameObject;
import framework.Vector;


public class CollisionBox implements CollisionShape, Debug {

	private boolean isAlive=true;
	private int x,y,w,h; //position and dimensions
	private static final String TYPE="COLLISIONBOX";
	private String tag; //used for deleting this shape
	
	 //stores the latest position of the GameObject this collision shape belongs to
	Vector objPosition=new Vector(0,0);
	
	//constructor
	public CollisionBox(int x, int y, int w, int h){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
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
	public void notify(Vector position) {
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

	public int width() {
		return w;
	}
	
	public int height() {
		return h;
	}

	public int getCenterX() {
		return objPosition.intX() + x + w/2;
	}
	
	public int getCenterY() {
		return objPosition.intY() + y + h/2;
	}
}
