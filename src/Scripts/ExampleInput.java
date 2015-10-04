package Scripts;

import framework.Game;
import framework.GameObject;
import framework.Script;
import framework.Vector;

public class ExampleInput implements Script {

	private int speed=3;
	private boolean once=true;
	
	@Override
	public void execute(GameObject obj) {
		//move player
		Vector direction = Game.getInput().getDirectionInput();
		Vector displacement = Vector.multiply(direction, new Vector(speed,speed));
		obj.moveBy(displacement);
		
		//deleting a GameObject with keyboard input
		if(once && Game.getInput().isKeyDown('Q')){
			Game.deleteObjByTag("map");
			once=false;
		}
	}

}
