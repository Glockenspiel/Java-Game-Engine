package framework;

import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

import Display.SwingWindow;

public class main{
	
	//private static ArrayList<GameObject> objs  = new ArrayList<GameObject>();

	public static void main(String[] args) {
		Game game = new Game();
		game.setWindow(new SwingWindow());
		game.loadLevel("filename");
		game.testIDs();
		game.start();
	}

}
