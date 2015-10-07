package Scripts;

import java.awt.event.KeyEvent;

import framework.Game;
import framework.GameObject;
import framework.Script;
import framework.Vector;

public class cameraFollow implements Script {

	private int id;
	private Vector offset;
	
	//sets this object to follow the camera
	public cameraFollow(int id, Vector offset){
		Game.getCamera().followObjectID(id, offset);
		this.id=id;
		this.offset=offset;
	}
	
	@Override
	public void execute(GameObject obj) {
		//toggle camera follow
		if(Game.getInput().isKeyPressed((char) KeyEvent.VK_T)){
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
