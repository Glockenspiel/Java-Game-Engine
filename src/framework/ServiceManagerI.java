package framework;

import saving.LoadingStateI;
import saving.SavingI;
import Collision.CollisionManagerI;
import debugging.Print;
import display.Camera;
import display.Window;

public interface ServiceManagerI {
	//setters
	public void setWindow(Window windowType);
	public void setInputType(Input inputType);
	public void setCollisionManager(CollisionManagerI cm);
	public void setPrint(Print printType);
	public void setLoading(LoadingStateI stateLoader);
	
	//check everything was set
	public void checkInit();
	
	//getters
	public Input getInput();
	public Print getPrint();
	public Camera getCamera();
	public Window getWindow();
	public CollisionManagerI getCollisionManager();
	public LoadingStateI getLoading();
	public SavingI getSaving();
}
