package saving;

import framework.Level;

public class GameState implements GameStateI {

	private Level currentLevel;
	
	public GameState(Level currentLevel){
		this.currentLevel=currentLevel;
	}
	
	public Level getCurrentLevel(){
		return currentLevel;
	}
}
