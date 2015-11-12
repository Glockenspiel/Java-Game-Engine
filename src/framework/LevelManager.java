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
	@Override
	public void queueChangeLevel(Level level){
		changeLevel=true;
		nextLevel=level;
	}
	
	//change the level if requested
	@Override
	public void doChangeLevel(){
		if(changeLevel){
			changeLevel=false;
			currentLevel=nextLevel;
			Game.loadCurrentLevel();
		}
	}
	
	//returns the current level object
	@Override
	public Level getCurrentLevel(){
		return currentLevel;
	}
	
	//sets the current Level
	@Override
	public void setCurrentLevel(Level level){
		currentLevel = level;
	}
	
	//a state load is required
	@Override
	public void load(){
		load=true;
	}
	
	//loads the latest state if a load() was called
	@Override
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

	@Override
	public void initCurrentLevel() {
		currentLevel.init();
	}
}
