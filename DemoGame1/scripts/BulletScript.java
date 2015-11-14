package scripts;

import misc.Vector;
import collision.CollisionListener;
import demo.Explosion;
import framework.Game;
import framework.GameObject;
import framework.Script;

public class BulletScript implements Script, CollisionListener {

	private Vector speed;
	private int objID;
	
	
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

	//delete the bullet and create an explosion
	@Override
	public void onTrigger(String tag, int id) {
		if(tag.equalsIgnoreCase("Enemy1")){
			Explosion explosion = new Explosion();
			explosion.moveTo(Game.getGameObjectById(objID).getPosition());
			Game.addGameObject(explosion);
			Game.deleteObjByID(id);
			Game.deleteObjByID(objID);
		}
	}

}
