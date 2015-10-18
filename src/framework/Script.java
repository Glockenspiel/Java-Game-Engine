package framework;

public interface Script {
	//executes a command
	public void execute(GameObject obj);
	
	//Interrupt any threads in this command
	public void interuptThreads();
}
