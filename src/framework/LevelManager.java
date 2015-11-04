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
	public static void queueChangeLevel(Level level){
		changeLevel=true;
		nextLevel=level;
	}
	
	//change the level
	void doChangeLevel(){
		if(changeLevel){
			changeLevel=false;
			currentLevel=nextLevel;
			Game.loadCurrentLevel();
		}
	}
	
	public boolean getChangeLevel(){
		return changeLevel;
	}
	
	public Level getCurrentLevel(){
		return currentLevel;
	}
	
	public void setCurrentLevel(Level level){
		currentLevel = level;
	//	currentLevel.init();
	}
	
	public static void load(){
		load=true;
	}
	
	public static void loadLatestState(){
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
