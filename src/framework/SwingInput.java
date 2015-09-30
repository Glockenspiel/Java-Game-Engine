package framework;

import java.util.ArrayList;
import java.util.Arrays;

public class SwingInput implements Input{
	
	//store booleans of keys pressed, each index relates to the key's unicode character
	final static int SIZE=128;
	static boolean [] prevInput = new boolean[SIZE];
	static boolean [] currentInput = new boolean[SIZE];
	
	
	public void setCurrentInput(){
		//set all values to false by default
		Arrays.fill(prevInput, Boolean.FALSE);
		Arrays.fill(currentInput, Boolean.FALSE);
	}
	
	@Override
	public void update(ArrayList<Integer> currentKeysPressed){
		//copy array of previous frame
		prevInput = Arrays.copyOf(currentInput, currentInput.length); 
		
		//set all pressed values to false
		Arrays.fill(currentInput, Boolean.FALSE);
		
		//set all keys pressed for this frame
		for(int i=0; i<currentKeysPressed.size(); i++){
			int keyIndex = currentKeysPressed.get(i);
			
			// check if valid key index
			if(keyIndex<SIZE && keyIndex>=0){
				currentInput[keyIndex]=true;
			}
		}
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
}
