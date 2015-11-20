package scripts;

import java.awt.event.KeyEvent;

import levelloading.ObjConstructor;
import framework.Game;
import framework.GameObject;
import framework.Level;
import framework.Script;

public class ToggleLevel implements Script {

	private Level level;
	
	public ToggleLevel(){}
	
	//loads to level given when Key M is pressed
	public ToggleLevel(Level level){
		this.level=level;
	}
	
	//command to change level if key M is pressed
	@Override
	public void execute(GameObject obj) {
		if(Game.Keyboard().isButtonPressed((char)KeyEvent.VK_M)){
				Game.changeLevel(level);
		}
	}

	@Override
	public void construct(String[] args) {
			level = (Level) ObjConstructor.classNameToObject(args[0]);
	}

	@Override
	public String[] getSaveArgs() {
		
		String [] args = new String[1];
		args[0]=level.getClass().getName();
		
		return args;
	}
}
