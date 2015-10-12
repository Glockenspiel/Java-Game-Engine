package Collision;

import java.awt.Color;

import debugging.Debug;
import display.Drawer;
import framework.Component;
import framework.GameObject;
import framework.Vector;


public class CollisionBox extends Component implements CollisionShape, Debug {

	private boolean isAlive;
	private int x,y,w,h;
	private static final String TYPE="COLLISIONBOX";
	
	public CollisionBox(int x, int y, int w, int h, boolean isAlive){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.isAlive=isAlive;
	}
	
	public int minX(){
		return x;
	}
	
	public int minY(){
		return y;
	}
	
	public int maxX(){
		return x+w;
	}
	
	public int maxY(){
		return y+h;
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
	public void update(GameObject obj) {}

	@Override
	public void draw(Drawer g, Vector objPos) {}

}
