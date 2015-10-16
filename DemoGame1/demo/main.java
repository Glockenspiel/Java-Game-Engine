package demo;

import framework.Game;
import levels.Level1;
import levels.Level2;


public class main{

	public static void main(String[] args) {
		Game game = new Game();
		//uncomment this to enable debug drawing
		game.enableDebugDraw(true);
		Game.start(new Level2());
	}

}
