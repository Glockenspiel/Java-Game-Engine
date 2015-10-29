package Collision;

import java.util.ArrayList;

import framework.GameObject;

public interface CollisionManagerI {
	//detect collisions
	public void detect(ArrayList<GameObject> objs);
	
	//resolve collisions
	public void resolve(ArrayList<GameObject> objs);
}
