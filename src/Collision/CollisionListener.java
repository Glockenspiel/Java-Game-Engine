package collision;

import framework.Script;

public interface CollisionListener extends Script {
	//events that will be invoked in the following situations
	
	//when a trigger owned by this object detects an overlap of a collision shape from another object (ignores trigger overlaps)
	public void onTrigger(String tag, int id);
}
