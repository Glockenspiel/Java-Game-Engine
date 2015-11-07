package threading;

import java.util.Collection;

public abstract class Split extends Thread {

	protected int startingIndex,endIndex;
	
	//evenly distributes the size of the objs to the start and end indexes
	protected void findStartAndEndIndexes(int splitIndex, int arraySize, int threadCount){
		//int threadCount=ThreadList.getThreadCount();
		int objsPerThread = arraySize/threadCount;
		
		//bonus is when a thread has to computer an extra index
		int bonus=arraySize%threadCount;
		int originalBonus=bonus, bonusDiff;
		
		int maxIndex=0, minIndex=0;
			
		for(int i=0; i<=splitIndex; i++){
			minIndex=maxIndex;
			//keep track of amount of times the bonus index was given
			bonusDiff = originalBonus-bonus;
			//last thread split, so set max to last index of objs
			if(i+1==threadCount){
				maxIndex=arraySize;
				i++;
			}
			else{
				//if a bonus index has to be given to a split
				if(bonus>0){
					maxIndex = (i+1)*(objsPerThread) + bonusDiff;
					bonus--;
				}
				//no bonus given
				else
					maxIndex=((i+1)*objsPerThread);
			}
		}
		startingIndex=minIndex;
		endIndex=maxIndex;
	}

	public abstract void update(Collection<?> objs, int splitIndex, int threadCount);
}
