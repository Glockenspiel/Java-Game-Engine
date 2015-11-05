package demo;

import demoSaving.ComplexLoading;
import demoSaving.ComplexSaving;
import saving.Saving;
import services.SwingWindow;
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
		Game.getServices().setWindow(new SwingWindow(0, 0, resolution.intX(), resolution.intY(), false, "Game"));
		Game.getServices().getWindow().showCursor(false);
		Game.getServices().setSaving(new ComplexSaving());
		Game.getServices().setLoading(new ComplexLoading());
		//custom cursor example
		//Game.getWindow().setCustomCursor("triangle.png");
		Game.getServices().enableDebugDraw(true);
		
		Game.start(new Level2());
		
	}

}
