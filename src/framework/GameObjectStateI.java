package framework;

public interface GameObjectStateI {
	public Vector getPosition();
	public int getID();
	public int getDrawLayer();
	public boolean getIsGlobal();
	public String getTag();
}
