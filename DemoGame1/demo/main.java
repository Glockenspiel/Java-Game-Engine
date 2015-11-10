package demo;

import interceptor.DebugFilter;
import interceptor.FormatFilter;
import demoSaving.ComplexLoading;
import demoSaving.ComplexSaving;
import saving.Saving;
import services.SwingPrint;
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
		Game.getServices().setPrintTarget(new SwingPrint());
	//	Game.getServices().getFilterManager().setFilter(new FormatFilter());
	//	Game.getServices().getFilterManager().setFilter(new DebugFilter());
		
		//custom cursor example
		//Game.getServices().getWindow().setCustomCursor("triangle.png");
		Game.getServices().setDebugDraw(true);
		
		Game.start(new Level1());
		
	}

}
