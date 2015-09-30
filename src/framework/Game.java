package framework;

import java.util.ArrayList;

import javax.swing.JFrame;

import Components.ExampleComponent;
import Components.Sprite;
import Display.SwingWindow;
import Display.Window;

public class Game {
	
	private static ArrayList<GameObject> objs  = new ArrayList<GameObject>();
	
	Window window;
	
	public Game(){}
	
	//sets the window type
	public void setWindow(Window window){
		this.window=window;
	}
	
	public void loadLevel(String filename){
		//todo: load level from file
		//game objects hardcoded
		objs.add(new GameObject("ground"));
		objs.add(new GameObject("enemy"));
				
		GameObject p = new GameObject("Player");
		p.addComponent(new ExampleComponent()); //add component
		p.addComponent(new Sprite("box.png", 64,64)); //add component
		p.moveBy(new Vector(100,0)); //move GameObject
		objs.add(p);
		
		//call init() method in all gameObjects
		for(GameObject g : objs)
			g.init();
	}
	
	//test for ids
	public void testIDs(){
		//display IDs
		System.out.println("GameObject:ID");
		for(GameObject g : objs){
			System.out.println(g.getTag() + ":" + g.getID());
		}
		System.out.println(); //spacing
	}
	
	public void start(){
		//if window type is not defined, set to SwingWindow by default
		if(window==null)
			window = new SwingWindow();
		
		//main loop
		boolean flag=true;
		while(flag){
			
			//======================================
			//this code should be described in the behaviour of a component and is 
			//only here for and example of finding and modifying a component
			
			//get the component and modify the value
			ExampleComponent ex = (ExampleComponent)getGameObjectByTag("player").getComponentByType("Example"); 
			ex.setNum(69); 

			
			//get all the components of the same time
			//uncomment this to see behaviour of getting a single component above
			ArrayList<Component> comps = new ArrayList<Component>();
			getGameObjectByTag("player").getAllComponentsByType("Example", comps);
			for(Component c : comps){
				ExampleComponent a = (ExampleComponent)c;
				a.setNum(100);
			}
			
			//========================================
					
			for(GameObject g : objs){
				g.update();
			}
			
			window.drawScene();
					
			//only loop once for testing
			flag=false;
		}
	}
	
	
	public GameObject getGameObjectByTag(String tag){
		for(GameObject g : objs)
			if(g.getTag().equalsIgnoreCase(tag))
				return g;
		
		System.out.println("Warning: no gameObject was found with tag \"" + tag+ "\"");
		return new GameObject("-1");
	}
	
	//return a shallow copy of all the GameObjects
	public static ArrayList<GameObject> copyOfGameObjects(){
		return new ArrayList<GameObject>(objs);
	}
}
