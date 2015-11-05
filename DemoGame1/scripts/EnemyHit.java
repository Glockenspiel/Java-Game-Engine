package scripts;

import collision.CollisionListener;
import demo.Explosion;
import framework.Event;
import framework.Game;
import framework.GameObject;
import framework.MathG;
import framework.Script;

public class EnemyHit implements CollisionListener {

	private int objID;
	private static int damage=10;
	
	public EnemyHit(int id) {
		objID=id;
	}

	//if the enemy hits the player it will invoke the events in player
	@Override
	public void onTrigger(String tag, int id) {
		if(tag.equalsIgnoreCase("Player")){
			for(Event e : Game.getGameObjectById(id).getEvents()){
					e.notify(getTag(), damage+MathG.randomNumber(0, 10));
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
	
	//returns the tag of the game object this script belongs to
	private String getTag(){
		return Game.getGameObjectById(objID).getTag();
	}

}
