package framework;

import java.util.ArrayList;

public abstract class Level {

	private ArrayList<GameObject> levelObjs = new ArrayList<GameObject>();
	
	public ArrayList<GameObject> getGameObjects(){
		return levelObjs;
	}
	
	protected void addObj(GameObject obj){
		levelObjs.add(obj);
	}
	
	public abstract void init();
	public abstract String getName();
}
