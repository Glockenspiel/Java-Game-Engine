package scripts;

import java.awt.event.KeyEvent;

import framework.Game;
import framework.GameObject;
import framework.Level;
import framework.Script;

public class ToggleLevel implements Script {

	private Level level;
	
	//loads to level given when Key M is pressed
	public ToggleLevel(Level level){
		this.level=level;
	}
	
	//command to change level if key M is pressed
	@Override
	public void execute(GameObject obj) {
		if(Game.getInput().isButtonPressed((char)KeyEvent.VK_M)){
				Game.changeLevel(level);
		}
	}
}
