package framework;

public interface EventListener extends Script{
	//invokes the event
	public void notify(String eventName, int value);
}
