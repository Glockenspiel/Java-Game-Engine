package scripts;

import levels.*;
import framework.Game;
import framework.GameObject;
import framework.Level;
import collision.CollisionListener;

public class ChangeLevel implements CollisionListener {

	Level level;
	
	public ChangeLevel(String levelName){
		Class cls = null;
		try {
			cls = Class.forName(levelName);
		} catch (ClassNotFoundException e1) {e1.printStackTrace();}
		try {
			level = (Level) cls.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void execute(GameObject obj) {}

	@Override
	public void onTrigger(String tag, int id) {
		if(tag.equalsIgnoreCase("Player"))
			Game.changeLevel(level);
	}
}
