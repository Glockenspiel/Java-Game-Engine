package Collision;

public interface CollisionListener {
	//events that will be invoked in the following situations
	
	//when this object collides with another object and returns the tag of the object it collided with
	public void onCollision(String tag);
	
	//when a trigger owned by this object detects an overlap of a collision shape from another object (ignores trigger overlaps)
	public void onTrigger(String tag);
}
