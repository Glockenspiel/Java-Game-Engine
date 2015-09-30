package Display;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import framework.Game;
import framework.GameObject;

public class DrawPanel extends JPanel  {
	
	SwingDrawer drawer = new SwingDrawer();
	
	public DrawPanel(){
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.setBackground(new Color(0,255,255));
	}


	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawer.setGraphics(g);
		
		//draw all GameObjecs
		ArrayList<GameObject> objs = Game.copyOfGameObjects();
		for(GameObject o : objs){
			o.draw(drawer);
		}	
	}

}
