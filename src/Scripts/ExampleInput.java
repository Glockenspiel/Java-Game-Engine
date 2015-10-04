package Scripts;

import Components.Sprite;
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
		
		//shoot
		if(Game.getInput().isKeyDown('P')){
			shoot(obj.getPosition());
		}
		
		//deleting a GameObject with keyboard input
		if(once && Game.getInput().isKeyDown('Q')){
			Game.deleteObjByTag("map");
			once=false;
		}
	}

	//spawn bullet object
	private void shoot(Vector position) {
		GameObject bullet = new GameObject("bullet");
		bullet.add(new Sprite("box.png", 8,8));
		bullet.moveTo(position);
		bullet.add(new BulletScript());
		Game.addGameObject(bullet);
	}

}
