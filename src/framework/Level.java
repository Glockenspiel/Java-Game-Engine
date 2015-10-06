package framework;

public abstract class Level {

	protected void addObj(GameObject obj){
		Game.addGameObject(obj);
	}
	
	public abstract void init();
	public abstract String getName();
}
