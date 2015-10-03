package framework;

import DemoLevels.Level1;


public class main{

	public static void main(String[] args) {
		Game game = new Game();
		game.enableDebugDraw(true);
		game.loadLevel(new Level1());
		Game.start();
	}

}
