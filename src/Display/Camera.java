package display;

import framework.Vector;

public interface Camera {
	
	public void moveBy(Vector amount);
	public void moveTo(Vector position);
	public Vector getPosition();
	public void followObjectID(int id, Vector offset);
	public void unFollow();
	public int getFollowId();
	public void update();
}
