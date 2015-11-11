package scripts;

import framework.Game;
import framework.GameObject;
import framework.Script;

public class SavingAndLoading implements Script {

	@Override
	public void execute(GameObject obj) {
		//save button
		if(Game.getInput().isKeyPressed('B')){
			Game.getServices().getSaving().saveState();
		}
		
		//load button
		if(Game.getInput().isKeyPressed('N')){
			Game.loadLatestSave();
		}
	}

}
