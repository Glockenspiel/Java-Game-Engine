package collision;

import misc.Debug;
import misc.Vector;
import display.Drawer;
import framework.Component;
import framework.GameObject;

public class CollisionCircle implements CollisionShape, Debug {

	private static final String TYPE="COLLISIONCIRCLE";
	private boolean isAlive;
	private int x,y,r;
	private String tag="";
	private Vector objPosition = new Vector(0,0);
	
	//constructor
	public CollisionCircle(int x, int y, int radius){
		this.x=x;
		this.y=y;
		this.r=radius;
		this.isAlive=true;
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
	public void notify(Vector position) {
		objPosition = position;
	}
	
	public int getX(){
		return objPosition.intX()+x;
	}
	
	public int getY(){
		return objPosition.intY()+y;
	}
	
	
	//returns minimum x value 
	@Override
	public int minX(){
		return objPosition.intX()+x-r;
	}
	
	//returns minimum  y value
	@Override
	public int minY(){
		return objPosition.intY()+y-r;
	}
	
	//returns maximum x value
	@Override
	public int maxX(){
		return objPosition.intX()+x+r;
	}
	
	//returns maximum y value
	@Override
	public int maxY(){
		return objPosition.intY()+y+r;
	}
	
	public int getRadius(){
		return r;
	}
}
