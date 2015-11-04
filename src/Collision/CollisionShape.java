package collision;

import framework.Vector;

public interface CollisionShape {
	
	public boolean getIsTrigger();
	public void setIsTrigger(boolean isTrigger);
	public boolean getAlive();
	public void setAlive(boolean isAlive);
	public String getType();
	public String getTag();
	public void setTag(String tag);
	public void update(Vector position);
}
