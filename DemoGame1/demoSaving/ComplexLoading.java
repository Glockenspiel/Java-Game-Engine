package demoSaving;

import saving.GameStateI;
import saving.LoadingState;
import scripts.PlayerStatus;
import framework.Game;
import framework.GameObject;
import framework.Script;

public class ComplexLoading extends LoadingState {
	
	
	public ComplexLoading(){}
	
	//load the state into the game
	@Override
	public void loadState(GameStateI state) {
		super.loadState(state);
		
		ComplexGameState complexState=null;
		if(state instanceof ComplexGameState){
			complexState = (ComplexGameState)state;
		}
		else return;
		
		GameObject player = Game.getGameObjectByTag("Player");
		player.moveTo(complexState.getPlayerPos());
		
		//find the player status script
		Script script  = player.getScriptByClass(PlayerStatus.class);
		PlayerStatus status = null;
		if(script!=null){
			status = (PlayerStatus) script;
		}
		else{
			Game.print("PlayerStatus script not found when loading");
		}
		
		//set values
		status.setHealth(complexState.getHealth());
		status.setMoney(complexState.getMoney());
	}

}
