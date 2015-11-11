package threading;

import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

import framework.Game;
import framework.GameObject;

//define your class
public class CollisionTask extends Task{
	private ArrayList<GameObject> objs;
	private int start,end; 
	public CollisionTask(ArrayList<GameObject> objs, int taskIndex, int taskCount) {
		this.objs=objs;
		int[] indexes = Scheduler.findStartAndEndIndexes(objs.size(), taskIndex, taskCount);
		start = indexes[0];
		end = indexes[1];
	}
	
	//check collision for all the GameObjects in this task
	@Override
	public Object compute() {
		for(int i=start; i<end; i++)
			Game.getServices().getCollisionManager().checkCollision(objs.get(i));
		return null;
	}
}