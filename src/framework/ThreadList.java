package framework;

import java.util.ArrayList;
import java.util.Collection;

//this class allows you to to create muiple parallel threads
public class ThreadList {
	
	private static int threadCount = 4;
	private static boolean once=false;
	private Split splites[];
	
	public ThreadList(Split threads[]){
		once=true;
		splites=threads;
	}
	
	public Split getThreadAtIndex(int i){
		if(i<0 || i>threadCount){
			Game.print("Warning: SplitedThreads.getThreadAtIndex(), index out of bounds: " + i);
			return null;
		}
		return splites[i];
	}
	
	public void setSplit(Collection<?> objs, int splitIndex){
		 splites[splitIndex].setValues(objs,splitIndex);
	}
	
	//runs all the threads and waits for them to all complete
	public void runAll(){
		for(int i=0; i<threadCount; i++)
			splites[i].run();
			
		//wait for all threads to complete
		for(int i = 0; i < threadCount; i++){
			try {
				splites[i].join();
			} catch (InterruptedException e) 
				{e.printStackTrace();}
		}
	}
	
	public void addAt(Split split, int index){
		if(index<0 || index>=threadCount) return;
		
		splites[index]=split;
	}

	public static int getThreadCount() {
		return threadCount;
	}
	
	//set the thread count, only before a ThreadList is created
	public static void setThreadCount(int count){
		//threadCount can only be set before ThreadList's constructor is called
		if(!once && count>0){
			threadCount=count;
		}
	}
}
