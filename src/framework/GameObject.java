package framework;

import java.awt.Color;
import java.util.ArrayList;

import Collision.CollisionShape;
import components.HUDItem;
import debugging.Debug;
import display.Drawer;
import display.SwingDrawer;

public class GameObject implements Debug{ // also known as an Entity
	
	//-1 invalid ID
	private int id=-1;
	private String tag="";
	private ArrayList<Component> components = new ArrayList<Component>();
	private ArrayList<CollisionShape> collisionShapes = new ArrayList<CollisionShape>();
	private ArrayList<Script> scripts = new ArrayList<Script>();
	private Vector position = new Vector(0,0);
	private Thread deleteThread;
	private boolean once=true;
	
	//get unique id of this gameObject
	public int getID(){
		//if id hasn't been generated yet, generate it
		if(id < 0) 
			generateID();
		
		return id;
	}
	
	private void generateID(){
		//todo: use UUID 
		id=(int)(Math.random() * ((1000 - 1) + 1)); //random number between 1000-1
	}
	
	//add a component to the gameObject
	public void add(Component c){
		c.setGameObjectTag(tag);
		components.add(c);
	}
	
	public void add(CollisionShape shape){
		collisionShapes.add(shape);
	}
	
	public void add(Script s){
		scripts.add(s);
	}
	
	public void removeCollisionShapesByTag(String tag){
		for(int i=0; i<collisionShapes.size(); i++){
			if(collisionShapes.get(i).getTag().equalsIgnoreCase(tag))
				collisionShapes.remove(i);
		}
	}
	
	//returns first found component with matching type
	public Component getComponentByType(String type){
		for(Component c : components)
			if(c.getType().equalsIgnoreCase(type))
				return c;
		
		return null;
	}
	
	//returns all components with matching type
	public void getAllComponentsByType(String type, ArrayList<Component> comps){
		for(Component c: components)
			if(c.getType().equalsIgnoreCase(type))
				comps.add(c);
	}
	
	public ArrayList<CollisionShape> getCollisionShapes(){
		return collisionShapes;
	}

	//update all components
	public void update(){
		if(once){
			if(deleteThread!=null)
				deleteThread.start();
			once=false;
		}
		
		for(Script s: scripts)
			s.execute(this);
		
		for(Component c : components)
			c.update(this);
	}
	
	//draw all components
	public void draw(Drawer g){
		for(Component c : components)
			c.draw(g, this.position);
	}
	
	//return a copy of position to avoid breaking encapsulation
	public Vector getPosition(){
		return new Vector(position.getX(), position.getY());
	}
	
	public void moveBy(Vector amount){
		position.moveBy(amount);
	}
	
	public void moveTo(Vector position){
		this.position.moveTo(position);
	}
	
	//get tag of this GameObject
	public String getTag(){
		return tag;
	}

	public GameObject(String tag){
		this.tag=tag;
		
	}
	
	//deletes this game object
	public void delete(){
		Game.deleteObjByID(id);
	}
	
	//deletes this object in a given amount of time (milliseconds)
	public void delete(long milliseconds){
		getID();
		deleteThread = new Thread(){
			public void run(){
				try {
					sleep(milliseconds);
					Game.deleteObjByID(getID());
					
				} catch (InterruptedException e) {
					//this will be interrupted when loading new level
				}
				
			}
		};
	}

	@Override
	public void debugDraw(Drawer g, Vector objPos) {
		//draw origin of game object
		Color lineColor = Color.GREEN;
		int lineLength=20;
		g.drawLine(position.intX()-lineLength/2, position.intY(), lineLength, 0, lineColor);
		g.drawLine(position.intX(), position.intY()-lineLength/2, 0, lineLength, lineColor);
		
		g.drawText(getPosition().toString(), position.intX()+2, position.intY()-5, lineColor);
		
		//draw debug of all components that are instances of debug
		for(Component c : components)
		if(c instanceof Debug){
            ((Debug) c).debugDraw(g, getPosition());
		}
		for(CollisionShape s : collisionShapes){
			if(s instanceof Debug){
				((Debug) s).debugDraw(g, getPosition());
			}
		}
	}

	//finds which scripts implement HUDItem and draw the HUD
	public void drawHUDItems(SwingDrawer drawer) {
		for(Script s : scripts){
			if(s instanceof HUDItem){
				((HUDItem) s).drawGUI(drawer);
			}
		}
	}

	//ends all threads within this GameObject with interrupts
	public void interruptThreads() {
		if(deleteThread!=null)
			deleteThread.interrupt();
		for(Component c : components)
			c.interruptThreads();
		for(Script s: scripts)
			s.interuptThreads();
	}
}
