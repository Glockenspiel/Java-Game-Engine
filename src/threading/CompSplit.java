package threading;

import java.util.ArrayList;
import java.util.Collection;

import framework.GameObject;

public class CompSplit extends Split {

	private ArrayList<GameObject> objs;
	
	public CompSplit(ArrayList<GameObject> objs, int splitIndex){
		//this.objs=objs;
	//	findStartAndEndIndexes(splitIndex, objs.size());
	}
	
	@Override
	public void update(Collection<?> objs,int splitIndex, int threadCount){
		this.objs=(ArrayList<GameObject>) objs;
		findStartAndEndIndexes(splitIndex, objs.size(), threadCount);
	}

	@Override
	public void run() {
		//uncomment this to see examples of how the start and end indexes where generated
		//Game.print("" + startingIndex + ":" + endIndex);
		for(int i=startingIndex; i<endIndex; i++)
			objs.get(i).updateComp();
	}
}
