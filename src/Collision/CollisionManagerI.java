package Collision;

import java.util.ArrayList;

import framework.GameObject;

public interface CollisionManagerI {
	
	public void detect(ArrayList<GameObject> objs);
	public void resolve(ArrayList<GameObject> objs);
}
