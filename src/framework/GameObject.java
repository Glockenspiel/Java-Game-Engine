package framework;

import java.awt.Graphics;
import java.util.ArrayList;

import Display.GraphicDrawer;

public class GameObject { // also known as an Entity
	
	//-1 invalid ID
	private int id=-1;
	private String tag="";
	private ArrayList<Component> components = new ArrayList<Component>();
	
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
	public void addComponent(Component c){
		c.setGameObjectTag(tag);
		components.add(c);
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
	
	public void init(){
		for(Component c : components)
			c.init();
	}
	
	//update all components
	public void update(){
		System.out.println("Updating all components in GameObject: " + tag + " (count:" + components.size() + ")");
		for(Component c : components)
			c.update();
	}
	
	public void draw(GraphicDrawer g){
		for(Component c : components)
			c.draw(g);
	}
	
	//get tag of this GameObject
	public String getTag(){
		return tag;
	}

	public GameObject(String tag){
		this.tag=tag;
	}
}
