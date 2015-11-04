package demo;

import display.SwingWindow;
import framework.Game;
import framework.LauncherOptions;
import framework.Vector;
import levels.Level1;
import levels.Level2;


public class main{

	public static void main(String[] args) {
		//create the game 
		Game game = new Game();
		
		Vector resolution = LauncherOptions.launch();
		Game.getServiceManager().setWindow(new SwingWindow(0, 0, resolution.intX(), resolution.intY(), false, "Game"));
		Game.getServiceManager().getWindow().showCursor(false);
		//custom cursor example
		//Game.getWindow().setCustomCursor("triangle.png");
		Game.enableDebugDraw(true);
		
		Game.start(new Level2());
		
	}

}
