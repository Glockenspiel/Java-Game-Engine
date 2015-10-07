package display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import framework.Game;
import framework.GameObject;

public class SwingPanel extends JPanel implements KeyListener {
	
	SwingDrawer drawer = new SwingDrawer();
	
	public SwingPanel(){
		this.addKeyListener(this);
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
		
		//draw all HUDItems
		for(GameObject o : objs){
			o.drawHUDItems(drawer);
		}
		
		//draw all debugging help 
		if(Game.isDrawingDebug())
			for(GameObject o : objs){
				o.debugDraw(drawer);
			}
	}


	//listen for key events in this panel
	@Override
	public void keyPressed(KeyEvent event) {
		Game.getInput().setPressed(event.getKeyCode());
	}


	@Override
	public void keyReleased(KeyEvent event) {
		Game.getInput().setReleased(event.getKeyCode());
	}


	@Override
	public void keyTyped(KeyEvent arg0) {}

}
