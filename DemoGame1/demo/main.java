package demo;

import display.SwingWindow;
import framework.Game;
import framework.LauncherOptions;
import framework.Vector;
import levels.Level1;
import levels.Level2;


public class main{

	public static void main(String[] args) {
		Game game = new Game();
		Vector resolution = LauncherOptions.launch();
		//uncomment this to enable debug drawing
		game.setWindow(new SwingWindow(0, 0, resolution.intX(), resolution.intY(), false, "Game"));
		//game.getWindow().setPreferredSize(resolution.intX(), resolution.intY());
		game.enableDebugDraw(true);
		Game.start(new Level2());
		
	}

}
