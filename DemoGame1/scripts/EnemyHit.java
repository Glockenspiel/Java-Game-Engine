package scripts;

import demo.Explosion;
import Collision.CollisionListener;
import framework.Event;
import framework.Game;
import framework.GameObject;
import framework.MathG;
import framework.Script;

public class EnemyHit implements Script, CollisionListener {

	private int objID;
	private static int damage=10;
	
	public EnemyHit(int id) {
		objID=id;
	}

	@Override
	public void onCollision(String tag, int id) {
		
	}

	@Override
	public void onTrigger(String tag, int id) {
		if(tag.equalsIgnoreCase("Player")){
			for(Event e : Game.getGameObjectById(id).getEvents()){
					e.invokeEvent(getTag(), damage+MathG.randomNumber(0, 10));
			}
			
			//create an explosion
			Explosion ex = new Explosion(-5,16,64,64);
			//move explosion to current object position and delete the current object
			ex.moveTo(Game.getGameObjectById(objID).getPosition());
			Game.addGameObject(ex);
			Game.deleteObjByID(objID);
		}
	}

	@Override
	public void execute(GameObject obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void interuptThreads() {
		// TODO Auto-generated method stub

	}
	
	private String getTag(){
		return Game.getGameObjectById(objID).getTag();
	}

}
