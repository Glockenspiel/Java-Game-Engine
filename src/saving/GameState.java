package saving;

import java.util.ArrayList;

import framework.GameObject;
import framework.GameObjectStateI;
import framework.Level;

public class GameState {

	private ArrayList<GameObjectStateI> objStates = new ArrayList<GameObjectStateI>();
	private Level currentLevel;
	
	public GameState(ArrayList<GameObject> objs, Level currentLevel){
		ArrayList<GameObjectStateI> objStates = new ArrayList<GameObjectStateI>();
		   for(GameObject o : objs){
			   objStates.add(o.getState());
		}
		   
		this.objStates.addAll(objStates);
		
		
		this.currentLevel=currentLevel;
	}
	
	public ArrayList<GameObjectStateI> getGameObjStates(){
		return new ArrayList<GameObjectStateI>(objStates);
	}
	
	public Level getCurrentLevel(){
		return currentLevel;
	}
}
