package Collision;

import debugging.Debug;
import display.Drawer;
import framework.Component;
import framework.GameObject;
import framework.Vector;

public class CollisionCircle extends Component implements CollisionShape, Debug {

	private static final String TYPE="CIRCLE";
	private boolean isAlive;
	private int x,y,r;
	
	public CollisionCircle(int x, int y, int radius, boolean isAlive){
		this.x=x;
		this.y=y;
		this.r=radius;
		this.isAlive=isAlive;
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
	public void update(GameObject obj) {}

	@Override
	public void draw(Drawer g, Vector objPos) {}

}
