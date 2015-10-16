package scripts;

import Collision.CollisionListener;
import framework.Game;
import framework.Time;
import framework.Vector;
import framework.GameObject;
import framework.Script;

public class BulletScript implements Script, CollisionListener {

	private Vector speed;
	
	public BulletScript(Vector speed){
		this.speed=new Vector(speed.getX(), speed.getY());
	}
	
	public BulletScript(int xSpeed, int ySpeed){
		speed = new Vector(xSpeed, ySpeed);
	}
	
	@Override
	public void execute(GameObject obj) {
		obj.moveBy(speed.getDeltaVector());
	}

	@Override
	public void interuptThreads() {}

	@Override
	public void onCollision(String tag) {}

	@Override
	public void onTrigger(String tag) {
		if(tag.equalsIgnoreCase("Bullet")==false)
			Game.print().log("bullet overlap: " + tag);
	}

}
