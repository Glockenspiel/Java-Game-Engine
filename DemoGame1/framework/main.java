package framework;

import levels.Level1;


public class main{

	public static void main(String[] args) {
		Game game = new Game();
		//uncomment this to enable debug drawing
		//game.enableDebugDraw(true);
		Game.start(new Level1());
		
		//this line of code also work instead of 2 lines
		//new Game().start(new Level1());
	}

}
