package framework;

public interface Input {
	
	//clear input for next frame
	void clear();
	
	//if current state of key is down
	public boolean isKeyDown(char key);
		
	//if current state of key is up
	public boolean isKeyUp(char key);
		
	//true if key pressed down
	public boolean isKeyPressed(char key);
		
	//true if key released
	public boolean isKeyReleased(char key);
	
	//returns the magnitude for the directional input
	public Vector getDirectionInput();

	//sets a key pressed
	void setPressed(int keyCode);
	
	//sets a key released
	void setReleased(int keyCode);
}
