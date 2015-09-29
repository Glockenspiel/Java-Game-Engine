package framework;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DrawPanel extends JPanel  {
	public DrawPanel(){
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.setBackground(new Color(150,150,150));
	}


	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//draw all GameObjecs
		ArrayList<GameObject> objs = Game.copyOfGameObjects();
		for(GameObject o : objs){
			o.draw(g);
		}	
	}

}
