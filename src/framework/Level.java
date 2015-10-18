package framework;

public abstract class Level {

	//shorthand for add GameObjects in a subclass of level
	protected void addObj(GameObject obj){
		Game.addGameObject(obj);
	}
	
	//initialise method, to create all the GameObjects and their Components, Scripts and CollisionShapes
	public abstract void init();
	
	//returns the name of this level
	public abstract String getName();
}
