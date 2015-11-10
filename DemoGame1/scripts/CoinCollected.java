package scripts;

import audio.AudioSource;
import audio.PlayingState;
import collision.CollisionListener;
import framework.EventListener;
import framework.Game;
import framework.GameObject;
import framework.Script;

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
			obj.add(new AudioSource("coins.wav", true));
			obj.delete(3000);
			Game.addGameObject(obj);
			//AudioSource src = (AudioSource) super.getComponentByType(AudioSource.class);
			//src.setState(new PlayingState(src));
		}
	}

	@Override
	public void execute(GameObject obj) {}
}
