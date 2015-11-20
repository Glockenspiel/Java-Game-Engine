package scripts;

import framework.Game;
import framework.GameObject;
import framework.Script;

public class ToggleDebugDrawing implements Script {

	@Override
	public void execute(GameObject obj) {
		//toggle debug drawing
		if(Game.Keyboard().isButtonPressed('L')){
			Game.getServices().setDebugDraw(!Game.getServices().isDrawingDebug());
		}
	}

	@Override
	public void construct(String[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getSaveArgs() {
		// TODO Auto-generated method stub
		return null;
	}

}
