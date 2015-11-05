package collision;

import framework.Script;

public interface CollisionListener extends Script {
	//this is called when a CollisionShape owned by this object detects an overlap of a CollisionShape from another object (ignores trigger overlaps)
	public void onTrigger(String tag, int id);
}
