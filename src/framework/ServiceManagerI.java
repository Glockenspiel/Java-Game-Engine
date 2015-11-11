package framework;

import filtering.PrintFilterManager;
import saving.LoadingStateI;
import saving.SavingI;
import services.Camera;
import services.CollisionManagerI;
import services.Input;
import services.Print;
import services.Window;

public interface ServiceManagerI {
	//setters
	public void setWindow(Window windowType);
	public void setInputType(Input inputType);
	public void setCollisionManager(CollisionManagerI cm);
	public void setPrintTarget(Print printType);
	public void setLoading(LoadingStateI stateLoader);
	public void setSaving(SavingI savingI);
	
	//check everything was set
	public void checkInit();
	
	//getters
	public Input getInput();
	public Camera getCamera();
	public Window getWindow();
	public CollisionManagerI getCollisionManager();
	public LoadingStateI getLoading();
	public SavingI getSaving();
	public PrintFilterManager getFilterManager();
	
	//getter and setter for boolean
	public void setDebugDraw(boolean isOn);
	public boolean isDrawingDebug();
}
