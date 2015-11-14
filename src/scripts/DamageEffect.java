package scripts;

import misc.MathG;
import misc.Vector;
import demo.Explosion;
import framework.EventListener;
import framework.Game;
import framework.GameObject;
import framework.SetObjID;

public class DamageEffect implements EventListener, SetObjID {

	private int objID;
	
	@Override
	public void execute(GameObject obj) {}

	@Override
	public void notify(String eventName, int value) {
		if(eventName.equals("hurt")){
			Vector position = Game.getGameObjectById(objID).getPosition();
			Explosion explo = new Explosion(position.intX()+ MathG.randomNumber(0, 0), position.intY()+MathG.randomNumber(-20, 20), 64, 64);
			Game.addGameObject(explo);
		}
	}

	@Override
	public void setGameObjectID(int id) {
		objID=id;
	}

}
