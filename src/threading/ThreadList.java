package threading;

import java.util.ArrayList;
import java.util.Collection;

import framework.Game;
import framework.GameObject;

//this class allows you to to create muiple parallel threads
public class ThreadList {
	private static boolean once=false;
	private Split splits[];
	
	public ThreadList(Split threads[]){
		splits=threads;
	}
	
	public Split getThreadAtIndex(int i){
		if(i<0 || i>getThreadCount()){
			Game.print("Warning: SplitedThreads.getThreadAtIndex(), index out of bounds: " + i);
			return null;
		}
		return splits[i];
	}
	
	public void setSplit(Collection<?> objs, int splitIndex){
		 splits[splitIndex].update(objs,splitIndex, getThreadCount());
	}
	
	//runs all the threads and waits for them to all complete
	public void runAll(){
		for(int i=0; i<getThreadCount(); i++)
			splits[i].run();
			
		//wait for all threads to complete
		for(int i = 0; i < getThreadCount(); i++){
			try {
				splits[i].join();
			} catch (InterruptedException e) 
				{e.printStackTrace();}
		}
	}
	
	public void addAt(Split split, int index){
		if(index<0 || index>=getThreadCount()) return;
		
		splits[index]=split;
	}

	public int getThreadCount() {
		return splits.length;
	}

	public void update(ArrayList<GameObject> objs) {
		for(int i=0; i<splits.length; i++){
			splits[i].update(objs, i, getThreadCount());
		}
	}
}
