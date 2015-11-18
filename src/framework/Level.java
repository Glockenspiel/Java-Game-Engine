package framework;

public abstract class Level {

	//shorthand to add GameObjects to the game in a subclass of level
	protected void addObj(GameObject obj){
		Game.addGameObjectWithoutBuffer(obj);
	}
	
	//initialise method, to create all the GameObjects and their Components, Scripts and CollisionShapes
	public abstract void init();
}
