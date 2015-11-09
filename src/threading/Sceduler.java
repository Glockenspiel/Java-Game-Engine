package threading;


public class Sceduler {
	
	public static final int THREAD_COUNT = Runtime.getRuntime().availableProcessors();
	
	//forks all the tasks and waits for them to complete
	public static void forkAndJoin(	Task[] tasks){
		boolean lastIndex;
		//fork each tasks
		for(int i=0; i<tasks.length; i++){
			lastIndex = i+1 ==tasks.length;
			if(!lastIndex){
				tasks[i].forkTask();
			}
			//compute the last task until it completes
			else{
				tasks[i].computeTask();
			}
		}
	
		//wait for all task that were forked to complete
		for(int i=0; i<tasks.length-1; i++)
			tasks[i].joinTask();
	}



/* evenly distributes the size of the array to the start and end indexes
 * returns start and end index as an integer array
 * use this algorithm instead of simply dividing by the number of threads
 * 
 * worst case:
 * objs.size() = 27
 * 
 * Algorithm result from worst case example:
 * thread 1: 8 indexes
 * thread 2: 8 indexes
 * thread 3: 8 indexes
 * thread 4: 7 indexes
 * 
 * 
 * works case example from dividing by 4
 * thread 1: 7 indexes
 * thread 2: 7 indexes
 * thread 3: 7 indexes
 * thread 4: 13 indexes
 */
public static  int[] findStartAndEndIndexes(int arraySize,int taskIndex, int taskCount){
		int objsPerThread = arraySize/taskCount;
		
		//bonus is when a thread has to computer an extra index
		int bonus=arraySize%taskCount;
		int originalBonus=bonus, bonusDiff;
		
		int maxIndex=0, minIndex=0;
			
		for(int i=0; i<=taskIndex; i++){
			minIndex=maxIndex;
			//keep track of amount of times the bonus index was given
			bonusDiff = originalBonus-bonus;
			//last thread split, so set max to last index of objs
			if(i+1==taskCount){
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
		int [] indexes = new int[]{minIndex, maxIndex};
		return indexes;
	}

}
