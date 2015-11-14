package scripts;

import java.awt.event.KeyEvent;

import misc.Vector;
import framework.Game;
import framework.GameObject;
import framework.Script;

public class cameraFollow implements Script {

	private int id;
	private Vector offset;
	
	//sets this object to follow the camera
	public cameraFollow(int id, Vector offset){
		Game.getCamera().followObjectID(id, offset);
		this.id=id;
		this.offset=offset;
	}
	
	//checks if the key T is pressed and toggle the camera following the player
	@Override
	public void execute(GameObject obj) {
		//toggle camera follow
		if(Game.getServices().getInput().isButtonPressed((char) KeyEvent.VK_T)){
			if(Game.getCamera().getFollowId()!=-1)
				Game.getCamera().unFollow();
			else{
				//uncommented example of using getGameObjectByID
				//int id = Game.getGameObjectByTag("player").getID();
				Game.getCamera().followObjectID(id, offset);
			}
		}
			
	}
}
