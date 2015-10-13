package scripts;

import framework.Time;
import framework.Vector;
import framework.GameObject;
import framework.Script;

public class BulletScript implements Script {

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

}
