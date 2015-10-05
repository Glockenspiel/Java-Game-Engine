package framework;

import java.util.ArrayList;

public interface Input {
	
	//clear input for next frame
	void clear();
	
	//if current state of key is down
	public boolean isKeyDown(char key);
		
	//if current state of key is up
	public boolean isKeyUp(char key);
		
	//true if key pressed down
	public boolean iskeyPressed(char key);
		
	//true if key released
	public boolean iskeyReleased(char key);
	
	public Vector getDirectionInput();

	void setPressed(int keyCode);
	
	void setReleased(int keyCode);
}
