package threading;

import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

import framework.Game;
import framework.GameObject;

//define your class
public class CollisionTask extends  RecursiveTask {
	private ArrayList<GameObject> objs;
	private int start,end; 
	public CollisionTask(ArrayList<GameObject> objs, int threadCount, int threadIndex) {
		this.objs=objs;
		int[] indexes = ForkAlg.findStartAndEndIndexes(objs.size(), threadCount, threadIndex);
		start = indexes[0];
		end = indexes[1];
	}
	
	//check collision for all the GameObjects in this task
	public Object compute() {
		for(int i=start; i<end; i++)
			Game.getServices().getCollisionManager().checkCollision(objs.get(i));
		return null;
	}
	
	

}