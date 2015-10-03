package framework;

import java.util.ArrayList;

import javax.swing.JFrame;

import Components.Sprite;
import Debugging.Print;
import Debugging.SwingPrint;
import Display.SwingWindow;
import Display.Window;
import Scripts.ExampleScript;

public class Game {
	
	private static ArrayList<GameObject> objs  = new ArrayList<GameObject>();
	//todo: have global data not bound to a single level, but to the game as a whole
	//such as player attributes, cross level information, etc.
	
	private static Window window;
	private static Input input;
	private static Print print;
	private static boolean drawDebug=false;
	private static Level currentLevel;
	
	public Game(){}
	
	//sets the window type
	public void setWindow(Window window){
		this.window=window;
	}
	
	public void setInputType(Input input){
		this.input=input;
	}
	
	public void setPrint(Print print){
		this.print = print;
	}
	
	public static void addGameObject(GameObject object){
		checkDuplicateTag(object);
		objs.add(object);
	}
	
	//check if any duplicate Tags and give warning if there's a duplicate
	private static boolean checkDuplicateTag(GameObject object){
		for(GameObject o : objs){
			if(o.getTag().equalsIgnoreCase(object.getTag())){
				print.log("Warning: GameObject with the tag \"" + object.getTag() + "\" "
						+ "already exists. This may cause unexpected execution");
				return true;
			}
		}
		
		return false;
	}
	
	//load level 
	public void loadLevel(Level level){
		if(print==null)
			print = new SwingPrint();
		currentLevel = level;
		objs.clear();
		level.init();
	}

	
	//checks to see if all attributes have been initialised correctly
	//if not this method sets them to the defaults
	private static void checkInit(){
		if(window==null)
			window = new SwingWindow();
		
		if(input==null)
			input = new SwingInput();
		
		if(print==null)
			print = new SwingPrint();
	}
	
	//start thread for game loop
	public static void start(){
		gameLoop.start();
	}
	
	private static Thread gameLoop = new Thread(){
		
		private long startTime;
		private static final long FRAME_TIME = 30; //milliseconds allowed per frame
		
		public void run(){
			//check initialisation was done correctly
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

				for(GameObject g : objs)
					g.update();
				
				//todo: update collision here
				
				window.drawScene();
						
				//don't loop forever
				i++;
				if(i>300)
				flag=false;
				
				try {Thread.sleep(calculateSleepTime());} 
		    	catch (InterruptedException e) {e.printStackTrace();}
			}
			
			print.log("Finished looping");
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
		
		print.log("Warning: no gameObject was found with tag \"" + tag+ "\"");
		return new GameObject("-1");
	}
	
	//return a shallow copy of all the GameObjects
	public static ArrayList<GameObject> copyOfGameObjects(){
		return new ArrayList<GameObject>(objs);
	}
	
	public static Input getInput(){
		return input;
	}
	
	public static Print print(){
		return print;
	}

	public void enableDebugDraw(boolean isOn) {
		drawDebug=isOn;
	}

	public static boolean isDrawingDebug() {
		return drawDebug;
	}
}
