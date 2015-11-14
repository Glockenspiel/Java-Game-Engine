package services;

import misc.Vector;

public interface KeyBoardInput {
	
	//clear input for next frame
	void clear();
	
	//if current state of key is down
	public boolean isButtonDown(int key);
		
	//if current state of key is up
	public boolean isButtonUp(int key);
		
	//true if key pressed down
	public boolean isButtonPressed(int key);
		
	//true if key released
	public boolean isButtonReleased(int key);
	
	//returns the magnitude for the directional input
	public Vector getDirectionInput();

	//sets a key pressed
	void setPressed(int keyCode);
	
	//sets a key released
	void setReleased(int keyCode);
}
