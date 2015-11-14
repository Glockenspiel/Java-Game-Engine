package framework;

import filtering.PrintFilterManager;
import saving.LoadingStateI;
import saving.SavingI;
import services.Camera;
import services.CollisionManagerI;
import services.KeyBoardInput;
import services.MouseInput;
import services.Print;
import services.Window;

public interface ServiceManagerI {
	//setters
	public void setWindow(Window windowType);
	public void setInputType(KeyBoardInput inputType);
	public void setCollisionManager(CollisionManagerI cm);
	public void setPrintTarget(Print printType);
	public void setLoading(LoadingStateI stateLoader);
	public void setSaving(SavingI savingI);
	
	//check everything was set
	public void checkInit();
	
	//getters
	public KeyBoardInput getInput();
	public Camera getCamera();
	public Window getWindow();
	public CollisionManagerI getCollisionManager();
	public LoadingStateI getLoading();
	public SavingI getSaving();
	public PrintFilterManager getFilterManager();
	public MouseInput getMouse();
	
	//getter and setter for boolean
	public void setDebugDraw(boolean isOn);
	public boolean isDrawingDebug();
}
