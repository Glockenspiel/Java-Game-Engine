package framework;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import levelloading.LevelConstructor;
import loaders.XmlLoader;
import misc.Time;
import misc.Timer;
import services.Camera;
import services.InputManager;
import services.KeyBoardInput;
import services.MouseInput;

public class Game {
	
	//all game objects currently being used in the game
	private static ArrayList<GameObject> objs  = new ArrayList<GameObject>();
	
	//buffers
	private static ArrayList<String> deleteBufferTag = new ArrayList<String>();
	private static ArrayList<Integer> deleteBufferIDs = new ArrayList<Integer>();
	private static ArrayList<GameObject> objsToAdd = new ArrayList<GameObject>();
	
	//managers
	private static ServiceManagerI serMan;
	private static LevelManagerI levelManager;

	//time for start of frame, used to calculate sleep time
	private static long startTime;
	private static boolean exitGame=false;
	
	private static Timer frameTimer = new Timer();
	
	
	//default constructor
	public Game(){
		serMan = new ServiceManager();
		levelManager = new LevelManager();
	}
	
	//service and level manager constructor
	public Game(ServiceManagerI serviceManager, LevelManagerI levelMan){
		serMan = serviceManager;
		levelManager = levelMan;
	}
	
	//returns the service manager object
	public static ServiceManagerI getServices(){
		return serMan;
	}
	
	//add a GameObject to the game
	public static void addGameObject(GameObject object){
		objsToAdd.add(object);
	}
	
	//request change level
	public static void changeLevel(Level level){
		levelManager.queueChangeLevel(level);
	}

	//loads level given
	public static void loadCurrentLevel(){
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
		
		levelManager.initCurrentLevel();
	}
	
	//returns the current level
	public static Level getCurrentLevel(){
		return levelManager.getCurrentLevel();
	}

	//start thread for game loop
	public static void start(Level level){
		levelManager.setCurrentLevel(level);
		
		gameLoop.start();
	}
	
	//thread for the main game loop
	private static Thread gameLoop = new Thread(){
		
		public void run(){
			gameLoop.setPriority(Thread.MAX_PRIORITY);
			serMan.checkInit();
			loadCurrentLevel();
			addObjs(); //add GameObject in buffer that were created when level is loaded
			frameTimer.setMicrosecs();
			//main loop
			boolean flag=true;
			while(flag){
				//start frame time
				startTime = System.currentTimeMillis();
				frameTimer.start();
				
				//add and delete Game Objects in buffer
				deleteGameObjects();
				addObjs();
				
				//checks if loading state is required and loads if so
				levelManager.loadLatestState();
				
				//update all GameObjects
				for(GameObject g : objs)
					g.update();

				//update new positions of collision shapes
				//as other GameObjects may have changed the current GameObjects position after the current GameObject updated
				for(GameObject g : objs)
					g.notifyCollisionShapes();
				
				//timer.start();
				//detect any collisions
				serMan.getCollisionManager().detect(objs);
				//timer.stopAndLog();
				
				//update camera once all GameObject positions are finalised
				serMan.getCamera().update();
				
				//draw Scene i.e. draw the level
				serMan.getWindow().drawScene();
				

				//change level if there is a request to change level
				levelManager.doChangeLevel();
				
				//serMan.getInputManager();
				//clear the input buffer
				InputManager.clearInput();
				//serMan.getInput().clear();
				//serMan.getMouse().clear();
				
				//check if there is a request to quit the game
				if(checkExitGame())
					flag=false;
				
				frameTimer.stopAndLog();
				//sleep so there is a limit to the number of frames per second
				try {Thread.sleep(calculateSleepTime());} 
		    	catch (InterruptedException e) {e.printStackTrace();}
			}

			//exit game if ended loop
			System.exit(0);
		}

		//checks if the escape game command is true
		private boolean checkExitGame() {
			if(Game.getServices().isDrawingDebug())
				if(Keyboard().isButtonDown(KeyEvent.VK_ESCAPE) && 
						Keyboard().isButtonDown(KeyEvent.VK_SHIFT))
					return true;
			return exitGame;
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
						deleteObjAtIndex(g);
						found=true;
					}
				}
			}
			deleteBufferTag.clear();
			
			//delete game objects with matching id
			for(Integer id : deleteBufferIDs){
				found=false;
				for(int g=0; g<objs.size() && !found; g++){
					if(objs.get(g).getID()==id){
						deleteObjAtIndex(g);
						found=true;
					}
				}
			}
			deleteBufferIDs.clear();
		}
		
		//deletes a GameObject from objs at the given index
		private void deleteObjAtIndex(int index){
			if(index>=0 && index<objs.size()){
				objs.get(index).dispose();
				objs.remove(index);
			}
		}

		//calculates the sleep time for this frame
		private long calculateSleepTime() {
			long executionTime= System.currentTimeMillis()-startTime;
			long sleepTime=(long) (Time.FRAME_TIME-executionTime);
			if(sleepTime<0) sleepTime=0;
			return sleepTime;
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
	
	//returns all GameObjects with a matching tag
	public static ArrayList<GameObject> getAllGameObjectsByTag(String tag){
		ArrayList<GameObject> gameObjs = new ArrayList<GameObject>();
		for(GameObject o : objs){
			if(o.getTag().equalsIgnoreCase(tag)){
				gameObjs.add(o);
			}
		}
		
		return gameObjs;
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
	
	//prints a message with the current filter state
	public static void print(String msg) {
		serMan.getFilterManager().filterRequest(msg);
	}
	
	//prints a message with the state specified
	public static void print(String msg, String stateName) {
		serMan.getFilterManager().filterRequest(msg, stateName);
	}

	//returns a shallow copy of all the GameObjects
	public static ArrayList<GameObject> copyOfGameObjects(){
		return new ArrayList<GameObject>(objs);
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
	
	//short hand for loading latest saveed state
	public static void loadLatestSave(){
		levelManager.load();
	}

	//short hand for getting the input service
	//this encapsulates level manager
	public static KeyBoardInput Keyboard(){
		return InputManager.getKeyBoard();
	}
	
	//short hand for getting the camera service
	public static Camera getCamera(){
		return serMan.getCamera();
	}

	//returns the time of when the execution started for the current frame
	public static long getFrameTime(){
		//return startTime;
		return frameTimer.calculateAvg()/1000;
	}
	
	public static void exit(){
		exitGame=true;
	}

	public static MouseInput Mouse() {
		return serMan.getMouse();
	}

	public static void addGameObjectWithoutBuffer(GameObject obj) {
		objs.add(obj);
	}

	public static void saveState() {
		String xmlString="";
		
		xmlString+="<Level>\n";
		xmlString+=LevelConstructor.getSharedObjStrings();
		//print(LevelConstructor.getSharedObjStrings());
		
		for(GameObject obj : objs){
		//	print(obj.getSaveString());
			xmlString+=obj.getSaveString();
		}
		
		xmlString+="</Level>";
		
		//print(xmlString);
		String levelName = Game.getCurrentLevel().getClass().getSimpleName();
		String filename = "Resources/Levels/"+levelName+".xml";
		try {
			XmlLoader.writeStringtoXML(xmlString, filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
