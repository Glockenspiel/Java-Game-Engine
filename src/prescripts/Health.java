package prescripts;

import framework.EventListener;
import framework.Game;
import framework.GameObject;
import framework.Script;
import framework.SetObjID;

public class Health implements EventListener, SetObjID {

	private int hp, maxhp;
	private int objID;
	
	public Health(int maxHealth){
		hp = maxHealth;	
	}
	
	@Override
	public void execute(GameObject obj) {}

	@Override
	public void notify(String eventName, int value) {
		if(eventName.equals("hurt")){
			hp-=value;
			checkDead();
		}
		else if(eventName.equals("heal")){
			hp+=value;
			if(hp>maxhp)
				hp=maxhp;
		}
	}

	private void checkDead() {
		if(hp <= 0){
			hp=0;
			Game.exit();
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
