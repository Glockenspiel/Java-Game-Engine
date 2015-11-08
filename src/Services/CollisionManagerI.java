package services;

import java.util.ArrayList;

import framework.GameObject;

public interface CollisionManagerI {
	//detect collisions
	public void detect(ArrayList<GameObject> objs);
	
	//check if the Current object collides with any object in objs
	public void checkCollision(GameObject currentObj);
	
	//update the tree used
	public void updateTree(ArrayList<GameObject> objs);
	
	//to be removed later
	public void printAvgTime();
}
