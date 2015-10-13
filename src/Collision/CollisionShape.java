package Collision;

public interface CollisionShape {
	
	public boolean getIsTrigger();
	public void setIsTrigger(boolean isTrigger);
	public boolean getAlive();
	public String getType();
	public String getTag();
	public void setTag(String tag);
}
