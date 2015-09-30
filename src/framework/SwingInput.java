package framework;

import java.util.Arrays;

public class SwingInput implements Input{
	
	//store booleans of keys pressed, each index relates to the key's unicode character
	final static int SIZE=128;
	static boolean [] prevInput = new boolean[SIZE];
	static boolean [] currentInput = new boolean[SIZE];
	
	
	public SwingInput(){
		//set all values to false by default
		Arrays.fill(prevInput, Boolean.FALSE);
		Arrays.fill(currentInput, Boolean.FALSE);
	}
	
	@Override
	public void update(){
		//copy array of previous frame
		prevInput = Arrays.copyOf(currentInput, currentInput.length); 
		//clear current input
		Arrays.fill(currentInput, Boolean.FALSE);
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
	public boolean iskeyPressed(char key){
		if(prevInput[key] == false && currentInput[key] == true)
			return true;
		return false;
	}
	
	
	//true if key released
	@Override
	public boolean iskeyReleased(char key){
		if(prevInput[key] == true && currentInput[key] == false)
			return true;
		return false;
	}

	//add key pressed to current frame
	@Override
	public void addKey(int keyCode) {
		//valid code
		if(keyCode<SIZE && keyCode>=0){
			currentInput[keyCode]=true;
		}
	}
}
