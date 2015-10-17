package scripts;

import demo.Explosion;
import Collision.CollisionListener;
import framework.Game;
import framework.Time;
import framework.Vector;
import framework.GameObject;
import framework.Script;

public class BulletScript implements Script, CollisionListener {
	
	

	private Vector speed;
	private int objID;
	private Explosion explosion = new Explosion();
	
	public BulletScript(Vector speed, int objID){
		this.objID=objID;
		this.speed=new Vector(speed.getX(), speed.getY());
	}
	
	public BulletScript(int xSpeed, int ySpeed, int objID){
		this.objID=objID;
		speed = new Vector(xSpeed, ySpeed);
	}
	
	@Override
	public void execute(GameObject obj) {
		obj.moveBy(speed.getDeltaVector());
	}

	@Override
	public void interuptThreads() {}

	@Override
	public void onCollision(String tag, int id) {}

	@Override
	public void onTrigger(String tag, int id) {
		//if(tag.equalsIgnoreCase("Bullet")==false)
		//	Game.print().log("bullet overlap: " + tag + " | id: "+ id);
		
		if(tag.equalsIgnoreCase("Enemy1")){
			explosion.moveTo(Game.getGameObjectById(objID).getPosition());
			Game.addGameObject(explosion);
			Game.deleteObjByID(id);
			Game.deleteObjByID(objID);
		}
	}

}
