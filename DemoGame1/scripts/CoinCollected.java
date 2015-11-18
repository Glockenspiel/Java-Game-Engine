package scripts;

import components.AudioSource;

import collision.CollisionListener;
import framework.Game;
import framework.GameObject;

public class CoinCollected implements CollisionListener {

	private int value, objID;
	
	public CoinCollected(int objID, int value){
		this.value=value;
		this.objID=objID;
	}

	//if this game object overlaps a GameObject with the tag 'player' it will invoke all the events in the player object
	@Override
	public void onTrigger(String tag, int id) {
		if(tag.equalsIgnoreCase("Player")){
			Game.getGameObjectById(id).notifyEventListeners("coin_collected", value);
			Game.deleteObjByID(objID);
			GameObject obj = new GameObject("ching");
			Game.addGameObjectWithoutBuffer(obj);
			obj.add(new AudioSource("coins.wav", true));
			obj.add(new DeleteOnAudioComplete());
			Game.addGameObject(obj);
		}
	}

	@Override
	public void execute(GameObject obj) {}
}
