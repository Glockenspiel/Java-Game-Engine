package prescripts;

import framework.EventListener;
import framework.Game;
import framework.GameObject;
import framework.SetObjID;

public class DeleteOnAudioComplete implements EventListener, SetObjID {

	private int objID;
	
	@Override
	public void execute(GameObject obj) {}

	@Override
	public void notify(String eventName, int value) {
		if(eventName.equals("Audio_complete")){
			Game.deleteObjByID(objID);
		}
	}

	@Override
	public void setGameObjectID(int id) {
		objID=id;
	}

	@Override
	public void construct(String[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getSaveArgs() {
		// TODO Auto-generated method stub
		return null;
	}

}
