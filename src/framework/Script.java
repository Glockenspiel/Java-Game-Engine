package framework;

public interface Script {
	//executes a command
	public void execute(GameObject obj);

	public void construct(String[] args);
	
	public String[] getSaveArgs();
}
