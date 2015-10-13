package components;

import java.util.HashMap;
import java.util.Map;

import display.Drawer;
import framework.Component;
import framework.GameObject;
import framework.Vector;

public class Animator extends Component {

	private static final String TYPE = "Animator";
	private Map<String, Animation> animations = new HashMap<String, Animation>();
	private Animation currentAnim = null;
	private String currentAnimState = "";
	
	
	public boolean setCurrentAnimation(String stateName){
		if(animations.containsKey(stateName)){
			currentAnim = animations.get(stateName);
			currentAnimState = stateName;
			currentAnim.reset();
			return true;
		}
		return false;
	}
	
	public void addAnimation(Animation anim, String stateName){
		animations.put(stateName, anim);
	}
	
	public String getCurrentState(){
		return currentAnimState;
	}
	
	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public void update(GameObject obj) {
		if(currentAnim!=null)
			currentAnim.update(obj);
	}

	@Override
	public void draw(Drawer g, Vector objPos) {
		if(currentAnim!=null)
			currentAnim.draw(g,objPos);
	}

	@Override
	public void interruptThreads() {}

}
