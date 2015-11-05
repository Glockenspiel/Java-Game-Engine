package collision;

import framework.Vector;

public interface CollisionShape {
	public boolean getAlive();
	public void setAlive(boolean isAlive);
	public String getType();
	public String getTag();
	public void setTag(String tag);
	public void notify(Vector position);
}
