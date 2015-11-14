package services;

import misc.Vector;

public abstract class MouseInput {
	
	public static int LEFT=1,RIGHT=3,MIDDLE=2;
	
	public abstract void clear();
	public abstract boolean isButtonDown(int key);
	public abstract boolean isButtonUp(int key);
	public abstract boolean isButtonPressed(int key);
	public abstract boolean isButtonReleased(int key);
	public abstract void setPressed(int keyCode);
	public abstract void setReleased(int keyCode);
	public abstract Vector getMousePosition();
}
