package services;

public class InputManager {
	
	private static MouseInput mouse;
	private static KeyBoardInput keyBoard;
	
	public static byte PC=1, Android=2;
	
	private static byte INPUT_TYPE;
	
	public InputManager(byte inputType){
		INPUT_TYPE=inputType;
		if(inputType == PC){
			mouse = new SwingMouseInput();
			keyBoard = new SwingKeyBoardInput();
		}
	}
	
	public static MouseInput getMouse(){
		return mouse;
	}
	
	public static KeyBoardInput getKeyBoard(){
		return keyBoard;
	}
	
	public static void clearInput(){
		if(INPUT_TYPE==PC){
			mouse.clear();
			keyBoard.clear();
		}
	}
	
	public static String getInputType(){
		switch(INPUT_TYPE){
			case(1) : return "PC";
			case(2) : return "Android";
		}
		
		return "input type not set";
	}
}
