package framework;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Collision.CollisionManager;
import Collision.CollisionManagerI;
import levels.Level1;
import levels.Level2;
import debugging.Print;
import debugging.SwingPrint;
import display.Camera;
import display.CameraSimple;
import display.SwingWindow;
import display.Window;

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
	private static Camera camera;
	private static boolean drawDebug=false;
	private static Level currentLevel;
	private static Level nextLevel;
	private static boolean changeLevel=false;
	private static boolean gameStarted=false;
	private static CollisionManagerI collisionManager;
	
	
	
	public Game(){}

	
	//sets the window type
	public static void setWindow(Window windowType){
		if(gameStarted){
			Game.print().log("Window cannot be set once game has started");
			return;
		}
		window=windowType;
	}
	
	public static void setInputType(Input inputType){
		if(gameStarted){
			Game.print().log("Input cannot be set once game has started");
			return;
		}
		input=inputType;
	}
	
	public static void setCollisionManager(CollisionManagerI cm){
		collisionManager = cm;
	}
	
	public static void setPrint(Print printType){
		if(gameStarted){
			Game.print().log("Print cannot be set once game has started");
			return;
		}
		print = printType;
	}
	
	public static void addGameObject(GameObject object){
		objsToAdd.add(object);
	}
	
	public static void changeLevel(Level level){
		changeLevel=true;
		nextLevel=level;
	}
	
	private static void doChangeLevel(){
		changeLevel=false;
		loadLevel(nextLevel);
	}
	
	//load level 
	private static void loadLevel(Level level){
		deleteBufferTag.clear();
		deleteBufferIDs.clear();
		
		for(GameObject g : objsToAdd)
			g.interruptThreads();
		objsToAdd.clear();

		//remove GameObjects that are not global
		for(int i=0; i<objs.size(); i++){
			if(objs.get(i).getIsGlobal()==false){
				objs.get(i).interruptThreads();
				objs.remove(i);
				i--;
			}
		}
		
		
		
		currentLevel = level;
		level.init();
	}
	
	public static Level getCurrentLevel(){
		return currentLevel;
	}

	
	//checks to see if all attributes have been initialised correctly
	//if not this method sets them to the defaults
	private static void checkInit(){
		if(window==null)
			window = new SwingWindow(100,50,512,256,false, "Framework");
		
		if(camera==null)
			camera = new CameraSimple(0,0);
		
		if(input==null)
			input = new SwingInput();
		
		if(print==null)
			print = new SwingPrint();
		
		if(collisionManager==null)
			collisionManager = new CollisionManager();
	}
	
	//start thread for game loop
	public static void start(Level level){
		currentLevel = level;
		gameStarted=true;
		gameLoop.start();
	}
	
	private static Thread gameLoop = new Thread(){
		
		private long startTime;
		
		
		public void run(){
			//check initialisation was done correctly
			checkInit();
			loadLevel(currentLevel);
			addObjs();
			
			//main loop
			boolean flag=true;
			while(flag){
				startTime = System.currentTimeMillis();
				
				//add and delete Game Objects in buffer
				deleteGameObjects();
				addObjs();
				
				//input.update();
				for(GameObject g : objs)
					g.update();
				
				//update new positions of collision shapes
				//todo use observer design pattern to keep position updated
				for(GameObject g : objs)
					g.updateCollisionShapes();
				
				collisionManager.detect(objs);
				//todo: update collision here
				camera.update();
				
				window.drawScene();

				
				if(changeLevel){
					doChangeLevel();
				}
				
				input.clear();
				
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

		private void addObjs() {
			for(GameObject o : objsToAdd){
				boolean flag=true;
				
				//only allow one global GameObject with the same tag
				//check if a global object with the same tag exists
				if(o.getIsGlobal()){
					for(int i=0; i<objs.size() && flag; i++){
						if(objs.get(i).getIsGlobal() && objs.get(i).getTag().equalsIgnoreCase(o.getTag())){
							flag=false;
						}
					}
				}
				
				//add if this GameObject has a unique tag
				if(flag)
				objs.add(o);	
			}
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
			long sleepTime=(long) (Time.FRAME_TIME-executionTime);
			if(sleepTime<0) sleepTime=0;
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
	
	public static GameObject getGameObjectById(int id){
		for(GameObject g: objs){
			if(g.getID()==id)
				return g;
		}
		print.log("Warning: no gameObject was found with id \"" + id+ "\"");
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
	
	public static Camera getCamera(){
		return camera;
	}
	
	public static Window getWindow(){
		return window;
	}
	
	//add tags to buffer which will be deleted later
	public static void deleteObjByID(int id){
		deleteBufferIDs.add(id);
	}
	
	public static boolean objExistsWithTag(String tag){
		for(GameObject obj : objs)
			if(obj.getTag().equalsIgnoreCase(tag))
				return true;
		
		return false;
	}

}
