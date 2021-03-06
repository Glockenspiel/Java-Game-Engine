package collision;

import misc.Vector;

public interface CollisionShape {
	public boolean getAlive();
	public void setAlive(boolean isAlive);
	public String getType();
	public String getTag();
	public void setTag(String tag);
	public void notify(Vector position);
	public int minX();
	public int minY();
	public int maxX();
	public int maxY();
	public void construct(String[] args);
	public String[] getSaveArgs();
}
