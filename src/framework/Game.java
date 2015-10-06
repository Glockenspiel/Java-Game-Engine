package framework;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import Components.Sprite;
import Debugging.Print;
import Debugging.SwingPrint;
import Display.SwingWindow;
import Display.Window;

public class Game {
	
	private static ArrayList<GameObject> objs  = new ArrayList<GameObject>();
	private static ArrayList<String> deleteBufferTag = new ArrayList<String>();
	private static ArrayList<Integer> deleteBufferIDs = new ArrayList<Integer>();
	private static ArrayList<GameObject> objsToAdd = new ArrayList<GameObject>();
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
		objsToAdd.add(object);
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
	
	public Level getCurrentLevel(){
		return currentLevel;
	}

	
	//checks to see if all attributes have been initialised correctly
	//if not this method sets them to the defaults
	private static void checkInit(){
		if(window==null)
			window = new SwingWindow(512,256,false, "Framework");
		
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
			adObjs();
			int i=0;
			//main loop
			boolean flag=true;
			while(flag){
				startTime = System.currentTimeMillis();
				//input.update();
				
				
				
				for(GameObject g : objs)
					g.update();
				
				//todo: update collision here
				
				window.drawScene();
						
				//add and delete Game Objects in buffer
				deleteGameObjects();
				adObjs();
				
				input.clear();
				//don't loop forever
				i++;
				if(i>300)
				flag=false;
				
				
				if(checkExitGame())
					flag=false;
				
				try {Thread.sleep(calculateSleepTime());} 
		    	catch (InterruptedException e) {e.printStackTrace();}
			}
			
			//exit game if ended loop
			System.exit(0);
		}

		//checks if the escape game command is true
		private boolean checkExitGame() {
			if(input.isKeyDown((char)KeyEvent.VK_ESCAPE))
				return true;
			return false;
		}

		private void adObjs() {
			for(GameObject o : objsToAdd)
				objs.add(o);
			objsToAdd.clear();
		}

		private void deleteGameObjects() {
			boolean found;
			
			//delete game objects with matching id
			for(String s : deleteBufferTag){
				found=false;
				for(int g=0; g<objs.size() && !found; g++){
					if(objs.get(g).getTag().equalsIgnoreCase(s)){
						objs.remove(g);
						found=true;
					}
				}
				if(found==false)
					Game.print().log("GameObject tag not found when deleting GameObject by Tag : " + s);
			}
			deleteBufferTag.clear();
			
			//delete game objects with matching id
			for(Integer id : deleteBufferIDs){
				found=false;
				for(int g=0; g<objs.size() && !found; g++){
					if(objs.get(g).getID()==id){
						objs.remove(g);
						found=true;
					}
				}
				if(found==false)
					Game.print().log("GameObject ID not found when deleting GameObject by ID : " + id);
			}
			deleteBufferIDs.clear();
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
	
	//get input object
	public static Input getInput(){
		return input;
	}
	
	//display messages 
	public static Print print(){
		return print;
	}

	//turn debug drawing on or off
	public void enableDebugDraw(boolean isOn) {
		drawDebug=isOn;
	}

	//if drawing debugging help
	public static boolean isDrawingDebug() {
		return drawDebug;
	}
	
	//add tags to buffer which will be deleted later
	public static void deleteObjByTag(String tag){
		deleteBufferTag.add(tag);
	}
	
	//add tags to buffer which will be deleted later
	public static void deleteObjByID(int id){
		deleteBufferIDs.add(id);
	}
}
