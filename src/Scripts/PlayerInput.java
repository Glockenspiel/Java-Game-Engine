package Scripts;

import Components.Sprite;
import framework.Game;
import framework.GameObject;
import framework.Script;
import framework.Vector;

public class PlayerInput implements Script {

	private int speed=3;
	private boolean once=true;
	private Vector playerSize;
	
	public PlayerInput(Vector playerSize){
		this.playerSize=playerSize;
		
	}
	
	@Override
	public void execute(GameObject obj) {
		//move player
		Vector direction = Game.getInput().getDirectionInput();
		Vector displacement = Vector.multiply(direction, new Vector(speed,speed));
		obj.moveBy(displacement);
		//obj.moveBy(Vector.multiply(displacement, new Vector(-1,-1)));
	//	Game.getCamera().moveBy(displacement);
		

		//shoot
		if(Game.getInput().isKeyPressed('P')){
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
		Vector bulletSize = new Vector(16,8);
		
		bullet.add(new Sprite("laser.png", bulletSize.intX(), bulletSize.intY()));
		
		//draw bullet at front of player
		Vector offset = new Vector(playerSize.intX(),playerSize.intY()/2-bulletSize.intY()/2);
		bullet.moveTo(Vector.add(position, offset));
		bullet.add(new BulletScript(2,0));
		bullet.delete(4000);
		Game.addGameObject(bullet);
	}

}
