package threading;

import java.util.ArrayList;
import java.util.Collection;

import framework.Game;
import framework.GameObject;

public class ThreadList {
	private static boolean once=false;
	private Split splits[];
	
	public ThreadList(Split threads[]){
		splits=threads;
	}
	
	//returns the thread at index
	public Split getThreadAtIndex(int i){
		if(i<0 || i>getThreadCount()){
			Game.print("Warning: SplitedThreads.getThreadAtIndex(), index out of bounds: " + i);
			return null;
		}
		return splits[i];
	}
	
	//updates the split at index
	public void updateSplit(Collection<?> objs, int splitIndex){
		 splits[splitIndex].update(objs,splitIndex, getThreadCount());
	}
	
	//runs all the threads and waits for them to all complete
	public void runAll(){
		for(int i=0; i<getThreadCount(); i++)
			splits[i].run();
			
		//wait for all threads to complete
		for(Split s: splits){
			try {
				s.join();
			} catch (InterruptedException e) 
				{e.printStackTrace();}
		}
	}
	
	//add a split at index
	public void addAt(Split split, int index){
		if(index<0 || index>=getThreadCount()) return;
		
		splits[index]=split;
	}

	//returns the amount of threads being used
	public int getThreadCount() {
		return splits.length;
	}

	//update the current collection in each of the splits
	public void update(ArrayList<?> objs) {
		for(int i=0; i<splits.length; i++){
			splits[i].update(objs, i, getThreadCount());
		}
	}

	//calls update() then calls runAll()
	public void updateAndRunAll(ArrayList<GameObject> objs) {
		update(objs);
		runAll();
	}
}
