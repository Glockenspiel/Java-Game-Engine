package threading;

import java.util.ArrayList;
import java.util.Collection;

import framework.Game;
import framework.GameObject;

public class CollisionSplit extends Split {

	private ArrayList<GameObject> objs;
	
	public CollisionSplit(){}
	
	@Override
	public void update(Collection<?> objs, int splitIndex, int threadCount) {
		this.objs = (ArrayList<GameObject>) objs;
		super.findStartAndEndIndexes(splitIndex, objs.size(), threadCount);
	}

	//check the collision for all the objects
	@Override
	public void run(){
		for(int i=startingIndex; i<endIndex; i++)
			Game.getServices().getCollisionManager().checkCollision(objs.get(i));
	}
}
