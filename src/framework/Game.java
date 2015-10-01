package framework;

import java.util.ArrayList;

import javax.swing.JFrame;

import Components.Sprite;
import Display.SwingWindow;
import Display.Window;
import Scripts.ExampleScript;

public class Game {
	
	private static ArrayList<GameObject> objs  = new ArrayList<GameObject>();
	//todo: have global data not bound to a single level, but to the game as a whole
	//such as player attributes, cross level information, etc.
	
	private static Window window;
	private static Input input;
	
	public Game(){}
	
	//sets the window type
	public void setWindow(Window window){
		this.window=window;
	}
	
	public void setInputType(Input input){
		this.input=input;
	}
	
	public void addGameObject(GameObject object){
		objs.add(object);
	}
	
	public void loadLevel(Level level){
		//intialise level and set new game objects
		level.init();
		objs = level.getGameObjects();
		
		//call initialise method in all gameObjects
		for(GameObject g : objs)
			g.init();
		
		//testIDs();
		start();
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
	
	//checks to see if all attributes have been initialised correctly
	//if not this method sets them to the defaults
	private static void checkInit(){
		if(window==null)
			window = new SwingWindow();
		
		if(input==null)
			input = new SwingInput();
	}
	
	private static void start(){
		gameLoop.start();
	}
	
	private static Thread gameLoop = new Thread(){
		
		private long startTime;
		private static final long FRAME_TIME = 30; //milliseconds allowed per frame
		
		public void run(){
			//check initialisation was done corrrectly
			checkInit();
			
			int i=0;
			//main loop
			boolean flag=true;
			while(flag){
				startTime = System.currentTimeMillis();
				input.update();
				//======================================
				//this code should be described in the behaviour of a component and is 
				//only here for and example of finding and modifying a component
				
				//get the component and modify the value
				ExampleScript ex = (ExampleScript)getGameObjectByTag("player").getComponentByType("Example"); 
				ex.setNum(69); 
	
				
				//get all the components of the same time
				//uncomment this to see behaviour of getting a single component above
				ArrayList<Component> comps = new ArrayList<Component>();
				getGameObjectByTag("player").getAllComponentsByType("Example", comps);
				for(Component c : comps){
					ExampleScript a = (ExampleScript)c;
					a.setNum(100);
				}
				
				
				//========================================
						
				for(GameObject g : objs){
					g.update();
				}
				
				window.drawScene();
						
				//dont loop forever
				i++;
				if(i>300)
				flag=false;
				
				try {Thread.sleep(calculateSleepTime());} 
		    	catch (InterruptedException e) {e.printStackTrace();}
			}
			
			System.out.println("Finished looping");
		}

		private long calculateSleepTime() {
			long executionTime= System.currentTimeMillis()-startTime;
			long sleepTime=FRAME_TIME-executionTime;
			return sleepTime;
		}
	};
	
	
	public static GameObject getGameObjectByTag(String tag){
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
	
	public static Input getInput(){
		return input;
	}
}
