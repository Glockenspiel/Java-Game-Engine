package threading;

import java.util.concurrent.RecursiveTask;

public abstract class Task extends RecursiveTask {
	
	//calls join() for the recursive task
	public void joinTask(){
		join();
	}
	
	//calls compute() for the recursive task
	public void computeTask(){
		compute();
	}
	
	//calls fork() in the recursive task
	public void forkTask(){
		fork();
	}
	
	//computes the function until completion. See RecursiveTask
	public abstract Object compute();
}
