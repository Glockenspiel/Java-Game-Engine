package saving;

import framework.Level;

public class GameState implements GameStateI {

	private Level currentLevel;
	
	//constructor
	public GameState(Level currentLevel){
		this.currentLevel=currentLevel;
	}
	
	//get the current level
	public Level getCurrentLevel(){
		return currentLevel;
	}
}
