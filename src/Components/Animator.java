package components;

import java.util.HashMap;
import java.util.Map;

import misc.Vector;
import display.Drawer;
import framework.Component;
import framework.GameObject;

public class Animator extends Component {

	private static final String TYPE = "Animator";
	private Map<String, Animation> animations = new HashMap<String, Animation>();
	private Animation currentAnim = null;
	private String currentAnimState = "";
	
	//change current animation
	public boolean setCurrentAnimation(String stateName){
		if(animations.containsKey(stateName)){
			currentAnim = animations.get(stateName);
			currentAnimState = stateName;
			currentAnim.reset();
			return true;
		}
		return false;
	}
	
	//add an animation
	public void addAnimation(Animation anim, String stateName){
		animations.put(stateName, anim);
	}
	
	//get the state name of the current animation
	public String getCurrentState(){
		return currentAnimState;
	}

	//update the current animation
	@Override
	public void update(GameObject obj) {
		if(currentAnim!=null)
			currentAnim.update(obj);
	}

	//draw the current animation
	@Override
	public void draw(Drawer g, Vector objPos) {
		if(currentAnim!=null)
			currentAnim.draw(g,objPos);
	}
}
