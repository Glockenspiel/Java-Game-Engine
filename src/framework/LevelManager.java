package framework;

import saving.GameStateI;

public class LevelManager implements LevelManagerI {

	//stores the current level and the desired next level
	private static Level currentLevel;
	private static Level nextLevel;
	
	//flags
	private static boolean changeLevel=false;
	private static boolean load=false;
	
	public LevelManager(){}
	
	//queues a level change
	public void queueChangeLevel(Level level){
		changeLevel=true;
		nextLevel=level;
	}
	
	//change the level if requested
	public void doChangeLevel(){
		if(changeLevel){
			changeLevel=false;
			currentLevel=nextLevel;
			Game.loadCurrentLevel();
		}
	}
	
	//returns the current level object
	public Level getCurrentLevel(){
		return currentLevel;
	}
	
	//sets the current Level
	public void setCurrentLevel(Level level){
		currentLevel = level;
	}
	
	//a state load is required
	public void load(){
		load=true;
	}
	
	//loads the latest state if a load() was called
	public void loadLatestState(){
		//if a load state is not required
		if(load==false) 
			return;
		
		//change flag to false
		load=false;
		
		GameStateI state = Game.getServices().getSaving().getLastState();
		
		//if no state is saved yet return
		if(state==null) 
			return;
		
		Game.getServices().getLoading().loadState(state);
	}
}