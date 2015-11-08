package demo;

import demoSaving.ComplexLoading;
import demoSaving.ComplexSaving;
import saving.Saving;
import services.SwingWindow;
import framework.Game;
import framework.LauncherOptions;
import levels.Level1;
import levels.Level2;
import misc.Vector;


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
		//Game.getServices().getWindow().setCustomCursor("triangle.png");
		Game.getServices().enableDebugDraw(true);
		
		Game.start(new Level1());
		
	}

}
