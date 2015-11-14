package scripts;

import framework.Game;
import framework.GameObject;
import framework.Script;

public class ToggleDebugDrawing implements Script {

	@Override
	public void execute(GameObject obj) {
		//toggle debug drawing
		if(Game.getInput().isButtonPressed('L')){
			Game.getServices().setDebugDraw(!Game.getServices().isDrawingDebug());
		}
	}

}
