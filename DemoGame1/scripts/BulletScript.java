package scripts;

import collision.CollisionListener;
import demo.Explosion;
import framework.Game;
import framework.Time;
import framework.Vector;
import framework.GameObject;
import framework.Script;

public class BulletScript implements Script, CollisionListener {

	private Vector speed;
	private int objID;
	private Explosion explosion = new Explosion();
	
	//constructor with a vector
	public BulletScript(Vector speed, int objID){
		this.objID=objID;
		this.speed=new Vector(speed.getX(), speed.getY());
	}
	
	//constructor with 2 integer values
	public BulletScript(int xSpeed, int ySpeed, int objID){
		this.objID=objID;
		speed = new Vector(xSpeed, ySpeed);
	}
	
	//move GameObject 
	@Override
	public void execute(GameObject obj) {
		obj.moveBy(speed.getDeltaVector());
	}

	@Override
	public void onCollision(String tag, int id) {}

	//delete the bullet and create an explosion
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
