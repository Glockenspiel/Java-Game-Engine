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

}
