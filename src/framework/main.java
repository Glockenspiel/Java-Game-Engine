package framework;

import java.util.ArrayList;
import java.awt.*;

import javax.swing.*;

import Components.Sprite;
import DemoLevels.Level1;
import Display.SwingWindow;
import Scripts.ExampleScript;

public class main{

	public static void main(String[] args) {
		Game game = new Game();
		game.loadLevel(new Level1());
	}

}
