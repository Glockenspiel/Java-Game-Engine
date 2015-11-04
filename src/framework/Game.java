package framework;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import saving.GameState;
import saving.GameStateI;
import saving.LoadingState;
import saving.LoadingStateI;
import saving.Saving;
import saving.SavingI;
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
	
	//all game objects currently being used in the game
	private static ArrayList<GameObject> objs  = new ArrayList<GameObject>();
	
	//buffers
	private static ArrayList<String> deleteBufferTag = new ArrayList<String>();
	private static ArrayList<Integer> deleteBufferIDs = new ArrayList<Integer>();
	private static ArrayList<GameObject> objsToAdd = new ArrayList<GameObject>();
	
	
	private static ServiceManager serMan;
	
	//stores the current level and the desired next level
	private static Level currentLevel;
	private static Level nextLevel;
	
	//flags
	private static boolean drawDebug=false;
	private static boolean changeLevel=false;
	private static boolean gameStarted=false;
	private static boolean load=false;
	
	//time for start of frame
	private static long startTime;
	
	
	//default constructor
	public Game(){
		serMan = new ServiceManager();
	}
	
	public static ServiceManager getServiceManager(){
		return serMan;
	}
	
	//add a GameObject to the game
	public static void addGameObject(GameObject object){
		objsToAdd.add(object);
	}
	
	//request change level
	public static void changeLevel(Level level){
		changeLevel=true;
		nextLevel=level;
	}
	
	//change the level
	private static void doChangeLevel(){
		if(changeLevel){
			changeLevel=false;
			loadLevel(nextLevel);
		}
	}
	
	//loads level given
	private static void loadLevel(Level level){
		//clear all the buffers
		deleteBufferTag.clear();
		deleteBufferIDs.clear();

		objsToAdd.clear();

		//remove all GameObjects that are not global
		for(int i=0; i<objs.size(); i++){
			if(objs.get(i).getIsGlobal()==false){
				objs.remove(i);
				i--;
			}
		}
		
		
		//initialise level
		currentLevel = level;
		level.init();
	}
	
	//returns the current level
	public static Level getCurrentLevel(){
		return currentLevel;
	}

	//start thread for game loop
	public static void start(Level level){
		currentLevel = level;
		gameStarted=true;
		gameLoop.start();
	}
	
	//thread for the main game loop
	private static Thread gameLoop = new Thread(){
		
		public void run(){
			//check initialisation was done correctly and load the starting level
			serMan.checkInit();
			loadLevel(currentLevel);
			addObjs(); //add GameObject in buffer that were created when level is loaded
			
			//main loop
			boolean flag=true;
			while(flag){
				//start frame time
				startTime = System.currentTimeMillis();
				
				//add and delete Game Objects in buffer
				deleteGameObjects();
				addObjs();
				
				//checks if loading state is required and loads if so
				loadLatestState();
				
				//update all GameObjects
				for(GameObject g : objs)
					g.update();
				
				//update new positions of collision shapes
				//as other GameObjects may have changed the current GameObjects position after the current GameObject updated
				for(GameObject g : objs)
					g.updateCollisionShapes();
				
				//detect any collisions
				serMan.getCollisionManager().detect(objs);
				
				//update camera once all GameObject positions are finalised
				serMan.getCamera().update();
				
				//draw Scene i.e. draw the level
				serMan.getWindow().drawScene();

				//change level if there is a request to change level
				doChangeLevel();
				
				//clear the input buffer
				serMan.getInput().clear();
				
				//check if there is a request to quit the game
				if(checkExitGame())
					flag=false;
				
				//sleep so there is a limit to the number of frames per second
				try {Thread.sleep(calculateSleepTime());} 
		    	catch (InterruptedException e) {e.printStackTrace();}
			}
			
			//exit game if ended loop
			System.exit(0);
		}
		
		

		//checks if the escape game command is true
		private boolean checkExitGame() {
			if(serMan.getInput().isKeyDown((char)KeyEvent.VK_ESCAPE))
				return true;
			return false;
		}

		//adds GameObjects in buffer to the level
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

		//deletes GameObjects in level that are listed in the buffers
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
				//if(found==false)
				//	Game.print().log("GameObject tag not found when deleting GameObject by Tag : " + s);
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
				//if(found==false)
				//	Game.print().log("GameObject ID not found when deleting GameObject by ID : " + id);
			}
			deleteBufferIDs.clear();
		}

		private long calculateSleepTime() {
			long executionTime= System.currentTimeMillis()-startTime;
			long sleepTime=(long) (Time.FRAME_TIME-executionTime);
			if(sleepTime<0) sleepTime=0;
			return sleepTime;
		}
		
		public long getStartTime(){
			return startTime;
		}
	};
	
	//returns the first GameObject found with a matching tag
	public static GameObject getGameObjectByTag(String tag){
		for(GameObject g : objs)
			if(g.getTag().equalsIgnoreCase(tag))
				return g;
		
		print("Warning: no gameObject was found with tag \"" + tag+ "\"");
		return new GameObject("-1");
	}
	
	//returns the GameObject with a matching id
	public static GameObject getGameObjectById(int id){
		for(GameObject g: objs){
			if(g.getID()==id)
				return g;
		}
		print("Warning: no gameObject was found with id \"" + id+ "\"");
		return new GameObject("-1");
	}
	
	public static void print(String msg) {
		serMan.getPrint().log(msg);
	}

	//returns a shallow copy of all the GameObjects
	public static ArrayList<GameObject> copyOfGameObjects(){
		return new ArrayList<GameObject>(objs);
	}
	
	//turn debug drawing on or off
	public static void enableDebugDraw(boolean isOn) {
		drawDebug=isOn;
	}

	//returns true if drawing debug
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
	
	//returns true if a GameObject exists with a matching tag
	public static boolean objExistsWithTag(String tag){
		for(GameObject obj : objs)
			if(obj.getTag().equalsIgnoreCase(tag))
				return true;
		
		
		return false;
	}
	
	public static void load(){
		load=true;
	}
	
	public static void loadLatestState(){
		//if a load state is not required
		if(load==false) 
			return;
		
		//change flag to false
		load=false;
		
		GameStateI state = serMan.getSaving().getLastState();
		
		//if no state is saved yet return
		if(state==null) 
			return;
		
		serMan.getLoading().loadState(state);
	}

	public static long getFrameStartTime(){
		return startTime;
	}
}
