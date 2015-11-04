package framework;

import saving.LoadingState;
import saving.LoadingStateI;
import saving.Saving;
import saving.SavingI;
import Collision.CollisionManager;
import Collision.CollisionManagerI;
import debugging.Print;
import debugging.SwingPrint;
import display.Camera;
import display.CameraSimple;
import display.SwingWindow;
import display.Window;

public class ServiceManager implements ServiceManagerI {
	
	//services
	private static Window window;
	private static Input input;
	private static Print print;
	private static SavingI saving;
	private static LoadingStateI loading;
	private static Camera camera;
	private static CollisionManagerI collisionManager;
	
	//flags
	private static boolean drawDebug;
	private static boolean finialised=false;
	
	public ServiceManager(){}
	
	//sets the window type
	public void setWindow(Window windowType){
		if(finialised){
			Game.print("Window cannot be set once game has started");
			return;
		}
		window=windowType;
	}
	
	//sets the input type
	public void setInputType(Input inputType){
		if(finialised){
			Game.print("Input cannot be set once game has started");
			return;
		}
		input=inputType;
	}
		
	//sets the collision manager
	public void setCollisionManager(CollisionManagerI cm){
		collisionManager = cm;
	}
	
	//sets the printing type
	public void setPrint(Print printType){
		if(finialised){
			Game.print("Print cannot be set once game has started");
			return;
		}
		print = printType;
	}
	
	//set state loader
	public void setLoading(LoadingStateI stateLoader){
		if(finialised){
			Game.print("Loading cannot be set once game has started");
			return;
		}
		loading = stateLoader;
	}
	
	//checks to see if all attributes have been initialised correctly for the game
	//if not this method sets them to the defaults
	public void checkInit(){
		int w=854,h=480; //default window size
		
		if(window==null)
			window = new SwingWindow(0,0,w,h,false, "Framework");
		
		if(window.getPreferredHeight()<=0 || window.getPreferredWidth()<=0){
			window.setPreferredSize(w, h);
		}
			
		if(saving==null)
			saving = new Saving();
		
		if(loading==null)
			loading = new LoadingState();
			
		if(camera==null)
			camera = new CameraSimple(0,0);
			
		if(input==null)
			input = new SwingInput();
			
		if(print==null)
			print = new SwingPrint();
			
		if(collisionManager==null)
			collisionManager = new CollisionManager();
		
		finialised=true;
	}
	
	//returns the input object
	public Input getInput(){
		return input;
	}

	//returns the print object
	public Print getPrint(){
		return print;
	}
	
	//returns the camera object
	public Camera getCamera(){
		return camera;
	}

	//returns the window object
	public Window getWindow(){
		return window;
	}
	
	//returns the collision manager object
	public CollisionManagerI getCollisionManager(){
		return collisionManager;
	}

	//returns the loading object
	public LoadingStateI getLoading() {
		return loading;
	}
	
	public SavingI getSaving() {
		return saving;
	}
	
	//turn debug drawing on or off
	public void enableDebugDraw(boolean isOn) {
		drawDebug=isOn;
	}
	
	//returns true if drawing debug
	public boolean isDrawingDebug() {
		return drawDebug;
	}
}
