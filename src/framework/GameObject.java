package framework;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import Debugging.Debug;
import Display.Drawer;

public class GameObject implements Debug{ // also known as an Entity
	
	//-1 invalid ID
	private int id=-1;
	private String tag="";
	private ArrayList<Component> components = new ArrayList<Component>();
	private ArrayList<Script> scripts = new ArrayList<Script>();
	private Vector position = new Vector(0,0);
	
	//get unique id of this gameObject
	public int getID(){
		//if id hasn't been generated yet, generate it
		if(id < 0) 
			generateID();
		
		return id;
	}
	
	private void generateID(){
		//todo: use UUID 
		id=(int)(Math.random() * ((100 - 1) + 1)); //random number between 100-1
	}
	
	//add a component to the gameObject
	public void add(Component c){
		c.setGameObjectTag(tag);
		components.add(c);
	}
	
	public void add(Script s){
		scripts.add(s);
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

	//update all components
	public void update(){
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

	@Override
	public void debugDraw(Drawer g) {
		//draw origin of game object
		Color lineColor = Color.RED;
		int lineLength=20;
		g.drawLine(position.intX()-lineLength/2, position.intY(), lineLength, 0, lineColor);
		g.drawLine(position.intX(), position.intY()-lineLength/2, 0, lineLength, lineColor);
		
		//draw debug of all components that are instances of debug
		for(Component c : components)
		if(c instanceof Debug){
            ((Debug) c).debugDraw(g);
		}
	}
}
