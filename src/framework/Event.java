package framework;

public interface Event extends Script{
	//invokes the event
	public void notify(String tag, int value);
}
