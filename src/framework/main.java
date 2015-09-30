package framework;

import java.util.ArrayList;
import java.awt.*;

import javax.swing.*;

import Components.ExampleComponent;
import Components.Sprite;
import Display.SwingWindow;
import Levels.Level1;

public class main{

	public static void main(String[] args) {
		Game game = new Game();
		game.setWindow(new SwingWindow());
		game.loadLevel(new Level1());
	}

}
