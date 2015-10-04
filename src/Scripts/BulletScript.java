package Scripts;

import framework.Vector;

import framework.GameObject;
import framework.Script;

public class BulletScript implements Script {

	private int speed=1;
	
	@Override
	public void execute(GameObject obj) {
		obj.moveBy(new Vector(speed,0));
	}

}
