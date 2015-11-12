package services;

import java.awt.event.KeyEvent;
import java.util.Arrays;

import misc.Vector;

public class SwingInput implements Input{
	
	//store booleans of keys pressed, each index relates to the key's unicode character
	private final static int SIZE=128;
	private static boolean [] prevInput = new boolean[SIZE];
	private static boolean [] currentInput = new boolean[SIZE];
	
	//default constructor
	public SwingInput(){
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
	public boolean isKeyDown(char key){
		return currentInput[key];
	}
	
	//if current state of key is up
	@Override
	public boolean isKeyUp(char key){
		return !currentInput[key];
	}
	
	//true if key pressed down
	@Override
	public boolean isKeyPressed(char key){
		if(prevInput[key] == false && currentInput[key] == true)
			return true;
		return false;
	}
	
	
	//true if key released
	@Override
	public boolean isKeyReleased(char key){
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

	//returns vector direction of WASD and arrow keys
	@Override
	public Vector getDirectionInput() {
		int x=0,y=0;
		
		//up
		if(isKeyDown('W') || isKeyDown((char)KeyEvent.VK_UP)){
			y=-1;
		}
		//down
		else if(isKeyDown('S') || isKeyDown((char)KeyEvent.VK_DOWN)){
			y=1;
		}
		
		//left
		if(isKeyDown('A') || isKeyDown((char)KeyEvent.VK_LEFT)){
			x=-1;
		}
		//right
		else if(isKeyDown('D') || isKeyDown((char)KeyEvent.VK_RIGHT)){
			x=1;
		}
		
		return new Vector(x,y);
	}
}
