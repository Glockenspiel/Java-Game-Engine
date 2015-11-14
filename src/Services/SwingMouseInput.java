package services;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import misc.Vector;

public class SwingMouseInput extends MouseInput{

	//store booleans of keys pressed, each index relates to the key's unicode character
	private final static int SIZE=128;
	private static boolean [] prevInput = new boolean[SIZE];
	private static boolean [] currentInput = new boolean[SIZE];
	Vector mousePos = new Vector(0,0);
	
	//default constructor
	public SwingMouseInput(){
		//set all values to false by default
		Arrays.fill(prevInput, Boolean.FALSE);
		Arrays.fill(currentInput, Boolean.FALSE);
	}
	
	//clears the input buffer
	@Override
	public void clear(){
		//copy array of previous frame
		prevInput = Arrays.copyOf(currentInput, currentInput.length); 
	}
	
	//if current state of key is down
	@Override
	public boolean isButtonDown(int key){
		return currentInput[key];
	}
	
	//if current state of key is up
	@Override
	public boolean isButtonUp(int key){
		return !currentInput[key];
	}
	
	//true if key pressed down
	@Override
	public boolean isButtonPressed(int key){
		if(prevInput[key] == false && currentInput[key] == true)
			return true;
		return false;
	}
	
	
	//true if key released
	@Override
	public boolean isButtonReleased(int key){
		if(prevInput[key] == true && currentInput[key] == false)
			return true;
		return false;
	}

	//add key pressed to current frame
	@Override
	public void setPressed(int keyCode) {
		//valid code
		if(keyCode<SIZE && keyCode>=0){
			currentInput[keyCode]=true;
		}
	}

	//sets a key is pressed
	@Override
	public void setReleased(int keyCode) {
		if(keyCode<SIZE && keyCode>=0){
			currentInput[keyCode]=false;
		}
	}
	
	@Override
	public Vector getMousePosition(){
		PointerInfo a = MouseInfo.getPointerInfo();
		Point pos = a.getLocation();
		return new Vector((int)pos.getX(), (int)pos.getY());
	}
}
