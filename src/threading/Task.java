package threading;

import java.util.concurrent.ForkJoinTask;

public interface Task {
	public void joinTask();
	public void computeTask();
	public void forkTask();
}
