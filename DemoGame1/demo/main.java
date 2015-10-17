package demo;

import display.SwingWindow;
import framework.Game;
import levels.Level1;
import levels.Level2;


public class main{

	public static void main(String[] args) {
		Game game = new Game();
		//uncomment this to enable debug drawing
		game.setWindow(new SwingWindow(100, 50, 854, 480, false, "Game"));
		game.getWindow().setPreferredSize(512, 256);
		game.enableDebugDraw(true);
		Game.start(new Level2());
	}

}
