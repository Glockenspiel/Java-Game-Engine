package prescripts;

import java.util.ArrayList;

import collision.CollisionListener;
import framework.Game;
import framework.GameObject;
import framework.SetObjID;

public class HurtAndDelete implements CollisionListener, SetObjID {

	private String tag;
	private int damage;
	private int objID;
	
	public HurtAndDelete(String tag, int damage){
		this.tag = tag;
		this.damage=damage;
	}

	
	@Override
	public void execute(GameObject obj) {}

	@Override
	public void onTrigger(String tag, int id) {
		if(this.tag.equalsIgnoreCase(tag)){
				for(GameObject  o : Game.getAllGameObjectsByTag(tag)){
					o.notifyEventListeners("hurt", damage);
				}
			
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
