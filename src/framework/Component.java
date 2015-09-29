package framework;

public abstract class Component {
	
	//game object tag is the name of the GameObject which this component belongs to
	private String type="", gameObjectTag="";
	
	
	protected void setGameObjectTag(String tag){
		gameObjectTag = tag;
	}
	
	public String getGameObjectTag(){
		return gameObjectTag;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public String getType(){
		return type;
	}
	
	public abstract void update();
	
}
