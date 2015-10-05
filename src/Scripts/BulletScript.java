package Scripts;

import framework.Vector;
import framework.GameObject;
import framework.Script;

public class BulletScript implements Script {

	private Vector speed;
	private boolean once=true;
	
	public BulletScript(Vector speed){
		this.speed=speed;
	}
	
	public BulletScript(int xSpeed, int ySpeed){
		speed = new Vector(xSpeed, ySpeed);
	}
	
	@Override
	public void execute(GameObject obj) {
		obj.moveBy(speed);
	}

}
