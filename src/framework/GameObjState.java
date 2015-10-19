package framework;

public class GameObjState implements GameObjectStateI{

	Vector position;
	String tag;
	int id, drawLayer;
	boolean isGlobal;
	
	public GameObjState(GameObject obj){
		position = new Vector(obj.getPosition().getX(), obj.getPosition().getY());
		this.tag=obj.getTag();
		this.id=obj.getID();
		this.isGlobal=obj.getIsGlobal();
		this.drawLayer=obj.getDrawLayer();
	}
	
	@Override
	public Vector getPosition() {
		return position;
	}

	@Override
	public int getID() {
		return id;
	}

	@Override
	public int getDrawLayer() {
		return drawLayer;
	}

	@Override
	public boolean getIsGlobal() {
		return isGlobal;
	}

	@Override
	public String getTag() {
		return tag;
	}
	
	
	

}
