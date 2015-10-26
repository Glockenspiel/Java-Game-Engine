package scripts;

import Collision.CollisionListener;
import framework.Event;
import framework.Game;
import framework.GameObject;
import framework.Script;

public class CoinCollected implements Script, CollisionListener {

	private int value, objID;
	
	public CoinCollected(int objID, int value){
		this.value=value;
		this.objID=objID;
	}
	
	@Override
	public void onCollision(String tag, int id) {}

	@Override
	public void onTrigger(String tag, int id) {
		if(tag.equalsIgnoreCase("Player")){
			for(Event e : Game.getGameObjectById(id).getEvents()){
				e.invokeEvent("coin", value);
			}
			Game.deleteObjByID(objID);
		}
	}

	@Override
	public void execute(GameObject obj) {}

	@Override
	public void interuptThreads() {}

}
