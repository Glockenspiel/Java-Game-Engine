package saving;

import scripts.PlayerStatus;
import framework.Script;
import framework.Vector;
import framework.Game;
import framework.GameObject;

public class ComplexSaving extends Saving {
	
	public ComplexSaving(){
		super();
	}
	
	@Override
	public void saveState() {
		GameObject player = Game.getGameObjectByTag("Player");
		if(player==null){
			Game.print("saving failed to find GameObject by tag: Player");
		}
			
		Vector pos = player.getPosition();
		Script script = player.getScriptByClass(PlayerStatus.class);
		if(script==null){
			Game.print("saving failed to find Script in player GameObject with class PlayerStatus");
		}
		PlayerStatus status = (PlayerStatus) script;
		int health = status.getHP();
		int money = status.getMoney();
		
		ComplexGameState state = new ComplexGameState(Game.getCurrentLevel(), pos, health, money);
		super.originator.setState(state);
		careTaker.add(originator.saveStateToMemento());
	}
}
