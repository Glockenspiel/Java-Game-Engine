package scripts;

import java.awt.event.KeyEvent;

import services.MouseInput;
import misc.Vector;
import components.Animator;
import components.AudioSource;
import demo.Bullet;
import framework.Game;
import framework.GameObject;
import framework.Script;

public class PlayerInput implements Script {

	private int speed=10,
			slowSpeed=10, 
			fastSpeed=20;
	private Vector playerSize;
	
	public PlayerInput(Vector playerSize){
		this.playerSize=playerSize;
	}
	
	//take player input and do commands with it
	@Override
	public void execute(GameObject obj) {
		//move player
		Vector direction = Game.Keyboard().getDirectionInput();
		Vector displacement = Vector.multiply(direction, new Vector(speed,speed));
		obj.moveBy(displacement.getDeltaVector());
		
		//shoot
		if(Game.Keyboard().isButtonPressed('P') || Game.Mouse().isButtonPressed(MouseInput.LEFT)){
			shoot(obj.getPosition());
		}
		
		//enable boost
		char boostKey = KeyEvent.VK_SPACE;
		if(Game.Keyboard().isButtonDown(boostKey)){
			speed = fastSpeed;
		}
		else{
			speed = slowSpeed;
		}

		//display animation for boost
		Animator a = (Animator) obj.getComponentByType(Animator.class);
		if(a!=null){
			if(Game.Keyboard().isButtonDown(boostKey) && a.getCurrentState()!="big"){
					a.setCurrentAnimation("big");
			}
			else if(Game.Keyboard().isButtonUp(boostKey)  && a.getCurrentState()!="small"){
				a.setCurrentAnimation("small");
			}
		}
	}

	//spawn bullet object
	private void shoot(Vector position) {
		Bullet bullet = new Bullet("bullet"); //new GameObject("bullet");

		//draw bullet at front of player
		Vector offset = new Vector(playerSize.intX(),playerSize.intY()/2-bullet.getSize().intY()/2);
		bullet.moveTo(Vector.add(position, offset));
		bullet.delete(4000);
		Game.addGameObject(bullet);
		GameObject obj = new GameObject("bang");
		obj.add(new AudioSource("bullet.wav", true));
		obj.delete(3000);
		Game.addGameObject(obj);
	}

	@Override
	public void construct(String[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String[] getSaveArgs() {
		// TODO Auto-generated method stub
		return null;
	}
}
