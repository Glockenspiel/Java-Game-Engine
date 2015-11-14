package display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import misc.Vector;
import framework.Game;
import framework.GameObject;

@SuppressWarnings("serial")
public class SwingPanel extends JPanel implements KeyListener {
	
	private Drawer drawer;
	
	//constructor
	public SwingPanel(){
		drawer =  new SwingDrawer();
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.setBackground(new Color(0,200,200));
		
		addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	            Game.getServices().getMouse().setPressed(me.getButton());
	          } 
	          
	          public void mouseReleased(MouseEvent me){
	        	  Game.getServices().getMouse().setReleased(me.getButton());
	          }
	        });
	}


	//paints the game in the correct order
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawer.setGraphics(g);
		
		//draw all GameObjecs
		ArrayList<GameObject> objs = Game.copyOfGameObjects();
		
		//sort objs by drawLayer
		Collections.sort(objs, new Comparator<GameObject>(){
		     public int compare(GameObject o1, GameObject o2){
		    	return  o1.getDrawLayer()-o2.getDrawLayer();
		     } 
		});
		
		for(GameObject o : objs){
			o.draw(drawer);
		}
		
		//draw all HUDItems
		for(GameObject o : objs){
			o.drawHUDItems(drawer);
		}
		
		//draw all debugging help 
		Vector zero = new Vector(0,0);
		if(Game.getServices().isDrawingDebug()){
			for(GameObject o : objs){
				o.debugDraw(drawer, zero);
			}
			
			//draw quad trees
			if(Game.getServices().getCollisionManager()!=null)
				Game.getServices().getCollisionManager().drawQuadTree(drawer);	
			
			//draw frame execution time
			drawer.drawHUDText("Frame time: "+ (double)((double)Game.getFrameTime()/1000) + "ms", 
					Game.getServices().getWindow().getPreferredWidth()/2-50, 10, Color.RED);
		}
	}


	//listen for key events in this panel
	//notify the input buffer of the key press
	@Override
	public void keyPressed(KeyEvent event) {
		if(Game.getServices().getInput()!=null)
			Game.getServices().getInput().setPressed(event.getKeyCode());
	}

	//notify the input buffer of the key release
	@Override
	public void keyReleased(KeyEvent event) {
		if(Game.getServices().getInput()!=null)
			Game.getServices().getInput().setReleased(event.getKeyCode());
	}

	//do nothing
	@Override
	public void keyTyped(KeyEvent arg0) {}
}
