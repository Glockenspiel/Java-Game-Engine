package framework;

import java.util.ArrayList;

public interface Input {
	
	void update();
	
	//if current state of key is down
	public boolean isKeyDown(char key);
		
	//if current state of key is up
	public boolean isKeyUp(char key);
		
	//true if key pressed down
	public boolean iskeyPressed(char key);
		
	//true if key released
	public boolean iskeyReleased(char key);

	void addKey(int keyCode);
}
